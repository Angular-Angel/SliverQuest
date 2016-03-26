/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sliverquest;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author angle
 */
public class SliverQuest {
    public static Random random;
    public static ArrayList<String> commonSpecials;
    public static ArrayList<String> uncommonSpecials;
    public static ArrayList<String> rareSpecials;
    public static ArrayList<String> legendarySlivers;
    public static ArrayList<Sliver> slivers;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        random = new Random();
        
        commonSpecials = new ArrayList<>();
        uncommonSpecials = new ArrayList<>();
        rareSpecials = new ArrayList<>();
        legendarySlivers = new ArrayList<>();
        slivers = new ArrayList<>();
        
        parseSlivers(readRaw(new File("Slivers.json")));
        
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        while (true) {
            String command = sc.next();
            readCommand(command);
        }
        
    }
    
    public static Sliver parseSliver(JSONObject o) {
        Sliver sliver = new Sliver((String) o.get("Name"), (String) o.get("Mana Cost"), ((Long) o.get("Tier")).intValue());
        return sliver;
    }
    
    public static void parseSlivers(JSONArray json) {
        for (Object o : json) {
            slivers.add(parseSliver((JSONObject) o));
        }
    }
    
    public static JSONArray readRaw(File file) {
        JSONParser parser = new JSONParser();
        
        try {
            return (JSONArray) parser.parse(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SliverQuest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SliverQuest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SliverQuest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void readCommand(String command) {
        if (command.startsWith("Spawn Slivers: ")) {
            command = command.replace("Spawn Slivers: ", "");
            generateSlivers(Integer.parseInt(command.split(" ")[0]), command.split(" ")[1]);
        } else if (command.startsWith("Exit")) System.exit(0);
        else if (command.startsWith("Add Sliver: ")) {
            command = command.replace("Add Sliver: ", "");
            switch (command.split(" ")[0]) {
                case "1":
                    commonSpecials.add(command.split(" ")[1]);
                    break;
                case "2":
                    uncommonSpecials.add(command.split(" ")[1]);
                    break;
                case "3":
                    rareSpecials.add(command.split(" ")[1]);
                    break;
                case "4":
                    legendarySlivers.add(command.split(" ")[1]);
                    break;
            }
        }
        else System.out.println(command);
    }
    
    public static void generateSlivers(int quantity, String mana) {
        
        int colorless = 0, white = 0, blue = 0, black = 0, red = 0, green = 0;
        
        while (mana.length() > 0) {
            char c = mana.charAt(0);
            switch (c) {
                case 'C': 
                    colorless++;
                    break;
                case 'W': 
                    white++;
                    break;
                case 'U': 
                    blue++;
                    break;
                case 'B': 
                    black++;
                    break;
                case 'R': 
                    red++;
                    break;
                case 'G': 
                    green++;
                    break;
            }
            if (mana.length() > 1)
            mana = mana.substring(1);
            else mana = "";
        }
        
        for (Sliver sliver : slivers) {
            int tempColorless = colorless, tempWhite = white, tempBlue = blue, tempBlack = black, tempRed = red, tempGreen = green;
            tempBlack -= sliver.black; if (tempBlack < 0 ) continue;
            tempWhite -= sliver.white; if (tempWhite < 0 ) continue;
            tempBlue -= sliver.blue; if (tempBlue < 0 ) continue;
            tempRed -= sliver.red; if (tempRed < 0 ) continue;
            tempGreen -= sliver.green; if (tempGreen < 0 ) continue;
            if (tempColorless + tempBlack +tempBlue + tempGreen + tempRed + tempWhite >= sliver.colorless) {
                switch (sliver.tier) {
                    case 1:
                        commonSpecials.add(sliver.name);
                        break;
                    case 2:
                        uncommonSpecials.add(sliver.name);
                        break;
                    case 3:
                        rareSpecials.add(sliver.name);
                        break;
                    case 4:
                        legendarySlivers.add(sliver.name);
                        break;
                }
            }
        }
        
        
        HashMap<String, Integer> slivers = new HashMap<>();
        for (int i = 0; i < quantity; i++) {
            String sliver = generateSliver();
            if (slivers.containsKey(sliver))
                slivers.put(sliver, slivers.get(sliver) + 1);
            else slivers.put(sliver, 1);
        }
        
        for (String s : slivers.keySet()) {
            System.out.println(s + ": " + slivers.get(s));
        }
        
        commonSpecials.clear();
        uncommonSpecials.clear();
        rareSpecials.clear();
        legendarySlivers.clear();
    }
    
    public static String generateSliver() {
        int rand = random.nextInt(100);
        if (rand >= 90) {
            rand = random.nextInt(100);
            if (rand == 99 && legendarySlivers.size() > 0) return "Sliver " + legendarySlivers.get(random.nextInt(legendarySlivers.size()));
            else if (rand > 90 && rareSpecials.size() > 0) return rareSpecials.get(random.nextInt(rareSpecials.size())) + " Sliver";
            else if (rand > 60 && uncommonSpecials.size() > 0) return uncommonSpecials.get(random.nextInt(uncommonSpecials.size())) + " Sliver";
            else if (commonSpecials.size() > 0) return commonSpecials.get(random.nextInt(commonSpecials.size())) + " Sliver";
        }
        return "Sliver";
    }
    
}
