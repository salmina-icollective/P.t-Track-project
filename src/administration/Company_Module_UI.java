package administration;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Company_Module_UI {
	
	WebDriver driver;
     
	@BeforeTest
	public void Setup() throws InterruptedException {
		//WebDriver driver;
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.Chrome.driver", "externalDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://pt-track-sg-web-app.azurewebsites.net/?redirectUri=/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	
	}
	
	@Parameters({"emailid", "password"})
	@Test
	public void Validate_Login(String emailid, String password) throws InterruptedException {	
		
		driver.findElement(By.name("Email Address")).sendKeys(emailid);
		
		Thread.sleep(3000);
		//find password
		driver.findElement(By.id("password")).sendKeys(password);
		
		
		//click submit
		driver.findElement(By.id("next")).click();
		driver.findElement(By.xpath("//button[@id=\"next\" and @ type=\"submit\"]")).click();
		
		Thread.sleep(8000);
		String expTittle = "Dashboard | pt.Track";
		String actual_tittle = driver.getTitle();
		System.out.println(actual_tittle);
		
		if (expTittle.equals(actual_tittle)){
			System.out.println("Login sucessful");
			
		}else if(expTittle != actual_tittle) {
			System.out.println("Login Failed");
		Thread.sleep(4000);
			driver.quit();
		}
		//assert.assertEquals(actual_tittle, "actual_tittle");
		
	}
	@Test
	public void Admin_Nav_to_comp() throws InterruptedException {
		//driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Reports'])[1]/following::span[2]")).click();)
		driver.findElement(By.xpath("/html/body/section/aside/div/ul/li[2]/div/span[2]")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Company Management")).click();
		Thread.sleep(4000);
		
	}
	//A Company when selecting No on "Are you service provider option"
	@Test(dependsOnMethods = "Validate_Login")
	public void AddCompany_ServiceProviderNo() throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='primaryLayout']/main/div/div/div[2]/button/span[2]")).click();
		WebElement CompanyName = driver.findElement(By.id("companyName"));
		CompanyName.getCssValue("");
		driver.findElement(By.id("country")).click();
		Thread.sleep(2000);
		
		//Send keys functionality
		WebElement country = driver.findElement(By.id("country"));
		Thread.sleep(4000);
		country.sendKeys("Albania");
		Thread.sleep(4000);
		country.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(4000);
		country.sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("customerCode")).sendKeys("00001235988");
		
		//when selecting "No" on are you a service provider
		driver.findElement(By.xpath("//input[@class=\"ant-radio-input\" and @value=\"false\"]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("website")).sendKeys("www.my-company.com");
		driver.findElement(By.id("isBriefInTradeDateEditable")).click();
		driver.findElement(By.xpath("//button[@class=\"ant-btn ant-btn-primary\"]")).click();
		
		
		
		String tittle = driver.getTitle();
		System.out.println(tittle);
		Assert.assertEquals(tittle, "Company Management | pt.Track");

		Thread.sleep(8000);
		
	}
	
	//Add Company when selecting "Yes on "Are you service provider option and All on "Which companies do you service"
	@Test(dependsOnMethods = "Validate_Login")
	public void AddCompany_All() throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='primaryLayout']/main/div/div/div[2]/button/span[2]")).click();
		driver.findElement(By.id("companyName")).sendKeys("S21_Company");
		driver.findElement(By.id("country")).click();
		Thread.sleep(2000);
		
		
		//Send keys functionality
		WebElement country = driver.findElement(By.id("country"));
		Thread.sleep(4000);
		country.sendKeys("Albania");
		Thread.sleep(4000);
		country.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(4000);
		country.sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("customerCode")).sendKeys("000012359");
		
		//When selecting yes on are you a service provider 
		driver.findElement(By.xpath("//*[@id=\"isServiceProvider\"]/label[1]/span[1]/input")).click();
		driver.findElement(By.xpath("//*[@id=\"isServiceProvider\"]/label[1]/span[1]/input")).isSelected();
		driver.findElement(By.id("companyTypeId")).click();
		List<WebElement> companyTypes = driver.findElements(By.xpath("//div[@class=\"ant-select-item-option-content\"]"));
		for (WebElement companyType : companyTypes) {
			if (companyType.getText().equals("Execution Supplier")) {
				companyType.click();
				break;
			}
		}
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/form/div[7]/div/div/div/div[2]/div/div/div/div/div[1]/span")).click();
		driver.findElement(By.id("website")).sendKeys("www.my-company.com");
		driver.findElement(By.id("isBriefInTradeDateEditable")).click();
		driver.findElement(By.xpath("//button[@class=\"ant-btn ant-btn-primary\"]")).click();
		
		
		
		String tittle = driver.getTitle();
		System.out.println(tittle);
		Assert.assertEquals(tittle, "Company Management | pt.Track");

		Thread.sleep(8000);
	}
	
	//Add Company when selecting "Yes on "Are you service provider option and All on "Which companies do you service"
	@Test(dependsOnMethods = "Validate_Login")
	public void AddCompany_SelectCompanies() throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='primaryLayout']/main/div/div/div[2]/button/span[2]")).click();
		driver.findElement(By.id("companyName")).sendKeys("S22_Company");
		driver.findElement(By.id("country")).click();
		Thread.sleep(2000);
		
		//Send keys functionality
		WebElement country = driver.findElement(By.id("country"));
		Thread.sleep(4000);
		country.sendKeys("Egypt");
		Thread.sleep(4000);
		country.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(4000);
		country.sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("customerCode")).sendKeys("00001235688");
		
		//When selecting yes on are you a service provider 
		driver.findElement(By.xpath("//*[@id=\"isServiceProvider\"]/label[1]/span[1]/input")).click();
		driver.findElement(By.xpath("//*[@id=\"isServiceProvider\"]/label[1]/span[1]/input")).isSelected();
		driver.findElement(By.id("companyTypeId")).click();
		List<WebElement> companyTypes = driver.findElements(By.xpath("//div[@class=\"ant-select-item-option-content\"]"));
		for (WebElement companyType : companyTypes) {
			if (companyType.getText().equals("Execution Supplier")) {
				companyType.click();
				break;
			}
		}
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/form/div[7]/div/div/div/div[2]/div/div/div/div/div[2]/div")).click();
		Thread.sleep(2000);
		//Select Companies pop up
		
		//search
		driver.findElement(By.xpath("//input[@id=\"searchText\" and @placeholder=\"Enter company here\"]")).sendKeys("s");
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/form/div[2]/div/div[1]/div/div[2]/div/div/span/span/span[2]/button/span")).click();
		Thread.sleep(4000);
		List<WebElement> results = driver.findElements(By.xpath("//*[@class=\"ant-checkbox-wrapper ant-checkbox-wrapper-in-form-item\"]"));
		
		System.out.println(results.size());
		
		for (int i=0; i<results.size(); i++) {
			
			System.out.println(results.get(i).getText());
			if (results.get(i).getText().contains("s")) {
				System.out.println("search passed");
			}
				
		}
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/form/div[2]/div/div[1]/div/div[2]/div/div/span/span/span[1]/span/span/span")).click();
		Thread.sleep(4000);
		
		//selecting companies
		List <WebElement> CompaniesLists = driver.findElements(By.className("ant-checkbox-input"));
		Random r = new Random();
		
		
		for (int CompLst = 0; CompLst<5; CompLst++) {
		int randomCompany = r.nextInt(CompaniesLists.size());
		CompaniesLists.get(randomCompany).click();
		}
		
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/form/div[5]/button[2]")).click();
		
		driver.findElement(By.id("website")).sendKeys("www.my-company.com");
		driver.findElement(By.id("isBriefInTradeDateEditable")).click();
		driver.findElement(By.xpath("//button[@class=\"ant-btn ant-btn-primary\"]")).click();
		
		
		
		String tittle = driver.getTitle();
		System.out.println(tittle);
		Assert.assertEquals(tittle, "Company Management | pt.Track");

		Thread.sleep(8000);
	}
	
	
	@Test(dependsOnMethods = "Validate_Login")
	public void Edit_Company() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[2]/td[8]/div/div[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("companyName")).clear();
		Thread.sleep(4000);
		driver.findElement(By.id("companyName")).sendKeys("Company_156");
		Thread.sleep(4000);
		driver.findElement(By.id("country")).click();
		Thread.sleep(2000);
		
		//Send keys functionality
		WebElement country = driver.findElement(By.id("country"));
		Thread.sleep(4000);
		country.sendKeys("South");
		Thread.sleep(4000);
		country.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(4000);
		country.sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("customerCode")).sendKeys("0000123565999");
		
		driver.findElement(By.xpath("//button[@class=\"ant-btn ant-btn-primary\"]")).click();
		
		String tittle = driver.getTitle();
		System.out.println(tittle);
	}
	
	@Test(dependsOnMethods = "Validate_Login")
	public void search() throws InterruptedException {
		driver.findElement(By.id("searchBy")).click();
		Thread.sleep(4000);
		List<WebElement> searchBy = driver.findElements(By.className("ant-select-item-option-content"));
		
		for (WebElement searchby : searchBy) {
			if (searchby.getText().equals("Company")) {
				
				searchby.click();
				break;
			}
		}
		
		
		//Search 
		driver.findElement(By.id("searchText")).sendKeys("sample");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"primaryLayout\"]/main/div/div[1]/div[1]/form/div/div/div[2]/div/div/span/div[2]/div/div/div/div/span/span/span[2]/button/span")).click();
		Thread.sleep(4000);
		List<WebElement> results = driver.findElements(By.xpath("//tr[@class=\"ant-table-row ant-table-row-level-0\"]"));
		System.out.println(results.size());
		
		for (int i=0; i<results.size(); i++) {
			
			System.out.println(results.get(i).getText());
			if (results.get(i).getText().contains("Mel")) {
				System.out.println("search passed");
			}
				
		}
	}
	@Test	
	public void Archive() throws InterruptedException {	
		driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[12]/td[8]/div/div[2]/span")).click();
		//Cancel archive
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div[2]/button[2]")).click();
		Thread.sleep(4000);
		String tittle = driver.getTitle();
		System.out.println(tittle);
		Assert.assertEquals(tittle, "Company Management | pt.Track");
		Thread.sleep(4000);
		
		//Yes Archive option
		driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[12]/td[8]/div/div[2]/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div[2]/button[1]")).click();
		Thread.sleep(2000);
		
		Assert.assertEquals(tittle, "Company Management | pt.Track");
		
	}
	static String sort;	
	@Test	
	
	public void ListCompany_UI() throws InterruptedException {	
		
		//Add button CSS verification
		
		WebElement AddCompButt  = driver.findElement(By.xpath("//*[@id=\"primaryLayout\"]/main/div/div[1]/div[2]/button"));
		String backColor = AddCompButt.getCssValue("background-color");
		System.out.println(backColor);
		
		String hexBackColor = Color.fromString(backColor).asHex();
		System.out.println(hexBackColor);
		
		
		String ActResult = AddCompButt.getCssValue("font-size");
		String ExpecResults = "16px";
		
		
		if (ActResult == ExpecResults) {
			
			System.out.println("Company Add button font size correc");
			
		}
		
		String ActFontWei = AddCompButt.getCssValue("font-weight");
		String ExpecFontWeig = "500";
		
		System.out.println(ActFontWei);
		if (ActFontWei.equals(ExpecFontWeig) ) {
			System.out.println("Company Add button font weight correc");
		}else {
			System.out.println("Add company button has Incorrect fontweight");
		}
		
		//Search UI verification 
		driver.findElement(By.id("searchBy")).click();
		
		List<WebElement> searchBy = driver.findElements(By.className("ant-select-item-option-content"));
		
		for (WebElement searchby : searchBy) {
			String fontSize = searchby.getCssValue("font-size");
			System.out.println(fontSize);
			
			
			String ExpectedFont = "14px";
			if (fontSize.equals(ExpectedFont) ) {
				
				System.out.println( "Dropdown elements has correct fonts");
			}else
			{
				System.out.println("Incorrect font size on dropdown elements");
			}
		}
		
		
	}

}
