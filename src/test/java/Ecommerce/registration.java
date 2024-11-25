package Ecommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class registration {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test(priority = 1)
    public void launchHomepage() {
        //Section: opens homepage & verify it loaded correctly
        driver.get("https://automationexercise.com/");
        try {
            WebElement consentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Consent']")));
            consentButton.click();
        } catch (Exception e) {
            System.out.println("consent button not displayed");
        }

        String title = driver.getTitle();
        Assert.assertEquals(title, "Automation Exercise");
    }

    @Test(priority = 2)
    public void addProductToCart() {
        // adds first product to cart
        driver.findElement(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/div[1]/a")).click();
        // opens the cart and review added item
        driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u")).click();
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Automation Exercise - Checkout"));
    }

    @Test(priority = 3)
    public void proceedToCheckout() {
        driver.findElement(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a")).click();
    }

    @Test(priority = 4)
    public void register() {
        driver.findElement(By.xpath("//*[@id=\"checkoutModal\"]/div/div/div[2]/p[2]/a/u")).click();
        // fills in user details for registration
        driver.findElement(By.name("name")).sendKeys("ayodeji");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("ayodeji@gmail.com");
        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("123456");

        // pagedown
        Actions pageDown = new Actions(driver);
        pageDown.sendKeys(Keys.PAGE_DOWN).perform();

        // Select date of birth: day, month, year
        WebElement days = driver.findElement(By.id("days"));
        Select pickDay = new Select(days);
        pickDay.selectByIndex(10);

        WebElement months = driver.findElement(By.id("months"));
        Select pickMonth = new Select(months);
        pickMonth.selectByValue("10");

        WebElement years = driver.findElement(By.id("years"));
        Select pickYear = new Select(years);
        pickYear.selectByVisibleText("2000");

        // subcribe to newsletter & special offers
        driver.findElement(By.name("newsletter")).click();
        driver.findElement(By.id("optin")).click();
        // adds contact information & address
        driver.findElement(By.id("first_name")).sendKeys("new");
        driver.findElement(By.name("last_name")).sendKeys("old");
        driver.findElement(By.id("company")).sendKeys("eCommerce");
        pageDown.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.id("address1")).sendKeys("address1");
        driver.findElement(By.id("address2")).sendKeys("address2");

        // selects country
        WebElement county = driver.findElement(By.id("country"));
        Select pickCountry = new Select(county);
        pickCountry.selectByIndex(5);

        driver.findElement(By.id("state")).sendKeys("state");
        driver.findElement(By.id("city")).sendKeys("city");
        driver.findElement(By.id("zipcode")).sendKeys("zipcode");
        pageDown.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.id("mobile_number")).sendKeys("22233344455");

        // completes account creation
        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();

        //verifies account creation success message
        String confirmationMessage = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/p[1]")).getText();
        Assert.assertEquals(confirmationMessage, "Congratulations! Your new account has been successfully created!");
    }


    @Test(priority = 5)
    public void completeOrder() {
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
        // navigates to cart & proceeds to checkout
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a")).click();

        // scrolls to comment field and adds order comment
        WebElement comments = driver.findElement(By.name("message"));
        Actions pageDown = new Actions(driver);
        pageDown.moveToElement(comments).perform();
        comments.sendKeys("My first order");

        driver.findElement(By.xpath("//*[@id=\"cart_items\"]/div/div[7]/a")).click();
        // adds card information & places the order
        driver.findElement(By.name("name_on_card")).sendKeys("new old");
        driver.findElement(By.name("card_number")).sendKeys("111122223333444");
        driver.findElement(By.name("cvc")).sendKeys("123");
        driver.findElement(By.name("expiry_month")).sendKeys("10");
        driver.findElement(By.name("expiry_year")).sendKeys("2025");
        pageDown.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.xpath("//button[@data-qa='pay-button']")).click();
        // verifies order confirmation message
        String orderconfirmation = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/p")).getText();
        Assert.assertEquals(orderconfirmation, "Congratulations! Your order has been confirmed!");
    }

    @Test(priority = 6)
    public void deleteAccount() {
        // Section: deletes user account
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a")).click();
        // verifies account deletion message
        String deletionMessage = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/p[1]")).getText();
        Assert.assertEquals(deletionMessage, "Your account has been permanently deleted!");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
