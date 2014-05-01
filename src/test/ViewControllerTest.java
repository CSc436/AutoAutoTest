package test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.TestCollection;

import org.junit.BeforeClass;
import org.junit.Test;

import view.ViewController;

/**
 * Tests for the view controller
 * 
 * @author jeffersd
 */

public class ViewControllerTest {

    private static Field listViewField;
    private static ViewController viewController;
    private static Class<ViewController> viewControllerClass;
    private static int currentnumberoftests;
    private static TestCollection testCollection;

    /**
     * Set up reflection for each of the tests.
     * 
     * @throws Exception
     *             If reflection isn't allowed
     */
    @BeforeClass
    public static void makeEverythingPublic() throws Exception {
        currentnumberoftests = 0;
        viewController = new ViewController();
        testCollection = TestCollection.getInstance();
        viewControllerClass = ViewController.class;
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
     * Tests the deletion of a test from the list.
     * 
     * @throws Exception
     *             Due to reflection.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testDeleteButtonAction() throws Exception {
        assertEquals(currentnumberoftests, getActualNumberOfTests());
        
        setupBogusValues("testname", "className", "methodName", "1");
        viewController.handleGenerateButtonAction(null);
        currentnumberoftests += 1;
        assertEquals(currentnumberoftests, getActualNumberOfTests());
        
        ListView<String> theListView = 
                (ListView<String>) listViewField.get(viewController);
        theListView.getSelectionModel().select(0);
        viewController.handleDeleteButtonAction(null);
        currentnumberoftests -= 1;
        assertEquals(currentnumberoftests, getActualNumberOfTests());
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
        for (int i = 0; i < 100; i++) {
            setupBogusValues("testname" + i, "className", "methodName", "1");
            viewController.handleGenerateButtonAction(null);
        }
        currentnumberoftests += 100;
        assertEquals(currentnumberoftests, getActualNumberOfTests());
    }
    
    /**
     * sets up bogus values so no errors are thrown during testing
     * @param testName the test name to use
     * @param className the class name to use
     * @param methodName the method name to use
     * @param returnValue the return value to use
     * 
     * @throws Exception should never throw, field names are correct
     */
    private void setupBogusValues(String testName, String className,
            String methodName, String returnValue) throws Exception {

        Field namefield = viewControllerClass
                .getDeclaredField("namefield");
        Field classfield = viewControllerClass
                .getDeclaredField("classnamefield");
        Field methodfield = viewControllerClass
                .getDeclaredField("methodnamefield");
        Field returnfield = viewControllerClass
                .getDeclaredField("returnfield");
        namefield.setAccessible(true);
        classfield.setAccessible(true);
        methodfield.setAccessible(true);
        returnfield.setAccessible(true);
        ((TextField) namefield
                .get(viewController)).setText(testName);
        ((TextField) classfield
                .get(viewController)).setText(className);
        ((TextField) methodfield
                .get(viewController)).setText(methodName);
        ((TextField) returnfield
                .get(viewController)).setText(returnValue);
    }
    
    /**
     * 
     * @return The actual number of tests in the test collection.
     */
    private int getActualNumberOfTests() {
        return testCollection.testCount();
    }
    
}
