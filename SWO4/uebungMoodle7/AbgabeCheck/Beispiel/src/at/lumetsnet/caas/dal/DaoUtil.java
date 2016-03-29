package at.lumetsnet.caas.dal;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;

import at.lumetsnet.caas.model.Entity;

public class DaoUtil {

	/***
	 * Generates a prepared statement for inserting an entity Sets all the
	 * values
	 * 
	 * @param con
	 * @param entity
	 * @param tableName
	 * @param filter
	 * @return
	 */
	public static PreparedStatement generateInsertStatement(Connection con,
			Entity entity, String tableName, Collection<String> filter) {

		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(entity.getClass());
			PropertyDescriptor[] pds = info.getPropertyDescriptors();

			StringBuilder builder = new StringBuilder();
			// build the insert string
			builder.append(String.format("insert into `%s` (", tableName));
			Arrays.stream(pds).forEach(
					x -> {
						if (!filter.stream().anyMatch(
								filterItem -> filterItem.equalsIgnoreCase(x
										.getName()))) {
							builder.append(x.getName());
							builder.append(",");
						}
					});
			builder.delete(builder.length() - 1, builder.length()); // delete
																	// last ","
			builder.append(") VALUES (");
			for (int i = 0; i < pds.length - filter.size(); i++) {
				builder.append("?,");
			}
			builder.delete(builder.length() - 1, builder.length()); // delete
																	// last ","
			builder.append(")");

			// create the statement
			PreparedStatement stmt = con.prepareStatement(builder.toString(),
					Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			for (int i = 0; i < pds.length; i++) {
				final String name = pds[i].getName();
				// filter properties which should not be persisted
				// lazy loading
				if (!filter.stream().anyMatch(
						filterItem -> filterItem.equalsIgnoreCase(name))) {
					Object data = pds[i].getReadMethod().invoke(entity);
					// Handle LocalDate
					if (data instanceof LocalDate) {
						LocalDate date = (LocalDate) data;
						stmt.setObject(
								index,
								Date.from(date.atStartOfDay()
										.atZone(ZoneId.systemDefault())
										.toInstant()));
					} else if (data instanceof LocalDateTime) {
						// handle loacalDateTime
						LocalDateTime date = (LocalDateTime) data;
						stmt.setObject(index, Date.from(date.atZone(
								ZoneId.systemDefault()).toInstant()));
					} else
						stmt.setObject(index, data);
					index++;
				}
			}
			return stmt;

		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}

	}

	/***
	 * Creates an update statement for an entity
	 * 
	 * @param con
	 * @param entity
	 * @param tableName
	 * @param filter
	 * @return
	 */
	public static PreparedStatement generateUpdateStatement(Connection con,
			Entity entity, String tableName, Collection<String> filter) {

		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(entity.getClass());
			PropertyDescriptor[] pds = info.getPropertyDescriptors();

			StringBuilder builder = new StringBuilder();
			// build statement
			builder.append(String.format("UPDATE `%s` SET ", tableName));
			Arrays.stream(pds).forEach(
					x -> {
						if (!filter.stream().anyMatch(
								filterItem -> filterItem.equalsIgnoreCase(x
										.getName()))) {
							builder.append(x.getName() + " = ?");
							builder.append(",");
						}
					});
			builder.delete(builder.length() - 1, builder.length()); // delete
																	// last ","
			// append id
			builder.append(" WHERE id = ?");

			// create statement
			PreparedStatement stmt = con.prepareStatement(builder.toString(),
					Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			for (int i = 0; i < pds.length; i++) {
				final String name = pds[i].getName();
				if (!filter.stream().anyMatch(
						filterItem -> filterItem.equalsIgnoreCase(name))) {
					Object data = pds[i].getReadMethod().invoke(entity);
					// Handle LocalDate
					if (data instanceof LocalDate) {
						LocalDate date = (LocalDate) data;
						stmt.setObject(
								index,
								Date.from(date.atStartOfDay()
										.atZone(ZoneId.systemDefault())
										.toInstant()));
					} else if (data instanceof LocalDateTime) {
						// Handle LocalDateTime
						LocalDateTime date = (LocalDateTime) data;
						stmt.setObject(index, Date.from(date.atZone(
								ZoneId.systemDefault()).toInstant()));
					} else
						stmt.setObject(index, data);
					index++;
				}
			}
			stmt.setLong(index, entity.getId());
			return stmt;

		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}

	}

}
