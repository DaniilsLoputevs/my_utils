import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class FlatIteratorTest {
    @Test()
    public void whenDiffNext() {
        try {
            Iterator<Iterator<Integer>> data = List.of(
                    List.of(1).iterator(),
                    List.of(2, 3).iterator()
            ).iterator();
            FlatIterator<Integer> flat = new FlatIterator<>(data);
            assertEquals(java.util.Optional.ofNullable(flat.next()).get().intValue(), 1);
            assertEquals(java.util.Optional.ofNullable(flat.next()).get().intValue(), 2);
            assertEquals(java.util.Optional.ofNullable(flat.next()).get().intValue(), 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatIterator<Integer> flat = new FlatIterator<>(data);
        assertEquals(java.util.Optional.ofNullable(flat.next()).get().intValue(), 1);
        assertEquals(java.util.Optional.ofNullable(flat.next()).get().intValue(), 2);
        assertEquals(java.util.Optional.ofNullable(flat.next()).get().intValue(), 3);
    }
    
    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatIterator<Integer> flat = new FlatIterator<>(data);
        assertTrue(flat.hasNext());
        assertTrue(flat.hasNext());
    }
    
    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatIterator<Integer> flat = new FlatIterator<>(data);
        assertEquals(java.util.Optional.ofNullable(flat.next()).get().intValue(), 1);
        assertFalse(flat.hasNext());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(
                List.of().iterator()
        ).iterator();
        FlatIterator<Object> flat = new FlatIterator<>(data);
        flat.next();
    }
    
    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Iterator<?>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of(1).iterator()
        ).iterator();
        FlatIterator flat = new FlatIterator(it);
        assertTrue(flat.hasNext());
        assertEquals(flat.next(), 1);
    }
    
    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Iterator<Object>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator()
        ).iterator();
        FlatIterator flat = new FlatIterator(it);
//        System.out.println(flat.hasNext());
        assertFalse(flat.hasNext());
    }
}