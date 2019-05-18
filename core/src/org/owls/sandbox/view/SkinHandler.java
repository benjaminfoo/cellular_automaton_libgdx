package org.owls.sandbox.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.VisUI;

public class SkinHandler extends VisUI {

    public enum SkinScale {

        custom_skin("skin/x1/uiskin.json", "default");

        private final String classpath;
        private final String sizesName;

        SkinScale(String classpath, String sizesName) {
            this.classpath = classpath;
            this.sizesName = sizesName;
        }

        public FileHandle getSkinFile() {
            return Gdx.files.classpath(classpath);
        }

        public String getSizesName() {
            return sizesName;
        }
    }

    /*
    public static Drawable getBackground() {
        return getSkin().getDrawable("window-bg");
    }
    */

}