package com.glassdoor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JobListTextVersion {

public static void main(String[] args) throws IOException {
		

//		1)Create arraylist of keywords.
//		add 20 different keyworks
//		list.add("java");
		List<String> jobList= new ArrayList<String>(); 

		// 
		File f2= new File("/home/physics/Documents/GuvenSelenium/SeleniumMavenP1/JobListTextVs");
		FileWriter fw2= new FileWriter(f2); 
		BufferedWriter writer=new BufferedWriter(fw2); 
		//String newLine= System.getProperty("\n");
		
		File f3= new File("/home/physics/Documents/GuvenSelenium/SeleniumMavenP1/JobListText");
		FileReader fr3=new FileReader(f3); 
		BufferedReader reader= new BufferedReader(fr3); 
		String st=""; 
		while((st= reader.readLine())!=null) {
			
			WebDriverManager.chromedriver().setup(); 
			WebDriver driver= new ChromeDriver(); 
			
			driver.manage().window().fullscreen();
			//set universal wait time
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// DIFFERENT THAN TRHEAD
			
			driver.get("http://glassdoor.com");
			
			String actual= driver.getTitle(); 
			String expected="Glassdoor Job Search | Find the job that fits your life"; 
			if (actual.equals(expected)) {
				System.out.println("PASS");
			}else {
				System.out.println("FAIL");
			}	
			
			
		
		driver.findElement(By.id("KeywordSearch")).clear();
		String jobName= st;  
		driver.findElement(By.id("KeywordSearch")).sendKeys(jobName);
		
		
		String location="Austin, TX"; 
		driver.findElement(By.id("LocationSearch")).clear();
		driver.findElement(By.id("LocationSearch")).sendKeys(location);
		
		driver.findElement(By.id("HeroSearchButton")).click();
		String actual1=driver.findElement(By.id("jobTitle")).getText();
		String expected1=jobName+" in "+location; 
		if (actual.equals(expected)) {
			System.out.println(" TASK2 PASS");
		}else {
			System.out.println("TASK1 FAIL");
		}
		String numofJobs=driver.findElement(By.className("jobsCount")).getText(); 
		System.out.println(numofJobs);
		System.out.println("There are  :"+ numofJobs+" " +jobName+" position found in "+ location + "");
		
		writer.write("There are  :"+ numofJobs+" " +jobName+" position found in "+ location + "" + "\n");
		
		
		driver.close();
		
//		String x=numofJobs.replaceAll(",", ""); 
//		int a=Integer.parseInt(x); 
//		System.out.println(a);
//		
		}
		writer.close();
	}

}
