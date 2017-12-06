package test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.NoSuchElementException;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TreeBidiMapTestSuite1 {
	
	private TreeBidiMap<Integer,String> testTBM;
	private TreeBidiMap<String,Integer> inverseTestTBM;
	
	/* Used to reset the testTBM object for test where it 
	 * needs to be TreeBidiMap properties need to be null 
	 * or empty
	 */
	private void reset(){
		testTBM = new TreeBidiMap<Integer,String>(); // reset object 
	}

	@Before
	public void setUp() throws Exception {
		testTBM = new TreeBidiMap<Integer,String>();
		inverseTestTBM = new TreeBidiMap<String, Integer>();
		for (int i = 1; i < 7; i++)
		{
			testTBM.put(i, "Key " + i);
			inverseTestTBM.put("Key " + i,i);
		}
	}

	@After
	public void tearDown() throws Exception {
		testTBM = null;
		inverseTestTBM = null;
	}

	@Test
	public void hashCodetest() {
		assertThat(testTBM.hashCode()).isNotNull();
	}
	
	@Test 
	public void inverseBidiMapTestNull(){
		reset();
		assertThat(testTBM.inverseBidiMap()).isNotNull().isEmpty();
	}
	
	@Test 
	public void inverseBidiMapTestNotNull(){
		assertThat(testTBM).isEqualTo(inverseTestTBM.inverseBidiMap());
	}
	
	@Test
	public void isEmptyTestNull(){
		reset();
		assertThat(testTBM.isEmpty()).isTrue();
	}
	
	@Test
	public void isEmptyTestNotNull(){
		assertThat(testTBM.isEmpty()).isFalse();
	}
	
	@Test
	public void keySetTestNull(){
		reset();
		assertThat(testTBM.keySet()).isNotNull().isEmpty();
	}

	@Test
	public void keySetTestNotNull(){
		int [] keys = {1,2,3,4,5,6};
		assertThat(testTBM.keySet().toArray()).isEqualTo(keys);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void lastKeyTestNodeCountZero(){
		testTBM = new TreeBidiMap<Integer,String>();
		testTBM.lastKey();
	}
	
	@Test 
	public void lastKeyTestWithNodes(){
		assertThat(testTBM.lastKey()).isEqualTo(6);
	}
	
	@Test
	public void mapIteratorTestEmpty(){
		reset();
		assertThat(testTBM.mapIterator()).isEqualTo(EmptyOrderedMapIterator.INSTANCE);
	}
	
	@Test
	public void mapIteratorTestNotEmpty(){
		assertThat(testTBM.mapIterator()).isNotNull().isNotEmpty();
		
		// Iterate through all the keys and check that the values and keys match
		OrderedMapIterator<Integer, String> i = testTBM.mapIterator();
		int k = 1;
		while (i.hasNext()){
			i.next();
			assertThat(i.getKey()).isEqualTo(k);
			assertThat(i.getValue()).isEqualTo("Key " + k++);
		}
	}
	
	@Test
	public void nextKeyTestEmptyNode(){
		reset();
		assertThat(testTBM.nextKey(1)).isNull();
	}
	
	@Test
	public void nextKeyTestNodes(){
		for (int i = 1; i <= testTBM.size(); i++){
			if (i == testTBM.size()){
				assertThat(testTBM.nextKey(i)).isNull();
			}else{
				assertThat(testTBM.nextKey(i)).isEqualTo(i+1);
			}
		}
	}
	
	@Test
	public void previousKeyTestEmptyNode(){
		reset();
		assertThat(testTBM.previousKey(1)).isNull();
	}
	
	@Test
	public void previousKeyTestNodes(){
		for (int i = testTBM.size(); i != 0; i--){
			if (i == 1){
				assertThat(testTBM.previousKey(i)).isNull();
			}else{
				assertThat(testTBM.previousKey(i)).isEqualTo(i-1);
			}
		}
	}
}
