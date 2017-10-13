package List;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;

public class ListUtilsTest {
    private List<Integer> listA;
    private List<Integer> listB;

    @Before
    public void initObjects() {
        listA = Arrays.asList(1, 2, 3, 4, 5);
        listA = Arrays.asList(3, 4, 5, 6, 7);
    }
}
