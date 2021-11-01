package org.zhl;

import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;
import org.zhl.token.Token;

/**
 * 走私者
 *
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public class Runer {
    public static void main(String[] args) throws ParseException {
        Lexer lexer1= new Lexer(new CodeDialog());
        for (Token<?> t ; (t = lexer1.read()) != Token.EOF;){
            System.out.println("=>  "+t.getContent());
        }
    }
}
