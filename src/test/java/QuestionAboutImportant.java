import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage.Locators.buttonShowAnswer;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionAboutImportant {
    private static WebDriver driver;
    private String description;
    private boolean isVisible;
    private int questionNumber;

    public QuestionAboutImportant(int questionNumber,String description, boolean isVisible) {
        this.questionNumber = questionNumber;
        this.description = description;
        this.isVisible = isVisible;
    }
    @Parameterized.Parameters
    public static Object[][] getExpectedAnswers() {
        return new Object[][]{
                {0,"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", true},
                {1,"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", true},
                {2,"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", true},
                {3,"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", true},
                {4,"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", true},
                {5,"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", true},
                {6,"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", true},
                {7,"Да, обязательно. Всем самокатов! И Москве, и Московской области.", true},
        };
    }
    @BeforeClass
    public static void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage","--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void checkAnswersToImportantQuestions() {
        List <WebElement> list_elements = driver.findElements(buttonShowAnswer);
        WebElement element = list_elements.get(questionNumber);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        String answerId = ("accordion__panel-" + Integer.toString(questionNumber));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.id(answerId)));
        String tagText = driver.findElement(By.id(answerId)).getText();
        assertEquals(description, tagText);
    }


    @AfterClass
    public static void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
