package models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private String user,rank,partyType,address, city, country, time,password,message;
    private LocalDate pob;
    private Image userImage;
    private int userId,partyId;
    private byte[] salt;
    private boolean admin;
    private double entry;
    
    //Login Account
    /**
     * User account login 
     * @param userId
     * @param admin 
     */
        public PartyFinder(int userId, boolean admin) {
        this.userId = userId;
        this.admin = admin;
    }
        public PartyFinder(String password, int userId, boolean admin) {
        this.password = password;
        this.userId = userId;
        this.admin = admin;
    }
        /**
         * User login with salt 
         * @param password
         * @param userId
         * @param salt
         * @param admin 
         */
    public PartyFinder(String password, int userId, byte[] salt, boolean admin) {
        this.password = password;
        this.userId = userId;
        this.salt = salt;
        this.admin = admin;
    }

    public PartyFinder(int partyId,int userId,String rank, String partyType, String address, String city, String country, String time, double entry,String message) {
        this.rank = rank;
        this.partyType = partyType;
        this.address = address;
        this.city = city;
        this.country = country;
        this.time = time;
        this.userId = userId;
        this.partyId = partyId;
        this.entry = entry;
        this.message = message;
    }

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

    public PartyFinder(String user, String rank, String partyType, String address, String city, String country, String time, LocalDate pob, double entry, int userId) {
        this.user = user;
        this.userId = userId;
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

    public PartyFinder(int userId, int partyId, String rank, String partyType, String address, String city, String country, String time, LocalDate pob, double entry) {
        this.rank = rank;
        this.partyType = partyType;
        this.address = address;
        this.city = city;
        this.country = country;
        this.time = time;
        this.pob = pob;
        this.userId = userId;
        this.partyId = partyId;
        this.entry = entry;
    }


    

    public double getEntry() {
        return entry;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPartyId() {
        return partyId;
    }

    public void setPartyId(int partyId) {
        this.partyId = partyId;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
     public void insertIntoDB() throws SQLException{
        Connection conn = null;
        PreparedStatement preparedstatement = null;
        try{
        //1. connect to db 
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PartyFinder?useSSL=false", 
                                                "root", "");
        //2. create a string that will holds our sql query with ? for user inputs 
        String sql = "INSERT INTO Parties (userName,rank,partyType,address,city,country,pob,entry)Values" +
	"(?,?,?,?,?,?,?,?)";
        //3. prepare the query 
        preparedstatement = conn.prepareStatement(sql);
        //4. convert the birthday in sql date 
        Date pob = Date.valueOf(getPob());
        //5. bind the values to the pareameters 
        preparedstatement.setString(1, getUser());
        preparedstatement.setString(2, getRank());
        preparedstatement.setString(3, getPartyType());
        preparedstatement.setString(4, getAddress());
        preparedstatement.setString(5, getCity());
        preparedstatement.setString(6, getCountry());
        preparedstatement.setDate(7, pob);
        preparedstatement.setDouble(8, getEntry());
        //6. execute the update
        preparedstatement.executeUpdate();
        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally 
        {
            if (conn != null)
                conn.close();
            if (preparedstatement != null)
                preparedstatement.close();
        }
    }
     public void updateInDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement statement = null;
               
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PartyFinder?useSSL=false",
                                        "root", "");

            //2. create a String that holds our sql query with ? for user inputs
            String sql= "UPDATE Parties " +
                        "SET userName = ?, " +
                        "    rank = ?, " +
                        "    partyType = ?, " +
                        "    address = ? " +
                        "    city = ? " +
                        "    country = ? " +
                        "    pob = ? " +
                        "    userImage = ? " +
                        "    entry = ? " +
                        "WHERE partyId = ?";

            //3. prepare the query
            statement = conn.prepareStatement(sql);

            //4. convert the birthday into a SQL date
            Date pob = Date.valueOf(getPob());

            //5. bind the values to the parameters
            statement.setString(1, getUser());
            statement.setString(2, getRank());
            statement.setString(3, getPartyType());
            statement.setString(4, getAddress());
            statement.setString(5, getCity());
            statement.setString(6, getCountry());
            statement.setDate(7, pob);
            statement.setString(8,getUserImage().toString());
            statement.setDouble(9, getEntry());
            statement.setInt(10, getUserId());

            //6. execute the update/insert
            statement.executeUpdate();
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
        }
    }
    
    //Reason for no need to check is because all users will be given default inputs that they can't remove unless they selected a new input
    
}
