package features.jep484_class_file_api;

import java.io.PrintStream;
import java.lang.classfile.ClassElement;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.MethodModel;
import java.lang.constant.ClassDesc;
import java.lang.constant.ConstantDescs;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClassFileAPI {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

  void main() throws Exception {
    Path classFilePath = Path.of("target/classes/features/jep484_class_file_api/Employee.class");

    ClassFile cf = ClassFile.of();
    ClassModel classModel = cf.parse(classFilePath);

    byte[] modifiedBytes = cf.build(classModel.thisClass().asSymbol(), classBuilder -> {
      for (ClassElement ce : classModel) {
        if (ce instanceof MethodModel mm && mm.code().isPresent()) {

          classBuilder.withMethod(mm.methodName(), mm.methodType(), mm.flags().flagsMask(), methodBuilder -> {

            methodBuilder.withCode(codeBuilder -> {
              codeBuilder.getstatic(ClassDesc.of(System.class.getName()), "out", ClassDesc.of(PrintStream.class.getName()));

              codeBuilder.ldc(
                  "\u001B[36m[DEBUG]\u001B[0m [\u001B[33m"
                  + DATE_TIME_FORMATTER.format(LocalDateTime.now())
                  + "\u001B[0m] Method: \u001B[32m"
                  + mm.methodName().stringValue() + "\u001B[0m"
              );
              codeBuilder.invokevirtual(ClassDesc.of(PrintStream.class.getName()), "println", MethodTypeDesc.of(ConstantDescs.CD_void, ClassDesc.of(String.class.getName())));

              mm.code().get().forEach(codeBuilder::with);
            });

          });
        } else {
          classBuilder.with(ce);
        }
      }
    });

    Files.write(Path.of("target/classes/features/jep484_class_file_api/Employee.class"), modifiedBytes);
    System.out.println("Class modified with simple logging!");
  }
}
