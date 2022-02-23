package com.epam.esm.model.dto;

import com.epam.esm.converter.TagDtoToTagConverter;
import com.epam.esm.model.entity.Tag;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TagDto {

    Long id;

    String name;

    public static Set<Tag> toTagSet(Set<TagDto> tagDtos) {
        TagDtoToTagConverter converter = new TagDtoToTagConverter();
        Set<Tag> tags = new HashSet<>();
        for (TagDto tagDto : tagDtos) {
            Tag tag = converter.convert(tagDto);
            tags.add(tag);
        }
        return tags;
    }

}
