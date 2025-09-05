package br.com.jeff.listapro.dao;

import br.com.jeff.listapro.model.Task;

import java.util.List;
import java.util.Optional;


public interface TaskDAO {

	Task save(Task t);
	boolean delete(int id);
	Optional<Task> update(Task t);
	List<Task> listAll();
	
}
