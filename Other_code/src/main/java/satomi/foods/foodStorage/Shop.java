package satomi.foods.foodStorage;

import satomi.foods.Food;
import satomi.foods.FoodStore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 1. Хранилище продуктов [#852]
 * *  Уровень : 2. Джуниор Категория : 2.5. ООД Топик : 2.5.3. LSP
 * *  создать классы хранилище продуктов Warehouse, Shop, Trash.
 */
public class Shop implements FoodStore {
    private final List<Food> foodList = new ArrayList<>();
    
    @Override
    public boolean add(Food food) {
        return foodList.add(food);
    }
    
    @Override
    public boolean delete(Food food) {
        return foodList.remove(food);
    }
    
    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return foodList.stream().filter(filter).collect(Collectors.toList());
    }
    
    @Override
    public List<Food> getAll() {
        return new ArrayList<>(foodList);
    }
    
    @Override
    public void clear() {
        foodList.clear();
    }
    
    @Override
    public boolean isValidFood(Food food) {
        return food.getQualityPercents() > 0.75;
    }
}
