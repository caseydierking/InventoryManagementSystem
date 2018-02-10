/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem.Models;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author caseydierking
 */
public class Outsourced extends Part {
    
    private SimpleStringProperty companyName;

    public Outsourced(SimpleStringProperty companyName, String name, double price, int inStock, int min, int max) {
        super(name, price, inStock, min, max);
        this.companyName = companyName;
    }

    /**
     * @return the companyName
     */
    public SimpleStringProperty getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(SimpleStringProperty companyName) {
        this.companyName = companyName;
    }
    
    
    
}
