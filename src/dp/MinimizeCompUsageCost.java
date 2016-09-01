/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kreshan
 */
public class MinimizeCompUsageCost {
    private static int computerValue = 100000;
    private static int[] yearlyCost = new int[]{0,6000,8000,12000}; 
    private static int[] valueAfterYear = new int[]{100000,80000,60000,50000};
    
    public static void main(String[] args) {

        Map<String, Integer> possibleSolutions = new LinkedHashMap<String, Integer>();
        findCost(0, 1, 5, 0, new StringBuilder("buy at 0"), possibleSolutions);
        findCost(0, 2, 5, 0, new StringBuilder("buy at 0"), possibleSolutions);
        findCost(0, 3, 5, 0, new StringBuilder("buy at 0"), possibleSolutions);

        int lowCost = Integer.MAX_VALUE;

        List<String> lowCostPaths = new ArrayList<String>();

        for (String path : possibleSolutions.keySet()) {
            System.out.println("(" + path + ") ==> " + possibleSolutions.get(path));
            if(lowCost > possibleSolutions.get(path)) {
                lowCost = possibleSolutions.get(path);
                lowCostPaths.clear();
                lowCostPaths.add(path);
            } else if(lowCost == possibleSolutions.get(path)){
                lowCostPaths.add(path);
            }
        }

        System.out.println("\nLow Cost Paths are ");
        for (String lowCostPath : lowCostPaths) {
            System.out.println(lowCostPath);
        }
        System.out.println("Low Cost Value " + lowCost);

    }
    
    private static void findCost(int buyingYear, int sellingYear, int lastYear, int cost, StringBuilder path, Map<String, Integer> possibleSolutions){
        int operatingCost = findOperatingCost(buyingYear, sellingYear);
        int depreciationCost = findDepreciationCost(buyingYear, sellingYear);


        int costSoFar = cost + (operatingCost + depreciationCost);

        if(sellingYear == lastYear){
            path.append("/sell at " + sellingYear);
            possibleSolutions.put(path.toString(), cost + operatingCost + depreciationCost);
            return ;
        }  else if(sellingYear > lastYear){
            return;
        }

        path.append("/sell and buy at "+ sellingYear);

        findCost(sellingYear, sellingYear+1, lastYear, costSoFar, new StringBuilder(path.toString()), possibleSolutions);
        findCost(sellingYear, sellingYear+2, lastYear, costSoFar, new StringBuilder(path.toString()), possibleSolutions);
        findCost(sellingYear, sellingYear+3, lastYear, costSoFar, new StringBuilder(path.toString()), possibleSolutions);

    }

    private static int findOperatingCost(int buyingYear, int sellingYear) {
        int cost = 0;
        int usage = sellingYear - buyingYear;
        for(int i = 1; i <= usage; i++){
            cost += yearlyCost[i];
        }
        return cost;
    }

    private static int findDepreciationCost(int buyingYear, int sellingYear){
        int usage = sellingYear - buyingYear;
        return computerValue - valueAfterYear[usage];
    }

}

