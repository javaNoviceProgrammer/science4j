package visualizer4j.statistics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import visualizer4j.results.DataPoint;
import visualizer4j.results.Execution;
import visualizer4j.utils.Pair;

import umontreal.iro.lecuyer.probdist.EmpiricalDist;

public class StatisticalDistribution<K extends Comparable<K>> implements Iterable<Map.Entry<Object, Integer>> {
	
	public static class DoubleDistribution extends StatisticalDistribution<Double> {
		public void add(double[] tab) {
			for (int i = 0 ; i < tab.length ; i++) {
				super.add(tab[i]);
			}
		}
	}
	
	public static class IntegerDistribution extends StatisticalDistribution<Integer> {
		public IntegerDistribution(int[] tab) {
			super();
			add(tab);
		}
		
		public void add(int[] tab) {
			for (int i = 0 ; i < tab.length ; i++) {
				super.add(tab[i]);
			}
		}		
	}
	
	private TreeMap<Object, Integer> struct;

	boolean hasValue = false;

	private int samples = 0;


	private int buckets = -1;
	private double min = Double.NaN;
	private double diff;
	private double bucketSize;
	private int maxSamples = Integer.MAX_VALUE;

	public StatisticalDistribution() {
		struct = new TreeMap<Object, Integer>();
	}
	
	public StatisticalDistribution(int maxSamples) {
		struct = new TreeMap<Object, Integer>();
		this.maxSamples = maxSamples;
	}	

	public StatisticalDistribution(int buckets, double min, double max) {
		this();
		this.buckets = buckets;
		this.min = min;
		this.diff = max - min;
		this.bucketSize = diff / buckets;
		if (max < min) {
			throw new IllegalArgumentException("Min cannot be greater than max");
		}
	}
	
	public Iterator<Map.Entry<Object, Integer>> iterator() {
		return struct.entrySet().iterator();
	}
	
	public Iterator<Pair<Double, Double>> iterator(int buckets) {
		return iterator(buckets, ((Number)this.struct.lastKey()).doubleValue());
	}	
		
	public Iterator<Pair<Double, Double>> iterator(int buckets, double max) {
		if (struct.size() == 0) return new ArrayList<Pair<Double,Double>>().iterator();
		double min = ((Number)this.struct.firstKey()).doubleValue();
		double incr = (max-min)/buckets;
		int[] buck = new int[buckets];
		ArrayList<Pair<Double, Integer>> list = new ArrayList<Pair<Double, Integer>>(buckets);
		int total = 0;
		for (int i = 0 ; i < buck.length ; i++) {
			for (Object o : struct.keySet()) {
				double val = ((Number)o).doubleValue();
				if (val < (i+1)*incr && val > (i*incr)) {
					buck[i] += struct.get(o);
					total += struct.get(o);
				}
			}
			list.add(new Pair<Double, Integer>(i*incr, buck[i]));
		}
		ArrayList<Pair<Double, Double>> listDef = new ArrayList<Pair<Double, Double>>(buckets);
		for (Pair<Double, Integer> p : list) {
			listDef.add(new Pair<Double, Double>(p.getFirst(), (double)((int)p.getSecond())/(double)total));
		}	
		return listDef.iterator();
	}

	private Object transform(Object value) {
		if (buckets < 0) {
			return value;
		}
		if (!(value instanceof Number)) {
			throw new IllegalStateException("Cannot transform value to insert into a bucket");
		}
		Number n = ((Number)value);
		double d = n.doubleValue();
		d = Math.floor((d - min) / bucketSize);
		return d*bucketSize;
	}

	public synchronized void add(K value) {
		if (samples >= maxSamples) return;
		Object o = transform(value);

		Integer occurences = struct.get(o);
		if (occurences == null) {
			struct.put(o, 1);
		} else {
			struct.put(o, occurences + 1);
		}
		samples++;
		if (struct.size() > 0) {
			hasValue = true;
		}
	}

	public void add(Collection<K> col) {
		for (K k : col) {
			add(k);
		}
	}

	public void add(K[] col) {
		for (K k : col) {
			add(k);
		}
	}

	public float getMean() {
		if (struct.size() == 0) return 0;
		if (isQuantifiable()) {
			float total = 0;
			int s = 0;
			for (Map.Entry<Object, Integer> entry : struct.entrySet()) {
				total += ((Number)entry.getKey()).floatValue() * entry.getValue();
				s += entry.getValue();
			}
			return total / s;
		} else {
			throw new IllegalStateException("Cannot compute mean on not number objects");						
		}
	}
	
	public double getSum() {
		if (struct.size() == 0) return 0;
		if (isQuantifiable()) {
			double total = 0;
			for (Map.Entry<Object, Integer> entry : struct.entrySet()) {
				total += ((Number)entry.getKey()).floatValue() * entry.getValue();
			}
			return total;
		} else {
			throw new IllegalStateException("Cannot compute mean on not number objects");						
		}
	}	

	public float getVar() {
		float mean = getMean();
		float total = 0;
		int s = 0;
		for (Map.Entry<Object, Integer> entry : struct.entrySet()) {
			total += Math.pow(((Number)entry.getKey()).floatValue()-mean, 2) * entry.getValue();
			s += entry.getValue();
		}
		return total / s;
	}
	
	public double[] getSumAndSize() {
		if (struct.size() == 0) return new double[]{0,0};
		double[] ret = new double[2];
		if (isQuantifiable()) {
			for (Map.Entry<Object, Integer> entry : struct.entrySet()) {
				ret[0] += ((Number)entry.getKey()).floatValue() * entry.getValue();
				ret[1] += entry.getValue();
			}
			return ret;
		} else {
			throw new IllegalStateException("Cannot sum not number objects");			
		}		
	}

	public int[] toDist() {
		if (buckets >= 0) {
			int[] tab = new int[buckets];
			int index = 0;
			for (Map.Entry<Object, Integer> entry : struct.entrySet()) {
				tab[index] = entry.getValue();
				index++;
			}
			return tab;
		} else {
			return null;
		}

	}

	public EmpiricalDist toEmpiricalDist() {
		if (isQuantifiable()) {
			double[] d = new double[samples];
			int index = 0;
			for (Map.Entry<Object, Integer> entry : struct.entrySet()) {
				int size = entry.getValue();
				double val = ((Number)entry.getKey()).doubleValue();
				for (int i = 0 ; i < size ; i++) {
					d[index] = val;
					index++;
				}
			}
			return new EmpiricalDist(d);
		} else {
			throw new IllegalStateException("Cannot compute mean on not number objects");
		}
	}

	public Object[][] getDistribution() {
		if (isDiscrete()) {
			int min = ((Number)struct.firstKey()).intValue();
			int max = ((Number)struct.lastKey()).intValue();
			Object[][] dist = new Object[2][(int)(max-min+1)];
			for (Object key : struct.keySet()) {
				int rounded = ((Number)key).intValue();
				dist[0][rounded-min] = rounded;
				if (dist[1][rounded-min] == null) {
					dist[1][rounded-min] = new Integer(struct.get(key));
				} else {
					Object o = dist[1][rounded];
					Integer ii = (Integer)o;
					dist[1][rounded-min] = new Integer((int)ii + (int)struct.get(key));
				}

			}
			return dist;
		} else {
			Object[][] ret = new Object[2][struct.size()];
			int i = 0;
			for (Object key : struct.keySet()) {
				ret[0][i] = key;
				ret[1][i] = struct.get(key);
				i++;
			}
			return ret;
		}

	}

	public double[][] getDistribution(int samples) {
		return null;
	}

	@Override
	public String toString() {

		if ((struct.size() > 0) && (Number.class.isAssignableFrom(struct.firstKey().getClass()))) {
			StringBuffer sb1 = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			Object[][] dist = getDistribution();
			float max = 0;
			for (int i = 0 ; i < dist[0].length ; i++) {
				if (dist[1][i] != null) {
					if (((Integer)dist[1][i]).floatValue() > max) {
						max = ((Integer)dist[1][i]).floatValue();
					}
				}
			}
			int length = (new String(max + "")).length();

			for (int i = 0 ; i < dist[0].length ; i++) {
				if (dist[0][i] != null) {
					float f1 = ((Integer)dist[0][i]).floatValue();
					int f2 = ((Integer)dist[1][i]).intValue();
					sb1.append(String.format("| %1$-"+length+".1f  ", f1));
					sb2.append(String.format("  %1$-"+length+"d  ", f2));
				} else {
					Object o2 = null;
					sb1.append(String.format("| %1$-"+length+".1f  ", ((Integer)dist[0][0]).floatValue()+i));
					sb2.append(String.format("| %1$-"+length+".1h  ",o2));

				}



			}
			return sb1.toString() + "\r\n" + sb2.toString();
		} else {
			return super.toString();
		}
	}
	
	/** 
	 * Element can be null in the list
	 **/
	public static double getMean(StatisticalDistribution[] list) {
		double sum = 0;
		double el = 0;
		for (int i = 0 ; i < list.length ; i++) {
			if (list[i] == null) continue;
			double[] r = list[i].getSumAndSize();
			sum += r[0];
			el += r[1];
		}
		return sum / el;
	}
	
	
	public static double getMean(StatisticalDistribution[][] list) {
		double sum = 0;
		double el = 0;
		for (int i = 0 ; i < list.length ; i++) {
			for (int j = 0 ; j < list.length ; j++) {
				if (list[i][j] == null) continue;
				double[] r = list[i][j].getSumAndSize();
				sum += r[0];
				el += r[1];
			}
		}
		return sum / el;
	}	

	public boolean isQuantifiable() {
		if (struct.size() <= 0) {
			return false;
		}
		return ((struct.firstKey() != null) && (Number.class.isAssignableFrom(struct.firstKey().getClass())));
	}
	
	public boolean isDiscrete() {
		if (struct.size() <= 0) {
			return false;
		}
		Object first = struct.firstKey();
		if (first == null) return false;
		Class fc = first.getClass();
		if (Integer.class.isAssignableFrom(fc)) return true;
		if (Byte.class.isAssignableFrom(fc)) return true;
		if (Short.class.isAssignableFrom(fc)) return true;
		if (Long.class.isAssignableFrom(fc)) return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public K getMostFrequentElement() {
		Map.Entry<Object, Integer> maxEntry = null;
		for (Map.Entry<Object, Integer> entry : struct.entrySet()) {
			if (maxEntry == null) {
				maxEntry = entry;
			}
			if (entry.getValue() > maxEntry.getValue()) {
				maxEntry = entry;
			}
		}
		if (maxEntry == null) {
			return null;
		} else {
			return (K)maxEntry.getKey();
		}
	}

	public boolean hasElement() {
		return (struct.entrySet().size() > 0);
	}

	public int getOccurencesOfMostFrequentElement() {
		Map.Entry<Object, Integer> maxEntry = null;
		for (Map.Entry<Object, Integer> entry : struct.entrySet()) {
			if (maxEntry == null) {
				maxEntry = entry;
			}
			if (entry.getValue() > maxEntry.getValue()) {
				maxEntry = entry;
			}
		}
		if (maxEntry == null) {
			return 0;
		} else {
			return maxEntry.getValue();
		}
	}

	public void storeDistribution(String histoName, Execution e, DataPoint dp) {
		Object[][] dist = this.getDistribution();
		for (int i = 0 ; i < dist[0].length ; i++) {
			if (dist[0][i] == null) continue;
			DataPoint der = dp.getDerivedDataPoint();
			der.addProperty("histogram of", histoName);
			der.addProperty("item", dist[0][i].toString());
			der.addResultProperty("occurences", dist[1][i].toString());
			e.addDataPoint(der);
		}
		
	}

	public double getMax() {
		if (isQuantifiable()) {
			return ((Number)struct.lastKey()).doubleValue();
		}
		throw new IllegalStateException("Cannot quantify distribution type");
	}



}
