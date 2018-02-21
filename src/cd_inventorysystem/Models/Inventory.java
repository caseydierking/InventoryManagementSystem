/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem.Models;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author caseydierking
 */
public class Inventory {
    
   private static ObservableList<Product> allProducts = FXCollections.observableArrayList();;
   private static ObservableList<Part> allParts = FXCollections.observableArrayList();

   
 
   
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    
    public void addPart(Part p){
        allParts.add(p);
    }
    
    public static void removePart(Part p){
        allParts.remove(p);
    }
    
     
  public void updatePart(int p, Part updatePart){
      
    allParts.set(p, updatePart);
   }
  
  //TODO
  
  public boolean deletePart(int p, Part deletePart){
       return false;
      
  }
  
  
  
  //Product Methods
  
  public static void addProduct(Product p){
      allProducts.add(p);
  }
  
  //Still need to work on this one.
  public boolean removeProduct(int p){
      return false;
  }
  
  public static void removeProduct(Product p){
      allProducts.remove(p);
  }
  
  
  public Product lookupProduct(int p){
       int productID = allProducts.indexOf(p);
      return allProducts.get(productID);
   } 
  
  public void updateProduct(int p,Part name){
      allParts.set(p, name);
      
  }

    
//    
//   public void addProduct(Product p){
//       
//       allProducts.add(p);
//    }
//   
   
   
  
    
    
}
