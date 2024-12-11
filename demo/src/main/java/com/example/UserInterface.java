//Ethan Lucas
//12-4-2024
//Nasa Meteorite Project

package com.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class UserInterface {
    
    private Meteorite[] meteorites;

    public void go()
    {
        Scanner scanner = new Scanner(System.in);
        String fileName = "demo/NASA_Meteorite.json";

        
        while (true)
        {
            System.out.println("Welcome to the NASA Meteorite tracking database.");
            System.out.println("Here's the menu of choices - ");
            System.out.println("0) Quit");
            System.out.println("1) Import meteorite data from a JSON file");
            System.out.println("2) Display the meteorite data");
            System.out.println("3) Export the meteorite data to a file");
            System.out.println("4) Find a meteorite by name");
            System.out.println("5) Find a meteorite by ID");
            System.out.println("6) List the largest meteorites");
            System.out.println("7) List the most recent meteorites by year");
            System.out.println("8) List the meteorite classes");
            System.out.print("Enter your choice: ");
    

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice)
        {
            case 0:
                return;
            case 1:
                System.out.print("Enter the JSON file name or press <Enter> to accept the default (demo/NASA_Meteorite.json)");
                String input = scanner.nextLine();
                if (!input.isEmpty())
                {
                    fileName = input;
                }
                meteorites = MeteoriteDataLoader.loadMeteorites(fileName);
                if(meteorites != null)
                {
                    System.out.println(meteorites.length + " records processed.");
                }
            break;
            case 2:
                if(meteorites != null)
                {
                    for (Meteorite meteorite : meteorites)
                    {
                        System.out.println(meteorite);
                    }
                }
            break;
            case 3:
                if (meteorites != null)
                {
                    try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("meteorites.dat")))
                    {
                        out.writeObject(meteorites);
                        System.out.println("Meteorite data exported successfully.");
                    } catch(IOException e)
                    {
                        System.out.println("Error exporting data: " + e.getMessage());
                    }   
                }
            break;
            case 4:
                System.out.print("Enter the name of the meteorite: ");
                String name = scanner.nextLine().toLowerCase();
                Optional<Meteorite> meteoriteByName = Arrays.stream(meteorites)
                    .filter(m -> m.getName().toLowerCase().contains(name))
                    .findFirst();
                if (meteoriteByName.isPresent())
                {
                    System.out.println(meteoriteByName.get().display());
                }
                else
                {
                    System.out.println("No meteorite found with that name.");
                }
            break;
            case 5:
                System.out.print("Enter the ID of the meteorite: ");
                String id = scanner.nextLine();
                Optional<Meteorite> meteoriteById = Arrays.stream(meteorites)
                    .filter(m -> m.getId().equals(id))
                    .findFirst();
                if (meteoriteById.isPresent())
                {
                    System.out.println(meteoriteById.get().display());
                }
                else
                {
                    System.out.println("No meteorite found with that ID.");
                }
            break;
            case 6:
                System.out.print("How many of the largest meteorites do you want to see? ");
                int count = scanner.nextInt();
                scanner.nextLine();
                Arrays.stream(meteorites)
                    .sorted((m1, m2) -> Double.compare(m2.getMass(), m1.getMass()))
                    .limit(count)
                    .forEach(m -> System.out.println(m.display()));
            break;
            case 7:
                    System.out.print("How many of the most recent meteorites do you want to see? ");
                    int recentCount = scanner.nextInt();
                    scanner.nextLine();
                    Arrays.stream(meteorites)
                            .sorted((m1, m2) -> {
                                if (m1.getYear() == null || m2.getYear() == null) return 0;
                                return m2.getYear().compareTo(m1.getYear());
                            })
                            .limit(recentCount)
                            .forEach(m -> System.out.println(m.display()));
            break;
            case 8:
                Arrays.stream(meteorites)
                    .collect(Collectors.groupingBy(Meteorite::getRecclass, Collectors.counting()))
                    .entrySet().stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .forEach(entry -> System.out.println(entry.getValue() + "      " + entry.getKey()));
            break;
            default:
                    System.out.println("Invalid choice. Please try again");
        }
    }
    }


    public static void main(String[] args)
    {
        UserInterface ui = new UserInterface();
        ui.go();
    }
}

