// test framework imports
import org.junit.Test;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.set.PredicatedSet;
import org.junit.After;
import static org.assertj.core.api.Assertions.*;

// tested class imports
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.apache.commons.collections4.set.PredicatedSet.predicatedSet;

public class PredicatedSet_test 
{ 
	// Dummy Predicate
    Predicate<Object> predicate = new Predicate<Object>() { 
            public boolean evaluate(Object o)
            	{ 
                	return o instanceof Integer; 
                }
    		};
            
	private Set<Object> a, b;
	
	@After
	public void destroy_set_objects()
	{
		a = b = null;
	}
	
	/*
	 * create
	 */
	@Test
	public void test_create_empty_set()
	{
		a = predicatedSet(new HashSet<Object>(), predicate);
		
		assertThat(a).as("a is of class PredicatedSet").isInstanceOf(PredicatedSet.class);
		
	}
	
	@Test
	public void test_create_not_empty_set()
	{
		a = new HashSet<Object>();
		a.add(1);
		
		b = predicatedSet(a, predicate);
		
		assertThat(b).as("a is of class PredicatedSet").isInstanceOf(PredicatedSet.class);
		assertThat(b.size()).as("a == b").isEqualTo(a.size());
		assertThat(b).as("b contains 1").contains(1);
	}
	
	@Test
	public void test_create__null_set_NullPointerException()
	{
		try
		{
			b = predicatedSet(null, predicate);
		}
		catch (NullPointerException  e)
		{
			assertThat(e.getMessage()).as("Exception message")
									  .isEqualTo("Collection must not be null.");
		}
	}

	@Test
	public void test_create__null_predicate_NullPointerException()
	{
		try
		{
			b = predicatedSet(a, null);
		}
		catch (NullPointerException  e)
		{
			assertThat(e.getMessage()).as("Exception message")
									  .isEqualTo("Collection must not be null.");
		}
	}
	
	@Test
	public void test_create_IllegalArgumentException()
	{
		a = new HashSet<Object>();
		a.add("hello");
		
		try
		{
			b = predicatedSet(a, predicate);
		}
		catch (IllegalArgumentException  e)
		{
			assertThat(e.getMessage()).as("Exception message")
									  .contains("rejected it");
		}
	}
	
	/*
	 * read functionalities don't differ from the implemented Set interface
	 * as such it is redundant to test them here. It is important to note
	 * that the Set interface is not part of the Apache Commons Collections.
	 */
	
	/*
	 * update
	 */
	@Test
	public void test_update_IllegalArgumentException()
	{
		a = predicatedSet(new HashSet<Object>(), predicate);
		
		try
		{
			a.add("hello");
		}
		catch (IllegalArgumentException  e)
		{
			assertThat(e.getMessage()).as("Exception message")
									  .contains("rejected it");
		}
	}
	
	@Test
	public void test_update_valid_element()
	{
		a = new HashSet<Object>();
		b = predicatedSet(a, predicate);
		b.addAll(Arrays.asList(1,2,3,4,5));
		
		assertThat(b).as("b as decorator of a").isNotEmpty();
		assertThat(b.size()).as("b.size()").isEqualTo(5);
		assertThat(b.containsAll(Arrays.asList(1,2,3,4,5))).as("read contents").isTrue();
	}
	
	/*
	 * delete functionalities don't differ from the implemented Set interface
	 * as such it is redundant to test them here. It is important to note
	 * that the Set interface is not part of the Apache Commons Collections.
	 */
}
