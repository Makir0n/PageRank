/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.ujmp.core.matrix.SparseMatrix;

/**
 *
 * @author makir0n
 */
public class Sort {

    ArrayList<Page> lst;

Sort(ArrayList<Double> values) {
        lst = new ArrayList<Page>();

        for (int i = 0; i < values.size(); i++) {
            //compareがintじゃないと比較できないので1000かけて大きく
            lst.add(new Page(i, values.get(i) * 1000));
        }
        Collections.sort(lst, new PageComparator());
    }

    ArrayList<Page> getBestID() {
        return lst;
    }
}

class PageComparator implements Comparator {

    @Override
    public int compare(Object s, Object t) {
        return (int) (((Page) t).getValue() - ((Page) s).getValue());
    }
}
