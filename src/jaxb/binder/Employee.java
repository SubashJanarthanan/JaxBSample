package jaxb.binder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class Employee.
 * 
 * @author Subash Janarthanan
 */
@XmlRootElement
@XmlType(propOrder = {
        "name",
        "id",
        "businessUnit"
})
public class Employee {

	/** The name. */
	private String name;
	
	/** The id. */
	private String id;
	
	/** The business unit. */
	private String businessUnit;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the business unit.
	 *
	 * @return the business unit
	 */
	public String getBusinessUnit() {
		return businessUnit;
	}

	/**
	 * Sets the business unit.
	 *
	 * @param businessUnit the new business unit
	 */
	@XmlElement
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	
}
