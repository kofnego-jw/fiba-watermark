package at.ac.uibk.fiba.watermark.stamper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SaveWorker implements AutoCloseable {
	
	private ExecutorService workers = Executors.newFixedThreadPool(4);
	
	public SaveWorker() {
		super();
	}
	
	public void submit(List<SaveTask> tasks) {
		tasks.stream().forEach(task -> workers.submit(task));
	}
	
	@Override
	public void close() throws Exception {
		workers.shutdown();
		if (!workers.awaitTermination(1000, TimeUnit.MILLISECONDS))
			workers.shutdownNow();
	}

}
