package study2.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import study2.utils.FileInput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.custom.ScrolledComposite;

public class editorcontent extends EditorPart implements ISaveablePart2 {
	public static final String ID = "study2.views.editorcontent";
	private Text text;
	private boolean dirty;
	private FileInput input;

	public editorcontent() {
	}

	/*
	 * (non-Javadoc )
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
	     ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER| SWT.V_SCROLL | SWT.H_SCROLL);
	        scrolledComposite.setExpandHorizontal(true);
	        scrolledComposite.setExpandVertical(true);
	     // 创建 Text 控件，确保包含 SWT.MULTI 和滚动条样式
	        text = new Text(scrolledComposite, SWT.BORDER | SWT.WRAP | SWT.MULTI );
	        scrolledComposite.setContent(text);
	       // 更新 ScrolledComposite 的最小尺寸
	        text.addModifyListener(new ModifyListener() {
	            @Override
	            public void modifyText(ModifyEvent e) {
	                dirty = true;
	                firePropertyChange(ISaveablePart2.PROP_DIRTY);
	                scrolledComposite.setMinSize(text.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	            }
	        });
	        loadText();
	    }


	/*
	 * (non-Javadoc )
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.
	 * 
	 * IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		saveToLocal(input.getName());
		dirty = false;
		firePropertyChange(ISaveablePart2.PROP_DIRTY);
	}

	/*
	 * (non-Javadoc )
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		FileDialog dialog = new FileDialog(getEditorSite().getShell(), SWT.SAVE);
		String path = dialog.open();
		if (path != null) {
			saveToLocal(path);
		}
	}

	/*
	 * (non-Javadoc )
	 * 
	 * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite,
	 * org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {

		setSite(site);
		setInput(input);
		setPartName(input.getName());
		this.input = (FileInput) input;
	}

	/*
	 * (non-Javadoc )
	 * 
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return dirty;
	}

	/*
	 * (non-Javadoc )
	 * 
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * 载入文件内容
	 */
	private void loadText() {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(input.getName()), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String line = reader.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = reader.readLine();
			}
			reader.close();
			text.setText(sb.toString());
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}
	}

	@Override
	public int promptToSaveOnClose() {
		if (dirty) {
			if (MessageDialog.openConfirm(getEditorSite().getShell(), "提示", "内容未保存，确认继续关闭操作？")) {
				return ISaveablePart2.NO;
			} else {
				return ISaveablePart2.CANCEL;
			}
		}
		return YES;
	}

	/**
	 * 保存文件内容到本地
	 * 
	 * @param path
	 */
	private void saveToLocal(String path) {
		try {
			OutputStream out = new FileOutputStream(path);
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
			writer.write(text.getText());
			writer.close();

			out.close();
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}
	}

	/*
	 * (non-Javadoc )
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		text.setFocus();
	}
}
