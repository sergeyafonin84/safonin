package ru.job4j.task;

import java.util.Comparator;
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

                if (o1.divName.compareTo(o2.divName) > 0) {
                    return -1;
                } else if (o1.divName.compareTo(o2.divName) < 0) {
                    return  1;
                } else {
                    if (o1.subName.compareTo(o2.subName) > 0) {
                        return -1;
                    } else if (o1.subName.compareTo(o2.subName) < 0) {
                        return 1;
                    } else {

                        if (o1.subSubName.compareTo(o2.subSubName) > 0) {
                            return -1;
                        } else if (o1.subSubName.compareTo(o2.subSubName) < 0) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            };
        });

        return unitsList;
    }

    int sortUnitsByNameDescForComparator(Unit o1, Unit o2) {
        if (o1.name.compareTo(o2.name) > 0) {
            return 1;
        } else if (o1.name.compareTo(o2.name) < 0) {
            return -1;
        } else {
            return 0;
        }
    };

}
