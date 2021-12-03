package Main;

import Entities.AbstractEntity;
import Entities.Bomb.Bomb;
import Entities.Bomb.Explosion;
import Entities.Characters.Bomber;
import Entities.Characters.Mob;
import Entities.MapTiles.PowerUps.Powerup;
import Entities.Message;
import Exceptions.LoadLevelException;
import Graphics.RenderGraphics;
import Graphics.Screen;
import Level.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements RenderGraphics {

    public int width, height;
    protected Level level;
    protected Game game;
    protected KeyHandler keyHandler;
    protected Screen screen;

    public AbstractEntity[] entities;
    public List<Mob> mobs = new ArrayList<Mob>();
    protected List<Bomb> bombs = new ArrayList<Bomb>();
    private List<Message> messages = new ArrayList<Message>();

    private int screenToShow = -1; //1:endgame, 2:change level, 3:paused

    private int time = Game.TIME;
    private int points = Game.POINTS;
    private int lives = Game.LIVES;

    public Board(Game game, KeyHandler input, Screen screen) {
        
        this.game = game;
        keyHandler = input;
        this.screen = screen;

        changeLevel(1); // Bắt đầu từ Level 1.
    }

    @Override
    public void update() {
        if (game.isPaused()) {

            return;
        }

        updateEntities();
        updateMobs();
        updateBombs();
        updateMessages();
        detectEndGame();

        for (int i = 0; i < mobs.size(); i++) {

            Mob a = mobs.get(i);
            if (((AbstractEntity)a).isRemoved()) {
                mobs.remove(i);
            }
        }
    }


    @Override
    public void render(Screen screen) {

        if (game.isPaused()) {

            return;
        }

        // Render Game.
        int x0 = Screen.xOffset >> 4; //tile precision, -> left X
        int x1 = (Screen.xOffset + screen.getWidth() + Game.TILE_SIZE) / Game.TILE_SIZE; // -> right X
        int y0 = Screen.yOffset >> 4;
        int y1 = (Screen.yOffset + screen.getHeight()) / Game.TILE_SIZE;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                entities[x + y * level.getWidth()].render(screen);
            }
        }

        renderBombs(screen);
        renderMobs(screen);
    }

    public void newGame() {

        resetProperties();
        changeLevel(1);
    }

    @SuppressWarnings("static-access")
    private void resetProperties() {

        points = Game.POINTS;
        lives = Game.LIVES;
        Bomber.powerUps.clear();

        game.playerSpeed = 1.0;
        game.bombRadius = 1;
        game.bombRate = 1;

    }

    public void restartLevel() {
        changeLevel(level.getLevel());
    }

    public void nextLevel() {
        changeLevel(level.getLevel() + 1);
    }

    public void changeLevel(int level) {
        
        time = Game.TIME;
        screenToShow = 2;
        game.resetScreenDelay();
        game.pause();
        mobs.clear();
        bombs.clear();
        messages.clear();

        try {
            
            this.level = new FileLevel("MapLevels/Level" + level + ".txt", this);
            entities = new AbstractEntity[this.level.getHeight() * this.level.getWidth()];

            this.level.createEntities();
        } catch (LoadLevelException e) {
            
            endGame();
        }
    }

    public void changeLevelByCode(String str) {

        int i = level.validCode(str);

        if (i != -1) {
            changeLevel(i + 1);
        }
    }

    public boolean isPowerupUsed(int x, int y, int level) {

        Powerup p;
        for (int i = 0; i < Bomber.powerUps.size(); i++) {

            p = Bomber.powerUps.get(i);
            if(p.getX() == x && p.getY() == y && level == p.getLevel()) {
                return true;
            }
        }

        return false;
    }

    // Xác định một số vấn đề game: Đã kết thúc game, hoặc k còn quái.
    protected void detectEndGame() {

        if (time <= 0)
            restartLevel();
    }

    public void endGame() {

        screenToShow = 1;
        game.resetScreenDelay();
        game.pause();
    }

    public boolean detectNoEnemies() {

        int total = 0;
        for (int i = 0; i < mobs.size(); i++) {
            if(!(mobs.get(i) instanceof Bomber))
                ++total;
        }

        return total == 0;
    }

    /*
        Xử lý Pause và Resume game.
     */
    public void gamePause() {

        game.resetScreenDelay();
        if (screenToShow <= 0) {
            screenToShow = 3;
        }

        game.pause();
    }

    public void gameResume() {

        game.resetScreenDelay();
        screenToShow = -1;
        game.run();
    }

    /*
        Màn hình game.
     */
    public void drawScreen(Graphics g) {

        switch (screenToShow) {
            case 1:
                screen.drawEndGame(g, points, level.getActualCode());
                break;
            case 2:
                screen.drawChangeLevel(g, level.getLevel());
                break;
            case 3:
                screen.drawPaused(g);
                break;
        }
    }

    /*
        Từ đây là getter, setter của class.
     */
    public AbstractEntity getEntity(double x, double y, Mob m) {

        AbstractEntity res = null;

        res = getExplosionAt((int)x, (int)y);
        if (res != null) {
            return res;
        }

        res = getBombAt(x, y);
        if (res != null) {
            return res;
        }

        res = getMobAtExcluding((int)x, (int)y, m);
        if (res != null) {
            return res;
        }

        res = getEntityAt((int)x, (int)y);

        return res;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = bombs.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.getX() == (int)x && b.getY() == (int)y)
                return b;
        }

        return null;
    }

    public Mob getMobAt(double x, double y) {
        Iterator<Mob> itr = mobs.iterator();

        Mob cur;
        while(itr.hasNext()) {
            cur = itr.next();

            if(cur.getXTile() == x && cur.getYTile() == y)
                return cur;
        }

        return null;
    }

    public Bomber getPlayer() {
        Iterator<Mob> itr = mobs.iterator();

        Mob cur;

        while(itr.hasNext()) {
            cur = itr.next();

            if(cur instanceof Bomber)
                return (Bomber) cur;
        }

        return null;
    }

    public Mob getMobAtExcluding(int x, int y, Mob a) {

        Iterator<Mob> itr = mobs.iterator();

        Mob cur;
        while(itr.hasNext()) {

            cur = itr.next();
            if(cur == a) {
                continue;
            }

            if(cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }

        }

        return null;
    }

    public Explosion getExplosionAt(int x, int y) {

        Iterator<Bomb> bs = bombs.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();

            Explosion e = b.explosionAt(x, y);
            if(e != null) {
                return e;
            }

        }

        return null;
    }

    public AbstractEntity getEntityAt(double x, double y) {

        return entities[(int)x + (int)y * level.getWidth()];
    }

    /*
        Thêm các nhân vật vào danh sách mob trên bản đồ hiện tại.
     */
    public void addEntities(int pos, AbstractEntity e) {
        entities[pos] = e;
    }

    public void addMob(Mob e) {
        mobs.add(e);
    }

    public void addBomb(Bomb e) {
        bombs.add(e);
    }

    public void addMessage(Message e) {
        messages.add(e);
    }

    /*
        Render các thực thể trên màn hình.
     */
    protected void renderEntities(Screen screen) {

        for (int i = 0; i < entities.length; i++) {
            entities[i].render(screen);
        }
    }

    protected void renderMobs(Screen screen) {

        Iterator<Mob> itr = mobs.iterator();

        while (itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    protected void renderBombs(Screen screen) {

        Iterator<Bomb> itr = bombs.iterator();

        while (itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    
    public void renderMessages(Graphics g) {

        Message m;
        for (int i = 0; i < messages.size(); i++) {

            m = messages.get(i);

            g.setFont(new Font("Arial", Font.PLAIN, m.getSize()));
            g.setColor(m.getColor());
            g.drawString(m.getMessage(), (int)m.getX() - Screen.xOffset * Game.SCALE_MULTIPLE, (int) m.getY());
        }
    }
    

    /*
        Update các thông số thực thể trên bản đồ.
     */
    protected void updateEntities() {

        if (game.isPaused() ) return;
        for (int i = 0; i < entities.length; i++) {
            entities[i].update();
        }
    }

    protected void updateMobs() {

        if (game.isPaused()) {
            return;
        }

        Iterator<Mob> itr = mobs.iterator();

        while(itr.hasNext() && !game.isPaused()) {
            itr.next().update();
        }
    }

    protected void updateBombs() {

        if (game.isPaused()) {
            return;
        }

        Iterator<Bomb> itr = bombs.iterator();

        while(itr.hasNext()) {
            itr.next().update();
        }
    }

    protected void updateMessages() {

        if (game.isPaused()) {
            return;
        }
        
        Message m;
        int left = 0;
        for (int i = 0; i < messages.size(); i++) {

            m = messages.get(i);
            left = m.getDuration();

            if(left > 0) {
                m.setDuration(--left);
            } else {
                messages.remove(i);
            }
        }
    }

    public KeyHandler getInput() {

        return keyHandler;
    }

    public Level getLevel() {

        return level;
    }

    public Game getGame() {

        return game;
    }

    public int getShow() {

        return screenToShow;
    }

    public void setShow(int i) {

        screenToShow = i;
    }

    public int getTime() {

        return time;
    }

    public int getLives() {

        return lives;
    }

    public int subtractTime() {

        if(game.isPaused()) {
            return this.time;
        } else {
            return this.time--;
        }
    }

    public int getPoints() {

        return points;
    }

    public void addPoints(int points) {

        this.points += points;
    }

    public void addLives(int lives) {

        this.lives += lives;
    }

    public int getWidth() {

        return level.getWidth();
    }

    public int getHeight() {

        return level.getHeight();
    }
}
