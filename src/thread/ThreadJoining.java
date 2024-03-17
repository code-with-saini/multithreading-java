package thread;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ThreadJoining {

    public static void main(String[] args) throws InterruptedException {

        List<Long> inputs = List.of(111111111110L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);
        List<FactorialThread> threads = new ArrayList<>();

        for(long l : inputs){
            threads.add(new FactorialThread(l));
        }

        for(Thread thread : threads){
            thread.setDaemon(true);
            thread.start();
        }

        for(Thread thread : threads){
            thread.join(2000);
        }

        for (FactorialThread thread : threads) {
            if (thread.isReady()) {
                System.out.println("Result for " + thread.getInput() + " is ready and result is " + thread.getResult());
            } else {
                System.out.println("Result for " + thread.getInput() + " is not ready yet its still processing");
            }
        }


    }

    private static class FactorialThread extends Thread{

        private long input;
        private boolean isReady = false;
        private BigInteger result;


        public FactorialThread(long input){
            this.input=input;
        }

        public void run(){
            this.result = factorial(input);
            this.isReady = true;
        }

        private BigInteger factorial(long input){
            BigInteger result = BigInteger.ONE;
            for(long i = input; i>0; i--){
                result = result.multiply(new BigInteger(String.valueOf(i)));
            }
            return result;
        }

        public boolean isReady(){
            return isReady;
        }

        public BigInteger getResult(){
            return result;
        }

        public long getInput() {
            return input;
        }
    }
}
