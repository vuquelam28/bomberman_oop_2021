package Level;

import Exceptions.LoadLevelException;
import Main.Board;

public abstract class Level implements LevelInterface {

    protected int width, height, level;
    protected String[] lineTiles;
    protected Board board;

    protected static String[] codes = {
            "You are pathetic",
            "Fucking noob",
            "Good try, but low skill",
            "Idiot sandwich",
            "Where's my lamb sauce?",
    };

    public Level(String path, Board board) throws LoadLevelException {

        loadLevel(path);
        this.board = board;
    }

    @Override
    public abstract void loadLevel(String path) throws LoadLevelException;

    public abstract void createEntities();

    public int validCode(String str) {

        for (int i = 0; i < codes.length; i++) {
            if (codes[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    // Lấy tên của file level thực tế.
    public String getActualCode() {

        return codes[level - 1];
    }

    // Getter chiều cao và chiều rộng của file bản đồ.
    public int getWidth() {

        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }

}
