public class MutexDemo {
    private static class SharedResource {
        private final Object mutex = new Object();

        public void accessResource() {
            synchronized (mutex) {
                System.out.println("Resource accessed by " + Thread.currentThread().getName());
                // Simulate some work
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Resource released by " + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        final SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(sharedResource::accessResource, "Thread-1");
        Thread t2 = new Thread(sharedResource::accessResource, "Thread-2");

        t1.start();
        t2.start();
    }
}
