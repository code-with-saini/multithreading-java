package thread.creation;

public class CatchingExceptionInThread {
    public static void main(String[] args){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Exception occur!");
            }
        });

        thread.setName("Misbehaving thread");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                System.out.println("A critical error happened in thread " +  thread.getName()
                        + " the error is " + throwable.getMessage());
            }
        });

        thread.start();

    }
}
