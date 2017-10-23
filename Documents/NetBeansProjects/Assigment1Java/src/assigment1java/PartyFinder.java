package assigment1java;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *  @FXML private TableView<Employee> PartyTable;
    @FXML private TableColumn<Employee, String> userColumn;
    @FXML private TableColumn<Employee, String> rankColumn;
    @FXML private TableColumn<Employee, String> partyTypeColumn;
    @FXML private TableColumn<Employee, String> addressColumn;
    @FXML private TableColumn<Employee, String> cityColumn;
    @FXML private TableColumn<Employee, String> countryColumn;
    @FXML private TableColumn<Employee, String> timeColumn;
    @FXML private TableColumn<Employee, LocalDate> dobColumn;
 */
/**
 *
 * @author Fir3AtWill
 */
public class PartyFinder {
    private String user,rank,partyType,address, city, country, time;
    private LocalDate pob;
    private Image userImage;
    private double entry;
//Constuctors with image 
    public PartyFinder(String user, String rank, String partyType, String address, String city, String country, String time, LocalDate pob, Image userImage, double entry) {
        this.user = user;
        this.rank = rank;
        this.partyType = partyType;
        this.address = address;
        this.city = city;
        this.country = country;
        this.time = time;
        this.pob = pob;
        this.userImage = userImage;
        this.entry = entry;
    }
    //Constuctors without image 

    public PartyFinder(String user, String rank, String partyType, String address, String city, String country, String time, LocalDate pob, double entry) {
        this.user = user;
        this.rank = rank;
        this.partyType = partyType;
        this.address = address;
        this.city = city;
        this.country = country;
        this.time = time;
        this.pob = pob;
        this.entry = entry;
        
        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("./src/images/default.png"));
            userImage = SwingFXUtils.toFXImage(bufferedImage, null);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public double getEntry() {
        return entry;
    }

    public void setEntry(double entry) {
        //Make sure dollar amount is greater then 0 
        if(entry < 0.00){
            throw new IllegalArgumentException("Entry cant be less then 0.00");
        }
        this.entry = entry;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        //Make sure user is filled 
        if(user == null || "".equals(user)){
            throw new IllegalArgumentException("Must have a user name");
        }
        else{
             
        }
        this.user = user;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        //No need to check but make sure party type is filled 
        if(partyType != null || !"".equals(partyType)){
            
        }
        else{
             throw new IllegalArgumentException("Must have a party type");
        }
        this.partyType = partyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        //Make sure the address isnt blank 
         if(address != null || !"".equals(address)){
            
        }
        else{
             throw new IllegalArgumentException("Must have an Address");
        }
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        //No need to check but this is to make sure the city is inputted 
        if(city != null || !"".equals(city)){
            
        }
        else{
             throw new IllegalArgumentException("Must have a city");
        }
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
         //No need to check but this is to make sure the country is inputted 
        if(country != null || !"".equals(country)){
            
        }
        else{
             throw new IllegalArgumentException("Must have a country");
        }
        this.country = country;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
         //No need to check but this is to make sure the time is inputted 
        if(time != null || !"".equals(time)){
            
        }
        else{
             throw new IllegalArgumentException("Must have a time");
        }
        this.time = time;
    }

    public LocalDate getPob() {
        return pob;
    }

    public void setPob(LocalDate pob) {
        //Make sure data is not in the past 
        if (pob.isAfter(LocalDate.now()) || (pob.isEqual(LocalDate.now()))){
            this.pob = pob;
        }
        else
            throw new IllegalArgumentException("Party cannot be in the past");
        this.pob = pob;
    }

    public Image getUserImage() {
        return userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage = userImage;
    }
    
    //Reason for no need to check is because all users will be given default inputs that they can't remove unless they selected a new input
    
}
