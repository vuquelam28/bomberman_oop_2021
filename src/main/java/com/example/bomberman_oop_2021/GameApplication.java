package com.example.bomberman_oop_2021;

import com.example.bomberman_oop_2021.entities.AbstractEntity;
import com.example.bomberman_oop_2021.entities.Character.Player;
import com.example.bomberman_oop_2021.entities.mapEntities.Grass;
import com.example.bomberman_oop_2021.entities.mapEntities.Wall;
import com.example.bomberman_oop_2021.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class GameApplication extends Application {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Player> entities = new ArrayList<Player>();
    private List<AbstractEntity> aliveObjects = new ArrayList<AbstractEntity>();

    public GameApplication() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Thêm scene vao stage
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Player bomberman = new Player(1, 1, Sprite.playerRight.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                AbstractEntity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }

                aliveObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(AbstractEntity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        aliveObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
