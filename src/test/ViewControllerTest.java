package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import view.ViewController;

/**
 * Tests for the view controller
 * @author jeffersd
 *
 */

public class ViewControllerTest {
	
	/**
	 * Tests the creation of the ViewController Class
	 */
	@Test
	public void testCreateController() {
		ViewController VC = new ViewController();
		VC.setAllFields("name");
		VC.generateTest();
	}
	
	
	/**
	 * Tests the creation and storage of tests.
	 * It creates ten thousand tests.
	 */
	@Test
	public void testGenerateTest() {
		ViewController VC = new ViewController();
		for (int i = 0; i < 10000; i++) {
			VC.setAllFields(Integer.toString(i));
			VC.generateTest();
		}
		assertEquals(10000, VC.getNumberOfTests());
	}
	
}
