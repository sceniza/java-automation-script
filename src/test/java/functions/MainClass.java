package functions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import interfaces.Imain;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static io.restassured.RestAssured.*;
import org.json.JSONObject;

public class MainClass implements Imain{
  public static String systemPath = System.getProperty("user.dir");
  private static final char[] NullPointerException = null;
  private String BASE_URL= System.getenv("base_url");
  public JSONObject requestParams = new JSONObject();
  String url = null;
  String thisPage = null;
  Response response;
  String responseValue;
  protected static WebDriver driver;	

	public void accessDriver (){
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--ignore-certificate-errors");
    options.addArguments("--window-size=1920,1200");
    options.addArguments("--ignore-certificate-errors");

    WebDriverManager.chromedriver().setup();
		ChromeDriver localdriver = new ChromeDriver(options);
    driver = localdriver;
	}

  public WebDriver getDriver() {
		return driver;
	}

  public void accessUrl () {
    driver.get(BASE_URL);
	};

  public currentPage getPage = page -> thisPage = page;

  private String findElementInCsv(String thisPage, String locator) {
    String filename = "Data/" + thisPage + "Locators.csv";
    String[] result = {null};
    String path = systemPath + "/src/test/resources/" + filename;
    String line = "";
      
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        
      while ((line = br.readLine()) != null){
        String[] data = line.split(",");
          if(data[0].equals(locator)){
            result[0]= data[1];
          }
      }
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }catch(IOException e) {
      e.printStackTrace();
    }

    return result[0];

  };

  getelement getElement = locator -> {
		String selector =findElementInCsv(thisPage, locator);
		WebElement element = driver.findElement(By.xpath(selector));
		return element;
	};

  public clickElement clickElementDisplayed = locator -> {
    if (locator != null){
      getElement.get(locator).click();

      try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      }
  };

  public fill_text fill_text_field = (value, locator)-> getElement.get(locator).sendKeys(value);

  public void wait(String locator){
    String selector =findElementInCsv(thisPage, locator);
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selector)));
  }
    
  public acceptalert acceptAlertMessage = ()-> {driver.switchTo().alert().accept();};

  public LinkedHashMap<String, String> dataTable(){
    List<WebElement> allRowsEle = driver.findElements(By.xpath("//table//tbody//tr"));
    LinkedHashMap<String, String> items = new LinkedHashMap<>();

    for (int i = 0; i <= allRowsEle.size(); i++) {
      String specificRowLoc = "//table//tbody//tr[" + i + "]";
      List<WebElement> columnsCells = driver.findElements(By.xpath(specificRowLoc + "//td"));
      if (columnsCells != null && columnsCells.size() > 3){
        items.put(columnsCells.get(1).getText(), columnsCells.get(2).getText());
      }
    }

    return items;

    };
    
  public void AddedItems (Map<String, String> dataTable){
    if (dataTable.equals(dataTable()) == true) {
      return;
    } else {
       System.out.println(NullPointerException);
    }
  }
    
  public payload postrecord = (url,path,body) -> {
    return given().baseUri(url).basePath(path).header("Content-Type","application/json" ).body(body.toString()).
      when().post().then().extract().response();
  };
    
  public add_params i_add_parameter_with_value = (key,value) -> {
    requestParams.put(key, value);
  };

  public void request(String request,String urlParam) {

    response = switch(request.toUpperCase()) { 
    case "POST" -> postrecord.response(BASE_URL, urlParam,requestParams);
    default -> throw new IllegalArgumentException("Unexpected value: " +
      request.toUpperCase()); };

  }
    
  public void the_response_code_should_be(Integer status) {
    Integer code = response.getStatusCode();
    if(!status.equals(code)) {
      throw new IllegalArgumentException("HTTP code does not match expected. " + code);
    }
  }

  public value responseBody = (key,value) -> {
    ObjectMapper mapper = new ObjectMapper();  
    responseValue = response.body().asString();

    try {  
      Map<String, Object> userData = mapper.readValue(  
      responseValue, new TypeReference<Map<String, Object>>() {});   
        if (userData.get(key).equals(value) == true){
          return;
        } else {
          System.out.print(NullPointerException);
        }
          
    } catch (Exception e) {  
        e.printStackTrace();  
    }   
  };

  public items cart= (value) ->{
    JsonPath cart = new JsonPath(response.asString());
    String items = cart.getString("Items.prod_id");
      
    if (items.contains(value)){
      return;
    } else {
      System.out.print(NullPointerException);
    }
  };
}