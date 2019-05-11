package utils;

	import java.util.Collection;
	import java.util.concurrent.ExecutorService;
	import java.util.concurrent.Executors;

	public final class ThreadUtils {

	    public static void execute(Collection<? extends Runnable> runnables) { 
	        ExecutorService executor = Executors.newFixedThreadPool(runnables.size());

	        for (Runnable runnable : runnables) {
	            executor.execute(runnable);
	        }

	        executor.shutdown();

	        while (!executor.isTerminated()) {
	        }
	    }

}
