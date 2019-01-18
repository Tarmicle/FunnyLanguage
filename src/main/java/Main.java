import cmdparser.CommandLineParser;
import parser.Expr;
import parser.InterpreterException;
import parser.UnexpectedSymbolException;
import tokenizer.Tokenizer;
import tokenizer.exceptions.CommentNotClosedException;
import tokenizer.exceptions.UnaspectedTokenException;
import tokenizer.exceptions.StringNotClosedException;
import tokenizer.exceptions.TokenizerException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        // write your code here
        // The name of the file to open.
        Expr programm;
        try {
            programm = new CommandLineParser(args)
                    .initCompiler()
                    .compile();
        } catch (Exception e) {
            System.err.println("Compiler exception");
            e.printStackTrace();
            return;
        }

        //new CommandLineParser(args).initCompiler().genTest();
        try {
            programm.eval(null);
        } catch (InterpreterException e) {
            System.err.println("Interpreter exception");
            e.printStackTrace();
        }

    }
}
