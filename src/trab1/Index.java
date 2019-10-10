package trab1;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Index {
	private final FileManager dataFile;

	private final FileManager indexFile;
	
	private Map<String, List<Long>> indexPage = new HashMap<>();
	
	public Index() throws IOException {
		dataFile = new FileManager("data.bin");
		indexFile = new FileManager("index.bin");
	}
	
	private final String getFullName(String line) {
		if (line.indexOf('"') > -1) {
			return line.split("/\\\"/")[1];
		} else {
			return line.split("\\,")[1];
		}
	}
	
	private final String[] getValues(String fullName) {
		return fullName.split("/\\w+/");
	}
	
	public final void generateIndexFile() {
		String line;
		do {
			line = dataFile.readNextLine();
			for (String name : getValues(getFullName(line))) {				
				indexFile.writeLine(name + ";" + );
			}
		} while (line != null);
		dataFile.closeFile();
		indexFile.closeFile();
	}
	
	private final List<Long> getPositions(long position) throws IOException {
		String line = indexFile.readLine(position);
		List<Long> result = new ArrayList<>();
		
		for (String index : line.split("\\;")[1].split("\\,")) {
			result.add(Long.parseLong(index));
		}
		
		return result;
	}
	
	public final List<Long> getDataPositions(long position) throws IOException {
		return getPositions(position);
	}
}
