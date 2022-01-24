package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Arrays;

/**
 *	@brief	An ADT that represents the courses 
 *	@details Uses Measures, InidicatorT, AttributeT, Services, and Norm.
 			 Assumes that the constructor starts with an empty set of learning outcomes.
 			 If learning outcomes are removed, it is assumed that not all of them will be
 			 removed before the Course is used in an aggregation calculation.
 */
public class CourseT implements Measures{
	
	private String name;
	private HashMap<IndicatorT, Set<LOsT>> m;

	/**
	 *	@brief Constructor that initializes a CourseT object.
	 *	@param courseName Represents a course name of type String.
	 *	@param indicators Represents a sequence of indicators of type IndicatorT.
	 */
	public CourseT(String courseName, IndicatorT[] indicators){
		name = courseName;
		m = new HashMap<IndicatorT, Set<LOsT>>();

		for(IndicatorT elem: indicators){
			m.put(elem, new HashSet<LOsT>());
		}

	}	

	/**
	 *	@brief	A getter for the course name.
	 *	@return The course name of type String.
	 */
	public String getName(){
		return name;
	}

	/**
	 *	@brief A getter for the sequence of indicators.
	 *	@return A sequence of indicators of type indicatorT.
	 */
	public IndicatorT[] getIndicators(){
		IndicatorT[] indicators = new IndicatorT[m.size()];
		return m.keySet().toArray(indicators);
	}

	/**
	 *	@brief A getter for the sequence of learning outcomes for a specific indicator.
	 * 	@details If the indicator is present in the map of indicators 
	 *			 and learning outcomes, then the sequence of learning outcomes 
	 *			 for that indicator is returned. If the indicator is not contained 
	 *			 in the map, then an empty sequence of LOsT is returned.
	 *	@param indicator Represents an indicator of type IndicatorT.
	 *	@return A sequence of learning outcomes of type LOsT.
	 */
	public LOsT[] getLOs(IndicatorT indicator){
		if (m.containsKey(indicator)){
			LOsT[] seq = new LOsT[m.get(indicator).size()];
			return m.get(indicator).toArray(seq);
		}
		return new LOsT[]{};
	}

	/**
	 *	@brief Adds a learning outcome to the corresponding indicator.
	 *	@details If the indicator is contained in the map of indicators 
	 *			 and learning outcomes, then a learning outcome is added 
	 *			 to the corresponding indicator.
	 *  @param indicator Represents an indicator of type IndicatorT.
	 *	@param outcome Represents a learning outcome of type LOsT.
	 */
	public void addLO(IndicatorT indicator, LOsT outcome){
		if(m.containsKey(indicator)){
			m.get(indicator).add(outcome);
		}

	}

	/**
	 *	@brief Deletes a learning outcome of the corresponding indicator.
	 *	@details If the indicator is contained in the map of indicators 
	 *			 and learning outcomes, then the outcome is deleted in the 
	 *			 corresponding indicator.
	 *  @param indicator Represents an indicator of type IndicatorT.
	 *	@param outcome Represents a learning outcome of type LOsT.
	 */
	public void delLO(IndicatorT indicator, LOsT outcome){
		if(m.containsKey(indicator)){
			m.get(indicator).remove(outcome);
		}

	}

	/**
	 *	@brief Checks if the learning outcomes of an indicator is a member of outcomes.
	 *	@details The indicator is a member if each of its learning outcomes is found in
	 *			 outcomes and each outcomes is found in the sequence of learning outcomes
	 *			 of that indicator.
	 *  @param indicator Represents an indicator of type IndicatorT.
	 *	@param outcomes Represents a sequence of learning outcomes of type LOsT.
	 *	@return A boolean representing if indicator and its learning outcomes are a member.
	 */
	public boolean member(IndicatorT indicator, LOsT[] outcomes){
		List<LOsT> outcomesList = Arrays.asList(outcomes);
		if(m.containsKey(indicator)){
			for(LOsT x : m.get(indicator)){
				if(!outcomesList.contains(x)){
					return false;
				}
			}
			for(LOsT out: outcomes){
				if(!m.get(indicator).contains(out)){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 *	@brief Implements the measure interface
	 *	@throws UnsupporedOperationException throws UnsupportedOperationException 
	 *			if no parameter of type IndicatorT or AttributeT was given. Not 
	 *			all signatures are valid for every subclass.
	 *	@return A sequence of doubles.
	 */
	public double[] measures(){
		throw new UnsupportedOperationException("No argument of type IndicatorT or AttributeT was given. Invalid signature for subclass.");
	}

	/**
	 *	@brief Implements the measure interface with IndicatorT parameter.
	 *	@param  ind Represents an indicator of type IndicatorT.
	 *	@return A sequence of doubles.
	 */
	public double[] measures(IndicatorT ind){
		double[] measureInd = {0,0,0,0};

		if (this.getLOs(ind).length == 0){
			return measureInd;
		}

		for(LOsT o : this.getLOs(ind)){
			measureInd = sumMeas(o.measures(), measureInd);
		}

		if (Norm.getNInd()){
			return Services.normal(measureInd);
		}
		else{
			return measureInd;
		}
	}

	/**
	 *	@brief Implements the measure interface with AttributeT parameter.
	 *	@param  att Represents an attribute of type AttributeT.
	 *	@return A sequence of doubles.
	 */
	public double[] measures(AttributeT att){

		double[] measureInd = {0,0,0,0};
	
		if (att.getIndicators().length == 0){
			return measureInd;
		}

		for(IndicatorT i : att.getIndicators()){
			measureInd = sumMeas(this.measures(i), measureInd);
		}

		if (Norm.getNAtt()){
			return Services.normal(measureInd);
		}
		else{
			return measureInd;
		}
	}

	/**
	 *	@brief A helper methods that reduces the sum of two sequence of doubles.
	 *	@param a Represents a sequence of doubles.
	 *	@param b Represents a sequence of doubles.
	 *	@return A sequence of doubles representing the sum.
	 */
	private double[] sumMeas(double[] a, double[] b){
		double[] sum = new double[a.length];		
		for(int i=0; i<a.length; i++){
			sum[i] = a[i] + b[i];
		}
		return sum;
	}

}
	


