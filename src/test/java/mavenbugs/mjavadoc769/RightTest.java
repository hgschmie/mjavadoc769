package mavenbugs.mjavadoc769;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class RightTest {

  @Test
  public void testRight() throws Exception {
    var method = Foo.class.getMethod("foo");
    var annotation = method.getAnnotation(Right.class);
    assertNotNull(annotation);

  }

  public static class Foo {
    @Right
    public void foo() {
    }
  }
}
