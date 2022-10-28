import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {

    public static Random random = new Random();
	 
	
	private static String[] nameDrinks = {"Coke", "Ayran","Fanta"};  //max 1 içecek olacak şekilde random yemek üretiyor
	
	public static synchronized List<Drinks> createDrink(){
		List<Drinks> drink = new ArrayList<>();
		
		int numberOfDrinks = random.nextInt(2);
	
		for(int i =0; i< numberOfDrinks ; i++) {
		Drinks drinks = new Drinks(nameDrinks[random.nextInt(3)] , 20);
		drink.add(drinks);
		}
		return drink;
	}

public static String[] nameFood = {"Burger", "Pizza", "Fries"}; //max 2 yemek olacak şekilde random yemek üretiyor
	
	public static synchronized List<Food> createFood(){
		List<Food> foods = new ArrayList<>();
		int numberOfFoods = random.nextInt(2)+1;
		for(int i =0; i< numberOfFoods ; i++) {
			
			Food food = new Food(nameFood[random.nextInt(3)] , 80);
			foods.add(food);
		}
		return foods;
	}
    
}
