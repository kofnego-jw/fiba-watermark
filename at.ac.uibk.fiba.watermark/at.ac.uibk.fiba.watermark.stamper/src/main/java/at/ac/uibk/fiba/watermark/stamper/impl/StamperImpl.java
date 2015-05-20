package at.ac.uibk.fiba.watermark.stamper.impl;

import ij.ImagePlus;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.uibk.fiba.watermark.stamper.ColorValue;
import at.ac.uibk.fiba.watermark.stamper.ImageFileType;
import at.ac.uibk.fiba.watermark.stamper.Stamper;
import at.ac.uibk.fiba.watermark.stamper.WatermarkException;
import at.ac.uibk.fiba.watermark.stamper.WatermarkSetting;

/**
 * Default implementation of stamper.
 * thread safe, once created.
 * @author totoro
 *
 */
public class StamperImpl implements Stamper {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StamperImpl.class);

	/**
	 * Watermark as imagePlus
	 */
	private final ImagePlus watermark;

	/**
	 * Watermark setting
	 */
	private final WatermarkSetting setting;

	public StamperImpl(ImagePlus watermark, WatermarkSetting setting) {
		this.watermark = watermark;
		this.setting = setting;
	}

	@Override
	public InputStream stamp(String originalPictPath, ImageFileType outputType)
			throws WatermarkException {
		
		LOGGER.debug("Stamping {}.", originalPictPath);
		
		InputStream in;
		try {
			in = new BufferedInputStream(new FileInputStream(originalPictPath));
		} catch (Exception e) {
			LOGGER.error("Cannot read original file.", e);
			throw new WatermarkException("Cannot read from " + originalPictPath + ": " + e.getMessage());
		}
		
		ImageFileType origType = ImageFileType.guessTypeByName(originalPictPath);
		if (origType==null)
			origType = DEFAULT_ORIGINAL_TYPE;
		
		return stamp(in, origType, outputType);
	}

	@Override
	public InputStream stamp(InputStream originalPict,
			ImageFileType originalType, ImageFileType outputType, float resize)
			throws WatermarkException {
		
		LOGGER.debug("Stamping an inpustream.");

		ImagePlus orig = ImageFileIO
				.readFile("tmp", originalPict, originalType);
		
		if (resize!=1F) {
			
			int dstWidth = (int) (orig.getWidth() * resize);
			int dstHeight = (int) (orig.getHeight() * resize);
			
			ImageProcessor resized = orig.getProcessor().resize(dstWidth, dstHeight, true);
			orig.setProcessor(resized);
		}

		ImagePlus stamped = stamp(orig);

		return ImageFileIO.writeImage(stamped, outputType);
	}

	protected ImagePlus stamp(ImagePlus orig) {

		WatermarkCalculator wc = new WatermarkCalculator(orig, watermark,
				setting);

		ImageProcessor proc = orig.getProcessor();
		ColorProcessor wmC = watermark.getProcessor()
				.resize(wc.getWmAppliedWidth(), wc.getWmAppliedHeight())
				.convertToColorProcessor();

		ColorProcessor procC = proc.convertToColorProcessor();

		Color backgroundColor = convertToColor(setting.getBackgroundColor());
		
		for (int i = 0; i < wc.getWmAppliedWidth(); i++) {
			for (int j = 0; j < wc.getWmAppliedHeight(); j++) {

				Color watermarkColor = wmC.getColor(i, j);
				
				if (backgroundColor!=null && watermarkColor.equals(backgroundColor)) {
					continue;
				}
				
				int x = wc.getWmAppliedPosX() + i;
				int y = wc.getWmAppliedPosY() + j;
				Color originalColor = procC.getColor(x, y);
				Color merged = mergeColor(originalColor, watermarkColor);
				procC.set(x, y, merged.getRGB());
				
			}
		}

		return new ImagePlus(orig.getTitle(), procC);
	}

	protected Color mergeColor(Color origC, Color wmC) {
		float oR = origC.getRed();
		float oG = origC.getGreen();
		float oB = origC.getBlue();

		float wR = wmC.getRed();
		float wG = wmC.getGreen();
		float wB = wmC.getBlue();
		
		int r, g, b;
		
		switch (setting.getStampingMode()) {
		case SUBSTRACT:
			r = (int) (oR - wR * (1 - setting.getOpacity()));
			g = (int) (oG - wG * (1 - setting.getOpacity()));
			b = (int) (oB - wB * (1 - setting.getOpacity()));
			break;
		default:
			r = (int) (oR * (1 - setting.getOpacity()) + wR * setting.getOpacity());
			g = (int) (oG * (1 - setting.getOpacity()) + wG * setting.getOpacity());
			b = (int) (oB * (1 - setting.getOpacity()) + wB * setting.getOpacity());
		}
		if (r<0) r = 0;
		if (g<0) g = 0;
		if (b<0) b = 0;
		if (r>255) r = 255;
		if (g>255) g = 255;
		if (b>255) b = 255;
		
		return new Color(r, g, b);
	}
	
	protected static Color convertToColor(ColorValue val) {
		if (val==null) return null;
		return new Color(val.getRed(), val.getGreen(), val.getBlue());
	}

}
