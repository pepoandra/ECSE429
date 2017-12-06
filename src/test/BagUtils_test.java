package test;

//Testing framework
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

//Tested Classes
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BagUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.apache.commons.collections4.bag.CollectionBag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.PredicatedBag;
import org.apache.commons.collections4.bag.PredicatedSortedBag;
import org.apache.commons.collections4.bag.SynchronizedBag;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.collections4.bag.TransformedBag;
import org.apache.commons.collections4.bag.TransformedSortedBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.bag.UnmodifiableBag;
import org.apache.commons.collections4.bag.UnmodifiableSortedBag;
import org.apache.commons.collections4.functors.TruePredicate;	

public class BagUtils_test {
	protected Predicate<Object> truePredicate = TruePredicate.truePredicate(); //Predicate that is always true
    protected Transformer<Object, Object> nopTransformer = TransformerUtils.nopTransformer(); //Transformer that does nothing
    
	@Test
	public void test_collectionBag()
	{
		HashBag h = new HashBag<>();
		Bag<Object> bag = BagUtils.collectionBag(h);
		
		assertThat(bag).as("Object is a CollectionBag").isInstanceOf(CollectionBag.class);
	}
	
	@Test
	public void test_emptyBag()
	{
		assertThat(BagUtils.emptyBag()).as("EmptyBag is UnmodifiableBag").isInstanceOf(UnmodifiableBag.class);
		assertThat(BagUtils.emptyBag()).as("EmptyBag empty").isEmpty();
	}
	
	@Test
	public void test_emptySortedBag()
	{
		assertThat(BagUtils.emptySortedBag()).as("EmptySortedBag is UnmodifiableSortedBag").isInstanceOf(UnmodifiableSortedBag.class);
		assertThat(BagUtils.emptySortedBag()).as("EmptySortedBag empty").isEmpty();
	}
	
	@Test
	public void test_predicatedBag()
	{
		HashBag h = new HashBag<>();
		Bag<Object> bag = BagUtils.predicatedBag(h, truePredicate);
		
		assertThat(bag).as("Object is a PredicatedBag").isInstanceOf(PredicatedBag.class);
	}
	
	@Test
	public void test_predicatedSortedBag()
	{
		TreeBag t = new TreeBag<>();
		Bag<Object> bag = BagUtils.predicatedSortedBag(t, truePredicate);
		
		assertThat(bag).as("Object is a PredicatedSortedBag").isInstanceOf(PredicatedSortedBag.class);
	}
	
	@Test
	public void test_synchronizedBag()
	{
		HashBag h = new HashBag<>();
		Bag<Object> bag = BagUtils.synchronizedBag(h);
		
		assertThat(bag).as("Object is a SynchronizedBag").isInstanceOf(SynchronizedBag.class);
	}
	
	@Test
	public void test_synchronizedSortedBag()
	{
		TreeBag t = new TreeBag<>();
		Bag<Object> bag = BagUtils.synchronizedSortedBag(t);
		
		assertThat(bag).as("Object is a SynchronizedSortedBag").isInstanceOf(SynchronizedSortedBag.class);
	}
	
	@Test
	public void test_transformingBag()
	{
		HashBag h = new HashBag<>();
		Bag<Object> bag = BagUtils.transformingBag(h, nopTransformer);
		
		assertThat(bag).as("Object is a TransformingBag").isInstanceOf(TransformedBag.class);
	}
	
	@Test
	public void test_transformingSortedBag()
	{
		TreeBag t = new TreeBag<>();
		Bag<Object> bag = BagUtils.transformingSortedBag(t, nopTransformer);
		
		assertThat(bag).as("Object is a TransformingSortedBag").isInstanceOf(TransformedSortedBag.class);
	}
	
	@Test
	public void test_unmodifiableBag()
	{
		HashBag h = new HashBag<>();
		Bag<Object> bag = BagUtils.unmodifiableBag(h);
		
		assertThat(bag).as("Object is a UnmodifiableBag").isInstanceOf(UnmodifiableBag.class);
	}
	
	@Test
	public void test_unmodifiableSortedBag()
	{
		TreeBag t = new TreeBag<>();
		Bag<Object> bag = BagUtils.unmodifiableSortedBag(t);
		
		assertThat(bag).as("Object is a UnmodifiableSortedBag").isInstanceOf(UnmodifiableSortedBag.class);
	}
}
