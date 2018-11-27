package tokenizer;

import org.junit.jupiter.api.Test;
import tokenizer.exceptions.TokenizerException;

import java.io.*;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenizerContructorTest {

    @Test
    void checkHasPreviousFalse() {
        InputStream is = new ByteArrayInputStream("".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        assertFalse(tokenizer.hasPrevious());
    }
    @Test
    void checkFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> new Tokenizer("."));
    }

    @Test
    void checkEarlyUndo() {
        InputStream is = new ByteArrayInputStream("1 0".getBytes(Charset.defaultCharset()));
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new InputStreamReader(is)));
        assertThrows(TokenizerException.class, tokenizer::undoNext);
    }
}
