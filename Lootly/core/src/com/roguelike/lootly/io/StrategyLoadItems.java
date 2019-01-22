package com.roguelike.lootly.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import com.roguelike.lootly.Lootly;
import com.roguelike.lootly.item.Item;

public class StrategyLoadItems implements LoadStrategy {

	@Override
	public void load() {
		
		File[] itemManifests = finder("config/");
		
		try {
	         File inputFile = new File("input.txt");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         
	         NodeList nList = doc.getElementsByTagName("student");
	         
		} catch (Exception e) {
			
		}
		
		
	}
	
	 public File[] finder( String dirName){
	        File dir = new File(dirName);

	        return dir.listFiles(new FilenameFilter() { 
	                 public boolean accept(File dir, String filename)
	                      { return filename.endsWith(".txt"); }
	        } );

	    }

}
