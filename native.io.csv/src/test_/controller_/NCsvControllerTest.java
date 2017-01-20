package test_.controller_;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import controller_.ICsvController;
import controller_.NCsvController;

public class NCsvControllerTest {

	///////////
	// TESTS //
	///////////
	
	/**
	 * Set null as source file.
	 */
	@Test
	public void T001() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();
		
		// Create source file.
		File file_sourceFile = null;
		
		try {
		
			// Set source file.
			iCsvController_csvController.setSourceFile(file_sourceFile);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set source file. Input parameter \"file_sourceFile\" must not be null."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set non-existing source file.
	 */
	@Test
	public void T002() {

		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\00_Jars\\DUMMY.csv");
		
		try {
		
			// Set source file.
			iCsvController_csvController.setSourceFile(file_sourceFile);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set source file. Input parameter \"file_sourceFile\" must exist."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set folder as source file.
	 */
	@Test
	public void T003() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create source file.
		File file_sourceFile = new File(System.getProperty("user.dir")+"\\00_Jars");
		
		try {
		
			// Set source file.
			iCsvController_csvController.setSourceFile(file_sourceFile);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set source file. Input parameter \"file_sourceFile\" must be a file."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Get source file without setting it.
	 */
	@Test
	public void T004() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		try {
			
			// Get source file.
			iCsvController_csvController.getSourceFile();
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot get source file. Source file has not been set."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set null as target file.
	 */
	@Test
	public void T005() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create target file.
		File file_targetFile = null;
		
		try {
		
			// Set target file.
			iCsvController_csvController.setTargetFile(file_targetFile);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set target file. Input parameter \"file_targetFile\" must not be null."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set folder as target file.
	 */
	@Test
	public void T006() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create target file.
		File file_targetFile = new File(System.getProperty("user.dir")+"\\00_Jars");
		
		try {
		
			// Set target file.
			iCsvController_csvController.setTargetFile(file_targetFile);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set target file. Input parameter \"file_targetFile\" exists but is not a file."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Get target file without setting it.
	 */
	@Test
	public void T007() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		try {
			
			// Get target file.
			iCsvController_csvController.getTargetFile();
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot get target file. Target file has not been set."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set null as encoding.
	 */
	@Test
	public void T008() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create encoding.
		String string_encoding = null;
		
		try {
		
			// Set encoding.
			iCsvController_csvController.setEncoding(string_encoding);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set encoding. Input parameter \"string_encoding\" must not be null."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set empty string as encoding.
	 */
	@Test
	public void T009() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create encoding.
		String string_encoding = "";
		
		try {
		
			// Set encoding.
			iCsvController_csvController.setEncoding(string_encoding);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set encoding. Input parameter \"string_encoding\" must not be blank."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set blank string as encoding.
	 */
	@Test
	public void T010() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create encoding.
		String string_encoding = "	";
		
		try {
		
			// Set encoding.
			iCsvController_csvController.setEncoding(string_encoding);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set encoding. Input parameter \"string_encoding\" must not be blank."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set unsupported encoding.
	 */
	@Test
	public void T011() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create encoding.
		String string_encoding = "DUMMY";
		
		try {
		
			// Set encoding.
			iCsvController_csvController.setEncoding(string_encoding);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().startsWith("Cannot set encoding. Encoding \""+string_encoding+"\" is not supported."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Enable column names.
	 */
	@Test
	public void T012() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Enable column names.
		iCsvController_csvController.setColumnNamesEnabled(true);
		
		// Check status.
		assertTrue(iCsvController_csvController.isColumnNamesEnabled());
		
	}
	
	/**
	 * Disable column names.
	 */
	@Test
	public void T013() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Disable column names.
		iCsvController_csvController.setColumnNamesEnabled(false);
		
		// Check status.
		assertTrue(!iCsvController_csvController.isColumnNamesEnabled());
		
	}
	
	/**
	 * Set null as row separator.
	 */
	@Test
	public void T014() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create row separator.
		String string_rowSeparator = null;
		
		try {
		
			// Set row separator.
			iCsvController_csvController.setRowSeparator(string_rowSeparator);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set row separator. Input parameter \"string_rowSeparator\" must not be null."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Set empty string as row separator.
	 */
	@Test
	public void T015() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create row separator.
		String string_rowSeparator = "";
		
		try {
		
			// Set row separator.
			iCsvController_csvController.setRowSeparator(string_rowSeparator);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot set row separator. Input parameter \"string_rowSeparator\" must not be empty."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}

	/**
	 * Add string mapping with null as key.
	 */
	@Test
	public void T016() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create string mapping key.
		String string_key = null;
		
		// Create string mapping_value.
		String string_value = "\r\n";
		
		try {
		
			// Add string mapping.
			iCsvController_csvController.addStringMapping(string_key,string_value);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot add string mapping. Input parameter \"string_key\" must not be null."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Add string mapping with empty string as key.
	 */
	@Test
	public void T017() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create string mapping key.
		String string_key = "";
		
		// Create string mapping_value.
		String string_value = "\r\n";
		
		try {
		
			// Add string mapping.
			iCsvController_csvController.addStringMapping(string_key,string_value);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot add string mapping. Input parameter \"string_key\" must not be blank."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Add string mapping with blank string as key.
	 */
	@Test
	public void T018() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create string mapping key.
		String string_key = "	";
		
		// Create string mapping_value.
		String string_value = "\r\n";
		
		try {
		
			// Add string mapping.
			iCsvController_csvController.addStringMapping(string_key,string_value);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot add string mapping. Input parameter \"string_key\" must not be blank."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Add string mapping with null as value.
	 */
	@Test
	public void T019() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create string mapping key.
		String string_key = "\\\\r\\\\n";
		
		// Create string mapping_value.
		String string_value = null;
		
		try {
		
			// Add string mapping.
			iCsvController_csvController.addStringMapping(string_key,string_value);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot add string mapping. Input parameter \"string_value\" must not be null."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Add existing string mapping.
	 */
	@Test
	public void T020() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create string mapping key.
		String string_key = "\\\\r\\\\n";
		
		// Create string mapping_value.
		String string_value = "\\r\\n";
		
		// Add string mapping.
		iCsvController_csvController.addStringMapping(string_key,string_value);
		
		try {
		
			// Add string mapping once again.
			iCsvController_csvController.addStringMapping(string_key,string_value);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot add string mapping. Key \""+string_key+"\" has already been mapped."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Add character mapping with null as key.
	 */
	@Test
	public void T021() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create character mapping key.
		String string_key = null;
		
		// Create character mapping_value.
		char character_value = '\r';
		
		try {
		
			// Add character mapping.
			iCsvController_csvController.addCharacterMapping(string_key,character_value);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot add character mapping. Input parameter \"string_key\" must not be null."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Add character mapping with empty string as key.
	 */
	@Test
	public void T022() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create character mapping key.
		String string_key = "";
		
		// Create character mapping_value.
		char character_value = '\r';
		
		try {
		
			// Add character mapping.
			iCsvController_csvController.addCharacterMapping(string_key,character_value);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot add character mapping. Input parameter \"string_key\" must not be blank."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Add character mapping with blank string as key.
	 */
	@Test
	public void T023() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create character mapping key.
		String string_key = "	";
		
		// Create character mapping_value.
		char character_value = '\r';
		
		try {
		
			// Add character mapping.
			iCsvController_csvController.addCharacterMapping(string_key,character_value);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot add character mapping. Input parameter \"string_key\" must not be blank."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}
	
	/**
	 * Add existing character mapping.
	 */
	@Test
	public void T024() {
		
		// Create csv controller.
		ICsvController iCsvController_csvController = new NCsvController();

		// Create character mapping key.
		String string_key = "\\\\r";
		
		// Create character mapping_value.
		char character_value = '\r';
		
		// Add character mapping.
		iCsvController_csvController.addCharacterMapping(string_key,character_value);
		
		try {
		
			// Add character mapping once again
			iCsvController_csvController.addCharacterMapping(string_key,character_value);
			
		} catch (IllegalStateException illegalStateException_exception) {
			
			// Check exception message.
			assertTrue(illegalStateException_exception.getMessage().equals("Cannot add character mapping. Key \""+string_key+"\" has already been mapped."));
			
			return;
			
		}
	
		// Fail if this code block is reached.
		fail();
		
	}

}