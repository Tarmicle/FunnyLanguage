package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserFunctionTest {
    @Test
    void testNullTokenizer() {
        assertThrows(IllegalArgumentException.class, () -> new Parser(null));
    }
}
