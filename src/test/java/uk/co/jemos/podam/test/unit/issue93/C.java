package uk.co.jemos.podam.test.unit.issue93;

import uk.co.jemos.podam.common.PodamCollection;

import java.util.List;

/**
 * Created by tedonema on 05/05/2015.
 */
public class C {

    @PodamCollection(nbrElements = 2)
    List<B> listOfB;


    public List<B> getListOfB() {
        return listOfB;
    }

    public void setListOfB(List<B> listOfB) {
        this.listOfB = listOfB;
    }
}
