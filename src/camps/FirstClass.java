package camps;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

public class FirstClass {
//we will write test metodes here
	
	/**test case T1
	 * open main page
	 * find all small option buttons
	 * check that every slide has the same given title and text
	 * close the site
	 */
	public static void t1() throws InterruptedException
	{
		String title="Recreation Sites & Trail BC";
		String text="Through Rec.SitesandTrails.BC we offer a respectful, family oriented wilderness camping experience, focused on improving all aspects of the customer�s enjoyment.";
		
		MainPage main=new MainPage();
		for(WebElement button:main.getSmallButtons())
		{
			button.click();
			Thread.sleep(2000);
			if (main.slideTitle().compareTo(title)!=0)
			{
				System.err.println("t1:Wrong title!\n title is:\n "+main.slideTitle());
			}
			if (main.slideText().compareTo(text)!=0)
				System.err.println("t1:Wrong text!\n text is:\n "+main.slideText());
		}
		Thread.sleep(2000);
		main.close();
	}
	/**test case 2
	 * open main page
	 * scroll down
	 * check the text in the images
	 */
	
	public static void t2() throws InterruptedException
	{
		HashMap<String, String> titlesAndText=new HashMap<String, String>();
		titlesAndText.put("Weaver Lake", "There is no better way to spend a day than on the water. Weaver Lake is a medium sized lake, large enough to spend a few days exploring, but not large enough to get lost. Bring your own boat, use the public boat launch, spend a memorable day on the water.");
		titlesAndText.put("Weaver Lake Group Site", "Enjoy exclusive use of this group site for your family or group gatherings. This site is located at the north east side of Weaver Lake and boasts a wharf, 10 tenting pad sites and a communal fire ring.");
		titlesAndText.put("Grace Lake", "Enjoy this small but popular camping site located next to Grace Lake. There is even a floating dock to fish from. A nice feature of this recreation site is the designated staging area and parking lot for motorized recreation that accesses the West Harrison Riding Area.");
		titlesAndText.put("Wolf Lake", "A favorite small group campsite situated next to Wolf Lake. Enjoy fishing off the wharf. There are 3 designated sites with picnic tables, fire rings and an outhouse.");
		titlesAndText.put("Wood Lake", "A large semi-open site with two camping areas, one on each side of the lake. Access is by gravel road and no power boats are allowed. We have picnic tables and fire rings at every site and outhouses positioned around the property.");
		titlesAndText.put("Wood Lake Group Site", "This site is a large semi-open site with 6 camping sites. You are given a key for the gate, so you can come and go as you please. There is lots of room for extra vehicles by the campsites.  Access is by gravel road and no power boats are allowed.");
		titlesAndText.put("Twenty Mile Bay", "The sites are nestled in an old growth forest and is situated on a peninsula with over 1.5 km�s beaches facing North and South on the West shore of Harrison Lake.  We have Picnic tables and fire pits at every site and outhouses positioned around the property.");
		titlesAndText.put("Chehalis River", "This is a beautiful site situated amongst mature timber. It borders the Chehalis River, which offers excellent steelhead fishing opportunities. There are two smaller sites situated across the road that also border the river. The two smaller sites are used mainly as group sites and for day-use activities.");
		titlesAndText.put("Chehalis River N. Group Site", "Chehalis River North Group Rec Site is located along the banks of Chehalis River. Access is by paved road. We have Picnic tables and fire pits at every site and outhouses positioned on the property. We offer a beautiful walking trail through the forest that terminates at the river downstream from the campground.");
		titlesAndText.put("Skwellepil Creek", "Come out and enjoy this freshly restored site. Enjoy the large beautiful lake that is 10 km in length and is located in the Chehalis Valley, west of Harrison Lake. A boat launch is available for those interested in fishing, or exploring the coves and bays along the shoreline.");
				
		MainPage main=new MainPage();
		main.scrollDown();
		for (String s:titlesAndText.keySet())
		{
			if (!main.findTextByCampName(s).contains(titlesAndText.get(s)))
				System.err.println("t2:wrong text, have this:\n"+main.findTextByCampName(s)+"\nWe need this:\n"+titlesAndText.get(s));
		}
		main.close();
	}
	
	/** test case 3	
	 * go to westharrisonreservations.com
	 * go to Make a Reservations page
	 * enter Start date 2016, 1, 1
	 * enter End date 2016, 10, 1
	 * search for reservation options
	 * check there is all "long name" camps as option
	 * check all short name camps (their name are part in other camp names)
	 */
	
	@SuppressWarnings("deprecation")
	public static void t3()
	{
		MainPage main=new MainPage();
		ReservationPage rp=main.openReservation();	
		rp.setStartDate(new Date(2016, 1, 1));
		rp.setEndDate(new Date(2016, 10, 1));
		rp.submitSearch();
		List<String> camps=new ArrayList<>();
		camps.add("Weaver Lake Group Site");
		camps.add("Grace Lake");
		camps.add("Wolf Lake");
		camps.add("Wood Lake Group Site");
		camps.add("Twenty Mile Bay");
		camps.add("Chehalis River North Group Site");
		camps.add("Skwellepil Creek");
		for(String s:camps)
		{
			if (!rp.checkCamp(s))
			{
				System.err.println("t3:camp "+s+" not found!");
			}
		}
		if (!rp.checkShortCamp("Weaver Lake", "Weaver Lake Group Site"))
		{
			System.err.println("t3:camp Weaver Lake not found!");
		}
		if (!rp.checkShortCamp("Wood Lake", "Wood Lake Group Site"))
		{
			System.err.println("t3:camp Wood Lake not found!");
		}
		if (!rp.checkShortCamp("Chehalis River", "Chehalis River North Group Site"))
		{
			System.err.println("t3:camp Chehalis River not found!");
		}
		rp.close();
	}
	
	/** test case 4
	 * 	go to westharrisonreservations.com
	 *  chose a non active option button (Click on an empty circle at the bottom of the slideshow )
	 *  verify the button will be active in 0.5 sec
	 */
	public static void t4()
	{
		MainPage main=new MainPage();
		WebElement button=main.notActivOptionButton();
		button.click();
		String rel=button.getAttribute("rel");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!main.checkActiveOptionButton(rel))
			System.err.println("t4: button not active");
		main.close();
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		//t1();
		//t2();
		//t3();
		t4();
	}
}
