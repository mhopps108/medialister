
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;


public class Movies 
{
	//private static String fileOut = "/Users/MHopps/Desktop/movie list.txt";		// movies
	//private static String moviesDirectory = "/Volumes/Movies"; 
	//private static final String sFmt = "%-60s %-30s %10s";
	private List<Movie> myMovies = new ArrayList<>();
	
	
	// constructor //
	public Movies(String s) 
	{
		
		//listDir(moviesDirectory);
		listDir(MLPanel.getDir());
		
		if (!myMovies.isEmpty())
		{
			Collections.sort(myMovies, Movie.MovieNameComparator);
			
			if (s.equals("info")) {
				writeMoviesWithInfo();
			}
			else if (s.equals("name")) {
				writeMovieNames();
			}		
			
			/*
			if (MLPanel.askToViewFile()) {
				MLPanel.openTxtFile(fileOut);
			}
			*/
		}
	}
		
	
	//
	public String getFileOut()
	{
		return MLPanel.getFileOut();
	}

	//
	public void listDir(String directoryName)
	{
        File directory = new File(directoryName);
        System.out.println(directoryName);

        if (directory.exists())
        {
        	//System.out.println("dir exisit");
            File[] fList = directory.listFiles();		// get all the files from a directory
            //System.out.println("fList lenght = " + fList.length);
            
            for (File f : fList) {
            	checkFile(f);
            }
        }
        else {
        	JOptionPane.showMessageDialog (null, "Could Not Find Location.");
        }
    } 
	
	
	//
	public void checkFile(File f)
	{
		if (f.isFile() && !f.isHidden())
    	{
			if (Movie.checkExt(f.getName()))
			{	
				Movie m = new Movie(f);	
				myMovies.add(m);
			}
    	}
    	else if (f.isDirectory())
    	{
    		listDir(f.getAbsolutePath());
    	}
	}
	
	
	// writes movies array to a file //
	public void writeMoviesWithInfo()
	{
		PrintWriter out = null; 
		try {
			out = new PrintWriter(new FileOutputStream(getFileOut()));
			out.println("--- List of Movies ---");
			out.println("Number of Movies: " + myMovies.size() + "\n");
			out.println(String.format(Movie.sFmt, "NAME", "QUALITY", "SIZE") + "\n");		// header
			
			for (Movie m : myMovies)
			{
				out.println(m);												// print all the movies
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		finally {
			out.close();
		}
	}
	
	// writes movies array to a file //
	public void writeMovieNames()
	{
		PrintWriter out = null;		
		try {
			out = new PrintWriter(new FileOutputStream(getFileOut()));
			out.println("--- List of Movies ---");
			out.println("Number of Movies: " + myMovies.size() + "\n");
			
			for (Movie m : myMovies)
			{
				out.println(m.toStringNames());																// print all the movies
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		finally {
			out.close();
		}
	}
	
}
