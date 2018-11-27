package parser;

import org.junit.jupiter.api.Test;
import tokenizer.exceptions.TokenizerException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserContructorTest {

    @Test
    void testNullTokenizer() {
        assertThrows(IllegalArgumentException.class, () -> new Parser(null));
    }

}
