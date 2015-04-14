package uk.co.jemos.podam.test.unit.issue86;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by tedonema on 14/04/2015.
 */
public class MyInitPojoUnitTest {

    private DefaultClassInfoStrategy classInfoStrategy = DefaultClassInfoStrategy.getInstance();


    private final PodamFactory podam = new PodamFactoryImpl();

    @Before
    public void init() throws Exception {
        classInfoStrategy.addExtraMethod(MyInitPojo.class, "init", String.class, String.class);
    }

    @Test
    public void testMyInitPojo() throws Exception {

        MyInitPojo pojo = podam.manufacturePojo(MyInitPojo.class);
        Assert.assertNotNull(pojo);
        Assert.assertNotSame(pojo.getString1(), pojo.getBackupString1());
        Assert.assertNotSame(pojo.getString2(), pojo.getBackupString2());


    }
}
