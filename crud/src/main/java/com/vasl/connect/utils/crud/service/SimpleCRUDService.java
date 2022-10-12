package com.vasl.connect.utils.crud.service;

import com.vasl.connect.utils.crud.dal.Entity;
import com.vasl.connect.utils.crud.dal.repository.EntityRepository;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class SimpleCRUDService<E> implements CRUDService<E> {

    private PagingAndSortingRepository repository;
    private CRUDServiceMapper<E> crudServiceMapper;
    private Class<E> persistentClass;
    private EntityRepository<E> entityRepository;
//    private CustomQueryRepository customQueryRepository;

    public SimpleCRUDService(PagingAndSortingRepository repository, CRUDServiceMapper<E> crudServiceMapper) {
        this.persistentClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.repository = repository;
        this.crudServiceMapper = crudServiceMapper;
        if (repository instanceof EntityRepository){
            entityRepository = (EntityRepository<E>) repository;
        }
    }

    public E create(E entity){
        return (E) repository.save(entity);
    }

    @SneakyThrows
    public E update(E newOne){
        E existing = getById(((Entity) newOne).getId());
        Entity existingBaseClone = (Entity)((Entity) existing).clone();
        crudServiceMapper.updateEntity(existing, newOne);
        crudServiceMapper.updateBaseEntity((Entity) existing, existingBaseClone);
        return (E) repository.save(existing);
    }

    public E getById(String id) {
        Optional<E> optional = repository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new NotFoundException("entity.not.found");
    }

    @Override
    public Optional<E> getOptionalById(String id) {
        return repository.findById(id);
    }

    public List<E> getSortedList(Sort sort){
        return (List<E>) repository.findAll(sort);
    }

    public List<E> getList(){
        return (List<E>) repository.findAll();
    }

    public Page<E> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public void deleteById(String id){
        E entity = getById(id);
        repository.delete(entity);
    }

    public Page<E> getRoots(Pageable pageable) {
        checkEntityRepositoryWasExtended();
        return entityRepository.findAllRoots(pageable);
    }

    public Page<E> getChildren(String parentId, Pageable pageable) {
        checkEntityRepositoryWasExtended();
        return entityRepository.findAllByParentId(parentId, pageable);
    }

    public E getParent(String childId) {
        checkEntityRepositoryWasExtended();
        Optional<E> childOptional = repository.findById(childId);
        if (childOptional.isEmpty()) throw new NotFoundException("child.not.found");
        E child = childOptional.get();
        Entity childEntity = (Entity) child;
        if (Objects.isNull(childEntity.getParentId()) || childEntity.getParentId().isBlank())
            throw new PreconditionRequiredException("child.does.not.have.parent");
        Optional<E> parentOptional = repository.findById(childEntity.getParentId());
        if (parentOptional.isPresent()) return parentOptional.get();
        throw new NotFoundException("parent.not.found");
    }

    private void checkEntityRepositoryWasExtended(){
        if(Objects.isNull(entityRepository)) throw new NullPointerException("entity repository wasn't extended.");
    }


}
