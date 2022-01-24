/**
 * Author: Chelsea Maramot
 * Revised: March 29, 2021
 * 
 * Description: Unit Tests for AttributeT ADT
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;

public class TestAttributeT
{

	private AttributeT att1;
	private AttributeT att2;
	private AttributeT att3;
	private final static String name = "John";
	private IndicatorT[] empty_ind = {};
	private IndicatorT[] indicators;
	private IndicatorT[]  two_indicators;

	@Before
	public void setUp(){
		indicators =  new IndicatorT[] {
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

		two_indicators = new IndicatorT[]{IndicatorT.estOutcomes, IndicatorT.assumpt};

		att1 = new AttributeT(name, indicators);
		att2 = new AttributeT(name, empty_ind);
		att3 = new AttributeT(name, two_indicators);

	}

    @Test
    public void testgetName()
    {
        assertTrue(att1.getName() == "John");

    }

    @Test
    public void testEmptyInd(){
    	assertTrue(att2.getIndicators().length == new IndicatorT[0].length);
    }

    @Test
    public void testGetAllIndicators(){
		List<IndicatorT> value = Arrays.asList(att1.getIndicators());
    	List<IndicatorT> L = Arrays.asList(indicators);
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
    }

    @Test
    public void testGetOneIndicator(){
		List<IndicatorT> value = Arrays.asList(att3.getIndicators());
    	List<IndicatorT> L = Arrays.asList(two_indicators);
    	assertTrue(value.size() == L.size() && value.containsAll(L) && L.containsAll(value));
    }

}
