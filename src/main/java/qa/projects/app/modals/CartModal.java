package qa.projects.app.modals;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CartModal {

    public static String productsSelector = "div.cart-product__body";
    public static SelenideElement emptyCard = $("div[data-testid='empty-cart']");
    public static SelenideElement closeButton = $("button.modal__close");
    public static SelenideElement firstProduct = $(productsSelector);
    public static ElementsCollection products = $$(productsSelector);
    public static SelenideElement threeDotsButtonFirstItem = $(productsSelector + " button");
    public static SelenideElement deleteFirstItem = $x("//button[text()=\" Видалити \"]");

    public static void deleteFirstItem() {
        System.out.println(firstProduct);
        threeDotsButtonFirstItem.click();
        deleteFirstItem.click();
    }
}
