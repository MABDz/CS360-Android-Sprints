package com.example.lab6_listycity;

/**
 * This is a class that defines a City.
 */
public class City implements Comparable<City> {
    /**
     * The name of the city
     */
    private String city;
    /**
     * The name of the province
     */
    private String province;

    /**
     * Constructor for City
     * @param city
     * This is the city name
     * @param province
     * This is the province name
     */
    public City(String city, String province){
        this.city = city;
        this.province = province;
    }

    /**
     * This returns the city name
     * @return
     * Return the city name
     */
    String getCityName(){
        return this.city;
    }

    /**
     * This returns the province name
     * @return
     * Return the province name
     */
    String getProvinceName(){
        return this.province;
    }

    /**
     * This compares two cities for sorting
     * @param city
     * The city to be compared
     * @return
     * Returns 0 if equal, else compares lexicographically
     */
    @Override
    public int compareTo(City city) {
        return this.city.compareTo(city.getCityName());
    }
}
