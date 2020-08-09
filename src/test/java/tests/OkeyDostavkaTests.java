package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.PressEnter;
import com.codeborne.selenide.commands.ShouldBe;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;


class OkeyDostavkaTests {

    String number  = "9816997343",
        email   = "fetaka6640@fazmail.net"; // todo
    String pass    = "123123qQ";
    String name    = "Даниил";
    String last    = "Инкогнито";
    String middle  = "Отче";
    String birth   = "12.12.1992";

    
    @Test
    void okeyAllProductSearchTest() {
        open("https://www.okeydostavka.ru/spb");

        $(byName("searchTerm")).setValue("Арбуз"); //ввести в поиске по каталогу сайта.
        $(".search-query__button").click(); //нажать на символ "лупа".

        $$(".ok-theme").shouldHave(size(18)); // проверка кол-во ед товара(было 17 стало 18)
    }

    @Test
    void okeyOnePoductSearchTest() { // todo исправить тест
        open("https://www.okeydostavka.ru/spb");

        $(byName("searchTerm")).setValue("Арбуз"); //ввести в поиске по каталогу сайта.(PressEnter).
        $(".search-query__button").click(); //нажать на "символ лупа".

        $$(".product.ok-theme").get(3).shouldBe(); //Проверить что в каталог добавили определенный арбуз.
    }

    @Test
    void okeyPickUpPointTest() {
        open("https://www.okeydostavka.ru/spb");

        $(".timeslot__text").click(); //нажать кнопку "Доставка: время не выбрано".
        $(".pickup-control").click(); //нажать кнопку "Самовывоз".

        $$(".store-list .store-item").shouldHave(size(6)); //Проверить наличие 6 магазинов
    }

    @Test
    void okeyAccountRegistrationTest() {
        open("https://www.okeydostavka.ru/spb");

        $(".header-mobile-account__profile").click(); // нажать на "иконку с избражением человека".
        $(".guest-registration-link").click(); // Нажать на кнопку "зарегистрироваться".
        $(".btn-cookie").click(); //Нажать на Кнопку "Принять" cookie.
        $(byName("email1")).setValue(email);
        $(byName("logonPassword")).setValue(pass);
        $(byName("logonPasswordVerify")).setValue(pass);
        $(byName("phone1")).setValue(number);
        $(byName("lastName")).setValue(last);
        $(byName("firstName")).setValue(name);
        $(byName("middleName")).setValue(middle);
        $("#dateOfBirthDisplay").setValue(birth);
        $("#genderMale_label").click(); //выбрать пол "Мужской".
        $(".button_text").click(); //Нажать кнопку "регистрация".

        $("body").shouldHave(text("Благодарим за регистрацию в интернет-магазине О'КЕЙ"));
    }

    @Test
    void okeySigninAndCheckoutTest() {
        open("https://www.okeydostavka.ru/spb");

        $(".btn-cookie").click(); //Нажать на Кнопку "Принять" cookie.
        $(".product-buttons .product-cart").click(); //Добовляем продукт в корзину "иконка с изображением продуктовой карзины"
        $(".header-mobile-account__cart").click(); //Нажать на "изображением продуктовой карзины"пользователя.
        $("#bread_logonLink").click(); //Нажать кнопку "Войти в систему и оформить заказ".
        $("#Custom_email").setValue(email);
        $("#Custom_logonPassword").setValue(pass);
        $(".logon-button").click(); //Нажать кнопку "Войти в систему".
        $(byLinkText("Оформить заказ")).click(); //Нажать кнопку "Оформить заказ".
        $("#combinedPageFinishButton").click(); //Нажать кнопку "Завершить оформление".

        $("html").shouldHave(text("Благодарим за Ваш заказ!"));
    }
}
