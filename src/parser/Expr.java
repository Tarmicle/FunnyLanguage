package parser;

public abstract class Expr {
    public abstract Val eval(Env env) throws InterpreterException;
    public ClosureVal checkClosure() throws InterpreterException{
        throw new InterpreterException();
    }
}
