package visualizer4j.utils;

import java.util.List;

import visualizer4j.path.Path;

public class NodePair implements Comparable<NodePair> {
	private int startNode;
	private int endNode;
	public int getStartNode() {
		return startNode;
	}
	public int getEndNode() {
		return endNode;
	}
	public NodePair(int start, int end) {
		startNode = start;
		endNode = end;
	}
	@Override
	public String toString() {
		return "Connection : " +
		startNode + "-" +
		endNode;
	}

	public static Path toPath(List<NodePair> pairs) {
		Path p = new Path();
		if (pairs.size() <= 0) {
			throw new IllegalArgumentException("Cannot create a path with 0 pairs");
		}
		p.add(pairs.iterator().next().getStartNode());
		for (NodePair pair : pairs) {
			p.add(pair.getEndNode());
		}
		return p;
	}

	public void reverse() {
		int i = startNode;
		startNode = endNode;
		endNode = i;
	}
	
	public NodePair reverseNew() {
		return new NodePair(endNode, startNode);
	}

	public int compareTo(NodePair p) {
		if (this.startNode < p.startNode) {
			return -1;
		} else if (this.startNode > p.startNode) {
			return 1;
		} else if (this.endNode < p.endNode) {
			return -1;
		} else if (this.endNode > p.endNode) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean equals(NodePair p) {
		return (p.startNode == this.startNode) && (p.endNode == this.endNode);
	}

	public boolean equals(NodePair p, boolean directed) {
		if (directed) {
			return equals(p);
		}
		return equals(p) || ((p.startNode == this.endNode) && (p.endNode == this.startNode));
	}
}
