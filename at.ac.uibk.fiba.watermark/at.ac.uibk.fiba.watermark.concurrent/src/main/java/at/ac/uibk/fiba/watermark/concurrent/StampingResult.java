package at.ac.uibk.fiba.watermark.concurrent;

import java.io.InputStream;

import at.ac.uibk.fiba.watermark.stamper.ImageFileType;

/**
 * DataObject for a StampingResult
 * @author totoro
 *
 */
public class StampingResult {
	
	/**
	 * The filename, of the request passed to the StamperWorker
	 */
	private final String filename;
	/**
	 * The result of the stamping
	 */
	private final InputStream result;
	/**
	 * The file type of the result
	 */
	private final ImageFileType fileType;
	
	public StampingResult(String filename, InputStream result, ImageFileType type) {
		super();
		this.filename = filename;
		this.result = result;
		this.fileType = type;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @return the result
	 */
	public InputStream getResult() {
		return result;
	}
	/**
	 * @return the fileType
	 */
	public ImageFileType getFileType() {
		return fileType;
	}
	
	

}
