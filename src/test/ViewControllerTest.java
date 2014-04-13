package test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import javafx.scene.control.CheckBox;
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
            if (field.getType() == CheckBox.class) {
                field.set(viewController, new CheckBox());
            }
        }
        listViewField = viewControllerClass.getDeclaredField("listView");
        listViewField.setAccessible(true);
        listViewField.set(viewController, new ListView<String>());
    }

    /**
     * Tests the deletion of a test from the list.
     * 
     * @throws Exception
     *             Due to reflection.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testDeleteButtonAction() throws Exception {
        setupBogusValues("testname", "className", "methodName", "1");
        viewController.handleGenerateButtonAction(null);
        currentnumberoftests = TestCollection.getInstance().testCount();
        ListView<String> theListView;
        theListView = (ListView<String>) listViewField.get(viewController);
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
            setupBogusValues("testname" + i, "className", "methodName", "1");
            viewController.handleGenerateButtonAction(null);
        }

        currentnumberoftests += 100;
        int actual = TestCollection.getInstance().testCount();
        assertEquals(currentnumberoftests, actual);
    }
    
    /**
     * sets up bogus values so no errors are thrown during testing
     * @param testName the test name to use
     * @param className the class name to use
     * @param methodName the method name to use
     * @param returnValue the return value to use
     */
    private void setupBogusValues(String testName, String className, 
            String methodName, String returnValue) {
        viewController.getNameField().setText(testName);
        viewController.getClassNameField().setText(className);
        viewController.getMethodNameField().setText(methodName);
        viewController.getReturnField().setText(returnValue);
    }

}
