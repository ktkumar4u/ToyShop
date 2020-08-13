package CodeShop;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Toys {

	public static void main(String[] args) {
		HashMap<String, Integer> priceMap = new HashMap <String, Integer> ();
		HashMap<String, Integer> inventoryMap = new HashMap <String, Integer> ();

    	
       //Get Price Input
		//Input to be given as A,5,B,1,C,3,D,2,E,8
		System.out.println("Enter Price for Toy in Each Category");
		priceMap=getInput ();	
		
		ValueComparator bvc = new ValueComparator(priceMap);
		TreeMap<String, Integer> sortedpriceMap = new TreeMap<String, Integer> (bvc);
			
		sortedpriceMap.putAll(priceMap);
		
		//Inventory Input for each Category of Toy
		//A,1,B,10,C,6,D,3,E,0
		System.out.println("Enter Inventory of Toy in Each Category in the shop");
		inventoryMap=getInput ();	

		System.out.println("Enter Available Amount");
		Scanner sc= new Scanner(System.in);
		String str= sc.nextLine(); 

		//Amount available  say 25
        int AvailableAmt= Integer.parseInt(str);
        
        int totalBoughtItem = 0 ;
        
        //Sort through TreeMap for Lowest Cost Items to appear first so that More items of that category could be bought
        Iterator<Map.Entry<String, Integer>> priceIterator = sortedpriceMap.entrySet().iterator();
        int total_cost =0 ;
    	int inv_bought_value= 0;
    	
       // Loop to pick 
        while (priceIterator.hasNext() && AvailableAmt > 0) {
        	Map.Entry<String, Integer> it =  priceIterator.next();
        	
        	Integer price_of_toy=it.getValue();
        	String key=it.getKey();
        	Integer invValue = inventoryMap.get(key);
        	int cost=price_of_toy.intValue() ;
        	
        	//System.out.println(key + cost);
        	//Loop to find out the max inventory for the particular category that could be bought
        	for (int i=0;i<=invValue; i++) {
        		int  cost_for_max_inv_possible = cost* i;
        		if (cost_for_max_inv_possible <= AvailableAmt) {
        			total_cost = cost_for_max_inv_possible ;
                    inv_bought_value=i;     		
        		}
        		}
        	
        	//System.out.println( "cost_for_max_inv" + key + ":" +  total_cost);	
        	
        	
        //Reducing available Amount after each Category of Toy is bought..
        	if (AvailableAmt >= total_cost)
        		   		AvailableAmt-=total_cost;
        	System.out.println("Bought " + inv_bought_value + " of " + key);
        	totalBoughtItem+=inv_bought_value;
         }
        System.out.println("Total Items Brought:" + totalBoughtItem) ;
	}
	
	public static HashMap<String, Integer> getInput() {
		
	
		HashMap<String, Integer> priceMap = new HashMap <String, Integer> ();
		
		
		Scanner sc= new Scanner(System.in);
		String str= sc.nextLine(); 
		StringTokenizer st= new StringTokenizer(str,",");
		String variety;
		String price;
        while (st.hasMoreTokens()) {
  
            // .nextToken is method is returning next token.
    	    variety =st.nextToken();
            price=st.nextToken();
            //System.out.println(variety);
            //System.out.println(price);
          //Price Input for each Category of Toy
            priceMap.put(variety,Integer.parseInt(price));
        } 

		return priceMap;
	}
	
	}


 class ValueComparator implements Comparator <String> {

	Map<String, Integer> base ;
	public ValueComparator (Map <String, Integer> base) {
		this.base=base;
	}
	
	public int compare (String a , String b) {
		
	if (base.get(a) >= base.get(b)) {
		return 1;
	} else {
		
		return -1;
	}
		
		
	}
	
	
	}
	
