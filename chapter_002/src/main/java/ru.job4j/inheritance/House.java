package ru.job4j.inheritance;

/**
 * house.
 */
public class House {
    /**
     * owner.
     */
    public Human owner;
    /**
     * engineer.
     */
    public Engineer engineer;
    /**
     * condition.
     */
    public Boolean goodCondition;

    /**
     * override.
     * @param obj for checking equalation.
     * @return result.
     */
    @Override
    public boolean equals(Object obj) {
        //return super.equals(obj);
        House testHouse = (House) obj;
        return this.engineer == testHouse.engineer && this.goodCondition == testHouse.goodCondition && this.owner == testHouse.owner;
    }
}
