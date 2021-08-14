package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import frameworkBase.PageActions;
import frameworkBase.ScenarioContext;
import frameworkBase.Utilities;

/**
 * The DemoShopHomePage class is having all the Demo shop Page element
 * properties and the related action methods. If the elements needs to be change
 * in future, this is the place that we have to do.
 * 
 * @author Swamy
 */

public class DemoShopHomePage extends PageActions {

	@FindBy(xpath = "(//img[@class='custom-logo'])[1]")
	private WebElement img_DemoShopLogo;

	@FindBy(xpath = "//a[contains(text(),'Accept all')]")
	private WebElement button_AcceptCookies;

	@FindBy(xpath = "(//a[@data-title='Add to wishlist'])[1]")
	private WebElement link_AddToWishList;

	@FindBy(xpath = "(//a[@title='Wishlist'])[1]")
	private WebElement link_WishListToolTip;

	@FindBy(xpath = "//a[@title='Remove this product']")
	private List<WebElement> button_RemoveProductFromWishList;

	@FindBy(xpath = "//td[@class='product-price']")
	private List<WebElement> label_ProductPrice;

	@FindBy(xpath = "(//a[@title='Cart']/i)[1]")
	private WebElement link_CartToolTip;

	@FindBy(xpath = "//a[@aria-label='Remove this item']")
	private List<WebElement> button_RemoveItemFromCartList;

	private ScenarioContext scenarioCntxt;
	private Utilities utilities_Obj = new Utilities();

	// constructor to get the context and also to initiate the webElements
	public DemoShopHomePage(ScenarioContext scenarioContext) {
		super(scenarioContext);
		this.scenarioCntxt = scenarioContext;
		PageFactory.initElements(scenarioContext.getDriver(), this);
	}

	private int itemMinimumPrice = 0;
	private int minimumPriceRow = 0;
	private int noOfItemsAddedToCart = 0;

	/*
	 * This function launches the application
	 */
	public void openDemoShopApplication() {
		launchApplication(utilities_Obj.getPropertyFromFile("config", "url"));
	}

	/*
	 * This function validates the application home page
	 */
	public void validateApplicationHomePage() {
		Assert.assertTrue(isElementVisible(img_DemoShopLogo), "Application Home Page validation Failed");
	}

	/*
	 * This function accepts the application coockies
	 */
	public void acceptCookies() {
		clickElement(button_AcceptCookies);
	}

	/*
	 * This function adds the products to wish list. It needs
	 * integer(numberOfProducts) as an argument to add the product to wish list
	 */
	public void addProductsToWishList(int numberOfProducts) {
		for (int i = 1; i <= numberOfProducts; i++) {
			clickElement(link_AddToWishList);
			waitForPageToLoad();
			By _linkBrowseWishList = By.xpath("(//a[@data-title='Browse wishlist'])[" + i + "]");
			isElementVisible(_linkBrowseWishList);
		}
	}

	/*
	 * This function clicks on Wish list Tool tip
	 */
	public void clickWishList() {
		clickElement(link_WishListToolTip);
	}

	/*
	 * This function validates the number of products in Wish list page
	 */
	public void validateWishlistPage(int noOfProductsAdded) {
		Assert.assertTrue(button_RemoveProductFromWishList.size() == noOfProductsAdded,
				"Products added into wishlist validation Failed");
	}

	/*
	 * This function sorts the lowest price item in the added Wish list
	 */
	public void searchForLeastPriceItemFromWishlist() {
		int minPrice = 0;
		for (int i = 0; i < label_ProductPrice.size(); i++) {
			String itemPrice[] = null;
			String minimumPrice1[] = null;
			String minimumPrice2[] = null;
			itemPrice = label_ProductPrice.get(i).getText().split("\\s");
			if (itemPrice.length == 2) {
				minimumPrice1 = itemPrice[0].split("\\.");
				minimumPrice2 = itemPrice[1].split("\\.");
				minPrice = Math.min(Integer.parseInt(minimumPrice1[0].substring(1)),
						Integer.parseInt(minimumPrice2[0].substring(1)));
				if (i == 0) {
					itemMinimumPrice = minPrice;
					minimumPriceRow = i + 1;
				} else {
					if (minPrice < itemMinimumPrice) {
						itemMinimumPrice = minPrice;
						minimumPriceRow = i + 1;
					}
				}
			} else {
				minimumPrice1 = itemPrice[0].split("\\.");
				minimumPrice2 = itemPrice[2].split("\\.");
				minPrice = Math.min(Integer.parseInt(minimumPrice1[0].substring(1)),
						Integer.parseInt(minimumPrice2[0].substring(1)));
				minimumPriceRow = i;
				if (i == 0) {
					itemMinimumPrice = minPrice;
					minimumPriceRow = i + 1;
				} else {
					if (minPrice < itemMinimumPrice) {
						itemMinimumPrice = minPrice;
						minimumPriceRow = i + 1;
					}
				}
			}
		}
	}

	/*
	 * This function adds the lowest price item to cart in Wish list page
	 */
	public void addLeastPriceItemToCart() {
		By link_AddToCartLeastPrice = By.xpath("(//a[contains(text(),'Add to cart')])[" + minimumPriceRow + "]");
		clickElement(link_AddToCartLeastPrice);
		noOfItemsAddedToCart = 1;
	}

	/*
	 * This function clicks Cart button
	 */
	public void clickCart() {
		waitForPageToLoad();
		clickElement(link_CartToolTip);
	}

	/*
	 * This function validates the items added to cart page
	 */
	public void validateCartPage() {
		waitForPageToLoad();
		Assert.assertTrue(button_RemoveItemFromCartList.size() == noOfItemsAddedToCart,
				"Validation of Products added into Cart is Failed");
	}

}