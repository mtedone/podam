package uk.co.jemos.podam.api;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static uk.co.jemos.podam.api.ExternalFactoryTest.InnerA;
import static uk.co.jemos.podam.api.ExternalFactoryTest.InnerGenericList;

public class GuavaReflectionUtilsTests {

	@Test
	public void getRawGenericParameterClass() {
		GuavaReflectionUtils guavaReflectionUtils = new GuavaReflectionUtils();

		InnerGenericList innerGenericList = new InnerGenericList(new ArrayList<InnerA>());
		Type type = innerGenericList.getClass().getConstructors()[0].getGenericParameterTypes()[0];

		Class<?> rawClass = guavaReflectionUtils.getRawGenericParameterClass(type);
		assertThat(rawClass.getName()).isEqualTo(InnerA.class.getName());
	}

	@Test(expected = IllegalStateException.class)
	public void twoTypeParamTest() {
		GuavaReflectionUtils guavaReflectionUtils = new GuavaReflectionUtils();

		Type type = TwoTypeParamClass.class.getConstructors()[0].getGenericParameterTypes()[0];

		Class<?> rawClass = guavaReflectionUtils.getRawGenericParameterClass(type);
		assertThat(rawClass.getName()).isEqualTo(InnerA.class.getName());
	}

	static public class TwoTypeParamClass {
		public TwoTypeParamClass(Map<String, Boolean> amap) {
		}
	}

	// assertions about reflection behaviour

	@Test
	public void innerClassConstructors() {
		Constructor<?>[] constructors = InnerGenericList.class.getConstructors();
		assertThat(constructors).hasSize(1);
		Constructor<?> constructor = constructors[0];
		Type[] genericParameterTypes = constructor.getGenericParameterTypes();
		Class<?>[] parameterTypes = constructor.getParameterTypes();

		assertThat(genericParameterTypes).hasSize(1);
		assertThat(parameterTypes).hasSize(1);
	}

	@Test
	public void normalClassConstructors() {
		Constructor<?>[] constructors = NormalGenericList.class.getConstructors();
		assertThat(constructors).hasSize(1);
		Constructor<?> constructor = constructors[0];
		Type[] genericParameterTypes = constructor.getGenericParameterTypes();
		Class<?>[] parameterTypes = constructor.getParameterTypes();

		assertThat(genericParameterTypes).hasSize(1);
		assertThat(parameterTypes).hasSize(1);
	}
}
