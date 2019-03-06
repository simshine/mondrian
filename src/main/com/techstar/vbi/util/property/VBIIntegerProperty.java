package com.techstar.vbi.util.property;

import java.util.Properties;

import org.eigenbase.util.property.IntegerProperty;

public class VBIIntegerProperty extends IntegerProperty {
    //~ Constructors -----------------------------------------------------------

    /**
     * Creates an Integer property. Minimum and maximum values are set to {@link
     * Integer#MIN_VALUE} and {@link Integer#MAX_VALUE}.
     *
     * @param properties Properties object which holds values for this property.
     * @param path Name by which this property is serialized to a properties
     * file, for example "com.acme.trace.Verbosity".
     * @param defaultValue Default value.
     */
    public VBIIntegerProperty(
        Properties properties,
        String path,
        int defaultValue)
    {
    	super(
            properties,
            path,
            defaultValue);
    }

    /**
     * Creates an Integer property which has no default value. Minimum and
     * maximum values are set to {@link Integer#MIN_VALUE} and {@link
     * Integer#MAX_VALUE}.
     *
     * @param properties Properties object which holds values for this property.
     * @param path Name by which this property is serialized to a properties
     * file, for example "com.acme.trace.Verbosity".
     */
    public VBIIntegerProperty(
        Properties properties,
        String path)
    {
        super(properties, path);
    }

    /**
     * Creates an Integer property with a default value and fixed minimum and
     * maximum values.
     *
     * @param properties Properties object which holds values for this property.
     * @param path Name by which this property is serialized to a properties
     * file, for example "com.acme.trace.Verbosity".
     * @param defaultValue Default value.
     * @param minValue the minimum value of this property (inclusive)
     * @param maxValue the maximum value of this property (inclusive)
     *
     * @throws IllegalArgumentException if <code>defaultValue</code> is not in
     * the range [<code>minValue</code>, <code>maxValue</code>].
     */
    public VBIIntegerProperty(
        Properties properties,
        String path,
        int defaultValue,
        int minValue,
        int maxValue)
    {
        super(
            properties,
            path,
            defaultValue, 
            minValue, 
            maxValue);
    }

    /**
     * Creates an Integer property with fixed minimum and maximum values.
     *
     * @param properties Properties object which holds values for this property.
     * @param path Name by which this property is serialized to a properties
     * file, for example "com.acme.trace.Verbosity".
     * @param minValue the minimum value of this property (inclusive)
     * @param maxValue the maximum value of this property (inclusive)
     */
    public VBIIntegerProperty(
        Properties properties,
        String path,
        int minValue,
        int maxValue)
    {
        super(properties, 
        	path, 
        	minValue, 
        	maxValue);
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
