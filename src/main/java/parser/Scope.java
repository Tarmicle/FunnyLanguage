package parser;

import java.util.ArrayList;

public class Scope {
    Scope externalScope;
    ArrayList<String> variables;

    public Scope(ArrayList<String> variables, Scope externalScope) {
        this.externalScope = externalScope;
        this.variables = variables;
    }

    public void checkIfScope() {

    }

    public Boolean isInScope(String value) {
        if (variables.stream().anyMatch(e -> e.equals(value))) return true;
        else if (externalScope != null) return externalScope.isInScope(value);
        else return false;
    }
}
