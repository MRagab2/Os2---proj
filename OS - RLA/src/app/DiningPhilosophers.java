package app;

public class DiningPhilosophers {

    public static void main(String[] args) throws Exception {

        final Push[] pushers = new Push[5];
        Object[] forks = new Object[pushers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < pushers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == pushers.length - 1) {

                // The last philosopher picks up the right fork first
                pushers[i] = new Push(rightFork, leftFork);
            } else {
                pushers[i] = new Push(leftFork, rightFork);
            }

            Thread t
                    = new Thread(pushers[i], "User " + (i + 1));
            t.start();
        }
    }
}

