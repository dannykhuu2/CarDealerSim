//Danny Khuu
//500 903 037
//April 11 2019

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import java.text.DateFormatSymbols;

public class AccountingSystem
{
    private ArrayList<Transaction> transactionsList;
    
    public AccountingSystem()
    {
        ArrayList<Transaction> t = new ArrayList<Transaction>();
        transactionsList = t;
    }
    //Adds a new transaction object to the transactions list and returns a transaction string
    public String add(Calendar date, Car car, String salesPerson, String type, double salePrice)
    {
        int ID = (int)(Math.random()*99)+1;
        Transaction transactions = new Transaction(ID, car, salesPerson, type , date, salePrice);
        transactionsList.add(transactions);
        return transactions.display();
    }
    //returns a specified transaction by the ID 
    public Transaction getTransaction(int id)
    {
        Transaction ref = null;
        for(int i = 0; i < transactionsList.size(); i++)
        {
            if(transactionsList.get(i).getID() == id)
            {
                ref = transactionsList.get(i);
            }
        }
        return ref;
    }
    //Finds the top seller based off the transactions list, does not include returns 
    public String findTopSP() 
    {
        int largest = 0;
        String largestName = "";
        String message = "";
        Map<String,Integer> salesAmount = new TreeMap<String,Integer>();
        for(int i = 0; i < transactionsList.size(); i++)
        {
            int count = 0;
            Transaction current = transactionsList.get(i);
            String name = current.getSalesName();
            if(current.getType().equals("BUY"))
			{
	            if(!salesAmount.containsKey(name))
	            {
	                salesAmount.put(name,1);
	            }
	            else
	            {
	                count = salesAmount.get(name);
	                count++;
	                salesAmount.put(name,count);
	            }
			}

        }
        //Goes through the keys of the TreeMap salesAmount in order to find the highest value and therefore the highest key
        for(String name: salesAmount.keySet())
        {
            int value = salesAmount.get(name); //What to print for a tie
            if(largest < value)
            {
                largest = value;
                largestName = name;
            }
            else if(largest == value && value != 0)
            {
                largestName = largestName + ", " +  name;
            }
        }
        if(transactionsList.size() > 0)
        {
        	message = "Top SP: " + largestName + " " + largest;
        }
        return message;
    }
    //Returns a string of various stats about the transactions made throughout the program
    public String findStats()
    {
        String message = "";
        double totalSalesPrice = 0.0;
        int average = 0;
        int total = 0;
        int totalReturns = 0;
        int highestSalesMonth = 0;
        int highestSales = 0;
        String highestMonthStr = "None";
        Map<Integer,Integer> highestMonth = new TreeMap<Integer,Integer>();
        for(int i = 0; i < transactionsList.size(); i++)
        {
            Transaction current = transactionsList.get(i);
            if(current.getType().equals("BUY"))
            {
                total++;
                totalSalesPrice = totalSalesPrice + current.getSalesPrice();
                int month = current.getMonth();
                int count = 0;
                if(!highestMonth.containsKey(month))
                {
                	highestMonth.put(month,1);
                }
                else
                {
                	count = highestMonth.get(month);
                	count++;
                	highestMonth.put(month, count);
                }
                
            }
            else
            {
                totalReturns++;
                totalSalesPrice = totalSalesPrice - current.getSalesPrice();
            }
        }
        for(int month : highestMonth.keySet())
        {
            int value = highestMonth.get(month);
            if(highestSales < value)
            {
                highestSales = value;
                highestSalesMonth = month;
            }
        }
        if(highestMonth.size() > 0)
        {
        	highestMonthStr = getMonthStr(highestSalesMonth);
        }
        average = (int)totalSalesPrice/12;
        message = "Total Sales: " + totalSalesPrice + " Total Sold: " + total + " Avg Sales: " + average + " Total Returned: "
         + totalReturns + " Best Month: " + highestMonthStr + ": cars sold - " + total; 
        return message;
    }
    //Returns the entire transaction list for displaying purposes
    public String display()
    {
        String message = "";
        for(int i = 0; i < transactionsList.size(); i++)
        {
            Transaction current = transactionsList.get(i);
            String str = current.display();
            message = message + str + "\n";
        }
        return message;
    }
    //returns a string for displaying all the transactions 
    public String displayByMonth(int month)
    {
    	
    	String message = "";
    	for(int i = 0; i < transactionsList.size(); i++)
    	{
    		Transaction current = transactionsList.get(i);
    		if(current.getMonth() == month && current.getType().equals("BUY"))
    		{
    			String str = current.display();
    			message = message + str + "\n";
    		}
    	}
    	return message;
 
    }
    //returns the month based on an integer from 0-11
    public String getMonthStr(int month)
    {
    	return new DateFormatSymbols().getMonths()[month];
    }
}