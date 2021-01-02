package visualizer4j.math;

import java.util.ArrayList;

import visualizer4j.utils.MoreArrays;

public class MooreBound {
	
	
	/**
	 * This methods returns the average distance between the nodes of a
	 * generalized moore graph that would have at least <code>numberOfNode</code>
	 * nodes and maximum (and uniform) degree <code>radix</code>. Note that
	 * the method does not check if such a graph exists.
	 * @param radix
	 * @param numberOfNodes
	 * @return
	 */
	public static double getMinimalDistance(int radix, int numberOfNodes) {
		int totalNodes = numberOfNodes;
		long totalDistances = 0;
		int i = 0;
		while(totalNodes > 0) {
			int n = Math.min(totalNodes, getNodesInLayerI(i, radix));
			totalDistances += n*i;
			totalNodes -= n;
			i++;
		}
		return (double)totalDistances/(double)(numberOfNodes-1);	
	}
	
	public static double[] getMinimalDistanceAndDiameter(int radix, int numberOfNodes) {
		int totalNodes = numberOfNodes;
		long totalDistances = 0;
		int i = 0;
		while(totalNodes > 0) {
			int n = Math.min(totalNodes, getNodesInLayerI(i, radix));
			totalDistances += n*i;
			totalNodes -= n;
			i++;
		}
		return new double[]{(double)totalDistances/(double)(numberOfNodes-1), (double)i-1};	
	}	

	private static int getNodesInLayerI(int i, int radix) {
		if (i == 0) {
			return 1;
		}
		if (i == 1) {
			return radix;
		}
		return (int)Math.pow(radix-1, i-1)* radix;
	}
	
	public static void main(String[] args) {
		// petersen
		System.out.println(getMinimalDistance(3, 10));
		// hoffman
		System.out.println(getMinimalDistance(7, 50));
		// exascale 5d torus
		System.out.println(getMinimalDistance(10, 100000));
	}

	public static int[] getNodesInLayers(int radix, int nodes) {
		// right now, copy paste from other method - to be improved
		int totalNodes = nodes;
		int i = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(totalNodes > 0) {
			int n = Math.min(totalNodes, getNodesInLayerI(i, radix));
			list.add(n);
			totalNodes -= n;
			i++;
		}
		return MoreArrays.toIntArray(list);
	}

}
