package uk.co.jemos.podam.test.unit.issue86;

import java.io.Serializable;

/**
 * Created by tedonema on 12/04/2015.
 */
public class ExtraMethodsPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private static class Holder {
        private String holdingString = null;
        private Long holdingLong = null;
    }

    private Holder myHolder;

    public ExtraMethodsPojo() {
        myHolder = new Holder();
    }

    public String getMyString() {
        return myHolder.holdingString;
    }

    public void setMyString(String myString) {
        myHolder.holdingString = myString;
    }

    public Long getMyLong() {
        return myHolder.holdingLong;
    }

    public void setMyLong(Long myLong) {
        myHolder.holdingLong = myLong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtraMethodsPojo that = (ExtraMethodsPojo) o;

        return myHolder.equals(that.myHolder);

    }

    @Override
    public int hashCode() {
        return myHolder.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExtraMethodsPojo{");
        sb.append("serialVersionUID=").append(serialVersionUID);
        sb.append(", myHolder=").append(myHolder);
        sb.append('}');
        return sb.toString();
    }
}
