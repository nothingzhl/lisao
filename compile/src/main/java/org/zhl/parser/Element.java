package org.zhl.parser;

import org.zhl.as.ASTree;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/2
 **/
public abstract class Element {

    /**
     * 解析
     *
     * @param lexer 词法分析程序
     * @param res   res
     * @throws ParseException 解析异常
     */
    protected abstract void parse(Lexer lexer, List<ASTree> res)
        throws ParseException;

    /**
     * 匹配
     *
     * @param lexer 词法分析程序
     * @return boolean
     * @throws ParseException 解析异常
     */
    protected abstract boolean match(Lexer lexer) throws ParseException;
}
