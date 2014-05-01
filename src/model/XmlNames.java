package model;

/**
 * Used in save/loading.  
 * When saving, this class associates the value with the name
 * When loading, this class gathers the value associated with the name
 * @author Ricky
 *
 */
public class XmlNames {
    /**
     * private constructor so nobody can instantiate this class
     */
    private XmlNames() {
        
    }
    
    /**
     * test name tag
     */
    public static final String SAVE_TEST_NAME_NAME = "name";
    
    /**
     * name of args
     */
    public static final String SAVE_ARGS_NAME = "Args";
    /**
     * name of className
     */
    public static final String SAVE_CLASS_NAME = "ClassName";
    /**
     * name of ExpectedReturn
     */
    public static final String SAVE_EXPECTED_RETURN_NAME = "ExpectedReturn";
    /**
     * name of ExpectedStdOut
     */
    public static final String SAVE_EXPECTED_STD_OUT_NAME = 
            "ExpectedStandardOut";
    /**
     * name of method
     */
    public static final String SAVE_METHOD_NAME = "MethodName";
    /**
     * name of floatPrecision
     */
    public static final String SAVE_FLOAT_PRECISION_NAME = "FloatPrecision";
    /**
     * name of stockedInput
     */
    public static final String SAVE_STOCKED_INPUT_NAME = "StockedInput";
    /**
     * name of timeoutTime
     */
    public static final String SAVE_TIMEOUT_TIME_NAME = "TimeoutTime";
    /**
     * name of isIgnoreCasing
     */
    public static final String SAVE_IS_IGNORE_CASING_NAME = 
            "IsIgnoreCasing";
    /**
     * name of isIgnorePunctuation
     */
    public static final String SAVE_IS_IGNORE_PUNCTUATION_NAME = 
            "IsIgnorePunctuation";
    /**
     * name of isIgnoreWhitespace
     */
    public static final String SAVE_IS_IGNORE_WHITESPACE_NAME = 
            "IsIgnoreWhitespace";
    /**
     * name of isVoid
     */
    public static final String SAVE_IS_VOID_NAME = "IsVoid";
}
