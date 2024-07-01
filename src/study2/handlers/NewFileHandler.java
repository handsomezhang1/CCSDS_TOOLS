package study2.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.eclipse.ui.ide.IDE;
public class NewFileHandler extends AbstractHandler {
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        
        // 创建文件名输入对话框
        InputDialog inputDialog = new InputDialog(shell, "New File", "Enter a file name:", "newfile", null);
        
        // 创建文件类型选择对话框
        FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
        fileDialog.setFilterExtensions(new String[] { "*.txt", "*.xml" });
        fileDialog.setFilterNames(new String[] { "Text File (*.txt)", "XML File (*.xml)" });

        if (inputDialog.open() == InputDialog.OK) {
            String fileName = inputDialog.getValue();
            fileDialog.setFileName(fileName);
            
            String filePath = fileDialog.open();
            if (filePath != null) {
                try {
                    File file = new File(filePath);
                    if (file.createNewFile()) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.close();
                        
                        // 打开文件
                        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                        IPath path = Path.fromOSString(filePath);
                        IDE.openEditor(page, path, true);
                    } else {
                        MessageDialog.openError(shell, "Error", "File already exists.");
                    }
                } catch (Exception e) {
                    throw new ExecutionException("Error creating new file", e);
                }
            }
        }
        return null;
    }
}
