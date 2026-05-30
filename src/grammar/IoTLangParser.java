package grammar;
// Generated from IoTLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class IoTLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SENSOR=1, ACTUADOR=2, VAR=3, SI=4, SINO=5, MIENTRAS=6, CADA=7, ENCENDER=8, 
		APAGAR=9, AJUSTAR=10, ENVIARSENAL=11, LEERTEMPERATURA=12, LEERHUMEDAD=13, 
		LEERESTADO=14, ESPERAR=15, PROGRAMAR=16, CANCELAR=17, IMPRIMIR=18, REGISTRAR=19, 
		ALERTA=20, VERDADERO=21, FALSO=22, DECIMAL=23, NUMERO=24, STRING=25, ID=26, 
		PLUS=27, MINUS=28, MULT=29, DIV=30, GTE=31, LTE=32, EQ=33, NEQ=34, GT=35, 
		LT=36, ASSIGN=37, SEMICOLON=38, COMMA=39, LPAREN=40, RPAREN=41, LBRACE=42, 
		RBRACE=43, COMMENT=44, BLOCK_COMMENT=45, WS=46, ERROR_CHAR=47;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_sensorDecl = 2, RULE_actuadorDecl = 3, 
		RULE_varDecl = 4, RULE_assignStmt = 5, RULE_encenderStmt = 6, RULE_apagarStmt = 7, 
		RULE_ajustarStmt = 8, RULE_enviarSenalStmt = 9, RULE_leerTempStmt = 10, 
		RULE_leerHumStmt = 11, RULE_leerEstadoStmt = 12, RULE_esperarStmt = 13, 
		RULE_programarStmt = 14, RULE_cancelarStmt = 15, RULE_imprimirStmt = 16, 
		RULE_registrarStmt = 17, RULE_alertaStmt = 18, RULE_ifStmt = 19, RULE_whileStmt = 20, 
		RULE_condition = 21, RULE_boolLiteral = 22, RULE_expr = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "sensorDecl", "actuadorDecl", "varDecl", "assignStmt", 
			"encenderStmt", "apagarStmt", "ajustarStmt", "enviarSenalStmt", "leerTempStmt", 
			"leerHumStmt", "leerEstadoStmt", "esperarStmt", "programarStmt", "cancelarStmt", 
			"imprimirStmt", "registrarStmt", "alertaStmt", "ifStmt", "whileStmt", 
			"condition", "boolLiteral", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'sensor'", "'actuador'", "'var'", "'si'", "'sino'", "'mientras'", 
			"'cada'", "'encender'", "'apagar'", "'ajustar'", "'enviarSenal'", "'leerTemperatura'", 
			"'leerHumedad'", "'leerEstado'", "'esperar'", "'programar'", "'cancelar'", 
			"'imprimir'", "'registrar'", "'alerta'", "'verdadero'", "'falso'", null, 
			null, null, null, "'+'", "'-'", "'*'", "'/'", "'>='", "'<='", "'=='", 
			"'!='", "'>'", "'<'", "'='", "';'", "','", "'('", "')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SENSOR", "ACTUADOR", "VAR", "SI", "SINO", "MIENTRAS", "CADA", 
			"ENCENDER", "APAGAR", "AJUSTAR", "ENVIARSENAL", "LEERTEMPERATURA", "LEERHUMEDAD", 
			"LEERESTADO", "ESPERAR", "PROGRAMAR", "CANCELAR", "IMPRIMIR", "REGISTRAR", 
			"ALERTA", "VERDADERO", "FALSO", "DECIMAL", "NUMERO", "STRING", "ID", 
			"PLUS", "MINUS", "MULT", "DIV", "GTE", "LTE", "EQ", "NEQ", "GT", "LT", 
			"ASSIGN", "SEMICOLON", "COMMA", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"COMMENT", "BLOCK_COMMENT", "WS", "ERROR_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IoTLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public IoTLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(IoTLangParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69177182L) != 0)) {
				{
				{
				setState(48);
				statement();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public SensorDeclContext sensorDecl() {
			return getRuleContext(SensorDeclContext.class,0);
		}
		public ActuadorDeclContext actuadorDecl() {
			return getRuleContext(ActuadorDeclContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public AssignStmtContext assignStmt() {
			return getRuleContext(AssignStmtContext.class,0);
		}
		public EncenderStmtContext encenderStmt() {
			return getRuleContext(EncenderStmtContext.class,0);
		}
		public ApagarStmtContext apagarStmt() {
			return getRuleContext(ApagarStmtContext.class,0);
		}
		public AjustarStmtContext ajustarStmt() {
			return getRuleContext(AjustarStmtContext.class,0);
		}
		public EnviarSenalStmtContext enviarSenalStmt() {
			return getRuleContext(EnviarSenalStmtContext.class,0);
		}
		public LeerTempStmtContext leerTempStmt() {
			return getRuleContext(LeerTempStmtContext.class,0);
		}
		public LeerHumStmtContext leerHumStmt() {
			return getRuleContext(LeerHumStmtContext.class,0);
		}
		public LeerEstadoStmtContext leerEstadoStmt() {
			return getRuleContext(LeerEstadoStmtContext.class,0);
		}
		public EsperarStmtContext esperarStmt() {
			return getRuleContext(EsperarStmtContext.class,0);
		}
		public ProgramarStmtContext programarStmt() {
			return getRuleContext(ProgramarStmtContext.class,0);
		}
		public CancelarStmtContext cancelarStmt() {
			return getRuleContext(CancelarStmtContext.class,0);
		}
		public ImprimirStmtContext imprimirStmt() {
			return getRuleContext(ImprimirStmtContext.class,0);
		}
		public RegistrarStmtContext registrarStmt() {
			return getRuleContext(RegistrarStmtContext.class,0);
		}
		public AlertaStmtContext alertaStmt() {
			return getRuleContext(AlertaStmtContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public WhileStmtContext whileStmt() {
			return getRuleContext(WhileStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				sensorDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				actuadorDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				varDecl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(59);
				assignStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
				encenderStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(61);
				apagarStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(62);
				ajustarStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(63);
				enviarSenalStmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(64);
				leerTempStmt();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(65);
				leerHumStmt();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(66);
				leerEstadoStmt();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(67);
				esperarStmt();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(68);
				programarStmt();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(69);
				cancelarStmt();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(70);
				imprimirStmt();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(71);
				registrarStmt();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(72);
				alertaStmt();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(73);
				ifStmt();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(74);
				whileStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SensorDeclContext extends ParserRuleContext {
		public TerminalNode SENSOR() { return getToken(IoTLangParser.SENSOR, 0); }
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public SensorDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sensorDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitSensorDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SensorDeclContext sensorDecl() throws RecognitionException {
		SensorDeclContext _localctx = new SensorDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sensorDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(SENSOR);
			setState(78);
			match(ID);
			setState(79);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActuadorDeclContext extends ParserRuleContext {
		public TerminalNode ACTUADOR() { return getToken(IoTLangParser.ACTUADOR, 0); }
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public ActuadorDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actuadorDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitActuadorDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActuadorDeclContext actuadorDecl() throws RecognitionException {
		ActuadorDeclContext _localctx = new ActuadorDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_actuadorDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(ACTUADOR);
			setState(82);
			match(ID);
			setState(83);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(IoTLangParser.VAR, 0); }
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(IoTLangParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(VAR);
			setState(86);
			match(ID);
			setState(87);
			match(ASSIGN);
			setState(88);
			expr(0);
			setState(89);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignStmtContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(IoTLangParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public AssignStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitAssignStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStmtContext assignStmt() throws RecognitionException {
		AssignStmtContext _localctx = new AssignStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(ID);
			setState(92);
			match(ASSIGN);
			setState(93);
			expr(0);
			setState(94);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EncenderStmtContext extends ParserRuleContext {
		public TerminalNode ENCENDER() { return getToken(IoTLangParser.ENCENDER, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public EncenderStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_encenderStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitEncenderStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EncenderStmtContext encenderStmt() throws RecognitionException {
		EncenderStmtContext _localctx = new EncenderStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_encenderStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(ENCENDER);
			setState(97);
			match(LPAREN);
			setState(98);
			match(ID);
			setState(99);
			match(RPAREN);
			setState(100);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ApagarStmtContext extends ParserRuleContext {
		public TerminalNode APAGAR() { return getToken(IoTLangParser.APAGAR, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public ApagarStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_apagarStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitApagarStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApagarStmtContext apagarStmt() throws RecognitionException {
		ApagarStmtContext _localctx = new ApagarStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_apagarStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(APAGAR);
			setState(103);
			match(LPAREN);
			setState(104);
			match(ID);
			setState(105);
			match(RPAREN);
			setState(106);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AjustarStmtContext extends ParserRuleContext {
		public TerminalNode AJUSTAR() { return getToken(IoTLangParser.AJUSTAR, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public TerminalNode COMMA() { return getToken(IoTLangParser.COMMA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public AjustarStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ajustarStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitAjustarStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AjustarStmtContext ajustarStmt() throws RecognitionException {
		AjustarStmtContext _localctx = new AjustarStmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ajustarStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(AJUSTAR);
			setState(109);
			match(LPAREN);
			setState(110);
			match(ID);
			setState(111);
			match(COMMA);
			setState(112);
			expr(0);
			setState(113);
			match(RPAREN);
			setState(114);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnviarSenalStmtContext extends ParserRuleContext {
		public TerminalNode ENVIARSENAL() { return getToken(IoTLangParser.ENVIARSENAL, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public TerminalNode COMMA() { return getToken(IoTLangParser.COMMA, 0); }
		public TerminalNode STRING() { return getToken(IoTLangParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public EnviarSenalStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enviarSenalStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitEnviarSenalStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnviarSenalStmtContext enviarSenalStmt() throws RecognitionException {
		EnviarSenalStmtContext _localctx = new EnviarSenalStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_enviarSenalStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(ENVIARSENAL);
			setState(117);
			match(LPAREN);
			setState(118);
			match(ID);
			setState(119);
			match(COMMA);
			setState(120);
			match(STRING);
			setState(121);
			match(RPAREN);
			setState(122);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeerTempStmtContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(IoTLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IoTLangParser.ID, i);
		}
		public TerminalNode ASSIGN() { return getToken(IoTLangParser.ASSIGN, 0); }
		public TerminalNode LEERTEMPERATURA() { return getToken(IoTLangParser.LEERTEMPERATURA, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public LeerTempStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leerTempStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitLeerTempStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeerTempStmtContext leerTempStmt() throws RecognitionException {
		LeerTempStmtContext _localctx = new LeerTempStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_leerTempStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(ID);
			setState(125);
			match(ASSIGN);
			setState(126);
			match(LEERTEMPERATURA);
			setState(127);
			match(LPAREN);
			setState(128);
			match(ID);
			setState(129);
			match(RPAREN);
			setState(130);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeerHumStmtContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(IoTLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IoTLangParser.ID, i);
		}
		public TerminalNode ASSIGN() { return getToken(IoTLangParser.ASSIGN, 0); }
		public TerminalNode LEERHUMEDAD() { return getToken(IoTLangParser.LEERHUMEDAD, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public LeerHumStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leerHumStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitLeerHumStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeerHumStmtContext leerHumStmt() throws RecognitionException {
		LeerHumStmtContext _localctx = new LeerHumStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_leerHumStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(ID);
			setState(133);
			match(ASSIGN);
			setState(134);
			match(LEERHUMEDAD);
			setState(135);
			match(LPAREN);
			setState(136);
			match(ID);
			setState(137);
			match(RPAREN);
			setState(138);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeerEstadoStmtContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(IoTLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IoTLangParser.ID, i);
		}
		public TerminalNode ASSIGN() { return getToken(IoTLangParser.ASSIGN, 0); }
		public TerminalNode LEERESTADO() { return getToken(IoTLangParser.LEERESTADO, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public LeerEstadoStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leerEstadoStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitLeerEstadoStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeerEstadoStmtContext leerEstadoStmt() throws RecognitionException {
		LeerEstadoStmtContext _localctx = new LeerEstadoStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_leerEstadoStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(ID);
			setState(141);
			match(ASSIGN);
			setState(142);
			match(LEERESTADO);
			setState(143);
			match(LPAREN);
			setState(144);
			match(ID);
			setState(145);
			match(RPAREN);
			setState(146);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EsperarStmtContext extends ParserRuleContext {
		public TerminalNode ESPERAR() { return getToken(IoTLangParser.ESPERAR, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public EsperarStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_esperarStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitEsperarStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EsperarStmtContext esperarStmt() throws RecognitionException {
		EsperarStmtContext _localctx = new EsperarStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_esperarStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(ESPERAR);
			setState(149);
			match(LPAREN);
			setState(150);
			expr(0);
			setState(151);
			match(RPAREN);
			setState(152);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramarStmtContext extends ParserRuleContext {
		public TerminalNode PROGRAMAR() { return getToken(IoTLangParser.PROGRAMAR, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode CADA() { return getToken(IoTLangParser.CADA, 0); }
		public List<TerminalNode> COMMA() { return getTokens(IoTLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(IoTLangParser.COMMA, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public ProgramarStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programarStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitProgramarStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramarStmtContext programarStmt() throws RecognitionException {
		ProgramarStmtContext _localctx = new ProgramarStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_programarStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(PROGRAMAR);
			setState(155);
			match(LPAREN);
			setState(156);
			match(CADA);
			setState(157);
			match(COMMA);
			setState(158);
			expr(0);
			setState(159);
			match(COMMA);
			setState(160);
			statement();
			setState(161);
			match(RPAREN);
			setState(162);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CancelarStmtContext extends ParserRuleContext {
		public TerminalNode CANCELAR() { return getToken(IoTLangParser.CANCELAR, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public CancelarStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cancelarStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitCancelarStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CancelarStmtContext cancelarStmt() throws RecognitionException {
		CancelarStmtContext _localctx = new CancelarStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_cancelarStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(CANCELAR);
			setState(165);
			match(LPAREN);
			setState(166);
			match(ID);
			setState(167);
			match(RPAREN);
			setState(168);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImprimirStmtContext extends ParserRuleContext {
		public TerminalNode IMPRIMIR() { return getToken(IoTLangParser.IMPRIMIR, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public ImprimirStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imprimirStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitImprimirStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImprimirStmtContext imprimirStmt() throws RecognitionException {
		ImprimirStmtContext _localctx = new ImprimirStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_imprimirStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(IMPRIMIR);
			setState(171);
			match(LPAREN);
			setState(172);
			expr(0);
			setState(173);
			match(RPAREN);
			setState(174);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RegistrarStmtContext extends ParserRuleContext {
		public TerminalNode REGISTRAR() { return getToken(IoTLangParser.REGISTRAR, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public List<TerminalNode> ID() { return getTokens(IoTLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IoTLangParser.ID, i);
		}
		public TerminalNode COMMA() { return getToken(IoTLangParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public RegistrarStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_registrarStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitRegistrarStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegistrarStmtContext registrarStmt() throws RecognitionException {
		RegistrarStmtContext _localctx = new RegistrarStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_registrarStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(REGISTRAR);
			setState(177);
			match(LPAREN);
			setState(178);
			match(ID);
			setState(179);
			match(COMMA);
			setState(180);
			match(ID);
			setState(181);
			match(RPAREN);
			setState(182);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlertaStmtContext extends ParserRuleContext {
		public TerminalNode ALERTA() { return getToken(IoTLangParser.ALERTA, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(IoTLangParser.STRING, 0); }
		public TerminalNode COMMA() { return getToken(IoTLangParser.COMMA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(IoTLangParser.SEMICOLON, 0); }
		public AlertaStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alertaStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitAlertaStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlertaStmtContext alertaStmt() throws RecognitionException {
		AlertaStmtContext _localctx = new AlertaStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_alertaStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(ALERTA);
			setState(185);
			match(LPAREN);
			setState(186);
			match(STRING);
			setState(187);
			match(COMMA);
			setState(188);
			expr(0);
			setState(189);
			match(RPAREN);
			setState(190);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode SI() { return getToken(IoTLangParser.SI, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public List<TerminalNode> LBRACE() { return getTokens(IoTLangParser.LBRACE); }
		public TerminalNode LBRACE(int i) {
			return getToken(IoTLangParser.LBRACE, i);
		}
		public List<TerminalNode> RBRACE() { return getTokens(IoTLangParser.RBRACE); }
		public TerminalNode RBRACE(int i) {
			return getToken(IoTLangParser.RBRACE, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode SINO() { return getToken(IoTLangParser.SINO, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_ifStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(SI);
			setState(193);
			match(LPAREN);
			setState(194);
			condition();
			setState(195);
			match(RPAREN);
			setState(196);
			match(LBRACE);
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69177182L) != 0)) {
				{
				{
				setState(197);
				statement();
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(203);
			match(RBRACE);
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SINO) {
				{
				setState(204);
				match(SINO);
				setState(205);
				match(LBRACE);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69177182L) != 0)) {
					{
					{
					setState(206);
					statement();
					}
					}
					setState(211);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(212);
				match(RBRACE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends ParserRuleContext {
		public TerminalNode MIENTRAS() { return getToken(IoTLangParser.MIENTRAS, 0); }
		public TerminalNode LPAREN() { return getToken(IoTLangParser.LPAREN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(IoTLangParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(IoTLangParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(IoTLangParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_whileStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(MIENTRAS);
			setState(216);
			match(LPAREN);
			setState(217);
			condition();
			setState(218);
			match(RPAREN);
			setState(219);
			match(LBRACE);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69177182L) != 0)) {
				{
				{
				setState(220);
				statement();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode GT() { return getToken(IoTLangParser.GT, 0); }
		public TerminalNode LT() { return getToken(IoTLangParser.LT, 0); }
		public TerminalNode GTE() { return getToken(IoTLangParser.GTE, 0); }
		public TerminalNode LTE() { return getToken(IoTLangParser.LTE, 0); }
		public TerminalNode EQ() { return getToken(IoTLangParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(IoTLangParser.NEQ, 0); }
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_condition);
		int _la;
		try {
			setState(233);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
			case NUMERO:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				expr(0);
				setState(229);
				((ConditionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 135291469824L) != 0)) ) {
					((ConditionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(230);
				expr(0);
				}
				break;
			case VERDADERO:
			case FALSO:
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				boolLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolLiteralContext extends ParserRuleContext {
		public TerminalNode VERDADERO() { return getToken(IoTLangParser.VERDADERO, 0); }
		public TerminalNode FALSO() { return getToken(IoTLangParser.FALSO, 0); }
		public BoolLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiteralContext boolLiteral() throws RecognitionException {
		BoolLiteralContext _localctx = new BoolLiteralContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_boolLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_la = _input.LA(1);
			if ( !(_la==VERDADERO || _la==FALSO) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecimalLiteralContext extends ExprContext {
		public TerminalNode DECIMAL() { return getToken(IoTLangParser.DECIMAL, 0); }
		public DecimalLiteralContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitDecimalLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralContext extends ExprContext {
		public TerminalNode STRING() { return getToken(IoTLangParser.STRING, 0); }
		public StringLiteralContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumLiteralContext extends ExprContext {
		public TerminalNode NUMERO() { return getToken(IoTLangParser.NUMERO, 0); }
		public NumLiteralContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitNumLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinaryExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(IoTLangParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(IoTLangParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(IoTLangParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(IoTLangParser.MINUS, 0); }
		public BinaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(IoTLangParser.ID, 0); }
		public IdExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof IoTLangVisitor ) return ((IoTLangVisitor<? extends T>)visitor).visitIdExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMERO:
				{
				_localctx = new NumLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(238);
				match(NUMERO);
				}
				break;
			case DECIMAL:
				{
				_localctx = new DecimalLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(239);
				match(DECIMAL);
				}
				break;
			case STRING:
				{
				_localctx = new StringLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(240);
				match(STRING);
				}
				break;
			case ID:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(241);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(252);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(250);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(244);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(245);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(246);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(247);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(248);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(249);
						expr(6);
						}
						break;
					}
					} 
				}
				setState(254);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 23:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001/\u0100\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0005\u0000"+
		"2\b\u0000\n\u0000\f\u00005\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001L\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0005\u0013\u00c7\b\u0013\n\u0013\f\u0013\u00ca"+
		"\t\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00d0"+
		"\b\u0013\n\u0013\f\u0013\u00d3\t\u0013\u0001\u0013\u0003\u0013\u00d6\b"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0005\u0014\u00de\b\u0014\n\u0014\f\u0014\u00e1\t\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0003\u0015\u00ea\b\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u00f3\b\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017"+
		"\u00fb\b\u0017\n\u0017\f\u0017\u00fe\t\u0017\u0001\u0017\u0000\u0001."+
		"\u0018\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.\u0000\u0004\u0001\u0000\u001f$\u0001\u0000"+
		"\u0015\u0016\u0001\u0000\u001d\u001e\u0001\u0000\u001b\u001c\u0104\u0000"+
		"3\u0001\u0000\u0000\u0000\u0002K\u0001\u0000\u0000\u0000\u0004M\u0001"+
		"\u0000\u0000\u0000\u0006Q\u0001\u0000\u0000\u0000\bU\u0001\u0000\u0000"+
		"\u0000\n[\u0001\u0000\u0000\u0000\f`\u0001\u0000\u0000\u0000\u000ef\u0001"+
		"\u0000\u0000\u0000\u0010l\u0001\u0000\u0000\u0000\u0012t\u0001\u0000\u0000"+
		"\u0000\u0014|\u0001\u0000\u0000\u0000\u0016\u0084\u0001\u0000\u0000\u0000"+
		"\u0018\u008c\u0001\u0000\u0000\u0000\u001a\u0094\u0001\u0000\u0000\u0000"+
		"\u001c\u009a\u0001\u0000\u0000\u0000\u001e\u00a4\u0001\u0000\u0000\u0000"+
		" \u00aa\u0001\u0000\u0000\u0000\"\u00b0\u0001\u0000\u0000\u0000$\u00b8"+
		"\u0001\u0000\u0000\u0000&\u00c0\u0001\u0000\u0000\u0000(\u00d7\u0001\u0000"+
		"\u0000\u0000*\u00e9\u0001\u0000\u0000\u0000,\u00eb\u0001\u0000\u0000\u0000"+
		".\u00f2\u0001\u0000\u0000\u000002\u0003\u0002\u0001\u000010\u0001\u0000"+
		"\u0000\u000025\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000034\u0001"+
		"\u0000\u0000\u000046\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u0000"+
		"67\u0005\u0000\u0000\u00017\u0001\u0001\u0000\u0000\u00008L\u0003\u0004"+
		"\u0002\u00009L\u0003\u0006\u0003\u0000:L\u0003\b\u0004\u0000;L\u0003\n"+
		"\u0005\u0000<L\u0003\f\u0006\u0000=L\u0003\u000e\u0007\u0000>L\u0003\u0010"+
		"\b\u0000?L\u0003\u0012\t\u0000@L\u0003\u0014\n\u0000AL\u0003\u0016\u000b"+
		"\u0000BL\u0003\u0018\f\u0000CL\u0003\u001a\r\u0000DL\u0003\u001c\u000e"+
		"\u0000EL\u0003\u001e\u000f\u0000FL\u0003 \u0010\u0000GL\u0003\"\u0011"+
		"\u0000HL\u0003$\u0012\u0000IL\u0003&\u0013\u0000JL\u0003(\u0014\u0000"+
		"K8\u0001\u0000\u0000\u0000K9\u0001\u0000\u0000\u0000K:\u0001\u0000\u0000"+
		"\u0000K;\u0001\u0000\u0000\u0000K<\u0001\u0000\u0000\u0000K=\u0001\u0000"+
		"\u0000\u0000K>\u0001\u0000\u0000\u0000K?\u0001\u0000\u0000\u0000K@\u0001"+
		"\u0000\u0000\u0000KA\u0001\u0000\u0000\u0000KB\u0001\u0000\u0000\u0000"+
		"KC\u0001\u0000\u0000\u0000KD\u0001\u0000\u0000\u0000KE\u0001\u0000\u0000"+
		"\u0000KF\u0001\u0000\u0000\u0000KG\u0001\u0000\u0000\u0000KH\u0001\u0000"+
		"\u0000\u0000KI\u0001\u0000\u0000\u0000KJ\u0001\u0000\u0000\u0000L\u0003"+
		"\u0001\u0000\u0000\u0000MN\u0005\u0001\u0000\u0000NO\u0005\u001a\u0000"+
		"\u0000OP\u0005&\u0000\u0000P\u0005\u0001\u0000\u0000\u0000QR\u0005\u0002"+
		"\u0000\u0000RS\u0005\u001a\u0000\u0000ST\u0005&\u0000\u0000T\u0007\u0001"+
		"\u0000\u0000\u0000UV\u0005\u0003\u0000\u0000VW\u0005\u001a\u0000\u0000"+
		"WX\u0005%\u0000\u0000XY\u0003.\u0017\u0000YZ\u0005&\u0000\u0000Z\t\u0001"+
		"\u0000\u0000\u0000[\\\u0005\u001a\u0000\u0000\\]\u0005%\u0000\u0000]^"+
		"\u0003.\u0017\u0000^_\u0005&\u0000\u0000_\u000b\u0001\u0000\u0000\u0000"+
		"`a\u0005\b\u0000\u0000ab\u0005(\u0000\u0000bc\u0005\u001a\u0000\u0000"+
		"cd\u0005)\u0000\u0000de\u0005&\u0000\u0000e\r\u0001\u0000\u0000\u0000"+
		"fg\u0005\t\u0000\u0000gh\u0005(\u0000\u0000hi\u0005\u001a\u0000\u0000"+
		"ij\u0005)\u0000\u0000jk\u0005&\u0000\u0000k\u000f\u0001\u0000\u0000\u0000"+
		"lm\u0005\n\u0000\u0000mn\u0005(\u0000\u0000no\u0005\u001a\u0000\u0000"+
		"op\u0005\'\u0000\u0000pq\u0003.\u0017\u0000qr\u0005)\u0000\u0000rs\u0005"+
		"&\u0000\u0000s\u0011\u0001\u0000\u0000\u0000tu\u0005\u000b\u0000\u0000"+
		"uv\u0005(\u0000\u0000vw\u0005\u001a\u0000\u0000wx\u0005\'\u0000\u0000"+
		"xy\u0005\u0019\u0000\u0000yz\u0005)\u0000\u0000z{\u0005&\u0000\u0000{"+
		"\u0013\u0001\u0000\u0000\u0000|}\u0005\u001a\u0000\u0000}~\u0005%\u0000"+
		"\u0000~\u007f\u0005\f\u0000\u0000\u007f\u0080\u0005(\u0000\u0000\u0080"+
		"\u0081\u0005\u001a\u0000\u0000\u0081\u0082\u0005)\u0000\u0000\u0082\u0083"+
		"\u0005&\u0000\u0000\u0083\u0015\u0001\u0000\u0000\u0000\u0084\u0085\u0005"+
		"\u001a\u0000\u0000\u0085\u0086\u0005%\u0000\u0000\u0086\u0087\u0005\r"+
		"\u0000\u0000\u0087\u0088\u0005(\u0000\u0000\u0088\u0089\u0005\u001a\u0000"+
		"\u0000\u0089\u008a\u0005)\u0000\u0000\u008a\u008b\u0005&\u0000\u0000\u008b"+
		"\u0017\u0001\u0000\u0000\u0000\u008c\u008d\u0005\u001a\u0000\u0000\u008d"+
		"\u008e\u0005%\u0000\u0000\u008e\u008f\u0005\u000e\u0000\u0000\u008f\u0090"+
		"\u0005(\u0000\u0000\u0090\u0091\u0005\u001a\u0000\u0000\u0091\u0092\u0005"+
		")\u0000\u0000\u0092\u0093\u0005&\u0000\u0000\u0093\u0019\u0001\u0000\u0000"+
		"\u0000\u0094\u0095\u0005\u000f\u0000\u0000\u0095\u0096\u0005(\u0000\u0000"+
		"\u0096\u0097\u0003.\u0017\u0000\u0097\u0098\u0005)\u0000\u0000\u0098\u0099"+
		"\u0005&\u0000\u0000\u0099\u001b\u0001\u0000\u0000\u0000\u009a\u009b\u0005"+
		"\u0010\u0000\u0000\u009b\u009c\u0005(\u0000\u0000\u009c\u009d\u0005\u0007"+
		"\u0000\u0000\u009d\u009e\u0005\'\u0000\u0000\u009e\u009f\u0003.\u0017"+
		"\u0000\u009f\u00a0\u0005\'\u0000\u0000\u00a0\u00a1\u0003\u0002\u0001\u0000"+
		"\u00a1\u00a2\u0005)\u0000\u0000\u00a2\u00a3\u0005&\u0000\u0000\u00a3\u001d"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005\u0011\u0000\u0000\u00a5\u00a6"+
		"\u0005(\u0000\u0000\u00a6\u00a7\u0005\u001a\u0000\u0000\u00a7\u00a8\u0005"+
		")\u0000\u0000\u00a8\u00a9\u0005&\u0000\u0000\u00a9\u001f\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ab\u0005\u0012\u0000\u0000\u00ab\u00ac\u0005(\u0000\u0000"+
		"\u00ac\u00ad\u0003.\u0017\u0000\u00ad\u00ae\u0005)\u0000\u0000\u00ae\u00af"+
		"\u0005&\u0000\u0000\u00af!\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u0013"+
		"\u0000\u0000\u00b1\u00b2\u0005(\u0000\u0000\u00b2\u00b3\u0005\u001a\u0000"+
		"\u0000\u00b3\u00b4\u0005\'\u0000\u0000\u00b4\u00b5\u0005\u001a\u0000\u0000"+
		"\u00b5\u00b6\u0005)\u0000\u0000\u00b6\u00b7\u0005&\u0000\u0000\u00b7#"+
		"\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005\u0014\u0000\u0000\u00b9\u00ba"+
		"\u0005(\u0000\u0000\u00ba\u00bb\u0005\u0019\u0000\u0000\u00bb\u00bc\u0005"+
		"\'\u0000\u0000\u00bc\u00bd\u0003.\u0017\u0000\u00bd\u00be\u0005)\u0000"+
		"\u0000\u00be\u00bf\u0005&\u0000\u0000\u00bf%\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c1\u0005\u0004\u0000\u0000\u00c1\u00c2\u0005(\u0000\u0000\u00c2\u00c3"+
		"\u0003*\u0015\u0000\u00c3\u00c4\u0005)\u0000\u0000\u00c4\u00c8\u0005*"+
		"\u0000\u0000\u00c5\u00c7\u0003\u0002\u0001\u0000\u00c6\u00c5\u0001\u0000"+
		"\u0000\u0000\u00c7\u00ca\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9\u00cb\u0001\u0000"+
		"\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb\u00d5\u0005+\u0000"+
		"\u0000\u00cc\u00cd\u0005\u0005\u0000\u0000\u00cd\u00d1\u0005*\u0000\u0000"+
		"\u00ce\u00d0\u0003\u0002\u0001\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000"+
		"\u00d0\u00d3\u0001\u0000\u0000\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000"+
		"\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d4\u00d6\u0005+\u0000\u0000\u00d5"+
		"\u00cc\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6"+
		"\'\u0001\u0000\u0000\u0000\u00d7\u00d8\u0005\u0006\u0000\u0000\u00d8\u00d9"+
		"\u0005(\u0000\u0000\u00d9\u00da\u0003*\u0015\u0000\u00da\u00db\u0005)"+
		"\u0000\u0000\u00db\u00df\u0005*\u0000\u0000\u00dc\u00de\u0003\u0002\u0001"+
		"\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000\u00de\u00e1\u0001\u0000\u0000"+
		"\u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e2\u0001\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e3\u0005+\u0000\u0000\u00e3)\u0001\u0000\u0000\u0000\u00e4"+
		"\u00e5\u0003.\u0017\u0000\u00e5\u00e6\u0007\u0000\u0000\u0000\u00e6\u00e7"+
		"\u0003.\u0017\u0000\u00e7\u00ea\u0001\u0000\u0000\u0000\u00e8\u00ea\u0003"+
		",\u0016\u0000\u00e9\u00e4\u0001\u0000\u0000\u0000\u00e9\u00e8\u0001\u0000"+
		"\u0000\u0000\u00ea+\u0001\u0000\u0000\u0000\u00eb\u00ec\u0007\u0001\u0000"+
		"\u0000\u00ec-\u0001\u0000\u0000\u0000\u00ed\u00ee\u0006\u0017\uffff\uffff"+
		"\u0000\u00ee\u00f3\u0005\u0018\u0000\u0000\u00ef\u00f3\u0005\u0017\u0000"+
		"\u0000\u00f0\u00f3\u0005\u0019\u0000\u0000\u00f1\u00f3\u0005\u001a\u0000"+
		"\u0000\u00f2\u00ed\u0001\u0000\u0000\u0000\u00f2\u00ef\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f2\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f3\u00fc\u0001\u0000\u0000\u0000\u00f4\u00f5\n\u0006\u0000\u0000"+
		"\u00f5\u00f6\u0007\u0002\u0000\u0000\u00f6\u00fb\u0003.\u0017\u0007\u00f7"+
		"\u00f8\n\u0005\u0000\u0000\u00f8\u00f9\u0007\u0003\u0000\u0000\u00f9\u00fb"+
		"\u0003.\u0017\u0006\u00fa\u00f4\u0001\u0000\u0000\u0000\u00fa\u00f7\u0001"+
		"\u0000\u0000\u0000\u00fb\u00fe\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001"+
		"\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd/\u0001\u0000"+
		"\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\n3K\u00c8\u00d1\u00d5"+
		"\u00df\u00e9\u00f2\u00fa\u00fc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}