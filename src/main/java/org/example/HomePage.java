package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for the home page
    public static final By SEARCH_BOX = By.id("gh-ac");
    public static final By SEARCH_BUTTON = By.id("gh-btn");
    public static final By FIRST_BOOK = By.xpath("//div[@id='srp-river-results']/ul/li[1]");
    public static final By ADD_TO_CART_BUTTON = By.id("atcBtn_btn_1");
    public static final By CART_COUNT = By.id("gh-cart-n");

    // Constructor
    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Methods to interact with elements
    public void searchFor(String searchTerm) {
        WebElement searchBox = driver.findElement(SEARCH_BOX);
        searchBox.sendKeys(searchTerm);
        WebElement searchButton = driver.findElement(SEARCH_BUTTON);
        searchButton.click();
    }

    public void clickFirstBook() {
        wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_BOOK));

        // Find the first book and click it
        WebElement firstBook = driver.findElement(FIRST_BOOK);
        firstBook.click();

        // Wait for the number of open windows to be 2 (assuming only one window opens)
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Store the current window handle (the original window)
        String originalWindow = driver.getWindowHandle();

        // Switch to the new window (there should now be two windows open)
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);  // Switch to the new window
                break;
            }
        }
    }


    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BUTTON));
        WebElement addToCartButton = driver.findElement(ADD_TO_CART_BUTTON);
        addToCartButton.click();
    }

    public String getCartCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(CART_COUNT));
        WebElement cartCountElement = driver.findElement(CART_COUNT);
        return cartCountElement.getText();
    }
}
