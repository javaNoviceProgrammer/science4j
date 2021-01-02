package visualizer4j.charts;

import java.io.Serializable;
import java.util.List;

import org.jfree.data.UnknownKeyException;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.util.PublicCloneable;

public class CustomCategoryDataset extends AbstractDataset implements
CategoryDataset, PublicCloneable, Serializable {

	private static final long serialVersionUID = -4410572023995771102L;

	/** A storage structure for the data. */
	private CustomKeyedValues2D[] data;

	/** Number of stored parameters */
	private int size;

	/**
	 * Creates a new (empty) dataset.
	 */
	public CustomCategoryDataset() {
		this.size = 10;
		this.data = new CustomKeyedValues2D[this.size];
		for (int i=0; i<this.size; ++i) {
			this.data[i] = new CustomKeyedValues2D();
		}
	}

	/**
	 * Returns the number of rows in the table.
	 * 
	 * @return The row count.
	 * 
	 * @see #getColumnCount()
	 */
	public int getRowCount() {
		return this.data[0].getRowCount();
	}

	/**
	 * Returns the number of columns in the table.
	 * 
	 * @return The column count.
	 * 
	 * @see #getRowCount()
	 */
	public int getColumnCount() {
		return this.data[0].getColumnCount();
	}

	/**
	 * Returns a value from the table.
	 * 
	 * @param row
	 *            the row index (zero-based).
	 * @param column
	 *            the column index (zero-based).
	 * 
	 * @return The value (possibly <code>null</code>).
	 * 
	 * @see #addValue(Number, Comparable, Comparable)
	 * @see #removeValue(Comparable, Comparable)
	 */
	public Number getValue(int type, int row, int column) {
		if (type >= this.size) {
			throw new IllegalArgumentException("Value must be smaler than " + this.size);
		}
		return this.data[type].getValue(row, column);
	}

	/**
	 * Returns the key for the specified row.
	 * 
	 * @param row
	 *            the row index (zero-based).
	 * 
	 * @return The row key.
	 * 
	 * @see #getRowIndex(Comparable)
	 * @see #getRowKeys()
	 * @see #getColumnKey(int)
	 */
	public Comparable<?> getRowKey(int type, int row) {
		if (type >= this.size) {
			throw new IllegalArgumentException("Value must be smaler than " + this.size);
		}
		return this.data[type].getRowKey(row);
	}

	/**
	 * Returns the row index for a given key.
	 * 
	 * @param key
	 *            the row key (<code>null</code> not permitted).
	 * 
	 * @return The row index.
	 * 
	 * @see #getRowKey(int)
	 */
	public int getRowIndex(int type, Comparable key) {
		// defer null argument check
		if (type >= this.size) {
			throw new IllegalArgumentException("Value must be smaler than " + this.size);
		}
		return this.data[type].getRowIndex(key);
	}

	/**
	 * Returns the row keys.
	 * 
	 * @return The keys.
	 * 
	 * @see #getRowKey(int)
	 */
	public List<Comparable<?>> getRowKeys() {
		return this.data[0].getRowKeys();
	}

	/**
	 * Returns a column key.
	 * 
	 * @param column
	 *            the column index (zero-based).
	 * 
	 * @return The column key.
	 * 
	 * @see #getColumnIndex(Comparable)
	 */
	public Comparable<?> getColumnKey(int column) {
		return this.data[0].getColumnKey(column);
	}

	/**
	 * Returns the column index for a given key.
	 * 
	 * @param key
	 *            the column key (<code>null</code> not permitted).
	 * 
	 * @return The column index.
	 * 
	 * @see #getColumnKey(int)
	 */
	public int getColumnIndex(Comparable key) {
		// defer null argument check
		return this.data[0].getColumnIndex(key);
	}

	/**
	 * Returns the column keys.
	 * 
	 * @return The keys.
	 * 
	 * @see #getColumnKey(int)
	 */
	public List<Comparable<?>> getColumnKeys() {
		return this.data[0].getColumnKeys();
	}

	/**
	 * Returns the value for a pair of keys.
	 * 
	 * @param rowKey
	 *            the row key (<code>null</code> not permitted).
	 * @param columnKey
	 *            the column key (<code>null</code> not permitted).
	 * 
	 * @return The value (possibly <code>null</code>).
	 * 
	 * @throws UnknownKeyException
	 *             if either key is not defined in the dataset.
	 * 
	 * @see #addValue(Number, Comparable, Comparable)
	 */
	public Number getValue(int type, Comparable rowKey, Comparable columnKey) {
		if (type >= this.size) {
			throw new IllegalArgumentException("Value must be smaler than " + this.size);
		}
		return this.data[type].getValue(rowKey, columnKey);
	}

	/**
	 * Adds a value to the table. Performs the same function as setValue().
	 * 
	 * @param value
	 *            the value.
	 * @param rowKey
	 *            the row key.
	 * @param columnKey
	 *            the column key.
	 * 
	 * @see #getValue(Comparable, Comparable)
	 * @see #removeValue(Comparable, Comparable)
	 */
	public void addValue(Number value, int type, Comparable<?> rowKey, Comparable<?> columnKey) {
		if (type >= this.size) {
			throw new IllegalArgumentException("Value must be smaler than " + this.size);
		}
		this.data[type].addValue(value, rowKey, columnKey);
		fireDatasetChanged();
	}

	/**
	 * Adds a value to the table.
	 * 
	 * @param value
	 *            the value.
	 * @param rowKey
	 *            the row key.
	 * @param columnKey
	 *            the column key.
	 * 
	 * @see #getValue(Comparable, Comparable)
	 */
	public void addValue(double value, int type, Comparable<?> rowKey, Comparable<?> columnKey) {
		addValue(new Double(value), type, rowKey, columnKey);
	}

	/**
	 * Adds or updates a value in the table and sends a
	 * {@link DatasetChangeEvent} to all registered listeners.
	 * 
	 * @param value
	 *            the value (<code>null</code> permitted).
	 * @param rowKey
	 *            the row key (<code>null</code> not permitted).
	 * @param columnKey
	 *            the column key (<code>null</code> not permitted).
	 * 
	 * @see #getValue(Comparable, Comparable)
	 */
	public void setValue(Number value, int type, Comparable<?> rowKey, Comparable<?> columnKey) {
		if (type >= this.size) {
			throw new IllegalArgumentException("Value must be smaler than " + this.size);
		}
		this.data[type].setValue(value, rowKey, columnKey);
		fireDatasetChanged();
	}

	/**
	 * Adds or updates a value in the table and sends a
	 * {@link DatasetChangeEvent} to all registered listeners.
	 * 
	 * @param value
	 *            the value.
	 * @param rowKey
	 *            the row key (<code>null</code> not permitted).
	 * @param columnKey
	 *            the column key (<code>null</code> not permitted).
	 * 
	 * @see #getValue(Comparable, Comparable)
	 */
	public void setValue(double value, int type, Comparable<?> rowKey, Comparable<?> columnKey) {
		if (type >= this.size) {
			throw new IllegalArgumentException("Value must be smaler than " + this.size);
		}
		setValue(new Double(value), type, rowKey, columnKey);
	}

	/**
	 * Adds the specified value to an existing value in the dataset (if the
	 * existing value is <code>null</code>, it is treated as if it were 0.0).
	 * 
	 * @param value
	 *            the value.
	 * @param rowKey
	 *            the row key (<code>null</code> not permitted).
	 * @param columnKey
	 *            the column key (<code>null</code> not permitted).
	 * 
	 * @throws UnknownKeyException
	 *             if either key is not defined in the dataset.
	 */
	public void incrementValue(double value, int type, Comparable<?> rowKey,
			Comparable<?> columnKey) {
		if (type >= this.size) {
			throw new IllegalArgumentException("Value must be smaler than " + this.size);
		}
		double existing = 0.0;
		Number n = getValue(rowKey, columnKey);
		if (n != null) {
			existing = n.doubleValue();
		}
		setValue(existing + value, type, rowKey, columnKey);
	}

	/**
	 * Removes a value from the dataset and sends a {@link DatasetChangeEvent}
	 * to all registered listeners.
	 * 
	 * @param rowKey
	 *            the row key.
	 * @param columnKey
	 *            the column key.
	 * 
	 * @see #addValue(Number, Comparable, Comparable)
	 */
	public void removeValue(Comparable<?> rowKey, Comparable<?> columnKey) {
		for (int i=0; i<this.size; ++i) {
			this.data[i].removeValue(rowKey, columnKey);
		}
		fireDatasetChanged();
	}

	/**
	 * Removes a row from the dataset and sends a {@link DatasetChangeEvent} to
	 * all registered listeners.
	 * 
	 * @param rowIndex
	 *            the row index.
	 * 
	 * @see #removeColumn(int)
	 */
	public void removeRow(int rowIndex) {
		for (int i=0; i<this.size; ++i) {
			this.data[i].removeRow(rowIndex);
		}
		fireDatasetChanged();
	}

	/**
	 * Removes a row from the dataset and sends a {@link DatasetChangeEvent} to
	 * all registered listeners.
	 * 
	 * @param rowKey
	 *            the row key.
	 * 
	 * @see #removeColumn(Comparable)
	 */
	public void removeRow(Comparable<?> rowKey) {
		for (int i=0; i<this.size; ++i) {
			this.data[i].removeRow(rowKey);
		}
		fireDatasetChanged();
	}

	/**
	 * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
	 * to all registered listeners.
	 * 
	 * @param columnIndex
	 *            the column index.
	 * 
	 * @see #removeRow(int)
	 */
	public void removeColumn(int columnIndex) {
		for (int i=0; i<this.size; ++i) {
			this.data[i].removeColumn(columnIndex);
		}
		fireDatasetChanged();
	}

	/**
	 * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
	 * to all registered listeners.
	 * 
	 * @param columnKey
	 *            the column key (<code>null</code> not permitted).
	 * 
	 * @see #removeRow(Comparable)
	 * 
	 * @throws UnknownKeyException
	 *             if <code>columnKey</code> is not defined in the dataset.
	 */
	public void removeColumn(Comparable<?> columnKey) {
		for (int i=0; i<this.size; ++i) {
			this.data[i].removeColumn(columnKey);
		}
		fireDatasetChanged();
	}

	/**
	 * Clears all data from the dataset and sends a {@link DatasetChangeEvent}
	 * to all registered listeners.
	 */
	public void clear() {
		for (int i=0; i<this.size; ++i) {
			this.data[i].clear();
		}
		fireDatasetChanged();
	}

	/**
	 * Tests this dataset for equality with an arbitrary object.
	 * 
	 * @param obj
	 *            the object (<code>null</code> permitted).
	 * 
	 * @return A boolean.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof CategoryDataset)) {
			return false;
		}
		CategoryDataset that = (CategoryDataset) obj;
		if (!getRowKeys().equals(that.getRowKeys())) {
			return false;
		}
		if (!getColumnKeys().equals(that.getColumnKeys())) {
			return false;
		}
		int rowCount = getRowCount();
		int colCount = getColumnCount();
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < colCount; c++) {
				Number v1 = getValue(r, c);
				Number v2 = that.getValue(r, c);
				if (v1 == null) {
					if (v2 != null) {
						return false;
					}
				} else if (!v1.equals(v2)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns a hash code for the dataset.
	 * 
	 * @return A hash code.
	 */
	@Override
	public int hashCode() {
		return this.data.hashCode();
	}

	/**
	 * Returns a clone of the dataset.
	 * 
	 * @return A clone.
	 * 
	 * @throws CloneNotSupportedException
	 *             if there is a problem cloning the dataset.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		CustomCategoryDataset clone = (CustomCategoryDataset) super.clone();
		clone.data = this.data.clone();
		return clone;
	}


	@Override
	public Comparable getRowKey(int row) {
		return this.data[0].getRowKey(row);
	}


	@Override
	public int getRowIndex(Comparable key) {
		return this.data[0].getRowIndex(key);
	}


	@Override
	public Number getValue(Comparable rowKey, Comparable columnKey) {
		return this.data[0].getValue(rowKey, columnKey);
	}

	@Override
	public Number getValue(int row, int column) {
		return this.data[0].getValue(row, column);
	}

}
