package com.roguelike.lootly.io;

import java.io.File;
import java.io.FilenameFilter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import com.roguelike.lootly.Lootly;
import com.roguelike.lootly.item.Item;

//Loads items into the itemList HashMap by parsing them out of .xml files located within /assets/config/. For formatting, see "exampleItemManifest.xml"
public class StrategyLoadItems implements LoadStrategy {

	@Override
	public void load() {
		
		File[] itemManifests = finder("config/"); //initialize the array to hold all the .xml files found within the config folder inside /assets
		
		//TODO: Remove debugging statement
		System.out.println("Item manifests found: " + itemManifests.length);
		
		try {
			for (File file: itemManifests) {
				File inputFile = new File("config/" + file.getName()); //appends config/ to the the name of the file to allow the program to correctly find it. Without config/, the program searches in /assets.
				
				//Set up the file for xml parsing
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		        Document doc = dBuilder.parse(inputFile);
		        doc.getDocumentElement().normalize();
		        //setup done
		        
		        NodeList nList = doc.getElementsByTagName("item"); //get a list of nodes with the name "item
		         
		        for (int temp = 0; temp < nList.getLength(); temp++) {
		        	Node nNode = nList.item(temp); //the current node being worked-on
		        	
		        	int format = Integer.parseInt(((Element) nNode.getParentNode()).getAttribute("format"));
		        	
		            System.out.println("\nCurrent Element: " + nNode.getNodeName());
		            System.out.println("\nItem format type: " + format);
		            
		            //TODO: Remove debugging statements
		            Element eElement = (Element) nNode;
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                eElement = (Element) nNode;
		                System.out.println("Item ID: " + eElement.getAttribute("id"));
		                System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
		                System.out.println("flavor text: " + eElement.getElementsByTagName("flavor").item(0).getTextContent());
		                System.out.println("sprite: " + eElement.getElementsByTagName("sprite").item(0).getTextContent());
		            } //end of debugging statements
		            
		            
		            if (format == 0) { //if the item being loaded has a generic base-item structure (as defined by its item_set in its .xml definition)
		            	Lootly.itemList.put(Integer.parseInt(eElement.getAttribute("id")), new Item(eElement.getElementsByTagName("name").item(0).getTextContent(), eElement.getElementsByTagName("flavor").item(0).getTextContent(), Integer.parseInt(eElement.getAttribute("id")), eElement.getElementsByTagName("sprite").item(0).getTextContent()));
		            }
		        }
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//From Stackoverflow. //TODO: Rewrite to be original code
	 public File[] finder( String dirName){
	        File dir = new File(dirName);
	        
	        System.out.println(dir.getAbsolutePath()); //TODO: remove debugging statement

	        return dir.listFiles(new FilenameFilter() { 
	                 public boolean accept(File dir, String filename)
	                      { return filename.endsWith(".xml"); }
	        });
	    }

}
