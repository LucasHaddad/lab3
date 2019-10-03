package trab1;

import java.io.IOException;
import java.io.RandomAccessFile;

public final class FileManager {
	
	private final RandomAccessFile file;
	
	private final long length;
	
	public FileManager(String filePath) throws IOException {
		file = new RandomAccessFile(filePath, "rw");
		length = file.length();
	}
	
	public final String readNextLine() throws IOException {
		return file.readLine();
	}
	
	public final String readLine(long pos) throws IOException {
		file.seek(pos);
		return readNextLine();
	}
	
	public final void writeLine(String line) throws IOException {
		file.writeBytes(line);
	}
	
	public final void writeLine(String line, long pos) throws IOException {
		file.seek(pos);
		writeLine(line);
	}
	
	public final void closeFile() throws IOException {
		file.close();
	}
}
