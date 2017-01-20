package main_.transceiver_;

import java.util.List;

import main_.reader_.IReader;
import main_.writer_.IWriter;

public class NTransceiver implements ITransceiver {
	
	////////////
	// FIELDS //
	////////////
	
	private IReader iReader_reader = null;

	private IWriter iWriter_writer = null;
	
	/////////////
	// METHODS //
	/////////////
	
	@Override
	public void setReader(IReader iReader_reader) {
		
		// Check input parameter.
		if (iReader_reader == null) {
			
			String string_message = "Cannot set reader. Input parameter \"iReader_reader\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.iReader_reader = iReader_reader;
		
	}

	@Override
	public IReader getReader() {

		// Assert reader has been set.
		if (this.iReader_reader == null) {
			
			String string_message = "Cannot get reader. Reader has not been set.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Return reader.
		return this.iReader_reader;
		
	}

	@Override
	public void setWriter(IWriter iWriter_writer) {

		// Check input parameter.
		if (iWriter_writer == null) {
			
			String string_message = "Cannot set writer. Input parameter \"iWriter_writer\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.iWriter_writer = iWriter_writer;
		
	}

	@Override
	public IWriter getWriter() {
		
		// Assert reader has been set.
		if (this.iWriter_writer == null) {
			
			String string_message = "Cannot get writer. Writer has not been set.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Return writer.
		return this.iWriter_writer;
		
	}

	@Override
	public void execute() {
		
		// Get reader.
		IReader iReader_reader = this.getReader();

		// Get writer.
		IWriter iWriter_writer = this.getWriter();
	
		try {

			// Open reader.
			iReader_reader.open();

			// Reset column names.
			iWriter_writer.resetColumnNames();

			// Receive column names from reader.
			for (String string_columnName : iReader_reader.getColumnNames()) {
				
				// Transmit column names to writer.
				iWriter_writer.addColumnName(string_columnName);
				
			}

			// Open writer.
			iWriter_writer.open();
			
			// Initialize row.
			List<Object> list_row = null;
					
			// Receive row from reader.
			while ((list_row = iReader_reader.read()) != null) {
				
				// Transmit row to writer.
				iWriter_writer.write(list_row);
						
			}
				
			// Close writer.
			iWriter_writer.close();
			
			// Close reader.
			iReader_reader.close();
			
		} catch (Exception exception_exception) {
			
			// Close reader.
			iReader_reader.close();

			// Close writer.
			iWriter_writer.close();
			
			// Throw exception.
			throw exception_exception;
			
		}
		
	}

}