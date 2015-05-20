package at.ac.uibk.fiba.watermark.concurrent;

import java.util.List;

/**
 * StamperWorker, once created, one can submit multiple StampingRequests
 * @author totoro
 *
 */
public interface StamperWorker extends AutoCloseable {
	
	/**
	 * Main method
	 * @param tasks
	 * @return
	 */
	public StampingResultQueue submitStampingTasks(List<StampingRequest> tasks);

}
