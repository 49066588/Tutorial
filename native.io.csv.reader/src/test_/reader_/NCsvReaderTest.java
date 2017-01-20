package test_.reader_;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import controller_.ICsvController;
import reader_.ICsvReader;
import reader_.NCsvReader;

public class NCsvReaderTest {
	
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

	///////////
	// TESTS //
	///////////
	
	/**
	 * Set null as csv controller.
	 */
	@Test
	public void T001() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create csv controller.
		ICsvController iCsvController_csvController = null;
		
		try {
		
			// Set csv controller.
			iCsvReader_csvReader.setCsvController(iCsvController_csvController);
			
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
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_1.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Check status.
		assertTrue(!iCsvReader_csvReader.isOpen());
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Check status.
		assertTrue(iCsvReader_csvReader.isOpen());
		
		// Close reader.
		iCsvReader_csvReader.close();
		
		// Check status.
		assertTrue(!iCsvReader_csvReader.isOpen());
		
	}
	
	/**
	 * Get column names when reader is closed.
	 */
	@Test
	public void T003() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_1.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		try {
		
			// Get column names.
			iCsvReader_csvReader.getColumnNames();
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot get column names. Reader is closed."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Check column names when file is empty and column names are enabled.
	 */
	@Test
	public void T004() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_1.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check column names.
		assertTrue(iCsvReader_csvReader.getColumnNames().size() == 0);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Check column names when file is empty and column names are disabled.
	 */
	@Test
	public void T005() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_1.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check column names.
		assertTrue(iCsvReader_csvReader.getColumnCount() == 0);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Check column names when file contains column names only and column names are enabled.
	 */
	@Test
	public void T006() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_2.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check column names.
		assertTrue(iCsvReader_csvReader.getColumnCount() == 2);
		
		assertTrue(iCsvReader_csvReader.getColumnNames().get(0).equals("C_0"));
		
		assertTrue(iCsvReader_csvReader.getColumnNames().get(1).equals("C_1"));
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Check column names when file contains column names only and column names are disabled.
	 */
	@Test
	public void T007() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_2.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names to read column names from first row.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check column names.
		assertTrue(iCsvReader_csvReader.getColumnNames().size() == 2);
		
		assertTrue(iCsvReader_csvReader.getColumnNames().get(0).equals("COLUMN_0"));
		
		assertTrue(iCsvReader_csvReader.getColumnNames().get(1).equals("COLUMN_1"));
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Check column names when file contains not only column names and column names are enabled.
	 */
	@Test
	public void T008() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check column names.
		assertTrue(iCsvReader_csvReader.getColumnCount() == 2);
		
		assertTrue(iCsvReader_csvReader.getColumnNames().get(0).equals("C_0"));
		
		assertTrue(iCsvReader_csvReader.getColumnNames().get(1).equals("C_1"));
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Check column names when file contains not only column names and column names are enabled.
	 */
	@Test
	public void T009() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check column names.
		assertTrue(iCsvReader_csvReader.getColumnCount() == 2);
		
		assertTrue(iCsvReader_csvReader.getColumnNames().get(0).equals("COLUMN_0"));
		
		assertTrue(iCsvReader_csvReader.getColumnNames().get(1).equals("COLUMN_1"));
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Get column names when column names are enabled and there are blank column names.
	 */
	@Test
	public void T010() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_4.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		try {
		
			// Get column names.
			iCsvReader_csvReader.getColumnNames();
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot get column names. Column name 1 must not be blank."));
			
			// Close reader.
			iCsvReader_csvReader.close();
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Check row count when file is empty.
	 */
	@Test
	public void T011() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_1.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check row count.
		assertTrue(iCsvReader_csvReader.getRowCount() == 0);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check row count.
		assertTrue(iCsvReader_csvReader.getRowCount() == 0);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Check row count when file contains only column names.
	 */
	@Test
	public void T012() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_2.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check row count.
		assertTrue(iCsvReader_csvReader.getRowCount() == 0);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check row count.
		assertTrue(iCsvReader_csvReader.getRowCount() == 1);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Check row count when file contains not only column names.
	 */
	@Test
	public void T013() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check row count.
		assertTrue(iCsvReader_csvReader.getRowCount() == 2);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Check row count.
		assertTrue(iCsvReader_csvReader.getRowCount() == 3);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Read when file is empty and column names are enabled.
	 */
	@Test
	public void T014() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_1.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Initialize row.
		List<Object> list_row = null;

		// Initialize loop counter.
		int integer_loopCounter = 0;
		
		// Read.
		while ((list_row = iCsvReader_csvReader.read()) != null) {
			
			// Increment loop counter.
			integer_loopCounter++;
			
			// Check row number.
			assertTrue(iCsvReader_csvReader.getRowNumber() == integer_loopCounter);
			
			if (!iCsvReader_csvReader.getCsvController().isColumnNamesEnabled()) {
				
				assertTrue(list_row.size() == 2);
				
				if (integer_loopCounter == 1) {
					
					assertTrue(list_row.get(0).equals("C_0"));
					
					assertTrue(list_row.get(1).equals("C_1"));
					
				} else {
					
					assertTrue(list_row.get(0).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
					assertTrue(list_row.get(1).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
				}
				
			} else {
				
				assertTrue(list_row.size() == 2);
				
				assertTrue(list_row.get(0).equals(""+iCsvReader_csvReader.getRowNumber()));
				
				assertTrue(list_row.get(1).equals(""+iCsvReader_csvReader.getRowNumber()));
				
			}
			
		}
		
		// Check loop counter.
		assertTrue(integer_loopCounter == 0);
		
		// Close reader.
		iCsvReader_csvReader.close();

	}
	
	/**
	 * Read when file is empty and column names are disabled.
	 */
	@Test
	public void T015() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_1.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Initialize row.
		List<Object> list_row = null;

		// Initialize loop counter.
		int integer_loopCounter = 0;
		
		// Read.
		while ((list_row = iCsvReader_csvReader.read()) != null) {
			
			// Increment loop counter.
			integer_loopCounter++;
			
			// Check row number.
			assertTrue(iCsvReader_csvReader.getRowNumber() == integer_loopCounter);
			
			if (!iCsvReader_csvReader.getCsvController().isColumnNamesEnabled()) {
				
				assertTrue(list_row.size() == 2);
				
				if (integer_loopCounter == 1) {
					
					assertTrue(list_row.get(0).equals("C_0"));
					
					assertTrue(list_row.get(1).equals("C_1"));
					
				} else {
					
					assertTrue(list_row.get(0).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
					assertTrue(list_row.get(1).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
				}
				
			} else {
				
				assertTrue(list_row.size() == 2);
				
				assertTrue(list_row.get(0).equals(""+iCsvReader_csvReader.getRowNumber()));
				
				assertTrue(list_row.get(1).equals(""+iCsvReader_csvReader.getRowNumber()));
				
			}
			
		}
		
		// Check loop counter.
		assertTrue(integer_loopCounter == 0);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Read when file contains only column names and column names are enabled.
	 */
	@Test
	public void T016() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_2.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Initialize row.
		List<Object> list_row = null;

		// Initialize loop counter.
		int integer_loopCounter = 0;
		
		// Read.
		while ((list_row = iCsvReader_csvReader.read()) != null) {
			
			// Increment loop counter.
			integer_loopCounter++;
			
			// Check row number.
			assertTrue(iCsvReader_csvReader.getRowNumber() == integer_loopCounter);
			
			if (!iCsvReader_csvReader.getCsvController().isColumnNamesEnabled()) {
				
				assertTrue(list_row.size() == 2);
				
				if (integer_loopCounter == 1) {
					
					assertTrue(list_row.get(0).equals("C_0"));
					
					assertTrue(list_row.get(1).equals("C_1"));
					
				} else {
					
					assertTrue(list_row.get(0).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
					assertTrue(list_row.get(1).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
				}
				
			} else {
				
				assertTrue(list_row.size() == 2);
				
				assertTrue(list_row.get(0).equals(""+iCsvReader_csvReader.getRowNumber()));
				
				assertTrue(list_row.get(1).equals(""+iCsvReader_csvReader.getRowNumber()));
				
			}
			
		}
		
		// Check loop counter.
		assertTrue(integer_loopCounter == 0);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Read when file contains only column names and column names are disabled.
	 */
	@Test
	public void T017() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_2.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Initialize row.
		List<Object> list_row = null;

		// Initialize loop counter.
		int integer_loopCounter = 0;
		
		// Read.
		while ((list_row = iCsvReader_csvReader.read()) != null) {
			
			// Increment loop counter.
			integer_loopCounter++;
			
			// Check row number.
			assertTrue(iCsvReader_csvReader.getRowNumber() == integer_loopCounter);
			
			if (!iCsvReader_csvReader.getCsvController().isColumnNamesEnabled()) {
				
				assertTrue(list_row.size() == 2);
				
				if (integer_loopCounter == 1) {
					
					assertTrue(list_row.get(0).equals("C_0"));
					
					assertTrue(list_row.get(1).equals("C_1"));
					
				} else {
					
					assertTrue(list_row.get(0).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
					assertTrue(list_row.get(1).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
				}
				
			} else {
				
				assertTrue(list_row.size() == 2);
				
				assertTrue(list_row.get(0).equals(""+iCsvReader_csvReader.getRowNumber()));
				
				assertTrue(list_row.get(1).equals(""+iCsvReader_csvReader.getRowNumber()));
				
			}
			
		}
		
		// Check loop counter.
		assertTrue(integer_loopCounter == 1);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Read when file contains not only column names and column names are enabled.
	 */
	@Test
	public void T018() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Initialize row.
		List<Object> list_row = null;

		// Initialize loop counter.
		int integer_loopCounter = 0;
		
		// Read.
		while ((list_row = iCsvReader_csvReader.read()) != null) {
			
			// Increment loop counter.
			integer_loopCounter++;
			
			// Check row number.
			assertTrue(iCsvReader_csvReader.getRowNumber() == integer_loopCounter);
			
			if (!iCsvReader_csvReader.getCsvController().isColumnNamesEnabled()) {
				
				assertTrue(list_row.size() == 2);
				
				if (integer_loopCounter == 1) {
					
					assertTrue(list_row.get(0).equals("C_0"));
					
					assertTrue(list_row.get(1).equals("C_1"));
					
				} else {
					
					assertTrue(list_row.get(0).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
					assertTrue(list_row.get(1).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
				}
				
			} else {
				
				assertTrue(list_row.size() == 2);
				
				assertTrue(list_row.get(0).equals(""+iCsvReader_csvReader.getRowNumber()));
				
				assertTrue(list_row.get(1).equals(""+iCsvReader_csvReader.getRowNumber()));
				
			}
			
		}
		
		// Check loop counter.
		assertTrue(integer_loopCounter == 2);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Read when file contains not only column names and column names are disabled.
	 */
	@Test
	public void T019() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Open reader.
		iCsvReader_csvReader.open();
		
		// Initialize row.
		List<Object> list_row = null;

		// Initialize loop counter.
		int integer_loopCounter = 0;
		
		// Read.
		while ((list_row = iCsvReader_csvReader.read()) != null) {
			
			// Increment loop counter.
			integer_loopCounter++;
			
			// Check row number.
			assertTrue(iCsvReader_csvReader.getRowNumber() == integer_loopCounter);
			
			if (!iCsvReader_csvReader.getCsvController().isColumnNamesEnabled()) {
				
				assertTrue(list_row.size() == 2);
				
				if (integer_loopCounter == 1) {
					
					assertTrue(list_row.get(0).equals("C_0"));
					
					assertTrue(list_row.get(1).equals("C_1"));
					
				} else {
					
					assertTrue(list_row.get(0).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
					assertTrue(list_row.get(1).equals(""+(iCsvReader_csvReader.getRowNumber()-1)));
					
				}
				
			} else {
				
				assertTrue(list_row.size() == 2);
				
				assertTrue(list_row.get(0).equals(""+iCsvReader_csvReader.getRowNumber()));
				
				assertTrue(list_row.get(1).equals(""+iCsvReader_csvReader.getRowNumber()));
				
			}
			
		}
		
		// Check loop counter.
		assertTrue(integer_loopCounter == 3);
		
		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
	/**
	 * Read when there are has too few values.
	 */
	@Test
	public void T020() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_5.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();

		try {
		
			// Read.
			while (iCsvReader_csvReader.read() != null) {
				
			}
		
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot read. Row 1 contains too few values. Fetched 2 instead of 3 values."));
			
			// Close reader.
			iCsvReader_csvReader.close();
			
			return;
			
		}
		
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Read when there are too many values.
	 */
	@Test
	public void T021() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_6.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(true);
		
		// Open reader.
		iCsvReader_csvReader.open();

		try {
		
			// Read.
			while (iCsvReader_csvReader.read() != null) {
				
				// Do nothing.
				
			}
		
		} catch (IllegalStateException illegalStateException_exception) {

			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot read. Row 1 contains too many values. Fetched 2 instead of 1 values."));
			
			// Close reader.
			iCsvReader_csvReader.close();
			
			return;
			
		}
		
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set limit.
	 */
	@Test
	public void T022() {
		
		// Get csv reader.
		ICsvReader iCsvReader_csvReader = this.getCsvReader();
		
		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\01_Files\\File_3.csv");
		
		// Set source file.
		iCsvReader_csvReader.getCsvController().setSourceFile(file_sourceFile);
		
		// Enable column names.
		iCsvReader_csvReader.getCsvController().setColumnNamesEnabled(false);
		
		// Create limit.
		long long_limit = 2;
		
		// Set limit.
		iCsvReader_csvReader.setLimit(long_limit);
		
		// Open reader.
		iCsvReader_csvReader.open();

		// Read.
		while (iCsvReader_csvReader.read() != null) {
			
			// Do nothing.
			
		}
		
		// Check row number.
		assertTrue(iCsvReader_csvReader.getRowNumber() == long_limit);

		// Close reader.
		iCsvReader_csvReader.close();
		
	}
	
}