/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment2;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.time.LocalDate;
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
import models.PartyFinder;

/**
 * FXML Controller class
 *
 * @author Fir3AtWill
 */
public class ViewAllPartiesController implements Initializable {
    @FXML private TableView<PartyFinder> PartyTable;
    @FXML private TableColumn<PartyFinder, Integer> partyIdColumn;
    @FXML private TableColumn<PartyFinder, Integer> userIdColumn;
    @FXML private TableColumn<PartyFinder, String> rankColumn;
    @FXML private TableColumn<PartyFinder, String> partyTypeColumn;
    @FXML private TableColumn<PartyFinder, String> streetColumn;
    @FXML private TableColumn<PartyFinder, String> cityColumn;
    @FXML private TableColumn<PartyFinder, String> countryColumn;
    @FXML private TableColumn<PartyFinder, String> timeColumn;
    @FXML private TableColumn<PartyFinder, LocalDate> pobColumn;
    @FXML private TableColumn<PartyFinder, Double> entryColumn;
    @FXML private Label totalLabel;
    @FXML private Label errmsgLabel;
    @FXML private Label UserIdLabel;
    LoginScreenController log = new LoginScreenController();
    public int userIdLog;
    private int currentUser;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    BigDecimal test;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
  public void initialize(URL url, ResourceBundle rb) {
        rankColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("rank"));
        partyTypeColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("partyType"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("address"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("city"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("country"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, String>("time"));
        pobColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, LocalDate>("pob"));
        partyIdColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, Integer>("userId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, Integer>("partyId"));
        entryColumn.setCellValueFactory(new PropertyValueFactory<PartyFinder, Double>("entry"));
       
         //load dummy data
        try{
            loadDbData();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        //What this code will do is add all of the entry fees together at the start of the application 
        int i;
        double total = 0;
        for (i = 0; i < PartyTable.getItems().size(); i++) { 
             total += entryColumn.getCellData(i);
             System.out.print(log.currentUserId);
             
        }
//        Changes the format to canadain currency 
        currentUser = log.currentUserId;
        UserIdLabel.setText(String.valueOf(currentUser));
        String moneyString = formatter.format(total);
        totalLabel.setText(String.valueOf(moneyString));
        }    

    public void testMath(){
        //ignore this was to test the math 
        int i;
        double total = 0;
        for (i = 0; i < PartyTable.getItems().size(); i++) { 
             total += entryColumn.getCellData(i);
             totalLabel.setText(String.valueOf(total));
        }
        
    }
    public void CreatePartyButtonPushed(ActionEvent event) throws IOException{
        //when button is pushed change scene to create page.
   //load a new scene
        userIdLog = currentUser;
        System.out.print(userIdLog);
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "CreateParty.fxml", "Create");
}
    public void ViewPartyButtonPushed(ActionEvent event) throws IOException{
    //load a new scene
    //Not yet used but this will change to view page when view button is pushed 
        SceneChanger sc = new SceneChanger();
        PartyFinder party = this.PartyTable.getSelectionModel().getSelectedItem();
        if (party == null)
            return;
        
        ViewPartyController vpc = new ViewPartyController();
        sc.changeScenes(event, "ViewParty.fxml", "View Party", party, vpc);
}
    public void EditPartyButtonPushed(ActionEvent event) throws IOException{
        SceneChanger sc = new SceneChanger();
        if(currentUser == userIdColumn.getCellData(this.PartyTable.getSelectionModel().getSelectedIndex())){
            PartyFinder party = this.PartyTable.getSelectionModel().getSelectedItem();
        if (party == null){
            errmsgLabel.setText("You dont have access to this party");
            return;
        }
        
        EditPartyController epc = new EditPartyController();
        sc.changeScenes(event, "EditParty.fxml", "Edit Party", party, epc);
        }
    }
    public void loadParty(ObservableList<PartyFinder> newList)
    {
        //When new data is loaded to the table it will reenter all of the entry fees to a new total 
        
        this.PartyTable.setItems(newList);
        int i;
        double total = 0;
        for (i = 0; i < PartyTable.getItems().size(); i++) { 
             total += entryColumn.getCellData(i);
             
        }
        String moneyString = formatter.format(total);
        totalLabel.setText(String.valueOf(moneyString));
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
            resultSet = statement.executeQuery("SELECT * FROM Party");
            
            //4.  create emplpoyee objects from each record
            while (resultSet.next())
            {
                PartyFinder newParty = new PartyFinder(resultSet.getInt("partyId"), 
                                                 resultSet.getInt("userId"),
                                                 resultSet.getString("rank"),
                                                 resultSet.getString("partyType"),
                                                 resultSet.getString("address"),
                                                 resultSet.getString("city"),
                                                 resultSet.getString("country"),
                                                 resultSet.getString("pTime"),
                                                 resultSet.getDate("pob").toLocalDate(),
                                                 resultSet.getDouble("entry"));
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
    public void ViewGraphButtonPushed(ActionEvent event) throws IOException{
        //when button is pushed change scene to create page.
   //load a new scene
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "ViewGraph.fxml", "ViewGraph");
}
    public void ViewAllEmployeesButtonPushed(ActionEvent event) throws IOException{
        //when button is pushed change scene to create page.
   //load a new scene
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "ViewAllUsers.fxml", "View All Users");
}
    public void LogOutButtonPushed(ActionEvent event) throws IOException{
         SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "LoginScreen.fxml", "Login");
    }
     public void ChangePasswordButtonPushed(ActionEvent event) throws IOException{
         SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "ChangePassword.fxml", "Change Password");
    }
     public void GoingButtonPushed(ActionEvent event) throws IOException, SQLException{
        PartyFinder party = this.PartyTable.getSelectionModel().getSelectedItem();
        if (party == null)
            return;
        else{
                  Connection conn = null;
                   PreparedStatement ps = null;
            try{
                //1.  connect to the DB
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PartyFinder?useSSL=false", "root", "");

                //2.  create a query string with ? used instead of the values given by the user
                String sql = "Update Party set Pop = Pop + 1 where PartyId = ?;";

                //3.  prepare the statement
                ps = conn.prepareStatement(sql);
                //4.  bind the volunteerID to the ?
                ps.setInt(1,this.partyIdColumn.getCellData(this.PartyTable.getSelectionModel().getSelectedItem()));

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
        
}

