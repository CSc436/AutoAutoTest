import javafx.stage.Stage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ViewTest {

	@Test
	public void testView() throws Exception {
		View myView = new View();
		myView.start(new Stage());
		assertTrue(true);
	}

}
