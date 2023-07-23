package qa.projects.app.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductListPage extends BasePage {
    public static SelenideElement firstItemBuyButton = $("button.buy-button");
    public static SelenideElement heading = $("h1.catalog-heading");
    public static SelenideElement catalogSelectionLabel = $("p.catalog-selection__label");
    public static SelenideElement sortingOptionExpensive = $("option[value=\"2: expensive\"]");
    public static ElementsCollection productPrices = $$("span.goods-tile__price-value");
    public static SelenideElement productTilePicture = $("a.goods-tile__picture");
    public static SelenideElement catalogViewButton = $("button.catalog-view__button");

    public static ElementsCollection listOfProducts = $$("li.catalog-grid__cell");

    public static SelenideElement getFilterElementIgnoreCase(String filter) {
        return $("a[data-id=\"" + filter + "\" i] ");
    }

    public static int getNumberOfProducts() {
        return listOfProducts.size();
    }

    public static void clickOnSideBarFilter(String filter) {
        ProductListPage.getFilterElementIgnoreCase(filter).click();
    }

    public static int[] getIntProductPrices() {
        int[] prices = new int[productPrices.size()];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = Integer.parseInt(productPrices.get(i).getOwnText().replaceAll("\\D", ""));
        }
        return prices;
    }

}
