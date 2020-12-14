package br.com.personalog.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Id;

import br.com.personalog.dao.query.EntrylogDaoQuery;
import br.com.personalog.model.Entrylog;
import br.com.personalog.model.Mood;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class EntrylogDaoImpl implements EntrylogDaoQuery {

	@NonNull
	private EntityManager entityManager;

	@Override
	public Entrylog saveEntry(Entrylog entrylog) {
		Integer moodId = Optional.ofNullable(entrylog.getMood()).orElse(new Mood()).getId();
		if(Objects.nonNull(entrylog.getId())){
			Entrylog updated = findEntry(entrylog.getId());
			updated.setDateTime(entrylog.getDateTime());
			updated.setDescription(entrylog.getDescription());
			updated.setDateTime(entrylog.getDateTime());
			entrylog = updated;
		}
		entrylog.setMood(Objects.nonNull(moodId)? entityManager.find(Mood.class, moodId) : null);
		entityManager.persist(entrylog);
		return entrylog;
	}

	@Override
	public Entrylog deleteEntry(Integer id) {
		Entrylog removedEntry = findEntry(id);
		entityManager.remove(removedEntry);
		return removedEntry;
	}

	@Override
	public Entrylog findEntry (Integer id){
		return entityManager.find(Entrylog.class, id);
	}

	private void reatach(Object entity, Object detached){
		try {
			int id = 0;
			for (Field f: detached.getClass().getDeclaredFields()) {
				if(Objects.nonNull(f.getAnnotation(Id.class))){
					id = (Integer) detached.getClass().getMethod("get"+ StringUtils.capitalize(f.getName())).invoke(detached);
					break;
				}
			}
			Object reatached = entityManager.find(detached.getClass(), id);
			Field f = entity.getClass().getDeclaredField(StringUtils.lowerCase(detached.getClass().getSimpleName()));
			entity.getClass().getMethod("set"+ StringUtils.capitalize(f.getName()), detached.getClass()).invoke(entity, reatached);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
