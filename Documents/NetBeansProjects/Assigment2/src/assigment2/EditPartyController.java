/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment2;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    @FXML private TextField TimeTextField;
    @FXML private TextField userNameTextField;
    @FXML private ChoiceBox partyTypeChoiceBox;
    @FXML private TextField streetTextField;
    @FXML private ChoiceBox cityChoiceBox;
    @FXML private ChoiceBox countryChoiceBox;
    @FXML private DatePicker pob;
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
        //Adding rows to the right dropdowns 
        partyTypeChoiceBox.setItems(ptcb.getItems());
        cityChoiceBox.setItems(citycb.getItems());
        countryChoiceBox.setItems(countrycb.getItems());
    }
    public void createButtonPushed(ActionEvent event) throws IOException, NoSuchAlgorithmException, SQLException
    {
        if(userNameTextField.getText().isEmpty())
            errorMsg.setText("UserName is empty");
        else if(streetTextField.getText().isEmpty())
            errorMsg.setText("Address is empty");
        else if(messageTextArea.getText().isEmpty())
            errorMsg.setText("Message is empty");
        else if(pob.getValue().isBefore(LocalDate.now()))
            errorMsg.setText("Party can not be in the past");
        
        
        else{
                   Connection conn = null;
                   PreparedStatement ps = null;
            try{
                //1.  connect to the DB
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PartyFinder?useSSL=false", "root", "");

                //2.  create a query string with ? used instead of the values given by the user
                String sql = "Update party set userName = ? ,rank = ? ,partyType = ? ,address = ?,city = ?,country = ?,pTime = ?,pob = ?,entry = ?,userId = ?,message = ? where PartyId = ?";

                //3.  prepare the statement
                ps = conn.prepareStatement(sql);
                 Date db = Date.valueOf(pob.getValue());
                //4.  bind the volunteerID to the ?
                ps.setString(1,userNameTextField.getText());
                ps.setString(2,"Newbie");
                ps.setString(3,partyTypeChoiceBox.getSelectionModel().getSelectedItem().toString());
                ps.setString(4,streetTextField.getText());
                ps.setString(5,cityChoiceBox.getSelectionModel().getSelectedItem().toString());
                ps.setString(6,countryChoiceBox.getSelectionModel().getSelectedItem().toString());
                ps.setString(7,TimeTextField.getText());
                ps.setDate(8, db);
                ps.setInt(9, (int) entrySpinner.getValue());
                ps.setInt(10,LoginScreenController.currentUserId);
                ps.setString(11,messageTextArea.getText());
                ps.setInt(12, party.getUserId());

                //5. execute the query
                ps.executeUpdate();


            }
            catch (SQLException e)
                {
                    System.err.println(e.getMessage());
                }
                finally
                {
                    if (ps != null)
                        ps.close();

                    if (conn != null)
                        conn.close();
                }
                SceneChanger sc = new SceneChanger();
                sc.changeScenes(event, "ViewAllParties.fxml", "View All Parties");

            }
    }
      public void BackButtonPushed(ActionEvent event) throws IOException{
      SceneChanger sc = new SceneChanger();
      sc.changeScenes(event, "ViewAllParties.fxml", "View All Parties");
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
        TimeTextField.setText(party.getTime());
    }
    
}
