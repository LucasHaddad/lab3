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
		String line = csvFile.readNextLine();
		while (line != null) {
			line = csvFile.readNextLine();
			dataFile.writeLine(line);
		}
		csvFile.closeFile();
		dataFile.closeFile();
	}
}
