/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.PartyFinder;

/**
 * FXML Controller class
 *
 * @author Fir3AtWill
 */
public class EditPartyController implements Initializable, ControllerClass {
    @FXML private TextField userNameTextField;
    @FXML private ChoiceBox partyTypeChoiceBox;
    @FXML private TextField streetTextField;
    @FXML private ChoiceBox cityChoiceBox;
    @FXML private ChoiceBox countryChoiceBox;
    @FXML private DatePicker pob;
    @FXML private ChoiceBox toTimeChoiceBox;
    @FXML private ChoiceBox fromTimeChoiceBox;
    @FXML private Spinner entrySpinner;
    @FXML private TextArea messageTextArea;
    @FXML private Label errorMsg;
    
    private PartyFinder party;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
          //Creating the the rows for each dropdown 
          this.entrySpinner.setValueFactory(valueFactory);
        ChoiceBox ptcb = new ChoiceBox(FXCollections.observableArrayList(
    "Party", "Concert", "Kegger", "House Party", "Club", "Bar")
        );
        ChoiceBox citycb = new ChoiceBox(FXCollections.observableArrayList(
    "Barrie", "Orillia", "Missuaga", "Brampton", "Guelph", "New Market")
        );
        ChoiceBox countrycb = new ChoiceBox(FXCollections.observableArrayList(
    "Canada", "Usa", "England", "India", "Russia", "Japan")
        );
        ChoiceBox totcb = new ChoiceBox(FXCollections.observableArrayList(
    "12pm", "1am", "2am", "3am", "4am", "5am", "6am", 
                "7am", "8am", "9am", "10am", "11am", "12am", 
                "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", 
                "7pm", "8pm", "9pm", "10pm", "11pm")
        );
        ChoiceBox fromtcb = new ChoiceBox(FXCollections.observableArrayList(
        "12pm", "1am", "2am", "3am", "4am", "5am", "6am", 
                "7am", "8am", "9am", "10am", "11am", "12am", 
                "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", 
                "7pm", "8pm", "9pm", "10pm", "11pm")
        );
        //Adding rows to the right dropdowns 
        partyTypeChoiceBox.setItems(ptcb.getItems());
        cityChoiceBox.setItems(citycb.getItems());
        countryChoiceBox.setItems(countrycb.getItems());
        toTimeChoiceBox.setItems(totcb.getItems());
        fromTimeChoiceBox.setItems(fromtcb.getItems());
        
    }  

    @Override
    public void preloadData(PartyFinder partyFinder) {
        party = partyFinder;
        
        userNameTextField.setText(party.getUser());
        partyTypeChoiceBox.setValue(party.getPartyType());
        streetTextField.setText(party.getAddress());
        cityChoiceBox.setValue(party.getCity());
        countryChoiceBox.setValue(party.getCountry());
        pob.setValue(party.getPob());
        messageTextArea.setText(party.getMessage());
    }
    
}
