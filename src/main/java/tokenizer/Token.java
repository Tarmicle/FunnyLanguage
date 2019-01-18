package tokenizer;

import java.math.BigDecimal;

public class Token {

    public final TYPE type;
    public String value;
    public BigDecimal bigDecimal;

    // Per ragioni di Test questi tocken devono essere pubblici
    public Token(TYPE type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token(TYPE type, BigDecimal value) {
        this.type = type;
        this.bigDecimal = value;
    }

    public String getStringVal() {
        return value;
    }

    public BigDecimal getBigDecimalVal() {
        return bigDecimal;
    }

    public Token.TYPE getType() {
        return type;
    }

    public enum TYPE {
        NIL,
        IFNOT, IF, THEN, ELSE, FI,
        WHILENOT, WHILE, DO, OD,
        PRINTLN, PRINT, TRUE, FALSE,
        END_OF_IDENTIFIERS,

        EOS, NUMBER, VARIABLE,
        CURLY_BRACKET_OPEN,
        UNKNOWN, STRING,
        COMMA, SEMICOLON,
        LOGICAL_AND,
        CURLY_BRACKET_CLOSE,
        ROUND_BRACKET_CLOSE,
        ROUND_BRACKET_OPEN,
        EQUAL, EQUAL_EQUAL,
        LAMBDA, PERCENT,
        PLUS, PLUS_EQUAL,
        MINUS, MINUS_EQUAL, BANG,
        ABSTERISC, ABSTERISC_EQUAL,
        DIVIDE, DIVIDE_EQUAL,
        MINOR, MINOR_EQUAL,
        MAJOR, MAJOR_EQUAL;

    }
}
