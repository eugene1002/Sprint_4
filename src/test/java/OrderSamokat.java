import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage.OrderPage;
import ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage.RentPage;
import static org.junit.Assert.assertEquals;
import static ru.praktikumservices.qascooter.sprint_4_final_mandrikov.HomePage.Locators.*;

@RunWith(Parameterized.class)
public class OrderSamokat {
    private WebDriver driver;
    private final By button;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final String metroStation;
    private final String deliveryDate;
    private final String comment;
    private final int daysRent;
    private final String colour;

    public OrderSamokat(By button, String firstName, String lastName, String address, String metroStation, String phoneNumber, String deliveryDate, int daysRent, String colour, String comment) {
        this.button = button;
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
                {buttonOrderInHeader,"Евгений", "Иванов", "Белгород, ул. Николая Чумичова, д. 38", "Сходненская", "+79988942788", "15.04.2023", 4,"black", "Обычный комментарий по заказу"},
                {buttonOrderInMiddle,"Алена", "Водонаева", "Москва, улица Собянина, дом 1 корп 2 кв 34", "Арбатская", "89899998899", "17.04.2023", 3,"grey", "Необычный комментарий по заказу"},
                {buttonOrderInHeader,"Диана", "Кузнецова", "Воронеж, ул. 9 Января, д. 12", "Новокузнецкая", "+74723981903", "17.04.2023", 1,"black", "Обычный комментарий по заказу"},
                {buttonOrderInMiddle,"Ян", "Топлес", "Москва, улица Пушкина, дом 132 корп 1 кв 87", "Измайловская", "89123456789", "23.04.2023", 5,"grey", "Необычный комментарий по заказу"},
        };
    }
    @Before
    public void init(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void testOrderHeaderButton() {
        OrderPage objOrderPage = new OrderPage(driver);
        RentPage objRentPage = new RentPage(driver);
        objOrderPage.createOrder(
                button,
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
        String actualResult = driver.findElement(modalWindow).getText();
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
