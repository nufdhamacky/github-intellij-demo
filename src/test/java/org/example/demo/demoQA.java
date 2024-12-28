package org.example.demo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class demoQA {
    public static void main(String[] args) {
        // Setup WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Test Case 1: Valid form submission with all correct inputs
            testValidFormSubmission(driver);

            // Test Case 2: Form submission with mandatory fields left blank
            testMandatoryFieldsValidation(driver);

            // Test Case 3: Invalid email format validation
            testInvalidEmailFormat(driver);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void testValidFormSubmission(WebDriver driver) throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        // Fill valid details
        driver.findElement(By.id("firstName")).sendKeys("Nufdha");
        driver.findElement(By.id("lastName")).sendKeys("Macky");
        driver.findElement(By.id("userEmail")).sendKeys("mmfnufdha@g.com");
        driver.findElement(By.cssSelector("label[for='gender-radio-2']")).click(); // Proper gender selection
        driver.findElement(By.id("userNumber")).sendKeys("0772740480");
        driver.findElement(By.id("dateOfBirthInput")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("dateOfBirthInput")).sendKeys("15 Jun 2001");
        driver.findElement(By.id("subjectsInput")).sendKeys("ICT");
        driver.findElement(By.id("subjectsInput")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).click();
        driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\mmfnu\\OneDrive\\Pictures\\Camera Roll\\nufdha.jpg");
        driver.findElement(By.id("currentAddress")).sendKeys("241/3, Quarry Road, Dehiwala");

        Thread.sleep(2000);
        driver.findElement(By.id("submit")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        // Validate success modal
        WebElement successMessage = driver.findElement(By.className("modal-content"));
        if (successMessage.isDisplayed()) {
            System.out.println("Valid form submission test passed!");
        } else {
            System.out.println("Valid form submission test failed!");
        }
    }

    public static void testMandatoryFieldsValidation(WebDriver driver) throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        // Leave mandatory fields blank and submit
        driver.findElement(By.id("submit")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        // Validate error message presence
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement lastName = driver.findElement(By.id("lastName"));

        if (firstName.getAttribute("class").contains("is-invalid") &&
                lastName.getAttribute("class").contains("is-invalid")) {
            System.out.println("Mandatory fields validation test passed!");
        } else {
            System.out.println("Mandatory fields validation test failed!");
        }
    }

    public static void testInvalidEmailFormat(WebDriver driver) throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        // Enter invalid email
        driver.findElement(By.id("userEmail")).sendKeys("invalid-email");
        driver.findElement(By.id("submit")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        // Validate email error class
        String emailFieldClass = driver.findElement(By.id("userEmail")).getAttribute("class");
        if (emailFieldClass.contains("is-invalid")) {
            System.out.println("Invalid email format validation test passed!");
        } else {
            System.out.println("Invalid email format validation test failed!");
        }
    }
}
