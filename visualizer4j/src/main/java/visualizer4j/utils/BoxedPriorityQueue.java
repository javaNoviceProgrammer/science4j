package visualizer4j.utils;

import java.util.Iterator;
import java.util.PriorityQueue;

public class BoxedPriorityQueue<E> implements Iterable<E> {

	private PriorityQueue<Cell> queue;

	public BoxedPriorityQueue() {
		queue = new PriorityQueue<Cell>();
	}

	private class Cell implements Comparable<Cell> {
		E e;
		double score;

		@SuppressWarnings("unchecked")
		public int compareTo(Cell c) {
			int r = (int)Math.signum(this.score - c.score);
			if (r == 0) {
				if (e instanceof Comparable) {
					return ((Comparable)e).compareTo(c.e);
				} else {
					return r;
				}
			} else {
				return r;
			}
			
		}

		@Override
		public String toString() {
			return score+":{"+e+"}";
		}

		public Pair<E, Double> getPair() {
			return new Pair<E, Double>(e, score);
		}
	}

	public boolean offer(E e, double score) {
		Cell c = new Cell();
		c.e = e;
		c.score = score;
		return queue.offer(c);
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public double peekScore() {
		return queue.peek().score;
	}

	public E peekElement() {
		return queue.peek().e;
	}
	
	public Pair<E, Double> peekBoth() {
		Cell c = queue.peek();
		return c.getPair();
		
	}

	public double pollScore() {
		return queue.poll().score;
	}

	public E pollElement() {
		return queue.poll().e;
	}

	public int size() {
		return queue.size();
	}
	
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			
			Iterator<Cell> it = queue.iterator();
				
			public E next() {
				return it.next().e;
			}
			
			public boolean hasNext() {
				return it.hasNext();
			}
			
			public void remove() {
			}
		};
	}

	@Override
	public String toString() {
		return queue.toString();
	}


}
