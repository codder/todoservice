package dev.codder.services;

import dev.codder.models.TODOModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Daniil on 2/25/2016.
 */

@Service("todoService")
@Transactional
public class TODOStorageServiceImpl implements TODOStorageService {

    private static final AtomicLong counter = new AtomicLong();

    private Map<Long, TODOModel> storage;

    public TODOStorageServiceImpl(){
        this.storage = new HashMap<Long, TODOModel>();
        save(new TODOModel(true, "1"));
        save(new TODOModel(true, "2"));
        save(new TODOModel(true, "3"));
    }

    public void delete(Long id) {
        if (storage.containsKey(id)){
            storage.remove(id);
        }
    }

    public int update(TODOModel todo) {
        if (todo.getId() == null || !storage.containsKey(todo.getId())){
            return 0;
        }
        storage.put(todo.getId(), todo);
        return 1;
    }

    public TODOModel save(TODOModel todo) {
        todo.setId(counter.incrementAndGet());
        storage.put(todo.getId(), todo);
        return todo;
    }

    public List<TODOModel> getAll() {
        List<TODOModel> all = new ArrayList<TODOModel>();
        for(Map.Entry<Long, TODOModel> entry: storage.entrySet()){
            all.add(entry.getValue());
        }
        return all;
    }

    public TODOModel getById(Long id) {
        if (storage.containsKey(id)){
            return storage.get(id);
        }
        return null;
    }

    public void deleteCompleted() {
        List<Long> removeKeys = new ArrayList<Long>();
        for(Map.Entry<Long, TODOModel> entry: storage.entrySet()){
            if (entry.getValue().isCompleted()){
                removeKeys.add(entry.getKey());
            }
        }

        for(Long removeId: removeKeys){
            this.storage.remove(removeId);
        }
    }
}
