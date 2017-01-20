package reader_;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main_.reader_.AReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;

import controller_.ICsvController;
import controller_.NCsvController;

public class NCsvReader extends AReader implements ICsvReader {

	////////////
	// FIELDS //
	////////////
	
	private ICsvController iCsvController_csvController = new NCsvController();
	
	private CSVParser csvParser_csvParser = null;
	
	private Iterator<CSVRecord> iterator_csvRecords = null;
	
	private CSVRecord csvRecord_csvRecord = null;
	
	private List<String> list_columnNames = null;
	
	private long long_rowNumber = 0;

	/////////////
	// METHODS //
	/////////////
	
	@Override
	public void setCsvController(ICsvController iCsvController_csvController) {
		
		// Check input parameter.
		if (iCsvController_csvController == null) {
			
			String string_message = "Cannot set csv controller. Input parameter \"iCsvController_csvController\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.iCsvController_csvController = iCsvController_csvController;
		
	}
	
	@Override
	public ICsvController getCsvController() {

		// Check input parameter.
		if (this.iCsvController_csvController == null) {
			
			String string_message = "Cannot get csv controller. Csv controller has not been set.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Return csv controller.
		return this.iCsvController_csvController;
		
	}

	private Reader getReader() {
		
		// Initialize input stream.
    	InputStream inputStream_inputStream = null;
    	
	    try {
	    	
	    	// Get input stream.
	    	inputStream_inputStream = new FileInputStream(this.getCsvController().getSourceFile());
	    	
		} catch (FileNotFoundException fileNotFoundException_exception) {
			
			// Throw exception if file cannot be found.
			String string_message = "Cannot get reader." + System.lineSeparator() + fileNotFoundException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		}
	    
	    // Initialize reader.
	    Reader reader_reader = null;
	    
	    try {
	    	
	    	// Turn input stream into byte order mark input stream.
	        BOMInputStream bomInputStream_bomInputStream = new BOMInputStream(inputStream_inputStream);
	        
	        // Get byte order mark.
	        ByteOrderMark byteOrderMark_byteOrderMark = bomInputStream_bomInputStream.getBOM();

	        // Get character set name.
	        String string_charsetName = byteOrderMark_byteOrderMark == null ? this.getCsvController().getEncoding() : byteOrderMark_byteOrderMark.getCharsetName();

	        // Create input stream reader.
	        reader_reader = new InputStreamReader(new BufferedInputStream(bomInputStream_bomInputStream),string_charsetName);

		} catch (UnsupportedEncodingException unsupportedEncodingException_exception) {
			
			String string_message = "Cannot get reader." + System.lineSeparator() + unsupportedEncodingException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		} catch (IOException ioException_exception) {
			
			String string_message = "Cannot get reader." + System.lineSeparator() + ioException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		}

	    // Return reader.
	    return reader_reader;
	    
	}
	
	@Override
	public void open() {
		
		// Return if reader is open.
		if (this.isOpen()) {
			
			return;
			
		}
		
		// Initialize csv format.
		CSVFormat csvFormat_csvFormat = CSVFormat.RFC4180;
		
		// Enable column names if required.
		if (this.getCsvController().isColumnNamesEnabled()) {
		
			csvFormat_csvFormat = csvFormat_csvFormat.withFirstRecordAsHeader();

		}
		
		// Set row separator.
		csvFormat_csvFormat = csvFormat_csvFormat.withRecordSeparator(this.getCsvController().getRowSeparator());

		// Set column separator.
		csvFormat_csvFormat = csvFormat_csvFormat.withDelimiter(this.getCsvController().getColumnSeparator());
		
		// Set quote.
		csvFormat_csvFormat = csvFormat_csvFormat.withQuote(this.getCsvController().getQuote());
		
		try {
			
			// Create csv parser.
			this.csvParser_csvParser = new CSVParser(this.getReader(),csvFormat_csvFormat);
			
			// Get csv record iterator.
			this.iterator_csvRecords = this.csvParser_csvParser.iterator();

		} catch (IOException ioException_exception) {
			
			String string_message = "Cannot open reader."+System.lineSeparator()+ioException_exception.getMessage();
			throw new IllegalStateException(string_message);

		}

	}
	
	@Override
	public boolean isOpen() {
		
		// Get status.
		boolean boolean_isOpen = (this.csvParser_csvParser != null);

		// Return status.
		return boolean_isOpen;
		
	}

	@Override
	public List<String> getColumnNames() {
		
		// Assert reader is open.
		if (!this.isOpen()) {
			
			String string_message = "Cannot get column names. Reader is closed.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (this.list_columnNames == null) {
			
			// Create column names list.
			this.list_columnNames = new ArrayList<String>();

			if (this.getCsvController().isColumnNamesEnabled()) {
			
				// Get column names from csv parser.
				Map<String,Integer> map_columnNames = this.csvParser_csvParser.getHeaderMap();
				
				for (String string_columnName : map_columnNames.keySet()) {
					
					if (string_columnName.trim().isEmpty()) {
						
						String string_message = "Cannot get column names. Column name "+(map_columnNames.get(string_columnName)+1)+" must not be blank.";
						throw new IllegalStateException(string_message);
						
					}
					
				}
				
				// Copy column names to list.
				this.list_columnNames.addAll(map_columnNames.keySet());

			} else {
				
				// Initialize csv format.
				CSVFormat csvFormat_csvFormat = CSVFormat.RFC4180;
				
				// Set row separator.
				csvFormat_csvFormat = csvFormat_csvFormat.withRecordSeparator(this.getCsvController().getRowSeparator());
	
				// Set column separator.
				csvFormat_csvFormat = csvFormat_csvFormat.withDelimiter(this.getCsvController().getColumnSeparator());
				
				// Set quote.
				csvFormat_csvFormat = csvFormat_csvFormat.withQuote(this.getCsvController().getQuote());
				
				try {
					
					// Create csv parser.
					CSVParser csvParser_csvParser = new CSVParser(this.getReader(),csvFormat_csvFormat);
					
					// Get csv record iterator.
					Iterator<CSVRecord> iterator_csvRecords = csvParser_csvParser.iterator();
	
					if (iterator_csvRecords.hasNext()) {
			
						// Get first csv record.
						CSVRecord csvRecord_csvRecord = iterator_csvRecords.next();
						
						// Initialize column count.
						int integer_columnCount = 0;
						
						// Get column count.
						while (true) {
							
							try {
							
								csvRecord_csvRecord.get(integer_columnCount);
	
							} catch (Exception exception_exception) {
								
								break;
								
							}
							
							integer_columnCount++;
							
						}
						
						// Add default column names.
						for (int integer_columnIndex=0;integer_columnIndex<integer_columnCount;integer_columnIndex++) {
							
							this.list_columnNames.add("COLUMN_"+integer_columnIndex);
							
						}
	
					}
					
					// Close csv parser.
					csvParser_csvParser.close();
					
				} catch (IOException ioException_exception) {
					
					String string_message = "Cannot get column names."+System.lineSeparator()+ioException_exception.getMessage();
					throw new IllegalStateException(string_message);
	
				}
				
			}
			
		}

		// Return column names.
		return this.list_columnNames;
		
	}

	@Override
	public long getRowCount() {
		
		// Assert reader is open.
		if (!this.isOpen()) {
			 
			String string_message = "Cannot get row count. Reader is closed.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Initialize row count.
		long long_rowCount = 0;
		
		// Initialize csv format.
		CSVFormat csvFormat_csvFormat = CSVFormat.RFC4180;
		
		// Set first row contains column names if required.
		if (this.getCsvController().isColumnNamesEnabled()) {
		
			csvFormat_csvFormat = csvFormat_csvFormat.withFirstRecordAsHeader();

		}
		
		// Set row separator.
		csvFormat_csvFormat = csvFormat_csvFormat.withRecordSeparator(this.getCsvController().getRowSeparator());
		
		// Set column separator.
		csvFormat_csvFormat = csvFormat_csvFormat.withDelimiter(this.getCsvController().getColumnSeparator());
		
		// Set quote.
		csvFormat_csvFormat = csvFormat_csvFormat.withQuote(this.getCsvController().getQuote());
		
		try {
			
			// Create csv parser.
			CSVParser csvParser_csvParser = new CSVParser(this.getReader(),csvFormat_csvFormat);
			
			// Get csv record iterator.
			Iterator<CSVRecord> iterator_csvRecords = csvParser_csvParser.iterator();
			
			while (iterator_csvRecords.hasNext()) {
				
				// Pop next csv record.
				iterator_csvRecords.next();
				
				// Increment row count.
				long_rowCount++;
				
			}

			// Close csv parser.
			csvParser_csvParser.close();

		} catch (IOException ioException_exception) {
			
			String string_message = "Cannot get row count."+System.lineSeparator()+ioException_exception.getMessage();
			throw new IllegalStateException(string_message);

		}
		
		return long_rowCount;
		
	}

	@Override
	public List<Object> read() {
		
		// Assert reader is open.
		if (!this.isOpen()) {
			 
			String string_message = "Cannot read. Reader is closed.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Initialize list of values.
		List<Object> list_values = null;
		
		if (this.iterator_csvRecords.hasNext()) {

			// Increment row number.
			this.long_rowNumber++;
			
			// If a positive limit is exceeded ...
			if (this.getLimit() > 0 && this.getRowNumber() > getLimit()) {
				
				// ... decrement row number ...
				this.long_rowNumber--;
				
				// ... and return null.
				return null;
				
			}
		
			// Create list of values.
			list_values = new ArrayList<Object>();
			
			// Get next csv record.
			this.csvRecord_csvRecord = this.iterator_csvRecords.next();
			
			// Check if number of column names is larger than number of row items.
			if (this.getColumnCount() > this.csvRecord_csvRecord.size()) {
				
				String string_message = "Cannot read. Row "+this.getRowNumber()+" contains too few values. Fetched "+this.csvRecord_csvRecord.size()+" instead of "+this.getColumnCount()+" values.";
				throw new IllegalStateException(string_message);
				
			}
			
			// Check if number of column names is smaller than number of row items.
			if (this.getColumnCount() < this.csvRecord_csvRecord.size()) {
				
				String string_message = "Cannot read. Row "+this.getRowNumber()+" contains too many values. Fetched "+this.csvRecord_csvRecord.size()+" instead of "+this.getColumnCount()+" values.";
				throw new IllegalStateException(string_message);
				
			}
			
			for (String string_columnName : this.getColumnNames()) {
				
				// Get value.
				String string_value = null;
				
				if (this.getCsvController().isColumnNamesEnabled()) {
				
					string_value = this.csvRecord_csvRecord.get(this.csvParser_csvParser.getHeaderMap().get(string_columnName));
					
				} else {
					
					string_value = this.csvRecord_csvRecord.get(this.getColumnNames().indexOf(string_columnName));
					
				}
				
				// Add value to list.
				list_values.add(string_value);
				
			}

		}
		
		// Return list of values.
		return list_values;

	}

	@Override
	public long getRowNumber() {
		
		// Return row number
		return this.long_rowNumber;
		
	}
	
	@Override
	public void close() {
		
		// Return if reader is closed.
		if (!this.isOpen()) {
			
			return;
			
		}

		try {
			
			// Close csv parser.
			this.csvParser_csvParser.close();
			
		} catch (IOException ioException_exception) {
			
			String string_message = "Cannot close reader."+System.lineSeparator()+ioException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		}
		
		// Reset csv parser.
		this.csvParser_csvParser = null;
		
		// Reset iterator.
		this.iterator_csvRecords = null;
		
		// Reset csv record.
		this.csvRecord_csvRecord = null;
		
		// Reset column names.
		this.list_columnNames = null;
		
		// Reset row number.
		this.long_rowNumber = 0;
		
	}

}