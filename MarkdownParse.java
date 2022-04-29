//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            if (openBracket == -1) {
                break;
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            if (closeBracket == -1) {
                break;
            }
            int openBracket2 = markdown.indexOf("[", openBracket+1);
            if (openBracket2 < closeBracket && openBracket2 != -1) {
                closeBracket = markdown.indexOf("]", closeBracket+1);
            }
            int openParen = markdown.indexOf("(", closeBracket);
            //System.out.println("open1: " + openBracket + " open2: " + openBracket2);
            if (openParen == -1) {
                break;
            }
            int closeParen = markdown.indexOf(")", openParen);
            if (closeParen == -1) {
                break;
            }
            String link = markdown.substring(openParen + 1, closeParen);
            currentIndex = closeBracket + 1;
            if (link.contains(" ")) {
                continue;
            }
            /*else if (openParen - closeBracket != 1) {
                continue;
            }*/
            else if (openBracket != 0) {
                if (markdown.substring(openParen-1, openParen).equals("!")) {
                    continue;
                }
                else {
                    toReturn.add(link);
                }
            }
            
            
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
