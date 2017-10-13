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
        a = new GrowthList<Integer>(2);
    }

    @Test
    public void testAdd() {
        a.add(new Integer(5));
    }

    @Test
    public void testAddPosition() {
        a.add(16, new Integer(5));
    }

    @Test
    public void testContains() {
        a.add(16, new Integer(1));
        assertThat(a).contains(1);
    }

    @Test(expected = AssertionError.class)
    public void testContainsFail() {
        assertThat(a).contains(7);
    }

    @Test
    public void testEmpty() {
        a.add(16, new Integer(1));
        assertThat(a).isNotEmpty();
    }

    @Test(expected = AssertionError.class)
    public void testEmptyFail() {
        a.add(16, new Integer(1));
        assertThat(a).isEmpty();
    }

    @Test
    public void testGet() {
        a.add(16, new Integer(5));
        assertThat(a).element(16).isEqualTo(new Integer(5));
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
        a.add(16, new Integer(5));
        assertThat(a).hasSize(17);
    }
}
