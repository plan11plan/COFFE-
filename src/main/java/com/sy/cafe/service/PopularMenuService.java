package com.sy.cafe.service;

import com.sy.cafe.dto.response.PopularMenuDto;
import com.sy.cafe.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PopularMenuService {
    private final MenuRepository menuRepository;

    // 인기 메뉴 캐시 추가
    @CachePut(value = "menu", cacheManager = "cacheManager")
    public List<PopularMenuDto> setPopularMenuCache() {
        log.info("캐시 추가");
        return menuRepository.popularMenus();
    }


}
