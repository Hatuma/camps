package camps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainPage {
	
	private WebDriver driver;

	public MainPage() {
		super();
		this.driver = new FirefoxDriver();
		driver.get("http://westharrisonreservations.com/");
	}
	
	public void close()
	{
		driver.quit();
	}
	
	
}
