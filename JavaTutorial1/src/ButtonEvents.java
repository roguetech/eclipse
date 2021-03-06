import java.awt.*;
import java.awt.event.*;
	
@SuppressWarnings("serial")
public class ButtonEvents extends Frame implements ActionListener {
	private TextField f;
	
	public ButtonEvents() {
		super("Lower/Uppercase App");
		this.setLayout(new FlowLayout());
		
		f = new TextField("HELLO WORLD", 30);
		this.add(f);
		
		Button b1 = new Button("To Uppercase");
		b1.addActionListener(this);
		this.add(b1);
		
		Button b2 = new Button("To Lowercase");
		b2.addActionListener(this);
		this.add(b2);
		
		this.pack();	
		this.setVisible(true);
	}

	//@Override
	public void actionPerformed(ActionEvent e) {
		String text = f.getText();
		if (e.getActionCommand().equals("To Uppercase")) {
			System.out.println(text);
			f.setText(text.toUpperCase());
		} else if (e.getActionCommand().equals("To Lowercase")) {
			f.setText(text.toLowerCase());
		}
		
	}
	
	public static void main(String[] args) {
		//new ButtonEvents();
		new Multi();
	}

}
