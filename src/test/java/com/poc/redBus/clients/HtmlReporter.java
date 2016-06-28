package com.poc.redBus.clients;

import java.io.File;
/*import java.util.regex.Pattern;*/

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HtmlReporter{
	
	@BeforeClass(alwaysRun = true)
	public void reporter() {
		File folder = new File("./");
		File[] listOfFiles = folder.getAbsoluteFile().getParentFile().getParentFile().getParentFile().listFiles();
		System.out.println("-------------");
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
	}
	
	@Test
	public void nothing() {
		
	}
}