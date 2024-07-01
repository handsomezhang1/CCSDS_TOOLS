package study2.utils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MyBoundBean {
	String ourString = "Hello";
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public void setString(String newString) {
		String oldString = ourString;
		ourString = newString;
		changes.firePropertyChange("ourString", oldString, newString);
	}

	public String getString() {
		return ourString;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changes.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changes.removePropertyChangeListener(listener);
	}
}