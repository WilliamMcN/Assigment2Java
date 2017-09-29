/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1sept29;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Fir3AtWill
 */
public class MovieController implements Initializable {
    @FXML private Label MovieStringLabel;
    
    @FXML private TextField MovieNameTextFeild;
    @FXML private TextField GenreTextFeild;
    @FXML private TextField DescriptionTextFeild;
    @FXML private TextField YearReleasedTextFeild;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public void MovieCreateButtonPushed(){
     int YearRel = Integer.parseInt(YearReleasedTextFeild.getText());
     String MovieName = MovieNameTextFeild.getText();
     String Genre = GenreTextFeild.getText();
     String Desc = DescriptionTextFeild.getText();
     try{
         Movie movie = new Movie (MovieName,Genre,Desc,YearRel);
         this.MovieStringLabel.setText(movie.toString());
     }
     catch (IllegalArgumentException e){
        this.MovieStringLabel.setText(e.getMessage());
     }
}    
    
}
