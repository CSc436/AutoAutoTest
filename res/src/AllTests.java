import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 *@author: Dillon
 */

// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
  SampleIntTest.class, 
  SampleStandardOutTest.class,
  SampleBooleanTest.class,
  SampleStringTest.class}
)

public class AllTests {
}
