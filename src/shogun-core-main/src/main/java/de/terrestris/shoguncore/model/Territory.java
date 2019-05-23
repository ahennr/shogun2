package de.terrestris.shoguncore.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.locationtech.jts.geom.MultiPolygon;


/**
 * @author Nils Bühner
 * @author Kai Volland
 * @author terrestris GmbH & Co. KG
 */
public class Territory extends PersistentObject {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private MultiPolygon geometry;

    /**
     * default constructor
     */
    public Territory() {
        super();
    }

    /**
     *
     * @param name
     * @param geometry
     */
    public Territory(String name, MultiPolygon geometry) {
        super();
        this.name = name;
        this.geometry = geometry;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public MultiPolygon getGeometry() {
        return geometry;
    }

    /**
     * @param geometry
     */
    public void setGeometry(MultiPolygon geometry) {
        this.geometry = geometry;
    }

    /**
     * @see java.lang.Object#hashCode()
     * <p>
     * According to
     * http://stackoverflow.com/questions/27581/overriding-equals
     * -and-hashcode-in-java it is recommended only to use getter-methods
     * when using ORM like Hibernate
     */
    public int hashCode() {
        // two randomly chosen prime numbers
        return new HashCodeBuilder(13, 37).
            appendSuper(super.hashCode()).
            append(getName()).
            append(getGeometry()).
            toHashCode();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     * <p>
     * According to
     * http://stackoverflow.com/questions/27581/overriding-equals
     * -and-hashcode-in-java it is recommended only to use getter-methods
     * when using ORM like Hibernate
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Territory))
            return false;
        Territory other = (Territory) obj;

        return new EqualsBuilder().
            appendSuper(super.equals(other)).
            append(getName(), other.getName()).
            append(getGeometry(), other.getGeometry()).
            isEquals();
    }

}
