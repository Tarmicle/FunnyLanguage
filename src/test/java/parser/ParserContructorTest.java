package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import tokenizer.Tokenizer;
import tokenizer.exceptions.CommentNotClosedException;
import tokenizer.exceptions.StringNotClosedException;
import tokenizer.exceptions.UnaspectedTokenException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParserContructorTest {

    Tokenizer mockTokenizer;

    @BeforeEach
    void init() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException {
        mockTokenizer = mock(Tokenizer.class);
        Answer answers = new MockTokenizerAnswers();
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers);
    }

    @Test
    void programmTest() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException {
        when(mockTokenizer.assertAndNext("{")).thenThrow(new UnaspectedTokenException());
        assertThrows(UnaspectedTokenException.class, () -> new Parser(mockTokenizer).compile());
    }

  /*  @Test
    void testArgs() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException {
        when(mockTokenizer.assertAndNext("{")).thenThrow(new UnaspectedTokenException());
        assertThrows(UnaspectedTokenException.class, () -> new Parser(mockTokenizer).compile());
    }*/

}
