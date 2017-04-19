grammar HelloWorld;

prog:   stat+;

stat:   expr NEWLINE         # printExpr
    |ID op='=' expr NEWLINE         # assign
    | NEWLINE                # blank
    ;

expr:
     expr op='^' expr            # povVal
    |expr op=('*'|'/') expr      # mulDiv
    |expr op=('+'|'-') expr      # addSub
    |DOUBLE                      # double
    |ID                          # id
    |'(' expr ')'                # parens
    ;

MUL :   '*' ;
DIV :   '/' ;
ADD :   '+' ;
SUB :   '-' ;
ID  :   [a-zA-Z]+ ;
DOUBLE :   [0-9]+([.,]?[0-9]+)?([eE][+-]?[0-9]+)?;
POW : '^';
NEWLINE:'\r'? '\n' ;
WS  :   [ \t]+ -> skip ;