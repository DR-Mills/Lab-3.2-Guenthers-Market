// Task: Make a shopping list application which uses collections to store your items.
// What will the application do?
// * Display a list of at least 8 item names and prices.
// * Ask the user to enter an item name
//     - If that item exists, display that item and price and add that item and its price to
//the user�s order.
//     - If that item doesn�t exist, display an error and re-prompt the user. (Display the
//menu again if you�d like.)
// * Ask if they want to add another. Repeat if they do. (User can enter an item more than
//once; we�re not taking quantity at this point.)
// * When they�re done adding items, display a list of all items ordered with prices in
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


public class GuenthersMarket {

	public static void main(String[] args) {
MultiMap letsShop = new MultiMap();

String item1 = letsShop.callItem(1);
String item2 = letsShop.callItem("Banana");

System.out.println(item1);
System.out.println(item2);
letsShop.printMenu();




		

	}

}
