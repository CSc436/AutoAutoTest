package test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import org.junit.Before;
import org.junit.Test;

import view.ViewController;

/**
 * Tests for the view controller
 * 
 * @author jeffersd
 * 
 */

public class ViewControllerTest {
    
    private Field listViewField;
    private ViewController viewController;
    
    /**
     * Set up reflection for each of the tests.
     * 
     * @throws Exception If reflection isn't allowed
     */
    @Before
    public void makeEverythingPublic() throws Exception {
        viewController = new ViewController();
        Class<ViewController> viewControllerClass = ViewController.class;
        for (Field field : viewControllerClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() == TextField.class) {
                field.set(viewController, new TextField());
            }
        }
        listViewField = viewControllerClass.getDeclaredField("listView");
        listViewField.setAccessible(true);
        listViewField.set(viewController, new ListView<String>());
    }
    
    /**
     * Tests the creation and storage of tests.
     * It creates ten thousand tests.
     */
    @Test
    public void testGenerateTest() {
        ViewController viewcontroller = new ViewController();
        for (int i = 0; i < 10000; i++) {
            viewcontroller.setAllFields(Integer.toString(i));
            // TestCase T = viewcontroller.generateTest();
            // assertEquals(viewcontroller.TC.getTest(i), T);
        }
        // assertEquals(20000, viewcontroller.getNumberOfTests());
    }

    /**
     * Tests the 'handleGenerateButtonAction' method.
     * 
     * @throws Exception
     *             Due to reflection.
     */
    @Test
    public void testGenerateButtonAction() throws Exception {
        ViewController viewcontroller = new ViewController();
        viewcontroller.handleGenerateButtonAction(null);
    }

    /**
     * Tests the 'deletebutton' action.
     * 
     * @throws NoSuchFieldException
     *             Due to reflection.
     * @throws IllegalAccessException
     *             Due to reflection.
     * 
     */
    @Test
    public void testDeleteButtonAction() throws NoSuchFieldException,
            IllegalAccessException {
        ViewController viewcontroller = new ViewController();
        viewcontroller.handleDeleteButtonAction(null);
        viewcontroller.handleDeleteButtonAction(null);
    }

    /**Tests the deletion of a button.
     * 
     * @throws Exception Due to reflection.
     */
    @Test
    public void testDeleteButtonAction1() throws Exception {

        ViewController viewcontroller = new ViewController();
        viewcontroller.handleGenerateButtonAction(null);
        ListView<String> theListView =
                (ListView<String>) listViewField.get(viewController);
        theListView.getSelectionModel().select(0);

        viewcontroller.handleDeleteButtonAction(null);
    }

    /**Tests getting the number of exceptions.
     * 
     * @throws Exception Due to reflection.
     */
    @Test
    public void testGetNumberOfTests() throws Exception {
        for (int i = 0; i < 100; i++) {
            viewController.handleGenerateButtonAction(null);
        }

        assertEquals(100, viewController.getNumberOfTests());
    }

}
