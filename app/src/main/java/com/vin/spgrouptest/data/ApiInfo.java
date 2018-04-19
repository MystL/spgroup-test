package com.vin.spgrouptest.data;

import java.util.Objects;

public class ApiInfo {

    private String status;

    public ApiInfo(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiInfo apiInfo = (ApiInfo) o;
        return Objects.equals(status, apiInfo.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(status);
    }
}
