package model;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class allows us to redirect pre-determined input into a program which
 * uses a Scanner object to receive user input via the keyboard.
 * 
 * @author William cohen
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
    }

    /**
     * Allow the buffer string to be created or reset. The current index will be
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
            if (inputStr.endsWith("\n")) {
                buffer = inputStr;
            } else {
                buffer = inputStr + "\n";
            }
            currentIndex = 0;
        }
    }

    /**
     * Reads the next character from the buffer, and returns it as an integer
     * value from 0 to 255. If the end of the buffer has been reached, returns
     * -1.
     * 
     * @return The number of characters actually read, or -1 if the buffer has
     *         been exhausted.
     * 
     * @throws IOException
     *             (This should never actually happen).
     */
    @Override
    public int read() throws IOException {
        // if there are no more characters to read
        if (currentIndex >= buffer.length()) {
            return -1;
        }
        byte result = (byte) buffer.charAt(currentIndex);
        currentIndex++; // advance index before we return
        return result;
    }

    /**
     * Reads characters up to the length of b, or the remaining unread
     * characters in the buffer, whichever is less.
     * 
     * @param b
     *            An array of type byte, which will be filled with the
     *            characters from the buffer, up to the lesser of size of b or
     *            remaining number of characters in the buffer.
     * 
     * @return The number of characters actually read, or -1 if the buffer has
     *         been exhausted. If the length of b is 0, then no characters are
     *         read.
     * 
     * @throws IOException
     *             (This should never actually happen).
     */
    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    /**
     * Attempts to read up to len bytes from the buffer, assuming that there are
     * that many unprocessed characters remaining. Bytes read are stored
     * beginning at b[off].
     * 
     * @param b
     *            An array of type byte, which will be filled with the
     *            characters from the buffer up to the lesser of b.length-offset
     *            to len, or remaining unread characters in the buffer.
     * 
     * @param off
     *            The offset (number of array cells to skip) for storing the
     *            characters into b. For example, and offset of 1 means that we
     *            start storing characters into b[1].
     * 
     * @param len
     *            The maximum number of characters that we want to read
     *            (although we may read less due to exhausting the buffer).
     * 
     * @return The number of characters actually read, or -1 if the buffer has
     *         been exhausted.
     * 
     * @throws IOException
     *             (This should never actually happen).
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (len < 0 || off < 0 || off + len > b.length) {
            throw new IndexOutOfBoundsException();
        }

        if (currentIndex >= buffer.length()) {
            return -1;
        }

        return privateRead(b, off, len);
    }

    /**
     * Fill the given byte[] with bytes from the buffer. It is assumed that off
     * and len are valid (non-negative) parameters.
     * 
     * @param b
     *            The byte array to fill
     * @param off
     *            The starting index
     * @param len
     *            The amount of characters to read
     * @return The number of character read
     * @throws IOException
     *             (This will never happen)
     */
    private int privateRead(byte[] b, int off, int len) throws IOException {
        int charsRead = 0;
        int target = off + len;

        for (int i = off; i < target; i++) {
            byte nextByte = (byte) read();
            if (nextByte == -1) {
                break;
            }
            b[i] = nextByte;
            charsRead++;
        }

        return charsRead;
    }
}

