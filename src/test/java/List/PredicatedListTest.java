package List;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.list.PredicatedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PredicatedListTest {
    private PredicatedList<Integer> a;
    private List<Integer> listA;

    @After
    public void destroyObjects() {
        listA = a = null;
    }

    @Before
    public void initObjects() {
        listA = new ArrayList<Integer>();
        for (int i = 1; i < 6; i++)
            listA.add(i);

        a = PredicatedList.predicatedList(listA, PredicateUtils.notNullPredicate());
    }

    @Test
    public void testAdd() {
        a.add(new Integer(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        a.add(null);
    }

    @Test
    public void testAddPosition() {
        a.add(4, new Integer(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPositionNull() {
        a.add(4, null);
    }

    @Test
    public void testContains() {
        assertThat(a).contains(1);
    }

    @Test(expected = AssertionError.class)
    public void testContainsFail() {
        assertThat(a).contains(7);
    }

    @Test
    public void testContainsNull() {
        assertThat(a).doesNotContainNull();
    }

    @Test
    public void testEmpty() {
        assertThat(a).isNotEmpty();
    }

    @Test(expected = AssertionError.class)
    public void testEmptyFail() {
        assertThat(a).isEmpty();
    }

    @Test
    public void testGet() {
        assertThat(a).element(1).isEqualTo(new Integer(2));
    }

    @Test(expected = AssertionError.class)
    public void testGetFail() {
        assertThat(a).element(1).isEqualTo(3);
    }

    @Test
    public void testRemovePosition() {
        a.add(0, new Integer(5));
        a.remove(0);
    }

    @Test
    public void testSize() {
        assertThat(a).hasSize(listA.size());
    }
}
