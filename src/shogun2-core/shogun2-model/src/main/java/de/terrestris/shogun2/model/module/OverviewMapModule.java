/**
 *
 */
package de.terrestris.shogun2.model.module;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import de.terrestris.shogun2.model.PersistentObject;
import de.terrestris.shogun2.model.map.MapConfig;

/**
 *
 * Class representing .......
 * An GeoExt.component.OverviewMap displays an overview map of an parent map.
 * You can use this component as any other Ext.Component, e.g give it as an item
 * to a panel.
 *
 *
 * @author AndreHenn
 * @author terrestris GmbH & Co. KG
 *
 */
@Table
@Entity
public class OverviewMapModule extends PersistentObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer magnification;
	private MapConfig overviewMapConfig;

	/**
	 *
	 */
	public OverviewMapModule() {
		super();
	}

	/**
	 * @param magnification
	 * @param overviewMapConfig
	 */
	public OverviewMapModule(Integer magnification, MapConfig overviewMapConfig) {
		super();
		this.magnification = magnification;
		this.overviewMapConfig = overviewMapConfig;
	}

	/**
	 * @return the magnification
	 */
	public Integer getMagnification() {
		return magnification;
	}

	/**
	 * @param magnification the magnification to set
	 */
	public void setMagnification(Integer magnification) {
		this.magnification = magnification;
	}

	/**
	 * @return the overviewMapConfig
	 */
	public MapConfig getOverviewMapConfig() {
		return overviewMapConfig;
	}

	/**
	 * @param overviewMapConfig the overviewMapConfig to set
	 */
	public void setOverviewMapConfig(MapConfig overviewMapConfig) {
		this.overviewMapConfig = overviewMapConfig;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 *
	 *      According to
	 *      http://stackoverflow.com/questions/27581/overriding-equals
	 *      -and-hashcode-in-java it is recommended only to use getter-methods
	 *      when using ORM like Hibernate
	 */
	public int hashCode() {
		// two randomly chosen prime numbers
		return new HashCodeBuilder(47, 13).
				appendSuper(super.hashCode()).
				append(getMagnification()).
				append(getOverviewMapConfig()).
				toHashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 *
	 *      According to
	 *      http://stackoverflow.com/questions/27581/overriding-equals
	 *      -and-hashcode-in-java it is recommended only to use getter-methods
	 *      when using ORM like Hibernate
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof OverviewMapModule))
			return false;
		OverviewMapModule other = (OverviewMapModule) obj;

		return new EqualsBuilder().
				append(getMagnification(), other.getMagnification()).
				append(getOverviewMapConfig(), other.getOverviewMapConfig()).
				isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

}