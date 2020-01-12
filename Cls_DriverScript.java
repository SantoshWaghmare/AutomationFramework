import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Cls_DriverScript {
	// This Process Performs Uploading the images (.jpg format).
	
	
	@Test(priority=1)
	public void UploadJPGImage() throws Exception {
		Cls_Action_Script1.Open_Browser("chrome");
		Cls_Action_Script1.Navigate_Url(Cls_Object_Repository.URL);
		Cls_Action_Script1.LoginToHomePage(Cls_Object_Repository.UserName, Cls_Object_Repository.Password);
		Cls_Action_Script1.verifyHomePageTitle();
		Cls_Action_Script1.commonClickOnTab(Cls_Object_Repository.TabName);
		Cls_Action_Script1.commonClickOnFilesTab(Cls_Object_Repository.FileTabName);
		Cls_Action_Script1.clickOnUploadButton();
		Cls_Action_Script1.uploadFileUsingRobotClass(Cls_Object_Repository.ImgName, "10");
		Cls_Action_Script1.verifyImageUploadedSuccessfully(Cls_Object_Repository.ImgName);
		Cls_Action_Script1.commonClickButton("Continue");
		Cls_Action_Script1.JPGImgCropImage1(24,50);
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.JPGImgCropImage1(21,35);
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.JPGImgCropImage1(21,22	);
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.commonClickButton("Update File");
		Cls_Action_Script1.verifyImageFileAddedInList("img3");
		Cls_Action_Script1.deleteImagesFromList();
		Cls_Action_Script1.clickOnLogOutButton();
		Cls_Action_Script1.Close_Browser();
	}
	
	@AfterMethod	
	public void TearDown(ITestResult result) throws Exception
	{
		if(ITestResult.FAILURE==result.getStatus());
		{
			System.out.println("The status is: "+result.getStatus());
			
			System.out.println("The TestCase Name is: "+result.getName());
			
			Cls_Action_Script1.takeScreenShotOfResult(result.getName());
			
			System.out.println("The Testcase is Failed,The TestCase Name is: "+result.getName());
			
			Cls_Action_Script1.Close_Browser(); 
		}
	
	}
	

	// This Process Performs Uploading the images (.Png format).
	@Test(priority=2)
	public void UploadPNGImage() throws Exception {
		Cls_Action_Script1.Open_Browser("chrome");
		Cls_Action_Script1.Navigate_Url(Cls_Object_Repository.URL);
		Cls_Action_Script1.LoginToHomePage(Cls_Object_Repository.UserName, Cls_Object_Repository.Password);
		Cls_Action_Script1.verifyHomePageTitle();
		Cls_Action_Script1.commonClickOnTab(Cls_Object_Repository.TabName);
		Cls_Action_Script1.commonClickOnFilesTab(Cls_Object_Repository.FileTabName);
		Cls_Action_Script1.clickOnUploadButton();
		Cls_Action_Script1.uploadFileUsingRobotClass(Cls_Object_Repository.PNGImage, "10");
		Cls_Action_Script1.verifyImageUploadedSuccessfully(Cls_Object_Repository.PNGImage);
		Cls_Action_Script1.commonClickButton("Continue");
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.commonClickButton("Crop Image");
		Cls_Action_Script1.commonClickButton("Update File");
		Cls_Action_Script1.verifyImageFileAddedInList("img");
		Cls_Action_Script1.deleteImagesFromList();
		Cls_Action_Script1.clickOnLogOutButton();
		Cls_Action_Script1.Close_Browser();
	}

	// This Process Performs Uploading the images (.gif format).
	@Test(priority=3)
	public void UploadGIFImage() throws Exception {
		Cls_Action_Script1.Open_Browser("chrome");
		Cls_Action_Script1.Navigate_Url(Cls_Object_Repository.URL);
		Cls_Action_Script1.LoginToHomePage(Cls_Object_Repository.UserName, Cls_Object_Repository.Password);
		Cls_Action_Script1.verifyHomePageTitle();
		Cls_Action_Script1.commonClickOnTab(Cls_Object_Repository.TabName);
		Cls_Action_Script1.commonClickOnFilesTab(Cls_Object_Repository.FileTabName);
		Cls_Action_Script1.clickOnUploadButton();
		Cls_Action_Script1.uploadFileUsingRobotClass(Cls_Object_Repository.GIFImage, "10");
		Cls_Action_Script1.verifyImageUploadedSuccessfully(Cls_Object_Repository.GIFImage);
		Cls_Action_Script1.commonClickButton("Continue");
		Cls_Action_Script1.commonClickButton("Update File");	
		Cls_Action_Script1.verifyImageFileAddedInList("img2");
		Cls_Action_Script1.deleteImagesFromList();
		Cls_Action_Script1.clickOnLogOutButton();
		Cls_Action_Script1.Close_Browser();
	}

	// This Process Performs Negative Testing(Uploading the Other Files e.g. Doc
	// or Excel.)
	// It Should Not Allow To upload Other files Accept Images.
	@Test(priority=5)
    public void UploadFileOnIMage()
    {
    	Cls_Action_Script1.Open_Browser("chrome");
		Cls_Action_Script1.Navigate_Url(Cls_Object_Repository.URL);
		Cls_Action_Script1.LoginToHomePage(Cls_Object_Repository.UserName, Cls_Object_Repository.Password);
		Cls_Action_Script1.verifyHomePageTitle();
		Cls_Action_Script1.commonClickOnTab(Cls_Object_Repository.TabName);
		Cls_Action_Script1.commonClickOnFilesTab(Cls_Object_Repository.FileTabName);
		Cls_Action_Script1.clickOnUploadButton();
		Cls_Action_Script1.uploadFileUsingRobotClass(Cls_Object_Repository.FileName, "10");
		Cls_Action_Script1.verifyErrorMsgInImageUpload(Cls_Object_Repository.errMsg);
		Cls_Action_Script1.clickOnLogOutButton();
		Cls_Action_Script1.Close_Browser();
    }
    
 // This Process Performs multiple Images Uplaod (GIF,PNG,JPG Format)
	@Test(priority=4)
     public void UploadMultipleIMages()
     {
     	Cls_Action_Script1.Open_Browser("chrome");
 		Cls_Action_Script1.Navigate_Url(Cls_Object_Repository.URL);
 		Cls_Action_Script1.LoginToHomePage(Cls_Object_Repository.UserName, Cls_Object_Repository.Password);
 		Cls_Action_Script1.verifyHomePageTitle();
 		Cls_Action_Script1.commonClickOnTab(Cls_Object_Repository.TabName);
 		Cls_Action_Script1.commonClickOnFilesTab(Cls_Object_Repository.FileTabName);
 		
 		Cls_Action_Script1.clickOnUploadButton();
 		Cls_Action_Script1.uploadFileUsingRobotClass(Cls_Object_Repository.ImgName, "10");
 		
 		Cls_Action_Script1.clickOnUploadButton();
 		Cls_Action_Script1.uploadFileUsingRobotClass(Cls_Object_Repository.PNGImage, "10");
 		
 		Cls_Action_Script1.clickOnUploadButton();
 		Cls_Action_Script1.uploadFileUsingRobotClass(Cls_Object_Repository.GIFImage, "10");
 		
 		Cls_Action_Script1.verifyImageUploadedSuccessfully(Cls_Object_Repository.ImgName);
 		Cls_Action_Script1.verifyImageUploadedSuccessfully(Cls_Object_Repository.PNGImage);
 		Cls_Action_Script1.verifyImageUploadedSuccessfully(Cls_Object_Repository.GIFImage);
 		Cls_Action_Script1.commonClickButton("Continue");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.commonClickButton("Crop Image");
 		Cls_Action_Script1.verifyImageFileAddedInList("img3");
 		Cls_Action_Script1.verifyImageFileAddedInList("img2");
 		Cls_Action_Script1.verifyImageFileAddedInList("img");
 		Cls_Action_Script1.deleteImagesFromList();
 		Cls_Action_Script1.clickOnLogOutButton();
 		Cls_Action_Script1.Close_Browser();
     }
    
}
