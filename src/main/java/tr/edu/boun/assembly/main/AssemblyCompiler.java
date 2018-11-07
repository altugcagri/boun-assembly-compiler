package tr.edu.boun.assembly.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Class is The Main Class
 * of The Assembly Compiler Program.
 * It Includes Main Method in Here
 *
 * @author Cagri Altug and Serhat Uzuncavdar
 */
public class AssemblyCompiler {

  private final static Logger LOGGER = LoggerFactory.getLogger(AssemblyCompiler.class);

  /**
   * This Method is booting The Assembly Compiler
   *
   * @param args includes input file path and output file path respectively
   * @example args[0] = "D:\\test\\simple.co" and args[1] = "D:\\test\\output.co"
   * @author Cagri Altug and Serhat Uzuncavdar
   */
  public static void main(String[] args) throws Exception {
    String inputFilePath = "";
    String outputFilePath = "";

    try {
      inputFilePath = args[0];
      outputFilePath = args[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      LOGGER.error("Input Arguments are missing input path : {}  and output path: {}", inputFilePath, outputFilePath);
      LOGGER.info("Example usage: Java -jar jar\\path input\\file\\path output\\file\\path");
      throw new Exception(e);
    }

    try {
      LOGGER.info("Compiler is starting with input {}", inputFilePath);
      final AssemblyCompileManager compileManager = new AssemblyCompileManager(inputFilePath, outputFilePath);
      compileManager.compileCommands();
      LOGGER.info("Output file {}", outputFilePath);
    } catch (Exception e) {
      LOGGER.error("Error Occurred during compiling input file {} with details: ", inputFilePath, e.getMessage());
    }
  }
}