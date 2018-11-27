package cmdparser;

import parser.Parser;
import tokenizer.Tokenizer;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class CommandLineParser {
    private String fileName;
    private String usage;

    public CommandLineParser(String[] a) {
        if (!(a.length == 1)) {
            System.err.println("Usage: java funny url");
            System.exit(1);
        }
        fileName = a[0];
    }

    public Parser initCompiler() throws FileNotFoundException {
        return new Parser(new Tokenizer(fileName));
    }
}
