package runnable;

import java.util.concurrent.BlockingDeque;

import ProAlpha.Test.App;

public class ProcessorThread implements Runnable {
    private final BlockingDeque<String> records;

    public ProcessorThread(BlockingDeque<String> records) {
        this.records = records;
    }

    public void run() {
        System.out.println("Processor Thread begins");
        String arg;
        while (!App.POISONING.equals(arg = records.poll())) {
            try {
                if (arg != null && arg.contains("proALPHA")) {
                    arg = arg.replaceAll("\"", "");
                    if (arg.contains("PA")) {
                        arg = arg.concat(arg.substring(2, arg.indexOf(":") + 1));
                        arg = arg.substring(arg.indexOf(":"), arg.length() - 1);
                    }

                    App.outs.add(arg.replace(":proALPHA:", ""));
                }
            } catch (Exception e) {
                System.out.println("an error occured while processing " + arg + " " + e);
            }
        }
        System.out.println("Processor Thread ends");
    }

}
