import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.spi.ToolProvider;

public class Build {
  public static void main(String[] args) {
    var mainClasses = Path.of("built/main/classes");
    run(
        "javac",
        "--module",
        "com.example.library",
        "--module-source-path",
        "./*/main/java",
        "-d",
        mainClasses.toString());

    var testClasses = Path.of("built/test/classes");
    run(
        "javac",
        "--module",
        "test.base,test.integration,com.example.library",
        "--module-source-path",
        "./*/test/java",
        "--patch-module",
        "com.example.library=com.example.library/main/java",
        "--module-path",
        mainClasses + File.pathSeparator + ".idea/lib",
        "-d",
        testClasses.toString());

    run(
        "java",
        "--module-path",
        testClasses + File.pathSeparator + mainClasses + File.pathSeparator + ".idea/lib",
        "--add-modules",
        "test.base",
        "--module",
        "org.junit.platform.console",
        "--select-module",
        "test.base");

    run(
        "java",
        "--module-path",
        testClasses + File.pathSeparator + mainClasses + File.pathSeparator + ".idea/lib",
        "--add-modules",
        "test.integration",
        "--module",
        "org.junit.platform.console",
        "--select-module",
        "test.integration");

    run(
        "java",
        "--module-path",
        testClasses + File.pathSeparator + mainClasses + File.pathSeparator + ".idea/lib",
        "--add-modules",
        "com.example.library",
        "--module",
        "org.junit.platform.console",
        "--select-module",
        "com.example.library");
  }

  static void run(String name, String... args) {
    System.out.println(">> " + name + " " + String.join(" ", args));
    var tool = ToolProvider.findFirst(name);
    if (tool.isPresent()) {
      var code = tool.get().run(System.out, System.err, args);
      if (code != 0) throw new Error("Non-zero exit code: " + code);
    }
    var command = new ArrayList<String>();
    command.add(name);
    command.addAll(List.of(args));
    try {
      var code = new ProcessBuilder(command).inheritIO().start().waitFor();
      if (code != 0) throw new Error("Non-zero exit code: " + code);
    } catch (Exception exception) {
      throw new Error(exception);
    }
  }
}
