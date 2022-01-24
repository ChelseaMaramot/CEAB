/**
 * Author: Chelsea Maramot
 * Revised: March 29, 2021
 * 
 * Description: Unit Tests for CourseT ADT
 */

package src;


import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TestCourseT
{ 
	private IndicatorT[] indicators;
	private IndicatorT[] SE2C03_indicators;
	private IndicatorT[] SE3A04_indicators;
	private IndicatorT[] empty_ind = {}; 

	private CourseT PROGRAM;
	private CourseT SE2C03;
	private CourseT SE3A04;
	private CourseT NONE;

	private static final String name1 = "PROGRAM";
	private static final String name2 = "SE2C03";
	private static final String name3 = "SE3A04";
	private static final String name4 = "NONE";

	private LOsT LO1;
    private LOsT LO2;
    private LOsT LO3;
    private LOsT LO4;




	@Before
	public void setUp(){
		indicators = new IndicatorT[] {
			IndicatorT.math, 
			IndicatorT.specEngKnow, 
			IndicatorT.assumpt, 
			IndicatorT.suitableFund, 
			IndicatorT.recogTheory,
			IndicatorT.modelSelect,
			IndicatorT.estOutcomes,
			IndicatorT.desProcess,
			IndicatorT.desPrinciples,
			IndicatorT.openEnded,
			IndicatorT.ideaGeneration,
			IndicatorT.healthSafety,
			IndicatorT.standards,
			IndicatorT.tools,
			IndicatorT.engInSoc,
			IndicatorT.awarePEO

		};


		SE2C03_indicators = new IndicatorT[] {IndicatorT.desPrinciples};
       	SE3A04_indicators = new IndicatorT[] {IndicatorT.standards, IndicatorT.healthSafety};
       
		//all of the indicators
		
		PROGRAM = new CourseT(name1, indicators);
		// One indicator
		SE2C03 = new CourseT(name2, SE2C03_indicators);
		// Two indicators
		SE3A04 = new CourseT(name3, SE3A04_indicators);
		// empty indicator
		NONE = new CourseT(name4, empty_ind);


		//Initialize L0
		LO1 = new LOsT("topic 1", 1, 2, 3, 4);
        LO2 = new LOsT("topic 2", 2, 4, 6, 8);
        LO3 = new LOsT("topic 3", 1, 1, 1, 1);
        LO4 = new LOsT("topic 4", 1000000000, 1000000000, 1000000000, 1000000000);


		//Add L0S: Program has the same LO for each ind
		for(IndicatorT ind : PROGRAM.getIndicators()){;
			PROGRAM.addLO(ind, LO1);
			PROGRAM.addLO(ind, LO2);
			PROGRAM.addLO(ind, LO3);
		}

		// Basic L0
		SE2C03.addLO(IndicatorT.desPrinciples, LO1);

		//Extreme LO
		SE3A04.addLO(IndicatorT.healthSafety, LO3);
		SE3A04.addLO(IndicatorT.healthSafety, LO4);
		SE3A04.addLO(IndicatorT.standards, LO4);
	}


	@After
	public  void tearDown(){

		indicators = null;
		SE2C03_indicators = null;
		SE3A04_indicators = null;
		empty_ind = null;

		PROGRAM = null;
		SE2C03 = null;
		SE3A04 = null;
		NONE = null;

		LOsT LO1 = null;
	    LOsT LO2 = null;
	    LOsT LO3 = null;
	    LOsT LO4 = null;

	}


	//Basic case
	@Test
    public void testGetNameProgram(){
    	CourseT SE2AA4 = new CourseT("SE2AA4",indicators);
        assertEquals(SE2AA4.getName(), "SE2AA4");
    }

    // ignores order 
    @Test
    public void testGetIndicatorsProgram(){
    	CourseT SE2AA4 = new CourseT("SE2AA4",indicators);
    	List<IndicatorT> ind = Arrays.asList(indicators);
    	List<IndicatorT> value = Arrays.asList(SE2AA4.getIndicators());
    	assertTrue(value.size() == ind.size() && value.containsAll(ind) && ind.containsAll(value));
    }

  
    @Test
    public void testGetEmptyIndicator(){
     	CourseT SE2AA4 = new CourseT("SE2AA4",empty_ind);
    	List<IndicatorT> ind = Arrays.asList(empty_ind);
    	List<IndicatorT> value = Arrays.asList(SE2AA4.getIndicators());
    	assertTrue(value.size() == ind.size());
    }

    // Basic Case in all indicators
    @Test
    public void testGetLOsInIndicators(){
    	List<LOsT> value = Arrays.asList(PROGRAM.getLOs(IndicatorT.awarePEO));
    	List<LOsT> L = Arrays.asList(new LOsT[]{LO1,LO2,LO3});
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
    }

    @Test 
    public void getLOsNotInIndicators(){
    	List<LOsT> value = Arrays.asList(SE2C03.getLOs(IndicatorT.modelSelect));
    	List<LOsT> L = Arrays.asList(new LOsT[0]);
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
    }

    //Empty L0S
    @Test
    public void testGetLOsEmptyIndicator(){
    	List<LOsT> value = Arrays.asList(NONE.getLOs(IndicatorT.modelSelect));
    	List<LOsT> L = Arrays.asList(new LOsT[0]);
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
    }


   //Add basic case
   @Test
   public void testAddLOsBasic(){
   		SE2C03.addLO(IndicatorT.desPrinciples, LO2);
   		SE2C03.addLO(IndicatorT.desPrinciples, LO3);

    	List<LOsT> value = Arrays.asList(SE2C03.getLOs(IndicatorT.desPrinciples));
    	List<LOsT> L = Arrays.asList(new LOsT[]{LO1, LO2, LO3});
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
   	}


   	// Adding indicator that is not there
   	@Test
    public void testAddLOsNoIndicator(){
    	SE2C03.addLO(IndicatorT.math, LO1);
    	List<LOsT> value = Arrays.asList(SE2C03.getLOs(IndicatorT.math));
    	List<LOsT> L = Arrays.asList(new LOsT[0]);
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
   	}  

   	// Adding L0 that already exists
   	   	@Test
    public void testAddLOsThatExists(){
    	SE2C03.addLO(IndicatorT.desPrinciples, LO1);
    	List<LOsT> value = Arrays.asList(SE2C03.getLOs(IndicatorT.desPrinciples));
    	List<LOsT> L = Arrays.asList(new LOsT[]{LO1});
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
   	}  

   	// Delete all
   	@Test
    public void testDelLOsAll(){
   		PROGRAM.delLO(IndicatorT.math, LO1);
   		PROGRAM.delLO(IndicatorT.math, LO3);
   		PROGRAM.delLO(IndicatorT.math, LO2);
    	List<LOsT> value = Arrays.asList(PROGRAM.getLOs(IndicatorT.math));
    	List<LOsT> L = Arrays.asList(new LOsT[0]);
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
   	}

	// Remove indicator that is not there
   	@Test
    public void testDelLOsNoIndicator(){
   		SE2C03.delLO(IndicatorT.desPrinciples, LO2);
    	List<LOsT> value = Arrays.asList(SE2C03.getLOs(IndicatorT.desPrinciples));
    	List<LOsT> L = Arrays.asList(new LOsT[]{LO1});
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
   	}  


   	// Basic case: contains all
   	@Test
   	public void testMemberBasic(){
   		LOsT[] L = {LO1, LO2, LO3};
   		assertTrue(PROGRAM.member(IndicatorT.math, L));
   	}

   	//not all values in the indicator is found in L
   	@Test
   	public void testMemberNotAllLOs(){
   		LOsT[] L = {LO1, LO2};
   		assertFalse(PROGRAM.member(IndicatorT.math, L));	
   	}

   	//All L0s are a member and there are extra LOs in the second param.
   	@Test
   	public void testMemberExtraOutcomes(){
   		LOsT[] L = {LO1, LO2, LO3, LO4};
   		assertFalse(SE3A04.member(IndicatorT.healthSafety, L));	
   	}

   	// none are a member --> Should this be true?
   	@Test
   	public void testMemberNone(){
   		LOsT[] L = {LO4};
   		assertFalse(PROGRAM.member(IndicatorT.math, L));	
   	}

   	// Some are a member but not all
   	@Test
   	public void testMemberSomeMembers(){
   		LOsT[] L = {LO3};
   		assertFalse(SE3A04.member(IndicatorT.healthSafety, L));	
   	}

   	// Should throw an exception
	@Test
	public void testMeasuresNoParameters(){
		boolean thrown = false;
		try{
			SE2C03.measures();
		}catch(UnsupportedOperationException e){
			thrown = true;
		}
		assertTrue(thrown);
	}	

	//Measures with indicator param
	@Test
	public void testMeasuresIndicatorEmptyLOs(){
    	assertEquals(Arrays.toString(SE3A04.measures(IndicatorT.desPrinciples)), Arrays.toString(new double[]{0.0,0.0,0.0,0.0}));
	}

	// Basic one LOs and first conditional
	@Test
	public void testMeasuresIndicatorAllTrueOneInd(){
		Norm.setNorms(true, true, true);
		assertTrue(Arrays.equals(SE2C03.measures(IndicatorT.desPrinciples), new double[]{0.1, 0.2, 0.3, 0.4}));
	}

	// Basic one LOs and First conditional
	@Test
	public void testMeasuresIndicatorTrueNIndOneLos(){
		Norm.setNorms(false, true, false);
		assertTrue(Arrays.equals(SE2C03.measures(IndicatorT.desPrinciples), new double[]{0.1, 0.2, 0.3, 0.4}));
	}

	// Conditional and large values
	@Test
	public void testMeasuresIndicatorAllFalseTwoInd(){
		Norm.setNorms(false, false, false);
		assertTrue(Arrays.equals(SE3A04.measures(IndicatorT.healthSafety), new double[]{1000000001,1000000001 ,1000000001, 1000000001}));
	}

	// Empty attribute
	@Test
	public void testMeasuresAttributeEmpty(){
		AttributeT att = new AttributeT("John", empty_ind);
		assertTrue(Arrays.equals(SE2C03.measures(att), new double[]{0.0, 0.0, 0.0, 0.0}));
	}

	// First condition true, empty indicator
	@Test
	public void testMeasuresAttributeGetNAttEmptyIndicator(){
		Norm.setNorms(true, true, true);
		AttributeT att = new AttributeT("John", empty_ind);
		assertTrue(Arrays.equals(NONE.measures(att), new double[]{0.0, 0.0, 0.0, 0.0}));
	}

	@Test
	public void testMeasureAttributeGetNAttTrueNorm(){
		Norm.setNorms(true, true, true);
		AttributeT att = new AttributeT("John", SE3A04_indicators);
		assertTrue(Arrays.equals(SE3A04.measures(att), new double[]{0.25, 0.25, 0.25, 0.25}));
	}


	@Test
	public void testMeasureAttributeGetNAttFalse(){
		Norm.setNorms(false, false, false);
		AttributeT att = new AttributeT("John", SE3A04_indicators);
		assertTrue(Arrays.equals(SE3A04.measures(att), new double[]{2000000001, 2000000001, 2000000001, 2000000001}));
	}

}
 