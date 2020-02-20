package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import models.BaseProduct;
import models.MenuItem;
import models.Restaurant;

public class RestaurantSerializator {
	
	
	////MenuItem object = new BaseProduct("coca cola", 7.0); 
     static String filename = "file.ser";
       
   public static void serialization(Restaurant rest) {
     try
     {    
      
         FileOutputStream file = new FileOutputStream(filename); 
         ObjectOutputStream out = new ObjectOutputStream(file);  
         out.writeObject(rest); 
         out.close(); 
         file.close(); 
           
         System.out.println("Object has been serialized"); 

     } 
       
     catch(IOException ex) 
     { 
         System.out.println("IOException is caught"); 
     } 

   }
   
    public static Restaurant  deserialization() { 
    	 Restaurant restDeserialize = null; 
    	 try { 
    		  
             // Reading the object from a file 
             FileInputStream file = new FileInputStream 
                                          (filename); 
             ObjectInputStream in = new ObjectInputStream 
                                          (file); 
             restDeserialize = (Restaurant)in.readObject(); 
   
             in.close(); 
             file.close(); 
             return restDeserialize;
         }  catch (IOException ex) { 
             System.out.println("IOException is caught"); 
             restDeserialize = new Restaurant();
             serialization(restDeserialize);
             return restDeserialize;
         } 
   
         catch (ClassNotFoundException ex) { 
             System.out.println("ClassNotFoundException" + " is caught"); 
             restDeserialize = new Restaurant();
            return restDeserialize;
         } 
    }
}
