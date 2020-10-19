package yi.sidney.springbootdemo.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import yi.sidney.springbootdemo.dao.MongoDemoDao;
import yi.sidney.springbootdemo.entity.MongoDemo;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MongoDemoService implements MongoDemoDao {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveDemo(MongoDemo mongoDemo) {
        mongoTemplate.save(mongoDemo);
    }

    @Override
    public void removeDemo(Long id) {
        mongoTemplate.remove(id);
    }

    @Override
    public void updateDemo(MongoDemo mongoDemo) {
        Query query = new Query(Criteria.where("id").is(mongoDemo.getId()));

        Update update = new Update();
        update.set("name", mongoDemo.getName());
        update.set("mobile", mongoDemo.getMobile());

        mongoTemplate.updateFirst(query, update, MongoDemo.class);
    }

    @Override
    public MongoDemo findDemoById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, MongoDemo.class);
    }

    public List<MongoDemo> findAll() {
        return mongoTemplate.findAll(MongoDemo.class);
    }
}
