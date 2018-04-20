package com.vin.spgrouptest.data;

import java.util.Arrays;
import java.util.Objects;

public class PsiResponses implements Validatable{

    private RegionMetadata[] region_metadata;
    private PsiItem[] items;
    private ApiInfo api_info;

    public PsiResponses(RegionMetadata[] region_metadata, PsiItem[] items, ApiInfo api_info){
        if(region_metadata == null || items == null){
            throw new IllegalArgumentException("Cannot init PsiResponse with Null metadata or items");
        }
        this.region_metadata = region_metadata;
        this.items = items;
        this.api_info = api_info;
    }

    public RegionMetadata[] getRegionMetadata() {
        return region_metadata;
    }

    public PsiItem[] getItems() {
        return items;
    }

    public ApiInfo getApiInfo() {
        return api_info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsiResponses that = (PsiResponses) o;
        return Arrays.equals(region_metadata, that.region_metadata) &&
                Arrays.equals(items, that.items) &&
                Objects.equals(api_info, that.api_info);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(api_info);
        result = 31 * result + Arrays.hashCode(region_metadata);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }

    @Override
    public boolean isValid() {
        if(region_metadata != null && region_metadata.length > 0
                && items != null && items.length > 0){
            return true;
        }
        return false;
    }
}
