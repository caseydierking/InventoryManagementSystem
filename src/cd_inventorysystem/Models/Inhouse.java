/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem.Models;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author caseydierking
 */
public class Inhouse extends Part {

    private SimpleIntegerProperty machineId;

    public Inhouse(int machineId, String name, double price, int inStock, int min, int max) {
        super(name, price, inStock, min, max);
        this.machineId = new SimpleIntegerProperty(machineId);
    }

    /**
     * @return the machineId
     */
    public SimpleIntegerProperty getMachineId() {
        return machineId;
    }

    /**
     * @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = new SimpleIntegerProperty(machineId);
    }

}
