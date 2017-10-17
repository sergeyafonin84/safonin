package ru.job4j.inheritance;

/**
 * vechicle.
 */
public class Vechicle {
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
     * equals.
     * @param obj param.
     * @return if equals.
     */
    @Override
    public boolean equals(Object obj) {
//        return super.equals(obj);
        Vechicle testAuto = (Vechicle) obj;
        return this.engineer == testAuto.engineer && this.goodCondition == testAuto.goodCondition && this.owner == testAuto.owner;
    }
}
