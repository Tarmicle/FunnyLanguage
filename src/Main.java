import cmdparser.CommandLineParser;
import parser.Expr;
import parser.InterpreterException;
import tokenizer.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CommentNotClosedException, StringNotClosedException, InvalidSymbolException, TokenizerException, InterpreterException {
        // write your code here
        // The name of the file to open.
        Expr programm = new CommandLineParser(args)
                .initCompiler()
                .compile();
        programm.eval(null);

    }
}
