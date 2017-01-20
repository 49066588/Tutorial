package test_.writer_;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main_.transceiver_.ITransceiver;
import main_.transceiver_.NTransceiver;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import reader_.ICsvReader;
import reader_.NCsvReader;
import controller_.ICsvController;
import writer_.ICsvWriter;
import writer_.NCsvWriter;

public class NCsvWriterTest {
	
	////////////
	// FIELDS //
	////////////
	
	private int integer_rowCount = 3;
	
	private int integer_columnCount = 2;

	/////////////
	// METHODS //
	/////////////
	
	private ICsvReader getCsvReader() {
		
		// Create csv reader.
		ICsvReader iCsvReader_csvReader = new NCsvReader();
		
		// Set encoding.
		iCsvReader_csvReader.getCsvController().setEncoding("UTF-8");
		
		// Set row separator.
		iCsvReader_csvReader.getCsvController().setRowSeparator("\r\n");
		
		// Set column separator.
		iCsvReader_csvReader.getCsvController().setColumnSeparator(';');
		
		// Set quote.
		iCsvReader_csvReader.getCsvController().setQuote('\"');
		
		// Return csv reader.
		return iCsvReader_csvReader;
		
	}
	
	private ICsvWriter getCsvWriter() {
		
		// Create csv writer.
		ICsvWriter iCsvWriter_csvWriter = new NCsvWriter();

		// Set encoding.
		iCsvWriter_csvWriter.getCsvController().setEncoding("UTF-8");
		
		// Set row separator.
		iCsvWriter_csvWriter.getCsvController().setRowSeparator("\r\n");
		
		// Set column separator.
		iCsvWriter_csvWriter.getCsvController().setColumnSeparator(';');
		
		// Set quote.
		iCsvWriter_csvWriter.getCsvController().setQuote('\"');
		
		// Return csv writer.
		return iCsvWriter_csvWriter;
		
	}
	
	private String getChecksum(File file_file) {
		
		// Initialize file input stream.
		FileInputStream fileInputStream_fileInputStream = null;
		
		try {
			
			// Create file input stream.
			fileInputStream_fileInputStream = new FileInputStream(file_file);
			
		} catch (FileNotFoundException fileNotFoundException_exception) {
			
			String string_message = "Cannot get checksum. "+System.lineSeparator()+fileNotFoundException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		}
		
		// Initialize checksum
		String string_checksum = null;
		
		try {
			
			// Get check sum.
			string_checksum = DigestUtils.md5Hex(fileInputStream_fileInputStream);
			
		} catch (IOException ioException_exception) {
			
			String string_message = "Cannot get checksum. "+System.lineSeparator()+ioException_exception.getMessage();
			throw new IllegalStateException(string_message);
			
		}
		
		// Return checksum.
		return string_checksum;
		
	}
	
	///////////
	// TESTS //
	///////////
	
	/**
	 * Set null as csv controller.
	 */
	@Test
	public void T001() {
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Create csv controller.
		ICsvController iCsvController_csvController = null;
		
		try {
		
			// Set csv controller.
			iCsvWriter_csvWriter.setCsvController(iCsvController_csvController);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set csv controller. Input parameter \"iCsvController_csvController\" must not be null."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Open and close.
	 */
	@Test
	public void T002() {
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_1.csv");
		
		// Set target file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);

		// Check status.
		assertTrue(!iCsvWriter_csvWriter.isOpen());
		
		// Open writer.
		iCsvWriter_csvWriter.open();
		
		// Check status.
		assertTrue(iCsvWriter_csvWriter.isOpen());
		
		// Close writer.
		iCsvWriter_csvWriter.close();
		
		// Check status.
		assertTrue(!iCsvWriter_csvWriter.isOpen());
		
	}
	
	/**
	 * Create csv file with truncation and without column names.
	 */
	@Test
	public void T003() {
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_2.csv");
		
		// Set target file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);

		// Enable truncation.
		iCsvWriter_csvWriter.setTruncationEnabled(true);
		
		// Disable column names.
		iCsvWriter_csvWriter.getCsvController().setColumnNamesEnabled(false);

		// Open writer.
		iCsvWriter_csvWriter.open();
		
		List<Object> list_row = new ArrayList<Object>();
		
		for (int integer_rowIndex=0;integer_rowIndex<this.integer_rowCount;integer_rowIndex++) {
			
			list_row.clear();
			
			for (int integer_columnIndex=0;integer_columnIndex<this.integer_columnCount;integer_columnIndex++) {
				
				list_row.add(integer_rowIndex);
				
			}
			
			iCsvWriter_csvWriter.write(list_row);
			
		}
		
		// Close writer.
		iCsvWriter_csvWriter.close();
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_targetFile);
		
		// Disable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Check row count.
		assertTrue(iCsvReader_csvReader.getRowCount() == this.integer_rowCount);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Create csv file with truncation and with column names.
	 */
	@Test
	public void T004() {
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Set target file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);

		// Enable truncation.
		iCsvWriter_csvWriter.setTruncationEnabled(true);
		
		// Enable column names.
		iCsvWriter_csvWriter.getCsvController().setColumnNamesEnabled(true);
		
		// Add column names.
		for (int integer_columnNameIndex=0;integer_columnNameIndex<this.integer_columnCount;integer_columnNameIndex++) {
			
			iCsvWriter_csvWriter.addColumnName("MY_COLUMN_"+integer_columnNameIndex);
			
		}
		
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
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_targetFile);
		
		// Disable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Check row count.
		assertTrue(iCsvReader_csvReader.getRowCount() == (this.integer_rowCount+1));
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Write to existing csv file without truncation.
	 */
	@Test
	public void T005() {
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Set target file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);

		// Disable truncation.
		iCsvWriter_csvWriter.setTruncationEnabled(false);
		
		// Enable column names.
		iCsvWriter_csvWriter.getCsvController().setColumnNamesEnabled(true);
		
		// Add column names.
		for (int integer_columnNameIndex=0;integer_columnNameIndex<this.integer_columnCount;integer_columnNameIndex++) {
			
			iCsvWriter_csvWriter.addColumnName("MY_COLUMN_"+integer_columnNameIndex);
			
		}
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_targetFile);
		
		// Disable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Get row count.
		long long_rowCount = iCsvReader_csvReader.getRowCount();
		
		// Close reader.
		iCsvReader_csvReader.close();
		
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
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Check row count.
		assertTrue(iCsvReader_csvReader.getRowCount() == (long_rowCount+this.integer_rowCount+1));
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Write too many values when column names are enabled.
	 */
	@Test
	public void T006() {
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_4.csv");
		
		// Set target file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);

		// Disable truncation.
		iCsvWriter_csvWriter.setTruncationEnabled(false);
		
		// Enable column names.
		iCsvWriter_csvWriter.getCsvController().setColumnNamesEnabled(true);
		
		// Add column names.
		for (int integer_columnNameIndex=0;integer_columnNameIndex<this.integer_columnCount;integer_columnNameIndex++) {
			
			iCsvWriter_csvWriter.addColumnName("MY_COLUMN_"+integer_columnNameIndex);
			
		}

		// Open writer.
		iCsvWriter_csvWriter.open();
		
		// Initialize row.
		List<Object> list_row = new ArrayList<Object>();
		
		for (int integer_rowIndex=0;integer_rowIndex<this.integer_rowCount;integer_rowIndex++) {
			
			// Reset row.
			list_row.clear();
			
			// Add values.
			for (int integer_columnIndex=0;integer_columnIndex<this.integer_columnCount+1;integer_columnIndex++) {
				
				list_row.add(integer_rowIndex);
				
			}
			
			try {
			
				// Write row.
				iCsvWriter_csvWriter.write(list_row);
				
			} catch (IllegalStateException illegalStateException_exception) {
				
				// Check exception message.
				assertTrue(illegalStateException_exception.getMessage().equals("Cannot write. Row 1 contains too many values. Fetched 3 instead of 2 values."));
				
				// Close writer.
				iCsvWriter_csvWriter.close();

				return;
				
			}
		
			// Fail if this code block is reached.
			fail();
			
		}
		
	}
	
	/**
	 * Write too many values when column names are disabled.
	 */
	@Test
	public void T007() {
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_5.csv");
		
		// Set target file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);

		// Disable truncation.
		iCsvWriter_csvWriter.setTruncationEnabled(false);
		
		// Disable column names.
		iCsvWriter_csvWriter.getCsvController().setColumnNamesEnabled(false);

		// Open writer.
		iCsvWriter_csvWriter.open();
		
		// Initialize row.
		List<Object> list_row = new ArrayList<Object>();
		
		for (int integer_rowIndex=0;integer_rowIndex<this.integer_rowCount;integer_rowIndex++) {
			
			// Reset row.
			list_row.clear();
			
			if (integer_rowIndex == 0) {
				
				// Add values.
				for (int integer_columnIndex=0;integer_columnIndex<this.integer_columnCount;integer_columnIndex++) {
					
					list_row.add(integer_rowIndex);
					
				}
				
			} else {
				
				// Add values.
				for (int integer_columnIndex=0;integer_columnIndex<this.integer_columnCount+1;integer_columnIndex++) {
					
					list_row.add(integer_rowIndex);
					
				}
				
			}
	
			try {
			
				// Write row.
				iCsvWriter_csvWriter.write(list_row);
				
			} catch (IllegalStateException illegalStateException_exception) {
				
				// Check exception message.
				assertTrue(illegalStateException_exception.getMessage().equals("Cannot write. Row 2 contains too many values. Fetched 3 instead of 2 values."));
				
				// Close writer.
				iCsvWriter_csvWriter.close();

				return;
				
			}
			
		}
		
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Write too few values when column names are enabled.
	 */
	@Test
	public void T008() {
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_6.csv");
		
		// Set target file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);

		// Disable truncation.
		iCsvWriter_csvWriter.setTruncationEnabled(false);
		
		// Enable column names.
		iCsvWriter_csvWriter.getCsvController().setColumnNamesEnabled(true);
		
		// Add column names.
		for (int integer_columnNameIndex=0;integer_columnNameIndex<this.integer_columnCount;integer_columnNameIndex++) {
			
			iCsvWriter_csvWriter.addColumnName("MY_COLUMN_"+integer_columnNameIndex);
			
		}

		// Open writer.
		iCsvWriter_csvWriter.open();
		
		// Initialize row.
		List<Object> list_row = new ArrayList<Object>();
		
		for (int integer_rowIndex=0;integer_rowIndex<this.integer_rowCount;integer_rowIndex++) {
			
			// Reset row.
			list_row.clear();
			
			// Add values.
			for (int integer_columnIndex=0;integer_columnIndex<this.integer_columnCount-1;integer_columnIndex++) {
				
				list_row.add(integer_rowIndex);
				
			}
			
			try {
			
				// Write row.
				iCsvWriter_csvWriter.write(list_row);
				
			} catch (IllegalStateException illegalStateException_exception) {
				
				// Check exception message.
				assertTrue(illegalStateException_exception.getMessage().equals("Cannot write. Row 1 contains too few values. Fetched 1 instead of 2 values."));
				
				// Close writer.
				iCsvWriter_csvWriter.close();
				
				return;
				
			}
		
			// Fail if this code block is reached.
			fail();
			
		}
	
	}
	
	/**
	 * Write too few values when column names are disabled.
	 */
	@Test
	public void T009() {
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_7.csv");
		
		// Set target file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);

		// Disable truncation.
		iCsvWriter_csvWriter.setTruncationEnabled(false);
		
		// Disable column names.
		iCsvWriter_csvWriter.getCsvController().setColumnNamesEnabled(false);

		// Open writer.
		iCsvWriter_csvWriter.open();
		
		// Initialize row.
		List<Object> list_row = new ArrayList<Object>();
		
		for (int integer_rowIndex=0;integer_rowIndex<this.integer_rowCount;integer_rowIndex++) {
			
			// Reset row.
			list_row.clear();
			
			if (integer_rowIndex == 0) {
				
				// Add values.
				for (int integer_columnIndex=0;integer_columnIndex<this.integer_columnCount+1;integer_columnIndex++) {
					
					list_row.add(integer_rowIndex);
					
				}
				
			} else {
				
				// Add values.
				for (int integer_columnIndex=0;integer_columnIndex<this.integer_columnCount;integer_columnIndex++) {
					
					list_row.add(integer_rowIndex);
					
				}
				
			}
	
			try {
			
				// Write row.
				iCsvWriter_csvWriter.write(list_row);
				
			} catch (IllegalStateException illegalStateException_exception) {
				
				// Check exception message.
				assertTrue(illegalStateException_exception.getMessage().equals("Cannot write. Row 2 contains too few values. Fetched 2 instead of 3 values."));
				
				// Close writer.
				iCsvWriter_csvWriter.close();

				return;
				
			}
			
		}
		
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Create copy of an existing csv file and compare MD5 checksums.
	 */
	@Test
	public void T010() {
		
		// Create transceiver.
		ITransceiver iTransceiver_transceiver = new NTransceiver();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_8.csv");;
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Disable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Set reader.
		iTransceiver_transceiver.setReader(iCsvReader_csvReader);
		
		// Get csv writer.
		ICsvWriter iCsvWriter_csvWriter = this.getCsvWriter();

		// Set target file.
		iCsvWriter_csvWriter.getCsvController().setTargetFile(file_targetFile);
		
		// Disable truncation.
		iCsvWriter_csvWriter.setTruncationEnabled(true);
		
		// Enable column names.
		iCsvWriter_csvWriter.getCsvController().setColumnNamesEnabled(true);
		
		// Set writer.
		iTransceiver_transceiver.setWriter(iCsvWriter_csvWriter);
		
		// Transfer.
		iTransceiver_transceiver.execute();
		
		// Get checksum of source file.
		String string_sourceChecksum = this.getChecksum(file_sourceFile);
		
		// Get checksum of target file.
		String string_targetChecksum = this.getChecksum(file_targetFile);

		// Assert MD5 checksums are identical.
		assertTrue(string_sourceChecksum.equals(string_targetChecksum));
		
	}
	
}