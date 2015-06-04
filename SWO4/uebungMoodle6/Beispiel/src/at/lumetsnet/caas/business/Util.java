package at.lumetsnet.caas.business;

import java.util.ArrayList;
import java.util.OptionalLong;

import at.lumetsnet.caas.model.Entity;

public class Util {

	public static <T extends Entity> void saveOrUpdate(T model, ArrayList<T> list) {
		// new
		if (model.getId() == -1) {
			OptionalLong max = list.stream().mapToLong(x -> x.getId()).max();
			if (max.isPresent()) {
				model.setId(max.getAsLong() + 1);
			}
			list.add(model);
		} else {
			list.removeIf(x -> x.getId() == model.getId());
			list.add(model);
		}

	}

}
