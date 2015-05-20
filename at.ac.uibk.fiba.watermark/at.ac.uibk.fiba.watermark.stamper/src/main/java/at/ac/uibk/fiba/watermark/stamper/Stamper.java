package at.ac.uibk.fiba.watermark.stamper;

import java.io.InputStream;

/**
 * Stamper
 * @author totoro
 *
 */
public interface Stamper {
	
	/**
	 * Default output type: JPEG
	 */
	public static final ImageFileType DEFAULT_OUTPUT_TYPE = ImageFileType.JPEG;
	
	/**
	 * Default original type: TIFF
	 */
	public static final ImageFileType DEFAULT_ORIGINAL_TYPE = ImageFileType.TIFF;
	
	/**
	 * 
	 * @param originalPictPath
	 * @return an inputstream of stamped, using JPEG as output and TIFF as input.
	 * @throws WatermarkException
	 */
	default public InputStream stamp(String originalPictPath) throws WatermarkException {
		return stamp(originalPictPath, DEFAULT_OUTPUT_TYPE);
	}
	
	/**
	 * Stamp a original type, guess the type by name
	 * @param originalPictPath
	 * @param outputType
	 * @return
	 * @throws WatermarkException
	 */
	public InputStream stamp(String originalPictPath, ImageFileType outputType) 
			throws WatermarkException;
	
	/**
	 * 
	 * @param originalPict
	 * @param originalType
	 * @param outputType
	 * @return
	 * @throws WatermarkException
	 */
	default public InputStream stamp(InputStream originalPict, ImageFileType originalType, 
			ImageFileType outputType) throws WatermarkException {
		return stamp(originalPict, originalType, outputType, 1F);
	}
	
	/**
	 * Main method
	 * @param originalPict
	 * @param originalType
	 * @param outputType
	 * @param resize
	 * @return
	 * @throws WatermarkException
	 */
	public InputStream stamp(InputStream originalPict, ImageFileType originalType, 
			ImageFileType outputType, float resize) throws WatermarkException;

}
