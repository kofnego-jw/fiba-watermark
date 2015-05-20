package at.ac.uibk.fiba.watermark.concurrent;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * The stamping result queue. Wrapper object around a List<Future<StampingResult>>
 * @author totoro
 *
 */
public class StampingResultQueue {
	
	/**
	 * The results
	 */
	private final Map<StampingRequest, Future<StampingResult>> results;
	
	public StampingResultQueue(Map<StampingRequest, Future<StampingResult>> results) {
		this.results = results;
	}
	
	/**
	 * 
	 * @return true if all future is done
	 */
	public boolean isFinished() {
		return ! results.entrySet().stream().filter(sr -> !sr.getValue().isDone())
				.findAny().isPresent();
	}
	
	/**
	 * 
	 * @return a partial List where the future is done
	 */
	public List<StampingResult> getFinishedResults() {
		return results.entrySet().stream().filter(f -> f.getValue().isDone())
				.map(f -> {
					try {
						return f.getValue().get();
					} catch (Exception e) {
						return null; // should never happen
					}
				}).filter(x -> x!=null)
				.collect(Collectors.toList());
	}

	/**
	 * @return the results
	 */
	public Map<StampingRequest, Future<StampingResult>> getResults() {
		return results;
	}
	
	/**
	 * 
	 * @return a number between 0 and 100
	 */
	public int progressInPercent() {
		if (isFinished()) return 100;
		List<StampingResult> fin = getFinishedResults();
		return fin.size() * 100 / results.size();
	}
	
	/**
	 *  
	 * @return a list of stamping request that are not yet done.
	 */
	public List<StampingRequest> getAwaitingRequests() {
		return results.entrySet().stream()
				.filter(x -> !x.getValue().isDone())
				.map(x -> x.getKey())
				.collect(Collectors.toList());
	}
	

}
