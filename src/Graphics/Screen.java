package Graphics;

import Entities.AbstractEntity;
import Entities.Characters.Bomber;
import Main.Board;
import Main.Game;

import java.awt.*;

public class Screen {

    protected int width, height;
    public int[] pixels;
    private int transparentColor = 0xffff00ff;

    public static int xOffset = 0, yOffset = 0;

    public Screen(int width, int height) {

        this.width = width;
        this.height = height;

        pixels = new int[width * height];

    }

    public void clear() {

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderEntity(int xp, int yp, AbstractEntity entity) { //save entity pixels
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + yp; //add offset
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xp; //add offset

                if (xa < -entity.getSprite().getSize() || xa >= width || ya < 0 || ya >= height) {
                    break; //fix black margins
                }

                if (xa < 0) {
                    xa = 0; //start at 0 from left
                }

                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if (color != transparentColor) {
                    pixels[xa + ya * width] = color;
                }
            }
        }
    }

    public void renderEntityWithBelowSprite(int xp, int yp, AbstractEntity entity, Sprite below) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xp;
                if (xa < -entity.getSprite().getSize() || xa >= width || ya < 0 || ya >= height) {
                    break; //fix black margins
                }
                if (xa < 0) {
                    xa = 0;
                }

                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if(color != transparentColor) {
                    pixels[xa + ya * width] = color;
                } else {
                    pixels[xa + ya * width] = below.getPixel(x + y * below.getSize());
                }
            }
        }
    }

    public static void setOffset(int xO, int yO) {
        
        xOffset = xO;
        yOffset = yO;
    }

    public static int calculateXOffset(Board board, Bomber bomber) {
        
        if (bomber == null) {
            return 0;
        }
        
        int temp = xOffset;

        double bomberX = bomber.getX() / 16;
        double complement = 0.5;
        int firstBreakpoint = board.getWidth() / 4;
        int lastBreakpoint = board.getWidth() - firstBreakpoint;

        if (bomberX > firstBreakpoint + complement && bomberX < lastBreakpoint - complement) {
            temp = (int) bomber.getX()  - (Game.WIDTH / 2);
        }

        return temp;
    }

    /*
        Vẽ các màn hình game.
     */
    public void drawEndGame(Graphics g, int points, String code) {
        
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * Game.SCALE_MULTIPLE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("GAME OVER", getRealWidth(), getRealHeight(), g);

        font = new Font("Arial", Font.PLAIN, 10 * Game.SCALE_MULTIPLE);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("POINTS: " + points, getRealWidth(), getRealHeight() + (Game.TILE_SIZE * 2) * Game.SCALE_MULTIPLE, g);


        font = new Font("Arial", Font.PLAIN, 10 * Game.SCALE_MULTIPLE);
        g.setFont(font);
        g.setColor(Color.GRAY);
        drawCenteredString(code, getRealWidth(), getRealHeight() * 2  - (Game.TILE_SIZE * 2) * Game.SCALE_MULTIPLE, g);
    }

    public void drawChangeLevel(Graphics g, int level) {
        
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * Game.SCALE_MULTIPLE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("LEVEL " + level, getRealWidth(), getRealHeight(), g);

    }

    public void drawPaused(Graphics g) {
        Font font = new Font("Arial", Font.PLAIN, 20 * Game.SCALE_MULTIPLE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("PAUSED", getRealWidth(), getRealHeight(), g);

    }

    
    public void drawCenteredString(String s, int w, int h, Graphics g) {
        
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    public int getWidth() {
        
        return width;
    }

    public int getHeight() {
        
        return height;
    }

    public int getRealWidth() {
        
        return width * Game.SCALE_MULTIPLE;
    }

    public int getRealHeight() {
        
        return height * Game.SCALE_MULTIPLE;
    }
}
