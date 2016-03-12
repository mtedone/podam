package uk.co.jemos.podam.test.dto.issue123;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by tedonema on 03/10/2015.
 */
public class GenericCollectionsConstructorPojo {

    private final List<Long> list1;
    private final List<String> list2;

    /**
     * Full constructor
     * @param list1 First List
     * @param list2 Second List
     */
    public GenericCollectionsConstructorPojo(List<Long> list1, List<String> list2) {
        this.list1  = new ArrayList<Long>(list1);
        this.list2 = new ArrayList<String>(list2);
    }

    public Vector<Long> getList1() {
        return new Vector<Long>(list1);
    }

    public Vector<String> getList2() {
        return new Vector<String>(list2);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GenericCollectionsConstructorPojo{");
        sb.append("list1=").append(list1);
        sb.append(", list2=").append(list2);
        sb.append('}');
        return sb.toString();
    }
}
