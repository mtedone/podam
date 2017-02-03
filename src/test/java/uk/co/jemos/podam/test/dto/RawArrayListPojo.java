package uk.co.jemos.podam.test.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class RawArrayListPojo implements Serializable {
    private java.util.ArrayList rawArrayList;

    public ArrayList getRawArrayList() {
        return rawArrayList;
    }

    public void setRawArrayList(ArrayList rawArrayList) {
        this.rawArrayList = rawArrayList;
    }

}