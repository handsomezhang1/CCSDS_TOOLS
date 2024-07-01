package study2.views;


import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.help.IWorkbenchHelpSystem;

public class AbstractHelpUI2 {

    // 实现帮助展示逻辑
    public void showHelp() {
        IWorkbenchHelpSystem helpSystem = PlatformUI.getWorkbench().getHelpSystem();
        helpSystem.displayHelp();
    }

}
