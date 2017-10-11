// test framework imports
import org.junit.Test;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.After;
import org.junit.Before;
import static org.assertj.core.api.Assertions.*;

// tested class imports
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.collections4.set.UnmodifiableSet.unmodifiableSet;;

public class UnmodifiableSetTest 
{
	private Set<Integer> a, b;
	
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
		a = new HashSet<Integer>();
		
		b = unmodifiableSet(a);	
		
		assertThat(b).as("b is unmodifiable").isExactlyInstanceOf(UnmodifiableSet.class);
		assertThat(b).as("b is decorator of a").isEmpty();
		assertThat(b.size()).as("a.size() == b.size()").isEqualTo(a.size());
	}
	
	@Test
	public void test_create_not_empty_set()
	{
		a = new HashSet<Integer>();
		a.addAll(Arrays.asList(1,2,3,4,5));
		
		b = unmodifiableSet(a);	
		
		assertThat(b).as("b is unmodifiable").isExactlyInstanceOf(UnmodifiableSet.class);
		assertThat(b).as("b is decorator of a").isNotEmpty();
		assertThat(b.size()).as("a.size() == b.size()").isEqualTo(a.size());
		
	}
		
	@Test
	public void test_create_NullPointerException()
	{
		try 
		{
			b = unmodifiableSet(a);	
		}
		catch (NullPointerException e)
		{
			assertThat(e.getMessage()).as("Exception message")
								   .isEqualTo("Collection must not be null.");
		}
		
	}
	
	/*
	 * read
	 */
	@Test
	public void test_read_empty_set()
	{
		a = new HashSet<Integer>();
		b = unmodifiableSet(a);	
		
		assertThat(b).as("b as decorator of a").isEmpty();
		assertThat(b.size()).as("a.size() == b.size()").isEqualTo(a.size());
	}
	
	@Test
	public void test_read_not_empty_set()
	{
		a = new HashSet<Integer>();
		a.addAll(Arrays.asList(1,2,3,4,5));
		
		b = unmodifiableSet(a);	
		
		assertThat(b).as("b as decorator of a").isNotEmpty();
		assertThat(b.size()).as("a.size() == b.size()").isEqualTo(a.size());
		assertThat(b.containsAll(Arrays.asList(1,2,3,4,5))).as("read contents").isTrue();
	}
	
	/*
	 * update
	 */
	@Test
	public void test_update_empty_set_UnsupportedOperationException()
	{
		a = new HashSet<Integer>();
		b = unmodifiableSet(a);	
		
		try
		{
			b.add(1);
		}
		catch (UnsupportedOperationException e)
		{
			// exception caught, but default exception doesn't have msg
			assertThat(e.getMessage()).as("Exception message").isNull();	
		}
		
	}
	
	@Test
	public void test_update_not_empty_set_UnsupportedOperationException()
	{
		a = new HashSet<Integer>();
		a.addAll(Arrays.asList(1,2,3,4,5));
		
		b = unmodifiableSet(a);	
		
		try
		{
			b.add(1);
		}
		catch (UnsupportedOperationException e)
		{
			// exception caught, but default exception doesn't have msg
			assertThat(e.getMessage()).as("Exception message").isNull();
		}
	}
	
	/*
	 * delete
	 */
	@Test
	public void test_udelete_empty_set_UnsupportedOperationException()
	{
		a = new HashSet<Integer>();
		b = unmodifiableSet(a);	
		
		try
		{
			b.clear();
		}
		catch (UnsupportedOperationException e)
		{
			// exception caught, but default exception doesn't have msg
			assertThat(e.getMessage()).as("Exception message").isNull();	
		}
		
	}
	
	@Test
	public void test_delete_empty_set_UnsupportedOperationException()
	{
		a = new HashSet<Integer>();
		a.addAll(Arrays.asList(1,2,3,4,5));
		
		b = unmodifiableSet(a);	
		
		try
		{
			b.remove(2);
		}
		catch (UnsupportedOperationException e)
		{
			// exception caught, but default exception doesn't have msg
			assertThat(e.getMessage()).as("Exception message").isNull();
		}
	}
}
