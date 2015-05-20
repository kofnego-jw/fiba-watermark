package at.ac.uibk.fiba.watermark.stamper.impl;

import ij.ImagePlus;
import at.ac.uibk.fiba.watermark.stamper.WatermarkSetting;

/**
 * Watermark calculator, used for the calculation of the watermark
 * @author totoro
 *
 */
public class WatermarkCalculator {
	
	private final float factor;
	
	private final int wmAppliedWidth, wmAppliedHeight;
	
	private final int wmAppliedPosX, wmAppliedPosY;
	
	public WatermarkCalculator(ImagePlus orig, ImagePlus watermark, WatermarkSetting setting) {
		float origWidth = orig.getWidth();
		float origHeight = orig.getHeight();
		
		float watermarkWidth = watermark.getWidth();
		float watermarkHeight = watermark.getHeight();
		
		float sizeX = setting.getSizeX();
		float sizeY = setting.getSizeY();
		
		float useWidthWMSizeFactor = (origWidth * sizeX) / watermarkWidth ;
		float useHeightWMSizeFactor = (origHeight * sizeY) / watermarkHeight;
		
		factor = (useWidthWMSizeFactor < useHeightWMSizeFactor) ?
				useWidthWMSizeFactor : useHeightWMSizeFactor;
		
		wmAppliedWidth = (int) (watermarkWidth * factor);
		wmAppliedHeight = (int) (watermarkHeight * factor);
		
		wmAppliedPosX = (int) (origWidth * setting.getPosX());
		wmAppliedPosY = (int) (origHeight * setting.getPoxY());
		
	}

	/**
	 * @return the factor
	 */
	public float getFactor() {
		return factor;
	}

	/**
	 * @return the wmAppliedWidth
	 */
	public int getWmAppliedWidth() {
		return wmAppliedWidth;
	}

	/**
	 * @return the wmAppliedHeight
	 */
	public int getWmAppliedHeight() {
		return wmAppliedHeight;
	}

	/**
	 * @return the wmAppliedPosX
	 */
	public int getWmAppliedPosX() {
		return wmAppliedPosX;
	}

	/**
	 * @return the wmAppliedPosY
	 */
	public int getWmAppliedPosY() {
		return wmAppliedPosY;
	}
	
	

}
