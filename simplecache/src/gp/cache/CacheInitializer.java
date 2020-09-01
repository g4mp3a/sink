package gp.cache;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CacheInitializer {

	/**
	 * Initialize the cache with contents from the input file
	 * Sample file describing the file format -
	 * entities.csv
	 * ------------
	 * 
	 * 1,foo,bar
	 * 2,abc,foo
	 * 3
	 * 4,xyz,bar
	 * 
	 * Single threaded as this is I/O bound
	 */
	static public void initialize(AbstractEntityTagCache<Integer, List<String>> input) {
		Pattern splitRegex = Pattern.compile(",");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")))) {
			for (String line; (line = reader.readLine()) != null;) {
				List<String> sArr = Arrays.asList(splitRegex.split(line));
				Integer key = Integer.parseInt(sArr.get(0));
				List<String> tags = new ArrayList<>();
				for (String s : sArr.subList(1, sArr.size())) {
					tags.add(s.intern());
				}

				input.put(key, tags);
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
}
