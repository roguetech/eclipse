import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class Multi extends Frame implements ActionListener {
    private TextField t1,t2,t3;

    public Multi() {
        super("Multiplication App");
        this.setLayout(new FlowLayout());
        
        Label l1 = new Label("A: ");
        this.add(l1);
        t1 = new TextField("Num", 20);
        this.add(t1);
        
        Label l2 = new Label("B: ");
        this.add(l2);
        t2 = new TextField("Num", 20);
        this.add(t2);
        
        Label l3 = new Label("A * B = ");
        this.add(l3);
        t3 = new TextField("Answer", 30);
        this.add(t3);
        
        Button b1 = new Button("Calculate");
        b1.addActionListener(this);
        this.add(b1);
        
        
        this.pack();
        this.setVisible(true);
        
    }

	//@Override
	public void actionPerformed(ActionEvent e) {
		//String s1 = t1.getText();
		//String s2 = t2.getText();
		int i1 = Integer.parseInt(t1.getText());
		int i2 = Integer.parseInt(t2.getText());
		if (e.getActionCommand().equals("Calculate")) {
			System.out.println("test: ");
			int result = i1 * i2;
			t3.setText(Integer.toString(result));
		}
	}
}
