package uk.co.jemos.podam.test;

import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;

public class RecursiveCollectionChildTest {

	@Test
	public void single() {
		PodamFactory factory = new PodamFactoryImpl();
		Outer outer = factory.manufacturePojo(Outer.class);
		Assert.assertNotNull(outer);
	}

	@Test
	public void collection() {
		PodamFactory factory = new PodamFactoryImpl();
		ListContainer outer = factory.manufacturePojo(ListContainer.class);
		Assert.assertNotNull(outer);
	}

	public class Outer {
		public Outer() {
		}

		public void setInner(Outer inner) {
			this.inner = inner;
		}

		public Outer inner;
	}

	public class ListContainer {
		public ListContainer() {

		}

		public void setList(List<ListContainer> list) {
			this.list = list;
		}

		public List<ListContainer> list;
	}

}
