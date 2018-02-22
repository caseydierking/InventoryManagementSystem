/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem.Models;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author caseydierking
 */
public class Product {
    
    
    //Declare the variables in a Product
    private static AtomicInteger nextID = new AtomicInteger(0);

    private SimpleStringProperty name;
    private SimpleIntegerProperty productID;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty inStock;
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;
    private ArrayList<Part> associatedParts;

    
    
    //Constructor for initializing the product
  
    
    
    public Product(String name, double price, int inStock, int min, int max, ArrayList<Part> associatedParts) {
        this.name = new SimpleStringProperty(name);
        this.productID = new SimpleIntegerProperty(nextID.incrementAndGet());
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.associatedParts = new ArrayList<>(associatedParts);
    }
    
    public Product() {
        this.name = new SimpleStringProperty("");
        this.productID= new SimpleIntegerProperty(nextID.incrementAndGet());
        this.price = new SimpleDoubleProperty(0);
        this.inStock = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.associatedParts = new ArrayList<>();

    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public SimpleIntegerProperty getProductID() {
        return productID;
    }

    public void setProductID(SimpleIntegerProperty productID) {
        this.productID = productID;
    }

    public SimpleDoubleProperty getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = new SimpleDoubleProperty(price);
    }

    public SimpleIntegerProperty getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = new SimpleIntegerProperty(inStock);
    }

    public SimpleIntegerProperty getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = new SimpleIntegerProperty(min);
    }

    public SimpleIntegerProperty getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = new SimpleIntegerProperty(max);
    }
    
    
    public void addAssociatedPart(Part p){
        associatedParts.add(p);
        
    }
    
 
    
    public boolean removeAssociatedPart(Part p){
        return associatedParts.remove(p);
        
    }
    
    public Part lookupAssociatedPart(int p){
       return associatedParts.get(p);
        
    }

    public void addAssociatedParts(ArrayList<Part> associatedParts) {
        
        this.associatedParts = associatedParts;    
    }

    public ArrayList<Part> getAssociatedParts() {
        return associatedParts;
    }

   
    public static String isProductValid(String name, double price, int inStock, int min, int max, String errorMessage) {
        if (name == null) {
            errorMessage += ("Please check the Part Name Field.\n");
        }
        if (price < 1) {
            errorMessage += ("The price must be greater than 0.\n");
        }
        
        if (min > max) {
            errorMessage += ("Inventory Min must be less than Max.\n");
        }
        if (max < min) {
            errorMessage += ("Inventory Max must be greater than Min\n");
        }
        if (inStock < min || inStock > max) {
            errorMessage += ("Part inventory must be between MIN and MAX values.\n");
        }
        return errorMessage;
    }
    
    
    
}
