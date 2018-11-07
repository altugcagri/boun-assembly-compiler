package tr.edu.boun.gen.anltr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExprGrammarFileLexer extends Lexer {

  static {
    RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
  }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
  public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9, T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, MUL = 14, DIV = 15, MOD = 16, ADD = 17, SUB = 18, POW = 19, ID = 20, NUM = 21, WS = 22;
  public static String[] channelNames = {"DEFAULT_TOKEN_CHANNEL", "HIDDEN"};

  public static String[] modeNames = {"DEFAULT_MODE"};

  public static final String[] ruleNames = {"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", "T__9", "T__10", "T__11", "T__12", "MUL",
      "DIV", "MOD", "ADD", "SUB", "POW", "ID", "NUM", "WS"};

  private static final String[] _LITERAL_NAMES = {null, "'begin'", "'end'", "'='", "'print'", "'if'", "'then'", "'while'", "'do'", "';'", "'pow('", "','",
      "')'", "'('", "'*'", "'/'", "'mod'", "'+'", "'-'", "'^'"};
  private static final String[] _SYMBOLIC_NAMES = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, "MUL", "DIV", "MOD",
      "ADD", "SUB", "POW", "ID", "NUM", "WS"};
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


  public ExprGrammarFileLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  @Override
  public String getGrammarFileName() {
    return "ExprGrammarFile.g4";
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public String[] getChannelNames() {
    return channelNames;
  }

  @Override
  public String[] getModeNames() {
    return modeNames;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }

  public static final String _serializedATN =
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u0080\b\1\4\2" + "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"
          + "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22" + "\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"
          + "\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6" + "\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13"
          + "\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"
          + "\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\7\25p\n\25\f\25" + "\16\25s\13\25\3\26\6\26v\n\26\r\26\16\26w\3\27\6\27{\n\27\r\27\16\27|"
          + "\3\27\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31" + "\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30\3\2\6\5\2C\\aac|\6"
          + "\2\62;C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\2\u0082\2\3\3\2\2\2\2\5\3\2\2" + "\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"
          + "\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2" + "\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"
          + "\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\65\3\2\2\2\79\3\2" + "\2\2\t;\3\2\2\2\13A\3\2\2\2\rD\3\2\2\2\17I\3\2\2\2\21O\3\2\2\2\23R\3\2"
          + "\2\2\25T\3\2\2\2\27Y\3\2\2\2\31[\3\2\2\2\33]\3\2\2\2\35_\3\2\2\2\37a\3" + "\2\2\2!c\3\2\2\2#g\3\2\2\2%i\3\2\2\2\'k\3\2\2\2)m\3\2\2\2+u\3\2\2\2-z"
          + "\3\2\2\2/\60\7d\2\2\60\61\7g\2\2\61\62\7i\2\2\62\63\7k\2\2\63\64\7p\2" + "\2\64\4\3\2\2\2\65\66\7g\2\2\66\67\7p\2\2\678\7f\2\28\6\3\2\2\29:\7?\2"
          + "\2:\b\3\2\2\2;<\7r\2\2<=\7t\2\2=>\7k\2\2>?\7p\2\2?@\7v\2\2@\n\3\2\2\2" + "AB\7k\2\2BC\7h\2\2C\f\3\2\2\2DE\7v\2\2EF\7j\2\2FG\7g\2\2GH\7p\2\2H\16"
          + "\3\2\2\2IJ\7y\2\2JK\7j\2\2KL\7k\2\2LM\7n\2\2MN\7g\2\2N\20\3\2\2\2OP\7" + "f\2\2PQ\7q\2\2Q\22\3\2\2\2RS\7=\2\2S\24\3\2\2\2TU\7r\2\2UV\7q\2\2VW\7"
          + "y\2\2WX\7*\2\2X\26\3\2\2\2YZ\7.\2\2Z\30\3\2\2\2[\\\7+\2\2\\\32\3\2\2\2" + "]^\7*\2\2^\34\3\2\2\2_`\7,\2\2`\36\3\2\2\2ab\7\61\2\2b \3\2\2\2cd\7o\2"
          + "\2de\7q\2\2ef\7f\2\2f\"\3\2\2\2gh\7-\2\2h$\3\2\2\2ij\7/\2\2j&\3\2\2\2" + "kl\7`\2\2l(\3\2\2\2mq\t\2\2\2np\t\3\2\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2"
          + "qr\3\2\2\2r*\3\2\2\2sq\3\2\2\2tv\t\4\2\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2" + "wx\3\2\2\2x,\3\2\2\2y{\t\5\2\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2"
          + "}~\3\2\2\2~\177\b\27\2\2\177.\3\2\2\2\6\2qw|\3\b\2\2";
  public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}