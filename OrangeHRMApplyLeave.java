package orangehrmpro;

public class OrangeHRMApplyLeave {

	public static void main(String[] args) {
	      System.setProperty("webdriver.chrome.driver","E:\\Automation\\Testing\\Automation excelr\\Selenium\\chromedriver_win32");

	        // Initialize WebDriver
	        WebDriver driver = new ChromeDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        try {
	            // Step 1: Open OrangeHRM login page
	            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	            driver.manage().window().maximize();

	            // Step 2: Login as an Employee
	            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
	            WebElement passwordField = driver.findElement(By.name("password"));
	            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

	            usernameField.sendKeys("Admin"); // Use employee username
	            passwordField.sendKeys("admin123"); // Use employee password
	            loginButton.click();

	            // Step 3: Navigate to Leave → Apply
	            WebElement leaveMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Leave")));
	            leaveMenu.click();

	            WebElement applyMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Apply")));
	            applyMenu.click();

	            // Step 4: Select Leave Type, Date Range, and Submit
	            WebElement leaveTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-select-text")));
	            leaveTypeDropdown.click();

	            WebElement leaveTypeOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Vacation']")));
	            leaveTypeOption.click();

	            WebElement fromDateField = driver.findElement(By.cssSelector("input[placeholder='yyyy-mm-dd'][name='fromDate']"));
	            WebElement toDateField = driver.findElement(By.cssSelector("input[placeholder='yyyy-mm-dd'][name='toDate']"));
	            WebElement applyButton = driver.findElement(By.cssSelector("button[type='submit']"));

	            fromDateField.sendKeys("2025-03-07");
	            toDateField.sendKeys("2025-03-08");
	            applyButton.click();

	            // Step 5: Validate the Leave Request in the Leave List
	            WebElement leaveListMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My Leave")));
	            leaveListMenu.click();

	            WebElement leaveRequest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Vacation']")));
	            System.out.println("Leave request submitted successfully and appears in the leave list!");

	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	    }
	}