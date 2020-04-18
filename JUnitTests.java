package application;

// ATeam7
// Russel Mendes ​rmendes@wisc.edu​, Lec 002, xTeam 201
// Akshay Bodla ​bodla@wisc.edu​, Lec 001, xTeam 138
// Roland Jiang ​rjiang45@wisc.edu​, Lec 002, xTeam 201
// Will Hu, ​whu72@wisc.edu​ , Lec 002, xTeam 201
// Ben Rinvelt, ​Rinvelt@wisc.edu​, Lec 002, xTeam 241
// JUnitTests Class - tests the functionality of various
// classes in our ATeam program
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/** TODO: add class header comments here */
public class JUnitTests {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {

  }

  // MONTH CLASS TESTING
  /**
   * Tests that the month initialization class works as intended
   * 
   */
  @Test
  public void test000_testMonthInit() {
    // Month 1 = Jan
    // Month 2 = Feb
    // ....
    Month testMonth = new Month("1", "2019");

    if (!testMonth.getName().equals("January")) {
      fail("Month Mistmatch");
    }
    // Year becomes an integer
    if (testMonth.getYear() != 2019) {
      fail("Year mismatch");
    }

    testMonth = new Month("12", "-1");
    if (!testMonth.getName().equals("December")) {
      fail("Month Mismatch");
    }
    if (testMonth.getYear() != -1) {
      fail("Year mismatch");
    }
    
    testMonth = null;
  }

  /**
   * Tests the month class ability to get the sum of all of its milk
   */
  @Test
  public void test001_testMonthGetTotalMilk() {

  }

  /**
   * Test the month class ability to set a date with data
   */
  @Test
  public void test002_testMonthDataInput() {
    Month testMonth = new Month("1", "2019");
    // Jan has 31 days. So the array goes from 0->30
    testMonth.setDayMilk(235, 30);
    testMonth.setDayMilk(-5, 15);
    testMonth.setDayMilk(600, 0);

    if (testMonth.getDayMilks(30) != 235) {
      fail("Data Mismatch");
    }
    if (testMonth.getDayMilks(15) != -5) {
      fail("Data Mismatch");
    }
    if (testMonth.getDayMilks(0) != 600) {
      fail("Data Mismatch");
    }
    
    testMonth = null;
  }

  /**
   * Test the month class ability to throw exceptions
   */
  @Test
  public void test003_testMonthExceptions() {
    Month testMonth = new Month("1", "2019");
    
    try {
      testMonth = new Month("111", "2019");
      fail("Input month was invalid");
    }catch(IllegalArgumentException e) {
    }
    try {
      testMonth = new Month("0", "2019");
      fail("Input month was invalid");
    }catch(IllegalArgumentException e) {
    }
    try {
      testMonth.setDayMilk(0, -1);
      fail("Input day was invalid");
    }catch(IllegalArgumentException e) {
    }
    try {
      //TODO improve
      testMonth.setDayMilk(0, 31);
      fail("Input day was invalid");
    }catch(IllegalArgumentException e) {  
    }
    try {
      testMonth.getDayMilks(-1);
      fail("Input day was invalid");
    }catch(IllegalArgumentException e) {
    }
    try {
      testMonth.getDayMilks(31);
      fail("Input day was invalid");
    }catch(IllegalArgumentException e) {  
    }
    
    
    
    testMonth = null;
  }



}

