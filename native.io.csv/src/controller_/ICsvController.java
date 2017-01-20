package controller_;

import java.io.File;
import java.util.Map;

/**
 * An {@link ICsvController} controls parameters that are used when reading from or writing to csv files.
 * 
 * @author Christian Oedingen
 */
public interface ICsvController {

	/**
	 * Set source file.
	 * @param file_sourceFile A {@link File}.
	 */
	public void setSourceFile(File file_sourceFile);
	
	/**
	 * Get source file.
	 * @return A {@link File}.
	 */
	public File getSourceFile();
	
	/**
	 * Set target file.
	 * @param file_targetFile A {@link File}.
	 */
	public void setTargetFile(File file_targetFile);
	
	/**
	 * Get target file.
	 * @return A {@link File}.
	 */
	public File getTargetFile();
	
	/**
	 * Set encoding.
	 * @param string_encoding A {@link String} containing an encoding.
	 */
	public void setEncoding(String string_encoding);
	
	/**
	 * Get encoding.
	 * @return A {@link String} containing an encoding.
	 */
	public String getEncoding();
	
	/**
	 * Enable or disable column names.
	 * @param boolean_isColumnNamesEnabled "True" if first row contains column names or "false" otherwise.
	 */
	public void setColumnNamesEnabled(boolean boolean_isColumnNamesEnabled);
	
	/**
	 * Check if column names are enabled.
	 * @return "True" if first row contains column names or "false" otherwise.
	 */
	public boolean isColumnNamesEnabled();

	/**
	 * Set row separator.
	 * @param string_rowSeparator A {@link String} defining a row separator.
	 */
	public void setRowSeparator(String string_rowSeparator);
	
	/**
	 * Get row separator.
	 * @return A {@link String} defining a row separator.
	 */
	public String getRowSeparator();
	
	/**
	 * Set column separator.
	 * @param char_columnSeparator A character defining a column separator.
	 */
	public void setColumnSeparator(char char_columnSeparator);
	
	/**
	 * Get column separator.
	 * @return A character defining a column separator.
	 */
	public char getColumnSeparator();
	
	/**
	 * Set quote.
	 * @param char_quote A character defining a quote.
	 */
	public void setQuote(char char_quote);
	
	/**
	 * Get quote.
	 * @return A character defining a quote.
	 */
	public char getQuote();
	
	/**
	 * Add string mapping.
	 * @param string_key A {@link String} containing a key, e.g. "\\r\\n". If a row separator is equal to a key it is 
	 * replaced by the value.
	 * @param string_value A {@link String} containing a value, e.g. "\r\n". If a row separator is equal to a key it is 
	 * replaced by the value.
	 */
	public void addStringMapping(String string_key,String string_value);
	
	/**
	 * Get string mappings.
	 * @return A {@link Map} of {@link String}s and {@link String}s containing all string mappings.
	 */
	public Map<String,String> getStringMappings();
	
	/**
	 * Add character mapping.
	 * @param string_key A {@link String} containing a key, e.g. "\\r". If a column separator or quote is equal to a key it is 
	 * replaced by the value.
	 * @param string_value A {@link String} containing a value, e.g. "\r". If a column separator or quote is equal to a key it is 
	 * replaced by the value.
	 */
	public void addCharacterMapping(String string_key,Character character_value);
	
	/**
	 * Get character mappings.
	 * @return A {@link Map} of {@link String}s and {@link String}s containing all string mappings.
	 */
	public Map<String,Character> getCharacterMappings();

}