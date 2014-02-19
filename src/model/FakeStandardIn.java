package model;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class allows us to redirect pre-determined input into a program which
 * uses a Scanner object to receive user input via the keyboard.
 */
public class FakeStandardIn extends InputStream {
    private String buffer;
    private int currentIndex;

    /**
     * Default constructor, creates a FakeStandardIn object with nothing in the
     * buffer. The user will add data later.
     */
    public FakeStandardIn() {
        buffer = "";
        currentIndex = 0;
    } // end of default constructor

    /**
     * Allow the buffer string to be created or reset The current index will be
     * reset to 0 when this happens.
     * 
     * @param inputStr
     *            The entire contents of the buffer, with each token separated
     *            by a newline \n character, final token may or may not be
     *            followed by a newline.
     * 
     */
    public void setString(String inputStr) {
        if ((inputStr == null) || (inputStr.length() < 1)) {
            buffer = "\n";
            currentIndex = 0;
        } else {
            // check to see if string already ends with newline or if we must
            // add one
            int l = inputStr.length();
            // if input is already newline-terminated
            if (inputStr.charAt(l - 1) == '\n') {
                buffer = inputStr;
            } else {
                buffer = inputStr + "\n";
            }
            currentIndex = 0;
        } // end of valid input string
    } // end of setString

    /**
     * Reads the next character from the buffer, and returns it as an integer
     * value from 0 to 255. If the end of the buffer has been reached, returns
     * -1.
     * 
     * @return The number of characters actually read, or -1 if the buffer has
     *         been exhausted.
     * 
     * @throws IOException (This should never actually happen).
     */
    @Override
    public int read() throws IOException {
        // if there are no more characters to read
        if (currentIndex == buffer.length()) {
            return -1;
        }
        currentIndex++; // advance index before we return
        return buffer.getBytes("UTF8")[currentIndex - 1];
    }

    /**
     * Reads characters up to the length of b, or the remaining unread
     * characters in the buffer, whichever is less. If the length of b is 0,
     * then returns 0. If the end of the buffer has been reached, returns -1.
     * Otherwise, at least one character/byte has been read, and the return
     * value is the number of bytes read.
     * 
     * @param b
     *            An array of type byte, which will be filled with the
     *            characters from the buffer, up to the lesser of size of b or
     *            remaining number of characters in the buffer.
     * 
     * @return The number of characters actually read, or -1 if the buffer has
     *         been exhausted.
     * 
     * @throws IOException (This should never actually happen).
     */
    @Override
    public int read(byte[] b) throws IOException {
        // result array is null, cannot hold any characters
        if (b == null) {
            return 0;
        }

        int resultLength = b.length;

        // result array cannot hold any characters
        if (resultLength == 0) {
            return 0;
        }

        // entire contents of buffer has been used
        if (currentIndex >= buffer.length()) {
            return -1;
        }

        // if we get here, then we can read at least one character
        // and process it into the appropriate byte value

        int charsRead = 0;

        for (int i = 0; i < resultLength; i++) {
            // typecast character into byte value
            b[i] = (byte) buffer.charAt(currentIndex);

            currentIndex++; // increment position in buffer
            charsRead++; // increment number of characters read

            // check to see if input buffer has been exhausted
            if (currentIndex >= buffer.length()) {
                return charsRead;
            }
        } // end of looping through and reading characters

        // returns the number of characters read and stored into array
        return charsRead;
    }

    /**
     * Attempts to read up to len bytes from the buffer, assuming that there are
     * that many unprocessed characters remaining. If len is 0, then nothing is
     * read, and returns 0. If buffer has been exhausted, then nothing is read,
     * and returns -1. Otherwise, at least one byte is read and stored in
     * b[off].
     * 
     * @param b
     *            An array of type byte, which will be filled with the
     *            characters from the buffer up to the lesser of
     *            b.length-offset, len, or remaining unread characters in the
     *            buffer.
     * 
     * @param off
     *            The offset (number of array cells to skip) for storing the
     *            characters into b. For example, and offset of 1 means that we
     *            start storing characters into b[1].
     * 
     * @param len
     *            The maximum number of characters that we want to read
     *            (although we may read less due to exhausting the array b or
     *            exhausting the buffer).
     * 
     * @return The number of characters actually read, or -1 if the buffer has
     *         been exhausted.
     * 
     * @throws IOException (This should never actually happen).
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if ((b == null) || (len == 0)) {
            return 0; // result array is null or nothing asked to read
        }

        if (off < 0) {
            return 0; // invalid offset -- do nothing and quit out of procedure
        }

        int resultLength = b.length;

        // result array cannot hold any characters
        if (resultLength == 0) {
            return 0;
        }

        // entire contents of buffer has been used
        if (currentIndex >= buffer.length()) {
            return -1;
        }

        // if we get here, we can read and process at least one character

        int charsRead = 0;

        // start i at the offset index
        for (int i = off; i < resultLength; i++) {
            // typecast character into byte value
            b[i] = (byte) buffer.charAt(currentIndex);

            currentIndex++; // increment position in buffer
            charsRead++; // increment number of characters read

            // check to see if input buffer has been exhausted
            if (currentIndex >= buffer.length()) {
                return charsRead;
            }
        } // end of looping through and reading characters
        return charsRead;
    } // end of read
}
