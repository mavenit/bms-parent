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
 *         &lt;element ref="{}paOperations"/&gt;
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
    "paOperations"
})
@XmlRootElement(name = "paBusinessApiConfig")
public class PaBusinessApiConfig
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected PaOperations paOperations;

    /**
     * Gets the value of the paOperations property.
     * 
     * @return
     *     possible object is
     *     {@link PaOperations }
     *     
     */
    public PaOperations getPaOperations() {
        return paOperations;
    }

    /**
     * Sets the value of the paOperations property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaOperations }
     *     
     */
    public void setPaOperations(PaOperations value) {
        this.paOperations = value;
    }

}
