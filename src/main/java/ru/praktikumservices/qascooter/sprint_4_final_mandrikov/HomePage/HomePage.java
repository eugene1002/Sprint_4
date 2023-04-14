package ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

    public class HomePage {

        private static WebDriver driver;

        // Локатор
        public HomePage(WebDriver driver) {
            this.driver = driver;
        }
//        Метод поиска кнопки "Заказать" в хедере страницы и нажатия на неё
        public void clickOnHeaderOrderButton() {
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]")).click();
        }
//        Метод поиска кнопки "Заказать" в середине страницы и нажатия на неё
        public void clickOnMiddleOrderButton() {
            WebElement element = driver.findElement(By.xpath("/html/body/div/div/div/div[4]/div[2]/div[5]/button"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            element.click();
        }
    }

