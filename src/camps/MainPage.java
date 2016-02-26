package camps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	public void scrollDown()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 2000);");
	}
	
	public String findTextByCampName(String name)
	{
		WebElement res=driver.findElement(By.xpath("//div/a/h3[contains(text(),'"+name+"')]/../../div[@class='column-image']/div[@class='column-text']"));
		String rez=res.getAttribute("textContent");
		return rez;
	}
	
	public ReservationPage openReservation()
	{
		driver.findElement(By.linkText("Make A Reservation")).click();
		return new ReservationPage(driver);
	}
	
	public WebElement notActivOptionButton()
	{
		return driver.findElement(By.className("nivo-control"));
	}
	
	public boolean checkActiveOptionButton(String rel)
	{
		return driver.findElement(By.xpath("//a[@rel='"+rel+"']")).getAttribute("class").compareTo("nivo-control active")==0;
	}
	
}
