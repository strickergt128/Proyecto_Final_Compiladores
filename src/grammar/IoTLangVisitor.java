package grammar;
// Generated from IoTLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link IoTLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface IoTLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(IoTLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(IoTLangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#sensorDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSensorDecl(IoTLangParser.SensorDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#actuadorDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActuadorDecl(IoTLangParser.ActuadorDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(IoTLangParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#assignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(IoTLangParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#encenderStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEncenderStmt(IoTLangParser.EncenderStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#apagarStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApagarStmt(IoTLangParser.ApagarStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#ajustarStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAjustarStmt(IoTLangParser.AjustarStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#enviarSenalStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnviarSenalStmt(IoTLangParser.EnviarSenalStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#leerTempStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeerTempStmt(IoTLangParser.LeerTempStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#leerHumStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeerHumStmt(IoTLangParser.LeerHumStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#leerEstadoStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeerEstadoStmt(IoTLangParser.LeerEstadoStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#esperarStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEsperarStmt(IoTLangParser.EsperarStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#programarStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramarStmt(IoTLangParser.ProgramarStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#cancelarStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCancelarStmt(IoTLangParser.CancelarStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#imprimirStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImprimirStmt(IoTLangParser.ImprimirStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#registrarStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistrarStmt(IoTLangParser.RegistrarStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#alertaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlertaStmt(IoTLangParser.AlertaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(IoTLangParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(IoTLangParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(IoTLangParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoTLangParser#boolLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(IoTLangParser.BoolLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decimalLiteral}
	 * labeled alternative in {@link IoTLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalLiteral(IoTLangParser.DecimalLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link IoTLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(IoTLangParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numLiteral}
	 * labeled alternative in {@link IoTLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumLiteral(IoTLangParser.NumLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link IoTLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(IoTLangParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link IoTLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(IoTLangParser.IdExprContext ctx);
}