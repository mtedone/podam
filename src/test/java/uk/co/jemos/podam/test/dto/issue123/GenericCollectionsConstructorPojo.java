package uk.co.jemos.podam.test.dto.issue123;

import java.util.List;
import java.util.Vector;

/**
 * Created by tedonema on 03/10/2015.
 */
public class GenericCollectionsConstructorPojo {

    private final List<Long> list1;
    private final List<String> list2;
    private final List<Integer> list3;

    /**
     * Full constructor
     * @param list1 First List
     * @param list2 Second List
     * @param list3 Third List
     */
    public GenericCollectionsConstructorPojo(Vector<Long> list1, Vector<String> list2, Vector<Integer> list3) {
        this.list1  = new Vector<Long>(list1);
        this.list2 = new Vector<String>(list2);
        this.list3 = new Vector<Integer>(list3);
    }

    public Vector<Long> getList1() {
        return new Vector<Long>(list1);
    }

    public Vector<String> getList2() {
        return new Vector<String>(list2);
    }

    public Vector<Integer> getList3() {
        return new Vector<Integer>(list3);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GenericCollectionsConstructorPojo{");
        sb.append("list1=").append(list1);
        sb.append(", list2=").append(list2);
        sb.append(", list3=").append(list3);
        sb.append('}');
        return sb.toString();
    }
}
