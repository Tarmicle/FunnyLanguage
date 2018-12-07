package parser;

import tokenizer.*;
import tokenizer.exceptions.CommentNotClosedException;
import tokenizer.exceptions.UnaspectedTokenException;
import tokenizer.exceptions.StringNotClosedException;
import tokenizer.exceptions.TokenizerException;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;

public class Parser {
    Tokenizer tokenizer;
    Token token;

    public Parser(Tokenizer tokenizer) throws IllegalArgumentException {
        if (tokenizer == null) throw new IllegalArgumentException("Tokenizer can't be null");
        this.tokenizer = tokenizer;
    }

    public Expr compile() throws IOException, StringNotClosedException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        Expr programm = program();
        if (token.type != Token.TYPE.EOS) {
            System.out.println("Compiler error, ");
            System.out.println("TOKEN: " + token.type);
        }
        return programm;
    }

    public Expr program() throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        token = tokenizer.assertAndNext("{");
        FunExpr firstExpression = function(null);
        return new InvokeExpr(firstExpression, null);
    }

    // function ::= "{" optParams optLocals optSequence "}" .
    public FunExpr function(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {

        ArrayList<String> params = optParams();
        ArrayList<String> locals = optLocals();
        ArrayList<String> temps = new ArrayList<>(params);

        temps.addAll(locals);

        return new FunExpr(params, locals, optSequence(new Scope(temps, scope)));
        //return new FunExpr(params, locals, code);
    }


    // optParams ::= ( "(" optIds ")" )? .
    private ArrayList<String> optParams() throws StringNotClosedException, IOException, CommentNotClosedException, TokenizerException, UnaspectedTokenException {
        ArrayList<String> ids = new ArrayList<>();
        if (token.value.equals("(")) {
            token = tokenizer.nextToken();
            ids.addAll(optIds());
            if (token.type != Token.TYPE.ROUND_BRACKET_CLOSE)
                throw new UnaspectedTokenException(")", token.value);
            token = tokenizer.nextToken();
        }
        return ids;
    }

    // optLocals ::= optIds .
    private ArrayList<String> optLocals() throws StringNotClosedException, IOException, CommentNotClosedException {
        return optIds();
    }

    //optSequence ::= ( "->" sequence )? .
    private Expr optSequence(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        if (token.type == Token.TYPE.LAMBDA) {
            token = tokenizer.nextToken();
            return sequence(scope);
        } else throw new UnaspectedTokenException("->", token.value);
    }

    private Expr sequence(Scope scope) throws IOException, CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        SeqExpr seqExpr = new SeqExpr();
        seqExpr.add(optAssignement(scope));

        while (token.type == Token.TYPE.SEMICOLON) {
            token = tokenizer.nextToken();
            if (token.type == Token.TYPE.CURLY_BRACKET_CLOSE) {
                token = tokenizer.nextToken();
                return seqExpr;
            }
            seqExpr.add(optAssignement(scope));
        }
        if (token.type == Token.TYPE.CURLY_BRACKET_CLOSE) {
            token = tokenizer.nextToken();
            return seqExpr;
        }
        return seqExpr;
    }

    // sequence ::= optAssignment ( ";" optAssignment )* .
    private Expr optAssignement(Scope scope) throws IOException, StringNotClosedException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        return assignement(scope);
    }

    // optIds::= id* .
    private ArrayList<String> optIds() throws StringNotClosedException, IOException, CommentNotClosedException {
        ArrayList<String> ids = new ArrayList<>();
        while (token.type == Token.TYPE.VARIABLE) {
            ids.add((String) token.value);
            token = tokenizer.nextToken();
        }
        return ids;
    }

    //  assignment ::= Id ( "=" | "+=" | "-=" | "*=" | "/=" | "%=" ) assignment
    //	  | logicalOr .
    private Expr assignement(Scope scope) throws IOException, CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        //scope.checkIfScope();
        if (token.type == Token.TYPE.VARIABLE) {
            Token tokenbk = token;
            token = tokenizer.nextToken();
            switch (token.type) {
                case EQUAL:
                    token = tokenizer.nextToken();
                    if (!scope.isInScope(tokenbk.getStringVal()))
                        throw new UnexpectedSymbolException("Unexpected symbol " + token.getStringVal());
                    return new SetVarExpr(tokenbk.getStringVal(), assignement(scope));
                default:
                    tokenizer.undoNext();
                    token = tokenbk;
                    break;
            }
        }
        return logicalOr(scope);
    }

    // logicalOr ::= logicalAnd ( "||" logicalOr )? .
    private Expr logicalOr(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {

        Expr expr = logicalAnd(scope);
        return expr;
    }

    // logicalAnd ::= equality ( "&&" logicalAnd )? .
    private Expr logicalAnd(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {

        return equality(scope);
    }

    // equality ::= comparison ( ( "==" | "!=" ) comparison )? .
    private Expr equality(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {

        return comparison(scope);
    }

    // comparison ::= add ( ( "<" | "<=" | ">" | ">=" ) add )? .
    private Expr comparison(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {

        return add(scope);
    }

    // add ::= mult ( ( "+" | "-" ) mult )* .
    private Expr add(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        Expr expr = mult(scope);
        Token.TYPE type = token.type;
        switch (type) {
            case PLUS:
                token = tokenizer.nextToken();
                expr = new BinaryExpr(expr, add(scope), type);
                break;
            case MINUS:
                token = tokenizer.nextToken();
                expr = new BinaryExpr(expr, add(scope), type);
                break;
        }
        return expr;
    }

    // mult ::= unary ( ( "*" | "/" | "%" ) unary )* .
    private Expr mult(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        Expr expr = unary(scope);
        Token.TYPE type = token.type;
        switch (type) {
            case ABSTERISC:
                token = tokenizer.nextToken();
                expr = new BinaryExpr(expr, mult(scope), type);
                break;
        }
        return expr;
    }

    // unary ::= ( "+" | "-" | "!" ) unary
    //	| postfix .
    private Expr unary(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        switch (token.type) {
            case PLUS:
                return unary(scope);
            case MINOR:
                return unary(scope);
            case BANG:
                return unary(scope);
            default:
                return postfix(scope);
        }

    }

    // postfix ::= primary args*
    private Expr postfix(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        Expr primary = primary(scope);
        if (token.getType() == Token.TYPE.ROUND_BRACKET_OPEN) {
            ExprList args = args(scope);
            return new InvokeExpr(primary, args);
        }
        return primary;
    }

    private Expr primary(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        switch (token.getType()) {
            case NUMBER:
                return num(scope);
            case STRING:
                return string(scope);
            case VARIABLE:
                return getId(scope);
            case CURLY_BRACKET_OPEN:
                token = tokenizer.nextToken();
                return function(scope);
            case IF:
                return condition(scope);
            case PRINT:
                return print(scope);
            case PRINTLN:
                return print(scope);
            default:
                throw new UnaspectedTokenException(token.getType().toString(), token.type);
        }

    }

    private Expr condition(Scope scope) throws UnexpectedSymbolException, TokenizerException, CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException {
        if (token.getType() == Token.TYPE.IF || token.getType() == Token.TYPE.IFNOT) {
            Expr sequence = sequence(scope);
            if (token.getType() != Token.TYPE.THEN)
                throw new UnexpectedSymbolException(token.type.toString());
            return new IfExpr();
        } else throw new UnexpectedSymbolException("Ciao" + token.type.toString());
    }

    private Expr getId(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnexpectedSymbolException {
        //TODO: Controllare se esiste nello scope
        if (!scope.isInScope(token.getStringVal())) {
            System.out.println("Unexpected symbol" + token.getStringVal());
            throw new UnexpectedSymbolException("Unexpected symbol " + token.getStringVal());
        }
        Expr getVarExpr = new GetVarExpr(token.value);
        token = tokenizer.nextToken();
        return getVarExpr;
    }

    private Expr num(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException {
        NumVal numVal = new NumVal(token.bigDecimal);
        token = tokenizer.nextToken();
        return numVal;
    }

    private StringVal string(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException {
        String rtn = token.value.toString();
        token = tokenizer.nextToken();
        return new StringVal(rtn);
    }

    private PrintExpr print(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, UnaspectedTokenException, TokenizerException, UnexpectedSymbolException {
        if (token.type == Token.TYPE.PRINT || token.type == Token.TYPE.PRINTLN) {
            // TODO: TENERE PRINT O PRINTLN
            Token prevToken = token;
            token = tokenizer.nextToken();
            return new PrintExpr(prevToken.getType(), args(scope));
        } else throw new UnaspectedTokenException("print or println", token.value);
    }

    private ExprList args(Scope scope) throws UnaspectedTokenException, StringNotClosedException, IOException, CommentNotClosedException, TokenizerException, UnexpectedSymbolException {
        if (token.type == Token.TYPE.ROUND_BRACKET_OPEN) {
            token = tokenizer.nextToken();

            ExprList expressions = new ExprList();
            expressions.add(sequence(scope));
            // SE non vi è una virgola o un'altra espressione...
            while (token.type == Token.TYPE.COMMA) {
                token = tokenizer.nextToken();
                expressions.add(sequence(scope));
            }
            // Consumo la parentesi
            if (token.type == Token.TYPE.ROUND_BRACKET_CLOSE) {
                token = tokenizer.nextToken();
            } else throw new UnaspectedTokenException(")", token.value);

            return expressions;
        } else throw new UnaspectedTokenException("(", token.value);
    }


/*
    private GetVarExpr getId(Scope scope) throws IOException{
        String id = token().name();
        scope.checkInScope(id);
        next();
        return new GetVarExpr(id);
    }

    // SCOPE: Usato nel set, assignement


    private Expr equality(Scope scope) throws IOException{
        Expr expr = comparison(scope);
        if(!isEqualityOp()){
            return expr;
        }
        Type oper = type();
        next();
        return new BinaryExpr(oprt, expr, comparison(scope));
    }*/
}
