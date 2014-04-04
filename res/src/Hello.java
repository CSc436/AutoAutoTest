import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.FakeStandardIn;
import model.FakeStandardOutput;
import org.junit.Test;

public class Hello {

    public void relaxedAssertEquals(Object expected, Object actual, boolean isIgnoreCasing, boolean isIgnoreWhitespace, boolean isIgnorePunctuation, int floatPrecision) {
        double precision = Math.pow(10, -(floatPrecision));
        if (expected instanceof Double || expected instanceof Float) {
            double expectedValue = (double) expected;
            double actualValue = (double) actual;
            assertEquals(expectedValue, actualValue, precision);
        } else if(expected instanceof String) {
            RelaxedStringFloatCheck checker = new RelaxedStringFloatCheck(
                isIgnoreCasing, isIgnoreWhitespace, isIgnorePunctuation, precision
            );
            assertTrue(checker.isAcceptable((String) expected, (String) actual));
        } else {
            assertEquals(expected, actual);
        }
    }

@Test
public void Hello World() {
   FakeStandardIn fsi = new FakeStandardIn();
fsi.setString("");
System.setIn(fsi);
   FakeStandardOutput fso = new FakeStandardOutput();
   System.setOut(fso);
   final Hello classInstance = new Hello();
   final Object[] returnValue = new Object[1];
Thread studentMethodRunner = new Thread() {
    public void run() {
        returnValue[0] = classInstance.helloWorld();
    }
};
int timeout = 50;
studentMethodRunner.start();
try {Thread.sleep(timeout);}
catch (InterruptedException e) {fail();}
if(studentMethodRunner.isAlive()) {
    studentMethodRunner.stop();
    fail();
}
   relaxedAssertEquals(, returnValue[0], false, false, false, 0);
   relaxedAssertEquals("Hello World", fso.getOutput(), false, false, false, 0);
}


}