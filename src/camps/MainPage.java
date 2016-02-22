package camps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
	public List<WebElement> getSmallButtons()
	{
		return driver.findElements(By.className("nivo-control"));
	}
	
	public WebElement rightButton()
	{
		return driver.findElement(By.className("nivo-nextNav"));
	}
	
	public WebElement leftButton()
	{
		return driver.findElement(By.className("nivo-prevNav"));
	}
	
	public String slideTitle()
	{
		return driver.findElement(By.className("nivo-caption")).findElement(By.tagName("h2")).getText();
	}
	
	public String slideText()
	{
		return driver.findElement(By.className("slide-text")).getText();
	}
	
}
