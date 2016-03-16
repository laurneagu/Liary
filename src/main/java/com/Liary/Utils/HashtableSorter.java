package com.Liary.Utils;

import java.util.*;

/**
 * Created by Laur on 2/5/2015.
 */
public class HashtableSorter {
    public static List<Map.Entry<String, Integer>> sortValue(Hashtable<String, Integer> t){

        //Sort Map.Entry by value
        List<Map.Entry<String, Integer>> result = new ArrayList(t.entrySet());

        Collections.sort(result, new Comparator<Hashtable.Entry<String, Integer>>() {
            public int compare(Hashtable.Entry<String, Integer> o1, Hashtable.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        return result;
    }
}
