package de.terrestris.shoguncore.model.wps;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import de.terrestris.shoguncore.converter.PropertyValueConverter;

/**
 *
 */
public class WpsProcessExecute extends WpsReference {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String identifier;

    /**
     *
     */
    private Map<String, WpsParameter> input = new HashMap<>();

    /**
     *
     */
    private Map<String, Object> inputDefaultValues = new HashMap<>();

    /**
     * Constructor
     */
    public WpsProcessExecute() {
    }

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the input
     */
    public Map<String, WpsParameter> getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(Map<String, WpsParameter> input) {
        this.input = input;
    }

    /**
     * @return the inputDefaultValues
     */
    public Map<String, Object> getInputDefaultValues() {
        return inputDefaultValues;
    }

    /**
     * @param inputDefaultValues the inputDefaultValues to set
     */
    public void setInputDefaultValues(Map<String, Object> inputDefaultValues) {
        this.inputDefaultValues = inputDefaultValues;
    }

    /**
     * @see java.lang.Object#hashCode()
     * <p>
     * According to
     * http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java
     * it is recommended only to use getter-methods when using ORM like Hibernate
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(47, 23) // two randomly chosen prime numbers
            .appendSuper(super.hashCode())
            .append(getIdentifier())
            .toHashCode();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     * <p>
     * According to
     * http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java
     * it is recommended only to use getter-methods when using ORM like Hibernate
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WpsProcessExecute))
            return false;
        WpsProcessExecute other = (WpsProcessExecute) obj;

        return new EqualsBuilder()
            .appendSuper(super.equals(other))
            .append(getIdentifier(), other.getIdentifier())
            .isEquals();
    }

    /**
     *
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
            .appendSuper(super.toString())
            .append("identifier", identifier)
            .toString();
    }
}
