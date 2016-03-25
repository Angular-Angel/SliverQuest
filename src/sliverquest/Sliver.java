/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sliverquest;

/**
 *
 * @author angle
 */
public class Sliver {
    public int colorless = 0, white = 0, blue = 0, black = 0, red = 0, green = 0;
    public int tier;
    public String name;
    
    public Sliver(String name, String mana, int tier) {
        this.tier = tier;
        this.name  = name;
        while (mana.length() > 0) {
            char c = mana.charAt(0);
            switch (c) {
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
                case '1':
                    colorless++;
                    break;
                case '2':
                    colorless += 2;
                    break;
                case '3':
                    colorless += 3;
                    break;
                case '4':
                    colorless += 4;
                    break;
                case '5':
                    colorless += 5;
                    break;
                case '6':
                    colorless += 6;
                    break;
                case '7':
                    colorless += 7;
                    break;
                case '8':
                    colorless += 8;
                    break;
                case '9':
                    colorless += 9;
                    break;
            }
            if (mana.length() > 1)
            mana = mana.substring(1);
            else mana = "";
        }
    }
    
}
