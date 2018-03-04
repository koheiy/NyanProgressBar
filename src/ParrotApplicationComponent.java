import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ParrotApplicationComponent implements ApplicationComponent {
    private int shownProgressBars;
    private boolean isShown;

    public ParrotApplicationComponent(LafManagerImpl lafManager) {
        lafManager.addLafManagerListener(new LafManagerListener() {
            @Override
            public void lookAndFeelChanged(LafManager lafManager) {
                updateProgressBarUi();
            }
        });
    }

    @Override
    public void initComponent() {
        updateProgressBarUi();
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override

    public String getComponentName() {
        return "NyanLafUpdater";
    }

    private void updateProgressBarUi() {
        UIManager.put("ProgressBarUI", ParrotProgressBarUi.class.getName());
        UIManager.getDefaults().put(ParrotProgressBarUi.class.getName(), ParrotProgressBarUi.class);
    }

    static ParrotApplicationComponent getInstance() {
        return ApplicationManager.getApplication().getComponent(ParrotApplicationComponent.class);
    }
}
