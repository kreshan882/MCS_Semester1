
package mcs_also;

import java.util.Arrays;  
import java.util.HashMap;  
import java.util.List;  
import java.util.ArrayList;  
import java.util.Map; 

public class AlgoAssi1_BoyerMoore {

    public static void main(String[] args) {  
    	String txt="ACGTCGTGACTAAGAGTGCAGACTGAGGTGACCGTAGACTAAGTCAGTAGACTCAG";
    	String patten="GACT_AG";
    	
    	String lett[]={"A","C","G","T"};
    	
    	for(int k=0;k<lett.length;k++){
    		String pat="";
    		pat=patten.replace("_", lett[k]);
    		List<Integer> matches = match(pat, txt);  
    		System.out.println(lett[k]+":" + matches.size());
          //  for (Integer integer : matches) System.out.println(lett[k]+":" + integer);  
    	}
         
    }  
    
	
    public static List<Integer> match(String pattern, String text) {  
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
        return matches;  
    }  
    private static Map<Character, Integer> preprocessForBadCharacterShift( String pattern) {   //ana 
        Map<Character, Integer> map = new HashMap<Character, Integer>();  
        for (int i = pattern.length() - 1; i >= 0; i--) {  
            char c = pattern.charAt(i);  
            if (!map.containsKey(c)){
            	//System.out.println(c+":"+i);
            	map.put(c, i);  
            }
        }  
        return map;  
    }  

}  