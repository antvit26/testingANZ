package anzTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;

public class testing {

	WebDriver driver = new ChromeDriver();
	String baseUrl = "https://digital.anz.co.nz/preauth/web/service/login";

	// Login Function.
	void login() {

		driver.get(baseUrl);

		WebElement username = driver.findElement(By.id("user-id"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement login = driver.findElement(By.id("submit"));

		username.sendKeys("82510091");// Username
		password.sendKeys("Friday@97+25");// Password
		login.click(); // Login to the account

	}

	// Test ANZ login
	void testLogin() {

		login(); // call login function

		String actualUrl = "https://digital.anz.co.nz/preauth/web/service/login";
		String expectedUrl = driver.getCurrentUrl();
		// System.out.println(expectedUrl);

		if (actualUrl.equalsIgnoreCase(expectedUrl))// Checking the URL
		{
			System.out.println("Test 1 : Login Test passed");
		} else {
			System.out.println("Test 2 : Login Test failed");
		}

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement logout = driver.findElement(By.id("logout"));
		logout.click();// Logout
	}

	
	// Converting the value into double and checking.
	void testTransfer() {

		login(); // Call login function.		
		

		// Finding the account balance before the transfer and convert the element into a double value before the transfer.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement Amount = driver.findElement(By.id("ember949"));

		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

		String stringAmount = Amount.getText();// Account balance before the transfer.
		Number money = null;
		try {
			money = currencyFormat.parse(stringAmount);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal moneyAmount = BigDecimal.valueOf(money.doubleValue());
		double actualAmount = moneyAmount.doubleValue(); //Converting BigDecimal into a double value.
		actualAmount += 5; // Add 5 (transfer amount).

		// Transfer the money to the account
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement transfer = driver.findElement(By.id("ember797"));
		transfer.click(); // Click on transfer button.

		// Finding the element id for transfer
		Select transferFrom = new Select(driver.findElement(By.id("instant-transfer-from-account")));
		Select transferTo = new Select(driver.findElement(By.id("instant-transfer-to-account")));
		WebElement amount = driver.findElement(By.id("instant-transfer-amount"));
		WebElement reference = driver.findElement(By.id("instant-transfer-reference"));
		WebElement submit = driver.findElement(By.id("instant-transfer-submit"));

		transferFrom.selectByVisibleText("Weekly Expenses");// Select the account
		transferTo.selectByVisibleText("Figo Payment");// Select the account
		amount.sendKeys("5"); // Amount to transfer
		reference.sendKeys("Test Transfer"); // Enter reference
		submit.click(); // Click to submit
		driver.navigate().refresh(); // refresh the page.

		// Find the account balance after the transfer and Check the transfer is successful
		WebElement Amount2 = driver.findElement(By.id("ember949"));
		String stringAmount2 = Amount2.getText();
		Number money2 = null;
		try {
			money2 = currencyFormat.parse(stringAmount2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal moneyAmount2 = BigDecimal.valueOf(money2.doubleValue());
		double expectedAmount = moneyAmount2.doubleValue();
		
		// Check the expected amount equals actualAmount and print the message.
		if (expectedAmount == actualAmount) { 
			System.out.println("Test 2 : Transfer successful");
		} else {
			System.out.println("Test 2 : Transfer unsuccessful");
		}

		// Logout
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement logout = driver.findElement(By.id("logout"));
		logout.click();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\New folder (2)\\chromedriver1.exe");

		 testing anz = new testing();
		 anz.testLogin();
		 anz.testTransfer();
	}

}
