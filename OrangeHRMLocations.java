package orangehrmpro;

public class OrangeHRMLocations {

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

	            // Step 3: Navigate to Admin → Organization → Locations
	            WebElement adminMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Admin")));
	            adminMenu.click();

	            WebElement organizationMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Organization")));
	            organizationMenu.click();

	            WebElement locationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Locations")));
	            locationsMenu.click();

	            // Step 4: Verify the list of locations
	            List<WebElement> locationsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".oxd-table-card")));
	            System.out.println("Number of locations displayed: " + locationsList.size());

	            // Step 5: Add a new location
	            WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.oxd-button--secondary")));
	            addButton.click();

	            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
	            WebElement cityField = driver.findElement(By.name("city"));
	            WebElement saveButton = driver.findElement(By.cssSelector("button[type='submit']"));

	            nameField.sendKeys("New York Office");
	            cityField.sendKeys("New York");
	            saveButton.click();

	            // Validate the new location is added
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='New York Office']")));
	            System.out.println("New location added successfully!");

	            // Step 6: Edit an existing location
	            WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='New York Office']/ancestor::div[@class='oxd-table-card']//button[1]")));
	            editButton.click();

	            WebElement editNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
	            editNameField.clear();
	            editNameField.sendKeys("Updated New York Office");
	            saveButton.click();

	            // Validate the update
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Updated New York Office']")));
	            System.out.println("Location updated successfully!");

	            // Step 7: Delete a location
	            WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Updated New York Office']/ancestor::div[@class='oxd-table-card']//button[2]")));
	            deleteButton.click();

	            WebElement confirmDeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.oxd-button--label-danger")));
	            confirmDeleteButton.click();

	            // Validate the deletion
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='Updated New York Office']")));
	            System.out.println("Location deleted successfully!");

	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	    }
	}