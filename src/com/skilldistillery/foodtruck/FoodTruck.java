package com.skilldistillery.foodtruck;

public class FoodTruck {
	
	// F I E L D S
	private static int nextID = 0;
	private int truckID = -1;

	private String name;
	private String foodType;
	private int rating;

	// C O N S T R U C T O R S
	public FoodTruck(String name, String foodType, int rating) {
		super();
		this.name = name;
		this.foodType = foodType;
		this.rating = rating;
		register();
	}

	// M E T H O D S 
	
	/*
	 * register
	 * 
	 * This function gives each new truck a sequential unique id
	 * 
	 * It also ensure that a truck cannot be given a second Id after it has already
	 * been given one
	 */
	private void register() {
		if (truckID == -1) {
			truckID = nextID++;
		} else {
			System.out.println("Already registered");
		}
	}
	
	// G E T T E R S _ A N D _ S E T T E R S
	public int getTruckID() {
		return truckID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "(name)" + name + ":" + "\n\tTruckID      : " + truckID + "\n\tType of Food : " + foodType
				+ "\n\tRating       : " + rating;
	}
}
