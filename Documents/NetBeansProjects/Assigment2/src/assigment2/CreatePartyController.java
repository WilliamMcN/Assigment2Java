/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import models.PartyFinder;

/**
 * FXML Controller class
 *
 * @author Fir3AtWill
 */
public class CreatePartyController implements Initializable {
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
    
    @FXML private ImageView partyImage;
    
    //Used for the file chooser
    private FileChooser fileChooser;
    private File imageFile;
    
    
    
    ViewAllPartiesController log = new ViewAllPartiesController();

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
        //Selected the first item in the dropbox as default 
        this.partyTypeChoiceBox.getSelectionModel().selectFirst();
        this.cityChoiceBox.getSelectionModel().selectFirst();
        this.countryChoiceBox.getSelectionModel().selectFirst();
        this.toTimeChoiceBox.getSelectionModel().selectFirst();
        this.fromTimeChoiceBox.getSelectionModel().selectFirst();
        
        try
        {
            //Creating a default image for the user 
            BufferedImage bufferedImage = ImageIO.read(new File("./src/Images/default.png"));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            partyImage.setImage(image);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }    

    void initialData(ObservableList<PartyFinder> items) {
       System.out.print("Words");
    }
    public void BackButtonPushed(ActionEvent event) throws IOException{
          SceneChanger sc = new SceneChanger();
          sc.changeScenes(event, "ViewAllParties.fxml", "All Parties");
    }
  
    public void chooseImageButtonPushed(ActionEvent event)
    {
        //Allows the user to pick their own image when this button is clicked 
        //get the stage to open a new window
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        
        //filter for only .jpg and .png files
        FileChooser.ExtensionFilter jpgFilter 
                = new FileChooser.ExtensionFilter("Image File (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pngFilter 
                = new FileChooser.ExtensionFilter("Image File (*.png)", "*.png");
        
        fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);
        
        
        //Set to the user's picture directory or C drive if not available
        String userDirectoryString = System.getProperty("user.home")+"\\Pictures";
        File userDirectory = new File(userDirectoryString);
        
        if (!userDirectory.canRead())
            userDirectory = new File("c:/");
        
        fileChooser.setInitialDirectory(userDirectory);
        
        //open the file dialog window
        imageFile = fileChooser.showOpenDialog(stage);
        
        //ensure user selected file 
        if (imageFile.isFile()){
            try{
                //Try to add the image to page 
                BufferedImage bufferedImage = ImageIO.read(imageFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                partyImage.setImage(image);
            }
            catch (IOException e){
                //if error happens, print to error to page 
                errorMsg.setText(e.getMessage());
            }
        }
    }
     public void createButtonPushed(ActionEvent event) throws IOException, NoSuchAlgorithmException, SQLException
    {
        Connection conn = null;
        PreparedStatement ps = null;
        
        
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PartyFinder?useSSL=false", "root", "");
            
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "INSERT INTO party(userName,rank,partyType,address,city,country,pTime,pob,entry,userId,message)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            
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
            ps.setString(7,fromTimeChoiceBox.getSelectionModel().getSelectedItem().toString() + "To" + this.toTimeChoiceBox.getSelectionModel().getSelectedItem().toString());
            ps.setDate(8, db);
            ps.setInt(9, (int) entrySpinner.getValue());
            ps.setInt(10,log.userIdLog);
            ps.setString(11,messageTextArea.getText());
            
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
            sc.changeScenes(event, "ViewAllPartiesController.fxml", "View All Parties");
        
    }
}
