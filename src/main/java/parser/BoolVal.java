package parser;

import tokenizer.Token;

import java.math.BigDecimal;

public class BoolVal extends Val {
    private boolean value;

    BoolVal(Token.TYPE value) {
        switch (value) {
            case TRUE:
                this.value = true;
                break;
            case FALSE:
                this.value = false;
                break;
        }
    }

    BoolVal checkBool() throws InterpreterException {
        return this;
    }

    public boolean getBool() {
        return value;
    }

    @Override
    public String toString() {
        if (value) return "true";
        else return "false";
    }
}
