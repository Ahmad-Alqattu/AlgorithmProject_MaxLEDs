package com.example.LEDs;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.HashMap;

public class Maxled {
    int[] ledSequence;
    HashMap<Integer,StackPane> stackPaneArray= new HashMap<Integer,StackPane>();
    int totalLEDs;
    Pane pane;
    byte[][] p;

    public Maxled(int[] ledSequence, int y,Pane pane) {
        this.pane=pane;
        this.ledSequence = ledSequence;
        this.totalLEDs = y;
        this.p = new byte[ledSequence.length][y + 1];
    }



    public void node() {

        for(int i = 1; i < this.ledSequence.length; ++i) {
            HBox H1 = new HBox();
            Circle circle = new Circle();
            StackPane sp = new StackPane();
            Label l1 = new Label(i+"");
            circle.setRadius(30);
            sp.setLayoutY(i*70);
            sp.setLayoutX(50);
            System.out.println(circle.getLayoutY()+" "+circle.getLayoutX());


            sp.getChildren().addAll(circle, l1);
            H1.setAlignment(Pos.CENTER);
            Circle circle2 = new Circle();


            System.out.println(circle2.getLayoutY()+" "+circle2.getLayoutX());

            circle2.setRadius(30.0
            );
            circle2.setFill(Color.valueOf("#349326"));
            StackPane sp2 = new StackPane();
            sp2.setLayoutY(i*70);
            sp2.setLayoutX(550);
            int sec = this.ledSequence[i];
            Label l2 = new Label(sec+"");
            circle.setRadius(30.0
            );
            circle.setFill(Color.valueOf("#349326"));
            circle.setStrokeWidth(8.0);
            sp2.getChildren().addAll(circle2, l2);
            stackPaneArray.put(sec,sp2);
            pane.getChildren().addAll(sp, sp2);

        }

    }


    public int lis() {
        int[][] temp = new int[ledSequence.length][totalLEDs + 1];
        int max = 0;

        int i;
        for(i = 1; i < ledSequence.length; ++i) {
            temp[i][0] = 0;
        }

        for(i = 1; i <= totalLEDs; ++i) {
            temp[0][i] = 0;
        }

        for(i = 1; i < ledSequence.length; ++i) {
            for(int j = 1; j < totalLEDs + 1; ++j) {
                if (ledSequence[i] == j) {
                    temp[i][j] = temp[i - 1][j - 1] + 1;
                    this.p[i][j] = 1;

                } else if (temp[i][j - 1] > temp[i - 1][j]) {
                    temp[i][j] = temp[i][j - 1];
                    this.p[i][j] = 0;
                } else {
                    temp[i][j] = temp[i - 1][j];
                    this.p[i][j] = 2;

                }

                if (temp[i][j] > max) {
                    max = temp[i][j];
                    System.out.println("ff "+j);

                    stackPaneArray.get(j).getLayoutX();
                    Line line =new Line();
                    line.setFill(Color.valueOf("#349326"));
                    line.setStrokeWidth(2);
                    ((Circle)stackPaneArray.get(j).getChildren().get(0)).setFill(Color.YELLOW);
                    ((Circle)stackPaneArray.get(j).getChildren().get(0)).setStroke(Color.BLACK);
                    line.setStartX(stackPaneArray.get(j).getLayoutX());
                    line.setStartY(stackPaneArray.get(j).getLayoutY()+35);
                    line.setEndX(110);
                    line.setEndY(j*70+35);

                    pane.getChildren().add(line);

                }
                System.out.print(":llllll: max=" + max + "\n");
            }
        }

        return max;
    }
}
