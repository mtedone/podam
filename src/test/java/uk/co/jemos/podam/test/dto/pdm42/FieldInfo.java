package uk.co.jemos.podam.test.dto.pdm42;

public class FieldInfo {

	private String fieldName;

	private String namespace;

	private String namespacePrefix;

	public FieldInfo() {
	}

	public FieldInfo(String fieldName, String namespace) {
		this.fieldName = fieldName;
		this.namespace = namespace;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getNamespacePrefix() {
		return namespacePrefix;
	}

	public void setNamespacePrefix(String namespacePrefix) {
		this.namespacePrefix = namespacePrefix;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	
	    StringBuilder retValue = new StringBuilder();
	    
	    retValue.append("FieldInfo ( ")
	        .append(super.toString()).append(TAB)
	        .append("fieldName = ").append(this.fieldName).append(TAB)
	        .append("namespace = ").append(this.namespace).append(TAB)
	        .append("namespacePrefix = ").append(this.namespacePrefix).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}
	
	
}
