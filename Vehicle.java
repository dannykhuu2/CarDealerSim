//Danny Khuu 
//500 903 037
//April 11 2019


public class Vehicle 
{	
	//Declaration of neccesary instance variables and constants
    private String mfr;
    private String color;
    private int power;
    private int numWheels;
    private int VIN;
    public static final int ELECTRIC_MOTOR = 0;
    public static final int GAS_ENGINE = 1; 
    
    //Constructor method that initializes instance varaibles
    public Vehicle(String m, String c, int p, int n)
    {
        mfr = m;
        color = c;
        power = p;
        numWheels = n;
        VIN = (int)(Math.random()*400)+100;
    }
    //Returns the manufacturer 
    public String getMfr()
    {
        return mfr;
    }
    //Returns the color
    public String getColor()
    {
        return color;
    }
    //Returns the power type in the form of integer
    public int getPower()
    {
        return power;
    }
    //Returns the number of wheels
    public int getNumWheels()
    {
        return numWheels;
    }
    //Sets the manufacturer
    public void setMfr(String m)
    {
        mfr = m;
    }
    //Sets the color 
    public void setColor(String c)
    {
        color = c;
    }
    //Sets the power type based on an integer
    public void setPower(int p)
    {
        power = p;
    }
    //Sets the number of wheels
    public void setNumWheels(int n)
    {
        numWheels = n;
    }
    //Checks the equity of two different objects
    public boolean equals(Object other)
    {
        Vehicle otherVehicle = (Vehicle)other;
        return mfr.equals(otherVehicle.mfr) && power == otherVehicle.power && numWheels == otherVehicle.numWheels;

    }
    public int getVIN()
    {
        return VIN;
    }
    //Displays the manufacturer and color in a string
    public String display()
    {
        return "VIN: " + VIN + " " + mfr + " " + color;
    }

}   