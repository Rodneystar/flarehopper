package api;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import web.ReactHandler;

public class ServerTests {

    @Mock
    ReactHandler reactHandler;


    @Test
    public void testReactHandler() {
                Mockito.verify(reactHandler.start());

    }
}
