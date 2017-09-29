package test1sept29;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fir3AtWill
 */
public class LaunchMovieView extends Application {
 public static void main(String[] args)
    {
        launch(args);
    }
   @Override
       public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Movie.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Test1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
