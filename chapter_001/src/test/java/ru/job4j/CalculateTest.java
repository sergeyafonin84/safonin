
package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
*@author Afonin Sergey (mailto:afonin1c@mail.ru)
*@version 1
*@since 03.10.2017
*/

public class CalculateTest {
/**
* Test echo.
*/ @Test
public void whenTakeNameThenTreeEchoPlusName() {
    String input = "Afonin Sergey";
    String expect = "Echo, echo, echo : Afonin Sergey"; 
    Calculate calc = new Calculate();
    String result = calc.echo(input);
    assertThat(result, is(expect));
}
 
}


/*
package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
*@author Afonin Sergey (afonin1c@mail.ru)
*@version 1
*@since 03.10.2017
*/

/*
public class CalculateTest {
/**
*Test add
*/

/*
@Test
public void whenAddOneToOneThenTwo(){
	ByteArrayOutputStream out = new ByteArrayOutputStream(); 
	System.setOut(new PrintStream(out));
	Calculate.main(null);
	assertThat(
		out.toString(),
		is(
			String.format(
				"Hello World%s",
				System.getProperty("line.separator")
			)
		
		)
	);
	}

  
}*/