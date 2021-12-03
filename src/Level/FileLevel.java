package Level;

import Entities.Characters.Bomber;
import Entities.Characters.Enemies.*;
import Entities.LayeredEntity;
import Entities.MapTiles.DestroyableTiles.BrickTile;
import Entities.MapTiles.PowerUps.PowerupBombs;
import Entities.MapTiles.PowerUps.PowerupFlames;
import Entities.MapTiles.PowerUps.PowerupSpeed;
import Entities.MapTiles.Undestroyable.GrassTile;
import Entities.MapTiles.Undestroyable.PortalTile;
import Entities.MapTiles.Undestroyable.WallTile;
import Exceptions.LoadLevelException;
import Graphics.Screen;
import Graphics.Sprite;
import Main.Board;
import Main.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

// Class này dùng để tải dữ liệu về bản đồ của một level lên.
public class FileLevel extends Level {

    public FileLevel(String path, Board board) throws LoadLevelException {

        super(path, board);
    }

    @Override
    public void loadLevel(String path) throws LoadLevelException {

        try {
            URL absPath = FileLevel.class.getResource("/" + path);

            BufferedReader in = new BufferedReader(new InputStreamReader(absPath.openStream()));

            String data = in.readLine();
            StringTokenizer tokens = new StringTokenizer(data);

            level = Integer.parseInt(tokens.nextToken());
            height = Integer.parseInt(tokens.nextToken());
            width = Integer.parseInt(tokens.nextToken());

            lineTiles = new String[height];

            for(int i = 0; i < height; ++i) {
                lineTiles[i] = in.readLine().substring(0, width);
            }

            in.close();
        } catch (IOException e) {
            throw new LoadLevelException("Error loading level " + path, e);
        }
    }

    @Override
    public void createEntities() {

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                addLevelEntity(lineTiles[y].charAt(x), x, y );
            }
        }
    }

    public void addLevelEntity(char c, int x, int y) {

        int pos = x + y * getWidth();

        switch(c) {

            case '#':
                board.addEntities(pos, new WallTile(x, y, Sprite.wall));
                break;

            case 'b':
                LayeredEntity layer = new LayeredEntity(x, y, new GrassTile(x ,y, Sprite.grass), new BrickTile(x ,y, Sprite.brick));

                if (!board.isPowerupUsed(x, y, level)) {
                    layer.addBeforeTop(new PowerupBombs(x, y, level, Sprite.powerup_bombs));
                }

                board.addEntities(pos, layer);
                break;

            case 's':
                layer = new LayeredEntity(x, y,
                        new GrassTile(x ,y, Sprite.grass),
                        new BrickTile(x ,y, Sprite.brick));

                if (!board.isPowerupUsed(x, y, level)) {
                    layer.addBeforeTop(new PowerupSpeed(x, y, level, Sprite.powerup_speed));
                }

                board.addEntities(pos, layer);
                break;

            case 'f':
                layer = new LayeredEntity(x, y,
                        new GrassTile(x ,y, Sprite.grass),
                        new BrickTile(x ,y, Sprite.brick));

                if (!board.isPowerupUsed(x, y, level)) {
                    layer.addBeforeTop(new PowerupFlames(x, y, level, Sprite.powerup_flames));
                }

                board.addEntities(pos, layer);
                break;

            case '*':
                board.addEntities(pos, new LayeredEntity(x, y, new GrassTile(x ,y, Sprite.grass), new BrickTile(x ,y, Sprite.brick)) );
                break;

            case 'x':
                board.addEntities(pos, new LayeredEntity(x, y, new GrassTile(x ,y, Sprite.grass),
                                   new PortalTile(x ,y, board, Sprite.portal), new BrickTile(x ,y, Sprite.brick)) );
                break;

            case ' ':
                board.addEntities(pos, new GrassTile(x, y, Sprite.grass));
                break;

            case 'p':
                board.addMob(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                Screen.setOffset(0, 0);

                board.addEntities(pos, new GrassTile(x, y, Sprite.grass) );
                break;

            //Enemies
            case '1':
                board.addMob(new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntities(pos, new GrassTile(x, y, Sprite.grass) );
                break;

            case '2':
                board.addMob(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntities(pos, new GrassTile(x, y, Sprite.grass) );
                break;

            case '3':
                board.addMob(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntities(pos, new GrassTile(x, y, Sprite.grass) );
                break;

            case '4':
                board.addMob(new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntities(pos, new GrassTile(x, y, Sprite.grass) );
                break;

            case '5':
                board.addMob(new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                board.addEntities(pos, new GrassTile(x, y, Sprite.grass) );
                break;

            default:
                board.addEntities(pos, new GrassTile(x, y, Sprite.grass) );
                break;
        }
    }
}
