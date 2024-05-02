package administration;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class Division {

	WebDriver driver;
    
	@BeforeTest
	public void Setup() throws InterruptedException {
		//WebDriver driver;
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.Chrome.driver", "externalDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://pt-track-sg-web-app.azurewebsites.net/?redirectUri=/");
		driver.manage().window().maximize();
		Thread.sleep(8000);
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
	public void Admin_Nav_to_div() throws InterruptedException {
		//driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Reports'])[1]/following::span[2]")).click();
		driver.findElement(By.xpath("/html/body/section/aside/div/ul/li[2]/div/span[2]")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Division Management")).click();
		Thread.sleep(4000);
		
	}
	@Test
	public void Add_division() throws InterruptedException {
	
	
	
	driver.findElement(By.xpath("//button[@class=\"ant-btn ant-btn-primary ant-btn-lg\"]")).click();
	Thread.sleep(4000);
	if (driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/form/div[5]/button[2]")).isEnabled()) {
		System.out.println("Button is enabled ");
	}else
	{
		System.out.println("Button is disabled  ");
	}
	Thread.sleep(4000);
	driver.findElement(By.xpath("//input[@placeholder=\"Enter division name\"]")).sendKeys("A_Div15");
	driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/form/div[5]/button[2]")).click();
	String tittle = driver.getTitle();
	Thread.sleep(4000);
	Assert.assertEquals(tittle, "Division Management | pt.Track");
	}
	
	@Test
	public void Edit_division() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[2]/td[5]/div/div[1]/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/form/div[2]/div/div/div/div[2]/div/div/span/span/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder=\"Enter division name\"]")).sendKeys("A_Div16");
		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/form/div[5]/button[2]")).click();
		
		Thread.sleep(4000);
	}
	
	@Test
	public void Archive_division() throws InterruptedException{
		driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[2]/td[5]/div/div[2]/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div[2]/button[2]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[2]/td[5]/div/div[2]/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/div[2]/button[1]")).click();
		String tittle = driver.getTitle();
		Thread.sleep(4000);
		Assert.assertEquals(tittle, "Division Management | pt.Track");
		
	}
	
	@Test
	public void Search_division() throws InterruptedException{
		driver.findElement(By.id("searchBy")).click();
		Thread.sleep(4000);
		List<WebElement> searchBy = driver.findElements(By.className("ant-select-item-option-content"));
		
		for (WebElement searchby : searchBy) {
			if (searchby.getText().equals("Division")) {
				
				searchby.click();
				break;
			}
		}
		
		driver.findElement(By.id("searchText")).sendKeys("Admin");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"primaryLayout\"]/main/div/div[1]/div[1]/form/div/div/div[2]/div/div/span/div[2]/div/div/div/div/span/span/span[2]/button/span")).click();
		Thread.sleep(4000);
		List<WebElement> results = driver.findElements(By.xpath("//tr[@class=\"ant-table-row ant-table-row-level-0\"]"));
		System.out.println(results.size());
		
		for (int i=0; i<results.size(); i++) {
			
			System.out.println(results.get(i).getText());
			if (results.get(i).getText().contains("Admin")) {
				System.out.println("search passed");
			}else
			{
				System.out.println("search failed");
			}
				
		}
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"primaryLayout\"]/main/div/div[1]/div[1]/form/div/div/div[2]/div/div/span/div[2]/div/div/div/div/span/span/span[1]/span/span/span")).click();
		
		
	}
	
	static String sort;
	@Test
	public void List_division() throws InterruptedException{
		try 
		{
			driver.findElement(By.xpath("//*[@id=\"primaryLayout\"]/main/div/div[1]/div[2]/button"));
			System.out.println("Add division button is displayed ");
			
			driver.findElement(By.id("searchBy")).click();
			System.out.println("Search dropdown is present");
			
			driver.findElement(By.id("searchText")).sendKeys("Admin");
			System.out.println("Search box is displayed");
			
			driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[2]/td[5]/div/div[2]/span"));
			System.out.println("Archive icon is displayed");
			
			driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[2]/table/tbody/tr[2]/td[5]/div/div[1]/span"));
			System.out.println("Edit icon is displayed");
			
			
			
			
		}
		catch(NoSuchElementException e) 
		{
			System.out.println("Elemets not present");
		}
		
		
		
		//Sort Division
		
		List<WebElement> divColums = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[1]"));
		String[] beforeSort = new String[divColums.size()];
		for(int i=0; i<divColums.size(); i++) {
			beforeSort[i] = divColums.get(i).getText().trim();
			System.out.println(beforeSort[i]);
			
		}
		
		for (int i=0; i<divColums.size(); i++){
			Arrays.sort(beforeSort);
			sort = beforeSort[i];
		}
		
		
		Thread.sleep(4000);
		WebElement sortButt = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[1]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt).perform();
		
		//sortButt.click();
		
		Thread.sleep(4000);
		divColums = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[1]"));
		String[] AfterSort = new String[divColums.size()];
		for(int i=0; i<divColums.size(); i++) {
			AfterSort[i] = divColums.get(i).getText().trim();
			System.out.println(AfterSort[i]);
			
		}
		
		Assert.assertNotEquals(beforeSort, AfterSort);
		System.out.println("Coulumn list sorted");
		
		
		
		//Sorting Status
		
		List<WebElement> Status = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[2]"));
		String[] beforeSort1 = new String[Status.size()];
		for(int i=0; i<Status.size(); i++) {
			beforeSort1[i] = Status.get(i).getText().trim();
			System.out.println(beforeSort1[i]);
			
		}
		
		for (int i=0; i<Status.size(); i++){
			Arrays.sort(beforeSort1);
			sort = beforeSort1[i];
		}
		
		
		Thread.sleep(4000);
		WebElement sortButt1 = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[2]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt1).perform();
		
		//sortButt.click();
		
		Thread.sleep(4000);
		Status = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[2]"));
		String[] AfterSort1 = new String[Status.size()];
		for(int i=0; i<Status.size(); i++) {
			AfterSort1[i] = Status.get(i).getText().trim();
			System.out.println(AfterSort1[i]);
		}
		
		Assert.assertNotEquals(beforeSort1, AfterSort1);
		System.out.println("CustomerCose list sorted");
		
		
		//Sorting by Last updated 
		
		List<WebElement> LastUpdated = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[3]"));
		String[] beforeSort2 = new String[LastUpdated.size()];
		for(int i=0; i<LastUpdated.size(); i++) {
			beforeSort2[i] = LastUpdated.get(i).getText().trim();
			System.out.println(beforeSort2[i]);
			
		}
		
		for (int i=0; i<LastUpdated.size(); i++){
			Arrays.sort(beforeSort2);
			sort = beforeSort2[i];
		}
		
		//sortButt.click();
		Thread.sleep(4000);
		WebElement sortButt2 = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[3]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt2).perform();
		
		
		
		Thread.sleep(4000);
		LastUpdated = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[3]"));
		String[] AfterSort2 = new String[LastUpdated.size()];
		for(int i=0; i<LastUpdated.size(); i++) {
			AfterSort2[i] = LastUpdated.get(i).getText().trim();
			System.out.println(AfterSort2[i]);
		}
	
		Assert.assertNotEquals(beforeSort2, AfterSort2);
		System.out.println("Country list sorted");
		
		
		//Sorting by UpdatedBy
		
		List<WebElement> UpdatedBy = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[4]"));
		String[] beforeSort3 = new String[UpdatedBy.size()];
		for(int i=0; i<UpdatedBy.size(); i++) {
			beforeSort3[i] = UpdatedBy.get(i).getText().trim();
			System.out.println(beforeSort3[i]);
			
		}
		
		for (int i=0; i<UpdatedBy.size(); i++){
			Arrays.sort(beforeSort3);
			sort = beforeSort3[i];
		}
		
		//sortButt.click();
		Thread.sleep(4000);
		WebElement sortButt3 = driver.findElement(By.xpath("//*[@id=\"adaptiveTable\"]/div/div[1]/table/thead/tr/th[4]/div/span[2]/span/span[2]"));
		new Actions(driver).doubleClick(sortButt3).perform();
		
		
		
		Thread.sleep(4000);
		UpdatedBy = driver.findElements(By.xpath("//tbody[@class=\"ant-table-tbody\"]//tr/td[4]"));
		String[] AfterSort3 = new String[UpdatedBy.size()];
		for(int i=0; i<UpdatedBy.size(); i++) {
			AfterSort3[i] = UpdatedBy.get(i).getText().trim();
			System.out.println(AfterSort3[i]);
		}
		Assert.assertNotEquals(beforeSort3, AfterSort3);
		System.out.println("Website list sorted");
		
		
	}	
		
}
