package org.blogging.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import groovy.lang.Lazy;
import org.blogging.domain.enumeration.BlogType;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

/**
 * A Blog.
 */
@Entity
@Table(name = "blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 10, max = 200)
    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BlogType type;

    @OneToMany(mappedBy = "blog", fetch = FetchType.EAGER)
    @Transient
    private Set<Entry> entries = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "blog_users",
               joinColumns = @JoinColumn(name="blogs_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="users_id", referencedColumnName="ID"))
    private Set<User> users = new HashSet<>();

    @ManyToOne
    private User createdUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Blog name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Blog description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BlogType getType() {
        return type;
    }

    public Blog type(BlogType type) {
        this.type = type;
        return this;
    }

    public void setType(BlogType type) {
        this.type = type;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public Blog entries(Set<Entry> entries) {
        this.entries = entries;
        return this;
    }

    public Blog addEntry(Entry entry) {
        entries.add(entry);
        entry.setBlog(this);
        return this;
    }

    public Blog removeEntry(Entry entry) {
        entries.remove(entry);
        entry.setBlog(null);
        return this;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Blog users(Set<User> users) {
        this.users = users;
        return this;
    }

    public Blog addUser(User user) {
        users.add(user);
        return this;
    }

    public Blog removeUser(User user) {
        users.remove(user);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public Blog createdUser(User user) {
        this.createdUser = user;
        return this;
    }

    public void setCreatedUser(User user) {
        this.createdUser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Blog blog = (Blog) o;
        if(blog.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, blog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Blog{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", type='" + type + "'" +
            '}';
    }
}
