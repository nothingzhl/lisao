package org.zhl.parser;

import org.zhl.as.ASTree;
import org.zhl.as.BinaryExpr;
import org.zhl.as.Name;
import org.zhl.as.NumberLiteral;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;
import org.zhl.parser.expr.*;
import org.zhl.token.Token;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhanghanlin
 * @date 2021/11/6
 **/
public class BasicParser {

    Set<String> reserved = new HashSet<>();

    Operators operators = new Operators();

    Parser expr0 = Parser.rule();

    Parser primary = Parser.rule(PrimaryExpr.class)
        .or(Parser.rule().sep("(").ast(expr0).sep(")"), Parser.rule().number(NumberLiteral.class),
            Parser.rule().identifier(Name.class, reserved), Parser.rule().string(StringLiteral.class));

    Parser factor = Parser.rule().or(Parser.rule(NegativeExpr.class).sep("-").ast(primary), primary);

    Parser expr = expr0.expression(BinaryExpr.class, factor, operators);

    Parser statement0 = Parser.rule();

    Parser block = Parser.rule(BlockStmnt.class).sep("{").option(statement0)
        .repeat(Parser.rule().sep(";", Token.EOL).option(statement0).sep("}"));

    Parser simple = Parser.rule(PrimaryExpr.class).ast(expr);

    Parser statement = statement0
        .or(Parser.rule(IfStmnt.class).sep("if").ast(expr).ast(block).option(Parser.rule().sep("else").ast(block)),
            Parser.rule(WhileStmnt.class).sep("while").ast(expr).ast(block), simple);
    Parser program = Parser.rule().or(statement,Parser.rule(NullStmnt.class).sep(";",Token.EOL));

    public BasicParser() {

        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=",1,Operators.RIGHT);
        operators.add("==",2,Operators.LEFT);
        operators.add(">",2,Operators.LEFT);
        operators.add("<",2,Operators.LEFT);
        operators.add("+",3,Operators.LEFT);
        operators.add("-",3,Operators.LEFT);
        operators.add("*",4,Operators.LEFT);
        operators.add("/",4,Operators.LEFT);
        operators.add("&",4,Operators.LEFT);
    }

    public ASTree parse(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }
}
