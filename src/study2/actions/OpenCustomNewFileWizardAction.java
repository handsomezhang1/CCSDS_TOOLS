package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

import study2.Activator;
import study2.wizards.SampleNewWizard;

public class OpenCustomNewFileWizardAction extends Action {

    public OpenCustomNewFileWizardAction() {
        setId("study2.action.OpenCustomNewFileWizardAction");
		setText("&新建…");
		setImageDescriptor(Activator.getImageDescriptor("/icons/newfile_wiz.gif"));
        // 可以为 Action 设置图标等属性
    }

    @Override
    public void run() {
        // 创建并打开自定义的新建文件向导
    	SampleNewWizard wizard = new SampleNewWizard();
        wizard.init(PlatformUI.getWorkbench(), null);
        WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
        dialog.open();
    }
}
