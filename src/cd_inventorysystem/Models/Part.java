
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem.Models;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author caseydierking
 */
public abstract class Part {
    
    private static int count = 0;

    
    // Setup the Variables
    SimpleStringProperty name;
    public static AtomicInteger nextID = new AtomicInteger(0);
    SimpleIntegerProperty partID;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty inStock;
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;

    
    //Initialize the variables
    public Part(String name, double price, int inStock, int min, int max) {
        this.name = new SimpleStringProperty(name);
        this.partID = new SimpleIntegerProperty(nextID.incrementAndGet());
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        
        
        
    }

    /**
     * @return the name
     */
    public SimpleStringProperty getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    /**
     * @return the partID
     */
    public SimpleIntegerProperty getPartID() {
        return partID;
    }
    
   

    /**
     * @param partID the partID to set
     */
    public void setPartID(SimpleIntegerProperty partID) {
        this.partID = partID;
    }

    /**
     * @return the price
     */
    public SimpleDoubleProperty getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = new SimpleDoubleProperty(price);
    }

    /**
     * @return the inStock
     */
    public SimpleIntegerProperty getInStock() {
        return inStock;
    }

    /**
     * @param inStock the inStock to set
     */
    public void setInStock(int inStock) {
        this.inStock = new SimpleIntegerProperty(inStock);
    }

    /**
     * @return the min
     */
    public SimpleIntegerProperty getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = new SimpleIntegerProperty(min);
    }

    /**
     * @return the max
     */
    public SimpleIntegerProperty getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = new SimpleIntegerProperty(max);
    }
    
    
    
            
    
}
