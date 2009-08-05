<#-- // Property accessors -->
<#foreach property in pojo.getAllPropertiesIterator()>
<#if pojo.getMetaAttribAsBool(property, "gen-property", true)>
 	<#if pojo.hasFieldJavaDoc(property)>    
    /**       
     * ${pojo.getFieldJavaDoc(property, 4)}
     */
	</#if>
    <#include "GetPropertyAnnotation.ftl"/>
    ${pojo.getPropertyGetModifiers(property)} ${pojo.getJavaTypeName(property, jdk5)} ${pojo.getGetterSignature(property)}() {
        return this.${property.name};
    }
    
    ${pojo.getPropertySetModifiers(property)} void set${pojo.getPropertyName(property)}(${pojo.getJavaTypeName(property, jdk5)} ${property.name}) {
        this.${property.name} = ${property.name};
    }
</#if>
</#foreach>

<#-- // Id and Label -->
	/**	
	* Gets FormIdField name for options
	*/
	@Transient
	public ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)} getFormIdField() {
		return ${pojo.getGetterSignature(pojo.identifierProperty)}();
	}
	
	/**	
	* Gets FormIdFieldClass
	*/
	@Transient
	public Class getFormIdFieldClass() {
		return ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}.class;
	}
	
	/**
	 * Gets PK for a concrete String
	 */
	@Transient
	public Serializable getPKForString(String stringPK){
		return new ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}(stringPK);
	}
	
	/**
	 * Gets String representation of PK field
	 */
	@Transient
	public String getStringForPK(){
		return ${pojo.getGetterSignature(pojo.identifierProperty)}().toString();
	}
	
	/**	
	* Gets FormLabelField name for options 
	*/
	@Transient
	public Object getFormLabelField() {
		return ${pojo.getGetterSignature(pojo.identifierProperty)}();
	}
