package uk.co.jemos.podam.test.unit;

import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.annotations.*;

/**
 * Created by tedonema on 12/06/2015.
 */
public class AnnotationsExceptionsTest {

    private PodamFactory podamFactory;

    @Before
    public void init() {
        podamFactory = new PodamFactoryImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void podamShouldThrowExceptionWhenPodamIntegerValueContainsInvalidCharacters() throws Exception {

        podamFactory.manufacturePojo(IntegerValueWithErrorPojo.class);
    }

    @Test(expected = IllegalArgumentException.class)
    @Title("Podam should throw an exception if the @PodamLongValue annotation contains invalid characters")
    public void podamShouldThrowExceptionWhenPodamLongValueContainsInvalidCharacters() throws Exception {
        podamFactory.manufacturePojo(LongValueWithErrorPojo.class);
    }


    @Test(expected = IllegalArgumentException.class)
    @Title("Podam should throw an exception if the @PodamByteValue annotation contains invalid characters")
    public void podamShouldThrowExceptionWhenPodamByteValueContainsInvalidCharacters() throws Exception {
        podamFactory.manufacturePojo(ByteValueWithErrorPojo.class);

    }

    @Test(expected = IllegalArgumentException.class)
    @Title("Podam should throw an exception if the @PodamShortValue annotation contains invalid characters")
    public void podamShouldThrowExceptionWhenPodamShortValueContainsInvalidCharacters() throws Exception {
        podamFactory.manufacturePojo(ShortValueWithErrorPojo.class);
    }

    @Test(expected = IllegalArgumentException.class)
    @Title("Podam should throw an exception if the @PodamFloatValue annotation contains invalid characters")
    public void podamShouldThrowExceptionWhenPodamFloatValueContainsInvalidCharacters() throws Exception {
        podamFactory.manufacturePojo(FloatValueWithErrorPojo.class);
    }



    @Test(expected = IllegalArgumentException.class)
    @Title("Podam should throw an exception if the @PodamDoubleValue annotation contains invalid characters")
    public void podamShouldThrowExceptionWhenPodamDoubleValueContainsInvalidCharacters() throws Exception {
        podamFactory.manufacturePojo(DoubleValueWithErrorPojo.class);
    }

    @Test(expected = IllegalArgumentException.class)
    @Title("Podam should throw an exception if the @PodamStrategyValue annotation contains the wrong strateg type")
    public void podamShouldThrowExceptionWhenPodamStrategyValueContainsInvalidStrategy() throws Exception {
        podamFactory.manufacturePojo(StringWithWrongStrategyTypePojo.class);
    }

}
