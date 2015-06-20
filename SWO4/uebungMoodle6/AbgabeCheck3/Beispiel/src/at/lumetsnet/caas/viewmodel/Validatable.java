package at.lumetsnet.caas.viewmodel;

/***
 * Interface used for validating entity view models
 * @author romanlum
 *
 */
public interface Validatable {

	/***
	 * validates the entity
	 * @return true on successfull validation otherwise false
	 */
	boolean validate();
}
