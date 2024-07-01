package study2.utils;

import java.io.File;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class FileInput implements IEditorInput {
	/**
	 * 文件路径
	 */
	private String path = "";

	public FileInput(String path) {
		this.path = path;
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return new File(path).exists();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return path;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return path;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}
		if (obj instanceof FileInput) {
			return path.equals(((FileInput) obj).getName());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return path.hashCode();
	}


}