package parser;

import tokenizer.CommentNotClosedException;
import tokenizer.StringNotClosedException;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.io.IOException;

public class Parser {
    Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public BinaryTree compile() throws IOException, StringNotClosedException, CommentNotClosedException {
        Token t;
        do {

            t = tokenizer.nextToken();
        } while (t.type != Token.TYPE.EOS);
        return new BinaryTree();
    }

    public void function() throws StringNotClosedException, IOException, CommentNotClosedException {
        tokenizer.nextToken(); // {
        optParams();
        optLocals();
        optSequence();
        tokenizer.nextToken(); // }
    }

    private void optParams() {
        optIds();
    }

    private void optLocals() {
    }

    private void optSequence() {
    }

    private void optIds() {

    }
    private void id(){

    }
}
