package orangehrmpro;

public class OrangeHRMApproveRejectionLeave {

	public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","E:\\Automation\\Testing\\Automation excelr\\Selenium\\chromedriver_win32");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Step 1: Open OrangeHRM login page
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.manage().window().maximize();

            // Step 2: Login as Admin or Supervisor
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
            WebElement passwordField = driver.findElement(By.name("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            usernameField.sendKeys("Admin"); // Use admin username
            passwordField.sendKeys("admin123"); // Use admin password
            loginButton.click();

            // Step 3: Navigate to Leave → Leave List
            WebElement leaveMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Leave")));
            leaveMenu.click();

            WebElement leaveListMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Leave List")));
            leaveListMenu.click();

            // Step 4: Find the Pending Leave Request
            WebElement employeeNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Type for hints...']")));
            employeeNameField.sendKeys("Alice"); // Enter employee name from the previous scenario

            WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
            searchButton.click();

            // Step 5: Approve or Reject the Leave Request
            WebElement approveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Pending Approval']/ancestor::div[@class='oxd-table-card']//button[text()='Approve']")));
            approveButton.click();

            // Step 6: Verify the Status Update
            WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Pending Approval']/ancestor::div[@class='oxd-table-card']//div[text()='Approved']")));
            if (statusElement.getText().equals("Approved")) {
                System.out.println("Leave request approved successfully!");
            } else {
                System.out.println("Leave request approval failed!");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
