/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.PartyFinder;

/**
 * FXML Controller class
 *
 * @author Fir3AtWill
 */
public class ViewAllUsersController implements Initializable {
    @FXML private TableView<PartyFinder> PartyTable;
    @FXML private TableColumn<PartyFinder, Integer> userIdColumn;
    @FXML private TableColumn<PartyFinder, Boolean> adminColumn;
    @FXML private Label errLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, Integer>("userId"));
        adminColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, Boolean>("entry"));
       
         //load dummy data
        try{
            loadDbData();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        // TODO
    }
  public void loadDbData() throws SQLException
    {
        //define an observable list
        ObservableList<PartyFinder> partys = FXCollections.observableArrayList();
        
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
            resultSet = statement.executeQuery("SELECT * FROM PartyLogin");
            
            //4.  create emplpoyee objects from each record
            while (resultSet.next())
            {
                PartyFinder newParty = new PartyFinder(resultSet.getInt("userId"), 
                                                        resultSet.getBoolean("admin"));
                partys.add(newParty);
            }
            PartyTable.getItems().addAll(partys);
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
        
    }    
  public void BackButtonPushed(ActionEvent event) throws IOException{
      SceneChanger sc = new SceneChanger();
      sc.changeScenes(event, "ViewAllPartiesAdmin.fxml", "View All Parties");
  }
  public void ChangeAdminButtonPushed() throws SQLException{
      if(adminColumn.getCellData(PartyTable.getSelectionModel().getSelectedIndex()) == true){
          System.out.print("True");
          Connection conn = null;
            PreparedStatement preparedStatement = null;

            try
            {
                //1. Connect to the database
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/partyfinder", "root", "");

                //2. Create a String that holds the query with ? as user inputs
                String sql = "Update partyLogin Set admin = ? Where userId = ?";

                //3. prepare the query
                preparedStatement = conn.prepareStatement(sql);


                //5. Bind the values to the parameters
                preparedStatement.setBoolean(1, false);
                preparedStatement.setInt(2, userIdColumn.getCellData(PartyTable.getSelectionModel().getSelectedIndex()));

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
      }
      if(adminColumn.getCellData(PartyTable.getSelectionModel().getSelectedIndex()) == false)
      {
          System.out.print("False");
          Connection conn = null;
            PreparedStatement preparedStatement = null;

            try
            {
                //1. Connect to the database
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/partyfinder", "root", "");

                //2. Create a String that holds the query with ? as user inputs
                String sql = "Update partyLogin Set admin = ? Where userId = ?";

                //3. prepare the query
                preparedStatement = conn.prepareStatement(sql);


                //5. Bind the values to the parameters
                preparedStatement.setBoolean(1, true);
                preparedStatement.setInt(2, userIdColumn.getCellData(PartyTable.getSelectionModel().getSelectedIndex()));

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
      }
      else 
      {
             errLabel.setText("Nothing is selected");
      }
  }
}

