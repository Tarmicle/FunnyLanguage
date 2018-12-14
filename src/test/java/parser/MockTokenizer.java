package parser;

import org.mockito.stubbing.Answer;
import tokenizer.Token;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockTokenizer {
    private int tockenNumber = 0;
    List<Token> mockTocken;


    public Answer provide_assertAndNext_Answer() {
        return (Answer<Token>) invocationOnMock -> {
            tockenNumber++;
            return mockTocken.get(tockenNumber++);
        };
    }

    public Answer provide_next_Answer() {
        return (Answer<Token>) invocationOnMock -> mockTocken.get(tockenNumber++);
    }

    public MockTokenizer(Code code) {
        switch (code) {
            case A_EQUAL_10:
                mockTocken = new ArrayList<>();
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(10)));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case SIMPLE_ADD_CLOSURE:
                mockTocken = new ArrayList<>();
                // {fn->fn={(a)->a=a+1}; print(fn(2));}

                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.PLUS, "+"));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(1)));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(2)));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case SIMPLE_ADD_STRING_CLOSURE:
                mockTocken = new ArrayList<>();
                // {fn->fn={(a)->a=a+1}; print(fn(2));}

                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.PLUS, "+"));
                mockTocken.add(new Token(Token.TYPE.STRING, "mondo"));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));
                //  print(fn(2));   ---
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.STRING, "Ciao..."));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));
                //                  ---


                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case SIMPLE_SUBTRACT_CLOSURE:
                mockTocken = new ArrayList<>();
                // {fn->fn={(a)->a=a-1}; print(fn(2));}

                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.MINUS, "-"));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(1)));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                //  print(fn(2));   ---
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(2)));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                //  End of closure  ---
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case SIMPLE_MULTIPLICATION_CLOSURE:
                mockTocken = new ArrayList<>();
                // {fn->fn={(a)->a=a*7}; print(fn(6));}

                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.ABSTERISC, "*"));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(7)));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                //  print(fn(2));   ---
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(6)));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                //  End of closure  ---
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case CONSEC_MULTIPLICATION_CLOSURE:
                mockTocken = new ArrayList<>();
                // {fn->fn={(a)->a=2*5*7}; print(fn(6));}
                //                                  └-> insignificante

                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(2)));
                mockTocken.add(new Token(Token.TYPE.ABSTERISC, "*"));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(5)));
                mockTocken.add(new Token(Token.TYPE.ABSTERISC, "*"));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(7)));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                //  print(fn(2));   ---
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "fn"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(6)));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                //  End of closure  ---
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case COUNTER_MAKER_CLOUSURE:
                mockTocken = new ArrayList<>();
                /*
                {makeCounter myCounter yourCounter ->
                    makeCounter = {(balance) ->
                        {(amount) -> balance = balance * amount}
                    };

                    myCounter = makeCounter(100);
                    yourCounter = makeCounter(50);
                    println("myCounter: ", myCounter(0));
                    println("yourCounter: ", yourCounter(0));
                 }
                 */

                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "makeCounter"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "myCounter"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "yourCounter"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));

                // makeCounter = {(balance) ->
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "makeCounter"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "balance"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));

                //  {(amount) -> balance = balance * amount}
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "amount"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "balance"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "balance"));
                mockTocken.add(new Token(Token.TYPE.ABSTERISC, "*"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "amount"));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));

                // };
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                // myCounter = makeCounter(100);
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "myCounter"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "makeCounter"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(100)));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                // yourCounter = makeCounter(100);
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "yourCounter"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "makeCounter"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(200)));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                // println(myCounter(2));
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "myCounter"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(2)));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                // println(yourCounter(3));
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "yourCounter"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(3)));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                //  End of closure  ---
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case IF_CLOSURE:
                mockTocken = new ArrayList<>();
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.IF, "if"));
                mockTocken.add(new Token(Token.TYPE.TRUE, "true"));
                mockTocken.add(new Token(Token.TYPE.THEN, "then"));
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.STRING, "hello"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.FI, "fi"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));
                //  End of closure  ---
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case IF_ELSE_CLOSURE:
                mockTocken = new ArrayList<>();
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.IF, "if"));
                mockTocken.add(new Token(Token.TYPE.FALSE, "false"));
                mockTocken.add(new Token(Token.TYPE.THEN, "then"));
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.STRING, "hello"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.ELSE, "else"));
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.STRING, "world"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.FI, "fi"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));
                //  End of closure  ---
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case MINOR:
                mockTocken = new ArrayList<>();
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));

                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(0)));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.MINOR, "<"));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(20)));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));
                //  End of closure  ---
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case TEST_WHILE:
                mockTocken = new ArrayList<>();
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));

                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(0)));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                mockTocken.add(new Token(Token.TYPE.WHILE, "while"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.MINOR, "<"));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(10)));
                mockTocken.add(new Token(Token.TYPE.DO, "do"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.EQUAL, "="));
                mockTocken.add(new Token(Token.TYPE.NUMBER, new BigDecimal(1)));
                mockTocken.add(new Token(Token.TYPE.PLUS, "+"));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.VARIABLE, "a"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.OD, "od"));
                mockTocken.add(new Token(Token.TYPE.SEMICOLON, ";"));

                //  End of closure  ---
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
            case TEST_EMPTY_LAMBDA:
                // {-> println({->} + "a")}
                mockTocken = new ArrayList<>();
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.PRINT, "print"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_OPEN, "("));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{"));
                mockTocken.add(new Token(Token.TYPE.LAMBDA, "->"));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.PLUS, "+"));
                mockTocken.add(new Token(Token.TYPE.STRING, "a"));
                mockTocken.add(new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")"));
                mockTocken.add(new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}"));
                mockTocken.add(new Token(Token.TYPE.EOS, "eos"));
                break;
        }

    }

    public Answer<Void> provide_undoNext_Answer() {
        return invocationOnMock -> {
            tockenNumber--;
            return null;
        };
    }
}
