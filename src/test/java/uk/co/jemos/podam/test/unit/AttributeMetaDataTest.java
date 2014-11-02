package uk.co.jemos.podam.test.unit;

import junit.framework.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class AttributeMetaDataTest {

    @Test
    public void testAttributeMetaData() {

        PodamFactory factory = new PodamFactoryImpl(new AbstractRandomDataProviderStrategy() {
            @Override
            public String getStringValue(AttributeMetadata attributeMetadata) {

                if (attributeMetadata.getPojoClass() == PojoSpecific.class) {
                    return "specific";
                } else {
                    return "classic";
                }
            }
        });

        Assert.assertEquals(factory.manufacturePojo(PojoClassic.class).getAtt(), "classic");
        Assert.assertEquals(factory.manufacturePojo(PojoSpecific.class).getAtt(), "specific");


    }

    public static class PojoClassic {

        private String att;

        public String getAtt() {
            return att;
        }

        public void setAtt(String att) {
            this.att = att;
        }
    }

    public static class PojoSpecific {

        private String att;

        public String getAtt() {
            return att;
        }

        public void setAtt(String att) {
            this.att = att;
        }
    }
}
