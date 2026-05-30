package semantic;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
    // Lista de errores encontrados
    private List<String> errors = new ArrayList<>();

    // Agrega un error léxico
    public void addLexicalError(int line, int column, String badChar) {
        String msg = String.format(
                "[ERROR LÉXICO] Línea %d, columna %d: Carácter no reconocido '%s'",
                line, column, badChar
        );
        errors.add(msg);
        System.err.println(msg);
    }

    // Agrega un error sintáctico
    public void addSyntaxError(int line, int column, String msg) {
        String error = String.format(
                "[ERROR SINTÁCTICO] Línea %d, columna %d: %s",
                line, column, msg
        );
        errors.add(error);
        System.err.println(error);
    }

    // Agrega un error semántico
    public void addSemanticError(int line, String msg) {
        String error = String.format(
                "[ERROR SEMÁNTICO] Línea %d: %s",
                line, msg
        );
        errors.add(error);
        System.err.println(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void printSummary() {
        System.out.println("\n--- RESUMEN DE ERRORES ---");
        System.out.println("Total de errores encontrados: " + errors.size());
        for (String e : errors) {
            System.out.println(e);
        }
    }
}
