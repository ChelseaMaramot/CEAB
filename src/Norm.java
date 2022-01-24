package src;


public class Norm{

	//State Variables? protected or private
	private static Boolean normLOs;
	private static Boolean normInd;
	private static Boolean normAtt;

	//abstract Object
	public static void setNorms(Boolean nLOs, Boolean nInd, Boolean nAtt){
		normLOs = nLOs;
		normInd = nInd;
		normAtt = nAtt;
	}

	public static boolean getNLOs(){
		return normLOs;
	}

	public static boolean getNInd(){
		return normInd;
	}

	public static boolean getNAtt(){
		return normAtt;
	}

	public static void setNLOs(Boolean nLOs){
		normLOs = nLOs;
	}

	public static void setNInd(Boolean nInd){
		normInd = nInd;
	}

	public static void setNAtt(Boolean nAtt){
		normAtt = nAtt;
	}

}