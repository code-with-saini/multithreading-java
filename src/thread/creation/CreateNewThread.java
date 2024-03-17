package thread.creation;

public class CreateNewThread {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("we are in new thread "
                        + Thread.currentThread().getName() + " and thread priority is" +
                        ":" + Thread.currentThread().getPriority());
            }
        });

        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println("before thread staring "
                + Thread.currentThread().getName() + " and thread priority is" +
                ":" + Thread.currentThread().getPriority());


        thread.setName("gaurav thread");
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();

        System.out.println("after thread staring " + Thread.currentThread().getName() );
    }
}