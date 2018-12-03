package parser;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import tokenizer.Token;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockTokenizerAnswers implements Answer {
    private int tockenNumber = 0;
    List<Token> mockTocken = new ArrayList<>();

    @Override
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
        mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
        mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
        mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
        mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
        mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(10)));
        mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));
        mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
        return mockTocken.get(tockenNumber++);
    }
}
