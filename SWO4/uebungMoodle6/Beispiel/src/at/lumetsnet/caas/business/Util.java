package at.lumetsnet.caas.business;

import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalLong;

import at.lumetsnet.caas.model.Entity;

public class Util {

	public static <T extends Entity> void saveOrUpdate(T model,
			ArrayList<T> list) {

		if (model.getId() == -1) {
			// new item
			OptionalLong max = list.stream().mapToLong(x -> x.getId()).max();
			if (max.isPresent()) {
				model.setId(max.getAsLong() + 1);
			}
			list.add(model);
		} else {
			// replace old one
			Optional<T> old = list.stream()
					.filter(x -> x.getId() == model.getId()).findAny();
			list.set(list.indexOf(old.get()), model);

		}

	}

}
