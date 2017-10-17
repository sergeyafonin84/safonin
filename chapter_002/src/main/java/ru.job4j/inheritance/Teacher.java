package ru.job4j.inheritance;

/**
 * teacher.
 */
public class Teacher extends Profession {
    /**
     * teach.
     * @param student param.
     * @return message.
     */
    public String teach(Student student) {

        student.goodKnowlige = true;

        return "Учитель " + this.getName() + " обучил студента: " + student.human.name;
    }
}
