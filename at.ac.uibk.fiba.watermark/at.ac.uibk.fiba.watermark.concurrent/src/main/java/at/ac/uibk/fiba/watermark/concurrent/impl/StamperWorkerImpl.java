package at.ac.uibk.fiba.watermark.concurrent.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.uibk.fiba.watermark.concurrent.StamperWorker;
import at.ac.uibk.fiba.watermark.concurrent.StampingRequest;
import at.ac.uibk.fiba.watermark.concurrent.StampingResult;
import at.ac.uibk.fiba.watermark.concurrent.StampingResultQueue;
import at.ac.uibk.fiba.watermark.stamper.Stamper;

/**
 * Default implementation of StamperWorker
 * @author totoro
 *
 */
public class StamperWorkerImpl implements StamperWorker {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StamperWorkerImpl.class);
	
	/**
	 * Waiting time for shutdown in Milliseconds
	 */
	private static final long SHUTDOWN_TIME = 3000;
	
	/**
	 * Default number of workers = 4
	 */
	protected static final int DEFAULT_NUMBER_OF_WORKERS = 4;
	
	/**
	 * The executorService, initialized by the constructor
	 */
	private ExecutorService workers;
	
	/**
	 * The stamper
	 */
	private final Stamper stamper;
	
	/**
	 * 
	 * @param stamper
	 */
	public StamperWorkerImpl(Stamper stamper) {
		this(stamper, DEFAULT_NUMBER_OF_WORKERS);
	}
	
	/**
	 * 
	 * @param stamper
	 * @param numberOfWorkers
	 */
	public StamperWorkerImpl(Stamper stamper, int numberOfWorkers) {
		this.stamper = stamper;
		workers = Executors.newFixedThreadPool(numberOfWorkers);
		LOGGER.debug("Stamper worker with {} threads created.", Integer.valueOf(numberOfWorkers));
	}
	
	@Override
	public StampingResultQueue submitStampingTasks(
			List<StampingRequest> tasks) {
		
		Map<StampingRequest,Future<StampingResult>> queue=  tasks.stream()
				.collect(Collectors.toMap(task -> task, request -> 
						workers.submit(new StampingCallable(stamper, request.getFilename(), 
								request.getOriginalInputStream(), 
								request.getOriginalType(), 
								request.getResize(), 
								request.getOutputType()))));
				
		
		return new StampingResultQueue(queue);
	}
	
	@Override
	public void close() throws Exception {
		LOGGER.info("StamperWorker closing...");
		workers.shutdown();
		if (!workers.awaitTermination(SHUTDOWN_TIME, TimeUnit.MILLISECONDS)) { 
			LOGGER.warn("StamperWorker does not shut down gracefully.");
            List<Runnable> droppedTasks = workers.shutdownNow(); //optional **
            LOGGER.warn("Forced shutdown with {} StampingRequests dropped.", Integer.valueOf(droppedTasks.size()));
        }
		
	}

}
