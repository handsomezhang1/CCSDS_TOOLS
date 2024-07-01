package study2.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;



public class TableViewerLabelProvider implements ITableLabelProvider {

    @Override
    public void addListener(ILabelProviderListener arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isLabelProperty(Object arg0, String arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Image getColumnImage(Object arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        
    	ArrayList<Object> list= (ArrayList<Object> )element;
return  ((ArrayList<Object>) element).get(columnIndex).toString();

    }

}

