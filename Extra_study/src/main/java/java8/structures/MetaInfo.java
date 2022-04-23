package java8.structures;

/**
 * https://github.com/xpinjection/java8-misuses/blob/master/src/com/xpinjection/java8/misused/lambda/AvoidComplexLambdas.java
 *
 * Optional
 * - все методы Optional работают только если isPresent() => true
 * - map() + chain invocation - Это КРУТО!!!
 * - иногда if(obj != null) - читается лучше чем Optional - Optional не идеален
 * - АНТИ-ПАТТЕРН : использовать Optional в:
 * 1 - Параметрах методов/конструкторов
 * 2 - (не всегда Хорошо/Плохо) return value
 * 3 - (не всегда Хорошо/Плохо) поле Объекта.
 * - Optional позволяет сделать elvis (даже каскадный) из Kotlin
 * - для случаев с Коллекциями(Optional<Collection> || Optional<List> ...), есть удобные методы:
 * Optional.filter(...) - filter(list -> !list.isEmpty())
 * Optional.orElse(...) - orElse(Collection.emptyList())
 * - Если вы используете isPresent() - Возможно вы что-то, делаете не так. (не всегда)
 *
 * Lambda
 * - Сложные и Длинные лямбды стоит выносить в отдельные методы:
 * 1 - boolean <T> lambdaMethod(T streamElement)
 * 2 - Predicate<T> <T> lambdaMethod(Object predicateConstructorParam)
 * - method::reference можно складывать в переменные и Подменять ИЗ ВНЕ - это КРУТО!
 * SOLID - OCP, SRP, DIP - в одном флаконе.
 * - Лямбды по типу: Predicate, Comparator, ... выносить в класс модель, так можно
 * их Переиспользовать в других местах, а не писать снова.
 * - Для Коллекций из коробки есть удобные методы в классе Comparator, об этом полезно помнить.
 *
 * Collections
 * - List
 * -- есть removeIf(predicate)
 *
 * - Map
 * -- computeIfAbsent - Если значения нету, то Высчитывает И Привязывает новое значение.
 * -- getOrDefault - Если значения нету, только отдаёт переданное значение.
 *
 * 1 - lombok + spring Qualifier
 * 2 - http url mapping override?
 * 3 - delete return old specific object
 *
 *
 * todo - Хочу потыкать Comparator
 */
public class MetaInfo {
}
