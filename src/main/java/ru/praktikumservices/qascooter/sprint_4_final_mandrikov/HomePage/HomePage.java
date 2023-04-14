package ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage.Locators.*;

public class HomePage {

    private static WebDriver driver;

    // Локатор
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
//        Метод поиска кнопки "Заказать" в хедере страницы и нажатия на неё

    //        Метод поиска кнопки "Заказать" в середине страницы и нажатия на неё
    public void clickOnMiddleOrderButton() {
        WebElement element = driver.findElement(buttonOrderInMiddle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void checkAnswersToImportantQuestions(int questionNumber, String description, boolean isVisible) {
        List<WebElement> list_elements = driver.findElements(buttonShowAnswer);
        WebElement element = list_elements.get(questionNumber);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        String answerId = ("accordion__panel-" + Integer.toString(questionNumber));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.id(answerId)));
        String tagText = driver.findElement(By.id(answerId)).getText();
        assertEquals(description, tagText);
    }
}

