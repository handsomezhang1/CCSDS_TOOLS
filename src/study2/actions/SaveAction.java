package study2.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

import study2.views.TelemetryDataView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;

public class SaveAction extends Action {
    private TelemetryDataView view;
    private boolean isCollecting = false;

    public SaveAction(TelemetryDataView view) {
      super("Start Collecting");
        this.view = view;
    }

    @Override
    public void run() {
        isCollecting = !isCollecting;
        if (isCollecting) {
            setText("Stop Collecting");
            view.scheduleSave();
        } else {
            setText("Start Collecting");
            view.stopSave();
        }
    }
}

