package org.tatasu.gwt.client.kendogwt.grid.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class GridHashMapParser {
	
	private ArrayList<HashMap<String, Object>> data;
	
	/*public GridHashMapParser(ArrayList<HashMap<String, Object>> data) {
		if(data == null || data.size() == 0) {
			throw new NullPointerException("ArrayList<HashMap<String, Object>> data не может быть null или data.size() == 0");
		}
		this.data = data;
	}*/
	
	public static ArrayList<String> getKeysName(ArrayList<HashMap<String, Object>> data) {
		if(data == null || data.size() == 0) {
			throw new NullPointerException("ArrayList<HashMap<String, Object>> data не может быть null или data.size() == 0");
		}
		
		ArrayList<String> rez = new ArrayList<String>();
		HashMap<String, Object> ford = data.get(0);		
		
		Set<String> set = ford.keySet();

	    Iterator<String> iter = set.iterator();

	    while (iter.hasNext()) {
	    	rez.add(iter.next());
	    }
	    
		return rez;
	}
}
