package uk.co.jemos.podam.test.unit.issue93;

import junit.framework.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by tedonema on 05/05/2015.
 */
public class Issue93UnitTest {

    @Test
    public void testLoop()  {
        A a =new PodamFactoryImpl().manufacturePojo(A.class);
        Assert.assertNotNull("The Pojo for class A should not be null", a);
    }
}
