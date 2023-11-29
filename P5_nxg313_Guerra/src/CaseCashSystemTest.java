import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaseCashSystemTest {

    // Sort by Balance TESTS
    @Test
    public void testSortBalance1() {
        CaseCashSystem a = new CaseCashSystem();
        a.init("bob", 350);
        a.init("alice", 200);
        a.init("chloe", 1);
        a.init("andrew", 1000);
        List<String> sortedNames = a.sortBalance();
        List<String> expectedOrder = Arrays.asList("chloe", "alice", "bob", "andrew");
        assertEquals(expectedOrder, sortedNames);
    }

    @Test
    public void testSortBalance_OneStudent() {
        CaseCashSystem a = new CaseCashSystem();
        a.init("bob", 350);
        List<String> sortedNames = a.sortBalance();
        List<String> expectedOrder = Arrays.asList("bob");
        assertEquals(expectedOrder, sortedNames);
    }

    @Test
    public void testSortBalance_ManyStudents() {
        CaseCashSystem a = new CaseCashSystem();
        a.init("bob", 350);
        a.init("alice", 200);
        a.init("chloe", 1);
        a.init("andrew", 1000);
        a.init("jacob", 1500);
        a.init("ally", 2000);
        a.init("andy", 0);

        List<String> sortedNames = a.sortBalance();
        List<String> expectedOrder = Arrays.asList("andy","chloe", "alice", "bob", "andrew", "jacob", "ally");
        assertEquals(expectedOrder, sortedNames);
    }

    // Sort by Name TESTS
    @Test
    public void testSortName1() {
        CaseCashSystem a = new CaseCashSystem();
        a.init("bob", 0);
        a.init("alice", 0);
        a.init("chloe", 0);
        a.init("zelda", 0);

        List<String> sortedNames = a.sortName();
        List<String> expectedOrder = Arrays.asList("alice", "bob", "chloe", "zelda");
        assertEquals(expectedOrder, sortedNames);
    }

    @Test
    public void testSortName_ManyNames() {
        CaseCashSystem a = new CaseCashSystem();
        a.init("bob", 0);
        a.init("bobb", 0);
        a.init("zoe", 0);
        a.init("zelda", 0);
        a.init("will", 0);
        a.init("jordan", 0);
        a.init("will", 0);

        List<String> sortedNames = a.sortName();
        List<String> expectedOrder = Arrays.asList("bob", "bobb", "jordan", "will", "zelda", "zoe");
        assertEquals(expectedOrder, sortedNames);
    }

    @Test
    public void testSortName_DifferingBalances() {
        CaseCashSystem a = new CaseCashSystem();
        a.init("bob", 300);
        a.init("alice", 10000);
        a.init("chloe", 2);
        List<String> sortedNames = a.sortName();
        List<String> expectedOrder = Arrays.asList("alice", "bob", "chloe");
        assertEquals(expectedOrder, sortedNames);
    }

    @Test
    public void testSortName_OneName() {
        CaseCashSystem a = new CaseCashSystem();
        a.init("bob", 0);
        List<String> sortedNames = a.sortName();
        List<String> expectedOrder = Arrays.asList("bob");
        assertEquals(expectedOrder, sortedNames);
    }




}