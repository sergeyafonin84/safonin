package ru.job4j.inheritance;

import org.junit.Test;


import static org.junit.Assert.assertEquals;


/**
 * test Engineer.
 */
public class EngineerTest {
    /**
     * test build.
     */
    @Test
    public void whenEngineerBuildHouse() {

        Human humanEngineer = new Human();
        humanEngineer.name = "Иван";
        Engineer engineer = new Engineer();
        engineer.human = humanEngineer;

        House result = engineer.build();

        House expectedHouse = new House();
        expectedHouse.engineer = engineer;
        expectedHouse.goodCondition = true;

        assertEquals(expectedHouse, result);
    }

    /**
     * test create.
     */
    @Test
    public void whenEngineerCreateVechicle() {

        Human humanEngineer = new Human();
        humanEngineer.name = "Иван";
        Engineer engineer = new Engineer();
        engineer.human = humanEngineer;

        Vechicle result = engineer.create();

        Vechicle expectedVechicle = new Vechicle();
        expectedVechicle.engineer = engineer;
        expectedVechicle.goodCondition = true;

        assertEquals(expectedVechicle, result);
    }

    /**
     * test repair house.
     */
    @Test
    public void whenEngineerrepairHouse() {

        House house = new House();
        house.goodCondition = false;

        Human humanEngineer = new Human();
        humanEngineer.name = "Иван";
        Engineer engineer = new Engineer();
        engineer.human = humanEngineer;

        house.engineer = engineer;

        String result = engineer.repairHouse(house);

        String expectedResult = "Инженер: Иван отремонтировал дом";

        assertEquals(expectedResult, result);
    }

    /**
     * test repair auto.
     */
    @Test
    public void whenEngineerrepairVechicle() {

        Vechicle vechicle = new Vechicle();
        vechicle.goodCondition = false;

        Human humanEngineer = new Human();
        humanEngineer.name = "Иван";
        Engineer engineer = new Engineer();
        engineer.human = humanEngineer;

        vechicle.engineer = engineer;

        String result = engineer.repairVechicle(vechicle);

        String expectedResult = "Инженер: Иван отремонтировал автомобиль";

        assertEquals(expectedResult, result);
    }
}
