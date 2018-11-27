package parser;

public abstract class Val extends Expr {
    /*public  checkNum() {
    }*/

    NumVal checkNum() throws InterpreterException {
        throw new InterpreterException();
    }

}
