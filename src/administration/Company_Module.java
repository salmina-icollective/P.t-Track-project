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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Company_Module {
	
	

	
	
	WebDriver driver;
     
	@BeforeTest
	public void Setup() throws InterruptedException {
		//WebDriver driver;
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.Chrome.driver", "externalDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://pt-track-sg-web-app.azurewebsites.net/?redirectUri=/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
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
		driver.findElement(By.id("companyName")).sendKeys("D30_Company");
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
	
	@DataProvider
	public void getTestData() {
		
	}
	
	
	
	@Test(dependsOnMethods = "Validate_Login")
	public void AddCompany_ServiceProviderNo_D(String Company_Name, String Country, String Customer_Code, String Website) throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='primaryLayout']/main/div/div/div[2]/button/span[2]")).click();
		
		driver.findElement(By.id("companyName")).sendKeys(Company_Name);
		
		driver.findElement(By.id("country")).click();
		WebElement country = driver.findElement(By.id("country"));
		
		
		country.sendKeys(Country);
		country.sendKeys(Keys.ARROW_DOWN);
		country.sendKeys(Keys.ENTER);
		
		
		driver.findElement(By.id("customerCode")).sendKeys(Customer_Code);
		
		//when selecting "No" on are you a service provider
		driver.findElement(By.xpath("//input[@class=\"ant-radio-input\" and @value=\"false\"]")).click();
		
		
		driver.findElement(By.id("website")).sendKeys(Website);
		
		driver.findElement(By.id("isBriefInTradeDateEditable")).click();
		driver.findElement(By.xpath("//button[@class=\"ant-btn ant-btn-primary\"]")).click();
		
		
		
		String tittle = driver.getTitle();
		System.out.println(tittle);
		Assert.assertEquals(tittle, "Company Management | pt.Track");

		
		
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
	
	public void ListCompany() throws InterruptedException {	
		try {
			//driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Reports'])[1]/following::span[2]"));
		//	System.out.println("Add Company button exist");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[2]/td[8]/div/div[1]"));
			System.out.println("Edit icon is present");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[12]/td[8]/div/div[2]/span"));
			System.out.println("Archive icon is present");
			Thread.sleep(2000);
			driver.findElement(By.id("searchBy"));
			System.out.println("Search dropdown present");
			Thread.sleep(2000);
			driver.findElement(By.id("searchText"));
			System.out.println("Search Box is present");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"primaryLayout\"]/main/div/div[1]/div[1]/form/div/div/div[2]/div/div/span/div[2]/div/div/div/div/span/span/span[2]/button/span"));
			System.out.println("search button/trigger is present");
			
			driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[1]/div/span[2]/span/span[2]"));
			System.out.println("Sort icon is present");
		}
		catch(NoSuchElementException e) 
		{
			System.out.println("Add button not present");		
			
		}
		
		//Sort Company by ascsending and descending
		List<WebElement> CompanyColums = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[1]"));
		String[] beforeSort = new String[CompanyColums.size()];
		for(int i=0; i<CompanyColums.size(); i++) {
			beforeSort[i] = CompanyColums.get(i).getText().trim();
			System.out.println(beforeSort[i]);
			
		}
		
		for (int i=0; i<CompanyColums.size(); i++){
			Arrays.sort(beforeSort);
			sort = beforeSort[i];
		}
		
		
		Thread.sleep(4000);
		WebElement sortButt = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[1]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt).perform();
		
		//sortButt.click();
		
		Thread.sleep(4000);
		CompanyColums = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[1]"));
		String[] AfterSort = new String[CompanyColums.size()];
		for(int i=0; i<CompanyColums.size(); i++) {
			AfterSort[i] = CompanyColums.get(i).getText().trim();
			System.out.println(AfterSort[i]);
			
		}
		
		Assert.assertNotEquals(beforeSort, AfterSort);
		System.out.println("Coulumn list sorted");
		
		
		//Sorting in customer code
		
		List<WebElement> CustomerCode = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[2]"));
		String[] beforeSort1 = new String[CustomerCode.size()];
		for(int i=0; i<CustomerCode.size(); i++) {
			beforeSort1[i] = CustomerCode.get(i).getText().trim();
			System.out.println(beforeSort1[i]);
			
		}
		
		for (int i=0; i<CustomerCode.size(); i++){
			Arrays.sort(beforeSort1);
			sort = beforeSort1[i];
		}
		
		
		Thread.sleep(4000);
		WebElement sortButt1 = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[2]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt1).perform();
		
		//sortButt.click();
		
		Thread.sleep(4000);
		CustomerCode = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[2]"));
		String[] AfterSort1 = new String[CustomerCode.size()];
		for(int i=0; i<CustomerCode.size(); i++) {
			AfterSort1[i] = CustomerCode.get(i).getText().trim();
			System.out.println(AfterSort1[i]);
		}
		
		Assert.assertNotEquals(beforeSort1, AfterSort1);
		System.out.println("CustomerCose list sorted");
		//Sorting by Country
		
		List<WebElement> Country = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[3]"));
		String[] beforeSort2 = new String[Country.size()];
		for(int i=0; i<Country.size(); i++) {
			beforeSort2[i] = Country.get(i).getText().trim();
			System.out.println(beforeSort2[i]);
			
		}
		
		for (int i=0; i<Country.size(); i++){
			Arrays.sort(beforeSort2);
			sort = beforeSort2[i];
		}
		
		//sortButt.click();
		Thread.sleep(4000);
		WebElement sortButt2 = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[3]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt2).perform();
		
		
		
		Thread.sleep(4000);
		Country = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[3]"));
		String[] AfterSort2 = new String[Country.size()];
		for(int i=0; i<Country.size(); i++) {
			AfterSort2[i] = Country.get(i).getText().trim();
			System.out.println(AfterSort2[i]);
		}
	
		Assert.assertNotEquals(beforeSort2, AfterSort2);
		System.out.println("Country list sorted");
		
		//Sorting by website
		
		List<WebElement> Website = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[4]"));
		String[] beforeSort3 = new String[Website.size()];
		for(int i=0; i<Website.size(); i++) {
			beforeSort3[i] = Website.get(i).getText().trim();
			System.out.println(beforeSort3[i]);
			
		}
		
		for (int i=0; i<Website.size(); i++){
			Arrays.sort(beforeSort3);
			sort = beforeSort3[i];
		}
		
		//sortButt.click();
		Thread.sleep(4000);
		WebElement sortButt3 = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[4]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt3).perform();
		
		
		
		Thread.sleep(4000);
		Website = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[4]"));
		String[] AfterSort3 = new String[Website.size()];
		for(int i=0; i<Website.size(); i++) {
			AfterSort3[i] = Website.get(i).getText().trim();
			System.out.println(AfterSort3[i]);
		}
		Assert.assertNotEquals(beforeSort3, AfterSort3);
		System.out.println("Website list sorted");
		
		//Sorting by Status
		
		List<WebElement> Status = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[5]"));
		String[] beforeSort4 = new String[Status.size()];
		for(int i=0; i<Status.size(); i++) {
			beforeSort4[i] = Status.get(i).getText().trim();
			System.out.println(beforeSort4[i]);
			
		}
		
		for (int i=0; i<Status.size(); i++){
			Arrays.sort(beforeSort4);
			sort = beforeSort4[i];
		}
		
		//sortButt.click();
		Thread.sleep(4000);
		WebElement sortButt4 = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[5]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt4).perform();
		
		
		
		Thread.sleep(4000);
		Status = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[5]"));
		String[] AfterSort4 = new String[Status.size()];
		for(int i=0; i<Status.size(); i++) {
			AfterSort4[i] = Status.get(i).getText().trim();
			System.out.println(AfterSort4[i]);
		}
		Assert.assertNotEquals(beforeSort4, AfterSort4);
		System.out.println("Status list sorted");
		
		
		//Sorting by last updated 
		
		List<WebElement> lastUpdated = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[6]"));
		String[] beforeSort5 = new String[lastUpdated.size()];
		for(int i=0; i<lastUpdated.size(); i++) {
			beforeSort5[i] = lastUpdated.get(i).getText().trim();
			System.out.println(beforeSort5[i]);
			
		}
		
		for (int i=0; i<lastUpdated.size(); i++){
			Arrays.sort(beforeSort5);
			sort = beforeSort5[i];
		}
		
		//sortButt.click();
		Thread.sleep(4000);
		WebElement sortButt5 = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[6]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt5).perform();
		
		
		
		Thread.sleep(4000);
		lastUpdated = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[6]"));
		String[] AfterSort5 = new String[lastUpdated.size()];
		for(int i=0; i<lastUpdated.size(); i++) {
			AfterSort5[i] = lastUpdated.get(i).getText().trim();
			System.out.println(AfterSort5[i]);
		}
		Assert.assertNotEquals(beforeSort5, AfterSort5);
		System.out.println("Last updated list sorted");
		
		//Sorting by Updated By
		
		List<WebElement> updatedBy = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[7]"));
		String[] beforeSort6 = new String[updatedBy.size()];
		for(int i=0; i<updatedBy.size(); i++) {
			beforeSort6[i] = updatedBy.get(i).getText().trim();
			System.out.println(beforeSort6[i]);
			
		}
		
		for (int i=0; i<updatedBy.size(); i++){
			Arrays.sort(beforeSort6);
			sort = beforeSort6[i];
		}
		
		//sortButt.click();
		Thread.sleep(4000);
		WebElement sortButt6 = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[7]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt6).perform();
		
		
		
		Thread.sleep(4000);
		updatedBy = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[7]"));
		String[] AfterSort6 = new String[updatedBy.size()];
		for(int i=0; i<updatedBy.size(); i++) {
			AfterSort6[i] = updatedBy.get(i).getText().trim();
			System.out.println(AfterSort6[i]);
		}
		Assert.assertNotEquals(beforeSort6, AfterSort6);
		System.out.println("Updated by list sorted");
	}
		

}
