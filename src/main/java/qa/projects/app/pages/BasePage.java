package qa.projects.app.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    public static SelenideElement catalogButton = $("#fat-menu");
    public static SelenideElement cartButton = $(".header-actions__item--cart button");
    public static SelenideElement searchInput = $("input.search-form__input");
    public static SelenideElement searchButton = $("button.search-form__submit");
    public static SelenideElement cartBadge = $("rz-cart .badge");

    public static void openCart() {
        cartButton.click();
    }
}
