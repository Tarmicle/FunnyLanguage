package parser;

public class StringVal extends Val {
    String val;
    public StringVal(String val) {
        this.val = val;
    }

    @Override
    public Val eval(Env env) {
        return this;
    }

    @Override
    public String toString() {
        return val;
    }
}
