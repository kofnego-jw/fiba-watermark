package at.ac.uibk.fiba.watermark.concurrent;

import java.io.InputStream;

import at.ac.uibk.fiba.watermark.stamper.ImageFileType;

/**
 * DataObject for Stamping Request
 * @author totoro
 *
 */
public final class StampingRequest {
	
	/**
	 * Filename of the request
	 */
	private final String filename;
	/**
	 * The original picture in an InputStream
	 */
	private final InputStream originalInputStream;
	/**
	 * The original type
	 */
	private final ImageFileType originalType;
	/**
	 * The desired output type
	 */
	private final ImageFileType outputType;
	
	/**
	 * The resize factor
	 */
	private final float resize;
	
	public StampingRequest(String filename, InputStream originalInputStream,
			ImageFileType originalType, float resize, ImageFileType outputType) {
		super();
		this.filename = filename;
		this.originalInputStream = originalInputStream;
		this.originalType = originalType;
		this.resize = resize;
		this.outputType = outputType;
	}
	public StampingRequest(String filename, InputStream originalInputStream,
			ImageFileType originalType, ImageFileType outputType) {
		this(filename, originalInputStream, originalType, 1.0F, outputType);
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @return the originalInputStream
	 */
	public InputStream getOriginalInputStream() {
		return originalInputStream;
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
	 * @return the resize
	 */
	public float getResize() {
		return resize;
	}
	
	

}
