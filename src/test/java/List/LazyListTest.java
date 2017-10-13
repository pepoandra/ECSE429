package List;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.FactoryUtils;
import org.apache.commons.collections4.list.LazyList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LazyListTest {
    private LazyList<Integer> a;
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

        a = LazyList.lazyList(listA, FactoryUtils.constantFactory(new Integer(0)));
    }

    @Test
    public void testAdd() {
        initObjects();

        a.add(new Integer(5));

        destroyObjects();
    }

    @Test
    public void testAddPosition() {
        initObjects();

        a.add(4, new Integer(5));

        destroyObjects();
    }

    @Test
    public void testContains() {
        initObjects();

        assertThat(a).contains(1);

        destroyObjects();
    }

    @Test(expected = AssertionError.class)
    public void testContainsFail() {
        initObjects();

        assertThat(a).contains(7);

        destroyObjects();
    }

    @Test
    public void testEmpty() {
        initObjects();

        assertThat(a).isNotEmpty();

        destroyObjects();
    }

    @Test(expected = AssertionError.class)
    public void testEmptyFail() {
        initObjects();

        assertThat(a).isEmpty();

        destroyObjects();
    }

    @Test
    public void testGet() {
        initObjects();

        assertThat(a).element(1).isEqualTo(new Integer(2));

        destroyObjects();
    }

    @Test
    public void testGetLazy() {
        initObjects();

        a.get(50);

        destroyObjects();
    }

    @Test(expected = AssertionError.class)
    public void testGetFail() {
        initObjects();

        assertThat(a).element(1).isEqualTo(3);

        destroyObjects();
    }

    @Test
    public void testRemovePosition() {
        initObjects();

        a.add(0, new Integer(5));
        a.remove(0);

        destroyObjects();
    }

    @Test
    public void testSize() {
        initObjects();

        assertThat(a).hasSize(listA.size());

        destroyObjects();
    }
}
