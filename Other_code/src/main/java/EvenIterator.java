import java.util.Iterator;

public class EvenIterator implements Iterator<Integer> {
    private final int[] arr;
    private int nextIndex = 0;
    
    public EvenIterator(int[] arr) {
        this.arr = arr;
    }
    
    /**
     * ↓ - этот знак делается через: AltGr + 2(num pad) + 5(num pad)
     *
     * @see <a href="https://www.alt-codes.net/">ctrl + click on hyper => open link</a>
     */
    public static void main(String[] args) { //     ↓           ↓     ↓     ↓               ↓     ↓   ↓
        var iter = new EvenIterator(new int[]{1, 1, 2, 3, 3, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 10, 3, 2, 12});
        
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
    
    @Override
    public boolean hasNext() {
        while (nextIndex < arr.length) {
            if (!(arr[nextIndex] % 2 == 0)) {
                nextIndex++;
            } else return true;
        }
        return false;
    }
    
    @Override
    public Integer next() {
        return arr[nextIndex++];
    }
}
