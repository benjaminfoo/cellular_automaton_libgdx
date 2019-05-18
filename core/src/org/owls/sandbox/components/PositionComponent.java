package org.owls.sandbox.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent extends Component {

    public Vector2 pos;

    public PositionComponent() {
        this.pos = new Vector2();
    }

    public PositionComponent(float x, float y){
        this.pos = new Vector2(x, y);
    }

}
