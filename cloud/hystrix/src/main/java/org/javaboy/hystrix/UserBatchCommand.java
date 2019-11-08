package org.javaboy.hystrix;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import org.javaboy.commons.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-11-02 9:21
 */
public class UserBatchCommand extends HystrixCollapser<List<User>, User, Integer> {
    private Integer id;
    private UserService userService;

    public UserBatchCommand(Integer id, UserService userService) {
        super(HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("")).andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(200)));
        this.id = id;
        this.userService = userService;
    }

    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
     * 请求合并  具体的合并操作在这里进行
     *
     * @param collection
     * @return
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Integer>> collection) {
        List<Integer> ids = new ArrayList<>(collection.size());
        for (CollapsedRequest<User, Integer> request : collection) {
            ids.add(request.getArgument());
        }
        UserCommand userCommand = new UserCommand(userService, ids);
        return userCommand;
    }

    /**
     * 映射请求响应到不同的请求上
     *
     * @param users
     * @param collection
     */
    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Integer>> collection) {
        int count = 0;
        for (CollapsedRequest<User, Integer> request : collection) {
            request.setResponse(users.get(count++));
        }
    }
}
