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
	
	public void backToMain()
	{
		driver.findElement(By.linkText("Home")).click();
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
}
