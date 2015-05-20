package at.ac.uibk.fiba.watermark.stamper;

import ij.ImagePlus;

import java.awt.image.BufferedImage;

import org.junit.Test;

import at.ac.uibk.fiba.watermark.stamper.impl.WatermarkCalculator;

public class WatermarkCalculatorTest {

	protected static ImagePlus landscapeWM() {
		BufferedImage bi = new BufferedImage(2000, 1000, BufferedImage.TYPE_INT_RGB);
		return new ImagePlus("watermark", bi);
	}
	
	protected static ImagePlus squareWM() {
		BufferedImage bi = new BufferedImage(1500, 1500, BufferedImage.TYPE_INT_RGB);
		return new ImagePlus("watermark", bi);
	}
	
	protected static ImagePlus portraitWM() {
		BufferedImage bi = new BufferedImage(1000, 2000, BufferedImage.TYPE_INT_RGB);
		return new ImagePlus("watermark", bi);
	}
	
	protected static ImagePlus landscapeOrig() {
		BufferedImage bi = new BufferedImage(5000, 2500, BufferedImage.TYPE_INT_RGB);
		return new ImagePlus("watermark", bi);
	}
	
	protected static ImagePlus squareOrig() {
		BufferedImage bi = new BufferedImage(3000, 3000, BufferedImage.TYPE_INT_RGB);
		return new ImagePlus("watermark", bi);
	}
	
	protected static ImagePlus portraitOrig() {
		BufferedImage bi = new BufferedImage(2500, 5000, BufferedImage.TYPE_INT_RGB);
		return new ImagePlus("watermark", bi);
	}
	
	@Test
	public void portraitOrig_landscapeWM() {
		ImagePlus wm = landscapeWM();
		ImagePlus orig = portraitOrig();
		WatermarkSetting ws = WatermarkSetting.DEFAULT_SETTING;
		
		WatermarkCalculator wc = new WatermarkCalculator(orig, wm, ws);
		output(orig, wm, wc);
		
	}

	@Test
	public void portraitOrig_squareWM() {
		ImagePlus wm = squareWM();
		ImagePlus orig = portraitOrig();
		WatermarkSetting ws = WatermarkSetting.DEFAULT_SETTING;

		WatermarkCalculator wc = new WatermarkCalculator(orig, wm, ws);
		output(orig, wm, wc);

	}

	@Test
	public void portraitOrig_portraitWM() {
		ImagePlus wm = portraitWM();
		ImagePlus orig = portraitOrig();
		WatermarkSetting ws = WatermarkSetting.DEFAULT_SETTING;

		WatermarkCalculator wc = new WatermarkCalculator(orig, wm, ws);
		output(orig, wm, wc);

	}

	@Test
	public void landscapeOrig_landscapeWM() {
		ImagePlus wm = landscapeWM();
		ImagePlus orig = landscapeOrig();
		WatermarkSetting ws = WatermarkSetting.DEFAULT_SETTING;

		WatermarkCalculator wc = new WatermarkCalculator(orig, wm, ws);
		output(orig, wm, wc);

	}

	@Test
	public void landscapeOrig_squareWM() {
		ImagePlus wm = squareWM();
		ImagePlus orig = landscapeOrig();
		WatermarkSetting ws = WatermarkSetting.DEFAULT_SETTING;

		WatermarkCalculator wc = new WatermarkCalculator(orig, wm, ws);
		output(orig, wm, wc);

	}

	@Test
	public void landscapeOrig_portraitWM() {
		ImagePlus wm = portraitWM();
		ImagePlus orig = landscapeOrig();
		WatermarkSetting ws = WatermarkSetting.DEFAULT_SETTING;

		WatermarkCalculator wc = new WatermarkCalculator(orig, wm, ws);
		output(orig, wm, wc);

	}

	@Test
	 public void squareOrig_landscapeWM() {
		ImagePlus wm = landscapeWM();
		ImagePlus orig = squareOrig();
		WatermarkSetting ws = WatermarkSetting.DEFAULT_SETTING;

		WatermarkCalculator wc = new WatermarkCalculator(orig, wm, ws);
		output(orig, wm, wc);

	}

	@Test
	public void squareOrig_squareWM() {
		ImagePlus wm = squareWM();
		ImagePlus orig = squareOrig();
		WatermarkSetting ws = WatermarkSetting.DEFAULT_SETTING;

		WatermarkCalculator wc = new WatermarkCalculator(orig, wm, ws);
		output(orig, wm, wc);

	}

	@Test
	public void squareOrig_portraitWM() {
		ImagePlus wm = portraitWM();
		ImagePlus orig = squareOrig();
		WatermarkSetting ws = WatermarkSetting.DEFAULT_SETTING;

		WatermarkCalculator wc = new WatermarkCalculator(orig, wm, ws);
		output(orig, wm, wc);

	}


	protected static void output(ImagePlus orig, ImagePlus wm, WatermarkCalculator wc) {
		System.out.println("Original  Dimension: " + orig.getWidth() + " x " + orig.getHeight());
		System.out.println("Watermark Dimension: " + wm.getWidth() + " x " + wm.getHeight());
		System.out.println("  Applied Dimension: " + wc.getWmAppliedWidth() + " x " + wc.getWmAppliedHeight());
		System.out.println("  Applied Positions: x = " + wc.getWmAppliedPosX() + " / y = " + wc.getWmAppliedPosY());
	}
	
}
