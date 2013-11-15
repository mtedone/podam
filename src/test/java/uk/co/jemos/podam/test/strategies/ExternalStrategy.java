package uk.co.jemos.podam.test.strategies;

import org.junit.Test;

import junit.framework.Assert;
import uk.co.jemos.podam.api.AttributeStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

public class ExternalStrategy {

	@Test
	public void storedModuleDto() {
		PodamFactory factory = new PodamFactoryImpl();

		MyFactory myFactory = new MyFactory();
		factory.registerFactory(MyCustomClass.class, myFactory);

		MyClass storedModuleDto = factory.manufacturePojo(MyClass.class);
		Assert.assertNotNull(storedModuleDto);
		Assert.assertNotNull(storedModuleDto.customClass);
	}

	public class MyFactory implements AttributeStrategy<MyCustomClass> {

		public MyCustomClass getValue() throws PodamMockeryException {
			return new MyCustomClass();
		}
	}

	public class MyClass {

		public MyClass(MyCustomClass customClass) {
			this.customClass = customClass;
		}

		public MyCustomClass customClass;
	}

	public class MyCustomClass {

	}

}
