package com.company.lambda;

import com.company.antlr.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asurov on 18.04.2017.
 */
public class EvalVisitor extends HelloWorldBaseVisitor<Double> {

    static Map<String, Double> memory = null;
    EvalVisitor(){
        if(memory == null){
            memory = new HashMap<>();
        }
    }

    /** expr NEWLINE */
    @Override
    public Double visitPrintExpr(HelloWorldParser.PrintExprContext ctx) {
        Double value = visit(ctx.expr());
        System.out.println(value);
        return 0.0;
    }
    /** ID */
    @Override
    public Double visitId(HelloWorldParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if ( memory.containsKey(id) ) return memory.get(id);
        return null;
    }


    /** ID '=' expr NEWLINE */
    @Override
    public Double visitAssign(HelloWorldParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        double value = visit(ctx.expr());
        memory.put(id, value); //
        return null;
    }
    @Override
    public Double visitPovVal(HelloWorldParser.PovValContext ctx){
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        return Math.pow(left,right);
    }

    /** INT */
    @Override
    public Double visitDouble(HelloWorldParser.DoubleContext ctx) {
        String doubleS = ctx.DOUBLE().getText();
        if(doubleS.contains(","))doubleS = doubleS.replace(',','.');
        double d =  Double.parseDouble(doubleS);
        return d;
    }
    /** expr op=('*'|'/') expr */
    @Override
    public Double visitMulDiv(HelloWorldParser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        if ( ctx.op.getType() == HelloWorldParser.MUL ) return left * right;
        return left / right;
    }
    /** expr op=('+'|'-') expr */
    @Override
    public Double visitAddSub(HelloWorldParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        if ( ctx.op.getType() == HelloWorldParser.ADD ) return left + right;
        return left - right;
    }
    /** '(' expr ')' */
    @Override
    public Double visitParens(HelloWorldParser.ParensContext ctx) {
        return visit(ctx.expr());
    }




}
