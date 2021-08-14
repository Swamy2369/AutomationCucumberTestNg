package stepDefinitions;

import java.util.Map;
import frameworkBase.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DemoShopHomePage;

/**
 * The DemoShopStepdefs class has all the Step definitions for Demo Shop Page
 * which we can use in all the feature files
 * 
 * @author Swamy
 */

public class DemoShopStepdefs {

	private ScenarioContext scenarioCntxt;
	private DemoShopHomePage demoShopHomePage_Obj;
	private int noOfProducts;

	public DemoShopStepdefs(ScenarioContext scenarioContext) {
		this.scenarioCntxt = scenarioContext;
		demoShopHomePage_Obj = new DemoShopHomePage(scenarioCntxt);
	}

	@Given("^I opens the Application$")
	public void userOpenedGoogleApplication() {
		demoShopHomePage_Obj.openDemoShopApplication();
		demoShopHomePage_Obj.validateApplicationHomePage();
		demoShopHomePage_Obj.acceptCookies();
	}

	@Then("^I add different products to my wish list$")
	public void userAddProductsToWishList(Map<String, String> map) {
		noOfProducts = Integer.parseInt(map.get("numberOfProducts"));
		demoShopHomePage_Obj.addProductsToWishList(noOfProducts);
	}

	@When("^I view my wishlist table$")
	public void verifyWishList() {
		demoShopHomePage_Obj.clickWishList();
	}

	@Then("^I find total four selected items in my wishlist$")
	public void clickSearchButton() {
		demoShopHomePage_Obj.validateWishlistPage(noOfProducts);
	}

	@When("^I search for lowest price product$")
	public void searchLowestPriceProduct() {
		demoShopHomePage_Obj.searchForLeastPriceItemFromWishlist();
	}

	@When("^I am able to add the lowest price item to my cart$")
	public void addLowestPriceItemToCart() {
		demoShopHomePage_Obj.addLeastPriceItemToCart();
	}

	@When("^I am able to verify the item in my cart$")
	public void validateItemInMyCartPage() {
		demoShopHomePage_Obj.clickCart();
		demoShopHomePage_Obj.validateCartPage();
	}

}