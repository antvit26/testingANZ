package anzTesting;

//import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.ProtocolHandshake;
//import org.testng.Assert;
//import org.testng.annotations.Test 


public class testClass { 
	
	
	WebDriver driver = new ChromeDriver();
	//WebDriver driver = new FirefoxDriver();
	String baseUrl = "https://digital.anz.co.nz/preauth/web/service/login";	
	String Loginname = "your username";
	String Loginpassword = "your password";
	
	
	WebElement username = driver.findElement(By.id("user-id"));
	WebElement password = driver.findElement(By.id("password"));
	WebElement login=driver.findElement(By.id("submit"));
	//WebElement logOff=driver.findElement(By.id("logOff"));
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\selenium\\New folder (2)\\chromedriver.exe");
		
		testClass t = new testClass();
		t.testLogin();
				
	}
	
	void testLogin(){
		
		driver.get(baseUrl);
		
		username.sendKeys(Loginname);
        password.sendKeys(Loginpassword);
        login.click();
        
        String actualUrl="https://digital.anz.co.nz/preauth/web/service/login";
        String expectedUrl= driver.getCurrentUrl();
        //System.out.println(expectedUrl);
        
        if(actualUrl.equalsIgnoreCase(expectedUrl))
        {
            System.out.println("Test passed");
        }
        else
        {
            System.out.println("Test failed");
        }	
        
        //logOff.click();
        
	}
	void testTransfer() {
		
	}

}
