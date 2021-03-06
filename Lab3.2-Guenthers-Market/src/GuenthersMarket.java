
// Task: Make a shopping list application which uses collections to store your items.
// What will the application do?
// * Display a list of at least 8 item names and prices.
// * Ask the user to enter an item name
//     - If that item exists, display that item and price and add that item and its price to
//the user?s order.
//     - If that item doesn?t exist, display an error and re-prompt the user. (Display the
//menu again if you?d like.)
// * Ask if they want to add another. Repeat if they do. (User can enter an item more than
//once; we?re not taking quantity at this point.)
// * When they?re done adding items, display a list of all items ordered with prices in
//columns.
// * Display the average price of items ordered.
//Build Specifications
// * Use a Map to keep track of the menu of items. It should have a String key (for item
//name) and Double value (for item price).
// * Use parallel ArrayLists (one of strings, one of doubles) to store the items ordered and
//their prices.
// * Write 3 methods to find 1) the average cost of the items ordered and the indexes of the
//2) highest and 3) lowest cost items.
//Extended Challenges
// * Implement a menu system so the user can enter just a letter or number for the item.
// * Make a third ArrayList for quantities of items ordered and allow the user to order more
//than one at a time.
//     - Extended: If they order an item already in their cart, increase the quantity rather
//than adding another entry.
// * Display the most and least expensive item ordered.

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GuenthersMarket {
	private int shoppingCartItemsCount = 0;
	private double shoppingCartItemsTotal = 0.00;
	private static final String WELCOME_MSG = "Welcome to Guenther's Market!";
	private static final String PURCHASE_MSG = "What item would you like? (enter No. or item name): ";
	private static final String NO_ITEM_MSG = "Sorry, we don't have that item. Please try again.\n";
	private static final String ORDER_MORE_MSG = "Would you like to order anything else? (y/n): ";

	private static ArrayList<String> shoppingCartItemName = new ArrayList<>();
	private static ArrayList<Double> shoppingCartItemPrice = new ArrayList<>();
	private static ArrayList<Integer> shoppingCartItemQuantity = new ArrayList<>();

	static Scanner scnr = new Scanner(System.in);
	static MultiMap letsShop = new MultiMap();

	public static void main(String[] args) {

		System.out.println(WELCOME_MSG);
		letsShop.printMenu();
		takeOrder();
		totalCart();

	}
	
	// Takes user order, entered as either an int or a String, validates
	// the item exists in the MultiMap, and passes it to the overloaded
	// updateShoppingCart() method.
	// Asks user if they want to order more, if yes, order process repeats
	// if no, loop takeOrder ends.
	private static void takeOrder() {
		boolean notFinishedShopping = true;
		boolean orderIncomplete = true;
		boolean readyToPay = false;
		
// Main loop START
		do {
			do {
				System.out.print(PURCHASE_MSG);
				String userInput = scnr.nextLine();
				
				// attempts to pass int to overloaded doesItemExist() method
				// if input is a string, a NumberFormatExeption is caught
				// and the value is passed as a String instead.
				// if the item does exist, the updateShoppingCart() is called
				// with the int or String value
				try {
					int userInputInt = Integer.valueOf(userInput);
					if (letsShop.doesItemExist(userInputInt)) {
						updateShoppingCart(userInputInt);
						orderIncomplete = false;
					} else {
						System.out.println(NO_ITEM_MSG);
					}
				} catch (NumberFormatException e) {
					userInput = capitalize(userInput);
					if (letsShop.doesItemExist(userInput)) {
						updateShoppingCart(userInput);
						orderIncomplete = false;
					} else
						System.out.println(NO_ITEM_MSG);
				}
				
			} while (orderIncomplete);
			
			orderIncomplete = true;
			
			do {
				System.out.println(ORDER_MORE_MSG);
				String orderAgain = scnr.nextLine();
				
				if (orderAgain.equalsIgnoreCase("y")) {
					notFinishedShopping = false;
					letsShop.printMenu();
				} else if (orderAgain.equalsIgnoreCase("n")) {
					notFinishedShopping = false;
					orderIncomplete = false;
				} else {
					notFinishedShopping = true;
					System.out.println("Please enter \"y\" or \"n\".");
				}
			} while (notFinishedShopping);
		} while (orderIncomplete);
	}

	// Accepts an int value.
	// checks if shoppingCartItemName ArrayList contains the item being purchased.
	// if item does not exist, the item, price, and quantity are all added to the
	// three respective parallel ArrayLists.
	// If the item does exist already, then only the quantity value in
	// shoppingCartItemQuantity is incremented by 1.
	private static void updateShoppingCart(int i) {
		String item = letsShop.intKeyMap.get(i);
		double price = letsShop.itemKeyMap.get(item);
		int itemIndex = shoppingCartItemName.indexOf(item);
		if (!shoppingCartItemName.contains(item)) {
			shoppingCartItemName.add(item);
			shoppingCartItemPrice.add(price);
			shoppingCartItemQuantity.add(1);
		} else {
			int currentItemCount = shoppingCartItemQuantity.get(itemIndex);
			currentItemCount++;
			shoppingCartItemQuantity.set(itemIndex, currentItemCount);
		}
		System.out.println("Adding " + item.toLowerCase() + " to cart at $" + price);
	}

	// Accepts a String value.
	// checks if shoppingCartItemName ArrayList contains the item being purchased.
	// if item does not exist, the item, price, and quantity are all added to the
	// three respective parallel ArrayLists.
	// If the item does exist already, then only the quantity value in
	// shoppingCartItemQuantity is incremented by 1.
	private static void updateShoppingCart(String str) {
		double price = letsShop.itemKeyMap.get(str);
		int itemIndex = shoppingCartItemName.indexOf(str);
		if (!shoppingCartItemName.contains(str)) {
			shoppingCartItemName.add(str);
			shoppingCartItemPrice.add(price);
			shoppingCartItemQuantity.add(1);
		} else {
			int currentItemCount = shoppingCartItemQuantity.get(itemIndex);
			currentItemCount++;
			shoppingCartItemQuantity.set(itemIndex, currentItemCount);
		}
		System.out.println("Adding " + str + " to cart at $" + price);
	}

	//calculates shopping cart item price and quantity total and
	// prints the average to the console
	// also prints index of highest and lowest priced items in cart.
	public static void totalCart() {
		double total = 0.0;
		for (double d : shoppingCartItemPrice) {
			total = total + d;
		}
		int quantity = 0;
		for (int i : shoppingCartItemQuantity) {
			quantity = quantity + i;
		}
		double average = total / quantity;
		System.out.printf("%s$%.2f%n", "Average price per item in order was ", average);
		System.out.println("The index of the highest priced item in cart is " + returnIndexOfHighItem() + ".");
		System.out.println("The index of the lowest priced item in cart is " + returnIndexOfLowItem() + ".");
	}

	public static int returnIndexOfHighItem() {
		double highest = Collections.max(shoppingCartItemPrice);
		return shoppingCartItemPrice.indexOf(highest);
	}
	
	public static int returnIndexOfLowItem() {
		double lowest = Collections.min(shoppingCartItemPrice);
		return shoppingCartItemPrice.indexOf(lowest);
	}
	public static String capitalize(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
	}

}
