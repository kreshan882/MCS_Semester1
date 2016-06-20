
package mcs_also;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;  
import java.util.HashMap;  
import java.util.List;  
import java.util.ArrayList;  
import java.util.Map; 

public class AlgoAssi1_BoyerMoore {

    public static void main(String[] args) {  
        try{
    	String txt="ACGTCGTGACTAAGAGTGCAGACTGAGGTGACCGTAGACTAAGTCAGTAGACTCAG";
    	String patten="GACT_AG";
        
//        System.out.println("Enter The Text:");
//    	BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//	String txt = bufferRead.readLine();
//        System.out.println("Enter The Patten:");
//        BufferedReader bufferRead2 = new BufferedReader(new InputStreamReader(System.in));
//	String patten = bufferRead2.readLine();
        
    	String lett[]={"A","C","G","T"};
    	int total=0;
        List<Integer> matches=new ArrayList<Integer>();
    	for(int k=0;k<lett.length;k++){
    		String pat="";
    		pat=patten.replace("_", lett[k]);
                int val=match(pat, txt);
    		matches.add(val);  
                total+=val; 
    	}
        
        System.out.println(total);
        for(int i=0;i<matches.size();i++){
            System.out.println(matches.get(i));
        } 
        }catch(Exception e){
            e.printStackTrace();
        }
    }  
    
	
    public static int match(String pattern, String text) {  
        List<Integer> matches = new ArrayList<Integer>();  
        int m = text.length();  
        int n = pattern.length();  
        Map<Character, Integer> rightMostIndexes = preprocessForBadCharacterShift(pattern);  
       
        int alignedAt = 0;  
        while (alignedAt + (n - 1) < m) {  //wwewwewcfdddfefd   search in full text (m-n+1)
        	
            for (int indexInPattern = n - 1; indexInPattern >= 0; indexInPattern--) {   //search in abc patten
	            int indexInText = alignedAt + indexInPattern;  
	            char x = text.charAt(indexInText);  
	            char y = pattern.charAt(indexInPattern);  
	                if (indexInText >= m) break;  
	                if (x != y) {  
	                    Integer r = rightMostIndexes.get(x);  
	                    if (r == null) {  
	                        alignedAt = indexInText + 1;  
	                    }  
	                    else {  
	                        int shift = indexInText - (alignedAt + r);  
	                        alignedAt += shift > 0 ? shift : 1;  
	                    }  
	                    break;  
	                }  
	                else if (indexInPattern == 0) {  
	                    matches.add(alignedAt);  
	                    alignedAt++;  
	                }  
            }  
        }  
        return matches.size();  
    }  
    private static Map<Character, Integer> preprocessForBadCharacterShift( String pattern) {   //ana 
        Map<Character, Integer> map = new HashMap<Character, Integer>();  
        for (int i = pattern.length() - 1; i >= 0; i--) {  
            char c = pattern.charAt(i);  
            if (!map.containsKey(c)){
            	map.put(c, i);  
            }
        }  
        return map;  
    }  

}  