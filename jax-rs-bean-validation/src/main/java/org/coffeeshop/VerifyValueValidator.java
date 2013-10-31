package org.coffeeshop;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Sets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Implementation for the user-defined constraint annotation @VerifyValue
 * This is a general purpose validator which verifies the value for any enum
 * If an Enum object has a getValue() method it will validate based on the value of the Enum
 * else will use the EnumConstant
 *
 * @author Bhakti Mehta
 */
public class VerifyValueValidator implements ConstraintValidator<VerifyValue, Object> {

    Class<? extends Enum<?>> enumClass;

    public void initialize(final VerifyValue enumObject) {
        enumClass = enumObject.value();

    }

    /**
     * Checks if the value specified is valid
     * @param myval  The value for the object
     * @param constraintValidatorContext
     * @return
     */
    public boolean isValid(final Object myval,
                           final ConstraintValidatorContext constraintValidatorContext) {


        if ((myval != null) && (enumClass != null)) {
            Enum[] enumValues = enumClass.getEnumConstants();
            Object enumValue = null;

            for (Enum enumerable : enumValues)   {
                if (myval.equals(enumerable.toString()) ) return true;
                enumValue = getEnumValue(enumerable);
                if ((enumValue != null)
                        && (myval.toString().equals(enumValue.toString())))  {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Invokes the getValue() method for enum if present
     * @param enumerable The Enum object
     * @return  returns the value of enum from getValue() or
     *          enum constant
     */
    private Object getEnumValue(Enum<?> enumerable) {
        try {
            for (Method method: enumerable.getClass().getDeclaredMethods()) {
                if (method.getName().equals("getValue")) {
                    return method.invoke(enumerable);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }




}
