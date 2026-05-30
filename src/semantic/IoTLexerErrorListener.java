package semantic;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class IoTLexerErrorListener extends BaseErrorListener {
    private ErrorHandler errorHandler;

    public IoTLexerErrorListener(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        String badChar = offendingSymbol != null
                ? offendingSymbol.toString()
                : "desconocido";
        errorHandler.addLexicalError(line, charPositionInLine, badChar);
    }
}
