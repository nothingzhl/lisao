package org.zhl.lexer;

import org.zhl.exception.ParseException;
import org.zhl.token.IdentifyToken;
import org.zhl.token.NumberToken;
import org.zhl.token.StringToken;
import org.zhl.token.Token;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public class Lexer {

    public static String regexPat =
        "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";

    private Pattern pattern = Pattern.compile(regexPat);

    private List<Token<?>> tokens = new ArrayList<>();

    private boolean hasMore;

    private LineNumberReader reader;

    public Lexer(Reader reader) {
        hasMore = true;
        this.reader = new LineNumberReader(reader);
    }

    public Token<?> read() throws ParseException {
        if (fillTokens(0)) {
            return tokens.remove(0);
        } else {
            return Token.EOF;
        }
    }

    public Token<?> peek(Integer i) throws ParseException {
        if (fillTokens(0)) {
            return tokens.get(i);
        } else {
            return Token.EOF;
        }
    }

    private boolean fillTokens(int i) throws ParseException {
        while (i >= tokens.size()) {
            if (hasMore) {
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }

    protected void readLine() throws ParseException {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }

        if (Objects.isNull(line)) {
            hasMore = false;
            return;
        }

        final int lineNumber = reader.getLineNumber();

        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);

        int pos = 0;
        int endPos = line.length();

        while (pos < endPos) {
            matcher.region(pos, endPos);
            if (matcher.lookingAt()) {
                addToken(lineNumber, matcher);
                pos = matcher.end();
            } else {
                throw new ParseException("bad token at line :" + lineNumber);
            }
        }

        tokens.add(new IdentifyToken(lineNumber, Token.EOL));

    }

    protected void addToken(int lineNumber, Matcher matcher) {

        final String m = matcher.group(1);

        if (Objects.nonNull(m) && Objects.isNull(matcher.group(2))) {
            Token<?> token;
            if (Objects.nonNull(matcher.group(3))) {
                token = new NumberToken(lineNumber, Integer.parseInt(m));
            } else if (Objects.nonNull(matcher.group(4))) {
                token = new StringToken(lineNumber, toStringLiteral(m));
            } else {
                token = new IdentifyToken(lineNumber, m);
            }
            tokens.add(token);
        }

    }

    private String toStringLiteral(String m) {
        StringBuilder sb = new StringBuilder();
        int len = m.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = m.charAt(i);
            if (c == '\\' && i + 1 < len) {
                int c2 = m.charAt(i + 1);
                if (c2 == '"' || c2 == '\\') {
                    c = sb.charAt(++i);
                } else if (c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
