package com.example.library.internal;

import javax.script.ScriptEngineManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InternalTests {
  @Test
  void useInternal() {
    Assertions.assertNotNull(new Internal().toString());
  }
  @Test
  void runningInNamedModule() {
    Assertions.assertTrue(InternalTests.class.getModule().isNamed());
  }
  @Test
  void useJavaScripting() {
    Assertions.assertNotNull(new ScriptEngineManager().getEngineFactories());
  }
}
