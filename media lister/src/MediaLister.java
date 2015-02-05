
import javax.swing.JFrame;

public class MediaLister extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	// constructor //
	public MediaLister() 
	{		
		JFrame mediaFrame = new JFrame("Media Lister");
		mediaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //mediaFrame.setBackground(Color.GREEN.darker().darker());
		mediaFrame.setResizable(false);
		mediaFrame.setLocationRelativeTo(null);
		mediaFrame.getContentPane().add(new MLPanel());
		mediaFrame.pack();
		mediaFrame.setVisible(true);
	}
	
	// main //
	public static void main(String[] args) 
	{
		new MediaLister();
	}
	
}