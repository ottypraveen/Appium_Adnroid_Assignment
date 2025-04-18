package Appium.Appium_Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class e_CommerceE2E extends BaseTest {

	@Test
	public void AppiumTest() throws MalformedURLException, InterruptedException {

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Praveen");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(2000);
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);
		List<WebElement> element = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		System.out.println(element.size());

		double sum = 0;
		for (int i = 0; i < element.size(); i++) {
			String amountinString = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i)
					.getText();
			double amount = Double.parseDouble(amountinString.substring(1));
			sum = sum + amount;

		}

		String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double actualsum = Double.parseDouble(displaySum.substring(1));
		Assert.assertEquals(actualsum, sum);
		WebElement elementTerms = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longPressaction(elementTerms);
		driver.findElement(By.xpath("//android.widget.Button[@text='CLOSE']")).click();
		driver.findElement(By.className("android.widget.CheckBox")).click();

		WebElement elementProceedButton = driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed"));
		Point location = elementProceedButton.getLocation();
		Dimension size = elementProceedButton.getSize();
		Point centerOfElement = getCenterofElement(location, size);
		
		tapEvent(centerOfElement);

		Thread.sleep(10000);

		Set<String> contextHandles = driver.getContextHandles();

		for (String context : contextHandles) {
			System.out.println(context);
		}
		/*
		 * Thread.sleep(4000); driver.context("WEBVIEW_com.androidsample.generalstore");
		 * driver.findElement(By.name("q")).sendKeys("MVN Repo");
		 * driver.findElement(By.name("q")).sendKeys(Keys.ENTER); driver.pressKey(new
		 * KeyEvent(AndroidKey.BACK)); driver.context("NATIVE_APP");
		 */

	}

}
