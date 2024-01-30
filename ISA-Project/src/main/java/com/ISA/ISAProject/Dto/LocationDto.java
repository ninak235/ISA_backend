package com.ISA.ISAProject.Dto;

import com.ISA.ISAProject.Model.Location;

import java.util.Objects;

public class LocationDto {

    private Integer Id;
    private String Address;
    private String City;
    private String Country;
    private double Longitude;
    private double Latitude;

    public LocationDto(){

    }
    public LocationDto(Integer id, String country, String city, String address,double longitude, double latitude) {
        Id = id;
        Address = address;
        Country = country;
        City = city;
        Longitude = longitude;
        Latitude = latitude;
    }

    public LocationDto(Location location) {
        this.Id = location.getId();
        this.Address = location.getAddress();;
        this.City = location.getCity();
        this.Country = location.getCountry();
        this.Longitude = location.getLongitude();
        this.Latitude = location.getLatitude();
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
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
