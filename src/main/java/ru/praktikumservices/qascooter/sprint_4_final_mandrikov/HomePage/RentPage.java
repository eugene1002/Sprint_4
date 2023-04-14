package ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class RentPage {
    private WebDriver driver;
    public RentPage(WebDriver driver) {
        this.driver = driver;
    }
    public void deliveryDate(String deliveryDate) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input")).sendKeys(deliveryDate);
    }
    public void clickRentalPeriod() {
        driver.findElement(By.className("Dropdown-arrow")).click();
    }
    public void selectRentalPeriod(int days){
        int maxDays = 7;
        int dayIndex;
        if(days > 0 && days <= maxDays) {
            dayIndex = days - 1;
            List<WebElement> dropdownOptions = driver.findElements(By.className("Dropdown-option"));
            dropdownOptions.get(dayIndex).click();
        }
    }
    public void selectColour(String colour) {
        driver.findElement(By.id(colour)).click();
    }

    public void setComment(String comment) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input")).sendKeys(comment);
    }

    public void clickOnFinalOrderButton() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]")).click();
    }
    public void aboutRent (String deliveryDate,int days,String colour,String comment) {
        deliveryDate(deliveryDate);
        clickRentalPeriod();
        selectRentalPeriod(days);
        selectColour(colour);
        setComment(comment);
    }
    public void clickOnYesButton() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]")).click();
    }
}
