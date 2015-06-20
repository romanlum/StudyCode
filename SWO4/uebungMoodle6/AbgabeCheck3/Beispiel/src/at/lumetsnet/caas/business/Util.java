package at.lumetsnet.caas.business;

import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalLong;

import at.lumetsnet.caas.model.Entity;

/***
 * Utility class used for mock services
 * @author romanlum
 *
 */
public class Util {

	/***
	 * Adds or replaces an object in the given list
	 * @param model
	 * @param list
	 */
	public static <T extends Entity> void saveOrUpdate(T model,
			ArrayList<T> list) {

		if (model.getId() == -1) {
			// new item
			long max = list.stream().mapToLong(x -> x.getId()).max().orElse(-1);
			model.setId(max + 1);
			list.add(model);
		} else {
			// replace old one
			Optional<T> old = list.stream()
					.filter(x -> x.getId() == model.getId()).findAny();
			list.set(list.indexOf(old.get()), model);

		}

	}

}
