package tests;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTest {
	
	/*Add all setup functionality here, which will generally be a few
	 * variable declarations, and a constructor. Remember, no magic numbers!
	 */
	
	long long1 = 3;	
	long long2 = 5;
	long expectedResult = 8;
	ExampleClass exampleClass = new ExampleClass(long1, long2);
	
	
	//method name should be "testMethodName"
	//add similar methods for each method of the class being tested
	@Test
	public void testAddNumbers() {
		
		
		//assertEquals is of the following form:
		//assertEquals(expected result, message to compare to)
		//value to compare to is nearly always a function call
		
		assertEquals(expectedResult, exampleClass.addNumbers());
	}
}
