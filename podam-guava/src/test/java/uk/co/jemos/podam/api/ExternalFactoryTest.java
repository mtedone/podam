package uk.co.jemos.podam.api;

import com.google.common.base.Optional;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ExternalFactoryTest {

	@Test
	public void storedModuleDto() {
		PodamFactory factory = new PodamFactoryImpl();

		PodamOptional podamOptional = new PodamOptional<OptionalInner>(OptionalInner.class);
		factory.registerFactory(Optional.class, podamOptional);

		TopClass storedModuleDto = factory.manufacturePojo(TopClass.class, OptionalInner.class);
		assertThat(storedModuleDto).isNotNull();

		Optional<OptionalInner> optionalObject = storedModuleDto.metadata;
		Assertions.assertThat(optionalObject.isPresent()).isTrue().as("Optional object exists");
		assertThat(optionalObject.get().well).isNotNull().as("and has a field populated");
	}

	public class TopClass {

		private InnerA id;

		private InnerB type;

		public TopClass(InnerA id, InnerB type, String name, String data,
						Optional<OptionalInner> metadata) {
			this.id = id;
			this.type = type;
			this.name = name;
			this.data = data;
			this.metadata = metadata;
		}

		private String name;

		private String data;

		private Optional<OptionalInner> metadata;

	}

	public class InnerA {

		int fielda;

		public InnerA(int fielda, String fieldb) {
			this.fielda = fielda;
			this.fieldb = fieldb;
		}

		String fieldb;
	}

	public class InnerB {

		public InnerB(int fieldb, int fielda) {
			this.fieldb = fieldb;
			this.fielda = fielda;
		}

		int fielda;
		int fieldb;
	}

	public class OptionalInner {

		public OptionalInner(Boolean well) {
			this.well = well;
		}

		Boolean well;
	}

}
