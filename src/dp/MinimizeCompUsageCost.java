package dp;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

        try{
            Map<String, Integer> allPossibleCondition = new TreeMap<String, Integer>();
            Map<String, Integer> lowCostPaths = new TreeMap<String, Integer>();

            MinimizeCompUsageCost.findCost(0, 0,  0, "start year 0", allPossibleCondition);//(buyYear,saleYear,Cost,path,map) 

            MinimizeCompUsageCost.getLowPath(allPossibleCondition,lowCostPaths);

            System.out.println("Low Cost maintain Paths => value");
            for (String path : lowCostPaths.keySet()) {
                System.out.println( path + " ==> " + lowCostPaths.get(path));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    
    private static void findCost(int buyingYear, int sellingYear,  int cost, String path, Map<String, Integer> possibleCondition){
        
        int serviceCost = serviceCost(buyingYear, sellingYear);
        int redusedValue = COMPUTER_VALUE - yearlyTrade[sellingYear-buyingYear];
        int costTotal = cost+ serviceCost + redusedValue;
        
        if(sellingYear == TOTAL_MAINTAIN_YEAR){
            path=path+" || sale year "+sellingYear;
            possibleCondition.put(path, costTotal );
            return ;
        }  
        else if(sellingYear > TOTAL_MAINTAIN_YEAR){
            return;
        }

        if(sellingYear !=0) {
            path=path+" || sell and buy at "+sellingYear;
        }

        findCost(sellingYear, sellingYear+1,  costTotal, path, possibleCondition);
        findCost(sellingYear, sellingYear+2,  costTotal, path, possibleCondition);
        findCost(sellingYear, sellingYear+3,  costTotal, path, possibleCondition);

    }

    private static int serviceCost(int buyingYear, int sellingYear) {
        int cost = 0;
        int usage = sellingYear - buyingYear;
        for(int i = 1; i <= usage; i++){
            cost += yearlyCost[i];
        }
        return cost;
    }

    private static void getLowPath(Map<String, Integer> possibleCondition, Map<String, Integer> lowCostPaths) throws Exception{
         System.out.println("All Paths:");
         int lowCost = Integer.MAX_VALUE;
        for (String path : possibleCondition.keySet()) {
            System.out.println( path + " ==> " + possibleCondition.get(path));
            
            if(lowCost > possibleCondition.get(path)) {
                lowCost = possibleCondition.get(path);
                lowCostPaths.clear();
                lowCostPaths.put(path,possibleCondition.get(path));
            } else if(lowCost == possibleCondition.get(path)){
                lowCostPaths.put(path,possibleCondition.get(path));
            }
        }
    }
    

}

