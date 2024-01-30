package com.ISA.ISAProject.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "Country",nullable = false)
    private String Country;

    @Column(name = "City",nullable = false)
    private String City;

    @Column(name ="Address" , nullable = false)
    private String Address;

    @Column(name = "Longitude" , nullable = false)
    private double Longitude;

    @Column(name = "Latitude" , nullable = false)
    private double Latitude;

    public Location() {
    }

    public Location(Integer id, String country, String city, String address, float longitude, float latitude) {
        Id = id;
        Country = country;
        City = city;
        Address = address;
        Longitude = longitude;
        Latitude = latitude;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(Longitude, location.Longitude) == 0 && Double.compare(Latitude, location.Latitude) == 0 && Objects.equals(Id, location.Id) && Objects.equals(Country, location.Country) && Objects.equals(City, location.City) && Objects.equals(Address, location.Address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Country, City, Address, Longitude, Latitude);
    }

    @Override
    public String toString() {
        return "Location{" +
                "Id=" + Id +
                ", Country='" + Country + '\'' +
                ", City='" + City + '\'' +
                ", Address='" + Address + '\'' +
                ", Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                '}';
    }
}
