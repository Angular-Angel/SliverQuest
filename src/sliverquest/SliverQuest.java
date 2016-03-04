/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sliverquest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        random = new Random();
        
        commonSpecials = new ArrayList<>();
        uncommonSpecials = new ArrayList<>();
        rareSpecials = new ArrayList<>();
        legendarySlivers = new ArrayList<>();
        
        commonSpecials.add("Muscle Sliver");
        commonSpecials.add("Sinew Sliver");
        commonSpecials.add("Plated Sliver");
        commonSpecials.add("Blade Sliver");
        commonSpecials.add("Talon Sliver");
        commonSpecials.add("Poultice Sliver");
        commonSpecials.add("Crypt Sliver");
        commonSpecials.add("Quick Sliver");
        uncommonSpecials.add("Battering Sliver");
        uncommonSpecials.add("Winged Sliver");
        uncommonSpecials.add("Galerider Sliver");
        uncommonSpecials.add("Venom Sliver");
        uncommonSpecials.add("Virulent Sliver");
        uncommonSpecials.add("Quilled Sliver");
        uncommonSpecials.add("Ward Sliver");
        uncommonSpecials.add("Gemhide Sliver");
        uncommonSpecials.add("Manaweft Sliver");
        uncommonSpecials.add("Might Sliver");
        uncommonSpecials.add("Blade Sliver");
        uncommonSpecials.add("Hibernation Sliver");
        uncommonSpecials.add("Heart Sliver");
        uncommonSpecials.add("Cautery Sliver");
        rareSpecials.add("Pulmonic Sliver");
        rareSpecials.add("Brood Sliver");
        rareSpecials.add("Bonescythe Sliver");
        rareSpecials.add("Essence Sliver");
        rareSpecials.add("Megantic Sliver");
        rareSpecials.add("Mnemonic Sliver");
        rareSpecials.add("Synapse Sliver");
        rareSpecials.add("Dormant Sliver");
        legendarySlivers.add("Sliver Queen");
        legendarySlivers.add("Sliver Overlord");
        legendarySlivers.add("Sliver Legion");
        legendarySlivers.add("Sliver Hivelord");
        
        
        HashMap<String, Integer> slivers = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            String sliver = generateSliver();
            if (slivers.containsKey(sliver))
                slivers.put(sliver, slivers.get(sliver) + 1);
            else slivers.put(sliver, 1);
        }
        
        for (String s : slivers.keySet()) {
            System.out.println(s + ": " + slivers.get(s));
        }
        
    }
    
    public static String generateSliver() {
        int rand = random.nextInt(100);
        if (rand < 99) return "Sliver";
        else {
            rand = random.nextInt(100);
            if (rand < 50) return commonSpecials.get(random.nextInt(commonSpecials.size()));
            else if (rand < 80) return uncommonSpecials.get(random.nextInt(uncommonSpecials.size()));
            else if (rand < 99) return rareSpecials.get(random.nextInt(rareSpecials.size()));
            else return legendarySlivers.get(random.nextInt(legendarySlivers.size()));
        }
    }
    
}
