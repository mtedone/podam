package uk.co.jemos.podam.test.unit.issue86;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.*;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tedonema on 12/04/2015.
 */
public class ExtraMethodsUnitTest {


    private DataProviderStrategy dataProviderStrategy = RandomDataProviderStrategy.getInstance();

    private AbstractClassInfoStrategy classInfoStrategy = DefaultClassInfoStrategy.getInstance();

    private PodamFactory podam = new PodamFactoryImpl(dataProviderStrategy);

    private ExtraMethodsPojo extraMethodsPojo = null;

    @Before
    public void setup() throws Exception {

        //Using Podam to get an empty pojo
        extraMethodsPojo = podam.manufacturePojo(ExtraMethodsPojo.class);
        Assert.assertNotNull("The initial pojo cannot be null", extraMethodsPojo);

        Set<ExtraMethodExecutorData> extraMethods = new HashSet<ExtraMethodExecutorData>();

        Method setMyStringMethod = ExtraMethodsPojo.class.getMethod("setMyString", String.class);

        ExtraMethodExecutorData setMyStringExecutorData =
                new ExtraMethodExecutorData(setMyStringMethod, extraMethodsPojo, new String[] {"Hello World"});

        Method setMyLongMethod = ExtraMethodsPojo.class.getMethod("setMyLong", Long.class);
        ExtraMethodExecutorData setMyLongExecutorData =
                new ExtraMethodExecutorData(setMyLongMethod, extraMethodsPojo, new Long[] {podam.manufacturePojo(Long.class)});

        classInfoStrategy.addExtraMethod(ExtraMethodsPojo.class, setMyStringExecutorData);
        classInfoStrategy.addExtraMethod(ExtraMethodsPojo.class, setMyLongExecutorData);


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
