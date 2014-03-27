package studentSolutions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.FakeStandardOutput;

import org.junit.Test;

public class StudentSolutionSet1Test {

    public void relaxedAssertEquals(Object expected, Object actual, boolean isIgnoreCasing, boolean isignoreWhitespace, boolean isignorePunctuation, double floatPrecision) {
        if (expected instanceof Double || expected instanceof Float) {
            double expectedValue = (double) expected;
            double actualValue = (double) actual;
            assertEquals(expectedValue, actualValue, 1e-(floatPrecsion));
        } else if(expected instanceof String) {
            RelaxedStringFloatCheck checker = new RelaxedStringFloatCheck(
                isIgnoreCasing, isIgnoreWhitespace, isIgnorePunctuation, floatPrecision
            );
            assertTrue(checker.isAcceptable((String) expected, (String) actual);
        } else {
            assertEquals(expected, actual);
        }
    }

@Test
public void add1test() {
   FakeStandardInput fsi = new FakeStandardInput();
fsi.setString("");
System.setIn(fsi);
   FakeStandardOutput fso = new FakeStandardOutput();
   System.setOut(fso);
   final StudentSolutionSet1 classInstance = new StudentSolutionSet1();
   final Object returnValue;
Thread studentMethodRunner = new Thread() {
    public void run() {
        returnValue = classInstance.add1(1);
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
   relaxedAssertEquals(2, returnValue, false, IS_IGNORE_WHITESPACE, false, 2);
   relaxedAssertEquals("", fso.getOutput(), false, IS_IGNORE_WHITESPACE, false, 2);
}
@Test
public void appendComtest() {
   FakeStandardInput fsi = new FakeStandardInput();
fsi.setString("");
System.setIn(fsi);
   FakeStandardOutput fso = new FakeStandardOutput();
   System.setOut(fso);
   StudentSolutionSet1 classInstance = new StudentSolutionSet1();
   Object returnValue;
Thread studentMethodRunner = new Thread() {
    public void run() {
        returnValue = classInstance.appendCom(boot);
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
   relaxedAssertEquals(boot.com, returnValue, false, IS_IGNORE_WHITESPACE, false, 2);
   relaxedAssertEquals("", fso.getOutput(), false, IS_IGNORE_WHITESPACE, false, 2);
}
@Test
public void oppositetest() {
   FakeStandardInput fsi = new FakeStandardInput();
fsi.setString("");
System.setIn(fsi);
   FakeStandardOutput fso = new FakeStandardOutput();
   System.setOut(fso);
   StudentSolutionSet1 classInstance = new StudentSolutionSet1();
   Object returnValue;
Thread studentMethodRunner = new Thread() {
    public void run() {
        returnValue = classInstance.oppositetest(true);
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
   relaxedAssertEquals(false, returnValue, false, IS_IGNORE_WHITESPACE, false, 2);
   relaxedAssertEquals("", fso.getOutput(), false, IS_IGNORE_WHITESPACE, false, 2);
}


}