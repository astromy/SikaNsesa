package repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;


@NoRepositoryBean
public interface Base<T, ID extends Serializable>
extends Repository<T, ID> {
<S extends T> S save(S entity);//Saves the given entity.
T findOne(ID primaryKey);      //Returns the entity identified by the given id.
Iterable<T> findAll();         //Returns all entities.
Long count();                  //Returns the number of entities.
void delete(T entity);          //Deletes the given entity.
boolean exists(ID primaryKey);  //Indicates whether an entity with the given id exists.

}