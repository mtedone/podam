package uk.co.jemos.podam.test.unit.issue86;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.*;

/**
 * Created by tedonema on 12/04/2015.
 */
public class ExtraMethodsUnitTest {


    private DataProviderStrategy dataProviderStrategy = new RandomDataProviderStrategyImpl();

    private AbstractClassInfoStrategy classInfoStrategy = DefaultClassInfoStrategy.getInstance();

    private PodamFactory podam = new PodamFactoryImpl(dataProviderStrategy);

    @Before
    public void setup() throws Exception {

        classInfoStrategy.addExtraMethod(ExtraMethodsPojo.class, "setMyString", String.class);
        classInfoStrategy.addExtraMethod(ExtraMethodsPojo.class, "setMyLong", Long.class);
    }

    @Test
    public void testExtraMethods() throws Exception {

        ExtraMethodsPojo pojo = podam.manufacturePojo(ExtraMethodsPojo.class);
        Assert.assertNotNull("The pojo cannot be null", pojo);
        Assert.assertNotNull("The long value cannot be zero", pojo.getMyLong());
        Assert.assertNotNull("The string value cannot be null", pojo.getMyString());
        Assert.assertTrue("The string value cannot be empty", pojo.getMyString().length() > 0);
    }
}
