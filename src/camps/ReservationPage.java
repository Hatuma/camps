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
	
	public void gotInIFrame()
	{
		WebElement calendarFrame=driver.findElement(By.id("bookingcalendar"));
		driver.switchTo().frame(calendarFrame);
	}
	
	public void quitIFrame()
	{
		driver.switchTo().defaultContent();
	}
	
	@SuppressWarnings("deprecation")
	public void setStartDate(Date date)
	{	
	    WebElement input = driver.findElement(By.id("sstartdate"));
	    input.sendKeys(date.getMonth()+"/"+date.getDay()+"/"+(date.getYear()%100));    
	}
	
	@SuppressWarnings("deprecation")
	public void setEndDate(Date date)
	{
	    WebElement input = driver.findElement(By.id("senddate"));
	    input.sendKeys(date.getMonth()+"/"+date.getDay()+"/"+(date.getYear()%100));
	}
	
	public void submitSearch()
	{
		WebElement submit = driver.findElement(By.id("btnAddToCart"));
		submit.click();
	}
	
	public boolean checkCamp(String campName)
	{
		boolean ok;
		try
		{			
			ok=driver.findElement(By.xpath("//div[@class='item']/div/h3/a[contains(text(),'"+campName+"')]"))!=null;		
		}
		catch(Exception e)
		{
			ok=false;
		}
		return ok;
	}
	
	public boolean checkShortCamp(String sortName,String longName)
	{
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
	
	public void viewProducts()
	{
		driver.findElement(By.id("btnViewProducts")).click();
	}
	
	public String choseRandomCamp()
	{
		List<WebElement> list=driver.findElements(By.className("pbook"));
		int random=(int)(Math.random()*(list.size()-1));
		String res=list.get(random).findElement(By.xpath("../../../../../../..//h3")).getText();
		list.get(random).click();
		return res;
	}
	
	public String choseAFreeInterval()
	{
		List<WebElement> list=driver.findElements(By.className("av"));
		boolean ok=false;
		int i=0;
		int j=0;
		int count=0;
		while(!ok&&count<10)
		{
			i=0;//i=(int)(Math.random()*(list.size()-2));
			int iday=Integer.parseInt(list.get(i).getText());
			j=i;
			while (j<i+7&&j<list.size()-2&&Integer.parseInt(list.get(j+1).getText())==iday-i+j+1)
			{
				j++;
			}
			if (j>i)
				ok=true;
			count++;
		}
		if(ok)
		{
			list.get(i).click();
			list.get(j).click();
			try
			{
				return driver.findElement(By.id("uid")).findElement(By.xpath("//option[@selected]")).getText();
			}
			catch(Exception e)
			{
				return null;
			}
		}
		else
		{
			System.err.println("we need better algoritam, didnt success to chose a free interval with 10 tries");
			return null;
		}
	}
	
	public void nextButton()
	{
		driver.findElement(By.id("btnAddToCart")).click();;
	}
	
	public String getFirstCampNameFromCart()
	{
		return driver.findElement(By.xpath("//div[@class='desc']/b")).getText();
	}
	
	public List<String> getCampNamesFromCart()
	{
		List<String> res=new ArrayList<>();
		for(WebElement desc:driver.findElements(By.xpath("//div[@class='desc']/b")))
		{
			res.add(desc.getText());
		}
		return res;
	}
	
	public boolean isCampInCart(String camp)
	{
		for(String s:getCampNamesFromCart())
		{
			if (s.contains(camp))
				return true;
		}
		return false;
	}
	
	public boolean checkCampsite(String s)
	{
		if (s==null)
			return true;
		try
		{
			driver.findElement(By.xpath("//div[@class='desc'and text()[contains(.,'"+s+"')]]"));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean checkChangeAndRemove()
	{
		boolean ok=false;
		try
		{
			driver.findElement(By.xpath("//a[text()[contains(.,'Change')]]"));
			driver.findElement(By.xpath("//a[text()[contains(.,'Remove')]]"));
			ok=true;
		}
		catch(Exception e)
		{
			ok=false;
		}
		return ok;
	}
	
	public void removeCamp()
	{
		driver.findElement(By.xpath("//a[text()[contains(.,'Remove')]]")).click();;
	}
	
	public boolean isCartEmpty()
	{
		boolean ok=false;
		try
		{
			driver.findElement(By.xpath("//p[text()[contains(.,'Your shopping cart is empty.')]]"));
			ok=true;
		}
		catch(Exception e)
		{
			ok=false;
		}
		return ok;
	}
	
	public void continueShopping()
	{
		driver.findElement(By.xpath("//button[text()[contains(.,'Continue Shopping')]]")).click();;
	}
}
