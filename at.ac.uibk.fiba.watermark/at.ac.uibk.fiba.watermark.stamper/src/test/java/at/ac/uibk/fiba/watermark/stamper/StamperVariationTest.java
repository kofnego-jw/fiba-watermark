package at.ac.uibk.fiba.watermark.stamper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import at.ac.uibk.fiba.watermark.stamper.WatermarkSetting.StampingMode;
import at.ac.uibk.fiba.watermark.stamper.impl.StamperFactoryImpl;

public class StamperVariationTest {
	
	private static final boolean RUN_TEST = false;
	
	private static final File OUTDIR = new File("./target/output/StamperVariation");
	
	
	
	private static StamperFactoryImpl stamperFactory;
	
	@BeforeClass
	public static void setUpStamper() throws Exception {
		Assume.assumeTrue(RUN_TEST);
		
		stamperFactory = new StamperFactoryImpl();		
		
	}
	
	@Test
	public void test_position() throws Exception {
		
		ByteArrayInputStream wm;
		byte[] wmContent = FileUtils.readFileToByteArray(new File("../src/site/resources/images/logo.png"));
		wm = new ByteArrayInputStream(wmContent);
		
		ByteArrayInputStream in;
		
		byte[] content = 
				FileUtils.readFileToByteArray(new File("../src/site/resources/material/test2.jpg"));
		in = new ByteArrayInputStream(content);
		
		WatermarkSetting setting = new WatermarkSetting(0.66F, 0.85F, 0.22F, 0.1F, 0.20F, ColorValue.WHITE, StampingMode.ADD);
		
		int counter = 1;;
		
		
		Stamper stamper = stamperFactory.getStamper(wm, ImageFileType.PNG, setting);
		
		InputStream out = stamper.stamp(in, ImageFileType.JPEG, ImageFileType.JPEG, 1F);
		File output = new File(OUTDIR, "quer" + Integer.toString(counter++) + ".jpg");
		FileUtils.copyInputStreamToFile(out, output);
		
	}
	
	@Test
	public void test_bestStamp() throws Exception {
		ByteArrayInputStream wm;
		byte[] wmContent = FileUtils.readFileToByteArray(new File("../src/site/resources/images/logo.png"));
		wm = new ByteArrayInputStream(wmContent);
		
		ByteArrayInputStream in;
		
		byte[] content = 
				FileUtils.readFileToByteArray(new File("../src/site/resources/material/test.tif"));
		in = new ByteArrayInputStream(content);
		
		int counter = 0;
		
		for (float opacity = 0.0F; opacity <=1; opacity +=0.05) {
			WatermarkSetting setting = 
					new WatermarkSetting(0.66F, 0.85F, 0.22F, 0.1F, opacity, ColorValue.WHITE, StampingMode.ADD);
			wm.reset();
			in.reset();
			
			Stamper stamper = stamperFactory.getStamper(wm, ImageFileType.PNG, setting);
			
			InputStream out = stamper.stamp(in, ImageFileType.TIFF, ImageFileType.JPEG, 1F);
			File output = new File(OUTDIR, "opacity" + Integer.toString(counter++) + ".jpg");
			FileUtils.copyInputStreamToFile(out, output);
			
		}
		
		
	}

}
