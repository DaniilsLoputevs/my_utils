package satomi.foods;

import satomi.foods.food.Bread;
import satomi.foods.foodStorage.Shop;
import satomi.foods.foodStorage.Trash;
import satomi.foods.foodStorage.WareHouse;

import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Calendar.AUGUST;

/**
 * Test merge int current
 */
public class MainService {
    private final List<FoodStore> storages = List.of(
            new Shop(),
            new WareHouse(),
            new Trash()
    );
    
    public static void main(String[] args) {
        //
        
        var mainService = new MainService();
        
        var start = new GregorianCalendar(2021, AUGUST, 1);
        var end = new GregorianCalendar(2021, AUGUST, 5);
        var bread = new Bread("Местный", start, end, 10.0, 0.0);
    
        System.out.println("quality = " + bread.getQualityPercents());
        
        mainService.add(bread);
    }
    
    public void add(Food food) {
        for (var s : storages) {
            if (s.isValidFood(food)) {
                s.add(food);
                break;
            }
        }
    }
    
    /**
     * Еда Перераспределение
     */
    public void foodRedistribution() {
        var allFoods = storages.stream()
                .flatMap(s -> s.getAll().stream());
        storages.forEach(Storage::clear);
        allFoods.forEach(this::add);
    }
}
