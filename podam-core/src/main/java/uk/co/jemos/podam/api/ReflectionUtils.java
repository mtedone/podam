package uk.co.jemos.podam.api;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {

	/**
	 * Given a setter, will return the type of the generic part of the parameter.
	 *
	 * @param setter
	 * @return
	 */
	public Type getGenericParameterTypeFromSetter(Method setter) {
		Type[] genericParameterTypes = setter.getGenericParameterTypes();
		if(genericParameterTypes.length != 1){
			throw new IllegalStateException("Setter should have a single argument.");
		}
		return genericParameterTypes[0];
	}

	public ParameterizedType getParameterizedType(Type theType) {
		ParameterizedType theParamType;
		if (theType instanceof ParameterizedType) {
			theParamType = (ParameterizedType) theType;
		} else {
			throw new IllegalStateException("Must be ParameterizedType type");
		}
		return theParamType;
	}
}
