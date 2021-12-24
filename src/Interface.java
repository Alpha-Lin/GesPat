import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;


public class Interface extends JFrame
{
	
	public Interface(int profession) 
	{
		
		switch(profession)
		{
			case 1:
			{
				GridBagConstraints gbc = new GridBagConstraints(); 
		        
		        this.setLayout(new GridBagLayout());  
		        
		        setTitle("GesPat-Agent Administration");
		        
		        
		        
		        
		        
		        setVisible(true);  
		        setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
			
			case 2:
			{
				GridBagConstraints gbc = new GridBagConstraints(); 
		        
		        this.setLayout(new GridBagLayout());  
		        
		        setTitle("GesPat-M"+"\u00e9"+"decin"); 
		        
		        
		        
		        
		        setVisible(true);  
		        setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
			
			case 3:
			{
				GridBagConstraints gbc = new GridBagConstraints(); 
		        
		        this.setLayout(new GridBagLayout());  
		        
		        setTitle("GesPat-Technicien"); 
		        
		        
		        
		        
		        
		        setVisible(true);  
		        setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		}
		
		setSize(1500, 800);
		
	}

}
