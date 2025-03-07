package orangehrmpro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class OrangeHRMJobTitles {

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

            // Step 3: Navigate to Admin → Job → Job Titles
            WebElement adminMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Admin")));
            adminMenu.click();

            WebElement jobMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Job")));
            jobMenu.click();

            WebElement jobTitlesMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Job Titles")));
            jobTitlesMenu.click();

            // Step 4: Verify the list of job titles
            List<WebElement> jobTitlesList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".oxd-table-card")));
            System.out.println("Number of job titles displayed: " + jobTitlesList.size());

            // Step 5: Add a new job title
            WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.oxd-button--secondary")));
            addButton.click();

            WebElement jobTitleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("jobTitle")));
            WebElement saveButton = driver.findElement(By.cssSelector("button[type='submit']"));

            jobTitleField.sendKeys("Software Engineer");
            saveButton.click();

            // Validate the new job title is added
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Software Engineer']")));
            System.out.println("New job title added successfully!");

            // Step 6: Edit an existing job title
            WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Software Engineer']/ancestor::div[@class='oxd-table-card']//button[1]")));
            editButton.click();

            WebElement editJobTitleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("jobTitle")));
            editJobTitleField.clear();
            editJobTitleField.sendKeys("Senior Software Engineer");
            saveButton.click();

            // Validate the update
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Senior Software Engineer']")));
            System.out.println("Job title updated successfully!");

            // Step 7: Delete a job title
            WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Senior Software Engineer']/ancestor::div[@class='oxd-table-card']//button[2]")));
            deleteButton.click();

            WebElement confirmDeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.oxd-button--label-danger")));
            confirmDeleteButton.click();

            // Validate the deletion
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='Senior Software Engineer']")));
            System.out.println("Job title deleted successfully!");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}