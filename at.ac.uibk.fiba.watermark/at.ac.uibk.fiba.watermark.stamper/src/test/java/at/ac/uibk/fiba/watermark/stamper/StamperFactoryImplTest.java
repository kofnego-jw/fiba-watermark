package at.ac.uibk.fiba.watermark.stamper;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import at.ac.uibk.fiba.watermark.stamper.impl.StamperFactoryImpl;

public class StamperFactoryImplTest {

	private static final boolean RUN_TEST = false;

	@BeforeClass
	public static void runTest() {
		Assume.assumeTrue(RUN_TEST);
	}
	
	@Test
	public void create() throws Exception {
		StamperFactoryImpl impl = new StamperFactoryImpl();
		Stamper stamper = impl.getStamper(new FileInputStream("../src/site/resources/images/logo.png"), 
				ImageFileType.PNG, WatermarkSetting.DEFAULT_SETTING);
		Assert.assertNotNull(stamper);
	}

}
