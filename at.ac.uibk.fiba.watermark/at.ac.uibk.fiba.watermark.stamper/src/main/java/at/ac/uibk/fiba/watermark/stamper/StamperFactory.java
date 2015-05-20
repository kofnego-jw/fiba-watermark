package at.ac.uibk.fiba.watermark.stamper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

public interface StamperFactory {

	/**
	 * Default watermark file type: PNG
	 */
	public static final ImageFileType DEFAULT_WATERMARK_FILETYPE = ImageFileType.PNG;

	/**
	 * 
	 * @param watermarkFilePath
	 * @return a stamper, using the default watermark setting and PNG as file type
	 * @throws WatermarkException
	 */
	default public Stamper getStamper(String watermarkFilePath) throws WatermarkException {
		ImageFileType type = ImageFileType.guessTypeByName(watermarkFilePath);
		if (type==null) type = DEFAULT_WATERMARK_FILETYPE;
		return getStamper(watermarkFilePath, type, WatermarkSetting.DEFAULT_SETTING);
	}

	/**
	 * Main method
	 * @param watermarkInputStream
	 * @param type
	 * @param setting
	 * @return a stamper, using the parameter passed to this method.
	 * @throws WatermarkException
	 */
	public Stamper getStamper(InputStream watermarkInputStream, ImageFileType type,
			WatermarkSetting setting) throws WatermarkException;

	/**
	 * 
	 * @param wmPath
	 * @param type
	 * @param setting
	 * @return stamper, cf. getStamper(InputStream, ImageFileType, WatermarkSetting)
	 * @throws WatermarkException
	 */
	default public Stamper getStamper(String wmPath, ImageFileType type, WatermarkSetting setting)
			throws WatermarkException {
		byte[] content;
		try {
			content = FileUtils.readFileToByteArray(new File(wmPath));
		} catch (Exception e) {
			throw new WatermarkException("Cannot read watermark file at '" + wmPath + "': " + e.getMessage());
		}
		
		return getStamper(new ByteArrayInputStream(content), type, setting);
	}
	
	/**
	 * 
	 * @param watermarkStream
	 * @param type
	 * @return Stamper using the default watermarkSetting
	 * @throws WatermarkException
	 */
	default public Stamper getStamper(InputStream watermarkStream, ImageFileType type) throws WatermarkException {
		return getStamper(watermarkStream, type, WatermarkSetting.DEFAULT_SETTING);
	}	

}
