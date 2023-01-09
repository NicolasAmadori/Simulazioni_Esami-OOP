package a02a.e1;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class DietFactoryImpl implements DietFactory{
    private static final int DEFAULT_FOODS_QUANTITY = 100;

    @Override
    public Diet standard() {
        final int CALORIES_LOWER_BOUND = 1500;
        final int CALORIES_HIGHER_BOUND = 2000;
        return new Diet() {

            Map<String, Map<Nutrient, Integer>> foodsValues = new HashMap<>();

            private int getTotalCalories(String foodName){
                return foodsValues.get(foodName).get(Nutrient.CARBS) + foodsValues.get(foodName).get(Nutrient.PROTEINS) + foodsValues.get(foodName).get(Nutrient.FAT);
            }

            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                foodsValues.put(name, nutritionMap);
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                int totalCalories = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * getTotalCalories(e.getKey()))
                    .reduce((a,b) -> a + b)
                    .get().intValue();
                return totalCalories >= CALORIES_LOWER_BOUND && totalCalories <= CALORIES_HIGHER_BOUND;
            }
            
        };
    }

    @Override
    public Diet lowCarb() {
        final int CALORIES_LOWER_BOUND = 1000;
        final int CALORIES_HIGHER_BOUND = 1500;
        final int CARBS_UPPER_BOUND = 300;
        return new Diet() {

            Map<String, Map<Nutrient, Integer>> foodsValues = new HashMap<>();

            private int getTotalCalories(String foodName){
                return foodsValues.get(foodName).get(Nutrient.CARBS) + foodsValues.get(foodName).get(Nutrient.PROTEINS) + foodsValues.get(foodName).get(Nutrient.FAT);
            }

            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                foodsValues.put(name, nutritionMap);
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                int totalCalories = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * getTotalCalories(e.getKey()))
                    .reduce((a,b) -> a + b)
                    .get().intValue();
                int totalCarbs = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * foodsValues.get(e.getKey()).get(Nutrient.CARBS))
                    .reduce((a,b) -> a + b)
                    .get().intValue();
                return totalCalories >= CALORIES_LOWER_BOUND && totalCalories <= CALORIES_HIGHER_BOUND && totalCarbs <= CARBS_UPPER_BOUND;
            }
            
        };
    }

    @Override
    public Diet highProtein() {
        final int CALORIES_LOWER_BOUND = 2000;
        final int CALORIES_HIGHER_BOUND = 2500;
        final int CARBS_UPPER_BOUND = 300;
        final int PROTEIN_LOWER_BOUND = 1300;
        return new Diet() {

            Map<String, Map<Nutrient, Integer>> foodsValues = new HashMap<>();

            private int getTotalCalories(String foodName){
                return foodsValues.get(foodName).get(Nutrient.CARBS) + foodsValues.get(foodName).get(Nutrient.PROTEINS) + foodsValues.get(foodName).get(Nutrient.FAT);
            }

            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                foodsValues.put(name, nutritionMap);
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                int totalCalories = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * getTotalCalories(e.getKey()))
                    .reduce((a,b) -> a + b)
                    .get().intValue();
                int totalCarbs = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * foodsValues.get(e.getKey()).get(Nutrient.CARBS))
                    .reduce((a,b) -> a + b)
                    .get().intValue();

                int totalProteins = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * foodsValues.get(e.getKey()).get(Nutrient.PROTEINS))
                    .reduce((a,b) -> a + b)
                    .get().intValue();
                return totalCalories >= CALORIES_LOWER_BOUND && totalCalories <= CALORIES_HIGHER_BOUND && totalCarbs <= CARBS_UPPER_BOUND && totalProteins >= PROTEIN_LOWER_BOUND;
            }
            
        };
    }

    @Override
    public Diet balanced() {
        final int CALORIES_LOWER_BOUND = 1600;
        final int CALORIES_HIGHER_BOUND = 2000;
        final int CARBS_LOWER_BOUND = 600;
        final int PROTEIN_LOWER_BOUND = 600;
        final int FAT_LOWER_BOUND = 400;
        final int PROTEIN_AND_FAT_UPPER_BOUND = 1100;
        return new Diet() {

            Map<String, Map<Nutrient, Integer>> foodsValues = new HashMap<>();

            private int getTotalCalories(String foodName){
                return getFoodCarbs(foodName) + getFoodProteins(foodName) + getFoodFat(foodName);
            }

            private int getFoodCarbs(String foodName){
                return foodsValues.get(foodName).get(Nutrient.CARBS);
            }

            private int getFoodProteins(String foodName){
                return foodsValues.get(foodName).get(Nutrient.PROTEINS);
            }

            private int getFoodFat(String foodName){
                return foodsValues.get(foodName).get(Nutrient.FAT);
            }

            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                foodsValues.put(name, nutritionMap);
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                int totalCalories = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * getTotalCalories(e.getKey()))
                    .reduce((a,b) -> a + b)
                    .get().intValue();
                int totalCarbs = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * getFoodCarbs(e.getKey()))
                    .reduce((a,b) -> a + b)
                    .get().intValue();

                int totalProteins = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * getFoodProteins(e.getKey()))
                    .reduce((a,b) -> a + b)
                    .get().intValue();
                
                int totalFat = dietMap.entrySet().stream()
                    .map(e -> e.getValue() / DEFAULT_FOODS_QUANTITY * getFoodFat(e.getKey()))
                    .reduce((a,b) -> a + b)
                    .get().intValue();

                return totalCalories >= CALORIES_LOWER_BOUND 
                    && totalCalories <= CALORIES_HIGHER_BOUND 
                    && totalCarbs >= CARBS_LOWER_BOUND 
                    && totalProteins >= PROTEIN_LOWER_BOUND 
                    && totalFat >= FAT_LOWER_BOUND 
                    && (totalFat + totalProteins) <= PROTEIN_AND_FAT_UPPER_BOUND;
            }
            
        };
    }
    
}
