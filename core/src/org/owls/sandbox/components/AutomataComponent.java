package org.owls.sandbox.components;

import com.artemis.Component;

import org.owls.sandbox.model.GameOfLifeMatrix;

public class AutomataComponent extends Component {

    public GameOfLifeMatrix game;


    public AutomataComponent() {
        game = new GameOfLifeMatrix(256, 256);
    }

}
