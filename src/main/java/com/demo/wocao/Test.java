package com.demo.wocao;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/10/28 2:29 PM
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        List<HzsOrganization> list = new ArrayList<>();
        HzsOrganization org = new HzsOrganization();
        org.setParentId(1L);
        list.add(org);

        org = new HzsOrganization();
        org.setParentId(2L);
        list.add(org);

        org = new HzsOrganization();
        org.setParentId(3L);
        list.add(org);

        List<Integer> cityIdList = new ArrayList<>();
        cityIdList.add(11111);
        cityIdList.add(22222);

        List<HzsOrganization> res = list.stream().filter(org1 -> cityIdList.contains(org1.getParentId().intValue())).collect(Collectors.toList());
        System.out.println(res.size());
        System.out.println(cityIdList.size());
        Long i = 11111L;
        System.out.println(cityIdList.contains(i.intValue()));
    }
}

@Setter
@Getter
class CreateDto {
    private Integer id;
}

@Setter
@Getter
class HzsOrganization {
    private Long parentId;
}
