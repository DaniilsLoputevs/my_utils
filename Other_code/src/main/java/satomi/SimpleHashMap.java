package satomi;

/**
 * 8. Реализовать собственную структуру данных - HashMap [#471707]
 * Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
 * boolean insert(K key, V value);
 * V get(K key);
 * boolean delete(K key);
 *
 * @param <K>
 * @param <V>
 */
public class SimpleHashMap<K, V>
//        implements Map<K, V>
{
//    private int capacity = 16; // по идеи это размер массива
//    private Node<K, V>[] hashtable = new Node[capacity];
//    private int size = 0; // Количество элементов HashMap-а;
//    private int modCount = 0; // сколько раз коллекция была изменена с момента ее создания
//    private float loadFactor = 0.75f; // коэффициент загрузки
//
//    @Override
//    public boolean insert(K key, V value) {
//        if (size == this.capacity) resizeInnerHashTable();
//        int keyHash = hash(key);
//        return (!checkHashTable(key))
//                ? add(keyHash, value, indexKey(key))
//                : add(keyHash, value, freeIndex());
//    }
//
//    @Override
//    public boolean delete(K key) {
//        boolean rsl = false;
//        if (checkHashTable(key)) {
//            hashtable[indexKey(key)] = null;
//            size--;
//            rsl = true;
//        }
//        return rsl;
//    }
//
//    @Override
//    public V get(K key) {
//        V v = null;
//        int index = indexKey(key);
//        if (checkHashTable(key)) {
//            if (compareHash(index, key)) {
//                v = (V) hashtable[index].getValue();
//            } else {
//                v = getFromTable(key);
//            }
//        }
//        return v;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    /**
//     * Метод проводит создание и встаку Node
//     *
//     * @param hash  результат работы метода hash()
//     * @param value значение
//     * @param index итоговый индекс в бакете
//     * @return
//     */
//    private boolean add(int hash, V value, int index) {
//        hashtable[index] = new Node<>(hash, value);
//        this.size++;
//        this.modCount++;
//        return true;
//    }
//
//    /**
//     * Определяет позицию в массиве куда будет помещен элемент
//     *
//     * @param key
//     * @return
//     */
//    private int indexKey(K key) {
//        return hash(key) & (capacity - 1);
//    }
//
//    /**
//     * Метод проводит удваивание массива при достижении порогового значения
//     */
//    private void resizeInnerHashTable() {
//        hashtable = Arrays.copyOf(hashtable, capacity * 2);
//    }
//
//    /**
//     * Метод прроверяет на присутствие key в hashtable
//     *
//     * @param key - Ключ
//     * @return Обнаружен key с таким значениме - true, нет - false.
//     */
//    private boolean checkHashTable(K key) {
//        int index = hash(key);
//        boolean rsl = false;
//        if (size > 0) {
//            for (Node node : hashtable) {
//                if (node != null && node.getHash() == index) {
//                    rsl = true;
//                    break;
//                }
//            }
//        }
//        return rsl;
//    }
//
//    /**
//     * Метод помогает избегать лишних вложености в методе: V get(key)
//     * Осуществляет поиск по всему массиву, в случаии колизии.
//     *
//     * @return V value or null
//     */
//    private V getFromTable(K key) {
//        V v = null;
//        for (int i = 0; i < hashtable.length; i++) {
//            if (compareHash(i, key)) {
//                v = (V) hashtable[i].getValue();
//                break;
//            }
//        }
//        return v;
//    }
//
//    /**
//     * Метод проверяет на равенство ключей по хешам
//     * Если ключи не равны, то возвращать false.
//     *
//     * @param index бакет
//     * @param key   ключ
//     * @return true or false
//     */
//    private boolean compareHash(int index, K key) {
//        return hashtable[index].getHash() == indexKey(key);
//    }
//
//    /**
//     * получение индекса для бакета
//     *
//     * @param key
//     * @return индекс
//     */
//    private int hash(K key) {
//        int rsl = 31;
//        rsl = rsl * key.hashCode();
//        return rsl % (hashtable.length - 1);
//    }
//
//    private int freeIndex() {
//        int rsl = -1;
//        for (int i = 0; i < this.capacity; i++) {
//            if (hashtable[i] == null) {
//                rsl = i;
//                break;
//            }
//        }
//        return rsl;
//    }
//
//    // метод для теста (Дебаг)
//    public void showTable() {
//        var index = 0;
//        for (Node node : hashtable) {
//            if (node != null) {
//                System.out.println(index + " " + node.getHash() + " " + node.getValue());
//            }
//            index++;
//        }
//    }
//
//    private static class Node<K, V> {
//        private final int hash;
//        private final V value;
//
//        public Node(int hash, V value) {
//            this.hash = hash;
//            this.value = value;
//        }
//
//        private int getHash() {
//            return hash;
//        }
//
//        private V getValue() {
//            return value;
//        }
//
//        @Override
//        public String toString() {
//            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
//                    .add("hash=" + hash)
//                    .add("value=" + value)
//                    .toString();
//        }
//    }
//
//    @Override
//    public Iterator<V> iterator() {
//        return new Iterator<>() {
//            private int count = 0;
//            private final int expectModCount = modCount;
//
//            @Override
//            public boolean hasNext() {
//                if (expectModCount != modCount) {
//                    throw new ConcurrentModificationException();
//                }
//                /* Next NOT NULL element in hashtable */
//                for (int i = count; i < hashtable.length; i++) {
//                    if (hashtable[i] != null) {
//                        count = i;
//                        return true;
//                    }
//                }
//                return false;
//            }
//
//            @Override
//            public V next() {
//                if (!hasNext()) {
//                    throw new NoSuchElementException();
//                }
//                System.out.println("count = " + count);
//                System.out.println("size = " + size);
//                return (V) hashtable[count++].getValue();
//            }
//        };
//    }
//
//    @Override
//    public String toString() {
//        return new StringJoiner(", ", SimpleHashMap.class.getSimpleName() + "[", "]")
//                .add("capacity=" + capacity)
//                .add("hashtable=" + Arrays.toString(hashtable))
//                .add("size=" + size)
//                .add("modCount=" + modCount)
//                .add("loadFactor=" + loadFactor)
//                .toString();
//    }
//
//    public static void main(String[] args) {
//        SimpleHashMap<String, Integer> siMap = new SimpleHashMap<>();
//        siMap.insert("1", 1);
//        siMap.insert("2", 11);
//        siMap.insert("3", 14);
//        siMap.insert("4", 17);
//        Iterator<Integer> iter = siMap.iterator();
//
//        while (iter.hasNext()) {
//            System.out.println("iter next : " + iter.next());
//        }
//        System.out.println(siMap.size());
//    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        SimpleHashMap<?, ?> that = (SimpleHashMap<?, ?>) o;
//        return size == that.size
//                && modCount == that.modCount
//                && capaCity == that.capaCity
//                && Double.compare(that.loadFactor, loadFactor) == 0
//                && Arrays.equals(hashtable, that.hashtable);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(size, modCount, capaCity, loadFactor);
//        result = 31 * result + Arrays.hashCode(array);
//        return result;
//    }
}
