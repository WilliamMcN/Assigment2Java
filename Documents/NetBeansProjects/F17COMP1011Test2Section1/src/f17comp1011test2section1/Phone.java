/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f17comp1011test2section1;

import java.time.LocalDate;
import javafx.scene.image.Image;

/**
 *
 * @author Fir3AtWill
 */
public class Phone {
    private String manufacturer,model,colour;
    private double  screenSize, cameraRes;
    private int phoneID,memory;

    public Phone(int phoneID, String manufacturer, String model, int memory, String colour, double screenSize, double cameraRes) {
        this.phoneID = phoneID;
        this.manufacturer = manufacturer;
        this.model = model;
        this.colour = colour;
        this.screenSize = screenSize;
        this.cameraRes = cameraRes;
        this.memory = memory;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public double getCameraRes() {
        return cameraRes;
    }

    public void setCameraRes(double cameraRes) {
        this.cameraRes = cameraRes;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
    
    
}
