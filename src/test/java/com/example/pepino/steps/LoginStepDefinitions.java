package com.example.pepino.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStepDefinitions {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user navigates to the Asis login page")
    public void the_user_navigates_to_the_asis_login_page() {
        driver.get("https://campus.usbco.edu.co/psp/USCS90PR/?cmd=login&languageCd=ESP&");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
    }

    @When("the user enters valid Asis credentials")
    public void the_user_enters_valid_asis_credentials() {
        WebElement user = driver.findElement(By.id("userid"));
        WebElement passwordField = driver.findElement(By.id("pwd"));
        WebElement loginButton = driver.findElement(By.name("Submit"));

        user.sendKeys("JFMOSQUERAI"); //Cuenta
        passwordField.sendKeys("Nara"); //Contraseña
        loginButton.click();
    }

    @Then("the user should be logged in to Asis")
    public void the_user_should_be_logged_in_to_asis() {
        wait.until(ExpectedConditions.urlContains("https://campus.usbco.edu.co/psp/USCS90PR/EMPLOYEE/HRMS/h/?tab=DEFAULT"));
        assertTrue(driver.getCurrentUrl().contains("https://campus.usbco.edu.co/psp/USCS90PR/EMPLOYEE/HRMS/h/?tab=DEFAULT"));
    }

    @When("the user enters invalid Asis credentials")
    public void the_user_enters_invalid_asis_credentials() {
        WebElement user = driver.findElement(By.id("userid"));
        WebElement passwordField = driver.findElement(By.id("pwd"));
        WebElement loginButton = driver.findElement(By.name("Sumit"));

        user.sendKeys("invalidEmail@example.com");
        passwordField.sendKeys("invalidPassword");
        loginButton.click();
    }

    @Then("the user should see a login error message")
    public void the_user_should_see_a_login_error_message() {

        wait.until(ExpectedConditions.urlContains("https://campus.usbco.edu.co/psp/USCS90PR/?&cmd=login&errorCode=105&languageCd=ESP"));
        assertTrue(driver.getCurrentUrl().contains("https://campus.usbco.edu.co/psp/USCS90PR/?&cmd=login&errorCode=105&languageCd=ESP"));
    
        //WebElement errorBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='login_error']")));
        //assertTrue(errorBox.isDisplayed());
    }
}
