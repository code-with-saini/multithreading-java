package thread.termination;

import java.math.BigInteger;

public class InterruptThreadMethod2 {

    public static void main(String[] args){

        Thread thread = new Thread(new LongComputation(new BigInteger("2100000000"),new BigInteger("2100000000")));

        thread.setDaemon(true);
        thread.start();


        try {
            Thread.sleep(1000);
            thread.interrupt();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

         // this will not help as interrupt is send but we dont have anything to handle that
        //Now we will find a hotspot where long calculation is taking place and check for interruption signal

    }


    private static class LongComputation implements Runnable{

        private BigInteger base;
        private BigInteger power;

        public LongComputation(BigInteger base, BigInteger power){
            this.base=base;
            this.power=power;
        }

        @Override
        public void run() {

            System.out.println("The value of " + base + " to the power " + power +  " is equal to: " + pow(base,power));
        }


        private BigInteger pow(BigInteger base, BigInteger power){

            BigInteger result = BigInteger.ONE;

            for(BigInteger i = BigInteger.ZERO; i.compareTo(power)!=0; i=i.add(BigInteger.ONE) ){

                //you can remove this check if you set this thread as deamon thread
               /* if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread is prematurely interrupted");
                    return BigInteger.ZERO;
                }*/


                result =  result.multiply(base);
            }

            return result;
        }
    }
}
