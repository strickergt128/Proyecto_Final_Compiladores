package codegen;

import grammar.IoTLangBaseVisitor;
import grammar.IoTLangParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Generador de código intermedio de 3 direcciones.
 * Recorre el árbol de parseo y emite instrucciones intermedias
 * usando temporales (T1, T2...) y etiquetas (L1, L2...).
 */
public class CodeGenerator extends IoTLangBaseVisitor<String> {

    private List<String> code = new ArrayList<>();
    private int tempCount = 0;
    private int labelCount = 0;

    /** Genera un nuevo nombre de temporal */
    private String newTemp() {
        tempCount++;
        return "T" + tempCount;
    }

    /** Genera una nueva etiqueta */
    private String newLabel() {
        labelCount++;
        return "L" + labelCount;
    }

    /** Emite una instrucción de código intermedio */
    private void emit(String instruction) {
        code.add(instruction);
    }

    // ===== DECLARACIONES =====

    @Override
    public String visitSensorDecl(IoTLangParser.SensorDeclContext ctx) {
        emit("DECLARE_SENSOR " + ctx.ID().getText());
        return null;
    }

    @Override
    public String visitActuadorDecl(IoTLangParser.ActuadorDeclContext ctx) {
        emit("DECLARE_ACTUADOR " + ctx.ID().getText());
        return null;
    }

    @Override
    public String visitVarDecl(IoTLangParser.VarDeclContext ctx) {
        String valor = visit(ctx.expr());
        emit(ctx.ID().getText() + " = " + valor);
        return null;
    }

    // ===== ASIGNACIÓN =====

    @Override
    public String visitAssignStmt(IoTLangParser.AssignStmtContext ctx) {
        String valor = visit(ctx.expr());
        emit(ctx.ID().getText() + " = " + valor);
        return null;
    }

    // ===== CONTROL DE ACTUADORES =====

    @Override
    public String visitEncenderStmt(IoTLangParser.EncenderStmtContext ctx) {
        emit("ENCENDER " + ctx.ID().getText());
        return null;
    }

    @Override
    public String visitApagarStmt(IoTLangParser.ApagarStmtContext ctx) {
        emit("APAGAR " + ctx.ID().getText());
        return null;
    }

    @Override
    public String visitAjustarStmt(IoTLangParser.AjustarStmtContext ctx) {
        String nivel = visit(ctx.expr());
        emit("AJUSTAR " + ctx.ID().getText() + " " + nivel);
        return null;
    }

    @Override
    public String visitEnviarSenalStmt(IoTLangParser.EnviarSenalStmtContext ctx) {
        emit("ENVIAR_SENAL " + ctx.ID().getText() + " " + ctx.STRING().getText());
        return null;
    }

    // ===== LECTURA DE SENSORES =====

    @Override
    public String visitLeerTempStmt(IoTLangParser.LeerTempStmtContext ctx) {
        String temp = newTemp();
        emit(temp + " = LEER_TEMPERATURA " + ctx.ID(1).getText());
        emit(ctx.ID(0).getText() + " = " + temp);
        return null;
    }

    @Override
    public String visitLeerHumStmt(IoTLangParser.LeerHumStmtContext ctx) {
        String temp = newTemp();
        emit(temp + " = LEER_HUMEDAD " + ctx.ID(1).getText());
        emit(ctx.ID(0).getText() + " = " + temp);
        return null;
    }

    @Override
    public String visitLeerEstadoStmt(IoTLangParser.LeerEstadoStmtContext ctx) {
        String temp = newTemp();
        emit(temp + " = LEER_ESTADO " + ctx.ID(1).getText());
        emit(ctx.ID(0).getText() + " = " + temp);
        return null;
    }

    // ===== TIEMPO Y EVENTOS =====

    @Override
    public String visitEsperarStmt(IoTLangParser.EsperarStmtContext ctx) {
        String tiempo = visit(ctx.expr());
        emit("ESPERAR " + tiempo);
        return null;
    }

    @Override
    public String visitProgramarStmt(IoTLangParser.ProgramarStmtContext ctx) {
        String intervalo = visit(ctx.expr());
        String lblEvento = newLabel();
        emit("PROGRAMAR_EVENTO " + lblEvento + " CADA " + intervalo);
        emit(lblEvento + ":");
        visit(ctx.statement());
        emit("FIN_EVENTO " + lblEvento);
        return null;
    }

    @Override
    public String visitCancelarStmt(IoTLangParser.CancelarStmtContext ctx) {
        emit("CANCELAR_EVENTO " + ctx.ID().getText());
        return null;
    }

    // ===== COMUNICACIÓN Y SALIDA =====

    @Override
    public String visitImprimirStmt(IoTLangParser.ImprimirStmtContext ctx) {
        String valor = visit(ctx.expr());
        emit("PRINT " + valor);
        return null;
    }

    @Override
    public String visitRegistrarStmt(IoTLangParser.RegistrarStmtContext ctx) {
        emit("LOG " + ctx.ID(0).getText() + " " + ctx.ID(1).getText());
        return null;
    }

    @Override
    public String visitAlertaStmt(IoTLangParser.AlertaStmtContext ctx) {
        String valor = visit(ctx.expr());
        emit("ALERT " + ctx.STRING().getText() + " " + valor);
        return null;
    }

    // ===== CONTROL DE FLUJO =====

    @Override
    public String visitIfStmt(IoTLangParser.IfStmtContext ctx) {
        String lblVerdadero = newLabel();
        String lblFalso = newLabel();
        String lblFin = newLabel();

        // Evaluar condición
        String cond = visitConditionCode(ctx.condition());

        emit("if " + cond + " goto " + lblVerdadero);
        emit("goto " + lblFalso);

        // Bloque SI (verdadero)
        emit(lblVerdadero + ":");
        for (IoTLangParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }
        emit("goto " + lblFin);

        // Bloque SINO (falso) — puede estar vacío
        emit(lblFalso + ":");
        // Los statements del bloque sino no son directamente accesibles
        // de forma separada en esta gramática simplificada

        emit(lblFin + ":");
        return null;
    }

    @Override
    public String visitWhileStmt(IoTLangParser.WhileStmtContext ctx) {
        String lblInicio = newLabel();
        String lblCuerpo = newLabel();
        String lblFin = newLabel();

        emit(lblInicio + ":");

        // Evaluar condición
        String cond = visitConditionCode(ctx.condition());

        emit("if " + cond + " goto " + lblCuerpo);
        emit("goto " + lblFin);

        // Cuerpo del ciclo
        emit(lblCuerpo + ":");
        for (IoTLangParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }
        emit("goto " + lblInicio);

        emit(lblFin + ":");
        return null;
    }

    /**
     * Genera código para la condición y retorna la representación en texto.
     */
    private String visitConditionCode(IoTLangParser.ConditionContext ctx) {
        if (ctx.boolLiteral() != null) {
            return ctx.boolLiteral().getText();
        }
        String izq = visit(ctx.expr(0));
        String der = visit(ctx.expr(1));
        String op = ctx.op.getText();
        String temp = newTemp();
        emit(temp + " = " + izq + " " + op + " " + der);
        return temp;
    }

    // ===== EXPRESIONES =====

    @Override
    public String visitBinaryExpr(IoTLangParser.BinaryExprContext ctx) {
        String izq = visit(ctx.expr(0));
        String der = visit(ctx.expr(1));
        String temp = newTemp();
        emit(temp + " = " + izq + " " + ctx.op.getText() + " " + der);
        return temp;
    }

    @Override
    public String visitNumLiteral(IoTLangParser.NumLiteralContext ctx) {
        return ctx.NUMERO().getText();
    }

    @Override
    public String visitDecimalLiteral(IoTLangParser.DecimalLiteralContext ctx) {
        return ctx.DECIMAL().getText();
    }

    @Override
    public String visitStringLiteral(IoTLangParser.StringLiteralContext ctx) {
        return ctx.STRING().getText();
    }

    @Override
    public String visitIdExpr(IoTLangParser.IdExprContext ctx) {
        return ctx.ID().getText();
    }

    // ===== SALIDA =====

    /**
     * Imprime todo el código intermedio generado en formato tabular.
     */
    public void printCode() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║          CÓDIGO INTERMEDIO (3 DIRECCIONES)           ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        for (int i = 0; i < code.size(); i++) {
            System.out.printf("║ %-3d │ %-48s║%n", i + 1, code.get(i));
        }
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    /** Retorna la lista de instrucciones generadas */
    public List<String> getCode() {
        return code;
    }
}
