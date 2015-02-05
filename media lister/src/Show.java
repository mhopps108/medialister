
import java.util.*;

public class Show 
{
	private String name;
	private Map<String, Integer> seasons = new TreeMap<String, Integer>();
	private String quality;
	
	// constructor //
	public Show(String n, Map<String, Integer> s)
	{
		name = n;
		seasons = s;
		//quality = q;
	}
		
	// overridden toString //
	public String toString() {
		return "";
	}
	
	// return name //
	public String getName() {
		return name;
	}
	
	// set name //
	public void setName(String n) {
		name = n;
	}
	
	// return seasons map //
	public Map<String, Integer> getSeasons() {
		return seasons;
	}
	
	// set seasons //
	public void setSeasons(Map<String, Integer> s) {
		seasons = s;
	}
	
	// return quality //
	public String getQuality() {
		return quality;
	}
	
	// set quality // 
	public void setQuality(String q) {
		quality = q;
	}
	
}

