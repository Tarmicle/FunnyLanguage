package parser;

import parser.ClosureVal;
import parser.Env;
import parser.InterpreterException;
import parser.Val;

public abstract class Expr {
    public abstract Val eval(Env env) throws InterpreterException;
    public ClosureVal checkClosure() throws InterpreterException{
        throw new InterpreterException();
    }
}
