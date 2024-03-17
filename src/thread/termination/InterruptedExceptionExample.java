package thread.termination;


//This is method 1 to interrupt a thread
public class InterruptedExceptionExample {

    public static void main(String[] args){

        Thread thread = new Thread(new BlockingCode());

        thread.start();
        thread.interrupt();

    }


    private static class BlockingCode implements Runnable{

        @Override
        public void run() {

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {  // InterruptedException handling Interrupting thread externally
               System.out.println("Exiting blocking code!");
            }

        }
    }
}
