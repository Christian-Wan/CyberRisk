import javax.swing.*;

public class Frame extends JFrame implements Runnable {

    private Engine engine;
    private Thread windowThread;
    private final int FRAMEWIDTH = 1500;
    private final int FRAMEHEIGHT = 900;

    public Frame() {
        engine = new Engine(this);
        this.add(engine.getPlayScreen());
        this.setTitle("Cyber Risk");
        this.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        this.setLocation(20, 20);
        this.setResizable(false);
        this.setVisible(true);
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
            engine.getPlayScreen().repaint();
            engine.getPlayScreen().update();

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
