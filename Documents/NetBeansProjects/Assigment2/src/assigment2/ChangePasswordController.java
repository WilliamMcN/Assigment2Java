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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ChangePasswordController implements Initializable {

    @FXML private Label userIdLabel;
    @FXML private Label errLabel;
    @FXML private TextField passwordTextField;
    @FXML private TextField passwordConfirmTextField;
    private byte[] salt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.print(LoginScreenController.currentUserId);
        userIdLabel.setText(String.valueOf(LoginScreenController.currentUserId));
    }
public void CreateUserButtonPushed(ActionEvent event) throws SQLException, NoSuchAlgorithmException, IOException{
    String confirmPassword = passwordConfirmTextField.getText();
    String userpassword = passwordTextField.getText();
    salt = PasswordGenerator.getSalt();
    
    if(userpassword.length() < 5)
        errLabel.setText("Password must be longer then 5 characters");
    
    if(userpassword.equals(confirmPassword)){
        userpassword = PasswordGenerator.getSHA512Password(userpassword, salt);
        {
            Connection conn = null;
            PreparedStatement preparedStatement = null;

            try
            {
                //1. Connect to the database
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/partyfinder", "root", "");

                //2. Create a String that holds the query with ? as user inputs
                String sql = "Update partylogin Set userPassword = ?, admin = ?, salt = ?"
                        + "WHERE userId = ?";

                //3. prepare the query
                preparedStatement = conn.prepareStatement(sql);


                //5. Bind the values to the parameters
                preparedStatement.setString(1, userpassword);
                preparedStatement.setBoolean(2, false);
                preparedStatement.setBlob(3, new javax.sql.rowset.serial.SerialBlob(salt));
                preparedStatement.setInt(4, LoginScreenController.currentUserId);

                preparedStatement.executeUpdate();
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }
            finally
            {
                if (preparedStatement != null)
                    preparedStatement.close();

                if (conn != null)
                    conn.close();
            }
            SceneChanger sc = new SceneChanger();
            sc.changeScenes(event, "LoginScreen.fxml", "Login");
        }
    }
    else 
        errLabel.setText("Passwords dont match");
}

public void LoginButtonPushed(ActionEvent event) throws IOException{
    SceneChanger sc = new SceneChanger();
    sc.changeScenes(event, "LoginScreen.fxml", "Login");
}
}
