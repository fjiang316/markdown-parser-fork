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
        assertEquals(3, 2+1);
    }

    @Test
    public void testLinks() throws IOException {
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> result = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://something.com", "some-thing.html"), result);
    }

    @Test
    public void testLinks2() throws IOException {
        Path fileName = Path.of("test2.md");
        String content = Files.readString(fileName);
        ArrayList<String> result = MarkdownParse.getLinks(content);
        assertEquals(List.of(), result);
    }

    @Test
    public void testLinks3() throws IOException {
        Path fileName = Path.of("test.md");
        String content = Files.readString(fileName);
        ArrayList<String> result = MarkdownParse.getLinks(content);
        assertEquals(List.of(), result);
    }

    @Test
    public void testLinks4() throws IOException {
        Path fileName = Path.of("new-test.md");
        String content = Files.readString(fileName);
        ArrayList<String> result = MarkdownParse.getLinks(content);
        assertEquals(List.of("google.com", "new-website.html", "https://ucsd.edu"), result);
    }

    @Test
    public void testSnippet1() throws IOException {
        Path fileName = Path.of("Snippet1.md");
        String content = Files.readString(fileName);
        ArrayList<String> result = MarkdownParse.getLinks(content);
        assertEquals(List.of("`google.com", "google.com", "ucsd.edu"), result);
    }

    @Test
    public void testSnippet2() throws IOException {
        Path fileName = Path.of("Snippet2.md");
        String content = Files.readString(fileName);
        ArrayList<String> result = MarkdownParse.getLinks(content);
        assertEquals(List.of("a.com", "a.com(())", "example.com"), result);
    }

    @Test
    public void testSnippet3() throws IOException {
        Path fileName = Path.of("Snippet3.md");
        String content = Files.readString(fileName);
        ArrayList<String> result = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://www.twitter.com", "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule", "https://cse.ucsd.edu/"), result);
    }
}
