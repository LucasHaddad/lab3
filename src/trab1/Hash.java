package trab1;

import java.io.IOException;
import java.io.RandomAccessFile;

public final class Hash {
	
	private final FileManager indexFile;

	private final FileManager hashFile;
	
	public Hash() throws IOException {
		indexFile = new FileManager("index.bin");
		hashFile = new FileManager("hash.bin");
	}
	
	private final String getFullName(String line) {
		if (line.indexOf('"') > -1) {
			return line.split("/\\\"/")[1];
		} else {
			return line.split("\\,")[1];
		}
	}
	
	private final String getValues(String fullName) {
		String values = "";
		for (String name : fullName.split("/\\w+/")) {
			values += name + ",";
		}
		return values;
	}
	
	private final int generateHash(String name) {
		return name.hashCode();
	}
	
	public final void generateHashFile() throws IOException {
		String line;
		do {
			line = indexFile.readNextLine();
			String fullName = getFullName(line);
			hashFile.writeLine(getValues(fullName) + ";" + generateHash(fullName));
		} while (line != null);
		indexFile.closeFile();
		hashFile.closeFile();
	}
	
	private final long getPosition(int hash) throws IOException {
		String line;
		do {
			line = hashFile.readNextLine();
			String[] values = line.split("/\\;/");
			int hashOnFile = Integer.parseInt(values[0]);
			if (hashOnFile == hash) return Integer.parseInt(values[1]);
		} while (line != null);
		return -1;
	}
	
	public final long getIndexPosition(String name) throws IOException {
		int hash = generateHash(name);
		long position = getPosition(hash);
		return position > -1 ? position : -1;
	}
}
