package com.xcx.service;

import com.xcx.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    
    @Autowired
    private StudentDao studentDao;

    /* @Transactional属性
        readOnly = true把当前事务设置为只读 默认是false
            对一个查询操作来说，如果我们把它设置成只读，就能够明确告诉数据库，这个操作不涉及写操作。这样数据库就能够针对查询操作来进行优化
        timeout设置事务超时时间,单位秒! 默认: -1 永不超时,不限制事务时间!
        rollbackFor = 指定哪些异常才会回滚,默认是 RuntimeException and Error 异常方可回滚，IOException 不会回滚!
        noRollbackFor = 指定哪些异常不会回滚, 默认没有指定,如果指定,应该在rollbackFor的范围内!
        isolation = 设置事务的隔离级别,mysql默认是repeatable read，推荐设置第二个级别
            1. 读未提交（Read Uncommitted）：事务可以读取未被提交的数据，容易产生脏读、不可重复读和幻读等问题。实现简单但不太安全，一般不用。
                    脏读：事务读取到其他事务未提交的数据。
                    不可重复读：事务读取到其他事务已经提交的修改了的数据。
                    幻读：事务读取到其他事务新增的数据。
            2. 读已提交（Read Committed）：事务只能读取已经提交的数据，可以避免脏读问题，但可能引发不可重复读和幻读。
            3. 可重复读（Repeatable Read）：在一个事务中，相同的查询将返回相同的结果集，不管其他事务对数据做了什么修改。可以避免脏读和不可重复读，但仍有幻读的问题。
            4. 串行化（Serializable）：最高的隔离级别，完全禁止了并发，只允许一个事务执行完毕之后才能执行另一个事务。可以避免以上所有问题，但效率较低，不适用于高并发场景。
        propagation 属性设置事务的传播行为，在被调用的子方法中设置传播行为，代表如何处理调用的事务！ 是加入，还是新事务等
            1. Propagation.REQUIRED：如果当前存在事务，则加入当前事务，否则创建一个新事务。
                                     如果父方法有事务，就加入，如果没有就新建自己独立！
            2. Propagation.REQUIRES_NEW：创建一个新事务，并在新事务中执行。如果当前存在事务，则挂起当前事务，即使新事务抛出异常，也不会影响当前事务。
                                         不管父方法是否有事务，我都新建事务，都是独立的！
            3. Propagation.NESTED：如果当前存在事务，则在该事务中嵌套一个新事务，如果没有事务，则与Propagation.REQUIRED一样。
            4. Propagation.SUPPORTS：如果当前存在事务，则加入该事务，否则以非事务方式执行。
            5. Propagation.NOT_SUPPORTED：以非事务方式执行，如果当前存在事务，挂起该事务。
            6. Propagation.MANDATORY：必须在一个已有的事务中执行，否则抛出异常。
            7. Propagation.NEVER：必须在没有事务的情况下执行，否则抛出异常。
     */
    @Transactional // 开启事务  可以加在类上，表示所有方法都有事务
    public void changeInfo(){
        studentDao.updateAgeById(77,1);
        int i = 1/0;   // 模拟异常, 事务回滚
        System.out.println("-----------");
        studentDao.updateNameById("test1",1);
    }
}
