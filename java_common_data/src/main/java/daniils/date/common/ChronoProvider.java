package daniils.date.common;

/**
 * Mark interface<p>
 *
 * Show that marked class provider group of API for Type T.<p>
 *
 * API that it's provide:
 * <li> map(...) || from(...)
 * <li> format
 * <li> compare
 * <li> copy && copy with changes
 * <li> ??? comparators (support stream API)
 * <li> ??? Compare strategy
 * <li> ??? ChronoOperation
 * <li> operators : plus() && minus() && plusAssign() && minusAssign()
 */
public interface ChronoProvider<T> {
}
