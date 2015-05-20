package at.ac.uibk.fiba.watermark.stamper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import at.ac.uibk.fiba.watermark.stamper.impl.StamperFactoryImpl;

public class StamperImplTest {
	
	private static final boolean RUN_TEST = false;
	
	private static StamperFactory stamperFactory;
	
	@BeforeClass
	public static void setUpImages() throws Exception {
		Assume.assumeTrue(RUN_TEST);
		stamperFactory = new StamperFactoryImpl();
	}
	
	
	@Test
	public void stampJpegAndResize() throws Exception {
		
		Stamper impl = stamperFactory.getStamper("../src/site/resources/images/logo.png");
		
		InputStream in = impl.stamp(new FileInputStream(new File("../src/site/resources/samples1/page.jpg")), 
				ImageFileType.JPEG, ImageFileType.JPEG, 0.33F);
		FileUtils.copyInputStreamToFile(in, new File("./target/output/stampedJpeg.jpg"));
	}

	@Test
	public void stampTiffAndSaveAsGif() throws Exception {
		
		Stamper impl = stamperFactory.getStamper("../src/site/resources/images/logo.png");
		
		InputStream in = impl.stamp(new FileInputStream(new File("../src/site/resources/samples1/page.tif")), ImageFileType.TIFF, ImageFileType.GIF);
		FileUtils.copyInputStreamToFile(in, new File("./target/output/stampedTiff.gif"));
	}
	@Test
	public void stampPng() throws Exception {
		
		Stamper impl = stamperFactory.getStamper("../src/site/resources/images/logo.png");
		
		InputStream in = impl.stamp(new FileInputStream(new File("../src/site/resources/samples1/page.png")), ImageFileType.PNG, ImageFileType.TIFF);
		FileUtils.copyInputStreamToFile(in, new File("./target/output/stampedPng.tif"));
	}
	@Test
	public void stampGif() throws Exception {
		
		Stamper impl = stamperFactory.getStamper("../src/site/resources/images/logo.png");
		
		InputStream in = impl.stamp(new FileInputStream(new File("../src/site/resources/samples1/page.gif")), ImageFileType.GIF, ImageFileType.PNG);
		FileUtils.copyInputStreamToFile(in, new File("./target/output/stampedGif.png"));
	}
	
	
}
