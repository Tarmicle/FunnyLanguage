package tokenizer;

public class InvalidSymbolException extends Throwable {
    String expected;
    Object actual;

    public InvalidSymbolException(String expected, Object actual) {
        super("Expected " + expected.toString() + " actual: " + actual.toString());
        this.expected = expected;
        this.actual = actual;
    }

}
