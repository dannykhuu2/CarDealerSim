//Danny Khuu
//500 903 037
//April 11 2019

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction
{
    private int ID;
    private Car carObj;
    private String salesName;
    private String transactionType;
    private Calendar date;
    private SimpleDateFormat formating;
    private double salePrice;
    //Constructor method for Transaction
    public Transaction(int ID, Car carObj, String salesName, String transactionType, Calendar date, double salePrice)
    {   
        this.ID = ID;
        this.carObj = carObj;
        this.salesName = salesName;
        this.transactionType = transactionType;
        this.date = date;
        this.salePrice = salePrice;
        formating = new SimpleDateFormat("EEE MMM dd, yyyy");
    }
    //returns a string for displaying purposes
    public String display()
    {
        String message = "";
        String dateStr = formating.format(date.getTime());
        String newDateStr = "";
        for(int i = 0; i < dateStr.length(); i ++)
        {
        	char ch = dateStr.charAt(i);
        	if(ch == '.')
        	{
        		ch = ',';
        	}
        	newDateStr = newDateStr + ch;
        	
        }
        message = "ID: " +  ID + " " + newDateStr + " " + transactionType + " SalesPerson: " + salesName + " Car: " + carObj.display();
        return message;
    }
    //Returns the ID 
    public int getID()
    {
        return ID;
    }
    //Returns the carObj
    public Car getCar()
    {
        return carObj;
    }
    //Returns the date
    public Calendar getDate()
    {
        return date;
    }
    //Returns the sales person
    public String getSalesName()
    {
        return salesName;
    }
    //Returns the sales price
    public double getSalesPrice()
    {
        return salePrice;
    }
    //Returns the month 
    public int getMonth()
    {
        return date.get(Calendar.MONTH);
    }
    //Returns the type of transaction
    public String getType()
    {
        return transactionType;
    }
}   