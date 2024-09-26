package testCases;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChildBrowserPopUp { 
	
public static void main(String[]args) throws InterruptedException { 
		
		//ChromeDriver driver = new ChromeDriver();
		
		WebDriver driver = new ChromeDriver(); 
		
		driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/"); 
		
		Thread.sleep(2000); 
		
        System.out.println("----Handling of child browser pop up----");
		
		driver.get("https://skpatro.github.io/demo/links/");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//input[@class='btn'])[3]")).click();
		
		Set<String> ids = driver.getWindowHandles();
		
		ArrayList<String> al = new ArrayList<>(ids);
		
		System.out.println(al.get(0));//id of main page---CDwindow-BC28251ABFBDB20A822855B9D458D59D
		System.out.println(al.get(1));//id of child page---CDwindow-60DBFFC0E3DB741A1E760F16FA3CD05E
		
		driver.switchTo().window(al.get(1));
		Thread.sleep(10000);
		
		driver.manage().window().maximize(); 
		
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//span[text()='Training'][1]")).click();
		
		Thread.sleep(5000);
		
		driver.switchTo().window(al.get(0));
		
		Thread.sleep(5000);
		
		//driver.close();
		
		driver.quit();
	
	}

}
