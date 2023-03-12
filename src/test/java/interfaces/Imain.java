package interfaces;

import org.openqa.selenium.WebElement;
import org.json.JSONObject;
import io.restassured.response.Response;

public interface Imain {

	public interface browserDrivers {
		public void driver();
	}
	
    public interface mainURL {
		public void currentURL();
	}
    public interface currentPage {
		public void currentpage(String page);
	}
		    
    public interface clickElement {
		public void click(String locator);
	}

    public interface getelement {
		WebElement get(String locator);
	}

    public interface acceptalert{
        public void accept();
    }

	public interface fill_text{
		public void fill(String value, String locator);
	}

    public interface payload {
        Response response(String url,String path,JSONObject requestParams);
    }

	public interface add_params{
		public void add(String key, String value);
	}
    
	public interface consolidate{
		public void string(String format);
	}

	public interface value{
		public void val(String key, String value);
	}
	public interface items{
		public void products(String value);
	}
}