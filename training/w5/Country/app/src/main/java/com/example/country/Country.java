package com.example.country;

public class Country {
    public int flagName;
    public String countryName;
    public String currency;

    public Country(int flagName, String countryName, String currency) {
        this.flagName = flagName;
        this.countryName = countryName;
        this.currency = currency;
    }

    public Country() {
    }

    public int getFlagName() {
        return flagName;
    }

    public void setFlagName(int flagName) {
        this.flagName = flagName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    @Override
    public String toString() {
        return "SocialNetwork{" +
                "icon=" + flagName +
                ", title=" + countryName +
                ", currency="+currency+ '\'' +
                '}';
    }
}
