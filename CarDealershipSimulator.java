//Danny Khuu 
//500 903 037
//April 11, 2019

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class CarDealershipSimulator 
{
  public static void main(String[] args) 
  {
	    //A CarDealership object, ArrayList of Cars and AccountingSystem object is created
		CarDealership carDeal = new CarDealership();
		ArrayList<Car> newCarsSim = new ArrayList<Car>();
		AccountingSystem account = carDeal.getAccountObject();
		try
		{
			//One scanner to read the whole line of file
			Scanner scannerFileLine = new Scanner(new File("cars.txt"));
			while(scannerFileLine.hasNextLine())
			{
				String fileLine = scannerFileLine.nextLine();
				//Second scnaner for reading individual input from line
				Scanner scannerInput = new Scanner(fileLine);
				int carType = 0;
				int engineType = 0;
				boolean awd = true;
				//Arraylist to hold the parameters for adding cars from text file
				ArrayList<String> parameters = new ArrayList<String>();
				//Reads all the parameters required for building the car and electric car objects, also checks certain parameters for matching constants
				while(scannerInput.hasNext())
				{
					String input = scannerInput.next();
					parameters.add(input);
				}
				//Specific indexes of the arrayList indicate the different parameters stored
				if(parameters.get(2).equals("SEDAN"))
				{
					carType = 0;
				}
				else if(parameters.get(2).equals("SUV"))
				{
					carType = 1;
				}
				else if(parameters.get(2).equals("SPORTS"))
				{
					carType = 2;
				}
				else if(parameters.get(2).equals("MINIVAN"))
				{
					carType = 3;
				}
				if(parameters.get(3).equals("GAS_ENGINE"))
				{
					engineType = 1;
				}
				else if(parameters.get(3).equals("ELECTRIC_MOTOR"))
				{
					engineType = 0;
				}
				if(parameters.get(6).equals("2WD"))
				{
					awd = false;
				}
				else if(parameters.get(6).equals("AWD"))
				{
					awd = true;
				}
				if(engineType == 1)
				{
					newCarsSim.add(new Car(parameters.get(0),parameters.get(1),carType,engineType,Double.parseDouble(parameters.get(4)),Integer.parseInt(parameters.get(5)),awd,Integer.parseInt(parameters.get(7))));
				}
				else if(engineType == 0)
				{
					newCarsSim.add(new ElectricCar(parameters.get(0),parameters.get(1),carType,engineType,Double.parseDouble(parameters.get(4)),Integer.parseInt(parameters.get(5)),awd,Integer.parseInt(parameters.get(7)),Integer.parseInt(parameters.get(8))));
				}
				scannerInput.close();
			}
			scannerFileLine.close();
		//catch is executed if the try block causes an exception
		}
		catch(IOException | NumberFormatException | IndexOutOfBoundsException e)
		{
			System.out.println(e);
		}


		//Creates the first scanner that takes the line of command
		System.out.println("Enter a command: ");
		Scanner scanner = new Scanner(System.in);
		String commandLine = "";
		String input = "";
		String transactionStr = "";
		String returnCar = "";
		boolean hasReturn = false;
		
		
		//Do while loop until the input from user is "Q" or "q"
		do
		{	
			input = "";
			commandLine = scanner.nextLine();
			if(!commandLine.isEmpty() && !commandLine.equals(" "))
			{
				//Second scanner is created to read the individual words
				Scanner scanner2 = new Scanner(commandLine);
				try 
				{
					input = scanner2.next();
				}
				//Catch prevents the commanndLine taking a value of blank spaces
				catch (NoSuchElementException e)
				{
					
				}
				if(input.equalsIgnoreCase("L"))
				{
					if(carDeal.amountOfCars() > 0)
					{
						carDeal.displayInventory();
					}
					else
					{
						System.out.println("No inventory: ");
					}

				}
				//Buys from arraylist, thus removing car object based on index
				else if(input.equalsIgnoreCase("BUY")) 
				{
					if(scanner2.hasNextInt())
					{
						int input3 = Integer.parseInt(scanner2.next());
						transactionStr = carDeal.buyCar(input3); //Take this out?
						if(transactionStr.equals("") || transactionStr.equals(null))
						{
							System.out.println("Invalid Input: ");
						} 
						else
						{
							System.out.println(transactionStr);
							returnCar = transactionStr;
							hasReturn = true;
						}
					}
					else
					{
						System.out.println("Invalid Input: ");
					}		

				}
				//Returns previously bought car
				else if(input.equalsIgnoreCase("RET"))
				{
					if(hasReturn == true && !returnCar.equals(""))
					{
						String message = carDeal.returnCar(returnCar);
						System.out.println(message);
						returnCar = "";
						hasReturn = false;
					}
					else
					{
						System.out.println("Can not return: ");
					}
				}
				//Adds the arraylist newCarsSim to arraylist contained in CarDealership class
				else if(input.equalsIgnoreCase("ADD"))
				{
					carDeal.addCars(newCarsSim);
				}
				//Price sort (All sorts by ascending)
				else if(input.equalsIgnoreCase("SPR"))
				{
					carDeal.sortByPrice();
				}
				//Safety Rating sort
				else if(input.equalsIgnoreCase("SSR"))
				{
					carDeal.sortBySafetyRating();
				}
				//Max range sort
				else if(input.equalsIgnoreCase("SMR"))
				{
					carDeal.sortByMaxRange();
				}
				//Clears all filters, back to false
				else if(input.equalsIgnoreCase("FCL"))
				{
					carDeal.filtersClear();
				}
				//Filter by price
				else if(input.equalsIgnoreCase("FPR"))
				{	
					//Checks the validity of the min and max parameters
					if(scanner2.hasNextInt())
					{
						int input3 = Integer.parseInt(scanner2.next());
						if(scanner2.hasNextInt())
						{
							int input4 = Integer.parseInt(scanner2.next());
							if(input3 < input4)
							{
								carDeal.filterByPrice(input3, input4);
							}
							else
							{
								System.out.println("Invalid: ");
							}
						}
						else
						{
							System.out.println("Invalid: ");
						}
					}
					else 
					{
						System.out.println("Invalid: ");
					}
					
				}
				//Filter by electric power
				else if(input.equalsIgnoreCase("FEL"))
				{
					carDeal.filterByElectric();
				}
				//filter by AWD
				else if(input.equalsIgnoreCase("FAW"))
				{
					carDeal.filterByAWD();
				}
				//If any of the sales commands are executed, goes through this if block
				else if(input.equalsIgnoreCase("SALES"))
				{
					//Checks for a next input, then this determines which sales command is executed
					if(scanner2.hasNextInt())
					{
						int m = scanner2.nextInt();
						String message = account.displayByMonth(m);
						System.out.println(message);
					}
					else if(scanner2.hasNext())
					{
						String nextStr = scanner2.next();
						if(nextStr.equalsIgnoreCase("TEAM"))
						{
							SalesTeam sales = carDeal.getTeam();
							System.out.println(sales.displayAll());
						}
						else if(nextStr.equalsIgnoreCase("TOPSP")) 
						{
							String message = account.findTopSP();
							System.out.println(message);
						}
						else if(nextStr.equalsIgnoreCase("STATS"))
						{
							String message = account.findStats();
							System.out.println(message);
						}
					}
					else
					{
						String message = account.display();
						System.out.println(message);
					}
				}
				//otherwise, the command is unknown
				else
				{	
					if(!input.equalsIgnoreCase("Q"))
					{
						System.out.println("Unknown Command: ");
					}
					else
					{
						System.out.println("Program Ended: ");
					}
				}
				//Closes the scanner once done, so that multiple commands in same row are not executed
				scanner2.close();
			}
			else
			{
				System.out.println("Invalid: ");
			}
			
		}
		while(!input.equalsIgnoreCase("Q"));
		scanner.close();
		
	
  }
}