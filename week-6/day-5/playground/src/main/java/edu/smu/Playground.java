package edu.smu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Playground {
    private List<String> equipment;
    private String parkName;
    private int acres;



//    public Playground() {
//        super(); // call to the Object default constructor
//    }


    public Playground(List<String> equipment, String parkName, int acres) {
        this.equipment = equipment;
        this.parkName = parkName;
        this.acres = acres;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public String getEquipmentString() {
        String returnVal = "Park equipment: ";
        for (String item : equipment) {
            returnVal = returnVal + item + "... ";
        }
        return returnVal;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getAcres() {
        return acres;
    }

    public void setAcres(int acres) {
        this.acres = acres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playground that = (Playground) o;
        return acres == that.acres && Objects.equals(equipment, that.equipment) && Objects.equals(parkName, that.parkName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipment, parkName, acres);
    }

    @Override
    public String toString() {
        return "Playground{" +
                "equipment=" + equipment +
                ", parkName='" + parkName + '\'' +
                ", acres=" + acres +
                '}';
    }
}
