package com.Isa.SimulatorLokacija;


import java.util.Objects;

public class LocationBean {
    private double Longitude;
    private double Latitude;

    public LocationBean() {
    }

    public LocationBean(double longitude, double latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }




    @Override
    public int hashCode() {
        return Objects.hash(Longitude, Latitude );
    }

    @Override
    public String toString() {
        return "LocationBean{" +
                "Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                '}';
    }
}
