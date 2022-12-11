# Lox programming language

## Grammer
```
program        -> declaration* EOF ;
declaration    -> funDecl
                  | varDecl
                  | statement ;
funDecl        -> "fun" function ;
function       -> IDENTIFIER "(" parameters? ")" block;
parameters     -> IDENTIFIER ( "," IDENTIFIER )* ;
varDecl        -> "var" IDENTIFIER ( "=" expression )? ;
statement      -> exprStmt
                  | forStmt
                  | ifStmt
                  | printStmt 
				  | returnStmt
				  | whileStmt
                  | block ;
returnStmt     -> "return" expression? ";" ;
forStmt        -> "for" "(" ( varDecl | exprStmt | ";")
                  expression? ";"
				  expression? ")" statement ;
whileStmt      -> "while" "(" expression ")" statement ;
ifStmt         -> "if" "(" expression ")" statement
                ( "else" statement )? ; 
block          -> "{" declaration* "}" ;
exprStmt       -> expression ";" ;
printStmt      -> "print" expression ";" ;

expression     -> assignment ;
assignment     -> IDENTIFIER "=" assignment
                  | logic_or ;
logic_or       -> logic_and ( "or" logic_and )* ;
logic_and      -> equality ( "and" equality )* ;
equality       -> comparison ( ( "!=" | "==" ) comparison )* ;
comparison     -> term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
term           -> factor ( ( "-" | "+" ) factor )* ;
factor         -> unary ( ( "/" | "*" ) unary )* ;
unary          -> ( "!" | "-" ) unary | call ;
call           -> primary ( "(" arguments? ")" )* ;
arguments      -> expression ( "," expression )* ;
primary        -> "true" | "false" | "nil"
                  | NUMBER | STRING
                  | "(" expression ")"
                  | IDENTIFIER ;
```
```
* means repeat zero or more times
? means zero or one time
| mean or
```
