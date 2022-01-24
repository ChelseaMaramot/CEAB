package src;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

public class AttributeT{

	private String name;
	private Set<IndicatorT> s;

	public AttributeT(String attribName, IndicatorT[] indicators){
		name = attribName;
		s =  new HashSet<IndicatorT>();
		for(IndicatorT elem: indicators){
			s.add(elem);
		}
	}

	public String getName(){ 
		return name;
	}

	public IndicatorT[] getIndicators(){
		IndicatorT seq[] = new IndicatorT[s.size()];
		return s.toArray(seq);
	}

}