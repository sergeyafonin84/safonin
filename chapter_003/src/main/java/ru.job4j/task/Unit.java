package ru.job4j.task;

public class Unit {

    String name;

    String subName;

    String subSubName;

    String divName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Unit unit = (Unit) o;

        return name != null ? name.equals(unit.name) : unit.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Unit(String name) {
        this.name = name;

        String nameForSplit = this.name.replace(
                new String("\\").toCharArray()[0],
                    new String(";").toCharArray()[0]);

        String regex = new String(";");

        String[] arrayOfDivAndSubDiv = nameForSplit.split(regex);

        for (int ind = 0; ind < arrayOfDivAndSubDiv.length; ind++) {
            if (ind == 0) {
                this.divName = arrayOfDivAndSubDiv[ind];
            } else if (ind == 1) {
                this.subName = arrayOfDivAndSubDiv[ind];
            } else if (ind == 2) {
                this.subSubName = arrayOfDivAndSubDiv[ind];
            }
        }

        if (this.subName == null) {
            this.subName = "zzzNoSubName";
        }

        if (this.subSubName == null) {
            this.subSubName = "zzzNoSubSubName";
        }

    }
}
