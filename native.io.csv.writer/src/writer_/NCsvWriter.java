package writer_;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import main_.writer_.AWriter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import controller_.ICsvController;
import controller_.NCsvController;

public class NCsvWriter extends AWriter implements ICsvWriter {

	////////////
	// FIELDS //
	////////////
	
	private ICsvController iCsvController_csvController = new NCsvController();

	private CSVPrinter csvPrinter_csvPrinter = null;
	
	private int integer_valueCount = -1;
	
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
	
	private Writer getWriter() {

		// Initialize writer.
		Writer writer_writer = null;
		
		try {
			
			// Create writer.
			writer_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.getCsvController().getTargetFile().getAbsolutePath(),!this.isTruncationEnabled()),this.getCsvController().getEncoding()));

		} catch (UnsupportedEncodingException unsupportedEncodingException_exception) {
			
			String string_message = "Cannot get writer." + System.lineSeparator() + unsupportedEncodingException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		} catch (IOException ioException_exception) {
			
			String string_message = "Cannot get writer." + System.lineSeparator() + ioException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		}

	    // Return writer.
	    return writer_writer;
	    
	}
	
	@Override
	public void open() {
		
		// Return if writer is open.
		if (this.isOpen()) {
			
			return;
			
		}
		
		// Get writer.
		Writer writer_writer = this.getWriter();

		// Initialize CSV format.
		CSVFormat csvFormat_csvFormat = CSVFormat.RFC4180;
		
		String[] stringArray_columnNames = null;
		
		// Set enable column names if required.
		if (this.getCsvController().isColumnNamesEnabled()) {
			
			// Get column name count.
			int integer_columnNameCount = this.getColumnNames().size();
			
			// Initialize column name array.
			stringArray_columnNames = new String[integer_columnNameCount];
			
			// Add column names to column name array.
			for (int integer_columnNameIndex=0;integer_columnNameIndex<integer_columnNameCount;integer_columnNameIndex++) {
				
				// Capitalize column names if required.
				if (this.isCapitalizeColumnNamesEnabled()) {
					
					stringArray_columnNames[integer_columnNameIndex] = this.getColumnNames().get(integer_columnNameIndex).toUpperCase();
					
				} else {
				
					stringArray_columnNames[integer_columnNameIndex] = this.getColumnNames().get(integer_columnNameIndex);
					
				}
				
			}
			
			// Set column name array.
			csvFormat_csvFormat = csvFormat_csvFormat.withHeader(stringArray_columnNames);

		}
		
		// Set row separator.
		csvFormat_csvFormat = csvFormat_csvFormat.withRecordSeparator(this.getCsvController().getRowSeparator());

		// Set column separator.
		csvFormat_csvFormat = csvFormat_csvFormat.withDelimiter(this.getCsvController().getColumnSeparator());
		
		// Set quote.
		csvFormat_csvFormat = csvFormat_csvFormat.withQuote(this.getCsvController().getQuote());
		
		try {
			
			// Create CSV printer.
			this.csvPrinter_csvPrinter = new CSVPrinter(writer_writer,csvFormat_csvFormat);

		} catch (IOException ioException_exception) {
			
			String string_message = "Cannot open writer."+System.lineSeparator()+ioException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		}
		
	}
	
	@Override
	public boolean isOpen() {
		
		// Get status.
		boolean boolean_isOpen = (this.csvPrinter_csvPrinter != null);
		
		// Return status.
		return boolean_isOpen;
		
	}

	@Override
	public void write(List<Object> list_values) {
		
		// Get value count.
		int integer_valueCount = list_values.size();
		
		if (this.getCsvController().isColumnNamesEnabled()) {
		
			// Get column count.
			long long_columnCount = this.getColumnNames().size();
			
			// Check if there are too many values.
			if (integer_valueCount > long_columnCount) {
				
				String string_message = "Cannot write. Row "+(this.getRowNumber()+1)+" contains too many values. Fetched "+integer_valueCount+" instead of "+long_columnCount+" values.";
				throw new IllegalStateException(string_message);
				
			}
			
			// Check if there are too few values.
			if (integer_valueCount < long_columnCount) {
				
				String string_message = "Cannot write. Row "+(this.getRowNumber()+1)+" contains too few values. Fetched "+integer_valueCount+" instead of "+long_columnCount+" values.";
				throw new IllegalStateException(string_message);
				
			}
			
		} else {
			
			if (this.integer_valueCount == -1) {
				
				this.integer_valueCount = integer_valueCount;
				
			}
			
			// Check if there are too many values.
			if (integer_valueCount > this.integer_valueCount) {
				
				String string_message = "Cannot write. Row "+(this.getRowNumber()+1)+" contains too many values. Fetched "+integer_valueCount+" instead of "+this.integer_valueCount+" values.";
				throw new IllegalStateException(string_message);
				
			}
			
			// Check if there are too few values.
			if (integer_valueCount < this.integer_valueCount) {
				
				String string_message = "Cannot write. Row "+(this.getRowNumber()+1)+" contains too few values. Fetched "+integer_valueCount+" instead of "+this.integer_valueCount+" values.";
				throw new IllegalStateException(string_message);
				
			}
			
		}
		
		// Initialize value array.
		Object[] arrayObject_values = new Object[integer_valueCount];
		
		// Copy value list to value array.
		arrayObject_values = list_values.toArray(arrayObject_values);

		try {

			// Delegate value array to CSV printer.
			this.csvPrinter_csvPrinter.printRecord(arrayObject_values);
			
		} catch (IOException ioException_exception) {
			
			String string_message = "Cannot write." + System.lineSeparator() + ioException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		}
		
		// Increment row number.
		this.long_rowNumber++;
		
	}
	
	@Override
	public long getRowNumber() {
		
		// Return row number
		return this.long_rowNumber;
		
	}

	@Override
	public void close() {
		
		// Return if writer is closed.
		if (!this.isOpen()) {
			
			return;
			
		}
		
		try {

			// Close CSV printer.
			this.csvPrinter_csvPrinter.close();

		} catch (IOException ioException_exception) {
	
			String string_message = "Cannot close writer." + System.lineSeparator() + ioException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		}
		
		// Reset CSV printer.
		this.csvPrinter_csvPrinter = null;
		
		// Reset value count.
		this.integer_valueCount = -1;
		
		// Reset row number.
		this.long_rowNumber = 0;
		
	}

}