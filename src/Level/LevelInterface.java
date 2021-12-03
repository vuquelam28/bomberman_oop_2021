package Level;

import Exceptions.LoadLevelException;

public interface LevelInterface {

    public void loadLevel(String path) throws LoadLevelException;
}
