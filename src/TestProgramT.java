/**
 * Author: Chelsea Maramot
 * Revised: March 29, 2021
 * 
 * Description: Unit Tests for ProgramT ADT
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestProgramT
{
	private ProgramT software1;
	private ProgramT software2;

	private IndicatorT[] indicators;
	private IndicatorT[] SE2C03_indicators;
	private IndicatorT[] SE3A04_indicators;
	private IndicatorT[] empty_ind = {}; 

	private CourseT SE2C03;
	private CourseT SE3A04;
	private CourseT SE2AA4;
	private CourseT NONE;

	private LOsT LO1;
    private LOsT LO2;
    private LOsT LO3;
    private LOsT LO4;

	

	@Before
	public void SetUp(){

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
		CourseT SE2AA4 = new CourseT("SE2AA4", indicators);
		// One indicator
		CourseT SE2C03 = new CourseT("SE2C03", SE2C03_indicators);
		// Two indicators
		CourseT SE3A04 = new CourseT("SE3A04", SE3A04_indicators);
		// empty indicator
		CourseT NONE = new CourseT("NONE", empty_ind);


		//Initialize L0
		LO1 = new LOsT("topic 1", 1, 2, 3, 4);
        LO2 = new LOsT("topic 2", 2, 4, 6, 8);
        LO3 = new LOsT("topic 3", 1, 1, 1, 1);
        LO4 = new LOsT("topic 4", 1000000000, 1000000000, 1000000000, 1000000000);


		//Add L0S: SE2AA4 has the same LO for each ind
		for(IndicatorT ind : SE2AA4.getIndicators()){;
			SE2AA4.addLO(ind, LO1);
			SE2AA4.addLO(ind, LO2);
			SE2AA4.addLO(ind, LO3);
		}

		// Basic L0
		SE2C03.addLO(IndicatorT.desPrinciples, LO1);
		//Extreme LO
		SE3A04.addLO(IndicatorT.healthSafety, LO3);
		SE3A04.addLO(IndicatorT.healthSafety, LO4);
		SE3A04.addLO(IndicatorT.standards, LO4);


		// Add courses to program
		software1 = new ProgramT();
		software1.add(SE2AA4);
		software1.add(SE3A04);
		software1.add(SE2C03);

		software2 = new ProgramT();
		software2.add(SE3A04);

	}

	// Empty measure
	@Test
    public void testMeasures(){
    	boolean thrown = false;
    	try{
    		software1.measures();
    	}catch(UnsupportedOperationException e){
    		thrown = true;
    	}
        assertTrue(thrown);
    }

    // Meaures with indicator parameter
    @Test
    public void testMeasuresIndicator(){
    	boolean thrown = false;
    	try{
    		software1.measures(IndicatorT.math);
    	}catch(UnsupportedOperationException e){
    		thrown = true;
    	}
        assertTrue(thrown);
    }

    @Test
    public void testMeasuresAttributeOneCourse(){
    	AttributeT att = new AttributeT("John", SE3A04_indicators);
    	assertTrue(Arrays.equals(software2.measures(att), new double[]{0.25, 0.25, 0.25, 0.25}));
    }

    @Test
    public void testMeasuresAttributeChangeAttributeOnly(){
    	AttributeT att = new AttributeT("John", indicators);
    	assertTrue(Arrays.equals(software2.measures(att), new double[]{0.25, 0.25, 0.25, 0.25}));
    }

    // Changed numbers in spreadsheet
    @Test
    public void testMeasuresSpreadsheet(){
		IndicatorT[] SE2AA4_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt,
                                                      IndicatorT.suitableFund, IndicatorT.recogTheory, IndicatorT.modelSelect,
                                                      IndicatorT.estOutcomes, IndicatorT.desProcess, IndicatorT.desPrinciples,
                                                      IndicatorT.openEnded, IndicatorT.tools, IndicatorT.engInSoc,
                                                      IndicatorT.awarePEO};
       IndicatorT[] SE2C03_indicators = new IndicatorT[] {IndicatorT.desPrinciples};
       IndicatorT[] SE3A04_indicators = new IndicatorT[] {IndicatorT.standards, IndicatorT.healthSafety};
       IndicatorT[] Unused_indicators = new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.tools};

 
       AttributeT Design = new AttributeT("Design", new IndicatorT[] {IndicatorT.desProcess, IndicatorT.desPrinciples,
                                                                      IndicatorT.openEnded, IndicatorT.ideaGeneration,
                                                                      IndicatorT.healthSafety, IndicatorT.standards});

       //Excercise Norm Module
       Norm.setNorms(false, false, false);

       //Exercise Learning Outcomes Module
       LOsT LO1 = new LOsT("topic 1", 23, 45, 56, 89);
       LOsT LO2 = new LOsT("topic 2", 15, 6, 78, 4);

       //Excercise Courses Module
       CourseT SE2AA4 = new CourseT("Software Engineering Design 1", SE2AA4_indicators);
       
       SE2AA4.addLO(IndicatorT.math, LO1);
       SE2AA4.addLO(IndicatorT.math, LO2);
            
       //Delete learning outcomes    
       SE2AA4.delLO(IndicatorT.math, LO1);
       SE2AA4.delLO(IndicatorT.math, LO2);

      // //Populate a few Courses
       CourseT SE2C03 = new CourseT("Data Structures and Algorithms", SE2C03_indicators);
       CourseT SE3A04 = new CourseT("Software Design II Large Scale Systems", SE3A04_indicators);

      // //Adding Learning Outcomes to Courses
       SE2AA4.addLO(IndicatorT.desProcess, new LOsT("Recog and follow eng des process", 100, 200, 300, 400));
       SE2AA4.addLO(IndicatorT.desProcess, new LOsT("Modularization and interface design", 25, 25, 25, 25));
       SE2AA4.addLO(IndicatorT.desPrinciples, new LOsT("Software qualities", 0, 0, 1, 0));
       SE2AA4.addLO(IndicatorT.openEnded, new LOsT("Complete design, implement and test for a set of modules", 15, 14, 13, 12));
       SE2C03.addLO(IndicatorT.desPrinciples, new LOsT("Identify time/space trade-offs", 0, 0, 4, 22));
       SE3A04.addLO(IndicatorT.standards, new LOsT("Select among design methodologies", 80, 150, 97, 110));      
       SE3A04.addLO(IndicatorT.standards, new LOsT("State the design principles", 1000, 2000, 3000, 4000));
       SE3A04.addLO(IndicatorT.standards, new LOsT("Evaluate design solution against requirements", 200, 80, 100, 4));      
       SE3A04.addLO(IndicatorT.healthSafety, new LOsT("Organize and plan the development of a software system", 80, 150, 97, 110));
       SE3A04.addLO(IndicatorT.healthSafety, new LOsT("Select among development doc templates", 1000000, 1000000, 1000000, 1000000));
       SE3A04.addLO(IndicatorT.healthSafety, new LOsT("Assess the impact of a requirement on the architecture", 2000, 800, 1000, 400));

      // //Add courses to P
       ProgramT P = new ProgramT();
       P.add(SE2AA4);
       P.add(SE2C03);
       P.add(SE3A04); 
       assertTrue(Arrays.equals(P.measures(Design), new double[]{0.24983574575658904,  0.24981557964258178, 0.250118818245802, 0.2502298563550272}));
   }

}
