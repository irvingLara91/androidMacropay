package com.example.applicationmacropay.models;
import java.io.Serializable;

public class User implements Serializable {
    String first_name;
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

}
