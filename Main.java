package com.cambeeler;

import java.util.*;

public class Main {
private static Map<Integer, Location> locations = new HashMap<Integer, Location>();
private static Map<Integer, String> OptionsDisplay = new HashMap<Integer, String>();
private static Map<String, String> vocabulary = new HashMap<String, String>();

private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Set<String> PossibleMoves = null;
        fillInDisplayOne();
        fillInAllLocationMap();
//        printAllOptions();



        Integer choice, IAmHere = 0;
        boolean keepgoing = true;
        Integer loc = 1;

        while (keepgoing){
            if(loc==-1){
                break;
            }

            System.out.println(locations.get(loc).getDescription());


            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are: ");
            for (String exit:exits.keySet()){
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scan.nextLine().toUpperCase();
            String[] words = direction.split(" ");
            for ( String word : words){
                if(vocabulary.containsKey(word)){
                   direction = vocabulary.get(word);
                }
            }


            if(exits.containsKey(direction)){
                loc = exits.get(direction);
            } else{
                System.out.println("You cannot go in that direction");
            }

//            choice = collectSelections();
//
//            switch(choice){
//                case 0:
//                    printAllOptions();
//                    break;
//                case 1:
//                    printCurrentLocation(IAmHere);
//                    break;
//                case 2:
//                    printCurrentMovementOptions(IAmHere);
//                    break;
//                case 3:
//                    printAvailableLocations();
//                    break;
//                case 4:
//                    IAmHere = moveToNewLocation(IAmHere, printCurrentMovementOptions(IAmHere));
//                    printCurrentLocation(IAmHere);
//                    printCurrentMovementOptions(IAmHere);
//                    printAllOptions();
//                    break;
//                case 99:
//                    System.out.println("Bye, it was fun while it lasted");
//                    keepgoing = false;
//                default:
//            }
        }

    }

    // Make these into a Map object and then print your objects so you can verify at time of selection.
    public static void printAllOptions(){
        System.out.println();
        System.out.println("===============================");
        Set<Integer> key = OptionsDisplay.keySet();
        for(Integer i : key){
            System.out.println("Option #" + i + ":  " + OptionsDisplay.get(i));
        }
        System.out.println("===============================");
    }

    public static void printAvailableLocations(){
        Set<Integer> key = locations.keySet();
        for (Integer k :key){
            System.out.println("location " + k + " -> " + locations.get(k).getDescription());
        }
    }

    public static void printCurrentLocation(Integer key){
        System.out.println("location " + key + " -> " + locations.get(key).getDescription());
    }

    public static Set<String> printCurrentMovementOptions(Integer fromHere){
        Set<String> directions = locations.get(fromHere).getExits().keySet();
        System.out.println("Available options for your location: " + locations.get(fromHere).getDescription() );
        for(String s :directions){
            System.out.println("Direction Available: " + s + ", to location # " + locations.get(fromHere).getExits().get(s));
        }
        return directions;
    }

    public static Integer moveToNewLocation(Integer here, Set<String> PossibleMoves){
        String input;
        boolean keepgoing = true;
        while(keepgoing){

            System.out.print("Enter your Choice (Q to quit):");
            input = scan.nextLine();
            if(ViableMovement(input, here)){
                return locations.get(here).getExits().get(input).intValue();
            }else if(input.compareToIgnoreCase("Q")==0) {
                keepgoing = false;
            } else{

                System.out.println("The value " + input + " is out of range, please try again...");
                keepgoing = true;
            }
        }
        return null;
    }

    // verify that the selection is numeric
    // verify that the selection is within the acceptable range of options
    // verify that the selection is

    public static Integer collectSelections(){
        Integer selection=0;
        String input;

//  Just selecting from the first menu...
        while(true){

            System.out.print("Enter your Choice:");
            input = scan.nextLine();
            try {
                selection = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Integer entry only");
                continue;
            }
            if(ViableEntry(selection)){

                break;
            }else{
                System.out.println("The number is out of range, please try again...");
            }

         }
        return selection;

    }
    public static boolean ViableEntry(Integer selection){
        Set<Integer> key = OptionsDisplay.keySet();
        for (Integer i : key){
            if (i==selection){
                return true;
            }
        }
        return false;
    }

    public static boolean ViableMovement(String direction, Integer here){

        Set<String> directions = locations.get(here).getExits().keySet();
        for (String d : directions){
            if (d.compareToIgnoreCase(direction)==0){
                return true;
            }
        }
        return false;
    }

    public static void fillInAllLocationMap(){
        //  Initial locations in the game

        Map<String, Integer> tempExit = new HashMap<String, Integer>( );
//  Location 0 exits
        tempExit.put("S", 3);
        tempExit.put("W", 5);

        locations.put(0, new Location(0,"Learning JAVA", tempExit));

        tempExit = new HashMap<String, Integer>();
//  Location 1 exits
        tempExit.put("W", 2);
        tempExit.put("N", 5);
        tempExit.put("E", 3);
        tempExit.put("S", 4);
        locations.put(1, new Location(1,"You are standing at the end of a road before a small brick building", tempExit));

        tempExit = new HashMap<String, Integer>();
//  Location 2 exits
        tempExit.put("N", 5);
        locations.put(2, new Location(2,"You are at the top of a Hill", tempExit));

        tempExit = new HashMap<String, Integer>();
//  Location 3 exits
        tempExit.put("W", 1);
        tempExit.put("N", 0);
        locations.put(3, new Location(3,"You are inside a building.  A small house for a small spring", tempExit));

        tempExit = new HashMap<String, Integer>();
//  Location 4 exits
        tempExit.put("W", 2);
        locations.put(4, new Location(4,"You are in a valley beside a stream", tempExit));

        tempExit = new HashMap<String, Integer>();
//  Location 5 exits
        tempExit.put("W", 2);
        tempExit.put("S", 1);
        tempExit.put("E", 0);

        locations.put(5, new Location(5,"You are in the forest", tempExit));


        vocabulary.put("QUIT",      "Q");
        vocabulary.put("STOP",      "Q");
        vocabulary.put("EXIT",      "Q");
        vocabulary.put("X",         "Q");

        vocabulary.put("NORTH",     "N");
        vocabulary.put("UP",        "N");
        vocabulary.put("U",         "N");

        vocabulary.put("EAST",      "E");
        vocabulary.put("RIGHT",     "E");
        vocabulary.put("R",         "E");

        vocabulary.put("SOUTH",     "S");
        vocabulary.put("DOWN",      "S");
        vocabulary.put("D",         "S");

        vocabulary.put("WEST",      "W");
        vocabulary.put("LEFT",      "W");
        vocabulary.put("L",         "W");
    }
    public static void fillInDisplayOne(){
        OptionsDisplay.put(0, "Reprint your options");
        OptionsDisplay.put(1, "Where are you now?");
        OptionsDisplay.put(2, "Where can you go from here?");
        OptionsDisplay.put(3, "What are all of the different locations?");
        OptionsDisplay.put(4, "Choose my next location to travel to");
        OptionsDisplay.put(99, "Exit");
    }

}
