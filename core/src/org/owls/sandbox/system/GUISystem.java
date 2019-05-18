package org.owls.sandbox.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.kotcrab.vis.ui.VisUI;

import org.owls.sandbox.components.AutomataComponent;
import org.owls.sandbox.components.ToolboxComponent;
import org.owls.sandbox.view.PatternsStage;
import org.owls.sandbox.view.RuleSetStage;
import org.owls.sandbox.view.StatisticsStage;
import org.owls.sandbox.view.ToolboxStage;

public class GUISystem extends EntityProcessingSystem {

    private ToolboxStage toolboxStage;
    private PatternsStage patternsStage;
    private StatisticsStage statisticsStage;
    private RuleSetStage ruleSetStage;

    @Wire
    private OrthographicCamera orthographicCamera;

    public GUISystem() {
        super(Aspect.one(AutomataComponent.class, ToolboxComponent.class));

        // load visui and the tixel-skin
        VisUI.load();
        // SkinHandler.load(new Skin(Gdx.files.internal("tixel.json")));

        toolboxStage = new ToolboxStage();
        toolboxStage.create();

        patternsStage = new PatternsStage();
        patternsStage.create();

        statisticsStage = new StatisticsStage();
        statisticsStage.create();

        ruleSetStage = new RuleSetStage();
        ruleSetStage.create();

        // setup input for the windows
        Gdx.input.setInputProcessor(
                new InputMultiplexer(
                        toolboxStage.getStage(),
                        patternsStage.getStage(),
                        statisticsStage.getStage(),
                        ruleSetStage.getStage()
                )
        );


        Gdx.app.log(getClass().getSimpleName(), "... graphical user interface initialized");

    }

    @Override
    protected void process(Entity e) {

        if (e.getComponent(AutomataComponent.class) != null) {
            AutomataComponent automataComponent = e.getComponent(AutomataComponent.class);

            statisticsStage.updateInfos(automataComponent.game);
            toolboxStage.updateInfos(automataComponent.game);
            patternsStage.updateInfos(automataComponent.game);
            ruleSetStage.updateInfos(automataComponent.game);
        }

        toolboxStage.render();
        patternsStage.render();
        statisticsStage.render();
        ruleSetStage.render();

    }

}
