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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Fir3AtWill
 */
public class ViewGraphController implements Initializable {
    @FXML    private BarChart<?, ?> barChart;
    @FXML    private CategoryAxis Parties;
    @FXML    private NumberAxis Entry;
    
    private XYChart.Series currentYearSeries;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          currentYearSeries = new XYChart.Series<>();
        
        //barChart.setTitle("Hours Worked");
        Parties.setLabel("Parties");
        Entry.setLabel("Entry");
        
        currentYearSeries.setName(Integer.toString(LocalDate.now().getYear()));
        
        try{
            populateSeriesFromDB();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        
        barChart.getData().addAll(currentYearSeries);
        
    
    }
     public void populateSeriesFromDB() throws SQLException
    {
        //get the results from the database
        Connection conn=null;
        Statement statement=null;
        ResultSet resultSet=null;
        
        try
        {
            //1.  connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/partyfinder", "root", "");
            
            //2.  create the statement
            statement = conn.createStatement();
            
            //3.  create a string with the sql statement
            String sql = "SELECT YEAR(pob), PartyId, Entry" +
                         " From Party " +
                         "GROUP BY YEAR(pob), PartyId " +
                         "ORDER BY YEAR(pob), Entry";
            
            //4. execute the query
            resultSet = statement.executeQuery(sql);
            
            //5.  loop over the result set and add to our series
            while (resultSet.next())
            {
                if (resultSet.getInt(1) == LocalDate.now().getYear())
                    currentYearSeries.getData().add(new XYChart.Data(resultSet.getString(2), resultSet.getDouble(3)));
            }       
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
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
public void BackButtonPushed(ActionEvent event) throws IOException, SQLException{
           SceneChanger sc = new SceneChanger();
           sc.changeScenes(event, "ViewAllPartiesAdmin.fxml", "All Parties");
          
      }    
    
}
