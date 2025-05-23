import javax.swing.*;

public class Frame extends JFrame implements Runnable {

    private Engine engine;
    private Thread windowThread;

    public Frame() {
        this.setTitle("Cyber Risk");
        startThread();
    }

    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }


    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(true) {

            //add updating and repainting


            try {
                double remainingTIme = nextDrawTime - System.nanoTime();
                remainingTIme = remainingTIme/1000000;

                if (remainingTIme < 0) {
                    remainingTIme = 0;
                }

                Thread.sleep((long) remainingTIme);
                nextDrawTime +=drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
