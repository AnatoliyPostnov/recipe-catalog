package com.postnov.recipecatalog.store;

import com.postnov.recipecatalog.model.Picture;
import org.springframework.content.commons.renditions.Renderable;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.commons.search.Searchable;
import org.springframework.content.rest.StoreRestResource;

@StoreRestResource(path = "content/pictures")
public interface PictureContentStore extends ContentStore<Picture, String>, Renderable<Picture>, Searchable<String> {
}