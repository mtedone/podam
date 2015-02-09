package uk.co.jemos.podam.test.unit;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import uk.co.jemos.podam.api.DataTypeFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.ExternalLibraryPojo;

import static org.junit.Assert.assertEquals;

public class DataTypeFactoryTest {

    @Test
    public void shouldUseProvidedDataTypeFactoryToManufacturePojo() throws Exception {
        PodamFactory podamFactory = new PodamFactoryImpl().withDataTypeFactory(ImmutableList.class, new ImmutableListFactory());

        ImmutableList immutableList = podamFactory.manufacturePojo(ImmutableList.class);

        assertEquals(1, immutableList.size());
    }

    @Test
    public void shouldUseProvidedDataTypeFactoryToManufacturePojoInsideClass() throws Exception {
        PodamFactory podamFactory = new PodamFactoryImpl().withDataTypeFactory(ImmutableList.class, new ImmutableListFactory());

        ExternalLibraryPojo externalLibraryPojo = podamFactory.manufacturePojo(ExternalLibraryPojo.class);

        assertEquals(1, externalLibraryPojo.getDoubles().size());
    }



    private static class ImmutableListFactory implements DataTypeFactory<ImmutableList> {

        @Override
        public ImmutableList manufacture() {
            return ImmutableList.of(Math.random());
        }
    }
}
