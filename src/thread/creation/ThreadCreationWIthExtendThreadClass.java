package thread.creation;

public class ThreadCreationWIthExtendThreadClass {

    public static void main(String[] args){

        Thread t1 = new NewThread();
        Thread t2 = new NewThread();
        t1.start();
        t2.start();
    }

    private static class NewThread extends Thread{

        @Override
        public void run(){

            System.out.println("We are in new thread called " + getName());
        }

    }
}
