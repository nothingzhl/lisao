package org.zhl.parser;

import org.zhl.as.ASTLeaf;
import org.zhl.as.ASTree;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhanghanlin
 * @date 2021/11/2
 **/
public class Parser {

    protected List<Element> elements;
    protected Factory factory;

    protected Parser(Parser p) {
        elements = p.elements;
        factory = p.factory;
    }

    public Parser(Class<? extends ASTree> clazz) {
        reset(clazz);
    }

    public Parser reset(Class<? extends ASTree> clazz) {
        elements = new ArrayList<>();
        factory = Factory.getForASTList(clazz);
        return this;
    }

    public Parser reset() {
        elements = new ArrayList<>();
        return this;
    }

    public ASTree parse(Lexer lexer) throws ParseException {
        ArrayList<ASTree> results = new ArrayList<>();
        // 解析对应的element 成ast
        for (Element e : elements) {
            e.parse(lexer, results);
        }
        return factory.make(results);
    }

    protected boolean match(Lexer lexer) throws ParseException {
        if (elements.size() == 0) {
            return true;
        } else {
            Element e = elements.get(0);
            return e.match(lexer);
        }
    }

    public static Parser rule() {
        return rule(null);
    }

    public static Parser rule(Class<? extends ASTree> clazz) {
        return new Parser(clazz);
    }

    public Parser number() {
        return number(null);
    }

    public Parser number(Class<? extends ASTLeaf> clazz) {
        elements.add(new NumToken(clazz));
        return this;
    }

    public Parser identifier(HashSet<String> reserved) {
        return identifier(null, reserved);
    }

    public Parser identifier(Class<? extends ASTLeaf> clazz, Set<String> reserved) {
        elements.add(new IdToken(clazz, reserved));
        return this;
    }

    public Parser string() {
        return string(null);
    }

    public Parser string(Class<? extends ASTLeaf> clazz) {
        elements.add(new StrToken(clazz));
        return this;
    }

    public Parser token(String... pat) {
        elements.add(new Leaf(pat));
        return this;
    }

    public Parser sep(String... pat) {
        elements.add(new Skip(pat));
        return this;
    }

    public Parser ast(Parser p) {
        elements.add(new Tree(p));
        return this;
    }

    public Parser or(Parser... p) {
        elements.add(new OrTree(p));
        return this;
    }

    public Parser maybe(Parser p) {
        Parser p2 = new Parser(p);
        p2.reset();
        elements.add(new OrTree(new Parser[] {p, p2}));
        return this;
    }

    public Parser option(Parser p) {
        elements.add(new Repeat(p, true));
        return this;
    }

    public Parser repeat(Parser p) {
        elements.add(new Repeat(p, false));
        return this;
    }

    public Parser expression(Parser subexp, Operators operators) {
        elements.add(new Expr(null, subexp, operators));
        return this;
    }

    public Parser expression(Class<? extends ASTree> clazz, Parser subexp, Operators operators) {
        elements.add(new Expr(clazz, subexp, operators));
        return this;
    }

    public Parser insertChoice(Parser p) {
        Element e = elements.get(0);
        if (e instanceof OrTree)
            ((OrTree)e).insert(p);
        else {
            Parser otherwise = new Parser(this);
            reset(null);
            or(p, otherwise);
        }
        return this;
    }

}
