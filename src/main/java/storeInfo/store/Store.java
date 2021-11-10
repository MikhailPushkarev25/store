package storeInfo.store;

import storeInfo.model.Post;
import storeInfo.model.User;

import java.util.Collection;

public interface Store {

    Collection<User> findUserAll();

    Collection<Post> findPostAll();

    void saveUser(User user);

    void savePost(Post post);

    User findById(int id);

    Post findByIdPost(int id);

    User findByEmail(String email);

    void removePost(int id);
}
