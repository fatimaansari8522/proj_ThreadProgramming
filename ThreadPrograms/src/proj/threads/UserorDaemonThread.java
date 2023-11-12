package proj.threads;

import java.util.Random;

import static java.lang.Math.abs;

public class UserorDaemonThread extends Thread {
    final private String threadType;
    private final int MAX_ITERATION = 100_000_000;
    public UserorDaemonThread(Boolean daemonThread) {
      if(daemonThread){
          setDaemon(true);
          threadType = "daemon";
      }
      else {
          threadType= "user";
      }
    }
    private int computeGCD(int number1, int number2){
        if(number2 == 0)
            return number1;
        return computeGCD(number2,number1%number2);
    }
@Override
    public void run(){
        final String threadString = "with"
                                    + threadType
                                    + "thread id"
                                    + Thread.currentThread();
    System.out.println("Entering run" + threadString);

    Random random = new Random();
    try{for(int i = 0; i<MAX_ITERATION; ++i){
    int number1 = abs(random.nextInt());
    int number2 = abs(random.nextInt());
    if((i%10000000) == 0){
        System.out.println("In run " + threadString + "the GCD of " + number1 + "and"
        + number2 + "is" + computeGCD(number1,number2));
    }
    }
    }
    finally {
        System.out.println("Leaving run() " + threadString);
    }
}

    public static void main(String[] args) {
        System.out.println("Entering main() ");
        // create a daemon thread if any command-line parameter is passed
        // to the program

        final Boolean daemonThread = args.length > 0;

        UserorDaemonThread thr = new UserorDaemonThread(daemonThread);
        thr.start();

        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            System.out.println("Leaving main method");
        }
        System.out.println("Hello");
    }
}
