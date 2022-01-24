package src;

public class LOsT implements Measures{

	private String name;
	private int n_blw;
	private int n_mrg;
	private int n_mts;
	private int n_exc;

	public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc){

		if (nblw < 0 || nmrg < 0 || nmts < 0 || nexc < 0){
			throw new IllegalArgumentException("One or more of the integer arguments is less than zero.");
		}
		if (nblw == 0 && nmrg == 0 && nmts == 0 && nexc == 0){
			throw new IllegalArgumentException("All of the integer arguments cannot be equal to zero.");
		}

		name = topic;
		n_blw = nblw;
		n_mrg = nmrg;
		n_mts = nmts;
		n_exc = nexc;

	}

	public String getName(){
		return name;
	}

	public boolean equals(LOsT o){
		return name == o.getName();
	}

	public double[] measures(){
		double[] n_seq = {n_blw, n_mrg, n_mts, n_exc};
		if (! Norm.getNLOs()){
			return n_seq;
		}
		return Services.normal(n_seq);
	}

	public double[] measures(IndicatorT ind){

		throw new UnsupportedOperationException("No indicator parameter should be given. Invalid signature for subclass");
	}

	public double[] measures(AttributeT att){
	
		throw new UnsupportedOperationException("No attribute parameter should be given. Invalid signature for subclass ");
	}

}