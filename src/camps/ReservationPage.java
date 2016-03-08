package camps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservationPage {

	private WebDriver driver;
	
	public ReservationPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean isItReservationPage()
	{
		return driver.findElement(By.className("entry-title")).getText().compareTo("Make A Reservation")==0;
	}
	
	@SuppressWarnings("deprecation")
	public void setStartDate(Date date)
	{
		WebElement calendarFrame=driver.findElement(By.id("bookingcalendar"));
		driver.switchTo().frame(calendarFrame);
	    WebElement input = driver.findElement(By.id("sstartdate"));
	    input.sendKeys(date.getMonth()+"/"+date.getDay()+"/"+(date.getYear()%100));
	    driver.switchTo().defaultContent();
	}
	
	@SuppressWarnings("deprecation")
	public void setEndDate(Date date)
	{
		WebElement calendarFrame=driver.findElement(By.id("bookingcalendar"));
		driver.switchTo().frame(calendarFrame);
	    WebElement input = driver.findElement(By.id("senddate"));
	    input.sendKeys(date.getMonth()+"/"+date.getDay()+"/"+(date.getYear()%100));
	    driver.switchTo().defaultContent();
	}
	
	public void submitSearch()
	{
		WebElement calendarFrame=driver.findElement(By.id("bookingcalendar"));
		driver.switchTo().frame(calendarFrame);
		WebElement submit = driver.findElement(By.id("btnAddToCart"));
		submit.click();
		driver.switchTo().defaultContent();
	}
	
	public boolean checkCamp(String campName)
	{
		WebElement calendarFrame=driver.findElement(By.id("bookingcalendar"));
		driver.switchTo().frame(calendarFrame);
		boolean ok;
		try
		{
			
			ok=driver.findElement(By.xpath("//div[@class='item']/div/h3/a[contains(text(),'"+campName+"')]"))!=null;
			
		}
		catch(Exception e)
		{
			ok=false;
			//e.printStackTrace();
		}
		finally
		{
		driver.switchTo().defaultContent();
		}
		return ok;
	}
	
	public boolean checkShortCamp(String sortName,String longName)
	{
		WebElement calendarFrame=driver.findElement(By.id("bookingcalendar"));
		driver.switchTo().frame(calendarFrame);
		boolean ok;
		try
		{
			
			List<WebElement> list=driver.findElements(By.xpath("//div[@class='item']/div/h3/a"));
			List<String> names=new ArrayList<>();
			for (WebElement element:list)
				names.add(element.getText());
			ok=false;
			for (String s:names)
			{
				if (s.contains(sortName)&& !s.contains(longName))
					ok=true;
			}
		}
		catch(Exception e)
		{
			ok=false;
			e.printStackTrace();
		}
		finally
		{
		driver.switchTo().defaultContent();
		}
		return ok;
	}
	
	public void close()
	{
		driver.quit();
	}
	
	public MainPage backToMain()
	{
		driver.findElement(By.linkText("Home")).click();
		return new MainPage(driver);
	}
	
}
