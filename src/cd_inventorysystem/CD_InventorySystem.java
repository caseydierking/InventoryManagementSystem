/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem;

import cd_inventorysystem.Models.Inhouse;
import cd_inventorysystem.Models.Inventory;
import cd_inventorysystem.Models.Part;
import cd_inventorysystem.Models.Product;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author caseydierking
 */
public class CD_InventorySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Part samplePart = new Inhouse(0,"testpart",1,50,1,1);
        Part anotherPart = new Inhouse(34,"testpart",1,50,1,1);
        Part thirdPart = new Inhouse(55,"testpart",1,50,1,1);

        Inventory.getAllParts().add(samplePart);
        Inventory.getAllParts().add(anotherPart);
        Inventory.getAllParts().add(thirdPart);

       
        
        
        Product sampleProduct = new Product("ProductA",50.0,1,1,1);
        Product anotherProduct = new Product("Productb",50.0,1,1,1);

        Inventory.getAllProducts().add(sampleProduct);
        Inventory.getAllProducts().add(anotherProduct);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        

    }
    
}
