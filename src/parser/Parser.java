package parser;

import tokenizer.Token;
import tokenizer.Tokenizer;

import java.io.IOException;

public class Parser {
    Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void compile() throws IOException {
        Token t;
        do {
            t = tokenizer.nextToken();
            System.out.println(t.type +"\t\t" + t.value);
        } while (t.type != Token.TYPE.EOS);
    }
}
