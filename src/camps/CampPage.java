package camps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampPage {

	WebDriver driver;
	String campName;
	public CampPage(WebDriver driver,String campName)
	{
		this.driver=driver;
		this.campName=campName;
	}
	
	public MainPage backToMain()
	{
		driver.findElement(By.linkText("Home")).click();
		return new MainPage(driver);
	}
	
	public String getPageTitle()
	{
		return driver.findElement(By.xpath("//h1")).getText();
	}
	
	public boolean checkSlideShow()
	{
		return driver.findElements(By.id("slideshow")).size()==1;
	}
	
	public boolean checkName()
	{
		boolean ok=getPageTitle().compareTo(campName)==0;
		ok|=getPageTitle().compareTo("Wolf Lake Group Site")==0&&campName.compareTo("Wolf Lake")==0;
		ok|=getPageTitle().compareTo("Chehalis River North Group Site")==0&&campName.compareTo("Chehalis River N. Group Site")==0;
		return ok;
	}
	
	public boolean checkNavigationMenu()
	{
		return driver.findElements(By.id("navigation-menu")).size()==1;
	}
	
	public void goToOverview()
	{
		driver.findElement(By.id("goto_overview")).click();
	}
	
	public ReservationPage goToReservation()
	{
		driver.findElement(By.id("goto_reserve")).click();
		return new ReservationPage(driver);
	}
	
	public void goToCampsites()
	{
		driver.findElement(By.id("goto_list_of_campsites")).click();
	}
	
	public void goToMap()
	{
		driver.findElement(By.id("goto_map_of_campground")).click();
	}
	
	public void goToDrivingDir()
	{
		driver.findElement(By.id("goto_driving_directions")).click();
	}
	
	public void goToWeather()
	{
		driver.findElement(By.id("goto_weather")).click();
	}
	
	public void goToContactUs()
	{
		driver.findElement(By.id("goto_contactform")).click();
	}
	
	public void goToGuidlines()
	{
		driver.findElement(By.id("goto_guidlines")).click();
	}
	
	public boolean isItOverviewPage()
	{
		try
		{
			return driver.findElement(By.xpath("//h2[text()='Overview']")).isDisplayed();
			
		}
		catch(Exception e)
		{
			try
			{
				return driver.findElement(By.xpath("//h1[text()='Overview']")).isDisplayed();
			}
			catch (Exception e2)
			{
				return false;
			}
		}
	}
	
	public boolean isItCampsitesPage()
	{
		try
		{
			driver.findElement(By.xpath("//h3[contains(text(),'Campsite')]"));	
		}
		catch(Exception e)
		{
			try
			{
				driver.findElement(By.xpath("//h2[contains(text(),'Campsite')]"));
			}
			catch (Exception e2)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean isItMapPage()
	{
		try
		{
			return driver.findElement(By.xpath("//h2[text()='Map of Campground']"))!=null;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public boolean isItDrivingDir()
	{
		try
		{
			return driver.findElement(By.xpath("//h2[text()='Driving Directions']"))!=null;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public boolean isItWeatherPage()
	{
		try
		{
			return driver.findElement(By.id("weather"))!=null;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}

	public boolean isItContactUs() 
	{
		try
		{
			return driver.findElement(By.id("contact_form")).getText().contains("If you have any questions or suggestions, we ");
		}
		catch(Exception e)
		{
			return false;
		}
		
	}

	public boolean isItGuidelines()
	{
		try
		{
			return driver.findElement(By.xpath("//h2[text()='Campground Guidelines ']"))!=null;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
}
