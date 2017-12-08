/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f17comp1011test2section1;

import java.io.File;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class EditPhoneViewController implements Initializable {

    @FXML    private TextField manufacutureTextField;
    @FXML    private TextField modelTextField;
    @FXML    private TextField memoryTextField;
    @FXML    private TextField colourTextField;
    @FXML    private TextField screenSizeTextField;
    @FXML    private Spinner<Integer> phoneIDSpinner;
    
    private int PhoneCount;
    private Phone phone;

    public void loadPhoneInfo() throws SQLException
    {
         {
        //query the database with the volunteerID provided, get the salt
        //and encrypted password stored in the database
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        int phoneId = (phoneIDSpinner.getValue());
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comp1011Test2", "root", "");
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM phones WHERE phoneID = ?";
            
            //3.  prepare the statement
            ps = conn.prepareStatement(sql);
            
            //4.  bind the volunteerID to the ?
            ps.setInt(1, phoneId);
            
            //5. execute the query
            resultSet = ps.executeQuery();
        
                 Phone newPhone = new Phone(resultSet.getInt("phoneID"), 
                                                 resultSet.getString("manufacturer"),
                                                 resultSet.getString("model"),
                                                 resultSet.getInt("memory"),
                                                 resultSet.getString("colour"),
                                                 resultSet.getDouble("screenSize"),
                                                 resultSet.getDouble("cameraRes"));
                manufacutureTextField.setText(newPhone.getManufacturer());
                modelTextField.setText(newPhone.getModel());
                memoryTextField.setText(newPhone.getMemory());
                colourTextField.setText(newPhone.getColour());
                screenSizeTextField.setText(newPhone.getScreenSize());
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
      }
    }
     public ObservableList<Phone> loadDbData() throws SQLException
    {
        //define an observable list
        ObservableList<Phone> phone = FXCollections.observableArrayList();
        
        //Read from the DB andd add employees to the list
        //connect to the DB
        Connection conn = null;  //connects to DB
        Statement statement = null; //is the SQL statement we want to run
        ResultSet resultSet = null; //is the response from the DB
        
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comp1011Test2?useSSL=false", 
                                                "root", "");
            
            //2.  create a statement
            statement = conn.createStatement();
            
            //3.  create the sql query
            resultSet = statement.executeQuery("SELECT * FROM phones");
            
            //4.  create phone objects from each record
            while (resultSet.next())
            {
                Phone newPhone = new Phone(resultSet.getInt("phoneID"), 
                                                 resultSet.getString("manufacturer"),
                                                 resultSet.getString("model"),
                                                 resultSet.getInt("memory"),
                                                 resultSet.getString("colour"),
                                                 resultSet.getDouble("screenSize"),
                                                 resultSet.getDouble("cameraRes"));
                phone.add(newPhone);
                PhoneCount++;
            }
        } 
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return phone;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manufacutureTextField.setDisable(true);
        modelTextField.setDisable(true);
        memoryTextField.setDisable(true);
        colourTextField.setDisable(true);
        screenSizeTextField.setDisable(true);
        try{
            this.phone = (Phone) (loadDbData());
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        SpinnerValueFactory<Integer> phoneIDValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,PhoneCount);
        phoneIDSpinner.setValueFactory(phoneIDValueFactory);
        phoneIDSpinner.setEditable(true);
    }    
    
}
