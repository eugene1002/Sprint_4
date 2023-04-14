package ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private WebDriver driver;

    // Локатор
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setFirstName(String firstName) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input")).sendKeys(firstName);
    }
    public void setLastName(String lastName) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input")).sendKeys(lastName);
    }
    public void setAddress(String address) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input")).sendKeys(address);
    }
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input")).sendKeys(phoneNumber);
    }
    public void setMetroStation(String metroStation) {
        driver.findElement(By.className("select-search__input")).sendKeys(metroStation);
        WebElement optionscont = driver.findElement(By.className("select-search__row"));
        optionscont.click();
    }
    public void clickOnNextButton() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button")).click();
    }
    public void createOrder (String firstName, String lastName, String address, String metroStation, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setMetroStation(metroStation);
        setPhoneNumber(phoneNumber);
    }
    public void clickOnButtonCheckStatus() {
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button"));
    }
}
