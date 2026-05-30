package semantic;

import java.util.*;

/**
 * Tabla de símbolos con soporte para alcances anidados (scopes).
 * Usa una pila de mapas para representar los niveles de alcance.
 */
public class SymbolTable {

    private Deque<Map<String, Symbol>> scopeStack;
    private ErrorHandler errorHandler;

    public SymbolTable(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        this.scopeStack = new ArrayDeque<>();
        enterScope(); // Alcance global
    }

    /** Entra a un nuevo alcance (bloque) */
    public void enterScope() {
        scopeStack.push(new LinkedHashMap<>());
    }

    /** Sale del alcance actual */
    public void exitScope() {
        if (!scopeStack.isEmpty()) {
            scopeStack.pop();
        }
    }

    /**
     * Inserta un símbolo en el alcance actual.
     * @return true si se insertó correctamente, false si ya existe en el alcance actual.
     */
    public boolean insert(Symbol symbol) {
        Map<String, Symbol> currentScope = scopeStack.peek();
        if (currentScope.containsKey(symbol.getNombre())) {
            return false;
        }
        currentScope.put(symbol.getNombre(), symbol);
        return true;
    }

    /**
     * Busca un símbolo en todos los alcances (del más interno al más externo).
     * @return el símbolo encontrado, o null si no existe.
     */
    public Symbol lookup(String nombre) {
        for (Map<String, Symbol> scope : scopeStack) {
            if (scope.containsKey(nombre)) {
                return scope.get(nombre);
            }
        }
        return null;
    }

    /**
     * Busca un símbolo solo en el alcance actual.
     */
    public Symbol lookupCurrentScope(String nombre) {
        return scopeStack.peek().getOrDefault(nombre, null);
    }

    /**
     * Retorna el nombre del alcance actual.
     */
    public String getCurrentScopeName() {
        return scopeStack.size() == 1 ? "global" : "bloque_" + scopeStack.size();
    }

    /**
     * Imprime toda la tabla de símbolos en formato tabular.
     */
    public void printTable() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         TABLA DE SÍMBOLOS                           ║");
        System.out.println("╠═════════════════╦════════════╦══════════╦═════════════════╦══════════╣");
        System.out.println("║ Nombre          ║ Categoría  ║ Tipo     ║ Alcance         ║ Línea    ║");
        System.out.println("╠═════════════════╬════════════╬══════════╬═════════════════╬══════════╣");

        List<Map<String, Symbol>> scopes = new ArrayList<>(scopeStack);
        Collections.reverse(scopes);
        for (Map<String, Symbol> scope : scopes) {
            for (Symbol s : scope.values()) {
                System.out.printf("║ %-15s ║ %-10s ║ %-8s ║ %-15s ║ %-8d ║%n",
                    s.getNombre(), s.getCategoria(), s.getTipo(),
                    s.getAlcance(), s.getLineaDeclaracion());
            }
        }
        System.out.println("╚═════════════════╩════════════╩══════════╩═════════════════╩══════════╝");
    }
}
