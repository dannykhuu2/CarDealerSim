//Danny Khuu 
//500 903 037
//April 11 2019


public class ElectricCar extends Car
{	
	//Declaration of instance variables for electric car
    private int rechargeTime;
    private String batteryType;
    //Constructor method utilizing super constructor from Car class along with new instance variables
    public ElectricCar(String m, String c,int model,int p, double safetyRating, int maxRange, boolean AWD, double price, int rechargeTime)
    {
        super(m,c,model,p,safetyRating,maxRange,AWD,price);
        this.rechargeTime = rechargeTime;
        batteryType = "Lithium"; //I think this needs to be declared somehow

    }
    //Returns recharge time
    public int getRechargeTime()
    {
        return rechargeTime;
    }
    //Returns battery type
    public String getBatteryType()
    {
        return batteryType;
    }
    //Sets the recharge time
    public void setRechargeTime(int r)
    {
        rechargeTime = r;
    }
    //Sets the battery type
    public void setBatteryType(String b)
    {
        batteryType = b;
    }
    //Displays the super display method from Car class along with battery type and recharge type
    public String display()
    {
        return super.display() + " BAT: " + batteryType + " RCH: " + rechargeTime;
    }




}   