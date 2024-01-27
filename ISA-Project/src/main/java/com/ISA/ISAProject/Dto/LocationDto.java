package com.ISA.ISAProject.Dto;

import java.util.Objects;

public class LocationDto {

    private double Longitude;
    private double Latitude;

    public LocationDto(){

    }
    public LocationDto(double longitude, double latitude) {
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
        return "LocationDto{" +
                "Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                '}';
    }
}
