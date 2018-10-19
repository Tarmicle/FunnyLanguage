package tokenizer;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tokenizer {
    private BufferedReader bufferedReader;
    private boolean eos = false;
    private static Integer MAX_IDENTIFIER_SIZE = 10;
    private static int EOS = -1;

    public Tokenizer(String fileName) {
        try {
            bufferedReader =
                    new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
    }


    // Skip whitespaces and place the marker on the first 'non spacing' character
    private static int skipWhiteSpaces(BufferedReader bufferedReader) throws IOException {
        bufferedReader.mark(1);
        int i = bufferedReader.read();
        while (i == ' ' || i == '\n'|| i=='\t') {
            bufferedReader.mark(1);
            i = bufferedReader.read();
        }
        bufferedReader.reset();
        return i;
    }

    private Token handleEOS() throws IOException {
        bufferedReader.close();
        eos = true;
        return new Token(Token.TYPE.EOS, "EOS");
    }

    public Token nextToken() throws IOException {
        if (eos) return new Token(Token.TYPE.EOS, "EOS");
        if (skipWhiteSpaces(bufferedReader) == EOS) {
            return handleEOS();
        }

        if (isBeginningAComment(bufferedReader)) {
            if (skipCommentArea(bufferedReader) == EOS)
                return handleEOS();
        }

        int nextChar = bufferedReader.read();


        if (nextChar == '{') return new Token(Token.TYPE.CURLY_BRACKET_OPEN, "{");
        if (nextChar == '}') return new Token(Token.TYPE.CURLY_BRACKET_CLOSE, "}");
        if (nextChar == '(') return new Token(Token.TYPE.ROUND_BRACKET_OPEN, "(");
        if (nextChar == ')') return new Token(Token.TYPE.ROUND_BRACKET_CLOSE, ")");
        if (nextChar == ',') return new Token(Token.TYPE.COMMA, ",");
        if (nextChar == ';') return new Token(Token.TYPE.SEMICOLON, ";");
        if (nextChar == '=') return handleEqual();
        if (nextChar == '*') return handleAbsterisc();
        if (nextChar == '/') return handleDivide();
        if (nextChar == '+') return handlePlus();
        if (nextChar == '-') return handleMinus();
        if (nextChar == '<') return handleMinor();
        if (nextChar == '>') return handleMajor();
        if (isANumber(nextChar)) return handleNumber(nextChar);
        if (identifierLen(nextChar) != 0) return handleIdentifier(identifierLen(nextChar), nextChar);
        if (nextChar == '"') return handleString();
        if (Character.isJavaIdentifierStart(nextChar)) return handleVar(nextChar);
        return new Token(Token.TYPE.UNKNOWN, Character.toString(((char) nextChar)));

    }

    private static boolean isBeginningAComment(BufferedReader bufferedReader) throws IOException {
        bufferedReader.mark(2);
        boolean isBeginningAComment = bufferedReader.read() == '/' && bufferedReader.read() == '*';
        bufferedReader.reset();
        return isBeginningAComment;
    }

    private static int handleCloseComment(BufferedReader reader, int commentStatus) throws IOException {
        reader.skip(2);
        return --commentStatus;
    }

    private static int handleOpenComment(BufferedReader reader, int commentState) throws IOException {
        reader.skip(2);
        return ++commentState;
    }

    private static boolean isColosingAComment(BufferedReader bufferedReader) throws IOException {
        bufferedReader.mark(2);
        boolean isClosingAComment = bufferedReader.read() == '*' && bufferedReader.read() == '/';
        bufferedReader.reset();
        return isClosingAComment;
    }

    private static void skipToNextCommentCandidate(BufferedReader bufferedReader) throws IOException {
        bufferedReader.mark(1);

        int char1 = bufferedReader.read();
        while (char1 != '*' && char1 != '/') {
            bufferedReader.mark(1);
            char1 = bufferedReader.read();
        }

        bufferedReader.reset();
    }

    private static int skipCommentArea(BufferedReader bufferedReader) throws IOException {
        int commentState = 0;
        do {
            skipToNextCommentCandidate(bufferedReader);
            if (isBeginningAComment(bufferedReader))
                commentState = handleOpenComment(bufferedReader, commentState);
            else if (isColosingAComment(bufferedReader))
                commentState = handleCloseComment(bufferedReader, commentState);
            else bufferedReader.read();
        } while (commentState != 0);
        return skipWhiteSpaces(bufferedReader);
    }


    Token handleVar(int next) throws IOException {
        StringBuilder varname = new StringBuilder();
        varname.append((char) next);
        bufferedReader.mark(1);
        while (Character.isJavaIdentifierPart(next = bufferedReader.read())) {
            varname.append((char) next);
            bufferedReader.mark(1);
        }
        bufferedReader.reset();
        return new Token(Token.TYPE.VARIABLE, varname.toString());
    }

    private Token handleString() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int next;
        while ((next = bufferedReader.read()) != '"') {
            stringBuilder.append((char) next);
        }
        return new Token(Token.TYPE.STRING, stringBuilder.toString());
    }

    private boolean isBeginningAComment() throws IOException {
        bufferedReader.mark(1);
        if (bufferedReader.read() == '*')
            return true;
        bufferedReader.reset();
        return false;
    }

    private boolean isACommentClosure() throws IOException {
        bufferedReader.mark(1);
        if (bufferedReader.read() == '/')
            return true;
        bufferedReader.reset();
        return false;
    }

    private Token handleIdentifier(int len, int firstChar) throws IOException {
        char[] c = new char[len];
        c[0] = (char) firstChar;
        bufferedReader.read(c, 1, len - 1);
        String token = new String(c).toUpperCase();
        return new Token(Token.TYPE.valueOf(token), token);
    }

    private int identifierLen(int firstChar) throws IOException {
        bufferedReader.mark(MAX_IDENTIFIER_SIZE);
        char[] c = new char[MAX_IDENTIFIER_SIZE];
        c[0] = (char) firstChar;
        bufferedReader.read(c, 1, MAX_IDENTIFIER_SIZE - 2);

        String readed = new String(c);
        for (Token.TYPE t : Token.TYPE.values()) {
            if (t.name().equals("END_OF_IDENTIFIERS")) {
                bufferedReader.reset();
                return 0;
            }
            if (readed.startsWith(t.name().toLowerCase())) {
                bufferedReader.reset();
                return t.name().length();
            }
        }
        return 0;
    }

    private Token handleEqual() throws IOException {
        bufferedReader.mark(1);
        switch (bufferedReader.read()) {
            case '=':
                return new Token(Token.TYPE.EQUAL_EQUAL, "==");
            default:
                bufferedReader.reset();
                return new Token(Token.TYPE.EQUAL, "=");
        }
    }

    private Token handlePlus() throws IOException {
        bufferedReader.mark(1);
        switch (bufferedReader.read()) {
            case '=':
                return new Token(Token.TYPE.PLUS_EQUAL, "+=");
            default:
                bufferedReader.reset();
                return new Token(Token.TYPE.PLUS, "+");
        }
    }

    private Token handleMinus() throws IOException {
        bufferedReader.mark(1);
        switch (bufferedReader.read()) {
            case '>':
                return new Token(Token.TYPE.LAMBDA, "->");
            case '=':
                return new Token(Token.TYPE.MINUS_EQUAL, "-=");
            default:
                bufferedReader.reset();
                return new Token(Token.TYPE.MINUS, "-");
        }
    }

    private Token handleAbsterisc() throws IOException {
        bufferedReader.mark(1);
        switch (bufferedReader.read()) {
            case '=':
                return new Token(Token.TYPE.ABSTERISC_EQUAL, "*=");
            default:
                bufferedReader.reset();
                return new Token(Token.TYPE.ABSTERISC, "*");
        }
    }

    private Token handleDivide() throws IOException {
        bufferedReader.mark(1);
        switch (bufferedReader.read()) {
            case '=':
                return new Token(Token.TYPE.DIVIDE_EQUAL, "/=");
            default:
                bufferedReader.reset();
                return new Token(Token.TYPE.DIVIDE, "/");
        }
    }

    private Token handleMinor() throws IOException {
        bufferedReader.mark(1);
        switch (bufferedReader.read()) {
            case '=':
                return new Token(Token.TYPE.MINOR_EQUAL, "<=");
            default:
                bufferedReader.reset();
                return new Token(Token.TYPE.MINOR, "<");
        }
    }

    private Token handleMajor() throws IOException {
        bufferedReader.mark(1);
        switch (bufferedReader.read()) {
            case '=':
                return new Token(Token.TYPE.MAJOR_EQUAL, ">=");
            default:
                bufferedReader.reset();
                return new Token(Token.TYPE.MAJOR, ">");
        }
    }

    private boolean isANumber(int i) {
        return ('0' <= i && i <= '9');
    }


    private Token handleNumber(int digit) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        do {
            stringBuilder.append(digit);
            bufferedReader.mark(1);
            digit = bufferedReader.read();
        } while (isANumber(digit));
        bufferedReader.reset();

        return new Token(Token.TYPE.NUMBER, stringBuilder.toString());
    }


}
