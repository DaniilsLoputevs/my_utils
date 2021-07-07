import java.util.*;

public class MyLinkedList<E> implements List<E> {
    
    private class Node {
        public E data;
        public Node next;
        
        public Node(E data) {
            this.data = data;
            this.next = null;
        }
        
        @SuppressWarnings("unused")
        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
        
        public String toString() {
            return "Node(" + data.toString() + ")";
        }
    }
    
    private int size;            // отслеживает колличество элементов
    private Node head;           // ссылается на первый узел
    
    public static void main(String[] args) {
        // run a few simple tests
        List<Integer> mll = new MyLinkedList<Integer>();
        mll.add(1);
        mll.add(2);
        mll.add(3);
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());

       /* mll.remove(new Integer(2));
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size()); */
        int rte = mll.indexOf(1);
        System.out.println(rte);
        mll.add(1, 6);
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
        mll.add(1, 7);
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
        mll.add(4, 10);
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
        // mll.add(10, 25);
        // System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
        mll.add(0, 25);
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
        
        
        var test = new LinkedList<>(List.of("one", "two"));
        System.out.println("test = " + test);
        
        
        MyLinkedList<Integer> src = new MyLinkedList<>();
        src.add(1);
        src.add(2);
        src.add(3);
        System.out.println("### INIT ###");
        System.out.println("index of 3 = " + src.indexOf(3));
        System.out.println("size       = " + src.size());
        System.out.println("src = " + Arrays.toString(src.toArray()));
        System.out.println();
        
        src.add(1, 10);
        System.out.println("src = " + Arrays.toString(src.toArray()));
        System.out.println("index of 3 = " + src.indexOf(3));
        System.out.println("size       = " + src.size());
        System.out.println("node [0] next = " + src.getNode(0).data + Objects.hashCode(src.getNode(0).next));
        System.out.println();
        
        src.add(2, 20);
        System.out.println("src = " + Arrays.toString(src.toArray()));
        System.out.println("index of 3 = " + src.indexOf(3));
        System.out.println("size       = " + src.size());
        System.out.println("node [0] next = " + src.getNode(0).data + Objects.hashCode(src.getNode(0).next));
        System.out.println();
        
        var str = new StringJoiner(" -> ");
        for (int each : src) {
            str.add(each + "");
        }
        System.out.println(str);
        
        
        
        
        
        var list = List.of(1,3);
        var elements = List.of(1,2,3);
        elements.forEach(each -> list.remove(each));
        elements.forEach(list::remove);
        
        list.removeAll(elements);
    }
    
    @Override
    public boolean add(E element) {
        if (head == null) {
            head = new Node(element);
        } else {
            Node node = head;
            // цикл до последнего узла
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node(element);
        }
        size++;
        return true;
    }
    
    /**
     * Вставляет указанный элемент в указанную позицию в этом списке.
     * Сдвигает элемент, который в данный момент находится в этой позиции
     * (если есть), и все последующие элементы вправо (добавляет единицу к их индексам).
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node node = (head == null) ? new Node(element, null) : getNode(index);
//        if (head == null) {
//            Node node = new Node(element, null);
//        }
//        Node node = getNode(index);
        
        Node node2 = new Node(node.data, node.next);
        node.data = element;
        node.next = node2;
        size++;
    }
    
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        boolean flag = true;
        for (E element : collection) {
            flag &= add(element);
        }
        return flag;
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    
    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }
    
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) return null; // NPE safe
        return getNode(index).data;
    }
    
    /**
     * Returns the node at the given index.
     *
     * @param index
     * @return
     */
    private Node getNode(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    
    /**
     * Возвращает индекс первого вхождения указанного элемента в этом списке или -1,
     * если этот список не содержит элемент. Более формально, возвращает самый низкий индекс,
     * iтакой что (o == null? Get (i) == null: o.equals (get (i))) , или -1, если такого индекса нет.
     *
     * @param target
     * @return
     */
    @Override
    public int indexOf(Object target) {
        /*if (target == null) {
            throw new NoSuchElementException();
        }*/
        Node node = head;
        for (int i = 0; i <= size; i++) {
            if (equals(target, node.data)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }
    
    /**
     * Checks whether an element of the array is the target.
     * <p>
     * Handles the special case that the target is null.
     *
     * @param target
     * @param element
     */
    private boolean equals(Object target, Object element) {
        if (target == null) {
            return element == null;
        } else {
            return target.equals(element);
        }
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        return Arrays.asList((E[]) toArray()).iterator();
    }
    
    @Override
    public int lastIndexOf(Object target) {
        Node node = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (equals(target, node.data)) {
                index = i;
//                return i;
            }
            node = node.next;
        }
        return index;
//        return -1;
    }
    
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }
    
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }
    
    @Override
    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }
    
    @Override
    public E remove(int index) {
        //TODO: FILL THIS IN!
        return null;
    }
    
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean flag = true;
        for (Object obj : collection) {
            flag &= remove(obj);
        }
        return flag;
    }
    
    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public E set(int index, E element) {
        Node node = getNode(index);
        if (node != null) {
            E old = node.data;
            node.data = element;
            return old;
        } else return null;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        // TODO: classify this and improve it.
        int i = 0;
        MyLinkedList<E> list = new MyLinkedList<E>();
        for (Node node = head; node != null; node = node.next) {
            if (i >= fromIndex && i <= toIndex) {
                list.add(node.data);
            }
            i++;
        }
        return list;
    }
    
    @Override
    public Object[] toArray() {
//        System.out.println("toArr()");
        Object[] array = new Object[size];
        int i = 0;
        for (Node node = head; node != null; node = node.next) {
//             System.out.println(node);
            array[i++] = node.data;
        }
//        System.out.println();
        return array;
    }
    
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }
}
