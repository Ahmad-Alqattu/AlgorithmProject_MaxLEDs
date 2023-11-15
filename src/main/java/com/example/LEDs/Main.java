package com.example.LEDs;
import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        System.out.print(":llllCll");

        BorderPane root = new BorderPane();
        HBox top = new HBox();
        Button read = new Button("Read file");
        read.setStyle("-fx-background-color: #577E91; -fx-text-fill: black;-fx-font-size: 14px;");
        Label dir = new Label("No file is selected");
        dir.setStyle("-fx-background-color: WHITE; -fx-text-fill: black; -fx-border-color: black;-fx-padding: 5;");
        top.getChildren().addAll(read, dir);
        top.setSpacing(15.0);
        top.setAlignment(Pos.CENTER);
        top.setStyle("-fx-background-color: #2E2E2E;");
        top.setPadding(new Insets(20.0, 10.0, 20.0, 10.0));
        HBox bottom = new HBox();
        bottom.setStyle("-fx-background-color: #2E2E2E;");
        Label maxled = new Label("");
        bottom.setMinHeight(50.0);
        maxled.setStyle(" -fx-text-fill: white;-fx-font-size: 18px; -fx-font-wheight: bold ");
        bottom.getChildren().addAll(maxled);
        bottom.setAlignment(Pos.CENTER);

        root.setBottom(bottom);
        root.setTop(top);

        read.setOnAction((e) -> {
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open inbut File");
                File sfile = fileChooser.showOpenDialog(primaryStage);
                dir.setText(sfile.getAbsolutePath());
                Scanner in = new Scanner(sfile);
                int n = Integer.parseInt(in.next());
                StringBuffer aa = new StringBuffer(in.nextLine());

                while(in.hasNext()) {
                    aa.append(in.nextLine());
                }

                String st = aa.toString();
                String[] string = st.split(" ");
                int[] result = new int[string.length + 1];
                result[0] = 0;

                for(int i = 0; i < string.length; ++i) {
                    result[i + 1] = Integer.valueOf(string[i]);
                }
                Pane pane =new Pane();
                Maxled Maxled = new Maxled(result, n,pane);
                Maxled.node();
                int maxLEDs = Maxled.lis();
                maxled.setText("maximum number of LEDs are lighted= " + maxLEDs);
                ScrollPane scroll= new ScrollPane();
                scroll.setContent(pane);
                root.setCenter(scroll);
            } catch (Exception var15) {
                var15.printStackTrace();
                dir.setText("Error! Select File Again");
            }

        });
        Scene scene = new Scene(root, 650, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
