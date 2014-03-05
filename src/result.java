import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class result extends JFrame {

	/**
	 * 
	 */
	result() {
		init();
		drawing.findPathNode(Graph.retPathNode());
	}

	private static final long serialVersionUID = -5433482859685468451L;

	JFrame frame = new JFrame("Output Frame");
	JTextArea adjMatrix;
	static JPanel mypanel = new JPanel();
	String text = "Test";
	JTextField jb = new JTextField(text, 5);
	JButton button2 = new JButton("add node");
	JTextField jt = new JTextField(text, 5);
	static JButton button3 = new JButton("add edge");
	static JButton button1 = new JButton("matrix");
	// /static JFrame frame = new JFrame("[=] There's a JPanel in here! [=]");
	JTextArea storyArea, pathArea;
	static JLabel adjMatrixLabel, sPath;
	String strMatrix, path;

	public final void init() {
		strMatrix = Dijkstra.getMatrix();
		path = Dijkstra.getPath();
		pathArea = new JTextArea(path, 5, 30);
		storyArea = new JTextArea(strMatrix, 5, 30);
		adjMatrixLabel = new JLabel("THe Adjacency Matrix is : ");

		sPath = new JLabel("The shortest path followed is :");
		sPath.setBounds(255, 10, 300, 100);
		System.out.println(strMatrix);

		pathArea.setEditable(false);
		pathArea.setBackground(Color.CYAN);
		pathArea.setLayout(new BorderLayout());
		pathArea.setBounds(250, 80, 230, 199);
		// String str = "The Adjaceny Matrix for the given Graph is : \n";

		adjMatrixLabel.setBounds(50, 10, 1000, 10);
		storyArea.setEditable(false);
		storyArea.setBounds(20, 40, 200, 200);
		storyArea.setBackground(Color.CYAN);
		storyArea.setLayout(new BorderLayout());
		// /draw d = new draw();
		setLayout(null);
		mypanel.setBackground(Color.GRAY);
		mypanel.setSize(700, 700);
		mypanel.setLayout(null);
		mypanel.setBounds(100, 0, 750, 500);
		mypanel.setOpaque(true);
		mypanel.setSize(500, 500);
		// mypanel.add(button1);
		mypanel.add(storyArea);
		mypanel.add(adjMatrixLabel);
		mypanel.add(sPath);
		mypanel.add(pathArea);
		add(mypanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		// /*
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				result ex = new result();
				ex.setVisible(true);
			}
		});
		// */
	}
}
