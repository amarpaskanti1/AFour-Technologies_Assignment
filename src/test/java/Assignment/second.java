package Assignment;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class second {

	static WebDriver driver;

	@Test
	public void TC2() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.flightradar24.com/data/airports/pnq");
		driver.manage().window().maximize();

		List<String> list = new ArrayList<String>();

		list.add("Bengaluru");
		list.add("Delhi");
		list.add("Goa");
		list.add("Chandigarh");
		list.add("Hyderabad");
		list.add("Nagpur");
		list.add("Dubai");

		for (String city : list) {
			getFlightStatus(city);
		}
		driver.close();
	}

	static void getFlightStatus(String place) {
		List<WebElement> elements = driver.findElements(By.xpath(
				"//label[text()='Arrivals ']/parent::div/table/tbody/tr[contains(@class,'hidden-xs')]/td[3]/div/span"));
		
		boolean status = false;

		for (WebElement ele : elements) {

			if (ele.getText().contains(place)) {
				String t = ele.findElement(By.xpath(".//ancestor::td/following-sibling::td[@class='ng-binding']"))
						.getText();
				System.out.println(place + "              : " + t);
				status = true;
			}
		}

		if (!status) {
			System.out.println(place + "              : data not available ");
		}
	}
}