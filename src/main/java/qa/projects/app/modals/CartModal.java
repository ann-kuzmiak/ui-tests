package qa.projects.app.modals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CartModal {

    public static SelenideElement emptyCard = $("div[data-testid='empty-cart']");
    public static SelenideElement closeButton = $("button.modal__close");
    public static ElementsCollection products = $$("div.cart-product__body");
    public static SelenideElement threeDotsButtonFirstItem = products.first().$("button");
    public static SelenideElement deleteFirstItem = $x("//button[text()=\" Видалити \"]");

    public static void deleteFirstItem() {
        threeDotsButtonFirstItem.click();
        deleteFirstItem.click();
    }
}
