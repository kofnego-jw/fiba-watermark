package at.ac.uibk.fiba.watermark.stamper;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.commons.io.FileUtils;

import at.ac.uibk.fiba.watermark.concurrent.StampingResult;

public class SaveTask implements Callable<Void> {
	
	private final Future<StampingResult> result;
	private final File outputDir;
	
	public SaveTask(Future<StampingResult> r, File outputDir) {
		System.out.println("SaveTask created." );
		this.result = r;
		this.outputDir = outputDir;
	}

	@Override
	public Void call() throws Exception {
		
		
		StampingResult sr = result.get();
		
		File outputFile = new File(outputDir, sr.getFilename() + "." + sr.getFileType().getSuffix());
		
		System.out.println("Saving to " + outputFile.getAbsolutePath());
		
		FileUtils.copyInputStreamToFile(sr.getResult(), outputFile);
		
		return null;
	}

}
