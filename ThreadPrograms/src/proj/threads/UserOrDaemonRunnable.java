package proj.threads;

import java.util.Random;

import static java.lang.Math.abs;

public class UserOrDaemonRunnable extends Random implements Runnable {
    final private String threadType;
    private final int MAX_ITERATIONS = 10000000;
    public UserOrDaemonRunnable(String threadType) {
       this.threadType = threadType;
    }
    @Override
    public void run() {
        final String threadString = "with " + threadType + "thread id " + Thread.currentThread();
        System.out.println("Entering run " + threadString);
        try{
          for(int i = 0; i<MAX_ITERATIONS;++i){
              int number1 = abs(nextInt());
              int number2 = abs(nextInt());
              if((i%10000000) == 0){
                  System.out.println("In-run " + threadString +
                          " the GCD of " + number1 +" and " + number2 + computeGCD(number1,number2));
              }
          }
        }
        finally {
            System.out.println("Leaving run() thread " + threadString);
        }

    }
    private int computeGCD(int number1, int number2){
        if(number2 == 0)
            return number1;
        return computeGCD(number2,number1%number2);
    }

    public static void main(String[] args) {
        System.out.println("Entering main thread");
        final Boolean isDaemon = args.length>0;
        UserOrDaemonRunnable userRun = new UserOrDaemonRunnable(isDaemon?"daemon":"user");

        Thread thr = new Thread(userRun);

        if(isDaemon)
            thr.setDaemon(true);
        thr.start();
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException ex){
            System.out.println("Leaving main");
        }
    }
}
