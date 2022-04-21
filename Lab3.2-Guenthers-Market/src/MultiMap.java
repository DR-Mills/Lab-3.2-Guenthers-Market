import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MultiMap {
	static TreeMap<Integer, String> intKeyMap = new TreeMap<>();
	static TreeMap<String, Double> itemKeyMap = new TreeMap<>();

	public MultiMap() {
		InitializeMapData();
	}

	private static void InitializeMapData() {
		addItemToMap("Apple", 2.31);
		addItemToMap("Banana", 0.94);
		addItemToMap("Blueberry", 4.39);
		addItemToMap("Durian", 8.99);
		addItemToMap("Mango", 1.76);
		addItemToMap("Raspberry", 4.91);
		addItemToMap("Broccoli", 1.44);
		addItemToMap("Bok choy", 3.23);
		addItemToMap("Cucumber", 0.62);
		addItemToMap("Kale", 2.23);
	}

	public static void addItemToMap(String str, Double d) {
		int i = intKeyMap.size();
		intKeyMap.put(i + 1, str);
		itemKeyMap.put(str, d);
	}

	public boolean doesItemExist(int n) {
		return intKeyMap.containsKey(n);
	}

	public boolean doesItemExist(String str) {
		return itemKeyMap.containsKey(str);
	}

	public void printMenu() {
		System.out.printf("%n%-8s%-15s%-15s%n", "No.", "Item", "Price");
		System.out.println("=============================");
		String num = "";
		String item = "";
		Double price = 0.0;
		for (Entry<Integer, String> entry : intKeyMap.entrySet()) {
			num = String.valueOf(entry.getKey());
			item = entry.getValue();
			price = itemKeyMap.get(entry.getValue());
			System.out.printf("%-8s%-15s%-15s%n", num, item, price);
		}
	}

}
