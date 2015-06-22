package at.lumetsnet.caas.dal;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

import at.lumetsnet.caas.model.Entity;

public class DaoUtil {

	public static PreparedStatement generateInsertStatement(Connection con, Entity entity, String tableName){
		
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(entity.getClass());
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
			
			StringBuilder builder = new StringBuilder();
			builder.append(String.format("insert into %s (",tableName));
			Arrays.stream(pds).forEach(x -> {
				if(! (x.getName().equalsIgnoreCase("id") || x.getName().equalsIgnoreCase("class")))  { //skip id, class
					builder.append(x.getName());
					builder.append(",");
				}
			});
			builder.delete(builder.length()-1, builder.length()); //delete last ","
			builder.append(") VALUES (");
			for(int i = 0;i<pds.length-2;i++) {
				builder.append("?,");
			}
			builder.delete(builder.length()-1, builder.length()); //delete last ","
			builder.append(")");
			System.out.println(builder.toString());
			PreparedStatement stmt = con.prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			for(int i = 0 ;i<pds.length;i++) {
				if(! (pds[i].getName().equalsIgnoreCase("id") || pds[i].getName().equalsIgnoreCase("class")))  { //skip id, class
					stmt.setObject(index, pds[i].getReadMethod().invoke(entity));
					index++;
				}
			}
			return stmt;
					
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}
		
	}
	
public static PreparedStatement generateUpdateStatement(Connection con, Entity entity, String tableName){
		
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(entity.getClass());
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
			
			StringBuilder builder = new StringBuilder();
			builder.append(String.format("UPDATE %s SET ",tableName));
			Arrays.stream(pds).forEach(x -> {
				if(! (x.getName().equalsIgnoreCase("id") || x.getName().equalsIgnoreCase("class")))  { //skip id, class
					builder.append(x.getName()+" = ?");
					builder.append(",");
				}
			});
			builder.delete(builder.length()-1, builder.length()); //delete last ","
			builder.append(" WHERE id = ?");
			System.out.println(builder.toString());
			PreparedStatement stmt = con.prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			for(int i = 0 ;i<pds.length;i++) {
				if(! (pds[i].getName().equalsIgnoreCase("id") || pds[i].getName().equalsIgnoreCase("class")))  { //skip id, class
					stmt.setObject(index, pds[i].getReadMethod().invoke(entity));
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
