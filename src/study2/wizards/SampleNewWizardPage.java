package study2.wizards;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (mpe).
 */

public class SampleNewWizardPage extends WizardPage {
	private Text containerText;

	private Text fileText;

	private ISelection selection;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public SampleNewWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Multi-page Editor File");
		setDescription("This wizard creates a new file extension that can be opened by a multi-page editor.");
		this.selection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Container:");

		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(e -> dialogChanged());

		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		label = new Label(container, SWT.NULL);
		label.setText("&File name:");

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(e -> dialogChanged());
		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				containerText.setText(container.getFullPath().toString());
			}
		}
		fileText.setText("new_file.xml");
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowse() {
	    DirectoryDialog dialog = new DirectoryDialog(getShell());
	    dialog.setText("Select new file container");
	    dialog.setMessage("Select a directory");
	    String selectedDirectory = dialog.open();
	    if (selectedDirectory != null) {
	        containerText.setText(selectedDirectory);
	    }
	}


	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
	    String containerName = getContainerName();
	    String fileName = getFileName();

	    if (containerName.length() == 0) {
	        updateStatus("File container must be specified");
	        return;
	    }
	    // 由于路径可能不在工作空间中，我们不再检查路径是否存在于工作空间中
	    // 而是检查路径是否为非空和有效的系统路径
	    File containerFile = new File(containerName);
	    if (!containerFile.isDirectory()) {
	        updateStatus("File container must be a valid directory");
	        return;
	    }
	    if (!containerFile.canWrite()) {
	        updateStatus("File container must be writable");
	        return;
	    }
	    if (fileName.length() == 0) {
	        updateStatus("File name must be specified");
	        return;
	    }
	    if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
	        updateStatus("File name must be valid");
	        return;
	    }
	    int dotLoc = fileName.lastIndexOf('.');
	    if (dotLoc != -1) {
	        String ext = fileName.substring(dotLoc + 1);
	        if (!ext.equalsIgnoreCase("cpp")&&!ext.equalsIgnoreCase("py")&&!ext.equalsIgnoreCase("java")&&!ext.equalsIgnoreCase("c")&&!ext.equalsIgnoreCase("xml")&&!ext.equalsIgnoreCase("xsd")&&!ext.equalsIgnoreCase("txt")&&!ext.equalsIgnoreCase("doc")) {
	            updateStatus("File extension must be \"cpp,c,java,py,txt,xml,xsd,doc\"");
	            return;
	        }
	    }
	    updateStatus(null);
	}


	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}

	public String getFileName() {
		return fileText.getText();
	}}