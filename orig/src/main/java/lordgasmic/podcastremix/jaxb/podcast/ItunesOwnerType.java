//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.27 at 07:08:04 AM EDT 
//


package lordgasmic.podcastremix.jaxb.podcast;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for itunesOwnerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="itunesOwnerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itunesName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itunesEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 *  
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itunesOwnerType", propOrder = {
    "itunesName",
    "itunesEmail"
})
public class ItunesOwnerType {

    @XmlElement(required = true)
    protected String itunesName;
    @XmlElement(required = true)
    protected String itunesEmail;

    /**
     * Gets the value of the itunesName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItunesName() {
        return itunesName;
    }

    /**
     * Sets the value of the itunesName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItunesName(String value) {
        this.itunesName = value;
    }

    /**
     * Gets the value of the itunesEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItunesEmail() {
        return itunesEmail;
    }

    /**
     * Sets the value of the itunesEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItunesEmail(String value) {
        this.itunesEmail = value;
    }

}
