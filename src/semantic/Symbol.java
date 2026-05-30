package semantic;

/**
 * Representa un símbolo en la tabla de símbolos del compilador.
 * Almacena información sobre sensores, actuadores y variables declaradas.
 */
public class Symbol {

    /** Categorías de símbolos */
    public enum Category { SENSOR, ACTUADOR, VARIABLE }

    /** Tipos de datos del lenguaje IoT */
    public enum Tipo { ENTERO, DECIMAL, CADENA, BOOLEANO, SENSOR, ACTUADOR }

    private String nombre;
    private Category categoria;
    private Tipo tipo;
    private String alcance;
    private int lineaDeclaracion;
    private Object valor;

    public Symbol(String nombre, Category categoria, Tipo tipo,
                  String alcance, int lineaDeclaracion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.tipo = tipo;
        this.alcance = alcance;
        this.lineaDeclaracion = lineaDeclaracion;
    }

    // --- Getters ---
    public String getNombre() { return nombre; }
    public Category getCategoria() { return categoria; }
    public Tipo getTipo() { return tipo; }
    public String getAlcance() { return alcance; }
    public int getLineaDeclaracion() { return lineaDeclaracion; }
    public Object getValor() { return valor; }
    public void setValor(Object valor) { this.valor = valor; }

    @Override
    public String toString() {
        return String.format("| %-15s | %-10s | %-8s | %-15s | Línea %-4d |",
            nombre, categoria, tipo, alcance, lineaDeclaracion);
    }
}
