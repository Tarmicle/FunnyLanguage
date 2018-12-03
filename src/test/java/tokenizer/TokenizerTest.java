package tokenizer;

import org.junit.jupiter.api.Test;
import tokenizer.exceptions.CommentNotClosedException;
import tokenizer.exceptions.UnaspectedTokenException;
import tokenizer.exceptions.StringNotClosedException;
import tokenizer.exceptions.TokenizerException;

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
    void checkMultipleUndo() throws StringNotClosedException, IOException, CommentNotClosedException, TokenizerException {
        InputStream is = new ByteArrayInputStream("1 0".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        Token next = tokenizer.nextToken();
        assertEquals(BigDecimal.ONE, next.getBigDecimalVal());
        next = tokenizer.nextToken();
        assertEquals(BigDecimal.ZERO, next.getBigDecimalVal());
        for (int i = 0; i < 3; i++) {
            tokenizer.undoNext();
            next = tokenizer.nextToken();
            assertEquals(BigDecimal.ZERO, next.getBigDecimalVal());
        }
    }

    @Test
    void checkEarlyUndo() {
        InputStream is = new ByteArrayInputStream("1 0".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        assertThrows(TokenizerException.class, tokenizer::undoNext);
    }

    @Test
    void checkDoubleUndoException() throws StringNotClosedException, IOException, CommentNotClosedException, TokenizerException {
        InputStream is = new ByteArrayInputStream("1 0".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        tokenizer.nextToken();
        tokenizer.undoNext();
        assertThrows(TokenizerException.class, tokenizer::undoNext);
    }

    @Test
    void checkAndNext() throws StringNotClosedException, IOException, CommentNotClosedException {
        InputStream is = new ByteArrayInputStream("{_true, _false, _if ->".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        Token next = tokenizer.nextToken();
        assertDoesNotThrow(() -> tokenizer.assertAndNext("_true"));
        assertThrows(UnaspectedTokenException.class, () -> tokenizer.assertAndNext("_true"));
    }


    @Test
    void checkEndOfStream() throws StringNotClosedException, IOException, CommentNotClosedException {
        InputStream is = new ByteArrayInputStream("".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        Token next = tokenizer.nextToken();
        assertEquals(Token.TYPE.EOS, next.getType());
        next = tokenizer.nextToken();
        assertEquals(Token.TYPE.EOS, next.getType());
    }

    @Test
    void testAssertAndNext() throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException {
        InputStream is = new ByteArrayInputStream("a b".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        Token a = tokenizer.assertAndNext("a");
        assertEquals("b", a.getStringVal());
    }
}
