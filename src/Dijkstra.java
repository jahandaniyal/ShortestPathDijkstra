import java.io.*;

class Graph {
	private static final int MAXNODES = 10;
	private static final int INFINITY = 999;
	int n, i;
	int[][] weight = new int[MAXNODES][MAXNODES];
	int[] distance = new int[MAXNODES];
	int[] precede = new int[MAXNODES];
	static int pathNode[][] = new int[10][2];
	int edgeM[][];
	int source, dest;
	static String strMatrix = "", strPath = "";

	void buildSpanningTree(int source, int destination) {
		boolean[] visit = new boolean[MAXNODES];

		for (int i = 0; i < n; i++) {
			distance[i] = INFINITY;
			precede[i] = INFINITY;
		}
		distance[source] = 0;

		int current = source;
		while (current != destination) {
			int distcurr = distance[current];
			int smalldist = INFINITY;
			int k = -1;
			visit[current] = true;

			for (int i = 0; i < n; i++) {
				if (visit[i])
					continue;

				int newdist = distcurr + weight[current][i];
				if (newdist < distance[i]) {
					distance[i] = newdist;
					precede[i] = current;
				}
				if (distance[i] < smalldist) {
					smalldist = distance[i];
					k = i;
				}
			}
			current = k;
		}
	}

	int[] getShortestPath(int source, int destination) {
		int i = destination;
		int finall = 0;
		int[] path = new int[MAXNODES];

		path[finall] = destination;
		finall++;
		while (precede[i] != source) {
			i = precede[i];
			path[finall] = i;
			finall++;
		}
		path[finall] = source;
		// System.out.println();
		int[] result = new int[finall + 1];
		System.arraycopy(path, 0, result, 0, finall + 1);
		return result;
	}

	void displayResult(int[] path) {
		System.out.println("\nThe shortest path followed is : \n");
		for (int i = path.length - 1; i > 0; i--) {
			System.out.println("\t\t( " + path[i] + " ->" + path[i - 1]
					+ " ) with cost = " + weight[path[i]][path[i - 1]]);
			strPath = strPath + "( " + path[i] + " ->" + path[i - 1]
					+ " ) with cost = " + weight[path[i]][path[i - 1]] + "\n";
			pathNode[i][0] = path[i];
			pathNode[i][1] = path[i - 1];
		}
		strPath = strPath + "\nTotal distance\n" + distance[path[i]];
		System.out.println("total distance");
		System.out.println(distance[path[i]]);
		reversePathNode();
		result rs = new result();
	}

	static void reversePathNode() {
		int len = pathNode.length, j = 0, temp[][] = new int[len][2], pos = 0, k = 0;
		for (int i = len - 1; i > 0; i--, j++) {
			System.out.println("......." + pathNode[i][0] + "\t"
					+ pathNode[i][1] + "\t");
			temp[j][1] = pathNode[i][1];
			temp[j][0] = pathNode[i][0];
		}
		pathNode = temp;
		for (pos = 0; pos < len; pos++)
			if (pathNode[pos][0] != 0 || pathNode[pos][1] != 0) {
				temp[k][0] = pathNode[pos][0];
				temp[k][1] = pathNode[pos][1];
				k++;
				System.out.println("...+++..." + pathNode[pos][0] + "\t"
						+ pathNode[pos][1] + "\t");
			}
		pathNode = temp;
		for (int i = len - 1; i > 0; i--, j++) {
			System.out.println("......." + pathNode[i][0] + "\t"
					+ pathNode[i][1] + "\t");
		}
	}

	int getNumber(String msg) {
		int ne = 0;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("\n" + msg + " : ");
			ne = Integer.parseInt(in.readLine());
		} catch (Exception e) {
			System.out.println("I/O Error");
		}
		return ne;
	}

	void SPA() {
		// n = getNumber("Enter the number of nodes  in the matrix");
		// n = edgeM.length;
		boolean flag = false;
		System.out.println("length : " + n);
		System.out.print("\nEnter the cost matrix : \n\n");
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (i == j)
					weight[i][j] = 0;
				else {
					for (int k = 0; k < n; k++) {
						if (edgeM[k][0] == i && edgeM[k][5] == j) {
							weight[i][j] = edgeM[k][6];
							weight[j][i] = 999;
							flag = true;
							break;
						} else
							flag = false;
						// weight[i][j] = getNumber("Cost " + (i+1) + "--" +
						// (j+1));
					}
					if (flag == false)
						weight[i][j] = 999;
				}

			}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(weight[i][j] + "   ");
				if (weight[i][j] == 999)
					strMatrix = strMatrix + "\u221E    ";
				else
					strMatrix = strMatrix + weight[i][j] + "   ";
			}
			strMatrix = strMatrix + "\n";
			System.out.println("");
		}
		// int s = getNumber("Enter the source node");
		// int d = getNumber("Enter the destination node");
		int s = getSource();
		int d = getDest();
		buildSpanningTree(s, d);
		displayResult(getShortestPath(s, d));
	}

	private int getSource() {
		// TODO Auto-generated method stub
		return source;
	}

	private int getDest() {
		// TODO Auto-generated method stub
		return dest;
	}

	public static int[][] retPathNode() {
		// TODO Auto-generated method stub
		return pathNode;
	}

	public void setEdge(int[][] edgeMatrix) {
		// TODO Auto-generated method stub
		edgeM = edgeMatrix;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++)
				System.out.print(edgeMatrix[i][j] + "   ");
			System.out.println("");
		}
	}

	public void setN(int i) {
		// TODO Auto-generated method stub
		n = i;
	}

	public void setDest(int d) {
		// TODO Auto-generated method stub
		dest = d;
	}

	public void setSource(int s) {
		// TODO Auto-generated method stub
		source = s;
	}
}

public class Dijkstra {
	public Dijkstra(int EdgeMatrix[][], int i, int source, int dest) {
		System.out.print("\n Inside Dijkstra.java");
		Graph g = new Graph();
		g.setEdge(EdgeMatrix);
		g.setSource(source);
		g.setDest(dest);
		g.setN(i);
		g.SPA();
	}

	public static void main(String args[]) {
		// Graph g = new Graph();
		// g.SPA();
	}

	public static String getMatrix() {
		// TODO Auto-generated method stub
		return Graph.strMatrix;
	}

	public static String getPath() {
		// TODO Auto-generated method stub
		return Graph.strPath;
	}
}