package visualizer4j.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import visualizer4j.logging.Logger;

public class Matrix<T> implements Iterable<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	transient static Logger logger = new Logger(Matrix.class);

	private ArrayList<ArrayList<T>> myGeneralMatrix = null;

	public Matrix(int size){
		this(size, size);
	}

	public Matrix(int size1, int size2) {
		myGeneralMatrix = new ArrayList<ArrayList<T>>(size1);
		myGeneralMatrix.ensureCapacity(size1);
		for (int i = 0 ; i < size1 ; i++) {
			ArrayList<T> line = new ArrayList<T>(size2);
			line.ensureCapacity(size2);
			for (int j = 0 ; j < size2 ; j++) {
				line.add(null);
			}
			myGeneralMatrix.add(i,line);
		}
	}

	public Matrix(T[][] tab) {
		this(tab.length);
		for (int i = 0 ; i < tab.length ; i++) {
			for (int j = 0 ; j < tab[i].length ; j++) {
				setMatrixElement(i,j,tab[i][j]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Matrix(float[][] tab) {
		this(tab.length, tab[0].length);
		for (int i = 0 ; i < tab.length ; i++) {
			for (int j = 0 ; j < tab[i].length ; j++) {
				Float f = tab[i][j];
				setMatrixElement(i,j,(T)f);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Matrix(int[][] tab) {
		this(tab.length, tab[0].length);
		for (int i = 0 ; i < tab.length ; i++) {
			for (int j = 0 ; j < tab[i].length ; j++) {
				Number f = tab[i][j];
				setMatrixElement(i,j,(T)f);
			}
		}
	}	
	

	@SuppressWarnings("unchecked")
	public Matrix(double[][] tab) {
		this(tab.length, tab[0].length);
		for (int i = 0 ; i < tab.length ; i++) {
			for (int j = 0 ; j < tab[i].length ; j++) {
				Double f = tab[i][j];
				setMatrixElement(i,j,(T)f);
			}
		}
	}

	public Matrix(Matrix<T> mat) {
		this(mat.size());
		int size = mat.size();
		for (int i = 0 ; i < size ; i++) {
			for (int j = 0 ; j < size ; j++) {
				setMatrixElement(i,j,mat.getMatrixElement(i,j));
			}
		}
	}
	
	public static float[][] newFloatMatrix(int size, float def) {
		float[][] tab = new float[size][size];
		for (int i = 0 ; i < size ; i++) {
			Arrays.fill(tab[i], def);
		}
		return tab;
	}
	

	public static double[][] newDoubleMatrix(int size, float def) {
		double[][] tab = new double[size][size];
		for (int i = 0 ; i < size ; i++) {
			Arrays.fill(tab[i], def);
		}
		return tab;
	}	

	public int size() {
		return myGeneralMatrix.size();
	}

	public Object[][] toArrayOfArray() {
		Object[][] tab = new Object[size()][size()];
		for (int i = 0 ; i < tab.length ; i++) {
			for (int j = 0 ; j < tab.length ; j++) {
				tab[i][j] = this.getMatrixElement(i,j);
			}
		}
		return tab;
	}
	
	public static <T> T[][] permute(T[][] x, int a, int b) {
		for (int i = 0 ; i < x.length ; i++) {
			T temp = x[a][i];
			x[a][i] = x[b][i];
			x[b][i] = temp;
		}
		for (int i = 0 ; i < x[0].length ; i++) {
			T temp = x[i][a];
			x[i][a] = x[i][b];
			x[i][b] = temp;
		}
		return x;
	}
	
	public static double[][] shiftRight(double[][] x, int y) {
		double[] temp = new double[y];
		for (int i = 0 ; i < x.length ; i++) {
			System.arraycopy(x[i], x[i].length - y, temp, 0, y);
			System.arraycopy(x[i], 0, x[i], y, x[i].length - y);
			System.arraycopy(temp, 0, x[i], 0, y);
		}
		return x;
	}
	
	public static double[][] shiftDown(double[][] x, int y) {
		double[][] temp = new double[y][x[0].length];
		for (int i = 0 ; i < y ; i++) {
			temp[i] = x[x.length - y];
			//System.arraycopy(x, x.length - y, temp, 0, y);
		}
		for (int i = x.length - y - 1 ; i >= 0 ; i--) {
			x[i+y] = x[i];
		//	System.arraycopy(x, 0, x, y, x.length - y);
		}	
		for (int i = 0 ; i < y ; i++) {
			x[i] = temp[i];
		}
		return x;
	}	
	
	public static double[][] permute(double[][] x, int a, int b) {
		for (int i = 0 ; i < x.length ; i++) {
			double temp = x[a][i];
			x[a][i] = x[b][i];
			x[b][i] = temp;
		}
		for (int i = 0 ; i < x[0].length ; i++) {
			double temp = x[i][a];
			x[i][a] = x[i][b];
			x[i][b] = temp;
		}
		return x;
	}
	
	public static int[][] permute(int[][] x, int a, int b) {
		for (int i = 0 ; i < x.length ; i++) {
			int temp = x[a][i];
			x[a][i] = x[b][i];
			x[b][i] = temp;
		}
		for (int i = 0 ; i < x[0].length ; i++) {
			int temp = x[i][a];
			x[i][a] = x[i][b];
			x[i][b] = temp;
		}
		return x;
	}	
	
	
	
	@SuppressWarnings("unchecked")	
	public static <T> T[][] transpose(T[][] x){
		Object[][] t = new Object[x[0].length][x.length];
		for(int i = 0; i < x.length; ++i){
			for(int j = 0; j < x[0].length; ++j){
				t[j][i] = x[i][j];
			}
		}
		return (T[][])t;
	}

	public static double[][] transpose(double[][] x){
		double [][] t = new double[x[0].length][x.length];
		for(int i = 0; i < x.length; ++i){
			for(int j = 0; j < x[0].length; ++j){
				t[j][i] = x[i][j];
			}
		}
		return t;
	}
	
	public static float[][] mult(float[][] v, double num) {
		float[][] res = new float[v.length][v[0].length];
		for(int i = 0; i < v.length; i++){
			for(int j=0; j < v[0].length; j++){
				res[i][j] = (float)(num * v[i][j]);
			}
		}
		return res;
	}
		
	public static double[][] mult(double[][] v, double num){
		double[][] res = new double[v.length][v[0].length];
		for(int i = 0; i < v.length; i++){
			for(int j=0; j < v[0].length; j++){
				res[i][j] = num * v[i][j];
			}
		}
		return res;
	}
	
	public static double mean(double[][] v){
		double tot = 0;
		int ii = 0;
		for(int i = 0; i < v.length; i++){
			for(int j=0; j < v[0].length; j++){
				tot += v[i][j];
				ii++;
			}
		}
		return tot/(double)ii;
	}
	
	public static double getVariance(double[][] v) {
		double tot = 0;
		double mean = mean(v);
		int ii = 0;
		for(int i = 0; i < v.length; i++){
			for(int j=0; j < v[0].length; j++){
				tot += Math.pow(v[i][j] - mean, 2);
				ii++;
			}
		}
		return 	tot/(float)(ii - 1);
	}		
	
	public static double[][] add(double[][] v, double num){
		double[][] res = new double[v.length][v[0].length];
		for(int i = 0; i < v.length; i++){
			for(int j=0; j < v[0].length; j++){
				res[i][j] = num + v[i][j];
			}
		}
		return res;
	}		
	
	public static <T> List<T> asList(T[][] tab) {
		return asList(tab, false);
	}
		
	public static <T> List<T> asList(T[][] tab, boolean withNulls) {
		ArrayList<T> t = new ArrayList<T>(tab.length*tab.length);
		for (int i = 0 ; i < tab.length ; i++) {
			for (int j = 0 ; j < tab.length ; j++) {
				if (withNulls || tab[i][j] != null)
					t.add(tab[i][j]);
			}
		}
		return t;	
	}

	public void toArrayOfArray(T[][] tab) {
		if (tab.length != this.size()) {
			throw new IllegalStateException("Array size mismatch");
		}
		for (int i = 0 ; i < tab.length ; i++) {
			for (int j = 0 ; j < tab.length ; j++) {
				tab[i][j] = this.getMatrixElement(i,j);
			}
		}
	}
	
	public float[][] toArrayOfFloat() {
		float[][] arr = new float[size()][size()];
		for (int i = 0 ; i < arr.length ; i++) {
			for (int j = 0 ; j < arr.length ; j++) {
				T ij = this.getMatrixElement(i,j);
				if (ij != null) {
					arr[i][j] = ((Number)ij).floatValue();
				}
			}
		}
		return arr;
	}
	
	public static float[][] toArrayOfFloat(double[][] d) {
		float[][] arr = new float[d.length][d.length];
		for (int i = 0 ; i < arr.length ; i++) {
			for (int j = 0 ; j < arr.length ; j++) {
				arr[i][j] = (float)d[i][j];
			}
		}
		return arr;
	}		

	public double[][] toArrayOfDouble() {
		double[][] arr = new double[size()][size()];
		for (int i = 0 ; i < arr.length ; i++) {
			for (int j = 0 ; j < arr.length ; j++) {
				T ij = this.getMatrixElement(i,j);
				if (ij != null) {
					arr[i][j] = ((Number)ij).doubleValue();
				}
			}
		}
		return arr;
	}
	
	public static double[][] toArrayOfDouble(int[][] in) {
		double[][] arr = new double[in.length][in.length];
		for (int i = 0 ; i < arr.length ; i++) {
			for (int j = 0 ; j < arr.length ; j++) {
				arr[i][j] = in[i][j];
			}
		}
		return arr;		
	}

	public int[][] toArrayOfInt() {
		int[][] arr = new int[size()][size()];
		for (int i = 0 ; i < arr.length ; i++) {
			for (int j = 0 ; j < arr.length ; j++) {
				T ij = this.getMatrixElement(i,j);
				if (ij != null) {
					arr[i][j] = ((Number)ij).intValue();
				}
			}
		}
		return arr;
	}
	
	public static int[][] toArrayOfInt(double[][] d) {
		int[][] arr = new int[d.length][d.length];
		for (int i = 0 ; i < arr.length ; i++) {
			for (int j = 0 ; j < arr.length ; j++) {
				arr[i][j] = (int)Math.round(d[i][j]);
			}
		}
		return arr;		
	}
	
	/**
	 * If d[i][j] = x AND d[j][i] = 0, then d[j][i] is made equal to x. Otherwise exception is thrown.
	 * @param d
	 */
	public static void symmetrise(double[][] d) {
		for (int i = 0 ; i < d.length ; i++) {
			for (int j = 0 ; j < d.length ; j++) {
				if (d[i][j] != 0 && d[j][i] == 0) {
					d[j][i] = d[i][j];
				}
				if (d[i][j] != 0 && d[j][i] != 0) throw new IllegalStateException("Cannot symmetrise matrix");
			}
		}		
	}
	
	/**
	 * This method makes sure that if d[i][j] == true, so d[j][i] will be true, too
	 * @param d
	 */
	public static void symmetrise(boolean[][] d) {
		for (int i = 0 ; i < d.length ; i++) {
			for (int j = 0 ; j < d.length ; j++) {
				d[j][i] |= d[i][j];
			}
		}
	}
	
	public static double sum(double[][] d) {
		double tot = 0;
		for (int i = 0 ; i < d.length ; i++) {
			for (int j = 0 ; j < d.length ; j++) {
				tot += d[i][j];
			}
		}
		return tot;	
	}
	
	public static double sum(Object[][] d) {
		double tot = 0;
		for (int i = 0 ; i < d.length ; i++) {
			for (int j = 0 ; j < d.length ; j++) {
				if (d[i][j] != null) {
					tot += ((Number)d[i][j]).doubleValue();
				}
			}
		}
		return tot;	
	}	
	
	
	
	public T getMatrixElement(NodePair np) {
		return myGeneralMatrix.get(np.getStartNode()).get(np.getEndNode());
	}

	public T getMatrixElement(int i, int j){
		return myGeneralMatrix.get(i).get(j);
	}

	public T getMatrixElementUndir(int i, int j) {
		T t = getMatrixElement(i,j);
		if (t == null) {
			return getMatrixElement(j,i);
		} else {
			return t;
		}
	}
	
	public Object clone() {
		Matrix<T> c = new Matrix<T>(this.size());
		for (int i = 0 ; i < size() ; i++) {
			for (int j = 0 ; j < size() ; j++) {
				c.setMatrixElement(i, j, getMatrixElement(i,j));
			}
		}
		return c;
	}
	
	public void setMatrixElement(NodePair np, T element) {
		myGeneralMatrix.get(np.getStartNode()).set(np.getEndNode(), element);
	}

	public void setMatrixElement(int i, int j, T element){
		myGeneralMatrix.get(i).set(j,element);
	}

	public ArrayList<ArrayList<T>> getMatrix(){
		return myGeneralMatrix;
	}

	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer();
		int nulls = 0;
		for(int i=0; i<myGeneralMatrix.size(); i++){
			for(int j=0; j<myGeneralMatrix.get(i).size(); j++){
				T obj = myGeneralMatrix.get(i).get(j);
				if (obj != null) {
					sb.append(obj);
					sb.append("\r\n");
				} else {
					nulls++;
				}
			}
			sb.append("\r\n");
		}
		if (nulls > 0) {
			sb.append("Note : " + nulls + " elements are null");
		}
		return sb.toString();
	}

	public Iterator<T> iterator() {
		return this.new MatrixIterator<T>(true);
	}

	public Iterable<T> matrixWithoutDiagonal() {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new MatrixIterator<T>(false);
			}
		};
	}

	@SuppressWarnings("unchecked")
	public Matrix<T> times(double d) {
		Matrix<T> m = new Matrix<T>(this.size());
		for (int i = 0 ; i < this.size() ; i++) {
			for (int j = 0 ; j < this.size() ; j++) {
				T t = this.getMatrixElement(i,j);
				if (t instanceof Number) {
					double r = ((Number)t).doubleValue() * d;
					m.setMatrixElement(i, j, (T)(Double)r);
				} else {
					throw new IllegalStateException("Not a number type");
				}
			}
		}
		return m;
	}
	
	public Iterable<Pair<NodePair, T>> asPairIteration() {
		final int size = myGeneralMatrix.size();
		return new Iterable<Pair<NodePair, T>>() {
			public Iterator<Pair<NodePair, T>> iterator() {
				return new Iterator<Pair<NodePair, T>>() {
					int i = 0;
					int j = 0;
					Pair<NodePair, T> next = prepareNext();
						
					private Pair<NodePair, T> prepareNext() {
						T object = null;
						next = null;
						while (next == null) {
							if (i >= size) {
								return null;
							}
							object = getMatrixElement(i,j);
							if (object != null) {
								next = new Pair<NodePair, T>(new NodePair(i,j), object);
							}
							j++;
							if (j >= size) {
								j = 0;
								i++;
							}
						}
						return next;
					}
					
					public void remove() {
					}
					
					public Pair<NodePair, T> next() {
						Pair<NodePair, T> toRet = next;
						next = prepareNext();
						return toRet;
					}
					
					public boolean hasNext() {
						return (next != null);
					}
				};
			}
		};
	}

	public void setTo(T t) {
		for (int i = 0 ; i < this.size() ; i++) {
			for (int j = 0 ; j < this.size() ; j++) {
				this.setMatrixElement(i,j,t);
			}
		}
	}


	public class MatrixIterator<U> implements Iterator<U> {
		int i = 0;
		int j = 0;
		U next = internalSetNext();
		boolean withDiagonal;

		public MatrixIterator(boolean withDiagonal) {
			this.withDiagonal = withDiagonal;
		}

		public boolean hasNext() {
			return (next != null);
		}

		public U next() {
			U toReturn = next;
			next = internalSetNext();
			return toReturn;
		}

		public void remove() {
		}

		@SuppressWarnings("unchecked")
		private U internalSetNext() {
			int sizeM = myGeneralMatrix.size();
			while (i < sizeM) {
				ArrayList line = myGeneralMatrix.get(i);
				Object it = line.get(j);
				int size = line.size();
				if (j+1 < size) {
					j++;
				} else if ( i < myGeneralMatrix.size()) {
					i++;
					j = 0;
				}
				if (it != null && (i != j || withDiagonal)) {
					return (U)it;
				}
			}
			return null;
		}
	}

	public class ContentIterator implements Iterator {

		Iterator<?> globalIterator;
		Iterator<?> localIte;
		Object next;

		public ContentIterator() {
			globalIterator = iterator();
			next = internalSetNext();
		}

		public boolean hasNext() {
			return (next != null);
		}

		public Object next() {
			Object toReturn = next;
			next = internalSetNext();
			return toReturn;
		}

		public void remove() {
		}

		private Object internalSetNext() {
			while ((localIte == null || !localIte.hasNext()) && globalIterator.hasNext()) {
				localIte = ((Iterable<?>)globalIterator.next()).iterator();
			}
			if (localIte != null && localIte.hasNext()) {
				//	try {
				//	logger.trace(localIte.hashCode()+"");
				return localIte.next();
				/*	}
				catch (Exception e) {
				//	logger.trace("****" + localIte.hashCode()+"");
					return null;
				}*/
			} else {
				return null;
			}

		}

	}

	public Iterator iteratorOnContent() {
		try {
			return this.new ContentIterator();
		}
		catch (ClassCastException e) {
			throw new IllegalArgumentException("Cannot get a content iterator is elements contained in matrix is not iterable");
		}
	}

	public List<T> getAllElements() {
		ArrayList<T> l = new ArrayList<T>();
		for (int i = 0 ; i < myGeneralMatrix.size() ; i++) {
			for (int j = 0 ; j < myGeneralMatrix.get(i).size() ; j++) {
				l.add(myGeneralMatrix.get(i).get(j));
			}
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	public List getAllContainedElements() {
		try {
			ArrayList total = new ArrayList();
			for (T cell : this) {
				Iterable ite = (Iterable)cell;
				for (Object o : ite) {
					total.add(o);
				}
			}
			return total;
		}
		catch (ClassCastException e) {
			throw new IllegalArgumentException("Cannot get a content iterator is elements contained in matrix is not iterable");
		}
	}
	
	public static String arrayToString(Object[] mat) {
		return arrayToString(new Object[][]{mat});
	}	

	public static String arrayToString(Object[][] mat) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i < mat.length ; i++) {
			if (i == 0) {
				sb.append("[ ");
			} else {
				sb.append("  ");
			}
			for (int j = 0 ; j < mat[i].length ; j++) {
				if (mat[i][j] != null) {
					sb.append((mat[i][j]).toString());
				}
				if (j+1 < mat[i].length) {sb.append(",");}
			}
			sb.append("]");
			if (i+1 < mat.length) {sb.append("\r\n");} else {
				sb.append("]");
			}
		}
		return sb.toString();
	}
	
	public static String arrayToString(int[] mat) {
		return arrayToString(new int[][]{mat});
	}

	public static String arrayToString(int[][] mat) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i < mat.length ; i++) {
			if (i == 0) {
				sb.append("[ ");
			} else {
				sb.append("  ");
			}
			for (int j = 0 ; j < mat[i].length ; j++) {
				sb.append(String.format("%9d",mat[i][j]));
				if (j+1 < mat[i].length) {sb.append(",");}
			}
			sb.append("]");
			if (i+1 < mat.length) {sb.append("\r\n");} else {
				sb.append("]");
			}
		}
		return sb.toString();
	}
	
	public static String arrayToString(double[] mat) {	
		return arrayToString(new double[][]{mat});
	}

	public static String arrayToString(double[][] mat) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i < mat.length ; i++) {
			if (i == 0) {
				sb.append("[ ");
			} else {
				sb.append("  ");
			}
			for (int j = 0 ; j < mat[i].length ; j++) {
				sb.append(String.format("%-9.3g", mat[i][j]));
				if (j+1 < mat[i].length) {sb.append("  ");}
			}
			sb.append("]");
			if (i+1 < mat.length) {sb.append("\r\n");} else {
				sb.append("]");
			}
		}
		return sb.toString();
	}
	
	public static String arrayToString(float[] mat) {
		return arrayToString(new float[][]{mat});
	}	

	public static String arrayToString(float[][] mat) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i < mat.length ; i++) {
			if (i == 0) {
				sb.append("[ ");
			} else {
				sb.append("  ");
			}
			for (int j = 0 ; j < mat[i].length ; j++) {
				sb.append(String.format("%-9.3g", mat[i][j]));
				if (j+1 < mat[i].length) {sb.append("  ");}
			}
			sb.append("]");
			if (i+1 < mat.length) {sb.append("\r\n");} else {
				sb.append("]");
			}
		}
		return sb.toString();
	}
	
	public static String arrayToString(boolean[] mat) {
		return arrayToString(new boolean[][]{mat});
	}

	public static String arrayToString(boolean[][] mat) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i < mat.length ; i++) {
			if (i == 0) {
				sb.append("[ ");
			} else {
				sb.append("  ");
			}
			for (int j = 0 ; j < mat[i].length ; j++) {
				sb.append(String.format("%10s", mat[i][j]));
				if (j+1 < mat[i].length) {sb.append(",");}
			}
			sb.append("]");
			if (i+1 < mat.length) {sb.append("\r\n");} else {
				sb.append("]");
			}
		}
		return sb.toString();
	}
	
	public static String arrayToString(long[] mat) {
		return arrayToString(new long[][]{mat});
	}

	public static String arrayToString(long[][] mat) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i < mat.length ; i++) {
			if (i == 0) {
				sb.append("[ ");
			} else {
				sb.append("  ");
			}
			for (int j = 0 ; j < mat[i].length ; j++) {
				sb.append(String.format("%10s", mat[i][j]));
				if (j+1 < mat[i].length) {sb.append(",");}
			}
			sb.append("]");
			if (i+1 < mat.length) {sb.append("\r\n");} else {
				sb.append("]");
			}
		}
		return sb.toString();
	}

	// COPY
	public static void matrixCopy(Object[][] src, Object[][] dest) {
		if (src.length != dest.length) {
			throw new IllegalArgumentException("Sizes must coincide");
		}
		for (int i = 0 ; i < src.length ; i++) {
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}
	}

	public static void matrixCopy(int[][] src, int[][] dest) {
		if (src.length != dest.length) {
			throw new IllegalArgumentException("Sizes must coincide");
		}
		for (int i = 0 ; i < src.length ; i++) {
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}
	}

	public static void matrixCopy(float[][] src, float[][] dest) {
		if (src.length != dest.length) {
			throw new IllegalArgumentException("Sizes must coincide");
		}
		for (int i = 0 ; i < src.length ; i++) {
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}
	}

	// MAXS
	public static int max(int[][] t) {
		int max = Integer.MIN_VALUE;
		for (int i = 0 ; i < t.length ; i++) {
			for (int j = 0 ; j < t[i].length ; j++) {
				int it = t[i][j];
				if (it > max) {
					max = it;
				}
			}
		}
		return max;
	}
	public static double max(double[][] t) {
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0 ; i < t.length ; i++) {
			for (int j = 0 ; j < t[i].length ; j++) {
				double it = t[i][j];
				if (it > max) {
					max = it;
				}
			}
		}
		return max;

	}

	public static float max(float[][] t) {
		float max = Float.NEGATIVE_INFINITY;
		for (int i = 0 ; i < t.length ; i++) {
			for (int j = 0 ; j < t[i].length ; j++) {
				float it = t[i][j];
				if (it > max) {
					max = it;
				}
			}
		}
		return max;
	}

	public static long max(long[][] t) {
		long max = Long.MIN_VALUE;
		for (int i = 0 ; i < t.length ; i++) {
			for (int j = 0 ; j < t[i].length ; j++) {
				long it = t[i][j];
				if (it > max) {
					max = it;
				}
			}
		}
		return max;
	}
	// MINS
	public static int min(int[][] t) {
		int max = Integer.MAX_VALUE;
		for (int i = 0 ; i < t.length ; i++) {
			for (int j = 0 ; j < t[i].length ; j++) {
				int it = t[i][j];
				if (it < max) {
					max = it;
				}
			}
		}
		return max;
	}
	public static double min(double[][] t) {
		double max = Double.POSITIVE_INFINITY;
		for (int i = 0 ; i < t.length ; i++) {
			for (int j = 0 ; j < t[i].length ; j++) {
				double it = t[i][j];
				if (it < max) {
					max = it;
				}
			}
		}
		return max;

	}
	public static float min(float[][] t) {
		float max = Float.POSITIVE_INFINITY;
		for (int i = 0 ; i < t.length ; i++) {
			for (int j = 0 ; j < t[i].length ; j++) {
				float it = t[i][j];
				if (it < max) {
					max = it;
				}
			}
		}
		return max;

	}
	public static long min(long[][] t) {
		long max = Long.MAX_VALUE;
		for (int i = 0 ; i < t.length ; i++) {
			for (int j = 0 ; j < t[i].length ; j++) {
				long it = t[i][j];
				if (it < max) {
					max = it;
				}
			}
		}
		return max;
	}

	public static double[][] toDouble(boolean[][] in) {
		if (in.length == 0) return new double[0][0];
		double[][] d = new double[in.length][in[0].length];
		for (int i = 0 ; i < in.length ; i++) {
			for (int j = 0 ; j < in[i].length ; j++) {
				d[i][j] = in[i][j] ? 1 : 0;
			}
		}
		return d;
	}

	public static double[][] toDouble(int[][] in) {
		if (in.length == 0) return new double[0][0];
		double[][] d = new double[in.length][in[0].length];
		for (int i = 0 ; i < in.length ; i++) {
			for (int j = 0 ; j < in[i].length ; j++) {
				d[i][j] = in[i][j];
			}
		}
		return d;
	}

	public static double[][] normalize(double[][] intensity) {
		double max = max(intensity);
		return mult(intensity, 1d/max);
	}

	public static double[][] normalize(int[][] dist) {
		double max = max(dist);
		return mult(dist, 1d/max);
	}

	private static double[][] mult(int[][] dist, double num) {
		if (dist.length == 0) return new double[0][];
		double[][] norm = new double[dist.length][dist[0].length];
		for (int i = 0 ; i < norm.length ; i++) {
			for (int j = 0 ; j < norm[i].length ; j++) {
				norm[i][j] = dist[i][j] * num;
			}
		}
		return norm;
	}





}
