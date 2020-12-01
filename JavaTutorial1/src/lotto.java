import java.awt.*;
import java.awt.event.*;

public class lotto extends Frame implements ActionListener {
	private TextField[] tx = new TextField[7];
	private Label[] l = new Label[7];
	
	public lotto() {
		super("Lotto");
		this.setLayout(new FlowLayout());
		
		for(int i=0; i <= l.length; i++) {
			l[i] = "1:";
		}
		
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
