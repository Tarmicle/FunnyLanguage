package tokenizer;

import org.junit.jupiter.api.Test;
import tokenizer.exceptions.CommentNotClosedException;
import tokenizer.exceptions.StringNotClosedException;
import tokenizer.exceptions.TokenizerException;

import java.io.*;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TokenizerCommentsTest {
    @Test
    void checkSkipInlineComments() throws TokenizerException, IOException {
        InputStream is = new ByteArrayInputStream("//hello\na".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        Token a = tokenizer.nextToken();
        assertEquals(a.getStringVal(), "a");
        assertEquals(a.type, Token.TYPE.VARIABLE);
    }
    @Test
    void checkSkipBlockComments() throws TokenizerException, IOException {
        InputStream is = new ByteArrayInputStream("/*hello\nworld*/\na".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        Token a = tokenizer.nextToken();
        assertEquals(a.getStringVal(), "a");
        assertEquals(a.type, Token.TYPE.VARIABLE);
    }
}
