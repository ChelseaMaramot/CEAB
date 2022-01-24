package src;

import java.util.HashSet;

public class ProgramT extends HashSet<CourseT> implements Measures{
	private HashSet<CourseT> s = this;

	public double[] measures(){
		throw new UnsupportedOperationException("No argument was given. Invalid signature for subclass.");
	}

	public double[] measures(IndicatorT ind){
		throw new UnsupportedOperationException("Argument of type IndicatorT was given. Invalid signature for subclass.");
	}

	public double[]  measures(AttributeT att){
		double[][] course = new double[s.size()][4]; 
		double[] sum = {0,0,0,0};
		int i = 0;

		for (CourseT c: s){
			course[i] = c.measures(att);
			i++;
		}

		for(double[] c_seq : course){
			sum = sumMeas(sum, c_seq);
		}
		return Services.normal(sum);
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

