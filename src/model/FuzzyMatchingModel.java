package model;

import org.apache.commons.lang.StringUtils;



/**
 * @author Carlos G
 *
 */
public class FuzzyMatchingModel {
    
    
    /**
     * @param one
     * @param two
     * @return result, which is the the ratio
     * of the two of total number num of combined cars
     * minus the number of changes needed to change one
     * string to the other, divided by the total number
     * of chars from both strings.
     */
    public double getRatio(String one, String two){
        
        int changes = StringUtils.getLevenshteinDistance(one,two);
        int totalChars = one.length() + two.length();
        int result = (totalChars - changes)/totalChars;
        return result;
    }
}
