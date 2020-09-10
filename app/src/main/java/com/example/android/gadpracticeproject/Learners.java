package com.example.android.gadpracticeproject;

public class Learners {

    private String name;
    private String hours;
    private String country;
    private String badgeUrl;

    public Learners(){

    }

    public Learners(String name, String hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    //GETTERS

    public String getName() {
        return name;
    }

    public String getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    //SETTERS


    public void setName(String name) {
        this.name = name;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}


