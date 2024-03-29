package thread.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiThreadingCodeExample {

    private static final int MAX_GUESS = 9999;

    public static void main(String[] args){

        Random random = new Random();
        Vault vault =  new Vault(random.nextInt(MAX_GUESS));

        List<Thread> threads = new ArrayList<>();
        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for(Thread thread : threads){
            thread.start();
        }

    }

    private static class Vault{
        private int password;
        public Vault(int password){
            this.password = password;
        }

        public boolean isCorrect(int guess) throws InterruptedException {
            Thread.sleep(5);
            return this.password == guess;
        }

    }

    private static abstract class HackerThread extends Thread{
        protected Vault vault;

        public HackerThread(Vault vault){
            this.vault = vault;
            this.setName(this.getClass().getName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start(){
            System.out.println("Starting thread " + this.getName());
            super.start();
        }

    }

    private static class AscendingHackerThread extends HackerThread{

        public AscendingHackerThread(Vault vault){
            super(vault);
        }

        @Override
        public void run(){

            for(int guess = 0; guess <= MAX_GUESS; guess++){
                try {
                    if(vault.isCorrect(guess)){
                       System.out.println( this.getName()+ " guessed the password " + guess);
                       System.exit(0);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    private static class DescendingHackerThread extends HackerThread{

        public DescendingHackerThread(Vault vault){
            super(vault);
        }

        @Override
        public void run(){
            for(int guess = MAX_GUESS; guess >= 0; guess--){
                try {
                    if(vault.isCorrect(guess)){
                        System.out.println(this.getName() + " guessed the password " + guess);
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private static class PoliceThread extends Thread{

        @Override
        public void run(){
            for(int timer = 10; timer > 0; timer--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Police coming in " + timer + " seconds");
            }
            System.out.println("Game Over Hackers!");
            System.exit(0);
        }
    }
}
