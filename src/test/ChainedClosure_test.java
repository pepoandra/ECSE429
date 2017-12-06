package test;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.functors.ChainedClosure;
import org.apache.commons.collections4.functors.NOPClosure;

public class ChainedClosure_test {
	private Closure<Object> c;
	
	static class MockClosure<T> implements Closure<T> {
        int count = 0;

        @Override
        public void execute(final T object) {
            count++;
        }

        public void reset() {
            count = 0;
        }
    }
	
	@Test
	public void test_constructor()
	{
		MockClosure a = new MockClosure();
		c = new ChainedClosure<Object>(new Closure[] {a});
		
		assertThat(c).as("Constructor Length > 0").isInstanceOf(ChainedClosure.class);
	}
	
	@Test
	public void test_chainedClosureZeroLength()
	{
		c = ChainedClosure.<Object>chainedClosure(new Closure[0]);
		assertThat(c).as("ChainedClosure Length 0").isSameAs(NOPClosure.INSTANCE);
	}
	
	@Test
	public void test_chainedClosureLengthBiggerZero()
	{
		MockClosure a = new MockClosure();
		MockClosure b = new MockClosure();
		c = ChainedClosure.chainedClosure(new Closure[] {a, b});
		
		assertThat(c).as("ChainedClosure Length > 0").isInstanceOf(ChainedClosure.class);
	}
	
	@Test
	public void test_chainedClosureCollectionsClosuresNull()
	{
		try
		{
			c = ChainedClosure.chainedClosure((Collection<Closure<Object>>) null);
		}
		catch(NullPointerException ex)
		{
			assertThat(ex.getMessage()).as("Null closures exception message").isEqualTo("Closure collection must not be null");
		}
	}
	
	@Test
	public void test_chainedClosureCollectionsSizeZero()
	{
		c = ChainedClosure.chainedClosure(Collections.<Closure<Object>>emptyList());
		assertThat(c).as("Collection size zero").isSameAs(NOPClosure.INSTANCE);
	}
	
	@Test
	public void test_chainedClosureCollectionsSizeBiggerZero()
	{
		MockClosure a = new MockClosure<>();
		Collection<Closure<Object>> coll = new ArrayList<>();
		coll.add(a);
		c = ChainedClosure.chainedClosure(coll);
		assertThat(c).as("ChainedClosure w/ Collections Size > 0").isInstanceOf(ChainedClosure.class);
	}
	
	@Test
	public void test_execute() {
		MockClosure a = new MockClosure();
		MockClosure b = new MockClosure();
		c = new ChainedClosure(a,b,a);
		
		c.execute(null);
		
		assertThat(a.count).as("MockClosure a count").isEqualTo(2);
		assertThat(b.count).as("MockClosure b count").isEqualTo(1);
	}
	
	
	//TODO: Figure out why getClosures() DNE
//	@Test
//	public void test_getClosures()
//	{
//		MockClosure a = new MockClosure();
//		c = ChainedClosure.chainedClosure(new Closure[] {a});
//		
//		assertThat().as("Get closures").isEqualTo(a);
//	}

}
