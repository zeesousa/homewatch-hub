package base;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.xml.sax.SAXException;
import server.Main;

import java.io.IOException;

@Ignore
public class ControllerTest {
  private static boolean STARTED = false;

  @BeforeClass
  public static void setUpBaseClass() {
    try {
      if (!STARTED) {
        Main.main(new String[1]);
        STARTED = true;
      }
    } catch (IOException | SAXException e) {
      e.printStackTrace();
    }
  }
}
