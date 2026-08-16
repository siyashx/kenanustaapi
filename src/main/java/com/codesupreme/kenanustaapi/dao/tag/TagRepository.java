package com.codesupreme.kenanustaapi.dao.tag;

import com.codesupreme.kenanustaapi.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findBySlugIgnoreCase(String slug);
    boolean existsBySlugIgnoreCase(String slug);
    List<Tag> findAllByOrderByIdAsc();
}
