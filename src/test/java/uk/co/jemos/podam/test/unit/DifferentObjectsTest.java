package uk.co.jemos.podam.test.unit;

import java.util.Observable;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author daivanov
 *
 */
public class DifferentObjectsTest {

	private static final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testObservableInstantiation() {
		Observable observable = factory.manufacturePojo(Observable.class);
		Assert.assertNotNull("Manufacturing failed", observable);
	}
}
