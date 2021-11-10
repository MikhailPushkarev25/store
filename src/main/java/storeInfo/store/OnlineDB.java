package storeInfo.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storeInfo.model.Post;
import storeInfo.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class OnlineDB implements Store {

    private final static BasicDataSource pool = new BasicDataSource();

    private final static Logger LOG =  LoggerFactory.getLogger(OnlineDB.class.getName());

    public OnlineDB() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(
                        OnlineDB.class.getClassLoader().getResourceAsStream("db.properties")))) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch(Exception e) {
            throw new IllegalStateException(e);
        }

        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    public final static class Lazy {
        private final static Store INST = new OnlineDB();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<User> findUserAll() {
        List<User> users = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM users")) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    users.add(new User(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("username"),
                            it.getString("email"),
                            it.getString("password")));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
        return users;
    }

    @Override
    public Collection<Post> findPostAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM post")) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(
                            it.getInt("id"),
                            it.getString("name")));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
        return posts;
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == 0) {
            create(user);
        } else {
            update(user);
        }
    }

    @Override
    public void savePost(Post post) {
        if (post.getId() == 0) {
            createPost(post);
        } else {
            updatePost(post);
        }
    }

    private Post createPost(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO post(name) VALUES(?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getName());
            try (ResultSet it = ps.getGeneratedKeys()) {
                if (it.next()) {
                    post.setId(it.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
        return post;
    }

    private void updatePost(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("UPDATE post SET name = ? WHERE id = ?")) {
            ps.setInt(1, post.getId());
            ps.setString(2, post.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
    }

    private User create(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO users(name, username, email, password) VALUES (?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            try (ResultSet it = ps.getGeneratedKeys()) {
                if (it.next()) {
                    user.setId(it.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
        return user;
    }

    private void update(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "UPDATE users SET name = ?, username = ?, email = ?, password = ? WHERE id = ?")) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    user = new User(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("username"),
                            it.getString("email"),
                            it.getString("password")
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
        return user;
    }

    @Override
    public Post findByIdPost(int id) {
        Post post = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM post WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    post = new Post(
                            it.getInt("id"),
                            it.getString("name"));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
        return post;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM users WHERE email = ?")) {
            ps.setString(1, email);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    user = new User(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("username"),
                            it.getString("email"),
                            it.getString("password")
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
        return user;
    }

    @Override
    public void removePost(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM post WHERE post.id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("Exception info, ", e);
        }
    }
}
