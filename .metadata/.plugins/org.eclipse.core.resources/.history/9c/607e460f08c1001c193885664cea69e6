// Exploring options of using a single map with an Integer as the key and a String List as the value.
// Currently abandoned for MultiMap Class instead. One problem with this setup is that if a user enters
// a partial string (i.e. "ry")--due to the use of contains method in String class--it will return the 
// first value with "ry" anywhere in it (i.e. Blueberry). Easy workarounds exist, I'm sure, but the
// MultiMap solution seems more straightforward for now, especially as a new unnamed String[] object
// doesn't need to be created each time. Here, in the entrySet, item.getValue = the memory address so
// only parsing it with Arrays.toString makes it printable (per current programming knowledge :) ).

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SingleMap {

	public SingleMap() { // constructor
		InitializeMapData();
	}
	
	static TreeMap<Integer, String[]> intKeyMap = new TreeMap<>();


	private static void InitializeMapData() {
		intKeyMap.put(1, new String[] { "Apple", "2.31" });
		intKeyMap.put(2, new String[] { "Banana", "0.94" });
		intKeyMap.put(3, new String[] { "Blueberry", "4.39" });
		intKeyMap.put(4, new String[] { "Durian", "8.99" });
		intKeyMap.put(5, new String[] { "Mango", "1.76" });
		intKeyMap.put(6, new String[] { "Raspberry", "4.91" });
		intKeyMap.put(7, new String[] { "Broccoli", "1.44" });
		intKeyMap.put(8, new String[] { "Bok Choy", "3.23" });
		intKeyMap.put(9, new String[] { "Cucumber", "0.62" });
		intKeyMap.put(10, new String[] { "Kale", "2.23" });
	}

	public String returnItemAndPrice(int num) {
		String item = intKeyMap.get(num)[0];
		String price = intKeyMap.get(num)[1];
		return String.format("%-15s$%-15s", item, price);
	}

	public String returnItemAndPrice(String str) {
		str = str.toLowerCase();
		for (Entry<Integer, String[]> item : intKeyMap.entrySet()) {
			String valueArrayAsLowerCaseString = Arrays.toString(item.getValue()).toLowerCase();
			if (valueArrayAsLowerCaseString.contains(str)) {
				int num = item.getKey();
				return returnItemAndPrice(num);
			}
		}
		return null;
	}
	
}