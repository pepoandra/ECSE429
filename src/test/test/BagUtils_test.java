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
		Bag<Object> bag = BagUtils.collectionBag(new HashBag<>());
		
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
		Bag<Object> bag = BagUtils.predicatedBag(new HashBag<>(), truePredicate);
		
		assertThat(bag).as("Object is a PredicatedBag").isInstanceOf(PredicatedBag.class);
	}
	
	@Test
	public void test_predicatedSortedBag()
	{
		Bag<Object> bag = BagUtils.predicatedSortedBag(new TreeBag<>(), truePredicate);
		
		assertThat(bag).as("Object is a PredicatedSortedBag").isInstanceOf(PredicatedSortedBag.class);
	}
	
	@Test
	public void test_synchronizedBag()
	{
		Bag<Object> bag = BagUtils.synchronizedBag(new HashBag<>());
		
		assertThat(bag).as("Object is a SynchronizedBag").isInstanceOf(SynchronizedBag.class);
	}
	
	@Test
	public void test_synchronizedSortedBag()
	{
		Bag<Object> bag = BagUtils.synchronizedSortedBag(new TreeBag<>());
		
		assertThat(bag).as("Object is a SynchronizedSortedBag").isInstanceOf(SynchronizedSortedBag.class);
	}
	
	@Test
	public void test_transformingBag()
	{
		Bag<Object> bag = BagUtils.transformingBag(new HashBag<>(), nopTransformer);
		
		assertThat(bag).as("Object is a TransformingBag").isInstanceOf(TransformedBag.class);
	}
	
	@Test
	public void test_transformingSortedBag()
	{
		Bag<Object> bag = BagUtils.transformingSortedBag(new TreeBag<>(), nopTransformer);
		
		assertThat(bag).as("Object is a TransformingSortedBag").isInstanceOf(TransformedSortedBag.class);
	}
	
	@Test
	public void test_unmodifiableBag()
	{
		Bag<Object> bag = BagUtils.unmodifiableBag(new HashBag<>());
		
		assertThat(bag).as("Object is a UnmodifiableBag").isInstanceOf(UnmodifiableBag.class);
	}
	
	@Test
	public void test_unmodifiableSortedBag()
	{
		Bag<Object> bag = BagUtils.unmodifiableSortedBag(new TreeBag<>());
		
		assertThat(bag).as("Object is a UnmodifiableSortedBag").isInstanceOf(UnmodifiableSortedBag.class);
	}
}
