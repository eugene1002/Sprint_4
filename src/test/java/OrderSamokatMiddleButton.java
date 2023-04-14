import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage.HomePage;
import ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage.OrderPage;
import ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage.RentPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderSamokatMiddleButton {
    private WebDriver driver;
    private  String firstName;
    private  String lastName;
    private  String address;
    private  String phoneNumber;
    private  String metroStation;
    private  String deliveryDate;
    private  String comment;
    private int daysRent;
    private String colour;
    public OrderSamokatMiddleButton(String firstName, String lastName, String address, String metroStation, String phoneNumber, String deliveryDate, int daysRent, String colour, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.metroStation = metroStation;
        this.deliveryDate = deliveryDate;
        this.daysRent = daysRent;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Диана", "Кузнецова", "Воронеж, ул. 9 Января, д. 12", "Новокузнецкая", "+74723981903", "17.04.2023", 1,"black", "Обычный комментарий по заказу"},
                {"Ян", "Топлес", "Москва, улица Пушкина, дом 132 корп 1 кв 87", "Измайловская", "89123456789", "23.04.2023", 5,"grey", "Необычный комментарий по заказу"},
        };
    }
    @Before
    public void init(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void testOrderMiddleButton() {
        OrderPage objOrderPage = new OrderPage(driver);
        HomePage objHomePage = new HomePage(driver);
        RentPage objRentPage = new RentPage(driver);
        objHomePage.clickOnMiddleOrderButton();
        objOrderPage.createOrder(
                firstName,
                lastName,
                address,
                metroStation,
                phoneNumber);
        objOrderPage.clickOnNextButton();
        objRentPage.aboutRent(
                deliveryDate,
                daysRent,
                colour,
                comment);
        objRentPage.clickOnFinalOrderButton();
        objRentPage.clickOnYesButton();
        String actualResult = driver.findElement(By.className("Order_Modal__YZ-d3")).getText();
        String textOrder = "Заказ оформлен\n" + "Номер заказа: .  Запишите его:\n" +
                "пригодится, чтобы отслеживать статус\n" + "Посмотреть статус";
        assertEquals(textOrder,actualResult);
        objOrderPage.clickOnButtonCheckStatus();
    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
