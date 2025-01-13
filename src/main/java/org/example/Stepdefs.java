package org.example;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.*;
import org.openqa.selenium.WindowType;

public class Stepdefs {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    public Stepdefs() {
    }

    @Given("I open the browser")
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:/Cucumber/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
    }

    @When("I navigate to {string}")
    public void navigateTo(String url) {
        driver.get(url);
    }

    @When("I search for {string}")
    public void searchFor(String searchTerm) {
        homePage.searchFor(searchTerm);
    }

    @When("I click on the first book in the list")
    public void clickFirstBook() {
        homePage.clickFirstBook();
    }

    @When("I add the book to the cart")
    public void addToCart() {
        homePage.addToCart();
    }

    @Then("the cart should display the number of items as updated")
    public void verifyCartUpdated() {
        String cartCount = homePage.getCartCount();
        assertNotNull("Cart count is not updated", cartCount);
        System.out.println("Test passed: Cart has been updated with count " + cartCount);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
