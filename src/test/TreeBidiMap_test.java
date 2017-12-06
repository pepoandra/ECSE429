package test;

// testing framework
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.assertj.core.api.Assertions.*;

// tested class
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

// dependencies of tested class
import java.util.Set;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class TreeBidiMap_test 
{
	private TreeBidiMap<String, String> m;
	
	@Before
	public void setUp(){ m = new TreeBidiMap<String, String>(); }

	@After
	public void tearDown() { m.clear(); }

	@Test
	public void test_clear() 
	{
		m.put("foo", "bar");
		m.clear();
		
		assertThat(m.size()).as("size after clear()").isEqualTo(0);
	}

	@Test
	public void test_containsKey()
	{
		m.put("foo", "bar");
		
		assertThat(m.containsKey("foo")).as("containsKey()").isEqualTo(true);
	}
	
	@Test
	public void test_containsValue()
	{
		m.put("foo", "bar");
		
		assertThat(m.containsValue("toto")).as("containValue()").isEqualTo(false);
	}
	
	@Test
	public void test_entrySet_null()
	{
		Set<Entry<String, String>> s = m.entrySet();
		
		assertThat(m.entrySet()).as("entrySet()").isEmpty();
		assertThat(m.entrySet()).as("entrySet()").isInstanceOf(Set.class);
	}
	
	@Test
	public void test_entrySet_notnull()
	{
		m.put("foo", "bar");
		Set<Entry<String, String>> s = m.entrySet();
		
		assertThat(m.entrySet()).as("is empty entrySet()").isNotEmpty();
		assertThat(m.entrySet()).as("Class of entrySet()").isInstanceOf(Set.class);
		assertThat(s.size()).as("size of entrySet()").isEqualTo(1);		
	}
	
	@Test
	public void test_equals()
	{
		BidiMap<String, String> n = new TreeBidiMap<String, String>();
		
		assertThat(m.equals(n)).as("m.equals(n)").isEqualTo(true);
	}
	
	@Test
	public void test_firstKey_exception()
	{
		try { m.firstKey(); }
		catch (NoSuchElementException e)
		{
			assertThat(e).as("Exception message").hasMessage("Map is empty");
		}
	}
	
	@Test
	public void test_firstKey_valid()
	{
		m.put("foo", "bar");
		
		assertThat(m.firstKey()).as("firstKey() of non-empty obj")
								.isEqualTo("foo");
	}
	
	@Test
	public void test_get()
	{
		m.put("foo", "bar");
		
		assertThat(m.get("foo")).as("get(\"foo\")").isEqualTo("bar");
	}
	
	@Test
	public void test_getKey()
	{
		m.put("foo", "bar");
		
		assertThat(m.getKey("bar")).as("getKey(\"bar\")").isEqualTo("foo");
	}
}
