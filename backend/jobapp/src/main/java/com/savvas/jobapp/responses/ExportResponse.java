package com.savvas.jobapp.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "Data")
public class ExportResponse {
    private List<UserResponse> users;
    private List<ArticleResponse> articles;
    private List<AdResponse> ads;
    private List<ExperienceResponse> experience;
    private List<CommentResponse> comments;
}
