package org.blogging.repository;

import org.blogging.domain.Blog;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Blog entity.
 */
@SuppressWarnings("unused")
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query("select blog from Blog blog where blog.createdUser.login = ?#{principal.username}")
    List<Blog> findByCreatedUserIsCurrentUser();

    @Query("select distinct blog from Blog blog left join fetch blog.users")
    List<Blog> findAllWithEagerRelationships();

    @Query("select blog from Blog blog left join fetch blog.users where blog.id =:id")
    Blog findOneWithEagerRelationships(@Param("id") Long id);

    @Query(value = "select * from Blog blog left join blog_users on blog.id = blog_users.blogs_id left join entry on blog.id = entry.blog_id where blog.id =:id", nativeQuery = true)
    Blog findOneWithRelationships(@Param("id") Long id);


    List<Blog> findAllByOrderByIdAsc();
}
