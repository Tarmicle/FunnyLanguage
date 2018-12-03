package tokenizer.exceptions;

public class UnaspectedTokenException extends Throwable {
    String expected;
    Object actual;

    public UnaspectedTokenException() {
        super();
    }

    public UnaspectedTokenException(String expected, Object actual) {
        super("Expected " + expected.toString() + " actual: " + actual.toString());
        this.expected = expected;
        this.actual = actual;
    }

}
