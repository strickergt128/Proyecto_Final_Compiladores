import grammar.IoTLangLexer;
import grammar.IoTLangParser;
import semantic.*;
import codegen.CodeGenerator;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Punto de entrada del compilador IoT DSL.
 * Ejecuta las 4 fases del compilador en secuencia:
 *   1. Análisis Léxico
 *   2. Análisis Sintáctico
 *   3. Análisis Semántico
 *   4. Generación de Código Intermedio
 */
public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.err.println("╔══════════════════════════════════════════════╗");
            System.err.println("║  Uso: java Main <archivo.iot>               ║");
            System.err.println("║  Ejemplo: java Main test/valid/test1.iot    ║");
            System.err.println("╚══════════════════════════════════════════════╝");
            System.exit(1);
        }

        String archivo = args[0];
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║         COMPILADOR IoT DSL — Inicio                 ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  Archivo: " + String.format("%-41s", archivo) + "║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        // Leer el archivo fuente
        CharStream input = CharStreams.fromFileName(archivo);
        ErrorHandler errorHandler = new ErrorHandler();

        // ═══════════════════════════════════════
        // FASE 1: ANÁLISIS LÉXICO
        // ═══════════════════════════════════════
        System.out.println("\n▶ FASE 1: Análisis Léxico...");

        IoTLangLexer lexer = new IoTLangLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new IoTLexerErrorListener(errorHandler));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill(); // Forzar tokenización completa

        // Mostrar tokens reconocidos
        System.out.println("\n  Tokens reconocidos:");
        // Calcular el ancho máximo del nombre de token para alinear
        int maxLen = 0;
        for (Token token : tokens.getTokens()) {
            if (token.getType() != Token.EOF) {
                String nombre = IoTLangLexer.VOCABULARY.getSymbolicName(token.getType());
                if (nombre == null) nombre = IoTLangLexer.VOCABULARY.getDisplayName(token.getType());
                if (nombre.length() > maxLen) maxLen = nombre.length();
            }
        }
        for (Token token : tokens.getTokens()) {
            if (token.getType() != Token.EOF) {
                String nombreTipo = IoTLangLexer.VOCABULARY.getSymbolicName(token.getType());
                if (nombreTipo == null) nombreTipo = IoTLangLexer.VOCABULARY.getDisplayName(token.getType());
                System.out.printf("    [%-" + maxLen + "s] '%s' (línea %d, col %d)%n",
                    nombreTipo, token.getText(), token.getLine(), token.getCharPositionInLine());
            }
        }

        // Verificar si hay tokens ERROR_CHAR (errores léxicos)
        for (Token token : tokens.getTokens()) {
            if (token.getType() == IoTLangLexer.ERROR_CHAR) {
                errorHandler.addLexicalError(token.getLine(),
                    token.getCharPositionInLine(), token.getText());
            }
        }

        if (errorHandler.hasErrors()) {
            System.out.println("\n❌ Errores léxicos encontrados. Compilación detenida.");
            errorHandler.printSummary();
            return;
        }
        System.out.println("\n  ✅ Análisis léxico: OK — " + (tokens.getTokens().size() - 1) + " tokens reconocidos");

        // ═══════════════════════════════════════
        // FASE 2: ANÁLISIS SINTÁCTICO
        // ═══════════════════════════════════════
        System.out.println("\n▶ FASE 2: Análisis Sintáctico...");

        // Resetear el stream de tokens para el parser
        tokens.seek(0);

        IoTLangParser parser = new IoTLangParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new IoTParserErrorListener(errorHandler));

        ParseTree tree = parser.program();

        if (errorHandler.hasErrors()) {
            System.out.println("\n❌ Errores sintácticos encontrados. Compilación detenida.");
            errorHandler.printSummary();
            return;
        }
        System.out.println("  ✅ Análisis sintáctico: OK — Árbol de parseo generado correctamente");
        System.out.println("\n  Árbol de parseo:");
        printPrettyTree(tree, parser, "  ");

        // ═══════════════════════════════════════
        // FASE 3: ANÁLISIS SEMÁNTICO
        // ═══════════════════════════════════════
        System.out.println("\n▶ FASE 3: Análisis Semántico...");

        SymbolTable symbolTable = new SymbolTable(errorHandler);
        SemanticVisitor semanticVisitor = new SemanticVisitor(symbolTable, errorHandler);
        semanticVisitor.visit(tree);

        // Mostrar tabla de símbolos siempre
        symbolTable.printTable();

        if (errorHandler.hasErrors()) {
            System.out.println("\n❌ Errores semánticos encontrados. Compilación detenida.");
            errorHandler.printSummary();
            return;
        }
        System.out.println("  ✅ Análisis semántico: OK — Sin errores de tipos ni declaraciones");

        // ═══════════════════════════════════════
        // FASE 4: GENERACIÓN DE CÓDIGO INTERMEDIO
        // ═══════════════════════════════════════
        System.out.println("\n▶ FASE 4: Generación de Código Intermedio...");

        CodeGenerator codeGen = new CodeGenerator();
        codeGen.visit(tree);
        codeGen.printCode();

        System.out.println("  ✅ Código intermedio generado exitosamente — "
            + codeGen.getCode().size() + " instrucciones");

        // ═══════════════════════════════════════
        // RESUMEN FINAL
        // ═══════════════════════════════════════
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║            COMPILACIÓN EXITOSA ✅                    ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  ✅ Fase 1: Análisis Léxico       — OK              ║");
        System.out.println("║  ✅ Fase 2: Análisis Sintáctico   — OK              ║");
        System.out.println("║  ✅ Fase 3: Análisis Semántico    — OK              ║");
        System.out.println("║  ✅ Fase 4: Código Intermedio     — OK              ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    /**
     * Imprime el árbol de parseo de forma indentada y legible.
     */
    private static void printPrettyTree(ParseTree tree, Parser parser, String indent) {
        if (tree instanceof RuleContext) {
            String ruleName = parser.getRuleNames()[((RuleContext) tree).getRuleIndex()];
            System.out.println(indent + ruleName);
            for (int i = 0; i < tree.getChildCount(); i++) {
                printPrettyTree(tree.getChild(i), parser, indent + "  ");
            }
        } else {
            // Nodo terminal (token)
            String text = tree.getText();
            if (!text.equals("<EOF>")) {
                System.out.println(indent + "\"" + text + "\"");
            }
        }
    }
}
