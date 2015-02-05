
import java.io.*;
import java.util.*;

public class Movie 
{
	private String name;
	private String quality;
	private String size;
	public static final String sFmt = "%-57s %-16s %6s";
	
	// constructor //
	public Movie(File f) 
	{
		String n = f.getName();	
		
		if (getQuality(n) != "") {  
			name = n.substring(0, n.indexOf('(')-1);  
		}
		else {  
			name = n.substring(0, n.length()-(getExt(n).length()+1));
		}
	
		quality = getQuality(f.getName());
		size = getFileSize(f);	
	}
	
	
	// gets the quality of the movie //
	public String getQuality(String s)
	{
		String quality = "";
		int i = s.indexOf('(');
		int j = s.lastIndexOf(')');
		
		if (i > 0 && j < s.length()-1)
		{
			quality = s.substring(i+1, j);
		}
			
		return quality;
	}
	
	// gets file extention //
	public String getExt(String s)
	{
		String extension = "";

		int i = s.lastIndexOf('.');
		if (i >= 0) {
		    extension = s.substring(i+1);
		}

		return extension;
	}
	
	
	// checks if 
	public static boolean checkExt(String s)
	{
		// s = getExt(s);  // ??
		int i = s.lastIndexOf('.');
		if (i >= 0) {
		    s = s.substring(i+1);
		}
		
		if (s.equals("mp4") || s.equals("mkv") || s.equals("avi") || s.equals("m4v")) {
			return true;
		}	
		return false;
	}

	// gets file size in gigabytes //
	public String getFileSize(File f)
	{
		double bytes = f.length();
		double kilobytes = (bytes / 1024);
		double megabytes = (kilobytes / 1024);
		double gigabytes = (megabytes / 1024);
		
		return String.format("%4.2f", gigabytes);
	}
	
	
	// compare 2 movies for sorting //
	public static Comparator<Movie> MovieNameComparator = new Comparator<Movie>() {
		@Override
		public int compare(Movie m1, Movie m2) 
		{
			String movie1 = m1.getName().toUpperCase();
			String movie2 = m2.getName().toUpperCase();
			return movie1.compareTo(movie2);
		}
    };
    
	
    // overridden toString //
	public String toString() {
		return String.format(sFmt, name, quality, size);
	}
	
	// different toString //
	public String toStringNames() {
		return String.format(name);
	}
	
	// returns name //
	public String getName() {
		return name;
	}
	
	// returns quality // 
	public String getQuality() {
		return quality;
	}
	
	// returns size // 
	public String getSize() {
		return size;
	}

	// set name //
	public void setName(String n) {
		name = n;
	}
	
	// set quality //
	public void setQuality(String q) {
		quality = q;
	}
	
	// set size //
	public void setSize(String s) {
		size = s;
	}
}
