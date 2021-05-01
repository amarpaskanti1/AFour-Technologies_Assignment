package Assignment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class first {
	static WebDriver driver;

	@Test
	public void TC01() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();

		driver.findElement(By.name("q")).sendKeys("selenium hq" + Keys.ENTER);

		List<WebElement> elements = driver
				.findElements(By.xpath("//div[@class='hlcw0c'] //h3[contains(@class,'LC20lb')]"));

		if (!getDesiredText(elements, "Wikipedia", 10))
			System.out.println("The requested \"Wikipedia\" details are not found");

		driver.close();
	}

	public static boolean getDesiredText(List<WebElement> elements, String text, int i) {
		while (i > 1) {
			int index = 1;
			for (WebElement ele : elements) {

				if (ele.getText().contains(text)) {

					String s = driver.findElement(By.xpath(
							"(//div[@class='hlcw0c'] //h3[contains(@class,'LC20lb')]//ancestor::div[@class='yuRUbf']/following-sibling::div/span/span)["
									+ index + "]"))
							.getText();

					System.out.println(s);
					return true;
				}
				index++;
			}

			driver.findElement(By.linkText("Next")).click();
			elements = driver.findElements(By.xpath("//div[@class='hlcw0c'] //h3[contains(@class,'LC20lb')]"));

			i--;
		}
		return false;
	}
}