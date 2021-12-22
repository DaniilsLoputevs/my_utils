package satomi.users;


/**
 * 3. Класс хранилища пользователей UserStorage [#1104]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.3. Синхронизация ресурсов
 * В этом задании нужно сделать блокирующий кеш UserStorage для модели User.
 * 1. Создать класс - структуру данных для хранение пользователей UserStorage.
 * 2. В заголовке класса обозначить аннотацию @ThreadSafe
 * из библиотеки  <groupId>net.jcip</groupId>
 * 3. Хранилище должно иметь методы boolean add (User user),
 * boolean update(User user), boolean delete(User user).
 * 4. И особый метод transfer(int fromId, int toId, int amount);
 * 5. Структура данных должна быть потокобезопасная;
 * 6. В структуре User Должны быть поля int id, int amount.
 */
public interface UserStorage {

    /**
     * Method execute adding Object User to userList
     * Returns:
     * the previous value associated with key (in case true), or null if there was no mapping for key.
     *
     * @param user object User
     * @return true or false
     */
    public boolean add(User user) ;

    /**
     * Method execute update userList values
     *
     * @param user Object which the we want do update
     * @return true if it was successful or false
     */
    public boolean update(User user) ;

    public boolean delete(User user) ;

    /**
     * Метод переводит деньги от одного клиента к другому
     *
     * @param fromId кличет от которого осущес-ся перевод
     * @param toId   клиет которому осущ-ся перевод
     * @param amount сумма перевода
     * @return true if it was successful or false
     */
    public boolean transfer(int fromId, int toId, int amount) ;

    /**
     * Method show userList size
     *
     * @return userList size
     */
    public int getSize();
    
}
