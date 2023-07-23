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
import static com.codeborne.selenide.WebDriverConditions.url;
import static qa.projects.app.AppConfig.baseUrl;

public class RozetkaTests {

    @BeforeMethod
    public void openRozetka() {
        open(baseUrl);
    }

    @Test(description = "add and remove from cart")
    public void cartTest() {
        String input = "iphone";
        int productsSize = 0;
        BasePage.openCart();
        CartModal.emptyCard.shouldBe(Condition.visible);
        CartModal.closeButton.click();
        BasePage.searchProduct(input);
        ProductListPage.firstItemBuyButton.shouldBe(Condition.visible);
        ProductListPage.firstItemBuyButton.click();
        productsSize++;
        ProductListPage.cartBadge.shouldBe(Condition.text(String.valueOf(productsSize)));
        ProductListPage.openCart();
        CartModal.products.shouldHave(size(productsSize));

        CartModal.deleteFirstItem() ;
        productsSize--;
        CartModal.emptyCard.shouldBe(Condition.visible);
        CartModal.products.shouldHave(size(productsSize));
    }

    @Test(description = "Apple category: number of subcategories and one subcategory")
    public static void categoryTest() {
        String category = "Apple";
        int categoriesSize = 20;

        BasePage.searchInput.setValue(category);
        BasePage.searchButton.click();
        CategoryPage.categories.shouldHave(size(categoriesSize));

        CategoryPage.categories.get(1).click();
        ProductListPage.heading.shouldHave(Condition.text(category));
    }

    @Test(description = "filter selection after search")
    public static void filterTest() {
        String input = "iphone 13";
        int numberOfProductsBefore, numberOfProductsAfter;

        BasePage.searchProduct(input);
        ProductListPage.getFilterElementIgnoreCase(input).shouldNotBe(Condition.empty);

        numberOfProductsBefore = ProductListPage.getNumberOfProducts();

        //Click on the filter seller Rozetka. - It is not Rozetka in the test, because the number wouldn't change if you click Rozetka
        ProductListPage.clickOnSideBarFilter(input);
        webdriver().shouldHave(url("https://rozetka.com.ua/ua/mobile-phones/c80003/producer=apple;series=113077,113083/"));
        numberOfProductsAfter = ProductListPage.getNumberOfProducts();

        Assert.assertTrue(numberOfProductsAfter < numberOfProductsBefore);
    }

    @Test(description = "grid and tile view sizes")
    public static void viewSizeTest() {
        Dimension productTileExpectedSize = new Dimension(177, 234);
        Dimension productGridExpectedSize = new Dimension(230, 304);
        String input = "iphone 13";

        BasePage.searchProduct(input);
        Assert.assertEquals(ProductListPage.productTilePicture.getSize(), productTileExpectedSize, "Product tile size is incorrect");
        ProductListPage.catalogViewButton.click();
        Assert.assertEquals(ProductListPage.productTilePicture.getSize(), productGridExpectedSize, "Product grid size is incorrect");
    }

    @Test(description = "sort from expensive to cheap")
    public static void sortTest() {
        String input = "iphone";

        BasePage.searchProduct(input);
        ProductListPage.sortingOptionExpensive.click();

        webdriver().shouldHave(url("https://rozetka.com.ua/ua/mobile-phones/c80003/producer=apple;sort=expensive/"));
        int[] productPrices = ProductListPage.getIntProductPrices();

        Assert.assertTrue(productPrices[0] > productPrices[1]);
        Assert.assertTrue(productPrices[1] > productPrices[2]);
    }

}
