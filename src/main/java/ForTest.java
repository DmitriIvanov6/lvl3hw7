public class ForTest {


    @AfterSuite
    public static void after() {
        System.out.println("After");
    }

//    @AfterSuite
//    public static void  afterWrong(){
//        System.out.println("AfterWrong");
//    }

    @Test(priority = 2)
    public static void test3() {
        System.out.println("test3");
    }


    @Test
    public static void test2() {
        System.out.println("test2");
    }


    @Test(priority = 7)
    public static void test1() {
        System.out.println("test1");
    }

    @BeforeSuite
    public static void before() {
        System.out.println("Before");
    }
}
