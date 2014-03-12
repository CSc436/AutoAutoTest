package test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.TestCase;

import org.junit.Test;

import view.ViewController;


/**
 * Tests for the view controller
 * @author jeffersd
 *
 */

public class ViewControllerTest {
	
	
	/**
	 * Tests the creation and storage of tests.
	 * It creates ten thousand tests.
	 */
	@Test
	public void testGenerateTest() {
		ViewController viewcontroller = new ViewController();
		for (int i = 0; i < 10000; i++) {
			viewcontroller.setAllFields(Integer.toString(i));
			//TestCase T = viewcontroller.generateTest();
			// assertEquals(viewcontroller.TC.getTest(i), T);
		}
		//assertEquals(20000, viewcontroller.getNumberOfTests());
	}
	
	/**Tests the 'handleGenerateButtonAction' method.
	 * @throws SecurityException
	 * Due to reflection. 
	 * @throws NoSuchFieldException
	 * Due to reflection. 
	 * @throws IllegalAccessException
	 * Due to reflection. 
	 * @throws IllegalArgumentException 
	 * Due to reflection.
	 * 
	 */
	@Test
	public void testGenerateButtonAction() throws NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException {
		
		ViewController viewcontroller = new ViewController();
		viewcontroller.handleGenerateButtonAction(null);
		
		Class<ViewController> vc = ViewController.class;
		Field listviewField = vc.getField("listView");
		listviewField.setAccessible(true);
		listviewField.set(viewcontroller, new ListView<String>());
		
		Field f1 = vc.getField("namefield");
		f1.setAccessible(true);
		f1.set(viewcontroller, new TextField());
		
		Field f2 = vc.getField("paramsfield");
		f2.setAccessible(true);
		f2.set(viewcontroller, new TextField());

		Field f3 = vc.getField("returnfield");
		f3.setAccessible(true);
		f3.set(viewcontroller, new TextField());
		
		Field f4 = vc.getField("stdinfield");
		f4.setAccessible(true);
		f4.set(viewcontroller, new TextField());
		
		Field f5 = vc.getField("stdoutfield");
		f5.setAccessible(true);
		f5.set(viewcontroller, new TextField());
		
		Field f6 = vc.getField("methodnamefield");
		f6.setAccessible(true);
		f6.set(viewcontroller, new TextField());
		
		Field f7 = vc.getField("classnamefield");
		f7.setAccessible(true);
		f7.set(viewcontroller, new TextField());
		
		
		viewcontroller.handleGenerateButtonAction(null);
	}
	
	
	
	/**Tests the 'deletebutton' action.
	 * @throws SecurityException
	 * Due to reflection.
	 * @throws NoSuchFieldException
	 * Due to reflection. 
	 * @throws IllegalAccessException
	 * Due to reflection. 
	 * @throws IllegalArgumentException
	 * Due to reflection. 
	 * 
	 */
	@Test
	public void testDeleteButtonAction() throws NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException {
		
		ViewController viewcontroller = new ViewController();
		viewcontroller.handleDeleteButtonAction(null);
		
		Class<ViewController> vc = ViewController.class;
		Field listviewField = vc.getField("listView");
		listviewField.setAccessible(true);
		listviewField.set(viewcontroller, new ListView<String>());
		viewcontroller.handleDeleteButtonAction(null);
	}
	
	
	
	@Test
	public void testDeleteButtonAction1() throws NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException {
		
		ViewController viewcontroller = new ViewController();
		
		Class<ViewController> vc = ViewController.class;
		Field listviewField = vc.getField("listView");
		listviewField.setAccessible(true);
		listviewField.set(viewcontroller, new ListView<String>());
		
		Field f1 = vc.getField("namefield");
		f1.setAccessible(true);
		f1.set(viewcontroller, new TextField());
		
		Field f2 = vc.getField("paramsfield");
		f2.setAccessible(true);
		f2.set(viewcontroller, new TextField());

		Field f3 = vc.getField("returnfield");
		f3.setAccessible(true);
		f3.set(viewcontroller, new TextField());
		
		Field f4 = vc.getField("stdinfield");
		f4.setAccessible(true);
		f4.set(viewcontroller, new TextField());
		
		Field f5 = vc.getField("stdoutfield");
		f5.setAccessible(true);
		f5.set(viewcontroller, new TextField());
		
		Field f6 = vc.getField("methodnamefield");
		f6.setAccessible(true);
		f6.set(viewcontroller, new TextField());
		
		Field f7 = vc.getField("classnamefield");
		f7.setAccessible(true);
		f7.set(viewcontroller, new TextField());
		
		viewcontroller.handleGenerateButtonAction(null);		
		((ListView<String>)listviewField.get(viewcontroller)).getSelectionModel().select(0);
		
		viewcontroller.handleDeleteButtonAction(null);
	}
	
	
	/**TODO
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveButton() throws Exception {
		ViewController viewcontroller = new ViewController();
		//viewcontroller.handleSaveButtonAction(null);
	}
	
	
	@Test
	public void testGetNumberOfTests() throws NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException {
		
		ViewController viewcontroller = new ViewController();
		
		Class<ViewController> vc = ViewController.class;
		Field listviewField = vc.getField("listView");
		listviewField.setAccessible(true);
		listviewField.set(viewcontroller, new ListView<String>());
		
		Field f1 = vc.getField("namefield");
		f1.setAccessible(true);
		f1.set(viewcontroller, new TextField());
		
		Field f2 = vc.getField("paramsfield");
		f2.setAccessible(true);
		f2.set(viewcontroller, new TextField());

		Field f3 = vc.getField("returnfield");
		f3.setAccessible(true);
		f3.set(viewcontroller, new TextField());
		
		Field f4 = vc.getField("stdinfield");
		f4.setAccessible(true);
		f4.set(viewcontroller, new TextField());
		
		Field f5 = vc.getField("stdoutfield");
		f5.setAccessible(true);
		f5.set(viewcontroller, new TextField());
		
		Field f6 = vc.getField("methodnamefield");
		f6.setAccessible(true);
		f6.set(viewcontroller, new TextField());
		
		Field f7 = vc.getField("classnamefield");
		f7.setAccessible(true);
		f7.set(viewcontroller, new TextField());
		
		for (int i = 0; i < 100; i++) {
			viewcontroller.handleGenerateButtonAction(null);
		}
		
		assertEquals(100, viewcontroller.getNumberOfTests());
	}
	
	
	
	
	
}
