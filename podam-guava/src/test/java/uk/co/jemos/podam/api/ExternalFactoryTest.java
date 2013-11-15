package uk.co.jemos.podam.api;

import com.google.common.base.Optional;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class ExternalFactoryTest {

	@Test
	public void optionalsWithConstructor() {
		PodamFactory factory = new PodamFactoryImpl();

		GenericOptionalFactory podamOptional = new GenericOptionalFactory(false);
		factory.registerFactory(Optional.class, podamOptional);

		TopClass storedModuleDto = factory.manufacturePojo(TopClass.class);
		assertThat(storedModuleDto).isNotNull();
		assertThat(storedModuleDto.metadata).isNotNull();

		Optional<OptionalInner> optionalObject = storedModuleDto.metadata;
		assertThat(optionalObject.isPresent()).isTrue().as("Optional object exists");
		assertThat(optionalObject.get().well).isNotNull().as("and has a field populated");

		Optional<InnerB> second = storedModuleDto.secondOptional;
		Assertions.assertThat(second.isPresent()).isTrue().as("Second optional object exists");
		assertThat(second.get()).isInstanceOf(InnerB.class);
		InnerB innerB = second.get();
		assertThat(innerB.fielda).isNotNull().as("and has a field populated");
	}

	@Test
	public void optionalsWithSetters() {
		PodamFactory factory = new GuavaPodamFactory();

		TopClassSetters storedModuleDto = factory.manufacturePojo(TopClassSetters.class);
		assertThat(storedModuleDto).isNotNull();
		assertThat(storedModuleDto.metadata).isNotNull();

		Optional<OptionalInner> optionalObject = storedModuleDto.metadata;
		assertThat(optionalObject.isPresent()).isTrue().as("Optional object exists");
		assertThat(optionalObject.get()).isInstanceOf(OptionalInner.class);
		OptionalInner optionalInner = optionalObject.get();
		assertThat(optionalInner.well).isNotNull().as("and has a field populated");

		Optional<InnerB> second = storedModuleDto.secondOptional;
		Assertions.assertThat(second.isPresent()).isTrue().as("Second optional object exists");
		assertThat(second.get()).isInstanceOf(InnerB.class);
		InnerB innerB = second.get();
		assertThat(innerB.fielda).isNotNull().as("and has a field populated");
	}

	@Test
	public void normalGenericWithoutGenericArg() {
		PodamFactory factory = new PodamFactoryImpl();
		InnerGenericList genericList = factory.manufacturePojo(InnerGenericList.class);
		assertThat(genericList).isInstanceOf(InnerGenericList.class);
		List<InnerA> list = genericList.list;
		Assertions.assertThat(list).hasSize(5);
	}

	@Test
	public void normalGeneric() {
		PodamFactory factory = new PodamFactoryImpl();
		InnerGenericList genericList = factory.manufacturePojo(InnerGenericList.class, InnerA.class);
		assertThat(genericList).isInstanceOf(InnerGenericList.class);
		List<InnerA> list = genericList.list;
		Assertions.assertThat(list).hasSize(5);
	}

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



	static public class TopClass {

		public TopClass(Optional<OptionalInner> metadata, Optional<InnerB> secondOptional) {
			this.metadata = metadata;
			this.secondOptional = secondOptional;
		}

		private Optional<OptionalInner> metadata;

		private Optional<InnerB> secondOptional;

	}

	static public class TopClassSetters {

		public void setMetadata(Optional<OptionalInner> metadata) {
			this.metadata = metadata;
		}

		public void setSecondOptional(Optional<InnerB> secondOptional) {
			this.secondOptional = secondOptional;
		}

		private Optional<OptionalInner> metadata;

		private Optional<InnerB> secondOptional;

	}

	static public class InnerA {

		int fielda;

		public InnerA(int fielda, String fieldb) {
			this.fielda = fielda;
			this.fieldb = fieldb;
		}

		String fieldb;
	}

	static public class InnerB {

		public InnerB(int fieldb, int fielda) {
			this.fieldb = fieldb;
			this.fielda = fielda;
		}

		int fielda;
		int fieldb;
	}

	static public class OptionalInner {

		public OptionalInner(Boolean well) {
			this.well = well;
		}

		Boolean well;
	}

	static public class InnerGenericList {
		private List<InnerA> list;

		public InnerGenericList(List<InnerA> list) {
			this.list = list;
		}
	}

}
