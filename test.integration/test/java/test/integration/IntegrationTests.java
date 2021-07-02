package test.integration;

import com.example.library.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.base.Basics;

class IntegrationTests {
  @Test
  void useLibrary() {
    Assertions.assertNotNull(new Library().toString());
  }
  @Test
  void useBasics() {
    Assertions.assertNotNull(new Basics().toString());
  }
  @Test
  void runningInNamedModule() {
    Assertions.assertTrue(IntegrationTests.class.getModule().isNamed());
  }
}
