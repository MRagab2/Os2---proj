package app;

public class Push implements Runnable {

    // The forks on either side of this Philosopher
    private Object user;
    private Object admin;

    public Push(Object user, Object admin) {
        this.user = user;
        this.admin = admin;
    }

    // Member variables, standard constructor

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 4000)));
    }

    // Rest of the methods written earlier

    // Member variables, methods defined earlier

    @Override
    public void run() {
        try {
            while (true) {

                // thinking
                doAction(": Working");
                synchronized (user) {
                    doAction(": Trying to Push");
                    synchronized (admin) {
                        // eating
                        doAction(": Wait for Admin Approve");

                        doAction(": Admin Approved");
                    }

                    // Back to thinking
                    doAction(": Pushed Successfully ,Back to Work");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
