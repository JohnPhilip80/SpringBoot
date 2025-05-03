package com.cca.john.student.models;

import java.util.List;

public interface CRUDoperations<T1,T2> {
	public void createItem(T1 item);
	public List<T1> readAllItems();
	public void updateItem(T1 item);
	public void deleteItem(T2 id);
	public T1 getItemById(T2 id);
}
