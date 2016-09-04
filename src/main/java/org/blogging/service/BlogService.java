package org.blogging.service;

import org.blogging.domain.Blog;
import org.blogging.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Blog.
 */
@Service
@Transactional
public class BlogService {

    private final Logger log = LoggerFactory.getLogger(BlogService.class);

    @Inject
    private BlogRepository blogRepository;

    /**
     * Save a blog.
     *
     * @param blog the entity to save
     * @return the persisted entity
     */
    public Blog save(Blog blog) {
        log.debug("Request to save Blog : {}", blog);
        Blog result = blogRepository.save(blog);
        return result;
    }

    /**
     *  Get all the blogs.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Blog> findAll() {
        log.debug("Request to get all Blogs");
        List<Blog> result = blogRepository.findAllByOrderByIdAsc();

        return result;
    }

    /**
     *  Get one blog by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Blog findOne(Long id) {
        log.debug("Request to get Blog : {}", id);
        Blog blog = blogRepository.findOneWithEagerRelationships(id);
        return blog;
    }

    /**
     *  Delete the  blog by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Blog : {}", id);
        blogRepository.delete(id);
    }
}
