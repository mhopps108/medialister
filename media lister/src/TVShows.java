
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class TVShows 
{
	//private static String fileOut = "/Users/MHopps/Desktop/tv shows.txt";
	//private static String tvDirectory = "/Volumes/3TB/TV";
	//private static String tvDirectory = "/Users/MHopps/Dropbox/Media Lister Stuff/TV";
	private List<Show> myTV = new ArrayList<>();

	// constructor //
	public TVShows() 
	{
		//listDir(tvDirectory);
		listDir(MLPanel.getDir());
		
		if (!myTV.isEmpty())
		{
			writeTVWithInfo();
			
			/*
			if (MLPanel.askToViewFile()) {
				MLPanel.openTxtFile(fileOut);
			}
			*/
		}
	}
	
	
	//
	public static String getFileOut()
	{
		return MLPanel.getFileOut();
	}
	
	// 
	public void listDir(String directoryName)
	{
        File directory = new File(directoryName);

        if (directory.exists())
        {
            File[] fList = directory.listFiles();		// get all the files from a directory
            
            for (File f : fList) {
            	checkFile(f);
            }
        }
        else {
        	JOptionPane.showMessageDialog (null, "Could Not Find Location.");
        }
        
        //JOptionPane.showMessageDialog (null, "Completed");
    } 
	
	
	//
	public void checkFile(File f)
	{
		Map<String, Integer> s = new TreeMap<>();
		
		if (f.isDirectory() && !f.isHidden())
		{
			File dir = new File(f.getAbsolutePath());
			
			if (dir.listFiles().length <= 2)
			{
				File[] fList = dir.listFiles();
				
				for (File file : fList)
				{
					if (file.getName().equals(f.getName()))//&& !file.isHidden() )	// if folder name is found again
					{
						System.out.println("-------NESTED FOLDER -- " + file.getName());
						s = findSeasons(file);
					}
					else if (file.isDirectory() && !file.isHidden())
					{
						s = findSeasons(f);
					}
				}			
			}
			else {
				s = findSeasons(f);
			}
			
			Show show = new Show(f.getName(), s);
			myTV.add(show);
		}
	}
	
	
	//
	public Map<String, Integer> findSeasons(File f)
	{
		Map<String, Integer> s = new TreeMap<>();
		
		File dir = new File(f.getAbsolutePath());
		if (dir.exists())
		{
			File[] fList = dir.listFiles();
			
			for (File file : fList)
			{
				if (!file.isHidden() && !file.isFile()) 
				{
					String fName = file.getName();
					int i = fName.indexOf('(');
					if (i > 0) {  
						fName = fName.substring(0, i);  
					}
					else {  
						fName = file.getName();
					}
					
					s.put(fName, file.listFiles().length);
				}
			}
		}
		
		return s;
	}
	
	// writes movies array to a file //
	public void writeTVWithInfo()
	{
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileOutputStream(getFileOut()));
			out.println("--- List of TV ---");
			out.println("# of Shows: " + myTV.size());
			
			for (Show s : myTV)
			{
				out.println("\n--- " + s.getName());
				
				Set<String> keySet = s.getSeasons().keySet();
				for (String key : keySet)
				{
					Integer numEp = s.getSeasons().get(key);
					out.println(key + ": " + numEp + " episodes");
				}
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
