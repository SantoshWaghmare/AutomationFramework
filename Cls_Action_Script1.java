import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Cls_Action_Script1 {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Robot robot;

	// Function For Initialize the browser ******************************
	public static void Open_Browser(String BrowserName) {
		if (BrowserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (BrowserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (BrowserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
	}

	// Navigate to the URL**************************
	public static void Navigate_Url(String Url) {
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.navigate().to(Url);

	}

	// Login To The HomePage Process ********************
	public static void LoginToHomePage(String UserName, String Password) {
		driver.findElement(By.id("user")).sendKeys(UserName);

		driver.findElement(By.id("password")).sendKeys(Password);

		driver.findElement(By.xpath("//input[@value='Login']")).submit();
	}

	// Verify The HomePage Title*********************************
	public static void verifyHomePageTitle() {
		String Title = driver.getTitle();

		Assert.assertEquals(Cls_Object_Repository.DashBoard_Msg, Title);
	}

	// Common Function For Click On Any Tab In the
	// HomePage*****************************
	public static void commonClickOnTab(String TabName) {
		driver.findElement(By.xpath("//ul//a[normalize-space()='" + TabName + "']")).click();
	}

	// Common Function For Click On Files Tab In
	// WebPage*********************************
	public static void commonClickOnFilesTab(String FileTabName) {
		driver.findElement(By.xpath("//nav[@id='sub_nav']//a[normalize-space()='" + FileTabName + "']")).click();
	}

	//***************This Process used to set the file path in Desktop Pop Up window To Upload the File
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	//**************** This Process is used to upload the File/Images/Videos Using robot classes
	public static void uploadFileUsingRobotActions(String filePath) {
		setClipboardData(filePath);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			System.out.println("Before Robot Class");
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			System.out.println("After Robot Class");
		} catch (AWTException e) {
			System.out.println("In Exception Block");
			e.printStackTrace();
		}
	}

	//**************** This Process is used to upload the File/Images/Videos Using robot classes
	public static void uploadFileUsingRobotClass(String fileNameOnly, String thinkTime) {

		try {

			String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\testData\\AllImageDoc\\"
					+ fileNameOnly;
			System.out.println("Upload file Path: " + System.getProperty("user.dir")
					+ "\\src\\test\\resources\\testData\\AllImageDoc\\" + fileNameOnly);
			uploadFileUsingRobotActions(filePath);
			waitFor(thinkTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Method : waitFor
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param :
	 *            timeUnitSeconds - wait time seconds
	 */
	public static boolean waitFor(String timeUnitSeconds) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(Integer.parseInt(timeUnitSeconds), TimeUnit.SECONDS));
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	//*****************Click On Upload Button 
	public static void clickOnUploadButton() {
		driver.findElement(By.id("file_manager_dropzone")).click();
	}

	//**************Verify wheather Image is uploaded successfully or not
	public static void verifyImageUploadedSuccessfully(String ImgName) {
		WebElement ele = driver.findElement(By
				.xpath("//form//div[normalize-space()='Check']//preceding::div[normalize-space()='" + ImgName + "']"));

		wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.visibilityOf(ele));

		boolean bln = ele.isDisplayed();

		Assert.assertEquals(bln, true);

	}

	//**************Verify wheather error msg occurs or not while uploading the FIle or video's in Image upload section
	public static void verifyErrorMsgInImageUpload(String errorMsg) {
		WebElement ele = driver.findElement(
				By.xpath("//form[@id='file_manager_dropzone']//span[normalize-space()='" + errorMsg + "']"));

		wait.until(ExpectedConditions.visibilityOf(ele));

		boolean bln = ele.isDisplayed();

		Assert.assertEquals(bln, true);
	}

	//Crop Functionality with x-axis and y-axis
	public static void JPGImgCropImage1(int horizontalOffset, int verticalOffset) {
		// Using Action class for drag and drop.
		Actions act = new Actions(driver);

		WebElement target = driver.findElement(By.xpath("(//div[@class='jcrop-holder']/div)[1]"));

		act.clickAndHold(target).moveByOffset(horizontalOffset, verticalOffset).build().perform();
	}

	//***************Common Functionality for click on Buttons.
	public static void commonClickButton(String buttonName) {

		WebElement ele = driver.findElement(By.xpath("//input[@value='" + buttonName + "']"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

		wait = new WebDriverWait(driver, 5);

		wait.until(ExpectedConditions.elementToBeClickable(ele));

		ele.click();
	}

	//Verify Wheather Images/files/videos successfully added in the list or not
	public static void verifyImageFileAddedInList(String fileName) {
		WebElement ele = driver.findElement(
				By.xpath("//ul[@id='js-file-manager-results']//li//a[contains(text(),'" + fileName + "')]"));

		boolean bln = ele.isDisplayed();

		Assert.assertTrue(bln);

	}

	//***********Delete all the images available in the list
	public static void deleteImagesFromList()
		{
			List<WebElement> ls=driver.findElements(By.xpath("//ul[@id='js-file-manager-results']//li"));
			
			int size=ls.size();
			
			for(int i=1;i<=size;i++)
			{
				WebElement els=driver.findElement(By.xpath("(//ul[@id='js-file-manager-results']//li//section//a[@class='icon_delete'])[1]"));
				if(els.isDisplayed())
                 {
                	 els.click();
                	 commonClickButton("Delete File");
                 }
			}
			
		}
	
	//******************Click On LogOut Buttons
	public static void clickOnLogOutButton()
	{
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
	}

	//***********************Below function is used to take screenshot
	public static void takeScreenShotOfResult(String FilePath) throws Exception
	{

		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		 // now copy the  screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\src\\test\\resources\\FailureScreenShots\\"+FilePath+".png"));

	}
	
	
	//*******************Close the browser window.
	public static void Close_Browser() {
		driver.close();
	}
}
