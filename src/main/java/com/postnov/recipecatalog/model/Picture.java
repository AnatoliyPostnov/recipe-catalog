package com.postnov.recipecatalog.model;

import lombok.Data;
import org.springframework.content.commons.annotations.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @ContentId
    @Column(name = "name")
    private String name;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "extension", nullable = false)
    private String extension;

    @ContentLength
    @Column(name = "content_length", nullable = false)
    private Long contentLength;

    @MimeType
    @Column(name = "mime_type", nullable = false)
    private String mimeType;
}
