package org.owls.sandbox.desktop;

import org.owls.sandbox.data.PredefinedMaps;
import org.owls.sandbox.model.GameOfLifeMatrix;

public class ConsoleLauncher {

    /**
     * @param args
     */
    public static void main(String[] args) {
        GameOfLifeMatrix gol = new GameOfLifeMatrix(1280, 720);
        gol.setMatrixData(PredefinedMaps.heavyWeightSpaceship);
        gol.startSimulation(512);
    }

}
