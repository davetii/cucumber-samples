import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class ContactUs_Stepdefs {

    private WebDriver driver;

    public String genRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                + "/src/main/java/drivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I access the webdriver university contact us page")
    public void iAccessTheWebdriverUniversityContactUsPage() {
        System.out.println("Hello");
        driver.get("https://www.webdriveruniversity.com/Contact-Us/contactus.html");
    }

    @When("I enter a unique first name")
    public void iEnterAUniqueFirstName() {
        String newFirstName = "firstName" + genRandomString(3);
        System.out.println(newFirstName);
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys(newFirstName);
    }

    @And("I enter a unique last name")
    public void iEnterAUniqueLastName() {
        String newLastName = "lastName" + genRandomString(3);
        System.out.println(newLastName);
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys(newLastName);
    }

    @And("I enter a unique email address")
    public void iEnterAUniqueEmailAddress() {
        String newEmail = "email" + genRandomString(3) + "@gmail.com";
        System.out.println(newEmail);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(newEmail);
    }

    @And("I enter a unique comment")
    public void iEnterAUniqueComment() {
        String newMessage = "new messgae " + genRandomString(4);
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys(newMessage);
    }

    @And("I click on submit button")
    public void iClickOnSubmitButton() {
        driver.findElement(By.xpath("//input[@value='SUBMIT']")).click();
    }

    @Then("I should be presented with success message")
    public void iShouldBePresentedWithSuccessMessage() {
        WebElement contactUsMessage = driver.findElement(By.xpath("//div[@id='contact_reply']"));
        Assert.assertEquals(contactUsMessage.getText(), "Thank You for your Message!");
    }
}
