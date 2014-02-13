package model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TemplateReaderTest {

    /**
     * reads the test file and ensures it was read correctly, prints it out for
     * user verification too.
     */
    @Test
    public void testFileRead() {
        String str = TemplateReader
                .readTemplate("TestFileForTemplateReaderClass.txt");
        System.out.println(str);
        assertTrue(str.equals("@Test\n" + "public void domp {\n"
                + "   assertEquals(plade wunt man);\n" + "}"));
    }
}
