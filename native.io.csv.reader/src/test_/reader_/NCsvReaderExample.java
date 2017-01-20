package test_.reader_;

import java.io.File;
import java.util.List;

import org.junit.Test;

import reader_.ICsvReader;
import reader_.NCsvReader;

public class NCsvReaderExample {

	///////////
	// TESTS //
	///////////
	
	/**
	 * Read from csv file.
	 */
	@Test
	public void T001() {
		
		// Get time stamp.
		long long_start = System.currentTimeMillis();
		
		// Create csv reader.
		ICsvReader iCsvReader_csvReader = new NCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names to read column names from first row.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Set encoding.
		iCsvReader_csvReader.getCsvController().setEncoding("UTF-8");
		
		// Set row separator.
		iCsvReader_csvReader.getCsvController().setRowSeparator("\r\n");
		
		// Set column separator.
		iCsvReader_csvReader.getCsvController().setColumnSeparator(';');
		
		// Set quote.
		iCsvReader_csvReader.getCsvController().setQuote('\"');
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Get row count.
		long long_rowCount = iCsvReader_csvReader.getRowCount();
		
		// Print row count.
		System.out.println("ROW COUNT: "+long_rowCount);
		
		// Get column count.
		long long_columnCount = iCsvReader_csvReader.getColumnCount();
		
		// Print column count.
		System.out.println("COLUMN COUNT: "+long_columnCount);

		// Get column names.
		List<String> list_columnNames = iCsvReader_csvReader.getColumnNames();
		
		// Print column names.
		System.out.println("COLUMN NAMES: "+list_columnNames);
		
		// Initialize row.
		List<Object> list_row = null;
		
		// Read rows.
		while ((list_row = iCsvReader_csvReader.read()) != null) {
			
			// Get row number.
			long long_rowNumber = iCsvReader_csvReader.getRowNumber();
			
			// Print row number and values.
			System.out.println("("+long_rowNumber+") - "+list_row);

		}
		
		// Close reader.
		iCsvReader_csvReader.close();
		
		// Get time stamp.
		long long_end = System.currentTimeMillis();
		
		System.out.println("RUNTIME: "+(long_end-long_start)+" ms");
		
	}
	
}