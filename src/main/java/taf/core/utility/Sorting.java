package taf.core.utility;

import java.text.Collator;
import java.util.List;
import java.util.Locale;

public class Sorting 
{
	public static boolean isSortedAscending(List<String> list, int startIndex)
	{
		Collator collator = Collator.getInstance(new Locale("tr", "TR"));

		boolean sorted = true;        
	    for (int i = startIndex; i < list.size(); i++) 
	    {
	    	//System.out.println(list.get(i-1) +" vs "+list.get(i));
	    	//System.out.println(collator.compare(list.get(i-1), list.get(i)));
	        if (collator.compare(list.get(i-1), list.get(i)) > 0) sorted = false;
	    }

	    return sorted;
	}
	public static boolean isSortedDescending(List<String> list, int startIndex)
	{
		Collator collator = Collator.getInstance(Locale.getDefault());
		boolean sorted = true;        
	    for (int i = startIndex; i < list.size(); i++) 
	    {	    	
	        if (collator.compare(list.get(i-1), list.get(i)) < 0) sorted = false;
	    }

	    return sorted;
	}
}
