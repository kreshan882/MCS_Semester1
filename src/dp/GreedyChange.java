/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dp;

/**
 *
 * @author Kreshan
 */
public class GreedyChange {
    public static void main(String[] args) {
        int amount = 56;
        int coin[]={20,5,1}; //
        int num;
        
        for(int k=0;k<coin.length;k++){
            if(coin[k]<=amount){
                num=amount/coin[k];
                System.out.println(coin[k]+" ::"+num);
                amount-=num*coin[k];
            }
        }
    }
}
