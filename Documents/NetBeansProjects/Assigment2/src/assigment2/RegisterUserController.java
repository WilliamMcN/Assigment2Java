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
public class RegisterUserController implements Initializable {
    private int UserCount;
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
        try {
            loadDbData();
        } catch (SQLException ex) {
            errLabel.setText(ex.toString());
        }
        userIdLabel.setText(Integer.toString(UserCount));
    }
 public ObservableList<PartyFinder> loadDbData() throws SQLException
    {
         UserCount = 0;
        //define an observable list
        ObservableList<PartyFinder> partyFinder = FXCollections.observableArrayList();
        
        //Read from the DB andd add employees to the list
        //connect to the DB
        Connection conn = null;  //connects to DB
        Statement statement = null; //is the SQL statement we want to run
        ResultSet resultSet = null; //is the response from the DB
        
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PartyFinder?useSSL=false", 
                                                "root", "");
            
            //2.  create a statement
            statement = conn.createStatement();
            
            //3.  create the sql query
            resultSet = statement.executeQuery("SELECT * FROM partyLogin");
            
            //4.  create emplpoyee objects from each record
            while (resultSet.next())
            {
                PartyFinder newParty = new PartyFinder(resultSet.getString("userPassword"), 
                                                 resultSet.getInt("userId"),
                                                 resultSet.getBoolean("admin"));
                partyFinder.add(newParty);
                UserCount++;
            }
            UserCount ++;
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
        return partyFinder;
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
                String sql = "INSERT INTO partylogin (userId, userPassword, admin, salt)"
                        + "VALUES (?,?,?,?)";

                //3. prepare the query
                preparedStatement = conn.prepareStatement(sql);


                //5. Bind the values to the parameters
                preparedStatement.setInt(1, UserCount);
                preparedStatement.setString(2, userpassword);
                preparedStatement.setBoolean(3, false);
                preparedStatement.setBlob(4, new javax.sql.rowset.serial.SerialBlob(salt));

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
