package at.ac.uibk.fiba.watermark.stamper.impl;

import ij.ImagePlus;

import java.io.InputStream;

import aQute.bnd.annotation.component.Component;
import at.ac.uibk.fiba.watermark.stamper.ImageFileType;
import at.ac.uibk.fiba.watermark.stamper.Stamper;
import at.ac.uibk.fiba.watermark.stamper.StamperFactory;
import at.ac.uibk.fiba.watermark.stamper.WatermarkException;
import at.ac.uibk.fiba.watermark.stamper.WatermarkSetting;

/**
 * Default implementation of the factory, no dependecies.
 * @author totoro
 *
 */
@Component(provide=StamperFactory.class)
public class StamperFactoryImpl implements StamperFactory {
	
	@Override
	public Stamper getStamper(InputStream watermarkInputStream,
			ImageFileType type, WatermarkSetting setting)
			throws WatermarkException {
		
		ImagePlus watermark = ImageFileIO.readFile("watermark", watermarkInputStream, type);
		
		return new StamperImpl(watermark, setting);
	}
}
