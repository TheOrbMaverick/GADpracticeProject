package com.example.android.gadpracticeproject;

public class SkillIq {

    private String name;
    private String score;
    private String country;
    private String badgeUrl;

    public SkillIq(){

    }

    //CONSTRUCTORS

    public SkillIq(String name, String score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    //GETTERS

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
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

    public void setScore(String score) {
        this.score = score;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
