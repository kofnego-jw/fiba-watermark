package at.ac.uibk.fiba.watermark.stamper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import at.ac.uibk.fiba.watermark.concurrent.StamperWorker;
import at.ac.uibk.fiba.watermark.concurrent.StampingRequest;
import at.ac.uibk.fiba.watermark.concurrent.StampingResultQueue;
import at.ac.uibk.fiba.watermark.concurrent.impl.StamperWorkerFactoryImpl;
import at.ac.uibk.fiba.watermark.stamper.impl.StamperFactoryImpl;

public class StamperWorkerTest {

	private static final boolean RUN_TEST = false;
	
	private static StamperWorker stamperWorker;
	private static SaveWorker saveWorker;
	
	private static final File BASE1 = new File("../src/site/resources/material");
	private static final File BASE2 = new File("../src/site/resources/samples1");
	
	
	@BeforeClass
	public static void setUpStamperWorker() throws Exception {
		Assume.assumeTrue(RUN_TEST);
		StamperWorkerFactoryImpl factory = new StamperWorkerFactoryImpl();
		StamperFactory sf = new StamperFactoryImpl();
		factory.setStamperFactory(sf);
		InputStream in = new FileInputStream("../src/site/resources/images/logo.png");
		stamperWorker = factory.getStamperWorker(in, ImageFileType.PNG, WatermarkSetting.DEFAULT_SETTING);
		saveWorker = new SaveWorker();
	}
	
	List<StampingRequest> createStampingRequests(File base) {
		return Stream.of(base.listFiles(f -> f.getName().toLowerCase().endsWith(".jpeg")))
			.map(f -> {
				try {
					InputStream in = new BufferedInputStream(new FileInputStream(f));
					return new StampingRequest(f.getName(), in, ImageFileType.JPEG, 0.66F, ImageFileType.JPEG);
				} catch (Exception e) {
					return null;
				}
			}).filter(x -> x!=null).
			collect(Collectors.toList());
	}
	
	@Test
	public void test_submitMultiple() throws Exception {
		StampingResultQueue resultsOne = stamperWorker.submitStampingTasks(createStampingRequests(BASE1));
		StampingResultQueue resultsTwo = stamperWorker.submitStampingTasks(createStampingRequests(BASE2));
		List<SaveTask> tasks1 = resultsOne.getResults()
				.entrySet().stream()
				.map(entry -> entry.getValue())
				.map(fsr -> new SaveTask(fsr, new File("./target/output/multi1"))).collect(Collectors.toList());
		saveWorker.submit(tasks1);
		List<SaveTask> tasks2 = resultsTwo.getResults().entrySet().stream()
				.map(entry -> entry.getValue())
				.map(fsr -> new SaveTask(fsr, new File("./target/output/multi2"))).collect(Collectors.toList());
		saveWorker.submit(tasks2);
		while (!resultsOne.isFinished() || ! resultsTwo.isFinished()) {
			
			System.out.println("Progress 1: " + resultsOne.progressInPercent());
			System.out.println("Progress 2: " + resultsTwo.progressInPercent());
			
			Thread.sleep(3000);
			
		}
		
		System.out.println("Progress 1: " + resultsOne.progressInPercent());
		System.out.println("Progress 2: " + resultsTwo.progressInPercent());
		
	}
	
	
	
	@AfterClass
	public static void cleanUp() throws Exception {
		if (stamperWorker!=null)
			stamperWorker.close();
		if (saveWorker!=null)
			saveWorker.close();
	}
	
}
