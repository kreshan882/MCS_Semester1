/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dp;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Kreshan
 */
public class GreedyCoinChange {
    private static int[] coinArray = new int[]{100, 50, 10, 5, 2, 1};

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please give the amount as an argument");
            return;
        }
        try {
            int amount = Integer.parseInt(args[0]);
            Map<Integer, Integer> coinSelectionMap = getCoins(amount);
            System.out.println("CoinSelection = " +  coinSelectionMap);
        } catch (NumberFormatException e) {
            System.err.println("Please specify amount as an integer");
        }
    }

    private static Map<Integer, Integer> getCoins(int amount) {
        Map<Integer, Integer> coinSelection = new LinkedHashMap<Integer, Integer>();
        int rest = amount;

        for (int coinValue : coinArray) {
            int numberOfCoins = rest / coinValue;

            if (numberOfCoins == 0) {
                continue;
            }
            rest = rest % coinValue;
            coinSelection.put(coinValue, numberOfCoins);

            if (rest == 0) {
                break;
            }
        }
        return coinSelection;
    }
}
