package main_.reader_;

import java.util.List;

/**
 * An {@link IReader} may serve as a source for an {@link ITransceiver}. Therefore any reader implementation has to meet the
 * requirements of this interface.
 * 
 * @author Christian Oedingen
 */
public interface IReader {
	
	/**
	 * Set limit.
	 * @param long_limit A long defining a maximum number if rows to read.
	 */
	public void setLimit(long long_limit);
	
	/**
	 * Get limit.
	 * @return A long defining a maximum number if rows to read.
	 */
	public long getLimit();

	/**
	 * Open reader. Establish connection to the underlying source.
	 */
	public void open();

	/**
	 * Check if reader is open.
	 * @return "True" is reader is open or "false" otherwise.
	 */
	public boolean isOpen();
	
	/**
	 * Get column names.
	 * @return A {@link List} of {@link String}s containing column names.
	 */
	public List<String> getColumnNames();
	
	/**
	 * Get column count.
	 * @return A long containing the number of columns.
	 */
	public long getColumnCount();
	
	/**
	 * Get row count.
	 * @return A long containing the number of rows.
	 */
	public long getRowCount();
 
	/**
	 * Read next row from the underlying source.
	 * @return A {@link List} of {@link Object}s containing the values of the next row 
	 * that is read from the underlying source or null if there is are no more rows.
	 */
	public List<Object> read();

	/**
	 * Get row number.
	 * @return A long containing the number of the row that was last read from the 
	 * underlying source starting at one.
	 */
	public long getRowNumber();
	
	/**
	 * Close reader. Disrupt connection to the underlying source and reset reader to its default state.
	 */
	public void close();
	
}