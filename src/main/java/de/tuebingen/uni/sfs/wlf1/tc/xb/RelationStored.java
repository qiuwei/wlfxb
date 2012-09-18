/**
 * 
 */
package de.tuebingen.uni.sfs.wlf1.tc.xb;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import de.tuebingen.uni.sfs.wlf1.tc.api.Relation;
import de.tuebingen.uni.sfs.wlf1.utils.CommonAttributes;

/**
 * @author Yana Panchenko
 *
 */
@SuppressWarnings("deprecation")
@XmlAccessorType(XmlAccessType.NONE)
@Deprecated
public class RelationStored implements Relation {

	public static final String XML_NAME = "relation";
	
	@XmlAttribute(name=CommonAttributes.ID)
	String id;
	@XmlAttribute(name=CommonAttributes.FUNCTION)
	String function;
	@XmlAttribute(name="refIDs", required = true)
	String[] tokRefs;
	
	@Override
	public String getFunction() {
		return function;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (id != null) {
			sb.append(id);
			sb.append(" -> ");
		}
		if (function != null) {
			sb.append(function);
			sb.append(" ");
		}
		sb.append(Arrays.toString(tokRefs));
		return sb.toString();
	}

}