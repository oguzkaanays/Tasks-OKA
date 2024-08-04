package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class elementHelper {

    public WebDriver driver;
    public WebDriverWait wait;

    public elementHelper(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void click(By locator) {

        findElement(locator).click();

    }
    public List<WebElement> findElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void clickElementWithText(By locator, String text) {
        boolean check = false;
        List<WebElement> elementList = findElements(locator);
        for (WebElement elem : elementList) {
            if (elem.getText().equals(text)) {
                check = true;
                elem.click();
                break;
            }
        }
        Assert.assertTrue(check, "Listede istediğin textteki elamanı bulamadım!!!");
    }
    public void checkUrl(String text){
        String actualURL = driver.getCurrentUrl();

        if (actualURL.equals(text)) {
            System.out.println("Current url value matches expected url value.");
        } else {
            System.out.println("Failure: Current url value did not match expected url value!");
        }

    }
    public void checkBaseUrl(String text){
        String actualURL = driver.getCurrentUrl();

        if (actualURL.startsWith(text)) {
            System.out.println("Current url value matches expected url value.");
        } else {
            System.out.println("Failure: Current url value did not match expected url value!");
        }

    }
    public void checkElement(By locator) {
        findElement(locator);
    }
    public void checkElementWithText(By locator, String text) {
        int i = 0;
        boolean check = false;
        findElement(locator);
        List<WebElement> elementList = findElements(locator);
        for (WebElement elem : elementList) {
            if (elem.getText().equals(text)) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check, "Listede istediğin textteki elamanı bulamadım!!!");
    }

    public void checkElementsWithText(By locator, String text) {
        int i = 0;
        boolean check = true;
        List<WebElement> elementList = findElements(locator);
        for (WebElement elem : elementList) {
            if (!elem.getText().contains(text)) {
                check = false;
                break;
            }
        }
        Assert.assertTrue(check, "The value returned could not match all of the values in the list.");
    }


}
