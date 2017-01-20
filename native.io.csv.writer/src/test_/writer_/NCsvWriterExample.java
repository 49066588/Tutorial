package test_.writer_;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import writer_.ICsvWriter;
import writer_.NCsvWriter;

public class NCsvWriterExample {
	
	////////////
	// FIELDS //
	////////////
	
	private int integer_rowCount = 20;
	
	private int integer_columnCount = 5;
	
	///////////
	// TESTS //
	///////////
	
	/**
	 * Write to csv file.
	 */
	@Test
	public void T001() {
		
		// Get time stamp.
		long long_start = System.currentTimeMillis();
		
		// Create csv writer.
		ICsvWriter iCsvWriter_csvWriter = new NCsvWriter();
		
		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_5.csv");
		
		// Set source file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);
		
		// Enable column names.
		iCsvWriter_csvWriter.getCsvController().setColumnNamesEnabled(true);
		
		// Set encoding.
		iCsvWriter_csvWriter.getCsvController().setEncoding("UTF-8");
		
		// Set row separator.
		iCsvWriter_csvWriter.getCsvController().setRowSeparator("\r\n");
		
		// Set column separator.
		iCsvWriter_csvWriter.getCsvController().setColumnSeparator(';');
		
		// Set quote.
		iCsvWriter_csvWriter.getCsvController().setQuote('\"');
		
		// Open writer.
		iCsvWriter_csvWriter.open();
		
		// Initialize row.
		List<Object> list_row = new ArrayList<Object>();
		
		for (int integer_rowIndex=0;integer_rowIndex<this.integer_rowCount;integer_rowIndex++) {
			
			// Reset row.
			list_row.clear();
			
			// Add values.
			for (int integer_columnIndex=0;integer_columnIndex<this.integer_columnCount;integer_columnIndex++) {
				
				list_row.add(integer_rowIndex);
				
			}
			
			// Write row.
			iCsvWriter_csvWriter.write(list_row);
			
		}
		
		// Close writer.
		iCsvWriter_csvWriter.close();
		
		// Get time stamp.
		long long_end = System.currentTimeMillis();
		
		System.out.println("RUNTIME: "+(long_end-long_start)+" ms");
		
	}
	
}