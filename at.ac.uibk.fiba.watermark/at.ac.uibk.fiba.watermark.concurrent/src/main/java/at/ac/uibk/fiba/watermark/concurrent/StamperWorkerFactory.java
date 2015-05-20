package at.ac.uibk.fiba.watermark.concurrent;

import java.io.InputStream;

import at.ac.uibk.fiba.watermark.stamper.ImageFileType;
import at.ac.uibk.fiba.watermark.stamper.WatermarkException;
import at.ac.uibk.fiba.watermark.stamper.WatermarkSetting;

/**
 * StamperWorkerFactory
 * @author totoro
 *
 */
public interface StamperWorkerFactory {
	
	/**
	 * 
	 * @param watermarkStream
	 * @param watermarkFileType
	 * @param setting
	 * @return A StamperWorker using the params passed to this method
	 * @throws WatermarkException
	 */
	public StamperWorker getStamperWorker(InputStream watermarkStream, ImageFileType watermarkFileType, WatermarkSetting setting)
			throws WatermarkException; 

}
