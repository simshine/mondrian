package com.techstar.vbi.util.property;

import java.util.Properties;

import org.eigenbase.util.property.BooleanProperty;

public class VBIBooleanProperty extends BooleanProperty {
	/**
     * Creates a Boolean property.
     *
     * @param properties Properties object which holds values for this property.
     * @param path Name by which this property is serialized to a properties
     * file, for example "com.acme.trace.Verbosity".
     * @param defaultValue Default value, null if there is no default.
     */
    public VBIBooleanProperty(
        Properties properties,
        String path,
        boolean defaultValue)
    {
    	super(properties, path, defaultValue);
    }

    /**
     * Creates a Boolean property which has no default value.
     *
     * @param properties Properties object which holds values for this property.
     * @param path Name by which this property is serialized to a properties
     * file, for example "com.acme.trace.Verbosity".
     */
    public VBIBooleanProperty(
        Properties properties,
        String path)
    {
        super(properties, path);
    }

    //~ Methods ----------------------------------------------------------------

    private String value;//缓存值
	private boolean isLoad = false;
	/**
     * Sets a property directly as a string.
     *
     * @return the previous value
     */
    public String setString(String value)
    {
    	synchronized (this) {
	    	this.value = value;
	        return (String) super.setString(value);
    	}
    }
	/**
     * Retrieves the value of a property, using a given default value, and
     * optionally failing if there is no value.
     */
    protected String getInternal(
        String defaultValue,
        boolean required)
    {
    	String path = getPath();
    	if (!isLoad) {
    		synchronized (this) {
    			if (!isLoad) {
    				value = properties.getProperty(path);
    				isLoad = true;
    			}
			}
    	}
        String value = (this.value == null) ? defaultValue : this.value;
        if (value != null) {
            return value;
        }
        if (defaultValue == null) {
            value = getDefaultValue();
            if (value != null) {
                return value;
            }
        }
        if (required) {
            throw new RuntimeException("Property " + path + " must be set");
        }
        return value;
    }
}
