/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author makir0n
 */
public class SortPeople {

    ArrayList<People> sortPeople;

    public SortPeople(ArrayList<People> list) {

        sortPeople = list;
        Collections.sort(list, new PeopleComparator());
    }

    ArrayList<People> getSortedPeople() {
        return sortPeople;
    }
}

class PeopleComparator implements Comparator {

    @Override
    public int compare(Object s, Object t) {
        return (int) (((People) t).getScore()- ((People) s).getScore());
    }
}
