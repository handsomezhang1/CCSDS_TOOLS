package study2.actions;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

public class CustomNewFileResourceWizard extends BasicNewFileResourceWizard {
    
    public CustomNewFileResourceWizard() {
        super();
    }

    @Override
    public void addPages() {
        super.addPages();
        IStructuredSelection selection = getSelection();
        WizardNewFileCreationPage mainPage = (WizardNewFileCreationPage) getStartingPage();
        
        // 设置默认的文件夹路径为项目的根目录
        IContainer container = getDefaultContainer();
        mainPage.setContainerFullPath(container.getFullPath());
        
        // 添加自定义选项，例如文件编码类型
        mainPage.setFileExtension("txt"); // 设置文件扩展名
        // 选择和显示默认文件夹
        selectAndReveal(container, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
    }
    
    // 获取默认的文件夹路径为项目的根目录
    private IContainer getDefaultContainer() {
        IStructuredSelection selection = getSelection();
        if (selection != null && !selection.isEmpty()) {
            Object selectedElement = selection.getFirstElement();
            if (selectedElement instanceof IResource) {
                IResource resource = (IResource) selectedElement;
                if (resource.getType() == IResource.FILE) {
                    return resource.getParent();
                }
                if (resource.getType() == IResource.FOLDER || resource.getType() == IResource.PROJECT) {
                    return (IContainer) resource;
                }
            }
        }
        return ResourcesPlugin.getWorkspace().getRoot();
    }
}
