import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmvritaTest {

//    /TC_1_1  - Тест кейс:
//    //1. Открыть страницу https://openweathermap.org/
//    //2. Набрать в строке поиска город Paris
//    //3. Нажать пункт меню Search
//    //4. Из выпадающего списка выбрать Paris, FR
//    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2Tag_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDrivers/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']"));
        searchButton.click();
        Thread.sleep(5000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class=\"search-dropdown-menu\"]//li//span[text() = 'Paris, FR ']\n"));
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);


        Thread.sleep(5000);

        driver.quit();
    }

//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что
//    title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void testTitle_OpenWeatherMapAPIGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDrivers/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchMenuGuide = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li[1]/a"));
        searchMenuGuide.click();
        Thread.sleep(5000);

        driver.getTitle();
        String actualResult = driver.getTitle();

        Assert.assertEquals(actualResult, expectedResult);


        driver.quit();
    }

//    TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Подтвердить, что температура для города показана в Фарингейтах
    @Test
    public void testTemperatureSwitchedToFahrenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDrivers/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchImperialF = driver.findElement(
                By.xpath("//div[@id=\"weather-widget\"]/div[1]/div/div/div[1]/div[2]/div[3]"));
        searchImperialF.click();
        Thread.sleep(5000);

        WebElement searchCurrentTemperature = driver.findElement(
                By.xpath("//div[@id=\"weather-widget\"]/div[2]/div[1]/div[1]/div[2]/div[1]" +
                        "/span[contains(text(),'°F')]")
        );

        String result = searchCurrentTemperature.getText();
        String actualResult = "";
        if (result.contains("F")){
            actualResult = "F";
        }

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
}

//    TC_11_03
//1.  Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
// We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
// You can allow all cookies or manage them individually.”
//            3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void testCookiesPanelIsOnPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDrivers/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResultB1 = "Allow all";
        String expectedResultB2 = "Manage cookies";

                driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchCookiesText = driver.findElement(
                By.xpath("//div[@id=\"stick-footer-panel\"]/div/div/div/div/p[text() = 'We use cookies " +
                        "which are essential for the site to work. We also use non-essential cookies to help us improve" +
                        " our services. Any data collected is anonymised. You can allow all cookies or manage them " +
                        "individually.']"));
        Thread.sleep(5000);

        WebElement searchAllowAllButton = driver.findElement(
                By.xpath("//div[@id=\"stick-footer-panel\"]/div/div/div/div/div/button")
        );

        WebElement searchManageCookiesButton = driver.findElement(
                By.xpath("//div[@id=\"stick-footer-panel\"]/div/div/div/div/div/a")
        );

        String actualResult = searchCookiesText.getText();
        String actualResultB1 = searchAllowAllButton.getText();
        String actualResultB2 = searchManageCookiesButton.getText();

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResultB1, expectedResultB1);
        Assert.assertEquals(actualResultB2, expectedResultB2);

        driver.quit();
    }

//TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
    @Test
    public void testMenuSupport() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDrivers/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchMenuSupport = driver.findElement(
                By.xpath("//div[@id=\"support-dropdown\"]"));
        searchMenuSupport.click();
        Thread.sleep(5000);

        WebElement searchMenuSupportFAQ = driver.findElement(
                By.xpath("//ul[@id=\"support-dropdown-menu\"]/li[1]/a")
        );

        WebElement searchMenuSupportHowToStart = driver.findElement(
                By.xpath("//ul[@id=\"support-dropdown-menu\"]/li[2]/a")
        );

        WebElement searchMenuSupportAskAQuestion = driver.findElement(
                By.xpath("//ul[@id=\"support-dropdown-menu\"]/li[3]/a")
        );

        String actualResultFAQ = searchMenuSupportFAQ.getText();
        String actualResultHowToStart = searchMenuSupportHowToStart.getText();
        String actualAskAQuestion = searchMenuSupportAskAQuestion.getText();

        Assert.assertEquals(actualResultFAQ, expectedResultFAQ);
        Assert.assertEquals(actualResultHowToStart, expectedResultHowToStart);
        Assert.assertEquals(actualAskAQuestion, expectedResultAskAQuestion);

        driver.quit();
    }

//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testReCaptchaVerificationFailed() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDrivers/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultAskAQuestion = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchMenuSupport = driver.findElement(
                By.xpath("//div[@id=\"support-dropdown\"]"));
        searchMenuSupport.click();
        Thread.sleep(5000);

        WebElement searchMenuSupportAskAQuestion = driver.findElement(
                By.xpath("//ul[@id=\"support-dropdown-menu\"]/li[3]/a")
        );
        searchMenuSupportAskAQuestion.click();
        Thread.sleep(5000);

        String url2 = "https://home.openweathermap.org/questions";
        driver.get(url2);
        Thread.sleep(5000);
        WebElement searchQuestionFormEmail = driver.findElement(
                By.xpath("//input[@id=\"question_form_email\"]")
        );
        searchQuestionFormEmail.sendKeys("emv.rita@gmail.com");
        Thread.sleep(5000);

//        String actualAskAQuestion = searchMenuSupportAskAQuestion.getText();
//
//        Assert.assertEquals(actualAskAQuestion, expectedResultAskAQuestion);

        driver.quit();
    }

}
