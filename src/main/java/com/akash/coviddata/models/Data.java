package com.akash.coviddata.models;

public class Data implements Comparable<Data> {
    private String country;
    private int totalCases;
    private int totalRecovered;
    private int totalDeaths;
    private int newCases;
    private int newDeath;
    private int newRecovered;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getNewDeath() {
        return newDeath;
    }

    public void setNewDeath(int newDeath) {
        this.newDeath = newDeath;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    @Override
    public String toString() {
        return "Data [country=" + country + ", newCases=" + newCases + ", newDeath=" + newDeath + ", newRecovered="
                + newRecovered + ", totalCases=" + totalCases + ", totalDeaths=" + totalDeaths + ", totalRecovered="
                + totalRecovered + "]";
    }

    @Override
    public int compareTo(Data d) {
        return (d.getTotalCases()) - getTotalCases();
    }

}
