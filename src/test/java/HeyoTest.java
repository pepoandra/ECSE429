import static org.junit.Assert.*;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class HeyoTest 
{
    @Test 
    public void testHeyo() 
    {
        Heyo h = new Heyo();
        assertThat(h.talk()).as("Checking if class can talk the talk")
        					.isTrue();
        
        assertTrue(h.talk());
    }
}
