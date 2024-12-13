/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.PartyFinder;
import models.PasswordGenerator;

/**
 * FXML Controller class
 *
 * @author Fir3AtWill
 */
public class LoginScreenController implements Initializable {
     @FXML private TextField userLoginTextField;
     @FXML private TextField passwordLoginTextField;
     @FXML private Label errorMsg;
     public static int currentUserId;
     public int userCount;
     
     
      public void loginButtonPushed(ActionEvent event) throws IOException, NoSuchAlgorithmException
    {
        //query the database with the volunteerID provided, get the salt
        //and encrypted password stored in the database
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        int userId = Integer.parseInt(userLoginTextField.getText());
        userCount = userId;
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PartyFinder?useSSL=false", "root", "");
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM PartyLogin WHERE userId = ?";
            
            //3.  prepare the statement
            ps = conn.prepareStatement(sql);
            
            //4.  bind the volunteerID to the ?
            ps.setInt(1, userId);
            
            //5. execute the query
            resultSet = ps.executeQuery();
            
            //6.  extract the password and salt from the resultSet
            String dbPassword=null;
            byte[] salt = null;
            boolean admin = false;
            PartyFinder partyFinder = null;
            
            while (resultSet.next())
            {
                dbPassword = resultSet.getString("userPassword");
                
                Blob blob = resultSet.getBlob("salt");
                
                //convert into a byte array
                int blobLength = (int) blob.length();
                salt = blob.getBytes(1, blobLength);
                
                admin = resultSet.getBoolean("admin");
                
                partyFinder = new PartyFinder(resultSet.getString("userPassword"),
                                              resultSet.getInt("userId"),
                                              resultSet.getBoolean("admin"));
            }
            
            //convert the password given by the user into an encryted password
            //using the salt from the database
            String userPW = PasswordGenerator.getSHA512Password(passwordLoginTextField.getText(), salt);
            
            SceneChanger sc = new SceneChanger();
            
            if (userPW.equals(dbPassword))
                SceneChanger.setLoggedInUser(partyFinder);
            
            //if the passwords match - change to the VolunteerTableView
            if (userPW.equals(dbPassword) && admin)
                sc.changeScenes(event, "ViewAllPartiesAdmin.fxml", "Its Party  Time!");
            if(userPW.equals(dbPassword) && admin == false){
                currentUserId = userId;
                sc.changeScenes(event, "ViewAllParties.fxml", "Create user");
            }
            else
                //if the do not match, update the error message
                errorMsg.setText("The userId and password do not match");
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        
        
    }
      
      public void registerButtonPushed(ActionEvent event) throws IOException, SQLException{
           SceneChanger sc = new SceneChanger();
           RegisterUserController reg = new RegisterUserController();
           reg.loadDbData();
           sc.changeScenes(event, "RegisterUser.fxml", "Create user");
          
      }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
