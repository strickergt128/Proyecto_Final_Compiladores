package semantic;

import grammar.IoTLangBaseVisitor;
import grammar.IoTLangParser;

/**
 * Visitante semántico que recorre el árbol de parseo y realiza:
 * - Registro de declaraciones en la tabla de símbolos
 * - Verificación de variables/dispositivos no declarados
 * - Verificación de declaraciones duplicadas
 * - Verificación de tipos compatibles en asignaciones y expresiones
 * - Validación de categorías (sensor vs actuador) en instrucciones IoT
 */
public class SemanticVisitor extends IoTLangBaseVisitor<Symbol.Tipo> {

    private SymbolTable symbolTable;
    private ErrorHandler errorHandler;

    public SemanticVisitor(SymbolTable symbolTable, ErrorHandler errorHandler) {
        this.symbolTable = symbolTable;
        this.errorHandler = errorHandler;
    }

    // ===== DECLARACIONES =====

    @Override
    public Symbol.Tipo visitSensorDecl(IoTLangParser.SensorDeclContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();
        if (symbolTable.lookupCurrentScope(nombre) != null) {
            errorHandler.addSemanticError(linea,
                "El sensor '" + nombre + "' ya fue declarado en este alcance.");
        } else {
            symbolTable.insert(new Symbol(nombre, Symbol.Category.SENSOR,
                Symbol.Tipo.SENSOR, symbolTable.getCurrentScopeName(), linea));
        }
        return null;
    }

    @Override
    public Symbol.Tipo visitActuadorDecl(IoTLangParser.ActuadorDeclContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();
        if (symbolTable.lookupCurrentScope(nombre) != null) {
            errorHandler.addSemanticError(linea,
                "El actuador '" + nombre + "' ya fue declarado en este alcance.");
        } else {
            symbolTable.insert(new Symbol(nombre, Symbol.Category.ACTUADOR,
                Symbol.Tipo.ACTUADOR, symbolTable.getCurrentScopeName(), linea));
        }
        return null;
    }

    @Override
    public Symbol.Tipo visitVarDecl(IoTLangParser.VarDeclContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();
        Symbol.Tipo tipoExpr = visit(ctx.expr());
        if (symbolTable.lookupCurrentScope(nombre) != null) {
            errorHandler.addSemanticError(linea,
                "La variable '" + nombre + "' ya fue declarada en este alcance.");
        } else {
            Symbol.Tipo tipo = (tipoExpr != null) ? tipoExpr : Symbol.Tipo.ENTERO;
            symbolTable.insert(new Symbol(nombre, Symbol.Category.VARIABLE,
                tipo, symbolTable.getCurrentScopeName(), linea));
        }
        return null;
    }

    // ===== ASIGNACIÓN =====

    @Override
    public Symbol.Tipo visitAssignStmt(IoTLangParser.AssignStmtContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();
        Symbol s = symbolTable.lookup(nombre);
        if (s == null) {
            errorHandler.addSemanticError(linea,
                "La variable '" + nombre + "' no fue declarada.");
            return null;
        }
        if (s.getCategoria() != Symbol.Category.VARIABLE) {
            errorHandler.addSemanticError(linea,
                "No se puede asignar a '" + nombre + "' porque es un " + s.getCategoria() + ", no una variable.");
            return null;
        }
        Symbol.Tipo tipoExpr = visit(ctx.expr());
        if (tipoExpr != null && !sonCompatibles(s.getTipo(), tipoExpr)) {
            errorHandler.addSemanticError(linea,
                "Tipo incompatible: no se puede asignar " + tipoExpr
                + " a '" + nombre + "' de tipo " + s.getTipo());
        }
        return null;
    }

    // ===== CONTROL DE ACTUADORES =====

    @Override
    public Symbol.Tipo visitEncenderStmt(IoTLangParser.EncenderStmtContext ctx) {
        validarEsActuador(ctx.ID().getText(), ctx.ID().getSymbol().getLine(), "encender");
        return null;
    }

    @Override
    public Symbol.Tipo visitApagarStmt(IoTLangParser.ApagarStmtContext ctx) {
        validarEsActuador(ctx.ID().getText(), ctx.ID().getSymbol().getLine(), "apagar");
        return null;
    }

    @Override
    public Symbol.Tipo visitAjustarStmt(IoTLangParser.AjustarStmtContext ctx) {
        validarEsActuador(ctx.ID().getText(), ctx.ID().getSymbol().getLine(), "ajustar");
        Symbol.Tipo tipoExpr = visit(ctx.expr());
        if (tipoExpr != null && !esNumerico(tipoExpr)) {
            errorHandler.addSemanticError(ctx.ID().getSymbol().getLine(),
                "El nivel para 'ajustar' debe ser un valor numérico, se encontró: " + tipoExpr);
        }
        return null;
    }

    @Override
    public Symbol.Tipo visitEnviarSenalStmt(IoTLangParser.EnviarSenalStmtContext ctx) {
        validarEsActuador(ctx.ID().getText(), ctx.ID().getSymbol().getLine(), "enviarSenal");
        return null;
    }

    // ===== LECTURA DE SENSORES =====

    @Override
    public Symbol.Tipo visitLeerTempStmt(IoTLangParser.LeerTempStmtContext ctx) {
        String varDestino = ctx.ID(0).getText();
        String sensor = ctx.ID(1).getText();
        int linea = ctx.ID(0).getSymbol().getLine();
        if (symbolTable.lookup(varDestino) == null) {
            errorHandler.addSemanticError(linea,
                "La variable '" + varDestino + "' no fue declarada.");
        }
        validarEsSensor(sensor, linea, "leerTemperatura");
        return Symbol.Tipo.DECIMAL;
    }

    @Override
    public Symbol.Tipo visitLeerHumStmt(IoTLangParser.LeerHumStmtContext ctx) {
        String varDestino = ctx.ID(0).getText();
        String sensor = ctx.ID(1).getText();
        int linea = ctx.ID(0).getSymbol().getLine();
        if (symbolTable.lookup(varDestino) == null) {
            errorHandler.addSemanticError(linea,
                "La variable '" + varDestino + "' no fue declarada.");
        }
        validarEsSensor(sensor, linea, "leerHumedad");
        return Symbol.Tipo.DECIMAL;
    }

    @Override
    public Symbol.Tipo visitLeerEstadoStmt(IoTLangParser.LeerEstadoStmtContext ctx) {
        String varDestino = ctx.ID(0).getText();
        String sensor = ctx.ID(1).getText();
        int linea = ctx.ID(0).getSymbol().getLine();
        if (symbolTable.lookup(varDestino) == null) {
            errorHandler.addSemanticError(linea,
                "La variable '" + varDestino + "' no fue declarada.");
        }
        validarEsSensor(sensor, linea, "leerEstado");
        return Symbol.Tipo.BOOLEANO;
    }

    // ===== TIEMPO Y EVENTOS =====

    @Override
    public Symbol.Tipo visitEsperarStmt(IoTLangParser.EsperarStmtContext ctx) {
        Symbol.Tipo tipoExpr = visit(ctx.expr());
        if (tipoExpr != null && !esNumerico(tipoExpr)) {
            errorHandler.addSemanticError(ctx.getStart().getLine(),
                "El tiempo de espera debe ser un valor numérico, se encontró: " + tipoExpr);
        }
        return null;
    }

    @Override
    public Symbol.Tipo visitProgramarStmt(IoTLangParser.ProgramarStmtContext ctx) {
        Symbol.Tipo tipoExpr = visit(ctx.expr());
        if (tipoExpr != null && !esNumerico(tipoExpr)) {
            errorHandler.addSemanticError(ctx.getStart().getLine(),
                "El intervalo de 'programar' debe ser numérico, se encontró: " + tipoExpr);
        }
        visit(ctx.statement());
        return null;
    }

    @Override
    public Symbol.Tipo visitCancelarStmt(IoTLangParser.CancelarStmtContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();
        Symbol s = symbolTable.lookup(nombre);
        if (s == null) {
            errorHandler.addSemanticError(linea,
                "El evento '" + nombre + "' no fue declarado.");
        }
        return null;
    }

    // ===== COMUNICACIÓN Y SALIDA =====

    @Override
    public Symbol.Tipo visitImprimirStmt(IoTLangParser.ImprimirStmtContext ctx) {
        visit(ctx.expr());
        return null;
    }

    @Override
    public Symbol.Tipo visitRegistrarStmt(IoTLangParser.RegistrarStmtContext ctx) {
        String dispositivo = ctx.ID(0).getText();
        String variable = ctx.ID(1).getText();
        int linea = ctx.ID(0).getSymbol().getLine();
        if (symbolTable.lookup(dispositivo) == null) {
            errorHandler.addSemanticError(linea,
                "El dispositivo '" + dispositivo + "' no fue declarado.");
        }
        if (symbolTable.lookup(variable) == null) {
            errorHandler.addSemanticError(linea,
                "La variable '" + variable + "' no fue declarada.");
        }
        return null;
    }

    @Override
    public Symbol.Tipo visitAlertaStmt(IoTLangParser.AlertaStmtContext ctx) {
        visit(ctx.expr());
        return null;
    }

    // ===== CONTROL DE FLUJO =====

    @Override
    public Symbol.Tipo visitIfStmt(IoTLangParser.IfStmtContext ctx) {
        visit(ctx.condition());
        symbolTable.enterScope();
        for (IoTLangParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }
        symbolTable.exitScope();
        // Si hay bloque 'sino', no podemos acceder directamente a los statements
        // del bloque sino porque ANTLR los mezcla. Usamos la estructura del contexto.
        // Los statements del bloque sino se visitan automáticamente si están
        // en la misma lista statement(). Manejamos el scope adecuadamente.
        return null;
    }

    @Override
    public Symbol.Tipo visitWhileStmt(IoTLangParser.WhileStmtContext ctx) {
        visit(ctx.condition());
        symbolTable.enterScope();
        for (IoTLangParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }
        symbolTable.exitScope();
        return null;
    }

    @Override
    public Symbol.Tipo visitCondition(IoTLangParser.ConditionContext ctx) {
        if (ctx.boolLiteral() != null) {
            return Symbol.Tipo.BOOLEANO;
        }
        Symbol.Tipo tipoIzq = visit(ctx.expr(0));
        Symbol.Tipo tipoDer = visit(ctx.expr(1));
        int linea = ctx.getStart().getLine();
        if (tipoIzq != null && tipoDer != null) {
            if (!esNumerico(tipoIzq) || !esNumerico(tipoDer)) {
                errorHandler.addSemanticError(linea,
                    "Los operandos de la comparación deben ser numéricos. Se encontró: "
                    + tipoIzq + " y " + tipoDer);
            }
        }
        return Symbol.Tipo.BOOLEANO;
    }

    // ===== EXPRESIONES =====

    @Override
    public Symbol.Tipo visitNumLiteral(IoTLangParser.NumLiteralContext ctx) {
        return Symbol.Tipo.ENTERO;
    }

    @Override
    public Symbol.Tipo visitDecimalLiteral(IoTLangParser.DecimalLiteralContext ctx) {
        return Symbol.Tipo.DECIMAL;
    }

    @Override
    public Symbol.Tipo visitStringLiteral(IoTLangParser.StringLiteralContext ctx) {
        return Symbol.Tipo.CADENA;
    }

    @Override
    public Symbol.Tipo visitIdExpr(IoTLangParser.IdExprContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();
        Symbol s = symbolTable.lookup(nombre);
        if (s == null) {
            errorHandler.addSemanticError(linea,
                "Identificador '" + nombre + "' no declarado.");
            return null;
        }
        return s.getTipo();
    }

    @Override
    public Symbol.Tipo visitBinaryExpr(IoTLangParser.BinaryExprContext ctx) {
        Symbol.Tipo tipoIzq = visit(ctx.expr(0));
        Symbol.Tipo tipoDer = visit(ctx.expr(1));
        int linea = ctx.op.getLine();
        if (tipoIzq != null && tipoDer != null) {
            if (!esNumerico(tipoIzq) || !esNumerico(tipoDer)) {
                errorHandler.addSemanticError(linea,
                    "Los operandos de '" + ctx.op.getText()
                    + "' deben ser numéricos. Se encontró: " + tipoIzq + " y " + tipoDer);
                return null;
            }
            // Si alguno es DECIMAL, el resultado es DECIMAL
            if (tipoIzq == Symbol.Tipo.DECIMAL || tipoDer == Symbol.Tipo.DECIMAL) {
                return Symbol.Tipo.DECIMAL;
            }
        }
        return Symbol.Tipo.ENTERO;
    }

    // ===== MÉTODOS AUXILIARES =====

    private void validarEsActuador(String nombre, int linea, String instruccion) {
        Symbol s = symbolTable.lookup(nombre);
        if (s == null) {
            errorHandler.addSemanticError(linea,
                "El dispositivo '" + nombre + "' no fue declarado para usar en '" + instruccion + "'.");
        } else if (s.getCategoria() != Symbol.Category.ACTUADOR) {
            errorHandler.addSemanticError(linea,
                "'" + nombre + "' no es un actuador. No se puede usar con '" + instruccion + "'.");
        }
    }

    private void validarEsSensor(String nombre, int linea, String instruccion) {
        Symbol s = symbolTable.lookup(nombre);
        if (s == null) {
            errorHandler.addSemanticError(linea,
                "El sensor '" + nombre + "' no fue declarado para usar en '" + instruccion + "'.");
        } else if (s.getCategoria() != Symbol.Category.SENSOR) {
            errorHandler.addSemanticError(linea,
                "'" + nombre + "' no es un sensor. No se puede usar con '" + instruccion + "'.");
        }
    }

    private boolean esNumerico(Symbol.Tipo tipo) {
        return tipo == Symbol.Tipo.ENTERO || tipo == Symbol.Tipo.DECIMAL;
    }

    private boolean sonCompatibles(Symbol.Tipo tipoVar, Symbol.Tipo tipoExpr) {
        if (esNumerico(tipoVar) && esNumerico(tipoExpr)) return true;
        return tipoVar == tipoExpr;
    }
}
