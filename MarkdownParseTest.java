import static org.junit.Assert.*;
import java.util.ArrayList;
import java.nio.file.Path;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.*;
public class MarkdownParseTest {
    @Test
    public void addition(){
        assertEquals(2, 1+1);
    }

    @Test
    public void testLinks() throws IOException {
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> result = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://something.com", "some-thing.html"), result);
    }
}
