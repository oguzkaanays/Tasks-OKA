package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import util.driverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.elementHelper;


public class home_allTests {

    WebDriver driver;
    elementHelper elementHelper;
    Actions actions;

    By img_insiderLogo = By.xpath("//img[@src='https://useinsider.com/assets/img/logo-old.png']");
    By btn_more = By.xpath("//a[@id='navbarDropdownMenuLink']");
    By btn_careers = By.xpath("//a[text()='Careers']");
    By hdr_teams = By.id("career-find-our-calling");
    By btn_teams = By.cssSelector("#career-find-our-calling h3");
    By hdr_locations = By.id("career-our-location");
    By btn_locations = By.cssSelector("#career-our-location p");
    By hdr_lifeAtInsider = By.xpath("//h2[text()='Life at Insider']");
    By txt_lifeAtInsider = By.xpath(" //p[contains(text(),'We’re here to grow')]");

    By btn_seeAllTeams = By.xpath("//a[contains(text(),'See all teams')]");

    By txt_browseOpenPositions = By.xpath("//h3[@class='mb-0']");

    By btn_qualityAssurance = By.xpath("//section/div/div/div[2]/div[12]/div[2]/a");
    // //div[2]/div[12]/div[2]/a/h3[text()='Quality Assurance']  //section[@id='career-find-our-calling'] //h3[text()='Quality Assurance']
    By btn_seeAllQAJobs = By.xpath("//a[text()='See all QA jobs']");
    By flt_byLocation = By.xpath("//span[@title='All']");
    By li_istanbulTurkey = By.xpath("//ul/li[text()='Istanbul, Turkey']");
    By flt_byDepartment = By.id("select2-filter-by-department-container");

    By li_qualityAssurance = By.cssSelector(".select2-results__option") ;
    By vld_jobRelatedToQA = By.xpath("//p[text()='Senior Software Quality Assurance Engineer']");
    By vld_allQaJobsTitles = By.cssSelector("#career-position-list p.position-title");
    By vld_allQaJobsDepartments = By.cssSelector("#career-position-list span.position-department");
    By vld_allQaJobsLocations = By.cssSelector("#career-position-list div.position-location");
    By vld_viewRole = By.cssSelector("#career-position-list a");
    By btn_viewRole = By.xpath("(//a[text()='View Role'])[1]");



    By btn_acceptAll = By.id("wt-cli-accept-all-btn");


    @BeforeClass
    public void setup() {
        // open "chrome" or "firefox" by parameters
        driverFactory.initializeDriver("chrome");
        driver = driverFactory.getDriver();
        elementHelper = new elementHelper(driver);


    }

    @Test(priority = 0)
    public void OpenTheSite() {

        driver.get("https://useinsider.com/");

        driver.findElement(img_insiderLogo);

        if (driver.findElement(img_insiderLogo).isDisplayed()) {
            System.out.println("Site opened successfully!");
        } else {
            System.out.println("Failure: Site could not be opened!");
        }

    }

    @Test(priority = 1)
    public void GoToCareerPageAndValidate(){

        elementHelper.clickElementWithText(btn_more,"Company");
        elementHelper.click(btn_careers);
        elementHelper.checkUrl("https://useinsider.com/careers/");
        elementHelper.checkElement(hdr_locations);
        elementHelper.checkElementWithText(btn_locations,"London");
        elementHelper.checkElement(hdr_teams);
        elementHelper.checkElementWithText(btn_teams,"Sales");
        elementHelper.checkElement(hdr_lifeAtInsider);
        //elementHelper.checkElement(txt_lifeAtInsider);

    }

    @Test(priority = 2)
    public void GoToQaJobsAndFilter(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        elementHelper.click(btn_acceptAll);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement seeAllTeams = driver.findElement(btn_seeAllTeams);

        int windowHeight = Integer.parseInt(js.executeScript("return window.innerHeight").toString());
        int elementY = seeAllTeams.getLocation().getY();
        while (elementY > windowHeight) {
            js.executeScript("window.scrollBy(0, " + windowHeight + ");");
            elementY -= windowHeight;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        elementHelper.click(btn_seeAllTeams);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement qualityAssurance = driver.findElement(btn_qualityAssurance);

        js.executeScript("arguments[0].click();", qualityAssurance);



        elementHelper.click(btn_seeAllQAJobs);
        elementHelper.findElement(flt_byLocation);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        elementHelper.click(flt_byLocation);
        elementHelper.click(li_istanbulTurkey);
        elementHelper.click(flt_byDepartment);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        elementHelper.clickElementWithText(li_qualityAssurance,"Quality Assurance");
        elementHelper.checkElement(vld_jobRelatedToQA);

    }
    @Test(priority = 3)
    public void ValidateFilteredJobs(){

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement browseOpenPositions = driver.findElement(txt_browseOpenPositions);

        int windowHeight = Integer.parseInt(js.executeScript("return window.innerHeight").toString());
        int elementY = browseOpenPositions.getLocation().getY();
        while (elementY > windowHeight) {
            js.executeScript("window.scrollBy(0, " + windowHeight + ");");
            elementY -= windowHeight;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        elementHelper.checkElementsWithText(vld_allQaJobsDepartments,"Quality Assurance");
        elementHelper.checkElementsWithText(vld_allQaJobsTitles,"Quality Assurance");
        elementHelper.checkElementsWithText(vld_allQaJobsLocations,"Istanbul, Turkey");


    }
    @Test(priority = 4)
    public void GoToTheApplicationSiteAndValidate(){



        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement viewRole = driver.findElement(btn_viewRole);
        js.executeScript("arguments[0].click();", viewRole);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        elementHelper.checkBaseUrl("https://jobs.lever.co/");



    }




    @AfterClass
    public void teardown() {
        // for closing browser
       // driver.quit();
    }





}
