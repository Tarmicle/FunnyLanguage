package parser;

public abstract class Val extends Expr {
    /*public  checkNum() {
    }*/

    @Override
    public Val eval(Env env) {
        return this;
    }

    NumVal checkNum() throws InterpreterException {
        throw new InterpreterException();
    }
}
