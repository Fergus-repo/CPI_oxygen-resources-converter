package com.oxygenxml.resources.batch.converter.extensions;

/**
 * Extensions of used files in converter. 
 * @author Cosmin Duna
 *
 */
public class FileExtensionType {
	
	/**
	 * Private constructor.
	 */
	 private FileExtensionType() {
	    throw new IllegalStateException("Utility class");
	  }
	
	/**
	 * Extensions of HTML type.
	 */
	protected static final String[] INPUT_HTML_EXTENSIONS = new String[] { "html", "xhtml", "htm", ".htx" };

	/**
	 * Extensions of Markdown type.
	 */
	protected static final String[] INPUT_MD_EXTENSIONS = new String[] { "md", "markdown" };

	/**
	 * Extensions of XML type.
	 */
	protected static final String[] INPUT_XML_EXTENSIONS = new String[] { "xml" };
	/**
	 * Extensions of JSON type.
	 */
	protected static final String[] INPUT_JSON_EXTENSIONS = new String[] { "json" };
	
	/**
	 * XHtml output type
	 */
	public static final String XHTML_OUTPUT_EXTENSION = "xhtml";

	/**
	 * DITA output type
	 */
	public static final String DITA_OUTPUT_EXTENSION = "dita";

	/**
	 * XML output type
	 */
	public static final String XML_OUTPUT_EXTENSION = "xml";
	/**
	 * JSON output type
	 */
	public static final String JSON_OUTPUT_EXTENSION = "json";
}
