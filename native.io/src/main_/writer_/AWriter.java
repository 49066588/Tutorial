package main_.writer_;

import java.util.ArrayList;
import java.util.List;

public abstract class AWriter implements IWriter {

	////////////
	// FIELDS //
	////////////
	
	private boolean boolean_isTruncationEnabled = false;
	
	private List<String> list_columnNames = new ArrayList<String>();
	
	private boolean boolean_isCapitalizeColumnNamesEnabled = false;
	
	private List<String> list_largeTextColumnNames = new ArrayList<String>();
	
	/////////////
	// METHODS //
	/////////////
	
	@Override
	public void setTruncationEnabled(boolean boolean_isTruncationEnabled) {
		
		// Assign input parameter.
		this.boolean_isTruncationEnabled = boolean_isTruncationEnabled;
		
	}

	@Override
	public boolean isTruncationEnabled() {
		
		// Return status.
		return this.boolean_isTruncationEnabled;
		
	}

	@Override
	public void addColumnName(String string_columnName) {
		
		// Check input parameter.
		if (string_columnName == null) {
			
			String string_message = "Cannot add column name. Input parameter \"string_columnName\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (string_columnName.trim().isEmpty()) {
			
			String string_message = "Cannot add column name. Input parameter \"string_columnName\" must not be blank.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (this.list_columnNames.contains(string_columnName)) {
			
			String string_message = "Cannot add column name. Column name \""+string_columnName+"\" has already been added.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.list_columnNames.add(string_columnName);
		
	}

	@Override
	public List<String> getColumnNames() {
		
		// Return column names.
		return this.list_columnNames;
		
	}
	
	@Override
	public void resetColumnNames() {
		
		// Reset column names.
		this.list_columnNames.clear();
		
	}
	
	@Override
	public void setCapitalizeColumnNamesEnabled(boolean boolean_isCapitalizeColumnNamesEnabled) {
		
		// Assign input parameter.
		this.boolean_isCapitalizeColumnNamesEnabled = boolean_isCapitalizeColumnNamesEnabled;
		
	}

	@Override
	public boolean isCapitalizeColumnNamesEnabled() {
	
		// Return status.
		return this.boolean_isCapitalizeColumnNamesEnabled;
		
	}

	@Override
	public void addLargeTextColumnName(String string_largeTextColumnName) {

		// Check input parameter.
		if (string_largeTextColumnName == null) {
			
			String string_message = "Cannot add large text column name. Input parameter \"string_largeTextColumnName\" must not be null.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (string_largeTextColumnName.trim().isEmpty()) {
			
			String string_message = "Cannot add large text column name. Input parameter \"string_largeTextColumnName\" must not be blank.";
			throw new IllegalStateException(string_message);
			
		}
		
		if (this.list_largeTextColumnNames.contains(string_largeTextColumnName)) {
			
			String string_message = "Cannot add large text column name. Large text column name \""+string_largeTextColumnName+"\" has already been added.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.list_largeTextColumnNames.add(string_largeTextColumnName);
		
	}

	@Override
	public List<String> getLargeTextColumnNames() {

		// Return large text column names.
		return this.list_largeTextColumnNames;
		
	}

}