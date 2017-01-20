package controller_;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class NCsvController implements ICsvController {

	////////////
	// FIELDS //
	////////////

	private File file_sourceFile = null;
	
	private File file_targetFile = null;
	
	private String string_encoding = "UTF-8";

	private boolean boolean_isColumnNamesEnabled = true;
	
	private String string_rowSeparator = "\r\n";
	
	private char char_columnSeparator = ',';
		
	private char char_quote = '"';
	
	private Map<String,String> map_stringMappings = new HashMap<String,String>();

	private Map<String,Character> map_characterMappings = new HashMap<String,Character>();

	//////////////////
	// CONSTRUCTORS //
	//////////////////
	
	public NCsvController() {
		
		// Add string mappings.
		this.addStringMapping("\\r\\n","\r\n");
		
		// Add character mappings.
		this.addCharacterMapping("\\r",'\r');
		
		this.addCharacterMapping("\\n",'\n');
		
		this.addCharacterMapping("\\t",'\t');
		
	}
	
	/////////////
	// METHODS //
	/////////////
	
	@Override
	public void setSourceFile(File file_sourceFile) {
		
		// Check input parameter.
		if (file_sourceFile == null) {

			String string_message = "Cannot set source file. Input parameter \"file_sourceFile\" must not be null.";
			throw new IllegalStateException(string_message);

		}
		
		if (!file_sourceFile.getParentFile().exists()) {
			
			String string_message = "Cannot set source file. Parent of input parameter \"file_sourceFile\" must exist.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (!file_sourceFile.getParentFile().isDirectory()) {
			
			String string_message = "Cannot set source file. Parent of input parameter \"file_sourceFile\" must be a folder.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (!file_sourceFile.exists()) {
			
			String string_message = "Cannot set source file. Input parameter \"file_sourceFile\" must exist.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (!file_sourceFile.isFile()) {
			
			String string_message = "Cannot set source file. Input parameter \"file_sourceFile\" must be a file.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.file_sourceFile = file_sourceFile;
		
	}
	
	@Override
	public File getSourceFile() {
		
		// Assert source file has been set.
		if (this.file_sourceFile == null) {
			
			// Throw exception.
			String string_message = "Cannot get source file. Source file has not been set.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Return source file.
		return this.file_sourceFile;
		
	}
	
	@Override
	public void setTargetFile(File file_targetFile) {
		
		// Check input parameter.
		if (file_targetFile == null) {

			String string_message = "Cannot set target file. Input parameter \"file_targetFile\" must not be null.";
			throw new IllegalStateException(string_message);

		}
		
		if (!file_targetFile.getParentFile().exists()) {
			
			String string_message = "Cannot set target file. Parent of input parameter \"file_targetFile\" must exist.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (!file_targetFile.getParentFile().isDirectory()) {
			
			String string_message = "Cannot set target file. Parent of input parameter \"file_targetFile\" must be a folder.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (file_targetFile.exists()) {
			
			if (!file_targetFile.isFile()) {
				
				String string_message = "Cannot set target file. Input parameter \"file_targetFile\" exists but is not a file.";
				throw new IllegalStateException(string_message);
				
			}
			
		}
		
		// Assign input parameter.
		this.file_targetFile = file_targetFile;
		
	}
	
	@Override
	public File getTargetFile() {
		
		// Assert target file has been set.
		if (this.file_targetFile == null) {
			
			// Throw exception.
			String string_message = "Cannot get target file. Target file has not been set.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Return target file.
		return this.file_targetFile;
		
	}
	
	@Override
	public void setEncoding(String string_encoding) {
		
		// Check input parameter.
		if (string_encoding == null) {
			
			String string_message = "Cannot set encoding. Input parameter \"string_encoding\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (string_encoding.trim().isEmpty()) {
			
			String string_message = "Cannot set encoding. Input parameter \"string_encoding\" must not be blank.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (!Charset.availableCharsets().keySet().contains(string_encoding)) {
			
			// Get supported encodings.
			String string_supportedEncodings = "\""+String.join("\", \"",Charset.availableCharsets().keySet())+"\"";
			
			String string_message = "Cannot set encoding. Encoding \""+string_encoding+"\" is not supported. Please choose from "+string_supportedEncodings+".";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.string_encoding = string_encoding;
		
	}
	
	@Override
	public String getEncoding() {
		
		// Assert encoding has been set.
		if (this.string_encoding == null) {
			
			String string_message = "Cannot get encoding. Encoding has not been set.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Return encoding.
		return this.string_encoding;
		
	}
	
	@Override
	public void setColumnNamesEnabled(boolean boolean_isColumnNamesEnabled) {
		
		// Assign input parameter.
		this.boolean_isColumnNamesEnabled = boolean_isColumnNamesEnabled;
		
	}

	@Override
	public boolean isColumnNamesEnabled() {
		
		// Return status.
		return this.boolean_isColumnNamesEnabled;
		
	}

	@Override
	public void setRowSeparator(String string_rowSeparator) {
		
		// Check input parameter.
		if (string_rowSeparator == null) {
			
			String string_message = "Cannot set row separator. Input parameter \"string_rowSeparator\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (string_rowSeparator.isEmpty()) {
			
			String string_message = "Cannot set row separator. Input parameter \"string_rowSeparator\" must not be empty.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.string_rowSeparator = string_rowSeparator;

	}

	@Override
	public String getRowSeparator() {
		
		// Assert row separator has been set.
		if (string_rowSeparator == null) {
			
			String string_message = "Cannot get row separator. Row separator has not been set.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Return row separator.
		return this.string_rowSeparator;
		
	}

	@Override
	public void setColumnSeparator(char char_columnSeparator) {

		// Assign input parameter.
		this.char_columnSeparator = char_columnSeparator;

	}

	@Override
	public char getColumnSeparator() {

		// Return column separator.
		return this.char_columnSeparator;
		
	}

	@Override
	public void setQuote(char char_quote) {

		// Assign input parameter.
		this.char_quote = char_quote;
		
	}

	@Override
	public char getQuote() {

		// Return quote.
		return this.char_quote;
		
	}

	@Override
	public void addStringMapping(String string_key,String string_value) {
		
		// Check input parameter.
		if (string_key == null) {
			
			String string_message = "Cannot add string mapping. Input parameter \"string_key\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (string_key.trim().isEmpty()) {
			
			String string_message = "Cannot add string mapping. Input parameter \"string_key\" must not be blank.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (string_value == null) {
			
			String string_message = "Cannot add string mapping. Input parameter \"string_value\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (this.map_stringMappings.containsKey(string_key)) {

			String string_message = "Cannot add string mapping. Key \""+string_key+"\" has already been mapped.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.map_stringMappings.put(string_key,string_value);
		
	}

	@Override
	public Map<String,String> getStringMappings() {
		
		// Return string mappings.
		return this.map_stringMappings;
		
	}

	@Override
	public void addCharacterMapping(String string_key, Character character_value) {

		// Check input parameter.
		if (string_key == null) {
			
			String string_message = "Cannot add character mapping. Input parameter \"string_key\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (string_key.trim().isEmpty()) {
			
			String string_message = "Cannot add character mapping. Input parameter \"string_key\" must not be blank.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (this.map_characterMappings.containsKey(string_key)) {

			String string_message = "Cannot add character mapping. Key \""+string_key+"\" has already been mapped.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.map_characterMappings.put(string_key,character_value);
		
	}

	@Override
	public Map<String, Character> getCharacterMappings() {
		
		// Get character mappings.
		return this.map_characterMappings;
		
	}
	
}