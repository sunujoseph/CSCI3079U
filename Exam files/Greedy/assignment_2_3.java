/**
 *
 * @author Pious Joseph
 */
import java.io.*;
public class assignment_2_3 {
    public static double W[];//Weight of item
    public static double V[];//Value of item
    public static int num = 0;//Number of items
    public static double knapw = 0;//Max Weight of Knapsack
    public static double VW[];//Ratio of Value to Weight of item (Value[i]/Weight[i] where i is the item number)
    
    public static void main(String args[])throws IOException{
    BufferedReader myInput = new BufferedReader (new InputStreamReader(System.in));
        
        //User Enter the number of items 
        System.out.println("Enter number of items: ");
        num = Integer.parseInt(myInput.readLine());
        
        //apply the number of items to each array
        W = new double[num];
        V = new double[num];
        VW = new double[num];
        
        //Enter Weight and Value of each item for num items
        for (int i = 0; i <num; i++){
            System.out.println("Enter Item [" + (i+1) +"]of Weight: ");
            W[i] = Double.parseDouble(myInput.readLine());
            System.out.println("Enter Item [" + (i+1) +"]of Value: ");
            V[i] = Double.parseDouble(myInput.readLine());
            VW[i] = V[i]/W[i];
        }

        //Enter the Knapsack Weight
        System.out.println("Enter Knapsack Weight: ");
        knapw = Double.parseDouble(myInput.readLine());
        
        //Output the current items available
        for (int i = 0; i <num; i++){
            System.out.println("======================================================================"
                    + "\nItem: " + (i+1) + "|Value: "+ V[i]  + "|Weight: " + W[i] + "|Ratio: " +VW[i]);
        }
        //Use the Greedy Sort Here.
        sort();
        
    }
    
    //Find the Max Value to Wieght Ratio item for Greedy algorithm
     static int getMax(){
        double max = 0;
        int in = -1;
        for (int i = 0; i < num; i++){
            if(VW[i] > max){
                max = VW[i];
                in = i;
            }
        }     
        return in;
    }
    
    
    //Sorting based on the Greedy algorithm
    public static void sort(){
        double tempW = 0;
        double tempV = 0;
        
        System.out.println("\nItems Selected: ");
        while(tempW < knapw){
            int tempi = getMax();
            if(tempi == -1){
                break;//no items left
            }
            //if the item fits within the knapsack, add to knapsack
             if ((W[tempi] + tempW) <= knapw){
                 System.out.println("Item: " + (tempi+1));
                 //Print the Item that is in the knapsack
                 
                 tempW = tempW + W[tempi];
                 
                 
                 tempV = tempV + V[tempi];
                 
                 //make the value and weight ratio 0 so the greedy algorithm
                 //would ignore the item and move on to the next one
                 VW[tempi] = 0;
                 
                 
                
            }
             //If the value is highest but the wieght exceeds the knapsack wieght, dont add anything
             else if (W[tempi] > knapw){
                 VW[tempi] = 0;//make the value and weight ratio 0 so the greedy algorithm
                              //would ignore the item and move on to the next one
             }
             
             else {
                break;//knapsack full
            }
            
            
        }
        
        //Once the Greedy Algorithm is done, 
        //Print the total value in the kanpsack
        //Print the total Weight of the the items in the knapsack
        //Express the percentage of the wieght of the knapsack
        System.out.println("\nMax Value = " + tempV + ", Max Weight = " + tempW);
        System.out.println("Weight Percentage: " + ((tempW/knapw)*100) + "%");
        
    }
    
}
