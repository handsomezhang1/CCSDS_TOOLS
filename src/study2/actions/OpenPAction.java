package study2.actions;

import java.io.File;
import java.time.LocalDateTime;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import study2.Activator;
import study2.Plugin.PluginManager;
import study2.utils.CopyByChannel;
import study2.utils.FileInput;
import study2.views.editorcontent;
import study2.utils.VariableUtil;

/**
 * @author
 *
 */
public class OpenPAction extends Action {
	private static final String ID = "study2.actions.open";
	private IWorkbenchWindow window;

	public OpenPAction(IWorkbenchWindow window) {
		this.window = window;
		setText("&打开…");
		setId(ID);
		setImageDescriptor(Activator.getImageDescriptor("/icons/newfile_wiz.gif"));
	}

	@Override
	public void run() {
		FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
		String path = dialog.open();
		IWorkbenchPage page = window.getActivePage();
		if (path != null) {
openplugin(window,path);
			
		}

		/*
		 * FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN); String path
		 * = dialog.open(); if (path != null) { //XXX
		 * MessageDialog.openInformation(window.getShell(), "Info", path); }
		 */
	}

	public static void  openplugin(IWorkbenchWindow window,String  path) {
		// TODO Auto-generated method stub

		File tempFile = new File(path.trim());
		String fileName = tempFile.getName();// 插件名称
		VariableUtil.CurrentPuluinName = fileName;
		VariableUtil.CurrentPuluinPath = path;
		PluginManager manager = new PluginManager();
		;
		manager.loadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
		if (manager.test(VariableUtil.CurrentPuluinName.split("\\.")[0]) == false) {
			System.out.println("JAR非法格式！");

			// XXX
			MessageDialog.openError(window.getShell(), "错误信息", "错误(E0001):JAR非法格式！");

			manager.unloadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
		} else {
			CopyByChannel.copyByChannel(path, "Plugin/" + VariableUtil.CurrentPuluinName);
			manager.unloadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
			File directory = new File("");
			System.out.println(directory.getAbsolutePath());
			VariableUtil.CurrentPuluinPath = directory.getAbsolutePath() +"/Plugin/"+ VariableUtil.CurrentPuluinName;// 修改当前路径，可以读取新路径
			manager.loadPlugin(VariableUtil.CurrentPuluinName.split("\\.")[0]);
			
			manager.view(VariableUtil.CurrentPuluinName.split("\\.")[0],VariableUtil.CurrentPuluinPath);
			 LocalDateTime now = LocalDateTime.now();
	            System.out.println("Current local time: " + now);
			System.out.println("[LOG]"+now+" "+"插件插入完成。");

			// XXX
			
			MessageDialog.openInformation(window.getShell(), "提示信息", "提示(T0001):插件插入完成！！！");
			// manager.view(VariableUtil.CurrentPuluinName.split("\\.")[0]);
			
		}
		;

		// System.out.println("方法一：fileName = " + fileName);
		CopyByChannel.copyByChannel(path, "Plugin/" + VariableUtil.CurrentPuluinName);
		FileInput input = new FileInput(path);
		// try {
		// page.openEditor(input, editorcontent.ID);
		// } catch (PartInitException e) {
		// MessageDialog.openError(window.getShell(), "错误", "无法打开文件： " + path);
		// }
		
	}
}