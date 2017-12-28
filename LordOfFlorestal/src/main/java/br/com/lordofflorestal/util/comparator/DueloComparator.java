package br.com.lordofflorestal.util.comparator;

import br.com.lordofflorestal.model.Duelo;
import java.util.Calendar;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gabriel
 */
public class DueloComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Calendar c1 = ((Duelo) o1).getDataCriacao();
        Calendar c2 = ((Duelo) o2).getDataCriacao();
        int flag = 0;
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) {
            flag = -1;
        } else {
            if (c1.getTimeInMillis() > c2.getTimeInMillis()) {
                flag = 1;
            }
        }
        return flag;
    }
}
