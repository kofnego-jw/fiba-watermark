package at.ac.uibk.fiba.watermark.stamper;

import ij.ImagePlus;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import at.ac.uibk.fiba.watermark.stamper.impl.ImageFileIO;

public class ImageFileIOTest {
	
	private static final boolean RUN_TEST = false;
	
	@BeforeClass
	public static void runTest() {
		Assume.assumeTrue(RUN_TEST);
	}
	
	@Test
	public void readGIF_writeJPEG() throws Exception {
		InputStream in = new FileInputStream(new File("../src/site/resources/images/logo.gif"));
		ImagePlus ip = ImageFileIO.readFile("GifTest", in, ImageFileType.GIF);
		InputStream out = ImageFileIO.writeImage(ip, ImageFileType.JPEG);
		FileUtils.copyInputStreamToFile(out, new File("./target/output/testGif.jpeg"));
	}

	@Test
	public void readJPEG_writeGIF() throws Exception {
		InputStream in = new FileInputStream(new File("../src/site/resources/images/logo.jpg"));
		ImagePlus ip = ImageFileIO.readFile("JpegTest", in, ImageFileType.JPEG);
		InputStream out = ImageFileIO.writeImage(ip, ImageFileType.GIF);
		FileUtils.copyInputStreamToFile(out, new File("./target/output/testJpeg.gif"));
	}
	
	@Test
	public void readTIFF_writePNG() throws Exception {
		InputStream in = new FileInputStream(new File("../src/site/resources/images/logo.tif"));
		ImagePlus ip = ImageFileIO.readFile("TiffTest", in, ImageFileType.TIFF);
		InputStream out = ImageFileIO.writeImage(ip, ImageFileType.PNG);
		FileUtils.copyInputStreamToFile(out, new File("./target/output/testTiff.png"));
	}
	
	@Test
	public void readPNG_writeTIFF() throws Exception {
		InputStream in = new FileInputStream(new File("../src/site/resources/images/logo.png"));
		ImagePlus ip = ImageFileIO.readFile("PngTest", in, ImageFileType.PNG);
		InputStream out = ImageFileIO.writeImage(ip, ImageFileType.TIFF);
		FileUtils.copyInputStreamToFile(out, new File("./target/output/testPNG.tif"));
	}
	
}
