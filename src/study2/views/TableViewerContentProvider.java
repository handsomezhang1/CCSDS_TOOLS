package study2.views;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TableViewerContentProvider  implements IStructuredContentProvider  {

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        
    }

    public Object[] getElements(Object inputElement) {  
          
        // TODO Auto-generated method stub  

        return ((List) inputElement).toArray();  

    }  

}
