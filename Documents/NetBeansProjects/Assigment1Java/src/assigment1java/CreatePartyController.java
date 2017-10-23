/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment1java;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Fir3AtWill
 */

public class CreatePartyController implements Initializable {
     private ObservableList<PartyFinder> partyFinder;
    
    @FXML private TextField userTextField;
    @FXML private ChoiceBox partyTypeChoiceBox;
    @FXML private TextField streetTextField;
    @FXML private ChoiceBox cityChoiceBox;
    @FXML private ChoiceBox countryChoiceBox;
    @FXML private DatePicker pob;
    @FXML private ChoiceBox toTimeChoiceBox;
    @FXML private ChoiceBox fromTimeChoiceBox;
    @FXML private TextField entryFeeTextField;
    @FXML private Label errorMsg;
    
    @FXML private ImageView partyImage;
    
    //Used for the file chooser
    private FileChooser fileChooser;
    private File imageFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        partyTypeChoiceBox.setItems(ptcb.getItems());
        cityChoiceBox.setItems(citycb.getItems());
        countryChoiceBox.setItems(countrycb.getItems());
        toTimeChoiceBox.setItems(totcb.getItems());
        fromTimeChoiceBox.setItems(fromtcb.getItems());
        this.partyTypeChoiceBox.getSelectionModel().selectFirst();
        this.cityChoiceBox.getSelectionModel().selectFirst();
        this.countryChoiceBox.getSelectionModel().selectFirst();
        this.toTimeChoiceBox.getSelectionModel().selectFirst();
        this.fromTimeChoiceBox.getSelectionModel().selectFirst();

        try
        {
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
        partyFinder = items;
    }
        public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        changeScene(event, "HomePagePartyFinder.fxml");
    }
        
        public void createNewPartyButtonPushed(ActionEvent event) throws IOException
    {
        try
        {
            PartyFinder newPar = new PartyFinder(userTextField.getText(),
                                                        "Newbie",
                                                        partyTypeChoiceBox.getSelectionModel().getSelectedItem().toString(), 
                                                        streetTextField.getText(),
                                                        cityChoiceBox.getSelectionModel().getSelectedItem().toString(),
                                                        countryChoiceBox.getSelectionModel().getSelectedItem().toString(),
                                                        fromTimeChoiceBox.getSelectionModel().getSelectedItem().toString() + "-" + toTimeChoiceBox.getSelectionModel().getSelectedItem().toString(),
                                                        pob.getValue(),
                                                        partyImage.getImage(),
                                                        Double.parseDouble(entryFeeTextField.getText())
                                                        );
            partyFinder.add(newPar);
            changeScene(event, "HomePagePartyFinder.fxml");
        }
        catch (IllegalArgumentException e)
        {
            errorMsg.setText(e.getMessage());
        }
    }

    private void changeScene(ActionEvent event, String scene)throws IOException {
         //load a new scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(scene));
        Parent parent = loader.load();
        Scene newScene = new Scene(parent);
        
        //access the controller of the new Scene and send over
        //the current list of employees
        HomePagePartyFinderController controller = loader.getController();
        controller.loadParty(partyFinder);
        
        //Get the current "stage" (aka window) 
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        //change the scene to the new scenee
        stage.setTitle("Home Page");
        stage.setScene(newScene);
        stage.show();
    }
     public void chooseImageButtonPushed(ActionEvent event)
    {
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
                BufferedImage bufferedImage = ImageIO.read(imageFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                partyImage.setImage(image);
            }
            catch (IOException e){
                System.err.println(e.getMessage());
            }
        }
    }
    
    
}
