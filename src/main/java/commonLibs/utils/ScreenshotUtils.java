package commonLibs.utils;

import java.io.File;

import org.openqa.selenium.TakeScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtils {
	private TakeScreenshot screenshot;
	
	public ScreenshotUtils(WebDriver driver) {
		screenshot = TakeScreenshot(driver);
	}
	
	public void captureAndSaveScreenshot(String filename) throws Exception{
		filename = filename.trim();
		File imgFile, tmpFile;
		
		imgFile = new File(filename);
		
		if(imgFile.exists()) {
			throw new Exception("File already exist");
		}
		
		tmpFile = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.moveFile(tmpFile, imgFile);
		
	}
	
}
