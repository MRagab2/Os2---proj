package app.Model;

import app.Controller;
import app.Utilities.Utils;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Philosopher implements Runnable {

    private Fork leftFork;
    private Fork rightFork;
    private int id;
    private String name;
    private State state;
    private ImageView headView;
    private Image thinkingImg;
    private Image hungryImg;
    private Image eatingImg;
    private TextArea loggingConsole;

    public Philosopher(Fork leftFork, Fork rightFork, int id, String name, ImageView headView,
                       Image thinkingImg, Image hungryImg, Image eatingImg, TextArea loggingConsole) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.id = id;
        this.name = name;
        this.headView = headView;
        this.thinkingImg = thinkingImg;
        this.hungryImg = hungryImg;
        this.eatingImg = eatingImg;
        this.loggingConsole = loggingConsole;
        state = State.THINKING;
    }

    @Override
    public void run() {
        while (Controller.RUNNING) {
            think();
            hungryWaitingToEat();
            eat();
            if (state == State.HUNGRY) {
            }
        }
        Platform.runLater(() -> loggingConsole.appendText("\n"+name + " stopped \n"));
        System.out.println("\n"+name + " stopped");
    }

    private void think() {
        try {
            if (state == State.THINKING) {
                System.out.println("\n"+name + " is thinking...");
                Platform.runLater(() -> {
                    loggingConsole.appendText("\n"+name + " is thinking... \n");
                });
                Thread.sleep(Utils.randomIntThink());
                state = State.HUNGRY;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void hungryWaitingToEat() {
        try {
            if (state == State.HUNGRY) {
                System.out.println("\n"+name + " is hungry...");
                Platform.runLater(() -> {
                    loggingConsole.appendText("\n"+name + " is hungry... \n");
                    headView.setImage(hungryImg);
                });

                Thread.sleep(Utils.randomIntHungry());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

// Avoiding Deadlock
    private void eat() {
        if (leftFork.pick(this)){           //left fork is available
            try {
                if (rightFork.pick(this)) { //right fork is available
                    try {
                        //Eat
                        System.out.println("\n"+name + " is eating...");
                        state = State.EATING;

                        Platform.runLater(() -> {
                            loggingConsole.appendText("\n"+name + " is eating... \n");
                            headView.setImage(eatingImg);
                        });

                        Thread.sleep(Utils.randomIntEat());

                        //Go back to Thinking state
                        state = State.THINKING;
                        Platform.runLater(() -> headView.setImage(thinkingImg));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        rightFork.drop(this);
                    }
                }
            } finally {
                leftFork.drop(this);
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
