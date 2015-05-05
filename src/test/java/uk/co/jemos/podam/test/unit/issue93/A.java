package uk.co.jemos.podam.test.unit.issue93;

import uk.co.jemos.podam.common.PodamCollection;

import java.util.List;

/**
 * Created by tedonema on 05/05/2015.
 */
public class A {

    @PodamCollection(nbrElements = 2)
    private List<C> listOfC;

    public List<C> getListOfC() {
        return listOfC;
    }

    public void setListOfC(List<C> listOfC) {
        this.listOfC = listOfC;
    }
}
