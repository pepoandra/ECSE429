package List;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.list.FixedSizeList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FixedSizeListTest {
    private FixedSizeList<Integer> a;
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
        a = FixedSizeList.fixedSizeList(listA);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        a.add(new Integer(5));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddPosition() {
        a.add(0, new Integer(5));
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
    public void testEmpty() {
        assertThat(a).isNotEmpty();
    }

    @Test(expected = AssertionError.class)
    public void testEmptyFail() {
        assertThat(a).isEmpty();
    }

    @Test
    public void testGet() {
        assertThat(a).element(1).isEqualTo(2);
    }

    @Test(expected = AssertionError.class)
    public void testGetFail() {
        assertThat(a).element(1).isEqualTo(3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemovePosition() {
        a.remove(0);
    }

    @Test
    public void testSize() {
        assertThat(a).hasSize(listA.size());
    }
}
