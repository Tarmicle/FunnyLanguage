import cmdparser.CommandLineParser;
import tokenizer.Tokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        // The name of the file to open.
        new CommandLineParser(args)
                .initCompiler()
                .compile();

    }
}
