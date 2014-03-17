/**
 *
 */
package uk.co.jemos.podam.test.dto;

public class EmbeddedAbstractFieldTestPojo {

    private final AbstractTestPojo testPojo;

    public EmbeddedAbstractFieldTestPojo(final AbstractTestPojo pojo) {
        assert(null != pojo);
        this.testPojo = pojo;
    }

    public AbstractTestPojo getPojo() {
        return this.testPojo;
    }

}
