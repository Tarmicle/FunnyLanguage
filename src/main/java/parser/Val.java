package parser;

public abstract class Val extends Expr {
    /*public  checkNum() {
    }*/

    @Override
    public Val eval(Env env) {
        return this;
    }

    boolean isNan(){
        return true;
    }

    NumVal checkNum() throws InterpreterException {
        throw new InterpreterException("Not a number");
    }

    BoolVal checkBool() throws InterpreterException {
        throw new InterpreterException();
    }

    Val sum(Val augend) throws InterpreterException {
        throw new InterpreterException("Sum operation not supported");
    }
}
