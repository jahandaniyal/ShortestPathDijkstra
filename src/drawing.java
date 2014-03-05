import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class drawing extends JPanel {

	int i = 0, matrix[][] = new int[10][10];
	// public boolean[] line;
	public Object g2;

	static int node[][] = new int[10][3], firstNode = 0, nodeNo = 0,
			edge[][] = new int[10][7];
	static int pathNode[][] = new int[10][2];
	private static int count_nodes = 0;
	private static int count_edges = 0, edgeCount = 0, px = 0, py = 0, m = 0;
	private static boolean drawvertex;
	static int xpos[] = new int[20], pathPosOrder[];
	private static int ypos[] = new int[20];
	private static int lx[] = new int[20];
	private static int ly[] = new int[20];
	private static int endlx[] = new int[20];
	private static int endly[] = new int[20];
	static boolean flagb1, flagb2, flagPath[] = new boolean[10];
	private static final long serialVersionUID = 3741286875336696164L;
	public static final boolean[] line = null;
	static String alphabet[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J" };
	static int HighlightNodeNo = 0;

	// display

	static boolean nearNode1(int x, int y) {

		int i, k = 0;
		boolean flag = false;
		for (i = 0; i < 10; i++) {
			if ((x >= (node[i][1] - 10) && x < (node[i][1] + 10 + 30))
					&& (y >= (node[i][2] - 10) && y < (node[i][2] + 30 + 10))) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	static int[] node1(int x, int y) {
		int i, k = 0;
		for (i = 0; i < 10; i++) {
			if ((x >= (node[i][1] - 10) && x < (node[i][1] + 10 + 30))
					&& (y >= (node[i][2] - 10) && y < (node[i][2] + 30 + 10))) {
				edge[edgeCount][k++] = i;
				edge[edgeCount][k++] = node[i][1];
				edge[edgeCount][k] = node[i][2];
				break;
			}
		}
		return edge[edgeCount];
	}

	static int[] node2(int x, int y) {
		int i, k = 3;
		for (i = 0; i < 10; i++) {
			if ((x >= (node[i][1] - 10) && x < (node[i][1] + 10 + 30))
					&& (y >= (node[i][2] - 10) && y < (node[i][2] + 30 + 10))) {
				edge[edgeCount][k++] = node[i][1];
				edge[edgeCount][k++] = node[i][2];
				edge[edgeCount][k++] = i;
				break;
			}
		}

		return edge[edgeCount];
	}

	static boolean nearNode(int x, int y, int pos) {
		int k = 0, i = 0;
		boolean flag = false, dup = false;
		if (pos == 1)
			k = 0;
		else if (pos == 2)
			k = 3;
		for (i = 0; i < 10; i++) {
			if ((x >= (node[i][1] - 10) && x < (node[i][1] + 10 + 30))
					&& (y >= (node[i][2] - 10) && y < (node[i][2] + 30 + 10))) {
				flag = true;
				edge[edgeCount][k++] = node[i][1] + 15;
				edge[edgeCount][k++] = node[i][2] - 15;
				edge[edgeCount][k] = i;
				setX(node[i][1] + 15);
				setY(node[i][2] - 15);

				if (pos == 1) {

					m = i;
					// System.out.println("Pos : " + pos + "  M : " +m);
				}
				break;
			}
		}
		if (pos == 2) {
			// for(int j=0;j<10;j++)
			{
				// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"+m+" 2nd node : "
				// + i);
				if (!(edge[m][2] == 0 || edge[i][5] == 0)) {
					System.out.println("Duplicate Edge not Allowed");
					dup = true;
					// break;
				}
				if (!(edge[m][5] == 0 || edge[i][2] == 0)) {
					System.out.println("Duplicate Edge not Allowed");
					dup = true;
					// break;
				} else
					edgeCount++;
			}
			for (i = 0; i < 10; i++) {
				for (int j = 0; j < 6; j++)
					System.out.print(edge[i][j] + "   ");
				System.out.println("");
			}
		}
		if (dup == false)
			return flag;
		// else edgeCount++;
		return false;
	}

	static void setY(int j) {
		// TODO Auto-generated method stub
		px = j;
	}

	static void setX(int j) {
		// TODO Auto-generated method stub
		py = j;
	}

	public int gX() {
		return px;
	}

	public int gY() {
		return py;
	}

	static boolean vicinity(int x1, int y1, int x2, int y2) {
		drawing dg = new drawing();
		// System.out.println("X1 : " + x1 + "\t X2 : " +x2+ "y1 : " + y1 +
		// "\t y2 : " + y2 );
		boolean flag1 = false, flag2 = false;
		for (int i = 0; i < 10; i++) { // System.out.println("xpos["+i+"]" +
										// xpos[i] + "\t ypox["+i+"]" +
										// dg.getYpos()[i]);
			if ((x1 >= (xpos[i] - 10) && x1 < (xpos[i] + 10 + 30))
					&& (y1 >= (dg.getYpos()[i] - 10) && y1 < (dg.getYpos()[i] + 30 + 10))) {
				flag1 = true;
			}
			if ((x2 >= (xpos[i] - 10) && x2 < (xpos[i] + 10 + 30))
					&& (y2 >= (dg.getYpos()[i] - 10) && y2 < (dg.getYpos()[i] + 30 + 10)))
				flag2 = true;
		}
		// System.out.println("FLag1 : " + flag1 +"\t Flag2 : " + flag2);
		if (flag1 && flag2)
			return flag1;
		return false;
	}

	static boolean vicinityNode(int x, int y) {
		boolean flag = false, near = false;
		for (int i = 0; i < 10; i++) {
			// System.out.println("Vicinity Node !");
			// System.out.println("\n x = " + x + "    y = "+y);
			if (((x >= (node[i][1] - 50) && x < (node[i][1] + 10 + 30)) && (y >= (node[i][2] - 50) && y < (node[i][2] + 30 + 10)))
					|| firstNode == 0) {
				near = true;
				firstNode = 1;
				break;
			}
		}
		if (!near) {
			node[nodeNo][0] = 1;
			node[nodeNo][1] = x;
			node[nodeNo][2] = y;
			flag = true;
			System.out.println("Marked " + nodeNo + "\n x = " + x + "    y = "
					+ y);
			firstNode = 1;
			nodeNo++;
		}
		return flag;
	}

	public void paint(Graphics g) {
		// System.out.println("Painting!");
		// /System.out.println("count in Drawing : " + count);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);

		for (int i = 0; i < getCount_edges(); i++) {
			// if(lx[i]!=0 && endlx[i] != 0 && ly[i]!=0 && endly[i] != 0)
			{
				// System.out.println("lx["+i+"]"+ getLx()[i] + "     ly["+i+"]"
				// +getLy()[i] + "    endlx["+i+"]"+getEndlx()[i]+
				// "   endly["+i+"]"+ getEndly()[i]);
				g2d.drawLine(getLx()[i], getLy()[i], getEndlx()[i],
						getEndly()[i]);
			}
		}

		for (i = 0; i < getCount_nodes(); i++) {
			g2d.setColor(Color.BLUE);

			if (isDrawvertex()) {
				g2d.fillOval(xpos[i] - 15, getYpos()[i] - 15, 28, 28);
				g2d.setColor(Color.WHITE);
				g2d.drawString(alphabet[i], xpos[i] - 5, getYpos()[i] + 5);
			}
		}
		/*
		 * Highlighting Nodes
		 */
		int pos = -1;

		if (HighlightNodeNo != pathNode.length)
			for (int i = 0; i < HighlightNodeNo; i++) {
				g2d.setColor(Color.RED);
				// if(i==0 || i==1) pathPosOrder =
				// findPathNode(Graph.retPathNode());
				g2d.fillOval(edge[pathPosOrder[i]][1] - 15,
						edge[pathPosOrder[i]][2] - 15, 28, 28);
				g2d.setColor(Color.WHITE);
				g2d.drawString(alphabet[pathPosOrder[i]],
						edge[pathPosOrder[i]][1] - 5,
						edge[pathPosOrder[i]][2] + 5);
				g2d.setColor(Color.GREEN);
				g2d.drawLine(edge[pathPosOrder[i]][1],
						edge[pathPosOrder[i]][2], edge[pathPosOrder[i]][3],
						edge[pathPosOrder[i]][4]);
				if (i == HighlightNodeNo - 1) {
					g2d.setColor(Color.GREEN);
					// if(i==0 || i==1) pathPosOrder =
					// findPathNode(Graph.retPathNode());
					g2d.fillOval(edge[pathPosOrder[i]][3] - 15,
							edge[pathPosOrder[i]][4] - 15, 28, 28);
					g2d.setColor(Color.WHITE);
					g2d.drawString(alphabet[pathPosOrder[i]],
							edge[pathPosOrder[i]][3] - 5,
							edge[pathPosOrder[i]][4] + 5);
					break;

				}

			}
	}

	public static int setHighlightNodeNo(int x) {
		HighlightNodeNo = x;
		return HighlightNodeNo;
	}

	public static int[] findPathNode(int pathN[][]) {
		System.out.println("PathN");
		for (int j = 0; j < pathN.length; j++)
			System.out.println(pathN[j][0] + "\t" + pathN[j][1] + "\t");
		// System.out.println("");
		int pos[] = new int[pathN.length];
		int i = -1, x = 0;
		for (x = 0; x < pathN.length; x++)
			for (i = 0; i < edge.length; i++) {
				System.out.println(edge[i][0] + "\t" + edge[i][5] + "\t"
						+ " pathN " + pathN[x][0] + "\t" + pathN[x][1] + "\t");
				if (pathN[x][0] == edge[i][0] && pathN[x][1] == edge[i][5]) {
					pos[x] = i;
					System.out.println("Pos---------------- : " + i);
					break;
				}
			}
		pathPosOrder = pos;
		for (int j = 0; j < pos.length; j++)
			System.out.println(pos[j] + "\t");
		System.out.println("");
		return pos;
	}

	public void paintPathNode(Graphics g) {

		System.out.println("pathnode.-----------------");
		Graphics2D g2d = (Graphics2D) g;
		int pos = 0;
		boolean flag = false;

		for (i = 0; i < flagPath.length && flagPath[i] != false; i++) {
			g2d.setColor(Color.RED);

			for (; pos < node.length; pos++)
				if (pathNode[i][0] == node[pos][1]
						&& pathNode[i][1] == node[pos][2]) {
					flag = true;
					break;
				}
			if (flag && flagPath[i]) {
				g2d.fillOval(node[pos][1], node[pos][2], 28, 28);
				g2d.setColor(Color.WHITE);
				g2d.drawString(alphabet[pos], node[pos][1] - 5,
						node[pos][2] + 5);
			}
			pos = 0;
			flag = false;
			repaint();
		}

	}

	public static void setFlagPath(int i) {
		flagPath[i] = true;
	}

	int retNodeX(int i) {
		return node[i][1];
	}

	int retNodeY(int i) {
		return node[i][2];
	}

	void callrepaint() {
		// System.out.println("Repainting ");

		repaint();
	}

	void setcountNodes(int value) {
		setCount_nodes(value);
		// System.out.println("count : " + getCount_nodes());
	}

	void setcountEdges(int value) {
		setCount_edges(value);
		// System.out.println("count : " + getCount_edges());
	}

	void setxpos(int pos, int value) {
		xpos[pos] = value;
		// System.out.println("xpos in drawing : " + xpos[pos] );
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		paint(g);
		paintPathNode(g);
		callrepaint();
	}

	public int[] getEndlx() {
		return endlx;
	}

	public void setEndlx(int endlx[]) {
		drawing.endlx = endlx;
	}

	static void getEdgeLength(int len) {
		edge[edgeCount][6] = len;
		System.out.println("Edge Matrix \n ");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++)
				System.out.print(edge[i][j] + "   ");
			System.out.println("");
		}
		edgeCount++;
	}

	int getmidPosEdgex(int x) {
		return x;
	}

	int getmidPosEdgey(int y) {
		return y;
	}

	public int getCount_edges() {
		return count_edges;
	}

	public void setCount_edges(int count_edges) {
		drawing.count_edges = count_edges;
	}

	public int[] getLx() {
		return lx;
	}

	public void setLx(int lx[]) {
		drawing.lx = lx;
	}

	public int[] getEndly() {
		return endly;
	}

	public void setEndly(int endly[]) {
		drawing.endly = endly;
	}

	public int[] getLy() {
		return ly;
	}

	public void setLy(int ly[]) {
		drawing.ly = ly;
	}

	public static boolean isDrawvertex() {
		return drawvertex;
	}

	public void setDrawvertex(boolean drawvertex) {
		drawing.drawvertex = drawvertex;
	}

	public int getCount_nodes() {
		return count_nodes;
	}

	public void setCount_nodes(int count_nodes) {
		drawing.count_nodes = count_nodes;
	}

	public int[] getYpos() {
		return ypos;
	}

	public static void setYpos(int ypos[]) {
		drawing.ypos = ypos;
	}

	public static int[][] getEdgeMatrix() {
		// TODO Auto-generated method stub
		return edge;
	}

	public static int countNodes() {
		// TODO Auto-generated method stub
		return count_nodes;
	}
}