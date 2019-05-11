package runnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.BlockingDeque;

import ProAlpha.Test.App;

public class ReaderThread implements Runnable{

	private final BlockingDeque<String> records;
	private final File file;

	public ReaderThread(BlockingDeque<String> records, File file) {
		this.records = records;
		this.file = file;
	}

	public void run() {
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				for(String arg : line.split(" ")) {
					records.add(arg);
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println("an error occured "+e);
		}
		for(int i=0;i<App.nbThread;i++) {
			records.add(App.POISONING);
		}
	}
}
