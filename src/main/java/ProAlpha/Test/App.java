package ProAlpha.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import runnable.ProcessorThread;
import runnable.ReaderThread;
import utils.ThreadUtils;
import writter.WritterResults;

public class App {
	private final static String PATH = "src/files";
	private final static String INPUT = "InputPA.txt";
	private final static String OUTPUT = "Ouput.txt";
	public static int nbThread =1 ;
	public static String POISONING = "Poisoning";
	public static ArrayList<String> outputs = new ArrayList<String>();

	public static void main(String[] args) {
		File input = new File(PATH, INPUT);
		File output = new File(PATH,OUTPUT);
		long debut;
		ArrayList<Runnable> testThreads = new ArrayList<Runnable>();
		BlockingDeque<String> records = new LinkedBlockingDeque<String>();
		testThreads.add(new ReaderThread(records, input));
		for (int i = 0; i < nbThread; i++) {
			testThreads.add(new ProcessorThread(records));
		}
		debut=System.currentTimeMillis();
		ThreadUtils.execute(testThreads);
		Collections.sort(outputs,new Comparator<String>() {
		    public  int compare(String s1, String s2) {
		          return s2.compareTo(s1);
		   }
		});
		new WritterResults(outputs,output,System.currentTimeMillis()-debut);
	}
}