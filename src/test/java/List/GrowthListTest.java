package List;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.collections4.list.GrowthList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GrowthListTest {
    private GrowthList<Integer> a;

    @After
    public void destroyObjects() {
        a = null;
    }

    @Before
    public void initObjects() {
        a = new GrowthList(2);
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

        a.add(16, new Integer(5));

        destroyObjects();
    }

    @Test
    public void testContains() {
        initObjects();

        a.add(16, new Integer(1));
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

        a.add(16, new Integer(1));
        assertThat(a).isNotEmpty();

        destroyObjects();
    }

    @Test(expected = AssertionError.class)
    public void testEmptyFail() {
        initObjects();

        a.add(16, new Integer(1));
        assertThat(a).isEmpty();

        destroyObjects();
    }

    @Test
    public void testGet() {
        initObjects();

        a.add(16, new Integer(5));
        assertThat(a).element(16).isEqualTo(new Integer(5));

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

        a.add(16, new Integer(5));
        assertThat(a).hasSize(17);

        destroyObjects();
    }
}
