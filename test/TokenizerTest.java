import org.junit.jupiter.api.Test;
import tokenizer.*;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

public class TokenizerTest {

    @Test
    void testEmptyReader() throws StringNotClosedException, IOException, CommentNotClosedException {
        InputStream is = new ByteArrayInputStream("".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        assertEquals(Token.TYPE.EOS, tokenizer.nextToken().type);
    }

    @Test
    void testNumber() throws StringNotClosedException, IOException, CommentNotClosedException {
        InputStream is = new ByteArrayInputStream("2".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        Token next = tokenizer.nextToken();
        assertEquals(Token.TYPE.NUMBER, next.type);
        assertEquals(new BigDecimal(2), next.bigDecimal);
    }

    @Test
    void testVariable() throws StringNotClosedException, IOException, CommentNotClosedException {
        InputStream is = new ByteArrayInputStream("{_true, _false, _if ->".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        Token next = tokenizer.nextToken();
        next = tokenizer.nextToken();
        assertEquals(Token.TYPE.VARIABLE, next.type);
        assertEquals("_true", next.value);
    }

    @Test
    void checkAndNext() throws StringNotClosedException, IOException, CommentNotClosedException {
        InputStream is = new ByteArrayInputStream("{_true, _false, _if ->".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        Token next = tokenizer.nextToken();
        assertDoesNotThrow(() -> tokenizer.assertAndNext("_true"));
        assertThrows(InvalidSymbolException.class, () -> tokenizer.assertAndNext("_true"));

    }
}
