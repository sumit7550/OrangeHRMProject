package orangehrmpro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrangeHRMCompanyInfo {

	public static void main(String[] args) {
		 System.setProperty("webdriver.chrome.driver","E:\\Automation\\Testing\\Automation excelr\\Selenium\\chromedriver_win32");

	        // Initialize WebDriver
	        WebDriver driver = new ChromeDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        try {
	            // Step 1: Open OrangeHRM login page
	            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	            driver.manage().window().maximize();

	            // Step 2: Login as Admin
	            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
	            WebElement passwordField = driver.findElement(By.name("password"));
	            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

	            usernameField.sendKeys("Admin");
	            passwordField.sendKeys("admin123");
	            loginButton.click();

	            // Step 3: Navigate to Admin → Organization → General Information
	            WebElement adminMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Admin")));
	            adminMenu.click();

	            WebElement organizationMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Organization")));
	            organizationMenu.click();

	            WebElement generalInfoMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("General Information")));
	            generalInfoMenu.click();

	            // Step 4: Verify Company Details
	            WebElement companyName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
	            WebElement companyAddress = driver.findElement(By.name("street1"));

	            System.out.println("Company Name: " + companyName.getAttribute("value"));
	            System.out.println("Company Address: " + companyAddress.getAttribute("value"));

	            // Step 5: Modify a Field (e.g., Company Name)
	            String newCompanyName = "OrangeHRM Updated";
	            companyName.clear();
	            companyName.sendKeys(newCompanyName);

	            // Save the changes
	            WebElement saveButton = driver.findElement(By.cssSelector("button[type='submit']"));
	            saveButton.click();

	            // Step 6: Verify the Update
	            WebElement updatedCompanyName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
	            String updatedNameValue = updatedCompanyName.getAttribute("value");

	            if (updatedNameValue.equals(newCompanyName)) {
	                System.out.println("Company name updated successfully!");
	            } else {
	                System.out.println("Company name update failed!");
	            }

	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	    }
	}
