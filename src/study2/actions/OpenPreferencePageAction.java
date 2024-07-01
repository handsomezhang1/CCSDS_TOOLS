package study2.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.dialogs.PreferencesUtil;

public class OpenPreferencePageAction extends Action {

    public OpenPreferencePageAction() {
        super("Settings");
        setId("study2.actions.OpenPreferencePageAction"); // 设置唯一的 ID
        // 设置 Action 的文本、图标等属性
    }

    @Override
    public void run() {
        // 打开特定的 Preference Page
        PreferencesUtil.createPreferenceDialogOn(null, "study2.page.preferencePage", null, null).open();
    }
}
