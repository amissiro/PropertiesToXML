/*

 Author: Azret Missirov
 Date  : 04/16/2013
 Program Name: Properties2XML.java
 Objective: This program gets Java properties and creates XML from those 
            properties.First program separates properties that start with 
            "java". Writes it into map, and splits key that contains "." 
            and assignes its' values. 
*/

import java.io.*;
import java.util.*;
import java.util.regex.*;

//**************************Properties2XML()*****************************

public class Properties2XML
{

//**************************main()*********************************

 public static void main(String[] args)
 {
  //using java regex to separate properties that start with word java 
  Pattern p = Pattern.compile("^java");
  
  //getting system properties
  Properties systemProperties = System.getProperties();
  
  //loading properties into map
  HashMap hashSystemProperties = new HashMap(systemProperties);
  Set keySet = hashSystemProperties.keySet();
  Iterator iterator = keySet.iterator();

  //creating two arrays which store key and value from map	  
  ArrayList<String> key = new ArrayList<String>();
  ArrayList<String> value = new ArrayList<String>();
  
  while (iterator.hasNext()) 
  {
   //extracting key and value from map
   String propertyKey = (String) iterator.next();
   String propertyValue = systemProperties.getProperty(propertyKey);
  
   //starting separation properties that start with java 			
   Matcher m = p.matcher(propertyKey);
   
   if (m.find())
   {
    //writing already separated key and value into array
    key.add(propertyKey); 
    value.add(propertyValue); 
   }
  }
   
   //From this point display properties as XML file
   System.out.println("<?xml version=\"1.0\"?>");
   System.out.println("<java>");      
   
   for (int i=0; i<key.size(); i++)
   {
    //Spliting keys that contains "."    			
    String[] xml= key.get(i).split("\\.");  
    
    //properties that contain one "."
    if (xml.length==2)
    {
     System.out.println("  <"+xml[1]+">"+
                            value.get(i)+
                         "</"+xml[1]+">");
    } 
    //properties that contain two "."
    else if (xml.length==3)
    {
     System.out.println("  <"+xml[1]+">"+
                        "\n    <"+xml[2]+
                        ">"+value.get(i)+
	                 "</"+xml[2]+">"+
                      "\n  </"+xml[1]+">");
    } 
    //properties that contain three "."
    else 
    {
     System.out.println("  <"+    xml[1]+">"+
                        "\n    <"+xml[2]+">"+
	                         "\n      <"+
                                      xml[3]+
                            ">"+value.get(i)+
			     "</"+xml[3]+">"+
                       "\n    </"+xml[2]+">"+
      		          "\n  </"+xml[1]+">");
    }   		  
   }
    System.out.println("</java>");
  }		
}
