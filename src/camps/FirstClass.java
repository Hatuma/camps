package camps;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FirstClass {
//we will write test metodes here
	
	public static void sleep(int time)
	{
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**test case T1
	 * open main page
	 * find all small option buttons
	 * check that every slide has the same given title and text
	 * close the site
	 */
	@Test
	public void t1()
	{
		boolean ok=true;
		String title="Recreation Sites & Trail BC";
		String text="Through Rec.SitesandTrails.BC we offer a respectful, family oriented wilderness camping experience, focused on improving all aspects of the customer’s enjoyment.";		
		MainPage main=new MainPage();
		
		for(WebElement button:main.getSmallButtons())
		{
			button.click();
			sleep(500);
			if (main.slideTitle().compareTo(title)!=0)
			{
				System.err.println("t1:Wrong title!\n title is:\n "+main.slideTitle());
				ok=false;
			}
			if (main.slideText().compareTo(text)!=0)
			{
				System.err.println("t1:Wrong text!\n text is:\n "+main.slideText());
				ok=false;
			}
		}
		main.close();
		assertEquals(ok,true);
	}
	/**test case 2
	 * open main page
	 * scroll down
	 * check the text in the images
	 */
	@Test
	public void t2()
	{
		boolean ok=true;
		HashMap<String, String> titlesAndText=new HashMap<String, String>();
		titlesAndText.put("Weaver Lake", "There is no better way to spend a day than on the water. Weaver Lake is a medium sized lake, large enough to spend a few days exploring, but not large enough to get lost. Bring your own boat, use the public boat launch, spend a memorable day on the water.");
		titlesAndText.put("Weaver Lake Group Site", "Enjoy exclusive use of this group site for your family or group gatherings. This site is located at the north east side of Weaver Lake and boasts a wharf, 10 tenting pad sites and a communal fire ring.");
		titlesAndText.put("Grace Lake", "Enjoy this small but popular camping site located next to Grace Lake. There is even a floating dock to fish from. A nice feature of this recreation site is the designated staging area and parking lot for motorized recreation that accesses the West Harrison Riding Area.");
		titlesAndText.put("Wolf Lake", "A favorite small group campsite situated next to Wolf Lake. Enjoy fishing off the wharf. There are 3 designated sites with picnic tables, fire rings and an outhouse.");
		titlesAndText.put("Wood Lake", "A large semi-open site with two camping areas, one on each side of the lake. Access is by gravel road and no power boats are allowed. We have picnic tables and fire rings at every site and outhouses positioned around the property.");
		titlesAndText.put("Wood Lake Group Site", "This site is a large semi-open site with 6 camping sites. You are given a key for the gate, so you can come and go as you please. There is lots of room for extra vehicles by the campsites.  Access is by gravel road and no power boats are allowed.");
		titlesAndText.put("Twenty Mile Bay", "The sites are nestled in an old growth forest and is situated on a peninsula with over 1.5 km’s beaches facing North and South on the West shore of Harrison Lake.  We have Picnic tables and fire pits at every site and outhouses positioned around the property.");
		titlesAndText.put("Chehalis River", "This is a beautiful site situated amongst mature timber. It borders the Chehalis River, which offers excellent steelhead fishing opportunities. There are two smaller sites situated across the road that also border the river. The two smaller sites are used mainly as group sites and for day-use activities.");
		titlesAndText.put("Chehalis River N. Group Site", "Chehalis River North Group Rec Site is located along the banks of Chehalis River. Access is by paved road. We have Picnic tables and fire pits at every site and outhouses positioned on the property. We offer a beautiful walking trail through the forest that terminates at the river downstream from the campground.");
		titlesAndText.put("Skwellepil Creek", "Come out and enjoy this freshly restored site. Enjoy the large beautiful lake that is 10 km in length and is located in the Chehalis Valley, west of Harrison Lake. A boat launch is available for those interested in fishing, or exploring the coves and bays along the shoreline.");
				
		MainPage main=new MainPage();
		main.scrollDown();
		for (String s:titlesAndText.keySet())
		{
			if (!main.findTextByCampName(s).contains(titlesAndText.get(s)))
			{
				System.err.println("t2:wrong text, have this:\n"+main.findTextByCampName(s)+"\nWe need this:\n"+titlesAndText.get(s));
				ok=false;
			}
		}
		main.close();
		assertEquals(ok,true);
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
	@Test
	@SuppressWarnings("deprecation")
	public void t3()
	{
		boolean ok=true;
		MainPage main=new MainPage();
		ReservationPage rp=main.openReservation();	
		rp.gotInIFrame();
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
				ok=false;
			}
		}
		if (!rp.checkShortCamp("Weaver Lake", "Weaver Lake Group Site"))
		{
			System.err.println("t3:camp Weaver Lake not found!");
			ok=false;
		}
		if (!rp.checkShortCamp("Wood Lake", "Wood Lake Group Site"))
		{
			System.err.println("t3:camp Wood Lake not found!");
			ok=false;
		}
		if (!rp.checkShortCamp("Chehalis River", "Chehalis River North Group Site"))
		{
			System.err.println("t3:camp Chehalis River not found!");
			ok=false;
		}
		rp.quitIFrame();
		rp.close();
		assertEquals(ok,true);
	}
	
	/** test case 4
	 * 	go to westharrisonreservations.com
	 *  chose a non active option button (Click on an empty circle at the bottom of the slideshow )
	 *  verify the button will be active in 0.5 sec
	 */
	@Test
	public void t4()
	{
		boolean ok=true;
		MainPage main=new MainPage();
		WebElement button=main.notActivOptionButton();
		button.click();
		String rel=button.getAttribute("rel");
		sleep(500);
		if (!main.checkActiveOptionButton(rel))
		{
			System.err.println("t4: button not active");
			ok=false;
		}
		main.close();
		assertEquals(ok,true);
	}
	/**	test case 5
	 *  go to westharrisonreservations.com
	 *  scroll down
	 *  return back up with the little button on the right
	 */
	@Test
	public void t5()
	{
		boolean ok=true;
		MainPage main=new MainPage();
		main.scrollDown();
		main.up();
		int poz1=main.readPosition();
		sleep(50);
		int poz2=main.readPosition();
		while (poz1!=poz2)
		{
			sleep(50);
			poz1=poz2;
			poz2=main.readPosition();
		}
		if (poz2!=0)
		{
			System.err.println("t5: didn't scrolled up, position="+poz2);
			ok=false;
		}
		main.close();
		assertEquals(ok,true);
	}
	
	/** test case 6
	 * go to westharrisonreservations.com
	 * scroll the slides with the small button on the right from the first to the last, then one more to return back to the first
	 * same as the step 2, just from right to left
	 */
	@Test
	public void t6()
	{
		boolean ok=true;
		MainPage main=new MainPage();
		int n=main.getSmallButtons().size();
		int current=Integer.parseInt(main.getActiveSmallButton().getAttribute("rel"));
		for (int i=0;i<n;i++)
		{
			main.rightButtonClick();;
			sleep(2000);
			current+=1;
			current%=n;
			if (!main.checkActiveOptionButton(""+current))
			{
				System.err.println("t6: the slide show didn't got to the left to slide "+current);
				ok=false;
			}
		}
		for (int i=0;i<n;i++)
		{
			main.leftButtonClick();
			sleep(2000);
			current+=n-1;
			current%=n;
			if (!main.checkActiveOptionButton(""+current))
			{
				System.err.println("t6: the slide show didn't got to the right to slide "+current);
				ok=false;
			}
		}
		main.close();
		assertEquals(ok,true);
	}
	//start 16.03.07 8:15
	//finished 16.03.07 11:00
	//active programming time 1:45
	/**test case 7
	 * go to main page
	 * scroll down
	 * find read more buttons
	 * go to every page and check
	 * -page h1 title is equal to the camp for the read more 
	 * -slide show is present
	 * -navigation menu is present
	 */
	@Test
	public void t7()
	{
		boolean ok=true;
		MainPage main=new MainPage();
		main.scrollDown();
		CampPage camp;
		List<WebElement>buttons=main.getReadMeButtons();
		for(int i=0;i<buttons.size();i++)
		{
			buttons=main.getReadMeButtons();
			WebElement currentReadMe=buttons.get(i);
			camp=main.openCampPage(currentReadMe,currentReadMe.findElement(By.xpath("//a[@href='"+currentReadMe.getAttribute("href")+"']/../../a/h3")).getText());
			if (!camp.checkName())
			{
				System.err.println("t7: wrong camp page, need: "+camp.campName+" found: "+camp.getPageTitle());
				ok=false;
			}
			if (!camp.checkSlideShow())
			{
				System.err.println("t7: slideshow missin on page "+camp.campName);
				ok=false;
			}
			if (!camp.checkNavigationMenu())
			{
				System.err.println("t7: navigation menu missin on page "+camp.campName);
				ok=false;
			}
			camp.backToMain();
			main.scrollDown();
		}
		main.close();
		assertEquals(ok,true);
	}
	
		//start 16.03.07 11:45
		//finished 16.03.08 11:00
		//active programming time 4:00
	/* test case 8
	 * Go to westharrisonreservations.com 
	 * Go to Chehalis River 
	 * Click on every horizontal menu item 
	 * Verify that the content of the page changes accordingly to selected menu item 
	 * Verify to all other campgrounds also
	 */
	@Test
	public void t8()
	{
		boolean ok=true;
		MainPage main=new MainPage();
		main.scrollDown();
		CampPage camp;
		List<WebElement>buttons=main.getReadMeButtons();
		for(int i=0;i<buttons.size();i++)
		{
			buttons=main.getReadMeButtons();
			WebElement currentReadMe=buttons.get(i);
			camp=main.openCampPage(currentReadMe,currentReadMe.findElement(By.xpath("//a[@href='"+currentReadMe.getAttribute("href")+"']/../../a/h3")).getText());
			
			try{
			camp.goToOverview();
			main.scrollDown();
			if (!camp.isItOverviewPage())
			{
				System.err.println("t8: didnt found overview page for camp "+camp.campName);
				ok=false;
			}
			}catch(Exception e)
			{
				System.err.println("t8: didnt found overview page for camp "+camp.campName);
				ok=false;
			}
			
			try{
			ReservationPage respage=camp.goToReservation();
			if (!respage.isItReservationPage())
			{
				System.err.println("t8: reservation page not found for camp "+camp.campName);
				ok=false;
			}
			main=respage.backToMain();
			buttons=main.getReadMeButtons();
			currentReadMe=buttons.get(i);
			main.scrollDown();
			camp=main.openCampPage(currentReadMe,currentReadMe.findElement(By.xpath("//a[@href='"+currentReadMe.getAttribute("href")+"']/../../a/h3")).getText());
			}catch(Exception e)
			{
				System.err.println("t8: reservation page not found for camp "+camp.campName);
				ok=false;
			}
	
			try{
			camp.goToCampsites();
			main.scrollDown();
			if (!camp.isItCampsitesPage())
			{
				System.err.println("t8: didnt found campsites page for camp "+camp.campName);
				ok=false;
			}
			}catch(Exception e)
			{
				System.err.println("t8: didnt found campsites page for camp "+camp.campName);
				ok=false;
			}

			try{
			camp.goToMap();
			if (!camp.isItMapPage())
			{
				System.err.println("t8: didnt found map page for camp "+camp.campName);
				ok=false;
			}
			}catch(Exception e)
			{
				System.err.println("t8: didnt found map page for camp "+camp.campName);
				ok=false;
			}
			
			try{
			camp.goToDrivingDir();
			if (!camp.isItDrivingDir())
			{
				System.err.println("t8: didnt found driving directions page for camp "+camp.campName);
				ok=false;
			}
			}catch(Exception e)
			{
				System.err.println("t8: didnt found driving directions page for camp "+camp.campName);
				ok=false;
			}
			
			try{
			camp.goToWeather();
			if (!camp.isItWeatherPage())
			{
				System.err.println("t8: didnt found weather page for camp "+camp.campName);
				ok=false;
			}
			}catch(Exception e)
			{
				System.err.println("t8: didnt found weather page for camp "+camp.campName);
				ok=false;
			}
			
			try{
			camp.goToContactUs();
			if (!camp.isItContactUs())
			{
				System.err.println("t8: didnt found contact us page for camp "+camp.campName);
				ok=false;
			}
			}catch(Exception e)
			{
				System.err.println("t8: didnt found contact us page for camp "+camp.campName);
				ok=false;
			}
			
			try{
			camp.goToDrivingDir();
			if (!camp.isItGuidelines())
			{
				System.err.println("t8: didnt found Guidelines page for camp "+camp.campName);
				ok=false;
			}
			}catch(Exception e)
			{
				System.err.println("t8: didnt found Guidelines page for camp "+camp.campName);
				ok=false;
			}

			main=camp.backToMain();
		}
		main.close();
		assertEquals(ok,true);
	}
	
			//start 16.03.09 9:10
			//finished 16.03.09 10:10
			//active programming time 1:00 
		/* test case 9
		 * Go to westharrisonreservations.com 
		 * Go to Chehalis River 
		 * Click on Campites horizontal menu 
		 * Click on the first campsite's picture 
		 * Verify that the popup is present with the pictures 
		 * Verify to all other campgrounds also
		 */
	@Test
	public void t9()
	{
		boolean ok=true;
		MainPage main=new MainPage();
		main.scrollDown();
		CampPage camp;
		List<WebElement>buttons=main.getReadMeButtons();
		for(int i=0;i<buttons.size();i++)
		{
			buttons=main.getReadMeButtons();
			WebElement currentReadMe=buttons.get(i);
			camp=main.openCampPage(currentReadMe,currentReadMe.findElement(By.xpath("//a[@href='"+currentReadMe.getAttribute("href")+"']/../../a/h3")).getText());
			try{
				camp.goToCampsites();
				main.scrollDown();		
				if (!camp.isItCampsitesPage())
				{
					System.err.println("t9: didnt found campsite on campsites page for camp "+camp.campName);
					ok=false;
				}
				else
				{
					try
					{
						camp.clickOnFirstCamp();
						if (!camp.isItPopUp())
						{
							System.err.println("t8: didnt found popup for the first campsite on campsites page for camp "+camp.campName);
							ok=false;
						}
					}catch(Exception e)
					{
						System.err.println("t9: didnt found first camp on campsites page for camp "+camp.campName);
						ok=false;
					}
					
				}
			}catch(Exception e)
			{
				System.err.println("t9: didnt found campsites page for camp "+camp.campName);
				ok=false;
			}
			main=camp.backToMain();
		}
		main.close();
		assertEquals(ok,true);
	}
	
				//start 16.03.10 8:35
				//finished 16.03.11 8:21
				//active programming time 3:00
			/* Go to westharrisonreservations.com
			 * Go to Make A Reservation page
			 * Select any campground
			 * Select any from date and end date for reservation
			 * Select any campsite
			 * verify:
			 * 		the selected campsite is in the shopping cart
			 * 		the name of the campground
			 * 		the name of the campsite
			 * 		the presence of the Change and Remove button
			 * Click on remove button
			 * Verify that the shopping cart is empty
			 */
	@Test
	public void t10()
	{
		MainPage main=new MainPage();
		boolean ok=true;
		ReservationPage rpage=main.openReservation();
		rpage.gotInIFrame();
		rpage.viewProducts();
		String camp=rpage.choseRandomCamp();
		String campsite=rpage.choseAFreeInterval();
		rpage.nextButton();
		sleep(2000);
		ok&=rpage.getFirstCampNameFromCart().contains(camp);
		if(!ok)
		{
			System.err.println("t10: wrong camp name");
		}
		if(!rpage.checkCampsite(campsite))
		{
			System.err.println("t10: campsite name: "+campsite+" not found");
			ok=false;
		}
		if (rpage.checkChangeAndRemove())
		{
			rpage.removeCamp();
			if (!rpage.isCartEmpty())
			{
				System.err.println("t10: the cart is not empty!");
				ok=false;
			}
		}
		else
		{
			System.err.println("t10: change or remove button not found");
			ok=false;
		}		
		rpage.quitIFrame();
		rpage.close();
		assertEquals(ok,true);
	}
	
			//start 16.03.11 8:30
			//finished 16.03.11 
			//active programming time 
		/* Go to westharrisonreservations.com
		 * Go to Make A Reservation page
		 * Select any campground
		 * Select any from date and end date for reservation
		 * Select any campsite
		 * verify:
		 * 		the selected campsite is in the shopping cart
		 * 		the name of the campground
		 * 		the name of the campsite
		 * 		the presence of the Change and Remove button
		 *  Click on Continue Shopping
		 *  Repeat steps 3,4,5,6
		 *  Remove the first entry from the shopping cart
		 *  Verify that the item has been removed
		 *  Remove the rest items from shopping cart
		 *  Verify that the shopping cart is empty
		 */
		@Test
		public void t11()
		{
			int n=3;
			MainPage main=new MainPage();
			boolean ok=true;
			ReservationPage rpage=main.openReservation();
			rpage.gotInIFrame();
			rpage.viewProducts();
			String[] camps=new String[n];
			for (int i=0; i<n;i++)
			{
				camps[i]=rpage.choseRandomCamp();
				String campsite=rpage.choseAFreeInterval();
				rpage.nextButton();
				sleep(2000);
				if(!rpage.isCampInCart(camps[i]))
				{
					System.err.println("t11: wrong camp name");
					ok=false;
				}
				if(!rpage.checkCampsite(campsite))
				{
					System.err.println("t11: campsite name: "+campsite+" not found");
					ok=false;
				}
				if (!rpage.checkChangeAndRemove())
				{
					System.err.println("t11: change or remove button not found");
					ok=false;
				}	
				if(i<n-1)
				{
					rpage.continueShopping();
				}
			}
			for(int i=0;i<n;i++)
			{
				rpage.removeCamp();
				if(rpage.isCampInCart(camps[i]))
				{
					System.err.println("t11: camp haven't been removed: "+camps[i]);
					ok=false;
				}
				
			}
			rpage.quitIFrame();
			rpage.close();
			assertEquals(ok,true);
		}
}
