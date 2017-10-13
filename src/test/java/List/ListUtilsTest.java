package List;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListUtilsTest {
    private List<Integer> listA;
    private List<Integer> listB;
    private List<Integer> listC;
    private List<Integer> listD;
    private List<Integer> listE;

    @After
    public void destroyObjects() {
        listA = listB = listC = null;
    }

    @Before
    public void initObjects() {
        listA = new ArrayList<Integer>();
        listB = new ArrayList<Integer>();
        listC = new ArrayList<Integer>();
        listD = new ArrayList<Integer>();
        listE = new ArrayList<Integer>();
        for (int i = 1; i < 6; i++) {
            listA.add(i);
            listB.add(i + 2);
            listC.add(i);
        }

        for (int i = 0; i < 46; i++) {
            listD.add(i);
        }

        for (int i = 34; i < 46; i++) {
            listE.add(i);
        }
    }

    @Test
    public void testEmptyIfNullNull() {
        List<Integer> a = ListUtils.emptyIfNull(null);
        assertThat(a).isNotNull().isEmpty();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testEmptyIfNullNullImmutableAdd() {
        List<Integer> a = ListUtils.emptyIfNull(null);
        a.add(5);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testEmptyIfNullNullImmutableRemove() {
        List<Integer> a = ListUtils.emptyIfNull(null);
        a.remove(0);
    }

    @Test
    public void testEmptyIfNullValue() {
        List<Integer> a = ListUtils.emptyIfNull(listA);
        assertThat(a).isEqualTo(listA);
    }

    @Test
    public void testEmptyIfNullValueAdd() {
        List<Integer> a = ListUtils.emptyIfNull(listA);
        a.add(90);
        assertThat(a).contains(90);
        assertThat(listA).contains(90);
    }

    @Test
    public void testIntersection() {
        List<Integer> a = ListUtils.intersection(listA, listB);
        for (int i = 3; i < 6; i++)
            assertThat(a).contains(i);
        assertThat(a).hasSize(3);
    }

    @Test
    public void testIsEqualListEqual() {
        assertThat(ListUtils.isEqualList(listA, listC)).isTrue();
    }

    @Test
    public void testIsEqualListNotEqual() {
        assertThat(ListUtils.isEqualList(listA, listB)).isFalse();
    }

    @Test
    public void testIsEqualListNullArg1() {
        assertThat(ListUtils.isEqualList(null, listC)).isFalse();
    }

    @Test
    public void testIsEqualListNullArg2() {
        assertThat(ListUtils.isEqualList(listA, null)).isFalse();
    }

    @Test
    public void testIsEqualListNullArgs() {
        assertThat(ListUtils.isEqualList(null, null)).isTrue();
    }

    @Test
    public void testPartitionEven() {
        List<List<Integer>> a = ListUtils.partition(listD, 23);
        assertThat(a).hasSize(2);
        assertThat(a.get(0)).hasSize(23);
        assertThat(a.get(1)).hasSize(23);
    }

    @Test
    public void testPartitionOdd() {
        List<List<Integer>> a = ListUtils.partition(listD, 22);
        assertThat(a).hasSize(3);
        assertThat(a.get(0)).hasSize(22);
        assertThat(a.get(1)).hasSize(22);
        assertThat(a.get(2)).hasSize(2);
    }

    @Test
    public void testRemoveAllIntersecting() {
        List<Integer> a = ListUtils.removeAll(listA, listB);
        assertThat(a).hasSize(2);
    }

    @Test
    public void testRemoveAllNonIntersecting() {
        List<Integer> a = ListUtils.removeAll(listA, listE);
        assertThat(a).hasSize(listA.size());
    }

    @Test
    public void testRetainAllIntersecting() {
        List<Integer> a = ListUtils.retainAll(listA, listB);
        assertThat(a).hasSize(3);
    }

    @Test
    public void testRetainAllNonIntersecting() {
        List<Integer> a = ListUtils.retainAll(listA, listE);
        assertThat(a).hasSize(0);
    }

    @Test
    public void testSubtractIntersectingAB() {
        List<Integer> a = ListUtils.subtract(listA, listB);
        assertThat(a).hasSize(2);
    }

    @Test
    public void testSubtractIntersectingBA() {
        List<Integer> a = ListUtils.subtract(listB, listA);
        assertThat(a).hasSize(2);
    }

    @Test
    public void testSubtractNonIntersecting() {
        List<Integer> a = ListUtils.subtract(listE, listA);
        assertThat(a).hasSize(listE.size());
    }

    @Test
    public void testSubtractSame() {
        List<Integer> a = ListUtils.subtract(listA, listA);
        assertThat(a).hasSize(0);
    }

    @Test
    public void testUnionIntersecting() {
        List<Integer> a = ListUtils.union(listA, listB);
        assertThat(a).hasSize(listA.size() + listB.size());
    }

    @Test
    public void testUnionNonIntersecting() {
        List<Integer> a = ListUtils.union(listA, listE);
        assertThat(a).hasSize(listA.size() + listE.size());
    }
}
