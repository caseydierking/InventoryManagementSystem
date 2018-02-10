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
    private ObservableList<Part> associatedParts;

    
    
    //Constructor for initializing the product
  
    
    
    public Product(String name, double price, int inStock, int min, int max) {
        this.name = new SimpleStringProperty(name);
        this.productID = new SimpleIntegerProperty(nextID.incrementAndGet());
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
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

    public void setPrice(SimpleDoubleProperty price) {
        this.price = price;
    }

    public SimpleIntegerProperty getInStock() {
        return inStock;
    }

    public void setInStock(SimpleIntegerProperty inStock) {
        this.inStock = inStock;
    }

    public SimpleIntegerProperty getMin() {
        return min;
    }

    public void setMin(SimpleIntegerProperty min) {
        this.min = min;
    }

    public SimpleIntegerProperty getMax() {
        return max;
    }

    public void setMax(SimpleIntegerProperty max) {
        this.max = max;
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
    
    
    
    
    
    
}
