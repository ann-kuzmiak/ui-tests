package qa.projects.app.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class CategoryPage extends BasePage{
    public static ElementsCollection categories = $$("ul li.portal-grid__cell");
}
