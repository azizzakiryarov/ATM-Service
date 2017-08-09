package se.groupfisk.storage;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public interface Service {
	
	 int withDraw(int amount) throws IOException;
	 
	 Reader printLastMonthReciept() throws IOException;
	 
	 Writer printReciept() throws IOException;

}
