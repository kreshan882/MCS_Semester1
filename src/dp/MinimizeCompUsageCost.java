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
    
    private static final int TOTAL_MAINTAIN_YEAR=5;
    private static final int COMPUTER_VALUE = 100000;
    
    private static int[] yearlyCost = new int[]{0,6000,8000,12000}; 
    private static int[] yearlyTrade = new int[]{100000,80000,60000,50000};
    
    
    public static void main(String[] args) {

        Map<String, Integer> possibleCondition = new LinkedHashMap<String, Integer>();
                               //(buyYear,saleYear,Cost,path,map)         
        MinimizeCompUsageCost.findCost(0, 1,  0, new StringBuilder("buy at 0"), possibleCondition);
//        MinimizeCompUsageCost.findCost(0, 2,  0, new StringBuilder("buy at 0"), possibleCondition);
//        MinimizeCompUsageCost.findCost(0, 3,  0, new StringBuilder("buy at 0"), possibleCondition);

        int lowCost = Integer.MAX_VALUE;

        List<String> lowCostPaths = new ArrayList<String>();

        for (String path : possibleCondition.keySet()) {
            System.out.println("(" + path + ") ==> " + possibleCondition.get(path));
            if(lowCost > possibleCondition.get(path)) {
                lowCost = possibleCondition.get(path);
                lowCostPaths.clear();
                lowCostPaths.add(path);
            } else if(lowCost == possibleCondition.get(path)){
                lowCostPaths.add(path);
            }
        }

        System.out.println("\nLow Cost Paths are ");
        for (String lowCostPath : lowCostPaths) {
            System.out.println(lowCostPath);
        }
        System.out.println("Low Cost Value " + lowCost);

    }
    
    private static void findCost(int buyingYear, int sellingYear,  int cost, StringBuilder path, Map<String, Integer> possibleSolutions){
        int serviceCost = serviceCost(buyingYear, sellingYear);
        int depreciationCost = COMPUTER_VALUE - yearlyTrade[sellingYear-buyingYear];


        int costSoFar = cost + (serviceCost + depreciationCost);

        if(sellingYear == TOTAL_MAINTAIN_YEAR){
            path.append(" || sell at " + sellingYear);
            possibleSolutions.put(path.toString(), cost + serviceCost + depreciationCost);
            return ;
        }  
        else if(sellingYear > TOTAL_MAINTAIN_YEAR){
            return;
        }

        path.append(" || sell and buy at "+ sellingYear);

        findCost(sellingYear, sellingYear+1,  costSoFar, new StringBuilder(path.toString()), possibleSolutions);
        findCost(sellingYear, sellingYear+2,  costSoFar, new StringBuilder(path.toString()), possibleSolutions);
        findCost(sellingYear, sellingYear+3,  costSoFar, new StringBuilder(path.toString()), possibleSolutions);

    }

    private static int serviceCost(int buyingYear, int sellingYear) {
        int cost = 0;
        int usage = sellingYear - buyingYear;
        for(int i = 1; i <= usage; i++){
            cost += yearlyCost[i];
        }
        return cost;
    }
}

