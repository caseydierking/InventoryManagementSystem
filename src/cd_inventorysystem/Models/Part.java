/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author caseydierking
 */
class Part {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty partID;
    private final SimpleDoubleProperty price;
    private final SimpleIntegerProperty inStock;
    private final SimpleIntegerProperty min;
    private final SimpleIntegerProperty max;

    public Part(String name, int partID, double price, int inStock, int min, int max) {
        this.name = new SimpleStringProperty(name);
        this.partID = new SimpleIntegerProperty(partID);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }
    
    
    
            
    
}
