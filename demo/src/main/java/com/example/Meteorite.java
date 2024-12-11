package com.example;

public class Meteorite {
    private String name;
    private String id;
    private String nametype;
    private String recclass;
    private double mass;
    private String year;
    private Geolocation geolocation;

    
    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return id;
    }

    public String getRecclass()
    {
        return recclass;
    }

    public double getMass()
    {
        return mass;
    }

    public String getYear()
    {
        return year;
    }

    public Geolocation getGeolocation()
    {
        return geolocation;
    }

    public String display()
    {
        return "name = " + name + ", id = " + id + ", recclass = " + recclass + ", mass = " + mass + ", year = " + year;
    }

    public String toString()
    {
        return display();
    }
}
