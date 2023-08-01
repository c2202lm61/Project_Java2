package Controllers;

import Model.Name;

import java.util.*;

public class SortA_Z implements Comparator<Name> {
    public int compare(Name o1, Name o2) {
        int nameComparison = o1.getName().compareTo(o2.getName());
        return nameComparison;
    }
}
