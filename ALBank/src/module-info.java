/**
 * 
 */
/**
 * 
 */
// Ajout de "open" pour garantir un accès à Jackson
open module ALBank {
	requires com.fasterxml.jackson.databind;
	requires java.xml;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires com.fasterxml.jackson.dataformat.xml;

}