package parser;

public class InterpreterException extends Throwable {
    InterpreterException() {
    }
    InterpreterException(String errorMessage){
        super(errorMessage);
    }
}
