package ru.job4j.inheritance;
/**
 * doctor.
 */
public class Doctor extends Profession {
    /**
     * diploma.
     */
    public String diploma;

    /**
     *
     * @param human return string result.
     * @return string result.
     */
    public String heal(Human human) {

        human.goodHealth = true;

        return "Доктор " + this.getName() + " лечит " + human.name;
    }
}
