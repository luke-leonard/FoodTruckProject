package com.skilldistillery.foodtruck;

import java.util.Scanner;

public class FoodTruckApp {

	// F I E L D S
	private static boolean scannerOpen = true;
	private Scanner scanner;
	private FoodTruck[] foodTrucks;
	private int numberOfTrucks = 0;
	boolean mustBeFull = false;

	// C O N S T R U C T O R S
	public FoodTruckApp(int maxFoodTrucks) {
		scanner = new Scanner(System.in);
		foodTrucks = new FoodTruck[maxFoodTrucks];
	}

	// M A I N

	/*
	 * main is pretty simple create a new foodTruck app which takes in a number of
	 * food trucks(for our case, always 5
	 * 
	 * then set the setting which states that a user must enter exactly the maximum
	 * number of trucks(to test putting in less food trucks, just set the value to
	 * false, i did not think this was something to handle with user input)
	 *
	 */
	public static void main(String[] args) {
		FoodTruckApp app = new FoodTruckApp(5);
		app.setMustBeFull(true);
		app.run();
		app.cleanUp();
	}

	// M E T H O D S

	/*
	 * run
	 * 
	 * a cleaver function that makes great use of the boolean outputs of the add
	 * truck and execute menu features
	 * 
	 * it also checks if the foodTrucks array must be full, I added this feature
	 * because of a sort of ambiguous instruction in the assignment. It wanted the
	 * code to quit if the user said quit before entering 5 trucks so my code is
	 * easilly able to handle any number of trucks, however the app can be limited
	 * to require an exact amount
	 * 
	 */
	public void run() {
		System.out.println("Welcome to the food truck app");
		if (mustBeFull) {
			System.out.println("You must enter " + foodTrucks.length + " Trucks");
		} else {
			System.out.println("You may enter up to " + foodTrucks.length + " Trucks");
		}
		while (addNewTruck())
			;
		if (mustBeFull) {
			if (numberOfTrucks != foodTrucks.length) {
				return;
			}
		}
		while (executeMenu())
			;
	}

	/*
	 * addNewTruck
	 * 
	 * this function has a list of prompts and then as the user enters the answer,
	 * the prompts are changed into the answers to them
	 * 
	 * This allows the user to quit at any time not just the name without needing
	 * constant if statements
	 * 
	 * The data is then used to generate a new food truck
	 * 
	 * this returns a boolean if the user quits at any time during the entering
	 */
	public boolean addNewTruck() {
		if (numberOfTrucks < foodTrucks.length) {
			String[] userPrompts = { "Enter Trucks name:", "Enter the Type of Food:", "Enter Rating:" };
			for (int i = 0; i < userPrompts.length; i++) {
				String userInput = getUserString(userPrompts[i]);
				if (userInput.equalsIgnoreCase("QUIT")) {
					return false;
				}
				userPrompts[i] = userInput;
			}
			foodTrucks[numberOfTrucks] = new FoodTruck(userPrompts[0], userPrompts[1],
					Integer.parseInt(userPrompts[2]));
			numberOfTrucks++;
			return true;
		} else {
			return false;
		}
	}

	/*
	 * executeMenu
	 * 
	 * Execute menu displays the menu and then handles the users response
	 *
	 * it returns true as long as the user wants to keep using it. This makes it
	 * able to be easily called
	 * 
	 */
	public boolean executeMenu() {
		printMenu();
		String choice = getUserString("");
		switch (choice) {
			case "1":
				// List all existing food trucks.
				printTrucks();
				break;
			case "2":
				// See the average rating of food trucks.
				printAverageRating();
				break;
			case "3":
				// Display the highest-rated food trucks.
				printHighestRatedFoodTrucks();
				break;
			case "4":
				// Quit the program.
				return false;
			default:
				System.out.println("Entered value was not a selction!");
				break;
		}
		return true;
	}

	/*
	 * getUserString
	 * 
	 * I wanted to do a little error management and make my code a little stronger
	 * 
	 * This function makes sure that the scanner is open and takes in a string from
	 * the user Strings are arguably more robust than other data types because they
	 * wont throw as many exceptions.
	 * 
	 * I have also made it a little easier by making input directly have a prompt
	 * taken in
	 * 
	 */
	public String getUserString(String userPrompt) {
		String retString = "";
		if (scannerOpen) {
			System.out.print(userPrompt);
			retString = scanner.nextLine();
		} else {
			System.out.println("The scanner is closed!");
		}
		return retString;
	}

	// prints the menu
	public void printMenu() {
		System.out.println();
		System.out.println("1) List all existing food trucks.");
		System.out.println("2) See the average rating of food trucks.");
		System.out.println("3) Display the highest-rated food truck.");
		System.out.println("4) Quit the program.");
	}

	// Prints all the trucks
	public void printTrucks() {
		if (numberOfTrucks > 0) {
			for (int i = 0; i < foodTrucks.length; i++) {
				if (foodTrucks[i] != null) {
					System.out.println(foodTrucks[i]);
					System.out.println();
				}
			}
		} else {
			System.out.println("There are no Trucks");
		}
	}

	// A print function for avg ratings
	public void printAverageRating() {
		if (numberOfTrucks > 0) {
			System.out.println("The average rating is " + calculateAverageRating());
		} else {
			System.out.println("There are no trucks");
		}
	}

	/*
	 * calculateAverageRating
	 * 
	 * takes in the rating of all trucks and sums them, then dividing by the total
	 * number
	 * 
	 */
	public double calculateAverageRating() {
		double sum = 0.0;
		for (int i = 0; i < foodTrucks.length; i++) {
			if (foodTrucks[i] != null) {
				sum += foodTrucks[i].getRating();
			}
		}
		return sum / numberOfTrucks;
	}

	// A print function that prints the highest rated truck and error checks no
	// trucks
	public void printHighestRatedFoodTrucks() {
		if (numberOfTrucks != 0) {
			FoodTruck[] topTrucks = reverseStintedBubbleSortTrucksByRating();
			System.out.println("The Highest Rating is " + topTrucks[0].getRating());
			for (int i = 0; i < topTrucks.length; i++) {
				System.out.println(topTrucks[i]);
				System.out.println();
			}
		} else {
			System.out.println("There are no Trucks");
		}
	}

	/*
	 * reverseStintedBubbleSortTrucksByRating
	 * 
	 * A confusing yet accurate name. I wanted to ensure that I printed all food
	 * trucks that had the highest rating, not just the first one. I also did not
	 * want to use any collections as we have not learned them and I really just
	 * wanted to see if I could make a modified bubble sort on my own
	 * 
	 * this function first removes nulls from the array and makes sure the array is
	 * larger than 1(because you cannot perform swaps on an array of size 1)
	 * 
	 * it then iterates through one round of bubble sort instead bringing the
	 * highest value to index 0 (this is why it iterates backwards)
	 * 
	 * it iterates through the list a second time, this time it compares each value
	 * to the already discovered highest value if it is equal to the highest, it
	 * swaps that value for the next-lowest-index available spot(it also makes sure
	 * the next available spot is actually available)
	 * 
	 * essentially it has an index at each end of the array and they work towards
	 * the middle taking any top value and moving it to the front
	 * 
	 * I then copy all values that are the highest and return them
	 * 
	 */
	private FoodTruck[] reverseStintedBubbleSortTrucksByRating() {
		FoodTruck[] cleanedFoodTrucks = cleanFoodTruckList(foodTrucks);
		if (numberOfTrucks == 1) {
			return cleanedFoodTrucks;
		}
		FoodTruck swapTruck;
		int numberOfHighest = 1;
		int largestIndex = cleanedFoodTrucks.length - 1;

		for (int i = largestIndex; i > 0; i--) {
			if (cleanedFoodTrucks[i - 1].getRating() < cleanedFoodTrucks[i].getRating()) {
				swap(cleanedFoodTrucks, i, i - 1);
			}
		}
		for (int i = largestIndex; i >= numberOfHighest;) {
			if (cleanedFoodTrucks[numberOfHighest].getRating() == cleanedFoodTrucks[0].getRating()) {
				numberOfHighest++;
				continue;
			}
			if (cleanedFoodTrucks[i].getRating() == cleanedFoodTrucks[0].getRating()) {
				swap(cleanedFoodTrucks, i, numberOfHighest);
				numberOfHighest++;
			}
			i--;
		}

		FoodTruck[] returnArray = new FoodTruck[numberOfHighest];
		for (int i = 0; i < numberOfHighest; i++) {
			returnArray[i] = cleanedFoodTrucks[i];
		}
		return returnArray;
	}

	// A swap function
	private void swap(FoodTruck[] arr, int index1, int index2) {
		FoodTruck swapTruck = arr[index2];
		arr[index2] = arr[index1];
		arr[index1] = swapTruck;
	}

	/*
	 * cleanFoodTruckList:
	 * 
	 * This Method is used to reassemble the food trucks into an array with no null
	 * values
	 * 
	 * It is only used in the calculation of the highest rated food trucks
	 */
	private FoodTruck[] cleanFoodTruckList(FoodTruck[] arr) {
		FoodTruck[] returnArray = new FoodTruck[numberOfTrucks];
		int currentPosition = 0;
		for (int i = 0; i < foodTrucks.length; i++) {
			if (foodTrucks[i] != null) {
				returnArray[currentPosition++] = foodTrucks[i];
			}
		}
		return returnArray;
	}

	// G E T T E R S _ A N D _ S E T T E R S
	public int getNumberOfTrucks() {
		return numberOfTrucks;
	}

	public void setNumberOfTrucks(int numberOfTrucks) {
		this.numberOfTrucks = numberOfTrucks;
	}

	public boolean isMustBeFull() {
		return mustBeFull;
	}

	public void setMustBeFull(boolean mustBeFull) {
		this.mustBeFull = mustBeFull;
	}

	// D E S T R U C T O R (E X P L I C I T)
	// use to close scanner
	public void cleanUp() {
		if (scannerOpen) {
			scanner.close();
			scannerOpen = false;
		} else {
			System.out.println("The scanner is already closed!");
		}
	}
}
