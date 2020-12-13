package br.upf.ads.gestordebolsas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.upf.ads.gestordebolsas.entity.Identifiable;
import br.upf.ads.gestordebolsas.util.JPAUtil;

@FacesConverter("entityconverter")
public class EntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			Class<?> clazz = uic.getValueExpression("value").getType(fc.getELContext());
			if (isIdentifiableClass(clazz) && isNumber(value)) {
				return em.find(clazz, Long.valueOf(value));
			} else {
				return value;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConverterException("Não foi possível converter o valor [" + value + "] no componente [" + uic.getId() + "]", ex);
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Identifiable) {
			return ((Identifiable) value).getId().toString();
		} else {
			return value.toString();
		}
	}

	public boolean isIdentifiableClass(Class<?> clazz) {
		for (Class<?> interfaceClazz : clazz.getInterfaces()) {
			if (interfaceClazz.getSimpleName().equals("Identifiable")) {
				return true;
			}
		}
		return false;
	}

	public boolean isNumber(String value) {
		try {
			Long.valueOf(value);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}
	
}