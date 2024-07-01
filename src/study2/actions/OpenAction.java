package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import study2.Activator;
import study2.utils.FileInput;
import study2.views.editorcontent;

/**
 * @author
 *
 */
public class OpenAction extends Action {
	private static final String ID = "study2.actions.open";
	private IWorkbenchWindow window;

	public OpenAction(IWorkbenchWindow window) {
		this.window = window;
		setText("&打开…");
		setId(ID);
		 // 设置动作的图标
	    setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
  
		
	}

	@Override
	public void run() {
		FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
		String path = dialog.open();
		if(path != null){
			IWorkbenchPage page = window.getActivePage();
			FileInput input = new FileInput(path);
			try {
				page.openEditor(input, editorcontent.ID);
			} catch (PartInitException e) {
				MessageDialog.openError(window.getShell(), "错误", "无法打开文件： " + path);
			}
		}
		
		/*
		FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
		String path = dialog.open();
		if (path != null) {
//XXX
			MessageDialog.openInformation(window.getShell(), "Info", path);
		}
		*/
	}
}