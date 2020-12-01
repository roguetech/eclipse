import java.awt.*;
import java.awt.event.*;

public class counter extends Frame implements ActionListener, WindowListener, AdjustmentListener{
	private Button b1, b2;
	private TextField t1;
	private Scrollbar sb;
	private MyCanvas c;
	//private int count;
	/**
	 * 
	 */
	private static final long serialVersionUID = -100861424542318402L;

	public counter() {
		super("Counter");
		this.setLayout(new BorderLayout());
		Panel p = new Panel(new FlowLayout());
		
		this.addWindowListener(this);
		b1 = new Button("Increment");
		p.add(b1);
		b1.addActionListener(this);
		t1 = new TextField(30);
		t1.setEditable(false);
		p.add(t1);
		b2 = new Button("Decrement");
		p.add(b2);
		b2.addActionListener(this);
		
		this.add(p, BorderLayout.NORTH);
		
		c = new MyCanvas(this);
		this.add(c, BorderLayout.CENTER);
		
		sb = new Scrollbar(Scrollbar.HORIZONTAL, 50, 10, 0, 110);
		this.add(sb, BorderLayout.SOUTH);
		sb.addAdjustmentListener(this);
		update();
		
		
		this.pack();
		this.setVisible(true);

	}
	
	public void incrementCountBy10() {
		this.sb.setValue(this.sb.getValue() + 10);
		this.update();
	}
	
	public void update(){
		t1.setText("The value is: " + sb.getValue());
		c.setCircleSize(sb.getValue());
	}

	@Override
	public void windowOpened(WindowEvent e) { }

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public void windowClosed(WindowEvent e) { }

	public void windowIconified(WindowEvent e) { }

	public void windowDeiconified(WindowEvent e) { }

	public void windowActivated(WindowEvent e) { }

	public void windowDeactivated(WindowEvent e) { }

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) {
			this.sb.setValue(sb.getValue()+1);
			update();
		} else if (e.getSource().equals(b2)) {
			this.sb.setValue(sb.getValue()-1);;
			update();
		}	
	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		//this.count = sb.getValue();
		update();
	}

}
