package main_.transceiver_;

import main_.reader_.IReader;
import main_.writer_.IWriter;

/**
 * An {@link ITransceiver} is means to receive data from any kind of (sufficiently parameterized) {@link IReader} 
 * and transmit it to any kind of (sufficiently parameterized) {@link IWriter}.
 * 
 * @author Christian Oedingen
 */
public interface ITransceiver {

	/**
	 * Set reader. Data is received from its underlying source.
	 * @param iReader_reader An {@IReader}.
	 */
	public void setReader(IReader iReader_reader);
	
	/**
	 * Get reader.
	 * @return An {@IReader}.
	 */
	public IReader getReader();
	
	/**
	 * Set writer. Data is transmitted to its underlying target.
	 * @param iWriter_writer An {@IWriter}.
	 */
	public void setWriter(IWriter iWriter_writer);
	
	/**
	 * Get writer.
	 * @return An {@link IWriter}.
	 */
	public IWriter getWriter();
	
	/**
	 * Execute.
	 */
	public void execute();
	
}