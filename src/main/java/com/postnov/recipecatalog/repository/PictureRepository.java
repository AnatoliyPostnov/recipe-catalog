package com.postnov.recipecatalog.repository;

import com.postnov.recipecatalog.model.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends CrudRepository<Picture, String> {
}
