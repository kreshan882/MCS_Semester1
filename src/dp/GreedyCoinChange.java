/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Kreshan
 */
public class GreedyCoinChange {
    private static int[] coinSets = new int[]{100, 50, 25, 10, 5, 2, 1};

    public static void main(String[] args) {
        try {
            //get input value
            Scanner reader = new Scanner(System.in); 
            System.out.print("Please give total amount :");
            int amount = reader.nextInt();
        
            Map<Integer, Integer> coinSelectMap = new HashMap<Integer, Integer>();

            for (int i=0;i<coinSets.length;i++) {
                int numberOfCoins = amount / coinSets[i];
                if (numberOfCoins == 0) continue;
                
                amount = amount % coinSets[i];
                coinSelectMap.put(coinSets[i], numberOfCoins);
                if (amount == 0) break;

            }
           
            
            System.out.println("Minimum Coin count = " +  coinSelectMap);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Please enter the interger");
        }
    }


}
