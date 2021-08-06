package experiments;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;

import static experiments.RuntimeEntitySearch.findAndShow;

public class RuntimeEntitySearch {
    private static final Map<String, Function<String, List<?>>> patterns = new HashMap<>();
    public static EntityStore<NPC> npc = new EntityStore<>();
    public static EntityStore<WPN> wpn = new EntityStore<>();
    
    static {
        new NPC(0, "player", new WPN(0, "AWP", 1000.00));
        new NPC(1, "enemy", new WPN(1, "AK-47", 67.00));
        new NPC(2, "enemy-1", new WPN(2, "AWP", 1000.00));
    }
    
    {
        patterns.put("npc:1", (arg) -> npc.find(npc -> npc.id == Integer.parseInt(arg)));
        patterns.put("npc:n", (arg) -> npc.find(npc -> npc.name.equals(arg)));
        patterns.put("npc:w", (arg) -> npc.find(npc -> npc.wpn.name.equals(arg)));
        
        patterns.put("wpn:n", (arg) -> wpn.find(wpn -> wpn.name.equals(arg)));
    }
    
    public static List<?> find(String pattern) {
        
        var args = pattern.split(" ");
        return patterns.get(args[0]).apply(args[1]);
    }
    
    public static <T> List<T> find(String pattern, Class<T> tClass) {
        return (List<T>) find(pattern);
    }
    
    public static void findAndShow(String pattern) {
        var rsl = new StringJoiner(System.lineSeparator());
        for (var entity : find(pattern)) {
            rsl.add(entity.toString());
        }
        System.out.println(rsl.toString());
    }
    
    
    public static void main(String[] args) throws InterruptedException {
//        var debug = new RuntimeEntitySearch();

//        System.out.println("player = " + debug.find("npc:n player", NPC.class));
//        System.out.println("enemy = " + debug.find("npc:w AWP"));
//        System.out.println("weapon = " + debug.find("wpn:n AK-47"));
//
//    var scn = new Scanner(System.in);
//        System.out.println(debug.find(scn.nextLine()).getClass().getName());
        
        
        var debug = new Debug();
        debug.AsyncStart();
        
        new NPC(0, "player", new WPN(0, "AWP", 1000.00));
        new NPC(1, "enemy", new WPN(1, "AK-47", 67.00));
        new NPC(2, "enemy-1", new WPN(2, "AWP", 1000.00));

//        Thread.sleep(1000_000_000L);
        
        var time = Long.valueOf(Long.MIN_VALUE);
        
        while (time != Long.MAX_VALUE) {
            time += 1;
        }
        
    }
}

class Debug {
    
    public void AsyncStart() {
        new Thread(() -> {
            var consoleScanner = new Scanner(System.in);
            while (true) {
                var line = consoleScanner.nextLine();
                if (!line.isEmpty()) {
                    CompletableFuture.runAsync(() -> {
                        findAndShow(line);
                    });
                }
            }
        }, "Debug Thread").start();
    }
}

class NPC {
    int id;
    String name;
    WPN wpn;
    
    public NPC(int id, String name, WPN wpn) {
        this.id = id;
        this.name = name;
        this.wpn = wpn;
        RuntimeEntitySearch.npc.put(this);
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", NPC.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("wpn=" + wpn)
                .toString();
    }
}

class WPN {
    int id;
    String name;
    Double damage;
    
    public WPN(int id, String name, Double damage) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        RuntimeEntitySearch.wpn.put(this);
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", WPN.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("damage=" + damage)
                .toString();
    }
}

class EntityStore<T> {
    private final List<T> entities = new ArrayList<>();
    
    
    public void put(T entity) {
        entities.add(entity);
    }
    
    public List<T> find(Predicate<T> predicate) {
        var rsl = new ArrayList<T>();
        for (var entity : entities) {
            if (predicate.test(entity)) rsl.add(entity);
        }
        return rsl;
    }
    
}
