package test;

import java.lang.reflect.Field;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import org.junit.Test;

import view.ViewController;


/**
 * Tests for the view controller
 * @author jeffersd
 *
 */

public class ViewControllerTest {
	
	
	
	/**Runs all of the tests in this class.
	 * @throws IllegalAccessException
	 * Due to reflection. 
	 * @throws IllegalArgumentException
	 * Due to reflection. 
	 * @throws SecurityException
	 * Due to reflection. 
	 * @throws NoSuchFieldException
	 * Due to reflection. 
	 * 
	 */
//	@Test
//	public void testAll() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//		testGenerateTest();
//		testGenerateButtonAction();
//		testDeleteButtonAction();
//	}
	
	
	/**
	 * Tests the creation and storage of tests.
	 * It creates ten thousand tests.
	 */
	@Test
	public void testGenerateTest() {
		ViewController VC = new ViewController();
		for (int i = 0; i < 10000; i++) {
			VC.setAllFields(Integer.toString(i));
			//TestCase T = VC.generateTest();
			// assertEquals(VC.TC.getTest(i), T);
		}
		//assertEquals(20000, VC.getNumberOfTests());
	}
	
	/**Tests the 'handleGenerateButtonAction' method.
	 * @throws NoSuchFieldException
	 * Due to reflection. 
	 * @throws IllegalAccessException
	 * Due to reflection. 
	 * 
	 */
	@Test
	public void testGenerateButtonAction() throws NoSuchFieldException, IllegalAccessException {
		ViewController VC = new ViewController();
		VC.handleGenerateButtonAction(null);
		
		Class<ViewController> vc = ViewController.class;
		Field F = vc.getField("listView");
		F.setAccessible(true);
		F.set(VC, new ListView<String>());
		
		Field f1 = vc.getField("namefield");
		f1.setAccessible(true);
		f1.set(VC, new TextField());
		
		Field f2 = vc.getField("paramsfield");
		f2.setAccessible(true);
		f2.set(VC, new TextField());

		Field f3 = vc.getField("returnfield");
		f3.setAccessible(true);
		f3.set(VC, new TextField());
		
		Field f4 = vc.getField("stdinfield");
		f4.setAccessible(true);
		f4.set(VC, new TextField());
		
		Field f5 = vc.getField("stdoutfield");
		f5.setAccessible(true);
		f5.set(VC, new TextField());
		
		Field f6 = vc.getField("methodnamefield");
		f6.setAccessible(true);
		f6.set(VC, new TextField());
		
		Field f7 = vc.getField("classnamefield");
		f7.setAccessible(true);
		f7.set(VC, new TextField());
		
		
		VC.handleGenerateButtonAction(null);
	}
	
	
	
	/**Tests the 'deletebutton' action.
	 * @throws NoSuchFieldException
	 * Due to reflection. 
	 * @throws IllegalAccessException
	 * Due to reflection. 
	 * 
	 */
	@Test
	public void testDeleteButtonAction() throws NoSuchFieldException, IllegalAccessException {
		ViewController VC = new ViewController();
		VC.handleDeleteButtonAction(null);
		
		Class<ViewController> vc = ViewController.class;
		Field F = vc.getField("listView");
		F.setAccessible(true);
		F.set(VC, new ListView<String>());
		VC.handleDeleteButtonAction(null);
	}
	
	
	
	
	
	
	
	
	
	
}
