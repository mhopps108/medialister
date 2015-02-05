
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;


public class MLPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int WIDTH  = 300; 
    private int HEIGHT = 400;
    private JPanel buttonsPanel = new JPanel();
    private JButton tvButton   = new JButton("TV");
	//private JButton moviesButton   = new JButton("Movies");
	private JButton moviesButton = new JButton(retrieveIcon("moviesButton.jpg"));
	private JRadioButton movieInfoButton = new JRadioButton("All Info"); 
    private JRadioButton movieNamesButton = new JRadioButton("Names");
    private static String dir = "";
    private JLabel dirLabel = new JLabel("");
    
    private JButton openFileButton = new JButton("Open File");
    private static String fileOut = "";
    private File defaultSaveLoc = new File("/Users/MHopps/Desktop/");

	// constructor //
	public MLPanel() 
	{
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.GREEN.darker());
        
        //buttonsPanel.setPreferredSize(new Dimension(250, 50));
		//buttonsPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //buttonsPanel.setLocation(50, 50);
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(tvButton);
        buttonsPanel.add(moviesButton);
        
        //buttonsPanel.add(openFileButton);
        
        // options listener for buttons
        MLOptionListener optionListener = new MLOptionListener();
        tvButton.addActionListener(optionListener);
        moviesButton.addActionListener(optionListener);
        movieInfoButton.addActionListener(optionListener);
        movieNamesButton.addActionListener(optionListener);
        
        openFileButton.addActionListener(optionListener);	
        openFileButton.setVisible(false);							// hide button til u have a file to read
        
        // group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(movieInfoButton);
        group.add(movieNamesButton);
        movieInfoButton.setSelected(true);
        
        // put the radio buttons in a column in a panel
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(movieInfoButton);
        radioPanel.add(movieNamesButton);
        radioPanel.setOpaque(false);
        
        add(buttonsPanel);
        add(radioPanel, BorderLayout.LINE_START);
        //setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(dirLabel);
        
        add(openFileButton);
        
        //dirLabel.setText("SOMETHING");
        //dirLabel.setHorizontalAlignment(alignment);
        dirLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        //JButton jb = new JButton(retrieveIcon("moviesButton.jpg"));
        //add(jb);
	}
	
	
	public static ImageIcon retrieveIcon(String path)
	{
	    return new ImageIcon(MLPanel.class.getResource(path));
	}
	
	//
	public static void setDir(String s) {
		dir = s;
	}
	
	//
	public static String getDir() {
		return dir;
	}
	
	//
	public static void setFileOut(String s) {
		fileOut = s;
	}
	
	//
	public static String getFileOut() {
		return fileOut;
	}
	
	
	
	//
	public void saveFileLocation()
	{
		JFileChooser chooser = new JFileChooser();
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setCurrentDirectory(defaultSaveLoc);
	    
	    int option = chooser.showSaveDialog(null);
	    if (option == JFileChooser.APPROVE_OPTION)
	    {
	        // save file here
	    	fileOut = chooser.getSelectedFile().getAbsolutePath() + ".txt";
	    	System.out.println("SAVE FILE: " + fileOut);
	    	//dirLabel.setText("File Loc: " + fileOut);
	    }
	}
	
	//
	public void chooseReadFolder()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("/Volumes"));
		chooser.setDialogTitle("Select Folder");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
			setDir(chooser.getSelectedFile().getAbsolutePath());
			//System.out.println("getCurrentDirectory(): " +  chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());
			dirLabel.setText("Read: " + chooser.getSelectedFile().getAbsolutePath());
		}
		else {
			System.out.println("No Selection");
		}
		
	}
	
	
	// action listener for panel buttons //
    private class MLOptionListener implements ActionListener
    {   
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource() == tvButton) 						// tv button
            {
				System.out.println("TV Button Pressed");
				chooseReadFolder();
				saveFileLocation();
				new TVShows();
            }
			if (e.getSource() == moviesButton) 					// movies button
            {
				chooseReadFolder();
				saveFileLocation();
				if (movieInfoButton.isSelected()) {				// movie info selected
					new Movies("info");
				} 
				else if (movieNamesButton.isSelected()) {		// movie names selected
					new Movies("name");
				}
				openFileButton.setVisible(true);
            }
			if (e.getSource() == openFileButton)
			{
				openTxtFile(fileOut);
			}
		}
    }

    //
    public static boolean askToViewFile()
    {
    	int opt = JOptionPane.showConfirmDialog(null, "View Txt File?", "Message", JOptionPane.YES_NO_OPTION);
		if (opt == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
    }
    
    // open txt file with text editor //
 	public static void openTxtFile(String fileOut)
 	{
 		File file = new File(fileOut);
 		try {
 			Desktop.getDesktop().open(file);
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 	}
}
    