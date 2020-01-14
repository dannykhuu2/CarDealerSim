//Danny Khuu 
//500 903 037
//April 11 2019


public class Car extends Vehicle implements Comparable<Car>
{	
	//Declaration of instance variables and constants
    private int model;
    private int maxRange;
    private double safetyRating;
    private boolean AWD;
    private double price;
    public static final int SEDAN = 0;
    public static final int SUV = 1;
    public static final int SPORTS = 2;
    public static final int MINIVAN = 3;
    //Constructor method that uses passed in parameters, also makes use of the super call for Vehicle constructor
    public Car(String m, String c, int model, int p, double safetyRating, int maxRange ,boolean AWD, double price)
    {
        super(m,c,p,4);
        this.model = model;
        this.maxRange = maxRange;
        this.safetyRating = safetyRating;
        this.AWD = AWD;
        this.price = price;
    }
    //Displays the display method from Vehicle superclass with instance variables from class Car
    public String display()
    {
        return super.display() + " " + convertConstant(model) + " $" + price + " SF: " + safetyRating + " RNG: " + maxRange;
    }
    //Checks the equity of two objects based on model and AWD
    public boolean equals(Object other)
    {	
    	
        Car otherCar = (Car)other;
        if(super.equals(otherCar))
        {
          return model == otherCar.model  && AWD == otherCar.AWD;
        }
        else
        {
            return false;
        }
    }
    //Returns if AWD
    public boolean getAWD() //Added these for CarDealership filters
    {
        return AWD;
    }
    //Returns the price
    public double getPrice() //Added these for CarDealership filters
    {
        return price;
    }
    //Returns the max range
    public int getMaxRange() // Same
    {
        return maxRange;
    }
    //Returns the safety rating
    public double getSafetyRate() //Same
    {
        return safetyRating;
    }
    //Required method for the Comparable interface, checks which price comes first
    public int compareTo(Car other)
    {
        if(price > other.price)
        {
            return 1;
        }
        else if(price < other.price)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
    //Converts the constants to its String correspondent for display purposes
    public String convertConstant(int constant)
    {
    	if(constant == 0)
    	{
    		return "SEDAN";
    	}
    	else if(constant == 1)
    	{
    		return "SUV";
    	}
    	else if(constant == 2)
    	{
    		return "SPORTS";
    	}
    	else if(constant == 3)
    	{
    		return "MINIVAN";
    	}
    	else
    	{
    		return "Invalid";
    	}
    }



}