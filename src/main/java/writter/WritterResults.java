package writter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WritterResults {

	public WritterResults(ArrayList<String> outputs, File output, long time) {
		System.out.println("WriterResult begins, number of datas : "+outputs.size());
		FileWriter writer = null;
		if (output.exists()) {
			output.delete();
		}
		try {
			writer = new FileWriter(output);
			writer.write("Duration of treatment of these datas : "+Long.toString(time)+" ms\n");

			for (String arg : outputs) {
				writer.write(arg +"\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occured " + e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("An error occured " + e);
			}
		}
		System.out.println("WriterResult ends");

	}
}
