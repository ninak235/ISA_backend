package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Location;

import java.util.Objects;

public class LocationSimulatorDto {
    private double Longitude;
    private double Latitude;

    public LocationSimulatorDto(){

    }
    public LocationSimulatorDto(double longitude, double latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }

    public LocationSimulatorDto(Location location) {
        this.Longitude = location.getLongitude();
        this.Latitude = location.getLatitude();
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
