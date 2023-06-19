package qa.projects.tests;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.projects.app.modals.CartModal;
import qa.projects.app.pages.BasePage;
import qa.projects.app.pages.CategoryPage;
import qa.projects.app.pages.ProductListPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static qa.projects.app.AppConfig.baseUrl;

public class RozetkaTests {

    @BeforeMethod
    public void openRozetka() {
        open(baseUrl);
    }

    @Test
    public void cartTest() {
        String input = "iphone";
        int productsSize = 0;

        BasePage.openCart();
        CartModal.emptyCard.shouldBe(Condition.visible);
        CartModal.closeButton.click();

        BasePage.searchInput.setValue(input).pressEnter();
        ProductListPage.firstItemBuyButton.click();
        productsSize++;
        ProductListPage.cartBadge.shouldBe(Condition.text(String.valueOf(productsSize)));
        ProductListPage.openCart();
        CartModal.products.shouldHave(size(productsSize));

        CartModal.deleteFirstItem();
        productsSize--;
        CartModal.emptyCard.shouldBe(Condition.visible);
        CartModal.products.shouldHave(size(productsSize));
    }

    @Test
    public static void categoryTest() {
        String category = "Apple";
        int categoriesSize = 20;

        BasePage.searchInput.setValue(category);
        BasePage.searchButton.click();
        CategoryPage.categories.shouldHave(size(categoriesSize));

        CategoryPage.categories.get(1).click();
        ProductListPage.heading.shouldHave(Condition.text(category));
    }

    @Test
    public static void filterTest() {
        String input = "iphone 13";
        int numberOfProductsBefore, numberOfProductsAfter;

        BasePage.searchInput.setValue(input).pressEnter();

        ProductListPage.filterElementIgnoreCase(input).shouldNotBe(Condition.empty);

        numberOfProductsBefore = ProductListPage.getNumberOfProducts();

        //Click on the filter seller Rozetka. - It is not Rozetka in the test, because the number wouldn't change if you click Rozetka
        ProductListPage.filterElementIgnoreCase(input).click();
        numberOfProductsAfter = ProductListPage.getNumberOfProducts();

        Assert.assertTrue(numberOfProductsAfter < numberOfProductsBefore);
    }

    @Test
    public static void viewSizeTest() {
        Dimension productTileExpectedSize = new Dimension(177, 234);
        Dimension productGridExpectedSize = new Dimension(230, 304);
        String input = "iphone 13";

        BasePage.searchInput.setValue(input).pressEnter();

        Assert.assertEquals(ProductListPage.productTilePicture.getSize(), productTileExpectedSize, "Product tile size is incorrect");
        ProductListPage.catalogViewButton.click();
        Assert.assertEquals(ProductListPage.productTilePicture.getSize(), productGridExpectedSize, "Product grid size is incorrect");
    }

    @Test
    public static void sortTest() {
        String input = "iphone";

        BasePage.searchInput.setValue(input).pressEnter();

        ProductListPage.sortingOptionExpensive.click();
        ProductListPage.getIntProductPrices();
        Assert.assertTrue(ProductListPage.getIntProductPrices()[0] > ProductListPage.getIntProductPrices()[1]);
        Assert.assertTrue(ProductListPage.getIntProductPrices()[1] > ProductListPage.getIntProductPrices()[2]);
    }

}
