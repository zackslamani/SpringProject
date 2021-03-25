package com.quest.etna.service;

import java.util.List;

import com.quest.etna.model.Address;

public interface IModelService<T> {

		public List<T> getList();
		public T getOneById(Long id);
		public T create (T entity);
		public T update (Long id, T entity);
		public Boolean delete(Long id);
}
