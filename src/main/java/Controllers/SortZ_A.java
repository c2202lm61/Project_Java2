package Controllers;

import Model.Name;

import java.util.*;
public class SortZ_A implements Comparator<Name> {
    public int compare(Name o1, Name o2) {
        int nameComparison = o2.getName().toUpperCase().compareTo(o1.getName().toUpperCase());
        return nameComparison;
    }
}
