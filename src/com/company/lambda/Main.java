package com.company.lambda;
import com.company.antlr.*;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        Scanner scanner = null;
        CharStream input = null;
        while (true) {
            if (inputFile != null) {
                is = new FileInputStream(inputFile);
                input = CharStreams.fromStream(is);
            } else {
                scanner = new Scanner(is);
                input = CharStreams.fromString(scanner.nextLine() + '\n');

            }

            if(input.toString().equals("stop\n"))break;
            HelloWorldLexer lexer = new HelloWorldLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            HelloWorldParser parser = new HelloWorldParser(tokens);
            ParseTree tree = parser.prog(); // parse

            EvalVisitor eval = new EvalVisitor();
            eval.visit(tree);
        }
    }
}
