/**
 *
 */
package de.terrestris.shogun2.model.module;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import de.terrestris.shogun2.model.PersistentObject;
import de.terrestris.shogun2.model.layer.Layer;
import de.terrestris.shogun2.model.map.MapConfig;

/**
 *
 * Class representing a
 * <a href="https://github.com/geoext/geoext3/blob/master/src/component/Map.js">map component</a> and a panel side by side
 *
 * @author Andre Henn
 * @author terrestris GmbH & Co. KG
 *
 */
@Entity
@Table
public class MapModule extends PersistentObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	@OneToOne
	// t.b.d: can mapConfig belong to several maps / overview maps
	private MapConfig mapConfig;

	@OneToOne(cascade = CascadeType.ALL)
	private OverviewMapModule overViewMap;

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "MAP_LAYERS", joinColumns = { @JoinColumn(name = "MAP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "LAYER_ID") })
	@OrderColumn(name = "INDEX")
	private List<Layer> mapLayers = new ArrayList<Layer>();

	/**
	 * default constructor
	 */
	public MapModule() {
		super();
	}

	/**
	 * @param name
	 * @param magnific
	 * @param mapConfig
	 * @param mapLayers
	 */
	public MapModule(String name, MapConfig mapConfig, List<Layer> mapLayers) {
		super();
		this.name = name;
		this.mapConfig = mapConfig;
		this.mapLayers = mapLayers;
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
	 * @return the mapConfig
	 */
	public MapConfig getMapConfig() {
		return mapConfig;
	}

	/**
	 * @param mapConfig the mapConfig to set
	 */
	public void setMapConfig(MapConfig mapConfig) {
		this.mapConfig = mapConfig;
	}

	/**
	 * @return the mapLayers
	 */
	public List<Layer> getMapLayers() {
		return mapLayers;
	}

	/**
	 * @param mapLayers the mapLayers to set
	 */
	public void setMapLayers(List<Layer> mapLayers) {
		this.mapLayers = mapLayers;
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
		return new HashCodeBuilder(47, 11).
				appendSuper(super.hashCode()).
				append(getName()).
				append(getMapLayers()).
				append(getMapConfig()).
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
		if (!(obj instanceof MapModule))
			return false;
		MapModule other = (MapModule) obj;

		return new EqualsBuilder().
				append(getName(), other.getName()).
				append(getMapConfig(), other.getMapConfig()).
				append(getMapLayers(), other.getMapLayers()).
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
