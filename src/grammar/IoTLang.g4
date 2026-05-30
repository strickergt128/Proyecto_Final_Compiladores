grammar IoTLang;

// =============================================
// PARSER RULES (Reglas sintácticas)
// =============================================

program
    : statement* EOF
    ;

statement
    : sensorDecl
    | actuadorDecl
    | varDecl
    | assignStmt
    | encenderStmt
    | apagarStmt
    | ajustarStmt
    | enviarSenalStmt
    | leerTempStmt
    | leerHumStmt
    | leerEstadoStmt
    | esperarStmt
    | programarStmt
    | cancelarStmt
    | imprimirStmt
    | registrarStmt
    | alertaStmt
    | ifStmt
    | whileStmt
    ;

sensorDecl      : SENSOR ID SEMICOLON ;
actuadorDecl    : ACTUADOR ID SEMICOLON ;

varDecl         : VAR ID ASSIGN expr SEMICOLON ;
assignStmt      : ID ASSIGN expr SEMICOLON ;

encenderStmt    : ENCENDER LPAREN ID RPAREN SEMICOLON ;
apagarStmt      : APAGAR LPAREN ID RPAREN SEMICOLON ;
ajustarStmt     : AJUSTAR LPAREN ID COMMA expr RPAREN SEMICOLON ;
enviarSenalStmt : ENVIARSENAL LPAREN ID COMMA STRING RPAREN SEMICOLON ;

leerTempStmt    : ID ASSIGN LEERTEMPERATURA LPAREN ID RPAREN SEMICOLON ;
leerHumStmt     : ID ASSIGN LEERHUMEDAD LPAREN ID RPAREN SEMICOLON ;
leerEstadoStmt  : ID ASSIGN LEERESTADO LPAREN ID RPAREN SEMICOLON ;

esperarStmt     : ESPERAR LPAREN expr RPAREN SEMICOLON ;
programarStmt   : PROGRAMAR LPAREN CADA COMMA expr COMMA statement RPAREN SEMICOLON ;
cancelarStmt    : CANCELAR LPAREN ID RPAREN SEMICOLON ;

imprimirStmt    : IMPRIMIR LPAREN expr RPAREN SEMICOLON ;
registrarStmt   : REGISTRAR LPAREN ID COMMA ID RPAREN SEMICOLON ;
alertaStmt      : ALERTA LPAREN STRING COMMA expr RPAREN SEMICOLON ;

ifStmt
    : SI LPAREN condition RPAREN LBRACE statement* RBRACE
      (SINO LBRACE statement* RBRACE)?
    ;

whileStmt
    : MIENTRAS LPAREN condition RPAREN LBRACE statement* RBRACE
    ;

condition
    : expr op=(GT | LT | GTE | LTE | EQ | NEQ) expr
    | boolLiteral
    ;

// Regla de parser para literales booleanos (evita conflicto léxico)
boolLiteral
    : VERDADERO
    | FALSO
    ;

expr
    : expr op=(MULT | DIV) expr           # binaryExpr
    | expr op=(PLUS | MINUS) expr          # binaryExpr
    | NUMERO                               # numLiteral
    | DECIMAL                              # decimalLiteral
    | STRING                               # stringLiteral
    | ID                                   # idExpr
    ;

// =============================================
// LEXER RULES (Reglas léxicas)
// =============================================

// --- Palabras reservadas (DEBEN ir antes que ID) ---
SENSOR          : 'sensor' ;
ACTUADOR        : 'actuador' ;
VAR             : 'var' ;
SI              : 'si' ;
SINO            : 'sino' ;
MIENTRAS        : 'mientras' ;
CADA            : 'cada' ;
ENCENDER        : 'encender' ;
APAGAR          : 'apagar' ;
AJUSTAR         : 'ajustar' ;
ENVIARSENAL     : 'enviarSenal' ;
LEERTEMPERATURA : 'leerTemperatura' ;
LEERHUMEDAD     : 'leerHumedad' ;
LEERESTADO      : 'leerEstado' ;
ESPERAR         : 'esperar' ;
PROGRAMAR       : 'programar' ;
CANCELAR        : 'cancelar' ;
IMPRIMIR        : 'imprimir' ;
REGISTRAR       : 'registrar' ;
ALERTA          : 'alerta' ;
VERDADERO       : 'verdadero' ;
FALSO           : 'falso' ;

// --- Tipos de datos literales ---
DECIMAL         : [0-9]+ '.' [0-9]+ ;
NUMERO          : [0-9]+ ;
STRING          : '"' (~["\r\n])* '"' ;

// --- Identificadores (DEBEN ir después de palabras reservadas) ---
ID              : [a-zA-Z_][a-zA-Z_0-9]* ;

// --- Operadores aritméticos ---
PLUS    : '+' ;
MINUS   : '-' ;
MULT    : '*' ;
DIV     : '/' ;

// --- Operadores relacionales (>=, <= ANTES de >, < para evitar ambigüedad) ---
GTE     : '>=' ;
LTE     : '<=' ;
EQ      : '==' ;
NEQ     : '!=' ;
GT      : '>' ;
LT      : '<' ;

// --- Operador de asignación ---
ASSIGN  : '=' ;

// --- Delimitadores ---
SEMICOLON : ';' ;
COMMA     : ',' ;
LPAREN    : '(' ;
RPAREN    : ')' ;
LBRACE    : '{' ;
RBRACE    : '}' ;

// --- Comentarios (se ignoran) ---
COMMENT       : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;

// --- Espacios en blanco (se ignoran) ---
WS : [ \t\r\n]+ -> skip ;

// --- Token de error léxico (cualquier carácter no reconocido) ---
ERROR_CHAR : . ;