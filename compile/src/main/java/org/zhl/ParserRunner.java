package org.zhl;

import org.zhl.as.ASTree;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;
import org.zhl.parser.BasicParser;
import org.zhl.token.Token;

/**
 * @author zhanghanlin
 * @date 2021/11/6
 **/
public class ParserRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        BasicParser basicParser = new BasicParser();
        while (l.peek(0) != Token.EOF){
            ASTree ast = basicParser.parse(l);
            System.out.println("=>" + ast.toString());
        }
    }
}
