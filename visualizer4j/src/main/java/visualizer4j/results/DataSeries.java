package visualizer4j.results;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import visualizer4j.utils.BoxedPriorityQueue;
import visualizer4j.utils.Pair;
import visualizer4j.utils.PairList;



public abstract class DataSeries implements Iterable<DataSeries.DataSeriesStruct> {


	public List<PairList<String,String>> crit;

	public PairList<String, String> getSerieCriteria() {
		PairList<String, String> pl = new PairList<String, String>();
		if (crit == null) {
			return pl;
		}
		for (PairList<String, String> p : crit) {
			pl.addAll(p);
		}
		return pl;
	}
	
	public String getValueOfLastCrit() {
		if (crit.size() == 0) return "";
		PairList<String, String> colorCrit = crit.get(0);
		if (colorCrit.size() == 0) return "";
		return colorCrit.getSecond(colorCrit.size() -1);
	}

	public String getLegend() {
		StringBuilder sb = new StringBuilder();
		if (crit != null) {
			for (int j = 0 ; j < crit.size() ; j++) {
				PairList<String, String> p = crit.get(j);
		//	for (PairList<String, String> p : crit) {
				for (int i = 0 ; i < p.size() ; i++) {
					Pair<?,?> pa = p.get(i);
					sb.append(pa.getFirst() + "=" + pa.getSecond());
					if (i+1 < p.size()) {
						sb.append(", ");
					}
				}
				if (j+1 < crit.size()) {
					sb.append(", ");
				}				
			}
			return sb.toString();
		} else {
			return null;
		}
	}

	public DataSeries(List<PairList<String,String>> crit) {
		this.crit = crit;
	}

	public Iterable<Float> getYs() {
		return new Iterable<Float>() {
			public Iterator<Float> iterator() {
				return getYsImpl();
			}
		};
	}

	public float[] getMeanedYs() {
		HashMap<String, LinkedList<Float>> struct = new HashMap<String, LinkedList<Float>>();

		for (DataSeries.DataSeriesStruct s : this) {
			String xValOrString;
			Double x;
			try {
				x = Double.parseDouble(s.xVal);
				xValOrString = x + "";
			} catch (Exception e) {
				if (s == null || s.xVal == null) {
					xValOrString = "NULL";
				} else {
					xValOrString = s.xVal;
				}
			}

			LinkedList<Float> nb = struct.get(xValOrString);
			if (nb == null) {
				nb = new LinkedList<Float>();
				struct.put(xValOrString, nb);
			}
			nb.addLast(s.yVal);
		}
		float[] tab = new float[struct.size()];
		int index = 0;
		for (LinkedList<Float> one : struct.values()) {
			float accum = 0;
			for (float f : one) {
				accum += f;
			}
			tab[index] = accum / one.size();
			index++;
		}
		return tab;
	}

	public static class DataSeriesSorter {
		public static class XEntry implements Comparable<XEntry> {
			public XEntry(String s) {
				alphaXValue = s;
				try {
					numericXValue = (float)Double.parseDouble(s);
					nativeX = true;
				} catch (NumberFormatException e) {}
			}
			
			@Override
			public int compareTo(XEntry o) {
				if (this.nativeX && o.nativeX) {
					return this.numericXValue.compareTo(o.numericXValue);
				} else {
					return this.alphaXValue.compareTo(o.alphaXValue);
				}
			}			

			public boolean isNumeric() {
				return (numericXValue != null);
			}

			public float mean() {
				float acum = 0;
				for (int i = 0 ; i < values.size() ; i++) {
					acum += values.get(i);
				}
				return acum/values.size();
			}

			public Vector<Float> values = new Vector<Float>();
			public Float numericXValue = null;
			public String alphaXValue;
			public boolean nativeX = false;

		}

		public DataSeriesSorter() {
			globalEquivalences = new TreeMap<String, Integer>();
		}

		TreeMap<String, Integer> globalEquivalences;

		private TreeMap<String, XEntry> getEntryTreeMap(DataSeries ds) {
			TreeMap<String, XEntry> xEntriesMap = new TreeMap<String, XEntry>();

			for (DataSeriesStruct s : ds) {
				XEntry entry = xEntriesMap.get(s.xVal);
				if (entry == null) {
					entry = new XEntry(s.xVal);
					xEntriesMap.put(s.xVal, entry);
				}
				entry.values.add(s.yVal);
			}
			return xEntriesMap;
		}

		public Collection<XEntry> getNumerisedList(DataSeries ds) {
			TreeMap<String, XEntry> xEntriesMap = getEntryTreeMap(ds);
			//		ArrayList<XEntry> list = new ArrayList<XEntry>(xEntriesMap.size());
			// test assign number to non-number
			numberNonNumeric(xEntriesMap.values());

			return xEntriesMap.values();
		}

		@SuppressWarnings("unchecked")
		public Collection<XEntry> getYSortedList(DataSeries ds) {
			BoxedPriorityQueue<XEntry> ySorted = new BoxedPriorityQueue();
			TreeMap<String, XEntry> xEntriesMap = getEntryTreeMap(ds);
			for (XEntry xe : xEntriesMap.values()) {
				ySorted.offer(xe, xe.mean());
			}
			List<XEntry> ls = new ArrayList<XEntry>(ySorted.size());
			while (ySorted.size() > 0) {
				XEntry xe = ySorted.pollElement();
				if (globalEquivalences.get(xe.alphaXValue) == null) {
					xe.numericXValue = (float)numberCounter;
					globalEquivalences.put(xe.alphaXValue, numberCounter);
					numberCounter++;
				} else {
					xe.numericXValue = (float)globalEquivalences.get(xe.alphaXValue);
				}
				xe.nativeX = false;
				
				ls.add(xe);
			}
			//	numberNonNumeric(ySorted.values());
			return ls;
		}

		int numberCounter = 0;

		private void numberNonNumeric(Collection<XEntry> col) {
		//	ArrayList<XEntry> al = new ArrayList<XEntry>(col);
		//	Collections.sort(al);
			for (XEntry xe : col) {
				if (xe.isNumeric() == false) {
					if (globalEquivalences.get(xe.alphaXValue) == null) {
						xe.numericXValue = (float)numberCounter;
						globalEquivalences.put(xe.alphaXValue, numberCounter);
						numberCounter++;
					} else {
						xe.numericXValue = (float)globalEquivalences.get(xe.alphaXValue);
					}
				}
				//	list.add(xe);
			}
		}
	}

	public abstract int getNumberOfPoints();

	public abstract Iterator<Float> getYsImpl();

	public static class EmptyChartValues extends DataSeries {

		public EmptyChartValues() {
			super(null);
		}

		@Override
		public String getLegend() {
			return "";
		}

		@Override
		public Iterator<DataSeriesStruct> iterator() {
			return new Iterator<DataSeriesStruct>() {
				@Override
				public DataSeriesStruct next() {
					return null;
				}

				@Override
				public boolean hasNext() {
					return false;
				}

				@Override
				public void remove() {}
			};
		}

		@Override
		public int getNumberOfPoints() {
			return 0;
		}
		@Override
		public Iterator<Float> getYsImpl() {
			return new Iterator<Float>() {
				@Override
				public Float next() {
					return null;
				}

				@Override
				public boolean hasNext() {
					return false;
				}

				@Override
				public void remove() {}
			};
		}
	}

	public static class DataSeriesStruct implements Comparable<DataSeriesStruct> {
		public String xVal;
		public float yVal;
		public int expId;
		@Override
		public int compareTo(DataSeriesStruct o) {
			try {
				Double d_loc = Double.parseDouble(this.xVal);
				Double d_alt = Double.parseDouble(o.xVal);
				return (int)Math.signum(d_loc - d_alt);
			}
			catch (NumberFormatException e) {
			}
			return xVal.compareTo(o.xVal);
		}
	}


}