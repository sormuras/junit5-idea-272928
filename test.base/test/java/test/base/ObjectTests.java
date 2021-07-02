package test.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjectTests {
  @Test
  void toStringIsNotNull() {
    Assertions.assertNotNull(new Object().toString());
  }
  @Test
  void runningInNamedModule() {
    Assertions.assertTrue(ObjectTests.class.getModule().isNamed());
  }
}
