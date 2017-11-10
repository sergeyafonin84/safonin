package ru.job4j.task;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DirectoryOfUnits {

    public List<Unit> sortUnitsDesc(List<Unit> unitsList) {

        unitsList.sort(new Comparator<Unit>() {
            @Override
            public int compare(Unit o1, Unit o2) {

                return sortUnitsByNameDescForComparator(o1, o2);
            }

        });
        return unitsList;
    }

    public List<Unit> sortUnitsAsc(List<Unit> unitsList) {

        unitsList.sort(new Comparator<Unit>() {
            @Override
            public int compare(Unit o1, Unit o2) {

                List<String> o1DecomposedName = o1.getDecomposedName();
                List<String> o2DecomposedName = o2.getDecomposedName();

                Iterator o1DecomposedNameIterator = o1DecomposedName.listIterator();
                Iterator o2DecomposedNameIterator = o2DecomposedName.listIterator();

                int returnValue = 0;

                while (o1DecomposedNameIterator.hasNext()) {

                    if (o1DecomposedNameIterator.hasNext()) {

                        if (!o2DecomposedNameIterator.hasNext()) {

                            returnValue = 1;

                            break;

                        } else {

                            String o1SubName = (String) o1DecomposedNameIterator.next();
                            String o2SubName = (String) o2DecomposedNameIterator.next();

                            if (o1SubName.compareTo(o2SubName) > 0) {
                                returnValue = -1;
                                break;
                            } else if (o1SubName.compareTo(o2SubName) < 0) {
                                returnValue = 1;
                                break;
                            } else if (!o1DecomposedNameIterator.hasNext()) {
                                returnValue = -1;
                                break;
                            } else {
                                continue;
                            }
                        }
                    } else if (o2DecomposedNameIterator.hasNext()) {
                        returnValue = -1;
                    }
                }
                return returnValue;
            };
        });
        return unitsList;
    }

    int sortUnitsByNameDescForComparator(Unit o1, Unit o2) {
        if (o1.getName().compareTo(o2.getName()) > 0) {
            return 1;
        } else if (o1.getName().compareTo(o2.getName()) < 0) {
            return -1;
        } else {
            return 0;
        }
    };
}
