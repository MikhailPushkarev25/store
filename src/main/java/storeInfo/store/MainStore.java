package storeInfo.store;

import storeInfo.model.Post;
import storeInfo.model.User;

public class MainStore {
    public static void main(String[] args) {
        Store store = OnlineDB.instOf();
        store.saveUser(new User(
                0, "Mikhail", "Pushkarev", "@mail.ru", "123"));
        for (User user : store.findUserAll()) {
            System.out.println(user.getId() + " " + user.getName());
        }

        store.savePost(new Post(0, "Apple"));
        for (Post post : store.findPostAll()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        System.out.println(store.findByIdPost(0));
    }
}
