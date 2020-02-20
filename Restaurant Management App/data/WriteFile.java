package data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {

	 String path;
	 boolean append_to_file = false;
	 public WriteFile(String path) {
	 	super();
	 	this.path = path;
	 }
	 public WriteFile(String path, boolean append_to_file) {
	 	super();
	 	this.path = path;
	 	this.append_to_file = append_to_file;
	 }

	 	public void writeToFile(String txt) throws IOException {
	 		FileWriter write  = new FileWriter(path, append_to_file);
	 		PrintWriter print_line = new PrintWriter(write);
	 		print_line.printf("%s"+"%n",txt);
	 		print_line.close();
	 		
	 	}
}
