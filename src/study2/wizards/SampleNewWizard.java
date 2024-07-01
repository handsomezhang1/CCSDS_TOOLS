package study2.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import java.io.*;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.ide.IDE;

import study2.utils.FileInput;
/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "mpe". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class SampleNewWizard extends Wizard implements INewWizard {
	private SampleNewWizardPage page;
	private ISelection selection;

	/**
	 * Constructor for SampleNewWizard.
	 */
	public SampleNewWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */
	@Override
	public void addPages() {
		page = new SampleNewWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	@Override
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		IRunnableWithProgress op = monitor -> {
			try {
				doFinish(containerName, fileName, monitor);
			} catch (CoreException e) {
				throw new InvocationTargetException(e);
			} finally {
				monitor.done();
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(String containerName, String fileName, IProgressMonitor monitor)
	        throws CoreException {
	    monitor.beginTask("Creating " + fileName, 2);
	    File file = new File(containerName, fileName);
	    try {
	        if (!file.exists()) {
	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	                if (fileName.endsWith(".java")) {
		                // 执行针对 .java 文件的特定操作
	                     String contents = "package p1.service;\n"
	                     		+ "\n"
	                     		+ "import java.io.IOException;\n"
	                     		+ "import java.util.ArrayList;\n"
	                     		+ "import java.util.List;\n"
	                     		+ "\n"
	                     		+ "import Factory.ToolServiceFactory;\n"
	                     		+ "import Factory.ToolServiceFactoryImpl;\n"
	                     		+ "\n"
	                     		+ "public class ****** extends ToolServiceFactory implements ToolServiceFactoryImpl {\n"
	                     		+ "\n"
	                     		+ "	public ******() throws IOException {\n"
	                     		+ "		\n"
	                     		+ "		super(\"******\", \"******\");\n"
	                     		+ "		\n"
	                     		+ "		// TODO Auto-generated constructor stub\n"
	                     		+ "	}\n"
	                     		+ "	private String outputtype=\"******\";\n"
	                     		+ "	private String name=\"******\";\n"
	                     		+ "	private String description=\"******\";\n"
	                     		+ "	private String author=\"******\";\n"
	                     		+ "	private String date=\"2024-**-**\";\n"
	                     		+ "	private String ToolName=\"****** \";\n"
	                     		+ "	private String Category=\"******\";\n"
	                     		+ "\n"
	                     		+ "	public List<String> service(ArrayList  list){\n"
	                     		+ "		// TODO 此处为service的实现类,在此处输入代码\n"
	                     		+ "	return resuList;\n"
	                     		+ "	\n"
	                     		+ "}\n"
	                     		+ "	public String V() {\n"
	                     		+ "		return \"SUCCESS\";\n"
	                     		+ "	}\n"
	                     		+ "}\n"
	                     		+ "";
	  	               writer.write(contents);
		            }
	               // String contents = "This is the initial file contents for *.mpe file that should be word-sorted in the Preview page of the multi-page editor";
	              //  writer.write(contents);
	            }
	        } else {
	            throw new CoreException(new Status(IStatus.ERROR, "study2", "File \"" + file.getAbsolutePath() + "\" already exists."));
	        }
	    } catch (IOException e) {
	        throw new CoreException(new Status(IStatus.ERROR, "study2", "Error creating file", e));
	    }
	    monitor.worked(1);
	    monitor.setTaskName("Opening file for editing...");

	    getShell().getDisplay().asyncExec(() -> {
	        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	        try {
	            // 创建一个 FileInput 来表示新创建的文件
	            FileInput input = new FileInput(file.getAbsolutePath());
	            // 检查文件后缀名是否为 .java
	      
	            // 使用特定的编辑器 ID 打开新文件
	            page.openEditor(input, "study2.views.editorcontent");
	        } catch (PartInitException e) {
	            MessageDialog.openError(getShell(), "Error", e.getMessage());
	        }
	    });
	    monitor.worked(1);
	}





	
	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream() {
		String contents =
			"This is the initial file contents for *.mpe file that should be word-sorted in the Preview page of the multi-page editor";
		return new ByteArrayInputStream(contents.getBytes());
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}