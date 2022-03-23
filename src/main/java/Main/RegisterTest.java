package Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterTest {
	
	WebDriver driver;
	SoftAssert sa;
	
	@BeforeTest
	public void setup() {
		ChromeOptions op = new ChromeOptions();
		op.setHeadless(false);
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver(op);
		sa = new SoftAssert();
		
	}
	
	@Test
	public void registerTest() {
		driver.get("https://demo.guru99.com/insurance/v1/index.php");
		driver.findElement(By.linkText("Register")).click();
		
		// Verifying Signup Page
		sa.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Sign up as a new user");
		
		// User Title
		Select dropUserTitle = new Select(driver.findElement(By.id("user_title")));
		dropUserTitle.selectByVisibleText("Mr");
		
		// Users name and Contact
		driver.findElement(By.id("user_firstname")).sendKeys("Anupama");
		driver.findElement(By.id("user_surname")).sendKeys("Dikkumbura");
		driver.findElement(By.id("user_phone")).sendKeys("0761306338");
		
		// Date of Birth
		Select dropBirthYear = new Select(driver.findElement(By.id("user_dateofbirth_1i")));
		dropBirthYear.selectByVisibleText("1995");
		Select dropBirthMonth = new Select(driver.findElement(By.id("user_dateofbirth_2i")));
		dropBirthMonth.selectByVisibleText("May");
		Select dropBirthDay = new Select(driver.findElement(By.id("user_dateofbirth_3i")));
		dropBirthDay.selectByVisibleText("20");
		
		driver.findElement(By.id("licencetype_f")).click();
		
		// User license period
		Select dropUserLicencePeriod = new Select(driver.findElement(By.id("user_licenceperiod")));
		dropUserLicencePeriod.selectByVisibleText("5");
		
		// User license period
		Select dropOccupation = new Select(driver.findElement(By.id("user_occupation_id")));
		dropOccupation.selectByVisibleText("Engineer");
		
		// Other details
		driver.findElement(By.id("user_address_attributes_street")).sendKeys("Ella road");
		driver.findElement(By.id("user_address_attributes_city")).sendKeys("Wellawaya");
		driver.findElement(By.id("user_address_attributes_county")).sendKeys("Sri Lanka");
		driver.findElement(By.id("user_address_attributes_postcode")).sendKeys("91200");
		driver.findElement(By.id("user_user_detail_attributes_email")).sendKeys("anupama@gmail.com");
		driver.findElement(By.id("user_user_detail_attributes_password")).sendKeys("anupama123");
		driver.findElement(By.id("user_user_detail_attributes_password_confirmation")).sendKeys("anupama123");
		driver.findElement(By.name("submit")).click();
		
		// Verifying Login Page
		sa.assertEquals(driver.findElement(By.tagName("h3")).getText(), "Login");
		
		// Login using created credentials
		driver.findElement(By.id("email")).sendKeys("anupama@gmail.com");
		driver.findElement(By.id("password")).sendKeys("anupama123");
		driver.findElement(By.name("submit")).click();
		
		// Login verification
		sa.assertEquals(driver.findElement(By.tagName("h4")).getText(), "anupama@gmail.com");
		
	}
	
	@AfterTest
	public void tearDown() {
		sa.assertAll();
		driver.close();
		driver.quit();
		
	}

}
