//Danny Khuu 
//500 903 037
//April 11 2019

import java.util.*;

public class CarDealership
{	
	//Declaration of instance variables
    private ArrayList<Car> cars;
    private SalesTeam sales;
    private AccountingSystem account;
    private boolean AWD = false;
    private boolean electric = false;
    private boolean price = false;
    private double max;
    private double min;
    //Default constructor method that initializes arraylist of Car objects
    public CarDealership()
    {
        ArrayList<Car> newCars = new ArrayList<Car>();
        cars = newCars; 
        sales = new SalesTeam();
        account = new AccountingSystem();

    }
    //Adds an Arraylist of cars to instance variable Arraylist cars
    public void addCars(ArrayList<Car> newCars)
    {
        for(int i = 0; i < newCars.size(); i++)
        {
            cars.add(newCars.get(i));
        }
    }
    //Removes the car indicated by VIN and returns a reference to that car, otherwise return null
    public String buyCar(int VIN)
    {
        Car ref = null;
        String message = "";
        double priceCar = 0;
        Calendar calendar = new GregorianCalendar();
        int year = 2019;
        int month = (int)(Math.random()*12);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int day = (int)(Math.random()* (max +1)) + 1;
        calendar.set(Calendar.DAY_OF_MONTH, day);
        if(VIN >= 100 && VIN <= 499 && cars.size() > 0)
        {   
            int index = 0;
            String member = "";
            Car current = null;
            for(int i = 0; i < cars.size(); i++)
            {   
                current = cars.get(i);
                if(current.getVIN() == VIN)
                {
                    index = i;
                    ref = cars.get(index);
                    cars.remove(index);
                    member = sales.getSalesMember();
                    priceCar = current.getPrice();
                    message = account.add(calendar,ref,member,"BUY",priceCar); 
                    break;
                }
            }
        }
        return message; //Can put a condition for else, for invalid input after
        
    }
    //Adds the previously returned referenced of car from buyCar method
    public String returnCar(String transactionStr)
    {   
    	String message = "";
        if(!transactionStr.equals("") && !transactionStr.equals(null)) 
        {   
            Scanner scanner = new Scanner(transactionStr);
            scanner.next();
            int ID = scanner.nextInt();
            Transaction transactionCar = account.getTransaction(ID);
            Calendar date = transactionCar.getDate();
            Calendar newDate = new GregorianCalendar();
            int day = date.get(Calendar.DAY_OF_MONTH);
            int diff = date.getActualMaximum(Calendar.DAY_OF_MONTH) - day;
            int newDay = (int)(Math.random()* diff + 1) + day;
            newDate.set(Calendar.DAY_OF_MONTH, newDay);
            newDate.set(Calendar.MONTH, date.get(Calendar.MONTH));
            newDate.set(Calendar.YEAR, 2019);
            Car reCar = transactionCar.getCar();
            String newSales = sales.getSalesMember();
            message = account.add(newDate,transactionCar.getCar(), newSales, "RET", transactionCar.getSalesPrice());
            cars.add(reCar);
            scanner.close();
        }
        return message;
    }
    //Displays inventory of Arraylist cars based on multiple filters that can be applied at once
    public void displayInventory()
    {
        for(int i = 0; i < cars.size(); i++)
        {
            boolean satisfied = true;
            Car current = cars.get(i);
            if(electric == true)
            {
                if(current.getPower() == 1)
                {
                    satisfied = false;
                }
            }
            if(AWD == true)
            {
                if(current.getAWD() == false)
                {
                    satisfied = false;
                }
            }
            if(price == true)
            {
                if(current.getPrice() < min || current.getPrice() > max)
                {
                    satisfied = false;
                }
            }
            //If the boolean satisfied is not triggered to be false, the specific car object is displayed
            if(satisfied == true)
            {   
                
                System.out.println(current.display());
            }
        }
    }
    //Set electric filter
    public void filterByElectric()
    {
        electric = true;
    }
    //Set AWD filter
    public void filterByAWD()
    {
        AWD = true;
    }
    //Set price filter, which takes input of min and max price
    public void filterByPrice(double minPrice, double maxPrice)
    {
        price = true;
        min = minPrice;
        max = maxPrice;
    }
    //Clears all the filters, setting back to false
    public void filtersClear()
    {
        electric = false;
        AWD = false;
        price = false;
        min = 0.0;
        max = 0.0;
        
    }
    //Sort by price using interface Comparable
    public void sortByPrice()
    {
        Collections.sort(cars);
    }
    //Creating inner class that implements comparator to sort by safety rate
    class compareBySafety implements Comparator<Car>
    {
        public int compare(Car a, Car b)
        {
            if(a.getSafetyRate() > b.getSafetyRate())
            {
                return -1;
            }
            else if(a.getSafetyRate() < b.getSafetyRate())
            {
                return 1;
            }
            else
            {
            	return 0;
            }
        }
    }
    //Sort by safety rating using inner class compareBySafety
    public void sortBySafetyRating()
    {
        Collections.sort(cars,new compareBySafety());
    }
    //Creating inner class that implements comparator to sort by max range
    class compareByMaxRange implements Comparator<Car>
    {
        public int compare(Car a, Car b)
        {
            if(a.getMaxRange() > b.getMaxRange())
            {
                return -1;
            }
            else if(a.getMaxRange() < b.getMaxRange())
            {
                return 1;
            }
            else
            {
                return 0; 
            }
        }
    }
    //Sort by max range using inner class compareByMaxRange
    public void sortByMaxRange()
    {
        Collections.sort(cars,new compareByMaxRange());
    }
    public int amountOfCars()
    {
    	return cars.size();
    }
    public AccountingSystem getAccountObject()
    {
        return account;
    }
    public SalesTeam getTeam()
    {
    	return sales;
    }

}