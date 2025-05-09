package com.codesupreme.kenanustaapi.dao.tag;

import com.codesupreme.kenanustaapi.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}