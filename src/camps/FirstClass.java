package camps;

import org.openqa.selenium.WebElement;

public class FirstClass {
//we will write test metodes here
	
	/*test case T1
	 * for now just open and close the web site 
	 */
	public static void t1() throws InterruptedException
	{
		String title="Recreation Sites & Trail BC";
		String text="Through Rec.SitesandTrails.BC we offer a respectful, family oriented wilderness camping experience, focused on improving all aspects of the customer’s enjoyment.";
		
		MainPage main=new MainPage();
		for(WebElement button:main.getSmallButtons())
		{
			button.click();
			Thread.sleep(2000);
			if (main.slideTitle().compareTo(title)!=0)
			{
				System.err.println("Wrong title!\n title is:\n "+main.slideTitle());
			}
			if (main.slideText().compareTo(text)!=0)
				System.err.println("Wrong text!\n text is:\n "+main.slideText());
		}
		Thread.sleep(2000);
		main.close();
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		t1();
	}
}
