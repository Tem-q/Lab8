package dragon;

import java.util.Comparator;

public class NameComparator implements Comparator<Dragon> {

    @Override
    public int compare(Dragon o1, Dragon o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
