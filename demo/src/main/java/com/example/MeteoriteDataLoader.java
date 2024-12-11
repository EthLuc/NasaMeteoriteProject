package com.example;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class MeteoriteDataLoader 
{
    public static Meteorite[] loadMeteorites(String filename) 
    {
        try (FileReader reader = new FileReader(filename)) 
        {
            Gson gson = new Gson();
            Meteorite[] meteorites = gson.fromJson(reader, Meteorite[].class);
            return meteorites;
        } catch (IOException e) 
        {
            System.out.println("Error reading the file: " + e.getMessage());
            return null;
        }
    }
}
