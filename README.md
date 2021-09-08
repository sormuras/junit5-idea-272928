# junit5-idea-272928
Repro repo for https://youtrack.jetbrains.com/issue/IDEA-272928

## Fixed

![image](https://user-images.githubusercontent.com/2319838/124249966-acd6dc80-db24-11eb-9ed0-308936c3a86b.png)

## Open

See https://youtrack.jetbrains.com/issue/IDEA-272928#focus=Comments-27-5177725.0-0 for details.

![image](https://user-images.githubusercontent.com/2319838/132439608-3750092b-59a8-4abf-ae06-7faa9e6f87f5.png)

A manual work-around is to add the following command-line arguments to the VM Options of the `All in com.example.library` run configuration:

```text
--add-modules
java.scripting
--add-reads
com.example.library=java.scripting
```

But these extra directives are already declared here: [com.example.library/test/java-module/module-info.java](https://github.com/sormuras/junit5-idea-272928/blob/main/com.example.library/test/java-module/module-info.java#L4)

```java
open /*test*/ module com.example.library {
  exports com.example.library;

  requires java.scripting;
  requires org.junit.jupiter;
}
```

It would be neat to also support this variant using Java's default "DSL" for module declarations.
