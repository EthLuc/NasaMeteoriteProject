package com.example;

public class Geolocation {
    private String type;
    private double[] coordinates;


    public String getType()
    {
        return type;
    }

    public double[] getCoordinates()
    {
        return coordinates;
    }

    public String toString()
    {
        return "Type: " + type + ", Coordinates: [" + coordinates[0] + ", " + coordinates[1] + "]";
    }
}

