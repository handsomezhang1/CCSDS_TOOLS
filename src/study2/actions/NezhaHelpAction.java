package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class NezhaHelpAction extends Action implements IWorkbenchAction {

    public NezhaHelpAction() {
        setText("哪吒架构帮助");
        // 设置快捷键为 Ctrl+H
        setAccelerator(SWT.CTRL | 'H');
        setId("study2.NezhaHelpAction");
        setActionDefinitionId("org.eclipse.ui.help.helpContents");
    }

    @Override
    public void run() {
    	System.out.println("aaa");
        PlatformUI.getWorkbench().getHelpSystem().displayHelpResource("help/toc.xml");
    }

    @Override
    public void dispose() {
        // Dispose resources if any
    }
}
