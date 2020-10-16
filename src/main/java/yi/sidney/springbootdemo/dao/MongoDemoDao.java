package yi.sidney.springbootdemo.dao;


import yi.sidney.springbootdemo.entity.MongoDemo;

public interface MongoDemoDao {
    void saveDemo(MongoDemo demoEntity);

    void removeDemo(Long id);

    void updateDemo(MongoDemo demoEntity);

    MongoDemo findDemoById(Long id);
}