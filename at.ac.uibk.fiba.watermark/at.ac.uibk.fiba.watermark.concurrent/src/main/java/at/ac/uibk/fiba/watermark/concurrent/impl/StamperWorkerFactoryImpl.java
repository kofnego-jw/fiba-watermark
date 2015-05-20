package at.ac.uibk.fiba.watermark.concurrent.impl;

import java.io.InputStream;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import at.ac.uibk.fiba.watermark.concurrent.StamperWorker;
import at.ac.uibk.fiba.watermark.concurrent.StamperWorkerFactory;
import at.ac.uibk.fiba.watermark.stamper.ImageFileType;
import at.ac.uibk.fiba.watermark.stamper.Stamper;
import at.ac.uibk.fiba.watermark.stamper.StamperFactory;
import at.ac.uibk.fiba.watermark.stamper.WatermarkException;
import at.ac.uibk.fiba.watermark.stamper.WatermarkSetting;

/**
 * Default implementation of StamperWorkerFactory, needs a StamperFactory
 * @author totoro
 *
 */
@Component(provide=StamperWorkerFactory.class)
public class StamperWorkerFactoryImpl implements StamperWorkerFactory {
	
	/**
	 * StamperFactory, used to create the Stamper
	 */
	private StamperFactory stamperFactory;
	
	@Reference
	public void setStamperFactory(StamperFactory sf) {
		this.stamperFactory = sf;
	}

	@Override
	public StamperWorker getStamperWorker(InputStream watermarkInputStream,
			ImageFileType watermarkType, WatermarkSetting setting)
			throws WatermarkException {
		Stamper stamper = stamperFactory.getStamper(watermarkInputStream, watermarkType, setting);
		
		return new StamperWorkerImpl(stamper);
	}


	
}
