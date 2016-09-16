package com.descarteaqui.descarteaqui.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gabriel on 10/09/2016.
 */
public class Petition {

    private String streetName;
    private String districtName;
    private String creationDate;
    private String justification;
    private final int ID;
    private String creator;
    private int ratesOK;
    private int ratesNG;
    private boolean alreadyClickOK = false;
    private boolean alreadyClickNG = false;


    public Petition(int ID, String streetName, String districtName, String justification, String creator) {
        this.ID = ID;
        this.streetName = streetName;
        this.districtName = districtName;

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        this.creationDate = df.format(new Date());

        this.justification = justification;
        this.creator = creator;
        this.ratesNG = 0;
        this.ratesOK = 0;
    }

    public int getID(){
        return this.ID;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCreationDate() {

        return this.creationDate;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getRatesOK() {
        return ratesOK;
    }

    public void rateOK() {
        if (!alreadyClickOK) {
            this.ratesOK++;
            this.alreadyClickOK = true;
            if (alreadyClickNG)
                unrateNG();
        }
    }

    public void unrateOK() {
        if (ratesOK >  0) {
            this.ratesOK--;
            this.alreadyClickOK = false;
        }
    }

    public void setRatesNG(int ratesNG){
        this.ratesNG = ratesNG;
    }

    public void setRatesOK(int ratesOK){
        this.ratesOK = ratesOK;
    }

    public int getRatesNG() {
        return ratesNG;
    }

    public void rateNG() {
        if (!alreadyClickNG) {
            this.ratesNG++;
            this.alreadyClickNG = true;
            if (alreadyClickOK)
                unrateOK();
        }
    }

    public void unrateNG() {
        if (ratesNG >  0) {
            this.ratesNG--;
            this.alreadyClickNG = false;
        }
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}