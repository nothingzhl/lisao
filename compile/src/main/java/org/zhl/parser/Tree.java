package org.zhl.parser;

import org.zhl.as.ASTree;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class Tree extends Element{

    private Parser parser;

    protected Tree(Parser parser) {
        this.parser = parser;
    }

    @Override
    protected void parse(Lexer lexer, List<ASTree> res) throws ParseException {

    }

    @Override
    protected boolean match(Lexer lexer) throws ParseException {
        return false;
    }
}
