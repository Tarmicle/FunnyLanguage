package tokenizer;

import java.math.BigDecimal;

public class Token {

    public final TYPE type;
     public Object value;
     public BigDecimal bigDecimal;
    Token(TYPE type, String value) {
        this.type = type;
        this.value = value;
    }

    Token(TYPE type, BigDecimal value) {
        this.type = type;
        this.bigDecimal = value;
    }

    public enum TYPE {
        IFNOT, IF, THEN, ELSE, FI,
        WHILENOT, WHILE, DO, OD,
        PRINTLN, PRINT,
        END_OF_IDENTIFIERS,

        EOS, NUMBER,VARIABLE,
        CURLY_BRACKET_OPEN,
        UNKNOWN, STRING,
        COMMA, SEMICOLON,
        CURLY_BRACKET_CLOSE,
        ROUND_BRACKET_CLOSE,
        ROUND_BRACKET_OPEN,
        EQUAL, EQUAL_EQUAL,
        LAMBDA,
        PLUS, PLUS_EQUAL,
        MINUS, MINUS_EQUAL,BANG,
        ABSTERISC, ABSTERISC_EQUAL,
        DIVIDE, DIVIDE_EQUAL,
        MINOR, MINOR_EQUAL,
        MAJOR, MAJOR_EQUAL

    }
}
