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
	
	public MainPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void close()
	{
		driver.quit();
	}
	
	public List<WebElement> getSmallButtons()
	{
		return driver.findElements(By.className("nivo-control"));
	}
	
	public WebElement getActiveSmallButton()
	{
		return driver.findElement(By.xpath("//a[contains(@class,'active')]"));
	}
	
	public WebElement rightButton()
	{
		return driver.findElement(By.className("nivo-nextNav"));
	}
	
	public WebElement leftButton()
	{
		return driver.findElement(By.className("nivo-prevNav"));
	}
	
	public void rightButtonClick()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", rightButton());
	}
	
	public void leftButtonClick()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", leftButton());
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
	
	public void up()
	{
		for (int i=0; i<10;i++)
		{
			try
			{
				driver.findElement(By.id("toTop")).click();
				return;
			}
			catch(Exception e)
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public int readPosition()
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Long value = (Long) executor.executeScript("return window.scrollY;");
		return value.intValue();
	}
	
	public List<WebElement> getReadMeButtons()
	{
		return driver.findElements(By.xpath("//div[@class='columnmore']/a"));
	}
	
	public CampPage openCampPage(WebElement readMeButton,String campName)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", readMeButton);
		return new CampPage(driver,campName);
	}
	

}
