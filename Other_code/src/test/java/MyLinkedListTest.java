import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MyLinkedListTest extends MyArrayListTest {

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<Integer>();
        list.add(1); // 0
        list.add(2); // 1 2
        list.add(3); // 2 3
        
        myList = new MyLinkedList<Integer>();
        myList.addAll(list);
    }
    
    @After
    public void tearDown() throws Exception {
        list = new ArrayList<>();
        myList = new MyLinkedList<>();
    }
    
    /**
     * Test method for {@link MyLinkedList#indexOf(Object)}.
     */
    @Test(expected = NullPointerException.class)
    public void testIndexOf1() { //throws Exception
        assertThat(myList.indexOf(1), is(0));
        assertThat(myList.indexOf(2), is(1));
        assertThat(myList.indexOf(3), is(2));
        assertThat(myList.indexOf(4), is(-1));
    }
    
    /**
     * Test method for {@link MyArrayList#indexOf(Object)}.
     */
    @Test(expected = NullPointerException.class)
    public void testIndexOfNull() {
        assertThat(myList.indexOf(0), is(-1));
        myList.add(0);
        assertThat(myList.indexOf(0), is(3));
    }
    
    /**
     * Test method for {@link MyArrayList#indexOf(Object)}.
     */
    @Test
    public void addIndexElement() {
        myList.add(1, 5);
        assertThat(myList.indexOf(1), is(0));
        assertThat(myList.indexOf(5), is(1));
    }
}