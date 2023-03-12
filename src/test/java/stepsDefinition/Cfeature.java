package stepsDefinition;
import java.util.Map;

import functions.MainClass;
import interfaces.IstepsFeatures;
import io.cucumber.java.en.*;

public class Cfeature implements IstepsFeatures{
	MainClass main = new MainClass();
    
    @Given("I open browser")
	public void i_open_browser() {
		main.accessDriver();
	}

    @Given("I am on homepage")
	public void i_am_on_homepage() {
		main.accessUrl();
	}

    @Given("Im on {string}")
	public void i_am_on(String page) {
		main.getPage.currentpage(page);
	}

	@Given("I wait for the {string} to be visible")
	public void wait(String locator) {	 
		main.wait(locator);
	}

    @When("I click {string}")
	public void i_click(String locator) {
		main.clickElementDisplayed.click(locator);
	}

	@When ("I enter {string} in {string} field")
	public void i_enter(String value, String locator){
		main.fill_text_field.fill(value, locator);
	}

	@When("I accept alert message")
	public void i_accept() {
		main.acceptAlertMessage.accept();
	}
    
	@Then ("I should see Items Added:")
	@Then ("I should see updated items in the cart:")
	public void items_added(Map<String, String>dataTable){
		main.AddedItems(dataTable);
	}

	@Given ("I add parameter {string} with value {string}")
	public void i_add_parameter(String key, String value){
		main.i_add_parameter_with_value.add(key,value);
	}
    
	@When("^I request \"(POST) (.*?)\"$")
	public void i_request(String request,String urlParam){
		main.request(request, urlParam);
	}

	@Then("^The response code should be \"(.*?)\"$")
	public void resonse_code(Integer status){
	}

	@Then ("The {string} should have {string}")
	public void i_Should_See(String key, String value){
		main.responseBody.val(key, value);
	}

	@Then ("The key value of prod_id should be {string}")
	public void cart_items(String value){
		main.cart.products(value);
	}
}