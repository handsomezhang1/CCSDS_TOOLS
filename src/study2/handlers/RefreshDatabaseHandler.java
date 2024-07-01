package study2.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

import study2.views.DataBaseView;

public class RefreshDatabaseHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        // 获取当前激活的数据库视图
        DataBaseView view = (DataBaseView) HandlerUtil.getActivePartChecked(event);
        // 调用视图的刷新方法
        view.refreshAllTableViews();
        return null;
    }
}
