// test framework imports
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.assertj.core.api.Assertions.*;

// tested class imports
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import static org.apache.commons.collections4.SetUtils.*;

public class SetUtilsTest 
{
	/* Set is an interface, in order to test SetUtils
	 * we must test its methods on a class implementing Set
	 * i.e. HashSet, LinkedHashSet or TreeSet
	 * We chose HashSet as it has LinkedHashSet as subclass
	 * and because TreeSet implements SortedSet and NavigableSet, 
	 * which are already tested in this test suite
	 * 
	 * No CRUD functionalities are tested in this class because 
	 * it only provides operations for already instantiaded sets.
	 * CRUD functionality for different types of sets is tested in the 
	 * other test classes in this test suite.
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
		a = b = c = null;
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
	 * isEqualSet
	 */
	@Test
	public void test_isEqualSet_null_sets() 
	{
		assert_clean_objects();

		b = null;
		
		assertThat(isEqualSet(a,b)).as("a != b").isFalse();
	}
	
	@Test
	public void test_isEqualSet_empty_sets() 
	{
		assert_clean_objects();
		
		assertThat(isEqualSet(a,b)).as("a == b").isTrue();
	}
	
	@Test
	public void test_isEqualSet_equal_sets() 
	{
		assert_clean_objects();

		a.addAll(Arrays.asList(1,2,3,4,5)); 
		b.addAll(Arrays.asList(1,2,3,4,5)); 
		
		assertThat(isEqualSet(a,b)).as("a == b").isTrue();
	}

	@Test
	public void test_isEqualSet_not_equal_sets() 
	{
		assert_clean_objects();

		a.addAll(Arrays.asList(1,2,3,4,5)); 
		b.addAll(Arrays.asList(1));
		
		assertThat(isEqualSet(a,b)).as("b != a").isFalse();
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
		
		a.add(1);
		
		c = union(a,b);
		
		assertThat(c).as("c = a || b").isNotEmpty();
		assertThat(c.size()).as("c.size()").isEqualTo(1);
		assertThat(c).as("c contains 1").contains(1);
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
			c = union(a,b);
		}
		catch (NullPointerException e)
		{
			assertThat(e.getMessage()).as("Exception message")
									  .contains("Sets must not be null.");
		}
	}
	
	/*
	 * difference
	 */
	@Test
	public void test_difference_empty_sets() 
	{
		assert_clean_objects();
		
		c = difference(a,b);
		
		assertThat(c).as("c = a \\ b").isEmpty();
	}
	
	@Test
	public void test_difference_first_empty_set() 
	{
		assert_clean_objects();
		
		a.add(1);
		
		c = difference(a,b);
		
		assertThat(c).as("c = a \\ b").isNotEmpty();
		assertThat(c.size()).as("c.size()").isEqualTo(1);
		assertThat(c).as("c contains 1").contains(1);
	}
	
	@Test
	public void test_difference_second_empty_set() 
	{
		assert_clean_objects();
		
		b.add(1);
		
		c = difference(a,b);
		
		assertThat(c).as("c = a \\ b").isEmpty();
	}
	
	@Test
	public void test_difference_a_includes_b() 
	{
		assert_clean_objects();
		
		a.addAll(Arrays.asList(1,2,3,4,5)); 
		b.addAll(Arrays.asList(2,3));
		a.addAll(b);

		c = difference(a,b);
		
		assertThat(c).as("c = a \\ b").isNotEmpty();
		assertThat(c.size()).as("c.size()").isEqualTo(3);
		assertThat(c).as("c contains 1,4,5").containsAll(Arrays.asList(1,4,5));
	}
	
	@Test
	public void test_difference_b_includes_a() 
	{
		assert_clean_objects();
		
		a.addAll(Arrays.asList(2,3));
		b.addAll(Arrays.asList(1,2,3,4,5)); 
		b.addAll(a);

		c = difference(a,b);
		
		assertThat(c).as("c = a \\ b").isEmpty();
	}
	
	@Test
	public void test_difference_a_not_includes_b() 
	{
		assert_clean_objects();
		
		a.addAll(Arrays.asList(1,2,3,4,5)); 
		b.addAll(Arrays.asList(6,7));

		c = difference(a,b);
		
		assertThat(c).as("c = a \\ b").isNotEmpty();
		assertThat(c.size()).as("c.size()").isEqualTo(5);
		assertThat(c).as("c contains [1-5]").containsAll(Arrays.asList(1,2,3,4,5));
	}
	
	@Test
	public void test_difference_NullPointerException()
	{
		assert_clean_objects();
		
		a = null;
		
		try
		{
			c = difference(a,b);
		}
		catch (NullPointerException e)
		{
			assertThat(e.getMessage()).as("Exception message")
									  .contains("Sets must not be null.");
		}
	}
	
	/*
	 * disjunction
	 */
	@Test
	public void test_disjunction_empty_sets() 
	{
		assert_clean_objects();
		
		c = disjunction(a,b);
		
		assertThat(c).as("c = disjunction(a,b)").isEmpty();
	}
	
	@Test
	public void test_disjunction_one_empty_set() 
	{
		assert_clean_objects();
		
		a.add(1);
		
		c = disjunction(a,b);
		
		assertThat(c).as("c = disjunction(a,b)").isNotEmpty();
		assertThat(c.size()).as("c.size()").isEqualTo(1);
		assertThat(c).as("c contains 1").contains(1);
	}
	
	// this test inheritantly tests the case where both a and b are not empty
	@Test
	public void test_disjunction_a_includes_b() 
	{
		assert_clean_objects();
		
		a.addAll(Arrays.asList(1,2,3,4,5)); 
		b.addAll(Arrays.asList(2,3));
		a.addAll(b);

		c = disjunction(a,b);
		
		assertThat(c).as("c = disjunction(a,b)").isNotEmpty();
		assertThat(c.size()).as("c.size()").isEqualTo(3);
		assertThat(c).as("c contains 1,4,5").containsAll(Arrays.asList(1,4,5));
	}
	
	@Test
	public void test_disjunction_NullPointerException()
	{
		assert_clean_objects();
		
		a = null;
		
		try
		{
			c = disjunction(a,b);
		}
		catch (NullPointerException e)
		{
			assertThat(e.getMessage()).as("Exception message")
									  .contains("Sets must not be null.");
		}
	}
}
