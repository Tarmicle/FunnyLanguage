package tokenizer;

public class Token {

    public final TYPE type;
    public final String value;

    Token(TYPE type, String value) {
        this.type = type;
        this.value = value;
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
        MINUS, MINUS_EQUAL,
        ABSTERISC, ABSTERISC_EQUAL,
        DIVIDE, DIVIDE_EQUAL,
        MINOR, MINOR_EQUAL,
        MAJOR, MAJOR_EQUAL

    }
}
