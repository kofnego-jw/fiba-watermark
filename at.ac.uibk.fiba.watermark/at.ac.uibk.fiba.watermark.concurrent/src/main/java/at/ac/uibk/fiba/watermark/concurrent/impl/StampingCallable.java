package at.ac.uibk.fiba.watermark.concurrent.impl;

import java.io.InputStream;
import java.util.concurrent.Callable;

import at.ac.uibk.fiba.watermark.concurrent.StampingResult;
import at.ac.uibk.fiba.watermark.stamper.ImageFileType;
import at.ac.uibk.fiba.watermark.stamper.Stamper;

/**
 * Callable implementation
 * @author totoro
 *
 */
public final class StampingCallable implements Callable<StampingResult> {
	
	/**
	 * Original picture
	 */
	private final InputStream original;
	
	/**
	 * Filename
	 */
	private final String filename;
	
	/**
	 * The stamper
	 */
	private final Stamper stamper;
	
	/**
	 * Original type
	 */
	private final ImageFileType originalType;
	
	/**
	 * Resize factor
	 */
	private final float resize;
	
	/**
	 * Output type
	 */
	private final ImageFileType outputType;
	
	public StampingCallable(Stamper stamper, String filename, InputStream original, 
			ImageFileType originalType, float resize, ImageFileType outputType) {
//		System.out.println("StampingCallable created for " + filename);
		this.stamper = stamper;
		this.filename = filename;
		this.original = original;
		this.originalType = originalType;
		this.resize = resize;
		this.outputType = outputType;
	}

	@Override
	public StampingResult call() throws Exception {
		
		InputStream result = stamper.stamp(original, originalType, outputType, resize);
		
		return new StampingResult(filename, result, outputType);
	}
	
	/**
	 * @return the original
	 */
	public InputStream getOriginal() {
		return original;
	}

	/**
	 * @return the stamper
	 */
	public Stamper getStamper() {
		return stamper;
	}

	/**
	 * @return the originalType
	 */
	public ImageFileType getOriginalType() {
		return originalType;
	}

	/**
	 * @return the outputType
	 */
	public ImageFileType getOutputType() {
		return outputType;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return the resize
	 */
	public float getResize() {
		return resize;
	}
	
	

}
