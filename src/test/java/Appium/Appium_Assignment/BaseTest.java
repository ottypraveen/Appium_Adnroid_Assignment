package Appium.Appium_Assignment;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	AndroidDriver driver;
	UiAutomator2Options option;

	// public AppiumDriverLocalService service;

	@BeforeTest
	public void configureAppium() throws MalformedURLException, InterruptedException {
		
		/*
		 * service=new AppiumServiceBuilder(). withAppiumJS(new File(
		 * "C:\\Users\\ottyp\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"
		 * )). withIPAddress("127.0.0.1"). usingPort(4723).build();
		 */
		
		option = new UiAutomator2Options();
		option.setDeviceName("PraveenEmulator2");
		option.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
		option.setApp(
				System.getProperty("user.dir")+"\\src\\test\\java\\Resources\\General-Store.apk");
		//option.setChromedriverExecutable("C:\\Users\\ottyp\\eclipse-workspace\\Appium_Assignment\\chromedriver.exe");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), option);
		Thread.sleep(10000);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		//service.stop();
	}

	public void longPressaction(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));

	}

	public Point getCenterofElement(Point location, Dimension size) {
		return new Point(location.getX() + size.getWidth() / 2, location.getY() + size.getHeight() / 2);
	}

	public void tapEvent(Point centerOfElement) {
		PointerInput Finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Finger1");
		Sequence seq1 = new Sequence(Finger1, 1)
				.addAction(Finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
				.addAction(Finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(Finger1, Duration.ofMillis(200))).addAction(Finger1
						.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), centerOfElement))
				.addAction(Finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Collections.singletonList(seq1));
	}

}
