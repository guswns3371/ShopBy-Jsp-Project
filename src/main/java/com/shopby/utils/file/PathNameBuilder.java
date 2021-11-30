package com.shopby.utils.file;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PathNameBuilder {
    private Long itemId;

    @Builder
    public PathNameBuilder(Long itemId) {
        this.itemId = itemId;
    }

    public String getThumbnailPath() {
        return itemId + "_thumbnail_image";
    }

    public String getInfoImagePath() {
        return itemId + "_info_image";
    }
}
