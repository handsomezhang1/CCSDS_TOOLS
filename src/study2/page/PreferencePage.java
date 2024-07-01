package study2.page;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import study2.Activator;
import study2.utils.PreferenceConstants;

public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public PreferencePage() {
        super(GRID);
        // 将 setDescription 移到构造方法，但移除 setPreferenceStore
        setDescription("哪吒架构环境全局配置设置");
    }

    @Override
    public void createFieldEditors() {
    	StringFieldEditor StringFieldEditor1=new StringFieldEditor(PreferenceConstants.P_IP_ADDRESS, "IP地址:", getFieldEditorParent());
    	addField(StringFieldEditor1);
    	IntegerFieldEditor StringFieldEditor2=new IntegerFieldEditor(PreferenceConstants.P_PORT, "端口号:", getFieldEditorParent());
        addField(StringFieldEditor2);
    }
   
    @Override
    public void init(IWorkbench workbench) {
        System.out.println("PreferencePage init called."); // 日志输出或断点
        Activator activator = Activator.getDefault();
        if (activator != null) {
            setPreferenceStore(activator.getPreferenceStore());
        } else {
            System.err.println("Error: Activator not initialized.");
            // 可能的话，显示错误消息给用户
        }
    }
    
    @Override
    public boolean performOk() {
        // 保存所有修改的偏好设置
    	System.err.println("Error: Act666");
        boolean result = super.performOk();
        // 可以在这里添加保存成功后的额外逻辑
        return result;
    }
    @Override
    protected void performApply() {
    	System.err.println("Error: Act666888");
        super.performApply();
        // 可以在这里添加应用成功后的额外逻辑
    }

}
