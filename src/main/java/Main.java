import cmdparser.CommandLineParser;
import parser.Expr;
import parser.InterpreterException;
import parser.UnexpectedSymbolException;
import tokenizer.exceptions.CommentNotClosedException;
import tokenizer.exceptions.InvalidSymbolException;
import tokenizer.exceptions.StringNotClosedException;
import tokenizer.exceptions.TokenizerException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CommentNotClosedException, StringNotClosedException, InvalidSymbolException, TokenizerException, InterpreterException, UnexpectedSymbolException {
        // write your code here
        // The name of the file to open.
        Expr programm = new CommandLineParser(args)
                .initCompiler()
                .compile();
        programm.eval(null);
    }
}
