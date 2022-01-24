/**
 * Author: Chelsea Maramot
 * Revised: March 29,2021
 * 
 * Description: Unit Tests for LOsT ADT
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestLOsT
{

	private LOsT LO1;
	private LOsT LO2;
	private LOsT LO3;

	private final static String name1 = "topic 1";
	private final static String name2 = "topic 2";
	private final static String name3 = "topic 3";


	@Before
	public void SetUp(){
       LO1 = new LOsT(name1, 1, 2, 3, 4);
       LO2 = new LOsT(name2, 15, 6, 0, 4);
       LO3 = new LOsT(name3, 100000000,100000000,100000000,100000000);
	}

	// Less than zero

    @Test
    public void testAllEqualZero(){
    	boolean thrown = false;
        try{
        	LOsT L03 = new LOsT("topic 3", 0, 0, 0, 0);
        }catch(IllegalArgumentException e){
        	thrown = true;
        }
        assertTrue(thrown);
    }

    // Tests if one value equal to zero
    // No exception should be thrown
    @Test
    public void testOneEqualZero(){
    	boolean thrown = true;
        try{
        	LOsT L03 = new LOsT("topic 3", 0, 100000, 100000, 100000);
        }catch(Exception e){
        	thrown = false;
        }
        assertTrue(thrown);
    }

    // Tests if two values equal to zero
    // No exception should be thrown
    @Test
    public void testTwoEqualZero(){
    	boolean thrown = true;
        try{
        	LOsT L03 = new LOsT("topic 3", 0, 0, 10000, 100000);
        }catch(Exception e){
        	thrown = false;
        }
        assertTrue(thrown);
    }

    // Tests if three values equal to zero
    // No exception should be thrown
    @Test
    public void testThreeEqualZero(){
    	boolean thrown = true;
        try{
        	LOsT L03 = new LOsT("topic 3", 0, 0, 0, 100000);
        }catch(Exception e){
        	thrown = false;
        }
        assertTrue(thrown);
    }


    //-------------------------------- testing if one parameter is less than zero---------------
    @Test
    public void testnblwLessThanZero(){
    	boolean thrown = false;
        try{
        	LOsT L03 = new LOsT("topic 3", -1, 1, 1, 1);
        }catch(IllegalArgumentException e){
        	thrown = true;
        }
        assertTrue(thrown);
    }


    @Test
    public void testnmrgLessThanZero(){
    	boolean thrown = false;
        try{
        	LOsT L03 = new LOsT("topic 3", 1, -1, 1, 1);
        }catch(IllegalArgumentException e){
        	thrown = true;
        }
        assertTrue(thrown);
    }
   

    @Test
    public void testnmtsLessThanZero(){
    	boolean thrown = false;
        try{
        	LOsT L03 = new LOsT("topic 3", 1, 1, -1, 1);
        }catch(IllegalArgumentException e){
        	thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testnexcLessThanZero(){
    	boolean thrown = false;
        try{
        	LOsT L03 = new LOsT("topic 3", 1, 1, 1, -1);
        }catch(IllegalArgumentException e){
        	thrown = true;
        }
        assertTrue(thrown);
    }

    //-------------------------------------------------------------------------------------

    //Basic case only
    @Test
    public void testGetName(){
    	assertEquals(LO3.getName(), name3);
    }

    // Name equals true
    @Test
    public void testEquals(){
    	assertTrue(LO1.equals(LO1));
    }

    // Name equals false
    @Test
    public void testEqualsNot(){
    	assertFalse(LO1.equals(LO2));
    }

	@Test
	public void testMeasuresIndicator(){
		boolean thrown = false;
		try{
			LO1.measures(IndicatorT.math);
		}catch(UnsupportedOperationException e){
			thrown = true;
		}
		assertTrue(thrown);
	}

	@Test
	public void testMeasuresAttribute(){
		AttributeT Tools = new AttributeT("Use of Engineering Tools", new IndicatorT[] {IndicatorT.tools});
		boolean thrown = false;
		try{
			LO1.measures(Tools);
		}catch(UnsupportedOperationException e){
			thrown = true;
		}
		assertTrue(thrown);
	}

	@Test
	public void testMeasuresNotNormFalse(){
		Norm.setNorms(false, false, false);
		assertTrue(Arrays.equals(LO2.measures(), new double[]{15,6,0,4}));
	}

	@Test
	public void testMeasuresNIndTrueAndNotNormFalse(){
		Norm.setNorms(false, true, false);
		assertTrue(Arrays.equals(LO2.measures(), new double[]{15,6,0,4}));
	}

	@Test
	public void testMeasuresNotNormTrue(){
		Norm.setNorms(true, false, false);
		assertTrue(Arrays.equals(LO2.measures(), new double[]{0.6,0.24,0,0.16}));
	}


	@Test
	public void testMeasuresNotNormFalseLarge(){
		Norm.setNorms(true, false, false);
		assertTrue(Arrays.equals(LO3.measures(), new double[]{0.25, 0.25, 0.25, 0.25}));
	}
}


