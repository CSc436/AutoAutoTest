import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.FakeStandardIn;
import model.FakeStandardOutput;
import org.junit.Test;

public class SampleStringTest {

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
public void oppositeTest() {
   FakeStandardIn fsi = new FakeStandardIn();
fsi.setString("");
System.setIn(fsi);
   FakeStandardOutput fso = new FakeStandardOutput();
   System.setOut(fso);
   final StudentSolutionSet1 classInstance = new StudentSolutionSet1();
   final Object[] returnValue = new Object[1];
Thread studentMethodRunner = new Thread() {
    public void run() {
        returnValue[0] = classInstance.opposite(false);
    }
};
int timeout = 1000;
studentMethodRunner.start();
try {Thread.sleep(timeout);}
catch (InterruptedException e) {fail();}
if(studentMethodRunner.isAlive()) {
    studentMethodRunner.stop();
    fail();
}
   relaxedAssertEquals(true, returnValue[0], false, false, false, 2);
   relaxedAssertEquals("", fso.getOutput(), false, false, false, 2);
}
@Test
public void badOppositeTest() {
   FakeStandardIn fsi = new FakeStandardIn();
fsi.setString("");
System.setIn(fsi);
   FakeStandardOutput fso = new FakeStandardOutput();
   System.setOut(fso);
   final StudentSolutionSet1 classInstance = new StudentSolutionSet1();
   final Object[] returnValue = new Object[1];
Thread studentMethodRunner = new Thread() {
    public void run() {
        returnValue[0] = classInstance.badOpposite(false);
    }
};
int timeout = 1000;
studentMethodRunner.start();
try {Thread.sleep(timeout);}
catch (InterruptedException e) {fail();}
if(studentMethodRunner.isAlive()) {
    studentMethodRunner.stop();
    fail();
}
   relaxedAssertEquals(true, returnValue[0], false, false, false, 2);
   relaxedAssertEquals("", fso.getOutput(), false, false, false, 2);
}
@Test
public void appendComTest() {
   FakeStandardIn fsi = new FakeStandardIn();
fsi.setString("");
System.setIn(fsi);
   FakeStandardOutput fso = new FakeStandardOutput();
   System.setOut(fso);
   final StudentSolutionSet1 classInstance = new StudentSolutionSet1();
   final Object[] returnValue = new Object[1];
Thread studentMethodRunner = new Thread() {
    public void run() {
        returnValue[0] = classInstance.appendCom(bannana);
    }
};
int timeout = 1000;
studentMethodRunner.start();
try {Thread.sleep(timeout);}
catch (InterruptedException e) {fail();}
if(studentMethodRunner.isAlive()) {
    studentMethodRunner.stop();
    fail();
}
   relaxedAssertEquals(bannana.com, returnValue[0], false, false, false, 2);
   relaxedAssertEquals("", fso.getOutput(), false, false, false, 2);
}
@Test
public void badAppendComTest() {
   FakeStandardIn fsi = new FakeStandardIn();
fsi.setString("");
System.setIn(fsi);
   FakeStandardOutput fso = new FakeStandardOutput();
   System.setOut(fso);
   final StudentSolutionSet1 classInstance = new StudentSolutionSet1();
   final Object[] returnValue = new Object[1];
Thread studentMethodRunner = new Thread() {
    public void run() {
        returnValue[0] = classInstance.badAppendCom(bannana);
    }
};
int timeout = 1000;
studentMethodRunner.start();
try {Thread.sleep(timeout);}
catch (InterruptedException e) {fail();}
if(studentMethodRunner.isAlive()) {
    studentMethodRunner.stop();
    fail();
}
   relaxedAssertEquals(bannana.com, returnValue[0], false, false, false, 2);
   relaxedAssertEquals("", fso.getOutput(), false, false, false, 2);
}


}