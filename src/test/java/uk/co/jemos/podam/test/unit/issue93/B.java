package uk.co.jemos.podam.test.unit.issue93;

import uk.co.jemos.podam.common.PodamCollection;

import java.util.List;

/**
 * Created by tedonema on 05/05/2015.
 */
public class B {

    @PodamCollection(nbrElements = 2)
    List<A> listOfC;

    public List<A> getListOfC() {
        return listOfC;
    }

    public void setListOfC(List<A> listOfC) {
        this.listOfC = listOfC;
    }
}
