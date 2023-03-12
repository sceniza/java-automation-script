package interfaces;

public interface IstepsFeatures {
    public void i_am_on_homepage();
    public void i_am_on(String page);
    public void i_accept();
    public void i_enter(String value, String locator);
    public void i_open_browser();
    public void i_add_parameter(String key, String value);
    public void i_request(String request,String urlParam);
    public void resonse_code(Integer status);
    public void i_Should_See(String key, String value);
}