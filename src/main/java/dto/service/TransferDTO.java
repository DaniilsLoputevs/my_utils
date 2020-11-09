package dto.service;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Use this annotation if you want map fields with original NAME TO field with DIFFERENT NAME.
 * DONT'T use it when you use only original name.
 * <p>
 * Set annotation in yours DTO class(target class), not in model class(from class).
 * <p>
 * ** If you set annotation, and want to original field name, add it in {@param altNames} as last elem,
 * it should work.
 * <p>
 * {@link Transfer} - annotation analyzer.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 08.11.2020.
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface TransferDTO {

    /**
     * Alternative names for transfer in DTO.
     * Override original field name.
     *
     * @return -
     */
    String[] altNames() default {};

}