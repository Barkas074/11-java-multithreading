public class Lucky {
    static int x = 0;
    static int count = 0;
    private static final Object object1 = new Object();
    private static final Object object2 = new Object();

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            int xInThread;
            while (x < 999999) {
                synchronized (object1) {
                    x++;
                    xInThread = x;
                    if (x > 999999)
                        break;
                }

                if ((xInThread % 10) + (xInThread / 10) % 10 + (xInThread / 100) % 10 == (xInThread / 1000)
                        % 10 + (xInThread / 10000) % 10 + (xInThread / 100000) % 10) {
                    System.out.println(xInThread + " " + Thread.currentThread().getName());
                    synchronized (object2) {
                        count++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}
