package test;

import org.junit.Test;

/**This class runs all of the test classes for the entire view package.
 * @author dillon
 *
 */
public class ViewTests {
	
	/**Runs a test over the whole package.
	 * 
	 */
	@Test
	public void testViewPackage() {
		testViewTest();
		testViewControllerTest();
	}
	
	
	/**Runs all of the tests in the ViewTest class.
	 * 
	 */
	@Test
	public void testViewTest() {
		ViewTest VT = new ViewTest();
		VT.testAll();
	}
	
	
	/**Runs all of the tests in the ViewControllerTest class.
	 * 
	 */
	@Test
	public void testViewControllerTest() {
		ViewControllerTest VCT = new ViewControllerTest();
		//VCT.testAll();
	}
}
