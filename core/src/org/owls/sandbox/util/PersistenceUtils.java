package org.owls.sandbox.util;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import org.owls.sandbox.model.GameOfLifeMatrix;

import java.io.FileWriter;
import java.io.IOException;

public class PersistenceUtils {

    public void save(FileHandle path, GameOfLifeMatrix gameOfLifeMatrix) {
        Json json = new Json();
        try {
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(path.file()));
            jsonWriter.write(json.prettyPrint(gameOfLifeMatrix));
            jsonWriter.flush();
            jsonWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameOfLifeMatrix load(FileHandle path) {
        Json json = new Json();
        return json.fromJson(GameOfLifeMatrix.class, path);
    }

}
