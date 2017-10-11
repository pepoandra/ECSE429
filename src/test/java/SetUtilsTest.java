// test framweork imports
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.assertj.core.api.Assertions.*;

// tested class imports
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import static org.apache.commons.collections4.SetUtils.*;


// TODO TEST CRUD
//		TEST EXCEPTIONS


public class SetUtilsTest 
{
	/* Set is an interface, in order to test SetUtils
	 * we must test its methods on a class implementing Set
	 * i.e. HashSet, LinkedHashSet or TreeSet
	 * We chose HashSet as it has LinkedHashSet as subclass
	 * and because TreeSet implements SortedSet and NavigableSet, 
	 * which are already tested in this test suite
	 */
	private Set<Integer> a, b, c;

	@Before
	public void init_set_objects()
	{
		a = new HashSet<Integer>();
		b = new HashSet<Integer>();
		c = new HashSet<Integer>();
	}
	
	@After
	public void destroy_set_objects()
	{
		a = b = null;
	}
	
	// repetitive checks at the beginning of each test case
	private void assert_clean_objects() {
		assertThat(a).as("isEmpty(a)").isEmpty();
		assertThat(b).as("isEmpty(b)").isEmpty();
		assertThat(c).as("isEmpty(c)").isEmpty();
	}
	
	/*
	 * emptyIfNull
	 */
	@Test
	public void test_emptyIfNull_non_null() 
	{
		assert_clean_objects();

		b = emptyIfNull(a);
		
		assertThat(b).as("b == a").isSameAs(a);
	}

	@Test
	public void test_emptyIfNull_null() 
	{
		assert_clean_objects();

		b = emptyIfNull(null);
		
		assertThat(b).as("b != a").isNotSameAs(a);
		assertThat(b).as("isEmpty(b)").isEmpty();
	}
	
	/*
	 * intersection
	 */
	@Test
	public void test_intersection_empty_sets() 
	{
		assert_clean_objects();
		
		c = intersection(a,b);
		
		assertThat(c).as("c = a && b").isEmpty();
	}
	
	@Test
	public void test_intersection_one_empty_set() 
	{
		assert_clean_objects();
		
		a.add(1);
		
		c = intersection(a,b);
		
		assertThat(c).as("c = a && b").isEmpty();
	}
	
	@Test
	public void test_intersection_empty_intersection() 
	{
		assert_clean_objects();
		
		a.add(1);
		b.add(2);

		c = intersection(a,b);
		
		assertThat(c).as("c = a && b").isEmpty();
	}

	// this test already tests inheritantly for a non empty intersection
	@Test
	public void test_intersection_a_includes_b() 
	{
		assert_clean_objects();
		
		a.addAll(Arrays.asList(1,2,3,4,5)); 
		b.addAll(Arrays.asList(2,3));
		a.addAll(b);

		c = intersection(a,b);
		
		assertThat(c).as("c = a && b").isNotEmpty();
		assertThat(c.size()).as("c.size()").isEqualTo(2);
		assertThat(c).as("c contains 2,3").containsAll(Arrays.asList(2,3));
	}
	
	@Test
	public void test_intersection_NullPointerException()
	{
		assert_clean_objects();
		
		a = null;
		
		try
		{
			c = intersection(a,b);
		}
		catch (NullPointerException e)
		{
			assertThat(e.getMessage()).as("Exception message")
									  .contains("Sets must not be null.");
		}
	}
	
	/*
	 * union
	 */
	@Test
	public void test_union_empty_sets() 
	{
		assert_clean_objects();
		
		c = union(a,b);
		
		assertThat(c).as("c = a || b").isEmpty();
	}
	
	@Test
	public void test_union_one_empty_set() 
	{
		assert_clean_objects();
		
		c = union(a,b);
		
		assertThat(c).as("c = a || b").isEmpty();
	}
	
	@Test
	public void test_union_a_includes_b() 
	{
		assert_clean_objects();
		
		a.addAll(Arrays.asList(1,2,3,4,5)); 
		b.addAll(Arrays.asList(2,3));
		a.addAll(b);

		c = union(a,b);
		
		assertThat(c).as("c = a || b").isNotEmpty();
		assertThat(c.size()).as("c.size()").isEqualTo(5);
		assertThat(c).as("c contains [1-5]").containsAll(Arrays.asList(1,2,3,4,5));
	}
	
	@Test
	public void test_union_a_not_includes_b() 
	{
		assert_clean_objects();
		
		a.addAll(Arrays.asList(1,2,3,4,5)); 
		b.addAll(Arrays.asList(6,7));

		c = union(a,b);
		
		assertThat(c).as("c = a || b").isNotEmpty();
		assertThat(c.size()).as("c.size()").isEqualTo(7);
		assertThat(c).as("c contains [1-7]").containsAll(Arrays.asList(1,2,3,4,5,6,7));
	}
	
	@Test
	public void test_union_NullPointerException()
	{
		assert_clean_objects();
		
		a = null;
		
		try
		{
			c = intersection(a,b);
		}
		catch (NullPointerException e)
		{
			assertThat(e.getMessage()).as("Exception message")
									  .contains("Sets must not be null.");
		}
	}
}
