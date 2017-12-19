//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.11 at 05:40:03 PM SGT 
//


package com.bms.eai.module.api.config.xml;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}propertyDetails"/&gt;
 *         &lt;element ref="{}humanResourceDetails"/&gt;
 *         &lt;element ref="{}commonModule"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "propertyDetails",
    "humanResourceDetails",
    "commonModule"
})
@XmlRootElement(name = "bmsModules")
public class BmsModules
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected PropertyDetails propertyDetails;
    @XmlElement(required = true)
    protected HumanResourceDetails humanResourceDetails;
    @XmlElement(required = true)
    protected CommonModule commonModule;

    /**
     * Gets the value of the propertyDetails property.
     * 
     * @return
     *     possible object is
     *     {@link PropertyDetails }
     *     
     */
    public PropertyDetails getPropertyDetails() {
        return propertyDetails;
    }

    /**
     * Sets the value of the propertyDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyDetails }
     *     
     */
    public void setPropertyDetails(PropertyDetails value) {
        this.propertyDetails = value;
    }

    /**
     * Gets the value of the humanResourceDetails property.
     * 
     * @return
     *     possible object is
     *     {@link HumanResourceDetails }
     *     
     */
    public HumanResourceDetails getHumanResourceDetails() {
        return humanResourceDetails;
    }

    /**
     * Sets the value of the humanResourceDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link HumanResourceDetails }
     *     
     */
    public void setHumanResourceDetails(HumanResourceDetails value) {
        this.humanResourceDetails = value;
    }

    /**
     * Gets the value of the commonModule property.
     * 
     * @return
     *     possible object is
     *     {@link CommonModule }
     *     
     */
    public CommonModule getCommonModule() {
        return commonModule;
    }

    /**
     * Sets the value of the commonModule property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonModule }
     *     
     */
    public void setCommonModule(CommonModule value) {
        this.commonModule = value;
    }

}