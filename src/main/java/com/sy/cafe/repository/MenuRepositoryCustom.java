package com.sy.cafe.repository;

import com.sy.cafe.dto.response.PopularMenuDto;

import java.util.List;

public interface MenuRepositoryCustom {
    List<PopularMenuDto> popularMenus();

}
