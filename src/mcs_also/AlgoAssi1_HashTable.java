import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;


	public class AlgoAssi1_HashTable {
	public static final int TABLE_SIZE=100000;
	public static  int TARGET_SUM[]={231552, 234756, 596873, 648219, 726312, 981237, 988331, 1277361, 1283379};
	
	public static void main(String[] args) {
		String output[]=new String[9];
		 try{
			 
			   int input[] = AlgoAssi1_HashTable.readInitFile();
			 
			   System.out.println("Insert into hash table.....");
			   Hashtable<Integer, Integer> hashtable=InsertToHashTable(input);
			   
			   System.out.println("Searching sum values.....");
			   for(int p=0;p<TARGET_SUM.length;p++){
				   output[p]="0";
				   for(int q=0;q<input.length;q++){
					   int value=TARGET_SUM[p]-input[q];
					   if(hashtable.containsKey(value)){
						   System.out.println(value+"+"+input[q]+"="+TARGET_SUM[p]);
						   output[p]="1";
						   break;
					   }  
				   }
			   }
			   
			   for (String op : output){
				   System.out.print(op+" ");
			   }
			        
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }

	private static Hashtable<Integer, Integer> InsertToHashTable(int[] input) throws Exception{
		Hashtable<Integer, Integer> hashtable = new Hashtable<Integer, Integer>(TABLE_SIZE);
		for(int i=0;i<input.length;i++){
			   int key=input[i]%(10000000);
			   hashtable.put(key ,input[i] );
		   }
		return hashtable;
	}

	private static int[] readInitFile() throws Exception{
		    BufferedReader br = null;
	        String sCurrentLine; 
	        br = new BufferedReader(new FileReader("C:\\Users\\kreshan\\Desktop\\HashInt.txt"));
	        int inputf[] = new int[TABLE_SIZE];
	        int i = 0;
	        while ((sCurrentLine = br.readLine()) != null) {

	            inputf[i++] = Integer.parseInt(sCurrentLine);
	        }
	        return inputf;
		
	}
	}