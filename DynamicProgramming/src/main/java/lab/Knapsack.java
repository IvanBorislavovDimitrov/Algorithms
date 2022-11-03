package lab;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Knapsack {

	public static void main(String[] args) throws Throwable {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int knapsackCapacity = Integer.parseInt(bufferedReader.readLine());
		List<Item> items = new ArrayList<>();
		String line;
		while (!"end".equals(line = bufferedReader.readLine())) {
			String[] itemInfo = line.split(" ");
			Item item = new Item();
			item.setName(itemInfo[0]);
			item.setWeight(Integer.parseInt(itemInfo[1]));
			item.setPrice(Integer.parseInt(itemInfo[2]));
			items.add(item);
		}

		int[][] prices = new int[items.size() + 1][knapsackCapacity + 1];
		boolean[][] itemsIncluded = new boolean[items.size() + 1][knapsackCapacity + 1];

		for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {

			Item item = items.get(itemIndex);
			int rowIndex = itemIndex + 1;

			for (int capacity = 0; capacity <= knapsackCapacity; capacity++) {
				if (item.getWeight() > capacity) {
					continue;
				}

				int excluding = prices[rowIndex - 1][capacity];
				int including = item.getPrice() + prices[rowIndex - 1][capacity - item.getWeight()];

				if (including > excluding) {
					prices[rowIndex][capacity] = including;
					itemsIncluded[rowIndex][capacity] = true;
				} else {
					prices[rowIndex][capacity] = excluding;
				}
			}
		}

		List<Item> solutionItems = new ArrayList<>();

		int capacity = knapsackCapacity;

		for (int itemIndex = items.size() - 1; itemIndex >= 0; itemIndex--) {
			if (itemsIncluded[itemIndex + 1][capacity]) {
				Item currentItem = items.get(itemIndex);
				solutionItems.add(currentItem);
				capacity -= currentItem.getWeight();
			}
		}

		System.out.println("Total Weight: " + solutionItems.stream().mapToInt(item -> item.getWeight()).sum());
		System.out.println("Total Value: " + prices[items.size()][knapsackCapacity]);
		solutionItems.stream().sorted().forEach(System.out::println);
	}
}

class Item implements Comparable<Item> {

	private String name;
	private int weight;
	private int price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%s", getName());
	}

	@Override
	public int compareTo(Item item) {
		return getName().compareTo(item.getName());
	}
}