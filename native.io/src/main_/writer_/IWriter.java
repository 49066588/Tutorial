package main_.writer_;

import java.util.List;
import java.util.Set;

/**
 * An {@link IWriter} may serve as a target for an {@link ITransceiver}. Therefore any writer implementation has to meet the
 * requirements of this interface.
 * 
 * @author Christian Oedingen
 */
public interface IWriter {
	
	/**
	 * Enable or disable truncation.
	 * @param boolean_isTruncate "True" if the underlying source is supposed to be truncated first or "false" otherwise.
	 */
	public void setTruncationEnabled(boolean boolean_isTruncationEnabled);
	
	/**
	 * Check if truncation is enabled or disabled.
	 * @return "True" if the underlying source is going to be truncated first or "false" otherwise.
	 */
	public boolean isTruncationEnabled();
	
	/**
	 * Add column name.
	 * @return A {@link String} containing a column name.
	 * 
	 * When using an {@link ITransceiver} the column names received from its {@link IReader} are 
	 * automatically transmitted to its {@link IWriter}.
	 */
	public void addColumnName(String string_columnName);
	
	/**
	 * Get column names.
	 * @return A {@link List} of {@link String}s containing all column names.
	 */
	public List<String> getColumnNames();
	
	/**
	 * Reset column names.
	 */
	public void resetColumnNames();
	
	/**
	 * Enable or disable column name capitalization.
	 * @param boolean_isCapitalizeColumnNamesEnabled "True" if column names are supposed to be capitalized or "false" otherwise.
	 * 
	 * This is particularly useful when writing to relational databases, because they typically require workarounds to handle
	 * non-capitalized column names correctly.
	 */
	public void setCapitalizeColumnNamesEnabled(boolean boolean_isCapitalizeColumnNamesEnabled);
	
	/**
	 * Check if column name capitalization is enabled.
	 * @return "True" if column names are going to be capitalized or "false" otherwise.
	 */
	public boolean isCapitalizeColumnNamesEnabled();
	
	/**
	 * Add large text column name.
	 * @param string_largeTextColumnName A {@link String} containing a large text column name.
	 * 
	 * This is necessary when writing to relational databases. For performance reason the typically differentiate between short
	 * and large text columns. One must consider this when creating table in relational databases.
	 */
	public void addLargeTextColumnName(String string_largeTextColumnName);
	
	/**
	 * Get large text column names.
	 * @return A {@link Set} of {@link String}s containing al large text column names.
	 */
	public List<String> getLargeTextColumnNames();
	
	/**
	 * Open writer. Establish connection to the underlying target.
	 */
	public void open();
	
	/**
	 * Check if writer is open.
	 * @return "True" is writer is open or "false" otherwise.
	 */
	public boolean isOpen();

	/**
	 * Write row to the underlying source.
	 * @param list_row A {@link List} of {@link Object}s containing the values of the next
	 * row that is written to the underlying source.
	 */
	public void write(List<Object> list_row);
	
	/**
	 * Get row number.
	 * @return A long containing the number of the row that was last written to the 
	 * underlying source starting at one.
	 */
	public long getRowNumber();

	/**
	 * Close writer. Disrupt connection to the underlying target and reset writer to its default state.
	 */
	public void close();
	
}