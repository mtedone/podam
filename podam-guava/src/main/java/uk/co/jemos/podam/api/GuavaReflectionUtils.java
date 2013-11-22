package uk.co.jemos.podam.api;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Reflection utils specific to Optionals, or that make use of Guava's reflection utilities.
 */
public class GuavaReflectionUtils {

	private ReflectionUtils reflectionUtils = new ReflectionUtils();

	public Type getSingleGenericTypeArgument(ParameterizedType theParamType) {
		Type[] actualTypeArguments = theParamType.getActualTypeArguments();
		if (actualTypeArguments.length != 1) {
			throw new IllegalStateException(ParameterizedType.class.getName() + " has more than one type argument.");
		}
		return actualTypeArguments[0];
	}

	public Class<?> getRawClass(Type actualTypeArgument) {
		TypeToken<?> typeToken = TypeToken.of(actualTypeArgument);
		Class<?> rawClass = typeToken.getRawType();
		return rawClass;
	}

	public Class<?> getRawGenericParameterClass(Type rawType) {
		ParameterizedType parameterizedType = reflectionUtils.getParameterizedType(rawType);
		Type actualTypeArgument = getSingleGenericTypeArgument(parameterizedType);
		Class<?> rawClass = getRawClass(actualTypeArgument);
		return rawClass;
	}
}
