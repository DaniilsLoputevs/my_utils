package satomi;

import org.junit.Before;
import org.junit.Test;

public class SimpleHashMapTest {
    
    private final SimpleHashMap<String, User> map = new SimpleHashMap<>();
    private final User one = new User("Petr", 2);
    private final User two = new User("Ivan", 3);
    private final User three = new User("Fedor", 5);
    
    @Before
    public void setUp() {
//        map.insert("Первый", one);
//        map.insert("Второй", two);
//        map.insert("Третий", three);
    }

    
    @Test
    public void insert() {
//        assertEquals(3, map.size());
    }

    @Test
    public void get() {
//        assertEquals(one, map.get("Первый"));
    }

    @Test
    public void delete() {
//        assertEquals(one, map.get("Первый"));
//        assertEquals(3, map.size());
//
//        map.delete("Первый");
//
//        assertNull(map.get("Первый"));
//        assertEquals(2, map.size());
    }

    @Test
    public void numberInKey() {
//        SimpleHashMap<Integer, String> numMap = new SimpleHashMap<>();
//        numMap.insert(10, "one");
//        numMap.insert(35, "two");
//        numMap.insert(315, "three");
//
//        assertEquals("one", numMap.get(10));
//        assertEquals("three", numMap.get(315));
    }
    
    
    /**
     * map.showTable - вспомогательный метод для Дебага.
     * Были проблемы с итератором.
     */
    @Test
    public void iterator() {
//        Iterator<User> iter = map.iterator();
//
////        for (var s : map) {
////            System.out.println("map iter val: " + s);
////        }
//
//        assertTrue(iter.hasNext());
//        assertNotNull(iter.next());
//
//        assertTrue(iter.hasNext());
//        assertNotNull(iter.next());
//
//        assertTrue(iter.hasNext());
//        assertNotNull(iter.next());
//
//        assertFalse(iter.hasNext());
    }
    
}