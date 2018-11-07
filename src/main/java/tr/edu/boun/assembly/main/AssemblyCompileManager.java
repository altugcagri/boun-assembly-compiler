package tr.edu.boun.assembly.main;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.edu.boun.gen.anltr.ExprGrammarFileLexer;
import tr.edu.boun.gen.anltr.ExprGrammarFileParser;
import tr.edu.boun.gen.anltr.ExprGrammarFileParser.BeginendContext;

/**
 * This Class is The Manager Class
 * of The Assembly Compiler Program.
 * It Creates Generator class for the compiling and
 * It Generates the output file
 *
 * @author Cagri Altug and Serhat Uzuncavdar
 */
public class AssemblyCompileManager {

  private final static Logger LOGGER = LoggerFactory.getLogger(AssemblyCompileManager.class);

  private final String inputFilePath;
  private final String outputFilePath;

  public AssemblyCompileManager(String inputFilePath, String outputFilePath) {
    this.inputFilePath = inputFilePath;
    this.outputFilePath = outputFilePath;
  }

  /**
   * This Method is creating proper generator and parser instances.
   * It calls writer method for creating output file.
   *
   * @author Cagri Altug and Serhat Uzuncavdar
   */
  public void compileCommands() throws Exception {
    LOGGER.info("Compiler Manager is trying to compile input {}", inputFilePath);
    try (FileInputStream fis = new FileInputStream(inputFilePath)) {
      final ANTLRInputStream input = new ANTLRInputStream(fis);
      final ExprGrammarFileLexer lexer = new ExprGrammarFileLexer(input);
      final CommonTokenStream tokens = new CommonTokenStream(lexer);
      final ExprGrammarFileParser parser = new ExprGrammarFileParser(tokens);
      final ParseTree tree = parser.stm();
      if (((BeginendContext) tree).exception != null) {
        throw new Exception(((BeginendContext) tree).exception);
      }
      LOGGER.info("Compile Tree is: {}", tree.toStringTree(parser));
      writeAssembly(tree);
    } catch (Exception e) {
      LOGGER.error("Error Occurred: {}", e.getMessage());
      throw new Exception(e);
    }
  }

  /**
   * This Method is creating the output file.
   *
   * @param tree parameter is used for creating Command generator.
   * @author Cagri Altug and Serhat Uzuncavdar
   */
  private void writeAssembly(ParseTree tree) throws Exception {

    try (FileOutputStream file = new FileOutputStream(outputFilePath); OutputStreamWriter fileWriter = new OutputStreamWriter(
        file); BufferedWriter writer = new BufferedWriter(fileWriter)) {
      LOGGER.info("Compiler Manager is trying to write assembly code to output file: {} ", outputFilePath);
      writer.write("code segment");
      writer.newLine();
      final CommandGenerator commandGenerator = new CommandGenerator();
      commandGenerator.visit(tree);
      commandGenerator.PrintExit();
      if (commandGenerator.isPrintVisited()) {
        commandGenerator.CallPrint();
      }
      commandGenerator.PrintData();
      final List<String> commandList = commandGenerator.getPrintList();
      for (String command : commandList) {
        writer.write(command);
        writer.newLine();
      }
      LOGGER.info("Compiler Manager has completed to write assembly code to output file: {} ", outputFilePath);
    }

  }

}
