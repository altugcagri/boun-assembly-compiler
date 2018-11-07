package tr.edu.boun.assembly.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tr.edu.boun.gen.anltr.ExprGrammarFileBaseVisitor;
import tr.edu.boun.gen.anltr.ExprGrammarFileParser;

/**
 * This Class makes assembly code generation
 * of the lines in the input file
 *
 * @author Cagri Altug and Serhat Uzuncavdar
 */
public class CommandGenerator extends ExprGrammarFileBaseVisitor<Integer> {

  private static final String RANDOM_LABEL_CHAR_SET = "abcdefghijklmnopqrstuvwxyz";
  private final Map<String, Integer> memory = new HashMap<String, Integer>();
  private final List<String> printList = new ArrayList();
  private boolean printVisited = false;

  /**
   * This method is implementation of assign a value to a variable
   * it converts '=' to assembly code
   * Example of string line to be converted to assembly:
   * a = 5
   */
  @Override
  public Integer visitAssign(ExprGrammarFileParser.AssignContext ctx) {
    String id = ctx.ID().getText();
    int value = visit(ctx.expr());
    memory.put(id, value);
    addToPrintList("     POP AX");
    addToPrintList("     MOV " + id + ", AX");
    return 0;
  }

  /**
   * This method is implementation of printing to screen
   * it calls 'print' print function on assembly code
   * Example of string line to be converted to assembly:
   * print ( 5 )
   */
  @Override
  public Integer visitPrint(ExprGrammarFileParser.PrintContext ctx) {
    addToPrintList("     POP AX");
    addToPrintList("     CALL print");
    setPrintVisited(true);
    return 0;
  }

  /**
   * This method is implementation of if statement
   * it converts 'if (expression) then' to assembly code
   *
   * Example of string line to be converted to assembly:
   * if ( a = 5 ) then
   */
  @Override
  public Integer visitIfthen(ExprGrammarFileParser.IfthenContext ctx) {
    visit(ctx.expr());
    String randomLabel = RandomLabel(5);
    addToPrintList("     POP AX");
    addToPrintList("     CMP AX, 0");
    addToPrintList("     JZ " + randomLabel);
    visit(ctx.stm());
    addToPrintList(randomLabel + ":");
    return 0;
  }

  /**
   * This method is implementation of while statement
   * it converts 'while (expression) do' to assembly code
   *
   * Example of string line to be converted to assembly:
   * while ( a < 10 ) do
   */
  @Override
  public Integer visitWhiledo(ExprGrammarFileParser.WhiledoContext ctx) {
    String randomLabel1 = RandomLabel(5);
    String randomLabel2 = RandomLabel(5);
    addToPrintList(randomLabel1 + ":");
    visit(ctx.expr());
    addToPrintList("     POP AX");
    addToPrintList("     CMP AX, 0");
    addToPrintList("     JZ " + randomLabel2);
    visit(ctx.stm());
    addToPrintList("     JMP " + randomLabel1);
    addToPrintList(randomLabel2 + ":");
    return 0;
  }

  /**
   * This method is implementation of begin and end  statement
   * it converts 'begin code block end' to assembly code
   *
   * Example of string line to be converted to assembly:
   * begin
   * a = 6;
   * b = 3;
   * c = a + b;
   * print(c)
   * end
   */
  @Override
  public Integer visitBeginend(ExprGrammarFileParser.BeginendContext ctx) {
    visit(ctx.opt_stmts());
    return 0;
  }

  /**
   * This method is implementation of int
   */
  @Override
  public Integer visitInt(ExprGrammarFileParser.IntContext ctx) {
    addToPrintList("     PUSH " + ctx.NUM().getText());
    return 0;
  }

  /**
   * This method is implementation of ID
   */
  @Override
  public Integer visitId(ExprGrammarFileParser.IdContext ctx) {
    String id = ctx.ID().getText();
    addToPrintList("     PUSH " + id);
    return 0;
  }

  /**
   * This method is implementation of multiplication operation
   * it converts '*' to assembly code
   * Example of string line to be converted to assembly:
   * 4 * 3
   */
  @Override
  public Integer visitMul(ExprGrammarFileParser.MulContext ctx) {
    String randomLabel = RandomLabel(5);
    addToPrintList("     POP CX");
    addToPrintList("     POP AX");
    addToPrintList("     TEST AX, AX");
    addToPrintList("     JNS " + randomLabel);
    addToPrintList("     NEG AX");
    addToPrintList("     NEG CX");
    addToPrintList(randomLabel + ":");
    addToPrintList("     IMUL CX");
    addToPrintList("     PUSH AX");
    addToPrintList("     MOV DX, 0");
    return 0;
  }

  /**
   * This method is implementation of division operation
   * it converts '*' to assembly code
   * Example of string line to be converted to assembly:
   * 12 / 3
   */
  @Override
  public Integer visitDiv(ExprGrammarFileParser.DivContext ctx) {
    String randomLabel = RandomLabel(5);
    addToPrintList("     POP CX");
    addToPrintList("     POP AX");
    addToPrintList("     TEST AX, AX");
    addToPrintList("     JNS " + randomLabel);
    addToPrintList("     NEG AX");
    addToPrintList("     NEG CX");
    addToPrintList(randomLabel + ":");
    addToPrintList("     IDIV CX");
    addToPrintList("     PUSH AX");
    addToPrintList("     MOV DX, 0");
    return 0;
  }

  /**
   * This method is implementation of modulus operation
   * it converts 'k mod m ' to assembly code
   * Example of string line to be converted to assembly:
   * 3 mod 5
   */
  @Override
  public Integer visitMod(ExprGrammarFileParser.ModContext ctx) {
    addToPrintList("     POP CX");
    addToPrintList("     POP AX");
    addToPrintList("     DIV CX");
    addToPrintList("     PUSH DX");
    addToPrintList("     MOV DX, 0");
    return 0;
  }

  /**
   * This method is implementation of addition operation
   * it converts '+' to assembly code
   * Example of string line to be converted to assembly:
   * 12 + 3
   */
  @Override
  public Integer visitAdd(ExprGrammarFileParser.AddContext ctx) {
    addToPrintList("     POP CX");
    addToPrintList("     POP AX");
    addToPrintList("     ADD AX, CX");
    addToPrintList("     PUSH AX");
    addToPrintList("     MOV DX, 0");
    return 0;
  }

  /**
   * This method is implementation of subscription operation
   * it converts '-' to assembly code
   * Example of string line to be converted to assembly:
   * 12 - 3
   */
  @Override
  public Integer visitSub(ExprGrammarFileParser.SubContext ctx) {
    addToPrintList("     POP CX");
    addToPrintList("     POP AX");
    addToPrintList("     SUB AX, CX");
    addToPrintList("     PUSH AX");
    addToPrintList("     MOV DX, 0");
    return 0;
  }

  /**
   * This method is implementation of parenthesis
   */
  @Override
  public Integer visitParens(ExprGrammarFileParser.ParensContext ctx) {
    //Implementation for parenthesis
    return visit(ctx.expr());
  }

  /**
   * This method is implementation of printing to screen
   * it converts 'print' to assembly code
   * Example of string line to be converted to assembly:
   * print ( 5 )
   */
  public void CallPrint() {
    addToPrintList("print: ");
    addToPrintList("     TEST AX, AX");
    addToPrintList("     JNS positive");
    addToPrintList("     PUSH AX");
    addToPrintList("     MOV DX, '-'");
    addToPrintList("     MOV AH, 02H");
    addToPrintList("     INT 21H");
    addToPrintList("     POP AX");
    addToPrintList("     NEG AX");
    addToPrintList("positive:");
    addToPrintList("     MOV SI, 10d");
    addToPrintList("     XOR DX, DX");
    addToPrintList("     MOV CX, 0");
    addToPrintList("nonzero:");
    addToPrintList("     DIV SI");
    addToPrintList("     ADD DX, 48d");
    addToPrintList("     PUSH DX");
    addToPrintList("     INC CX");
    addToPrintList("     XOR DX, DX");
    addToPrintList("     CMP AX, 0h");
    addToPrintList("     JNE nonzero");
    addToPrintList("printloop:");
    addToPrintList("     POP DX");
    addToPrintList("     MOV AH, 02h");
    addToPrintList("     INT 21h");
    addToPrintList("     DEC CX");
    addToPrintList("     JNZ printloop");
    addToPrintList("     MOV DX, ' '");
    addToPrintList("     MOV AH, 02h");
    addToPrintList("     MOV DX, 0");
    addToPrintList("     INT 21h");
    addToPrintList("     ret");
    setPrintVisited(false);
  }

  /**
   * This method is implementation of power operation
   * it converts 'pow(num1, num2)' to assembly code
   * it takes num2 nd power of num 1
   * Example of string line to be converted to assembly:
   * pow(2,3)
   */
  @Override
  public Integer visitPower(ExprGrammarFileParser.PowerContext ctx) {
    String randomLabel1 = RandomLabel(5);
    String randomLabel2 = RandomLabel(5);
    visit(ctx.expr(0));
    visit(ctx.expr(1));
    addToPrintList(randomLabel1 + ":");
    addToPrintList("     POP BX");
    addToPrintList("     CMP BX, 1");
    addToPrintList("     JZ " + randomLabel2);
    visit(ctx.expr(0));
    String randomLabel = RandomLabel(5);
    addToPrintList("     POP CX");
    addToPrintList("     POP AX");
    addToPrintList("     TEST AX, AX");
    addToPrintList("     JNS " + randomLabel);
    addToPrintList("     NEG AX");
    addToPrintList("     NEG CX");
    addToPrintList(randomLabel + ":");
    addToPrintList("     IMUL CX");
    addToPrintList("     PUSH AX");
    addToPrintList("     DEC BX");
    addToPrintList("     PUSH BX");
    addToPrintList("     JMP " + randomLabel1);
    addToPrintList(randomLabel2 + ":");
    return 0;
  }

  /**
   * This method is implementation of exit statement
   */
  public void PrintExit() {
    addToPrintList("EXIT:");
    addToPrintList("     INT 20");

  }

  /**
   * This method is implementation of DATA statement
   */
  public void PrintData() {
    //Variable declaration for assembly code
    addToPrintList("DATA: ");
    for (int dataIndex = 0; dataIndex < memory.size(); dataIndex++) {
      printList.add(memory.keySet().toArray()[dataIndex] + " DW 0");
    }
  }

  private static String RandomLabel(int count) {
    StringBuilder builder = new StringBuilder();
    while (count-- != 0) {
      int character = (int) (Math.random() * RANDOM_LABEL_CHAR_SET.length());
      builder.append(RANDOM_LABEL_CHAR_SET.charAt(character));
    }
    return builder.toString();
  }

  private void addToPrintList(String command) {
    printList.add(command);
  }

  public List getPrintList() {
    return printList;
  }

  public boolean isPrintVisited() {
    return printVisited;
  }

  public void setPrintVisited(boolean printVisited) {
    this.printVisited = printVisited;
  }

}
