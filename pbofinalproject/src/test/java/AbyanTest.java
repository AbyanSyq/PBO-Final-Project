import org.junit.Test;

import com.abyan.Manager.Element;
import com.abyan.Object.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class AbyanTest {
    @Test
    public void abyanBenar() {
        Monster monster = new Tanah("yoga", 1, 0, Element.TANAH);
        monster.setElement(Element.AIR);
        //assertEquals(1, monster.getElement().value);
        
    }
}

