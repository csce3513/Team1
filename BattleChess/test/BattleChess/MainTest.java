package BattleChess;

import org.junit.*;
import static org.junit.Assert.*;

public class MainTest {
    
    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class Main.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Main instance = new Main();
        instance.init();
    }

    /**
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Main.main(args);
    }
}
