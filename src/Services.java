package src;

public class Services{

	public static double[] normal(double[] v){
		double[] L = new double[v.length];
		double sum = 0;
		for(double elem : v){
			sum += elem;
		}

		for(int i=0; i < v.length; i++){

			L[i] = v[i] / sum;
		}
		return L; 
	}
}
