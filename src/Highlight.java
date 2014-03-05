import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Highlight extends JFrame implements ActionListener {

	Timer timer;
	boolean flag;
	/*
	 * JLabel label[] = new JLabel[10];
	 */
	/*
	 * JLabel l[] = new JLabel[10];
	 */
	static int count = 0;

	private static final long serialVersionUID = -8674097175560663913L;
	Graph_Start cn = new Graph_Start();

	public Highlight() {

		initUI();

	}

	public final void initUI() {

		/*
		 * JPanel panel = new JPanel(); panel.setLayout(null);
		 * panel.setSize(500, 500);
		 * 
		 * //drw.setPreferredSize(new Dimension(100,100)); // drw.setBounds(0,
		 * 0, 400, 300);
		 */

		setTimer();

		// Generate 5 labels
		/*
		 * 
		 * 
		 * l[0] = drawLabel("Vertex 1 ",200,200,60,20); l[1] =
		 * drawLabel("Vertex 2 ",300,200,60,20); l[2] =
		 * drawLabel("Vertex 3 ",250,250,60,20);
		 * 
		 * 
		 * for(int i=0;i<3;i++) { l[i].setOpaque(true); panel.add(l[i]); }
		 * panel.add(drw); add(panel); pack(); start(); setSize(500, 500);
		 */
	}

	protected JLabel drawLabel(String st, int x, int y, int width, int height) {
		JLabel label = new JLabel(st);
		label.setBounds(x, y, width, height);
		label.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		return label;
	}

	protected void label_ChangeColor(JLabel label, String color) {
		label.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	}

	protected void setTimer() {
		System.out.println("Timer Set !");
		timer = new Timer(3000, this);
		flag = false;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("actionEvent !");
		flag = true;
		timer.stop();

		if (count < 3) {

			// drw.line[count] = true;
			/*
			 * l[count].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			 */
			/*
			 * l[count].setForeground(Color.red);
			 */
			// drw.g2.setColor(Color.BLUE);
			// repaint();
			count++;
			start();
		} else
			timer.stop();

	}

	public void start() {
		System.out.println("Timer Started !");
		timer.start();
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				// contain c=new contain();
				Highlight ex = new Highlight();
				ex.setVisible(true);
			}
		});
	}
}