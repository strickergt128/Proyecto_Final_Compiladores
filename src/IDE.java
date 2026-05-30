import grammar.IoTLangLexer;
import grammar.IoTLangParser;
import semantic.*;
import codegen.CodeGenerator;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Arrays;
import java.util.regex.*;

/**
 * Mini IDE visual para el compilador IoT DSL.
 * Permite escribir código, compilar con un clic y ver la salida
 * con colores en una consola integrada.
 */
public class IDE extends JFrame {

    // =============================================
    // PALETA DE COLORES (Inspirada en Tokyo Night)
    // =============================================
    private static final Color BG_MAIN       = new Color(0x1a, 0x1b, 0x26);
    private static final Color BG_EDITOR     = new Color(0x24, 0x28, 0x3b);
    private static final Color BG_TOOLBAR    = new Color(0x16, 0x16, 0x1e);
    private static final Color BG_STATUSBAR  = new Color(0x16, 0x16, 0x1e);
    private static final Color BG_CONSOLE    = new Color(0x1a, 0x1b, 0x26);
    private static final Color BG_LINENUMS   = new Color(0x1e, 0x20, 0x30);

    private static final Color TEXT_NORMAL   = new Color(0xa9, 0xb1, 0xd6);
    private static final Color TEXT_LINENUM  = new Color(0x56, 0x5f, 0x89);
    private static final Color TEXT_MUTED    = new Color(0x6e, 0x73, 0x8d);

    private static final Color ACCENT_BLUE   = new Color(0x7a, 0xa2, 0xf7);
    private static final Color ACCENT_GREEN  = new Color(0x9e, 0xce, 0x6a);
    private static final Color ACCENT_RED    = new Color(0xf7, 0x76, 0x8e);
    private static final Color ACCENT_CYAN   = new Color(0x7d, 0xcf, 0xff);
    private static final Color ACCENT_YELLOW = new Color(0xe0, 0xaf, 0x68);
    private static final Color ACCENT_PURPLE = new Color(0xbb, 0x9a, 0xf7);
    private static final Color ACCENT_ORANGE = new Color(0xff, 0x9e, 0x64);

    private static final Color SELECTION_BG  = new Color(0x33, 0x46, 0x7c);
    private static final Color BORDER_COLOR  = new Color(0x30, 0x34, 0x46);

    private static final Color BTN_COMPILE   = new Color(0x7a, 0xa2, 0xf7);
    private static final Color BTN_HOVER     = new Color(0x89, 0xb4, 0xfa);
    private static final Color BTN_NORMAL    = new Color(0x3b, 0x40, 0x52);
    private static final Color BTN_NORM_HVR  = new Color(0x4a, 0x50, 0x65);

    // =============================================
    // FUENTES
    // =============================================
    private static final Font FONT_CODE = new Font("Consolas", Font.PLAIN, 14);
    private static final Font FONT_CONSOLE = new Font("Consolas", Font.PLAIN, 13);
    private static final Font FONT_LINENUM = new Font("Consolas", Font.PLAIN, 13);
    private static final Font FONT_UI = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_UI_BOLD = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 15);

    // =============================================
    // COMPONENTES
    // =============================================
    private JTextArea editor;
    private JTextPane console;
    private JTextArea lineNumbers;
    private JLabel statusLabel;
    private JLabel statusIcon;
    private File archivoActual = null;
    
    // Variables para el árbol visual
    private ParseTree lastTree = null;
    private IoTLangParser lastParser = null;

    // =============================================
    // CÓDIGO DE EJEMPLO
    // =============================================
    private static final String EJEMPLO = 
        "// Sistema de control de temperatura IoT\n" +
        "// Escribe tu código aquí y presiona Compilar (F5)\n\n" +
        "sensor temp1;\n" +
        "actuador ventilador1;\n" +
        "var temperatura = 0;\n\n" +
        "temperatura = leerTemperatura(temp1);\n\n" +
        "si (temperatura > 30) {\n" +
        "    encender(ventilador1);\n" +
        "    ajustar(ventilador1, 80);\n" +
        "    imprimir(\"Ventilador encendido\");\n" +
        "} sino {\n" +
        "    apagar(ventilador1);\n" +
        "    imprimir(\"Ventilador apagado\");\n" +
        "}\n";

    // =============================================
    // CONSTRUCTOR
    // =============================================
    public IDE() {
        super("IoT DSL Compiler — Mini IDE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1150, 800);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);

        // Icono de la ventana
        try {
            setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        } catch (Exception ignored) {}

        // Layout principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_MAIN);

        // Construir componentes
        mainPanel.add(crearHeader(), BorderLayout.NORTH);
        mainPanel.add(crearCuerpo(), BorderLayout.CENTER);
        mainPanel.add(crearStatusBar(), BorderLayout.SOUTH);

        setContentPane(mainPanel);

        // Atajos de teclado globales
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "compilar");
        getRootPane().getActionMap().put("compilar", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { compilar(); }
        });

        // Cargar ejemplo
        editor.setText(EJEMPLO);
        editor.setCaretPosition(0);
        actualizarLineNumbers();
    }

    // =============================================
    // HEADER (Título + Toolbar)
    // =============================================
    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG_TOOLBAR);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR));

        // Barra de título
        JPanel titleBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        titleBar.setOpaque(false);
        JLabel titleLabel = new JLabel("::  IoT DSL Compiler");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(TEXT_NORMAL);
        titleBar.add(titleLabel);

        JLabel versionLabel = new JLabel("v1.0");
        versionLabel.setFont(FONT_UI);
        versionLabel.setForeground(TEXT_MUTED);
        titleBar.add(versionLabel);

        // Toolbar con botones
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        toolbar.setOpaque(false);
        toolbar.setBorder(BorderFactory.createEmptyBorder(0, 6, 4, 6));

        JButton btnCompilar = crearBoton(">>  Compilar", BTN_COMPILE, Color.WHITE);
        btnCompilar.addActionListener(e -> compilar());
        btnCompilar.setToolTipText("Compilar código (F5)");

        JButton btnAbrir = crearBoton("[+]  Abrir", BTN_NORMAL, TEXT_NORMAL);
        btnAbrir.addActionListener(e -> abrirArchivo());
        btnAbrir.setToolTipText("Abrir archivo .iot (Ctrl+O)");

        JButton btnGuardar = crearBoton("[S]  Guardar", BTN_NORMAL, TEXT_NORMAL);
        btnGuardar.addActionListener(e -> guardarArchivo());
        btnGuardar.setToolTipText("Guardar archivo .iot (Ctrl+S)");

        JButton btnNuevo = crearBoton("[N]  Nuevo", BTN_NORMAL, TEXT_NORMAL);
        btnNuevo.addActionListener(e -> {
            editor.setText("");
            archivoActual = null;
            setTitle("IoT DSL Compiler — Mini IDE");
            actualizarLineNumbers();
        });

        JButton btnLimpiar = crearBoton("[X]  Limpiar", BTN_NORMAL, TEXT_NORMAL);
        btnLimpiar.addActionListener(e -> limpiarConsola());
        btnLimpiar.setToolTipText("Limpiar la consola de salida");

        JButton btnArbol = crearBoton("[Y]  Arbol Visual", BTN_NORMAL, ACCENT_CYAN);
        btnArbol.addActionListener(e -> mostrarArbolVisual());
        btnArbol.setToolTipText("Mostrar el arbol sintactico graficamente");

        toolbar.add(btnCompilar);
        toolbar.add(Box.createHorizontalStrut(8));
        toolbar.add(btnArbol);
        toolbar.add(Box.createHorizontalStrut(8));
        toolbar.add(btnAbrir);
        toolbar.add(btnGuardar);
        toolbar.add(btnNuevo);
        toolbar.add(Box.createHorizontalStrut(8));
        toolbar.add(btnLimpiar);

        header.add(titleBar, BorderLayout.NORTH);
        header.add(toolbar, BorderLayout.SOUTH);
        return header;
    }

    // =============================================
    // CUERPO (Editor + Consola)
    // =============================================
    private JSplitPane crearCuerpo() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBackground(BG_MAIN);
        splitPane.setBorder(null);
        splitPane.setDividerSize(4);
        splitPane.setUI(new javax.swing.plaf.basic.BasicSplitPaneUI() {
            @Override
            public javax.swing.plaf.basic.BasicSplitPaneDivider createDefaultDivider() {
                return new javax.swing.plaf.basic.BasicSplitPaneDivider(this) {
                    @Override
                    public void paint(Graphics g) {
                        g.setColor(ACCENT_BLUE);
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
            }
        });

        splitPane.setTopComponent(crearPanelEditor());
        splitPane.setBottomComponent(crearPanelConsola());
        splitPane.setResizeWeight(0.6);

        return splitPane;
    }

    // --------- Panel del Editor ---------
    private JPanel crearPanelEditor() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_EDITOR);

        // Etiqueta "Editor"
        JLabel label = crearEtiquetaSeccion("  >>  Editor de Codigo");
        panel.add(label, BorderLayout.NORTH);

        // Editor de texto
        editor = new JTextArea();
        editor.setFont(FONT_CODE);
        editor.setBackground(BG_EDITOR);
        editor.setForeground(TEXT_NORMAL);
        editor.setCaretColor(ACCENT_BLUE);
        editor.setSelectionColor(SELECTION_BG);
        editor.setSelectedTextColor(Color.WHITE);
        editor.setTabSize(4);
        editor.setMargin(new Insets(8, 8, 8, 8));
        editor.setBorder(null);

        // Tab inserta espacios en vez de cambiar foco
        editor.setFocusTraversalKeysEnabled(false);
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    e.consume();
                    try {
                        editor.getDocument().insertString(
                            editor.getCaretPosition(), "    ", null);
                    } catch (BadLocationException ex) { /* ignorar */ }
                }
            }
        });

        // Números de línea
        lineNumbers = new JTextArea(" 1 ");
        lineNumbers.setFont(FONT_LINENUM);
        lineNumbers.setBackground(BG_LINENUMS);
        lineNumbers.setForeground(TEXT_LINENUM);
        lineNumbers.setEditable(false);
        lineNumbers.setFocusable(false);
        lineNumbers.setMargin(new Insets(8, 8, 8, 4));
        lineNumbers.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER_COLOR));

        editor.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarLineNumbers(); }
            public void removeUpdate(DocumentEvent e) { actualizarLineNumbers(); }
            public void changedUpdate(DocumentEvent e) { actualizarLineNumbers(); }
        });

        JScrollPane scroll = new JScrollPane(editor);
        scroll.setRowHeaderView(lineNumbers);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scroll.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        scroll.setBackground(BG_EDITOR);
        scroll.getViewport().setBackground(BG_EDITOR);

        // Sincronizar scroll del editor con los números de línea
        scroll.getVerticalScrollBar().addAdjustmentListener(e -> lineNumbers.repaint());

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    // --------- Panel de la Consola ---------
    private JPanel crearPanelConsola() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_CONSOLE);

        JLabel label = crearEtiquetaSeccion("  >>  Consola de Salida");
        panel.add(label, BorderLayout.NORTH);

        console = new JTextPane();
        console.setFont(FONT_CONSOLE);
        console.setBackground(BG_CONSOLE);
        console.setForeground(TEXT_NORMAL);
        console.setEditable(false);
        console.setMargin(new Insets(8, 12, 8, 12));
        console.setBorder(null);

        JScrollPane scroll = new JScrollPane(console);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scroll.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        scroll.setBackground(BG_CONSOLE);
        scroll.getViewport().setBackground(BG_CONSOLE);

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    // =============================================
    // STATUS BAR
    // =============================================
    private JPanel crearStatusBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(BG_STATUSBAR);
        bar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR),
            BorderFactory.createEmptyBorder(4, 12, 4, 12)
        ));

        statusIcon = new JLabel("●");
        statusIcon.setFont(FONT_UI);
        statusIcon.setForeground(TEXT_MUTED);

        statusLabel = new JLabel("  Listo — Escribe código y presiona F5 para compilar");
        statusLabel.setFont(FONT_UI);
        statusLabel.setForeground(TEXT_MUTED);

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        left.setOpaque(false);
        left.add(statusIcon);
        left.add(statusLabel);

        JLabel rightLabel = new JLabel("IoTLang DSL  ");
        rightLabel.setFont(FONT_UI);
        rightLabel.setForeground(TEXT_MUTED);

        bar.add(left, BorderLayout.WEST);
        bar.add(rightLabel, BorderLayout.EAST);
        return bar;
    }

    // =============================================
    // COMPILACIÓN (Pipeline completo)
    // =============================================
    private void compilar() {
        limpiarConsola();
        String codigo = editor.getText();

        if (codigo.trim().isEmpty()) {
            appendConsola("[!] El editor esta vacio. Escribe codigo IoTLang para compilar.", ACCENT_YELLOW);
            actualizarStatus("[!] Editor vacio", ACCENT_YELLOW);
            return;
        }

        // Suprimir stderr (ErrorHandler imprime ahí, usaremos getErrors() en su lugar)
        PrintStream stderrOriginal = System.err;
        System.setErr(new PrintStream(new OutputStream() {
            public void write(int b) {}
        }));

        try {
            ErrorHandler errorHandler = new ErrorHandler();

            // ═════════════════════════════════════════
            // FASE 1: ANÁLISIS LÉXICO
            // ═════════════════════════════════════════
            appendConsola("▶ FASE 1: Análisis Léxico...", ACCENT_CYAN);
            appendConsola("", TEXT_NORMAL);

            CharStream input = CharStreams.fromString(codigo);
            IoTLangLexer lexer = new IoTLangLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new IoTLexerErrorListener(errorHandler));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            tokens.fill();

            // Calcular ancho dinámico y mostrar tokens
            appendConsola("  Tokens reconocidos:", TEXT_NORMAL);
            int maxLen = 0;
            for (Token t : tokens.getTokens()) {
                if (t.getType() != Token.EOF) {
                    String n = nombreToken(t);
                    if (n.length() > maxLen) maxLen = n.length();
                }
            }
            for (Token t : tokens.getTokens()) {
                if (t.getType() != Token.EOF) {
                    String n = nombreToken(t);
                    Color c = (t.getType() == IoTLangLexer.ERROR_CHAR) ? ACCENT_RED : TEXT_MUTED;
                    appendConsola(String.format("    [%-" + maxLen + "s] '%s' (línea %d, col %d)",
                        n, t.getText(), t.getLine(), t.getCharPositionInLine()), c);
                }
            }

            // Detectar ERROR_CHAR
            for (Token t : tokens.getTokens()) {
                if (t.getType() == IoTLangLexer.ERROR_CHAR) {
                    errorHandler.addLexicalError(t.getLine(), t.getCharPositionInLine(), t.getText());
                }
            }

            if (errorHandler.hasErrors()) {
                mostrarErrores("léxicos", errorHandler);
                return;
            }
            int numTokens = tokens.getTokens().size() - 1;
            appendConsola("\n  ✅ Análisis léxico: OK — " + numTokens + " tokens reconocidos", ACCENT_GREEN);

            // ═════════════════════════════════════════
            // FASE 2: ANÁLISIS SINTÁCTICO
            // ═════════════════════════════════════════
            appendConsola("\n▶ FASE 2: Análisis Sintáctico...", ACCENT_CYAN);

            tokens.seek(0);
            IoTLangParser parser = new IoTLangParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new IoTParserErrorListener(errorHandler));

            ParseTree tree = parser.program();
            
            // Guardar para el visor gráfico
            lastTree = tree;
            lastParser = parser;

            if (errorHandler.hasErrors()) {
                mostrarErrores("sintácticos", errorHandler);
                return;
            }
            appendConsola("  ✅ Análisis sintáctico: OK — Árbol generado correctamente", ACCENT_GREEN);

            // Imprimir árbol indentado
            appendConsola("\n  Árbol de parseo:", TEXT_NORMAL);
            imprimirArbol(tree, parser, "    ");

            // ═════════════════════════════════════════
            // FASE 3: ANÁLISIS SEMÁNTICO
            // ═════════════════════════════════════════
            appendConsola("\n▶ FASE 3: Análisis Semántico...", ACCENT_CYAN);

            SymbolTable symbolTable = new SymbolTable(errorHandler);
            SemanticVisitor semanticVisitor = new SemanticVisitor(symbolTable, errorHandler);
            semanticVisitor.visit(tree);

            // Capturar tabla de símbolos
            String tablaSimbolos = capturarStdout(() -> symbolTable.printTable());
            appendConsola(tablaSimbolos, ACCENT_PURPLE);

            if (errorHandler.hasErrors()) {
                mostrarErrores("semánticos", errorHandler);
                return;
            }
            appendConsola("  ✅ Análisis semántico: OK — Sin errores de tipos ni declaraciones", ACCENT_GREEN);

            // ═════════════════════════════════════════
            // FASE 4: GENERACIÓN DE CÓDIGO INTERMEDIO
            // ═════════════════════════════════════════
            appendConsola("\n▶ FASE 4: Generación de Código Intermedio...", ACCENT_CYAN);

            CodeGenerator codeGen = new CodeGenerator();
            codeGen.visit(tree);

            // Capturar código intermedio
            String codigoIntermedio = capturarStdout(() -> codeGen.printCode());
            appendConsola(codigoIntermedio, ACCENT_YELLOW);

            int numInstr = codeGen.getCode().size();
            appendConsola("  ✅ Código intermedio generado — " + numInstr + " instrucciones", ACCENT_GREEN);

            // ═════════════════════════════════════════
            // RESUMEN FINAL
            // ═════════════════════════════════════════
            appendConsola("", TEXT_NORMAL);
            appendConsola("╔══════════════════════════════════════════════════════╗", ACCENT_GREEN);
            appendConsola("║            COMPILACIÓN EXITOSA ✅                    ║", ACCENT_GREEN);
            appendConsola("╠══════════════════════════════════════════════════════╣", ACCENT_GREEN);
            appendConsola("║  ✅ Fase 1: Análisis Léxico       — OK              ║", ACCENT_GREEN);
            appendConsola("║  ✅ Fase 2: Análisis Sintáctico   — OK              ║", ACCENT_GREEN);
            appendConsola("║  ✅ Fase 3: Análisis Semántico    — OK              ║", ACCENT_GREEN);
            appendConsola("║  ✅ Fase 4: Código Intermedio     — OK              ║", ACCENT_GREEN);
            appendConsola("╚══════════════════════════════════════════════════════╝", ACCENT_GREEN);

            actualizarStatus("✅  Compilación exitosa — " + numInstr + " instrucciones generadas", ACCENT_GREEN);

        } catch (Exception e) {
            appendConsola("\n❌ Error inesperado: " + e.getMessage(), ACCENT_RED);
            actualizarStatus("❌  Error inesperado", ACCENT_RED);
            e.printStackTrace();
        } finally {
            System.setErr(stderrOriginal);
        }
    }

    // =============================================
    // HELPERS DE COMPILACIÓN
    // =============================================

    private String nombreToken(Token t) {
        String n = IoTLangLexer.VOCABULARY.getSymbolicName(t.getType());
        if (n == null) n = IoTLangLexer.VOCABULARY.getDisplayName(t.getType());
        return n;
    }

    private void mostrarErrores(String tipo, ErrorHandler errorHandler) {
        appendConsola("\n❌ Errores " + tipo + " encontrados. Compilación detenida.", ACCENT_RED);
        appendConsola("", TEXT_NORMAL);
        appendConsola("── Resumen de Errores ──", ACCENT_RED);
        for (String err : errorHandler.getErrors()) {
            appendConsola("  " + err, ACCENT_ORANGE);
        }
        actualizarStatus("❌  " + errorHandler.getErrors().size() + " error(es) " + tipo, ACCENT_RED);
    }

    /** Imprime el árbol de parseo de forma indentada y legible. */
    private void imprimirArbol(ParseTree tree, Parser parser, String indent) {
        if (tree instanceof RuleContext) {
            String ruleName = parser.getRuleNames()[((RuleContext) tree).getRuleIndex()];
            appendConsola(indent + ruleName, ACCENT_BLUE);
            for (int i = 0; i < tree.getChildCount(); i++) {
                imprimirArbol(tree.getChild(i), parser, indent + "  ");
            }
        } else {
            String text = tree.getText();
            if (!text.equals("<EOF>")) {
                appendConsola(indent + "\"" + text + "\"", TEXT_MUTED);
            }
        }
    }

    /** Captura la salida de System.out durante la ejecución de un Runnable. */
    private String capturarStdout(Runnable accion) {
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(baos, true, "UTF-8"));
            accion.run();
        } catch (Exception e) {
            // ignorar
        } finally {
            System.setOut(original);
        }
        return baos.toString(StandardCharsets.UTF_8).trim();
    }

    // =============================================
    // HELPERS DE CONSOLA
    // =============================================

    /** Agrega texto con color a la consola. */
    private void appendConsola(String texto, Color color) {
        StyledDocument doc = console.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setForeground(attrs, color);
        StyleConstants.setFontFamily(attrs, "Consolas");
        StyleConstants.setFontSize(attrs, 13);
        try {
            doc.insertString(doc.getLength(), texto + "\n", attrs);
        } catch (BadLocationException e) {
            // ignorar
        }
        // Auto-scroll al final
        console.setCaretPosition(doc.getLength());
    }

    private void limpiarConsola() {
        console.setText("");
    }

    // =============================================
    // HELPERS DE UI
    // =============================================

    private void actualizarLineNumbers() {
        int lineas = editor.getLineCount();
        // Calcular dígitos necesarios
        int digitos = String.valueOf(lineas).length();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= lineas; i++) {
            sb.append(String.format(" %" + digitos + "d ", i));
            if (i < lineas) sb.append("\n");
        }
        lineNumbers.setText(sb.toString());
    }

    private void actualizarStatus(String mensaje, Color color) {
        statusIcon.setForeground(color);
        statusLabel.setText("  " + mensaje);
        statusLabel.setForeground(color);
    }

    private JLabel crearEtiquetaSeccion(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONT_UI_BOLD);
        label.setForeground(TEXT_MUTED);
        label.setOpaque(true);
        label.setBackground(BG_TOOLBAR);
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
            BorderFactory.createEmptyBorder(5, 4, 5, 4)
        ));
        return label;
    }

    /** Crea un botón estilizado con efecto hover. */
    private JButton crearBoton(String texto, Color bgColor, Color fgColor) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(FONT_UI_BOLD);
        btn.setForeground(fgColor);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));

        Color hoverColor = (bgColor == BTN_COMPILE) ? BTN_HOVER : BTN_NORM_HVR;
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hoverColor); btn.repaint(); }
            public void mouseExited(MouseEvent e) { btn.setBackground(bgColor); btn.repaint(); }
        });

        return btn;
    }

    // =============================================
    // MOSTRAR ÁRBOL VISUAL (ANTLR TreeViewer)
    // =============================================
    private void mostrarArbolVisual() {
        if (lastTree == null || lastParser == null) {
            JOptionPane.showMessageDialog(this, 
                "Primero debes compilar un codigo sin errores sintacticos.", 
                "Arbol no disponible", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Usamos una clase anonima para modificar los espacios (gaps) 
        // que son protected en TreeViewer, asi los nodos no se pegan.
        org.antlr.v4.gui.TreeViewer viewer = new org.antlr.v4.gui.TreeViewer(
            Arrays.asList(lastParser.getRuleNames()), lastTree) {
            {
                this.gapBetweenNodes = 25; // Mas separacion horizontal
                this.gapBetweenLevels = 50; // Mas separacion vertical
            }
        };
        
        viewer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Colores LIGHT MODE
        viewer.setBoxColor(new Color(245, 245, 250)); // Fondo gris muy claro
        viewer.setBorderColor(new Color(80, 80, 100)); // Borde oscuro
        viewer.setTextColor(Color.BLACK); // Texto negro
        
        // Envolver en un panel para darle un margen interior
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        wrapper.add(viewer, BorderLayout.CENTER);
        
        JScrollPane scrollPane = new JScrollPane(wrapper);
        // Quitamos los scrollbars oscuros para que combine con el modo claro
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(null);

        JDialog dialog = new JDialog(this, "Arbol de Parseo Visual", false);
        dialog.add(scrollPane);
        dialog.setSize(900, 700);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // =============================================
    // ABRIR / GUARDAR ARCHIVO
    // =============================================

    private void abrirArchivo() {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos IoT (*.iot)", "iot"));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                archivoActual = fc.getSelectedFile();
                String contenido = Files.readString(archivoActual.toPath(), StandardCharsets.UTF_8);
                editor.setText(contenido);
                editor.setCaretPosition(0);
                setTitle("IoT DSL Compiler — " + archivoActual.getName());
                actualizarLineNumbers();
                actualizarStatus("Archivo abierto: " + archivoActual.getName(), ACCENT_BLUE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                    "Error al abrir el archivo:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarArchivo() {
        if (archivoActual == null) {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos IoT (*.iot)", "iot"));
            fc.setSelectedFile(new File("programa.iot"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                archivoActual = fc.getSelectedFile();
                if (!archivoActual.getName().endsWith(".iot")) {
                    archivoActual = new File(archivoActual.getAbsolutePath() + ".iot");
                }
            } else {
                return;
            }
        }
        try {
            Files.writeString(archivoActual.toPath(), editor.getText(), StandardCharsets.UTF_8);
            setTitle("IoT DSL Compiler — " + archivoActual.getName());
            actualizarStatus("Archivo guardado: " + archivoActual.getName(), ACCENT_GREEN);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error al guardar:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // =============================================
    // SCROLLBAR PERSONALIZADO (Dark Theme)
    // =============================================
    private static class DarkScrollBarUI extends javax.swing.plaf.basic.BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(0x41, 0x48, 0x68);
            this.trackColor = BG_EDITOR;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(0, 0));
            return btn;
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(thumbColor);
            g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2,
                thumbBounds.width - 4, thumbBounds.height - 4, 6, 6);
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(trackColor);
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }
    }

    // =============================================
    // PUNTO DE ENTRADA
    // =============================================
    public static void main(String[] args) {
        // Anti-aliasing para texto
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            new IDE().setVisible(true);
        });
    }
}
