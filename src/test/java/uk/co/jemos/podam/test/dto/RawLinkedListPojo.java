package uk.co.jemos.podam.test.dto;

import java.io.Serializable;
import java.util.LinkedList;

public class RawLinkedListPojo implements Serializable {

    private java.util.LinkedList rawLinkedList;

    public LinkedList getRawLinkedList() {
        return rawLinkedList;
    }

    public void setRawLinkedList(LinkedList rawLinkedList) {
        this.rawLinkedList = rawLinkedList;
    }
}
