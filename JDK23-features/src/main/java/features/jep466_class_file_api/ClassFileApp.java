
void main() throws IOException {
  Path classFilePath = Path.of("src/main/java/features/jep466_class_file_api/TestClass.class");
  byte[] classBytes = Files.readAllBytes(classFilePath);

  ClassFile cf = ClassFile.of();
  ClassModel classModel = cf.parse(classBytes);

  long publicMethodCount = classModel.methods().stream()
      .filter(method -> method.flags().has(AccessFlag.PUBLIC))
      .count();

  println("Number of public methods in class " + classModel.thisClass().name() + ": " + publicMethodCount);
}
