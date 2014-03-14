package test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.TestCollection;

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
     * This is used for testing, because our test collection is a singleton.
     */
    private int currentnumberoftests;

    /**
     * Set up reflection for each of the tests.
     * 
     * @throws Exception
     *             If reflection isn't allowed
     */
    @Before
    public void makeEverythingPublic() throws Exception {
        currentnumberoftests = 0;
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
     * Tests the 'handleGenerateButtonAction' method.
     * 
     * @throws Exception
     *             Due to reflection.
     */
    @Test
    public void testGenerateButtonAction() throws Exception {
        currentnumberoftests = TestCollection.getInstance().testCount();

        for (int i = 0; i < 100; i++) {
            viewController.handleGenerateButtonAction(null);
        }

        currentnumberoftests += 100;
        int actual = TestCollection.getInstance().testCount();
        assertEquals(currentnumberoftests, actual);
    }

    /**
     * Tests the deletion of a test from the list.
     * 
     * @throws Exception
     *             Due to reflection.
     */
    @Test
    public void testDeleteButtonAction() throws Exception {
        viewController.handleGenerateButtonAction(null);
        currentnumberoftests = TestCollection.getInstance().testCount();
        ListView<String> theListView = (ListView<String>) listViewField
                .get(viewController);
        theListView.getSelectionModel().select(0);
        viewController.handleDeleteButtonAction(null);
        currentnumberoftests -= 1;
        
        int actual = TestCollection.getInstance().testCount();
        assertEquals(currentnumberoftests, actual);
    }

    /**
     * Tests getting the number of tests. This particular test creates 100
     * tests, then verifies that there are 100 tests using the
     * getNumberOfTests() method.
     * 
     * @throws Exception
     *             Due to reflection.
     */
    @Test
    public void testGetNumberOfTests() throws Exception {
        currentnumberoftests = TestCollection.getInstance().testCount();
        for (int i = 0; i < 100; i++) {
            viewController.handleGenerateButtonAction(null);
        }

        currentnumberoftests += 100;
        int actual = TestCollection.getInstance().testCount();
        assertEquals(currentnumberoftests, actual);
    }

}
