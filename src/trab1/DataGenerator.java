package trab1;

import java.io.IOException;

public final class DataGenerator {
	
	private final FileManager csvFile;

	private final FileManager dataFile;
	
	public DataGenerator() throws IOException {
		csvFile = new FileManager("1605705_vgsales.csv");
		dataFile = new FileManager("data.bin");
	}
	
	public final void generateDataFile() throws IOException {
		String line;
		do {
			line = csvFile.readNextLine();
			dataFile.writeLine(line);
		} while (line != null);
		csvFile.closeFile();
		dataFile.closeFile();
	}
}
