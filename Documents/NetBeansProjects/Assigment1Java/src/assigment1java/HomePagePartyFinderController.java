/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment1java;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fir3AtWill
 */
public class HomePagePartyFinderController implements Initializable {
    
    @FXML private TableView<PartyFinder> PartyTable;
    @FXML private TableColumn<PartyFinder, String> userColumn;
    @FXML private TableColumn<PartyFinder, String> rankColumn;
    @FXML private TableColumn<PartyFinder, String> partyTypeColumn;
    @FXML private TableColumn<PartyFinder, String> addressColumn;
    @FXML private TableColumn<PartyFinder, String> cityColumn;
    @FXML private TableColumn<PartyFinder, String> countryColumn;
    @FXML private TableColumn<PartyFinder, String> timeColumn;
    @FXML private TableColumn<PartyFinder, LocalDate> pobColumn;
    @FXML private TableColumn<PartyFinder, Double> entryColumn;
    @FXML private Label totalLabel;
    
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    BigDecimal test;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("user"));
        rankColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("rank"));
        partyTypeColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("partyType"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("address"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("city"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("country"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("time"));
        pobColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, LocalDate>("partyOfBirth"));
        entryColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, Double>("entry"));
       
        PartyTable.setItems(getParties());
        int i;
        double total = 0;
        for (i = 0; i < PartyTable.getItems().size(); i++) { 
             total += entryColumn.getCellData(i);
             
        }
        String moneyString = formatter.format(total);
        totalLabel.setText(String.valueOf(moneyString));
    }    

    private ObservableList<PartyFinder> getParties() {
        
         ObservableList<PartyFinder> parties = FXCollections.observableArrayList();
        
        //add employees to the list
        parties.add(new PartyFinder("Will", "OG", "Kegger", "170 cool street", "Barrie", "Canada", "10pm-12pm",LocalDate.of(2018, Month.MARCH, 15),10.00));
        parties.add(new PartyFinder("Emily", "Newbie", "Concert","60 party street", "Orillia", "Canada", "8pm-1am",LocalDate.of(2017, Month.OCTOBER, 17),9.50));
        parties.add(new PartyFinder("Bob", "Novice", "House Party","50 school street", "A plus please", "Better then 80 :D", "8pm-12pm",LocalDate.of(2019, Month.APRIL, 22),11.50));
        
        //return the list
        return parties;
    }
    public void testMath(){
        int i;
        double total = 0;
        for (i = 0; i < PartyTable.getItems().size(); i++) { 
             total += entryColumn.getCellData(i);
             totalLabel.setText(String.valueOf(total));
        }
        
    }
    public void CreatePartyButtonPushed(ActionEvent event) throws IOException{
   //load a new scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CreateParty.fxml"));
        Parent parent = loader.load();
        Scene newEmployeeScene = new Scene(parent);
        
        //access the controller of the newEmployeeScene and send over
        //the current list of employees
        CreatePartyController controller = loader.getController();
        controller.initialData(PartyTable.getItems());
        
        //Get the current "stage" (aka window) 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        //change the scene to the new scene
        stage.setTitle("Create new employee");
        stage.setScene(newEmployeeScene);
        stage.show();
}
        public void ViewPartyButtonPushed(ActionEvent event) throws IOException{
    //load a new scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewParty.fxml"));
        Parent parent = loader.load();
        Scene newPartyScene = new Scene(parent);
        
        //access the controller of the newEmployeeScene and send over
        //the current list of employees
        ViewPartyController controller = loader.getController();
        
        //gets the employee selected in the table
        PartyFinder party = PartyTable.getSelectionModel().getSelectedItem();
        
      
        
        //Get the current "stage" (aka window) 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        //change the scene to the new scene
        stage.setTitle("View Employee");
        stage.setScene(newPartyScene);
        stage.show();
}
    public void loadParty(ObservableList<PartyFinder> newList)
    {
        this.PartyTable.setItems(newList);
        int i;
        double total = 0;
        for (i = 0; i < PartyTable.getItems().size(); i++) { 
             total += entryColumn.getCellData(i);
             
        }
        String moneyString = formatter.format(total);
        totalLabel.setText(String.valueOf(moneyString));
    }
    
}
