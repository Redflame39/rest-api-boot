package com.epam.esm.model.dto;

import com.epam.esm.converter.TagDtoToTagConverter;
import com.epam.esm.model.entity.Tag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TagDto {

    Long id;

    String name;

    public static List<Tag> toTagList(List<TagDto> tagDtos) {
        TagDtoToTagConverter converter = new TagDtoToTagConverter();
        List<Tag> tags = new ArrayList<>();
        for (TagDto tagDto : tagDtos) {
            Tag tag = converter.convert(tagDto);
            tags.add(tag);
        }
        return tags;
    }

}
