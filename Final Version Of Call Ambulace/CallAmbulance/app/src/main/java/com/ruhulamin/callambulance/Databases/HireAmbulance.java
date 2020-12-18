package com.ruhulamin.callambulance.Databases;

import android.widget.RadioGroup;

public class HireAmbulance {

    private String location;
    private String number;





    public HireAmbulance(String location, String number) {
        this.location = location;
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
