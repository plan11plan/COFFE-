package com.sy.cafe.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sy.cafe.domain.QMenu;
import com.sy.cafe.domain.QOrderItem;
import com.sy.cafe.dto.response.PopularMenuDto;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MenuRepositoryImpl implements MenuRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    QOrderItem orderItem = QOrderItem.orderItem;
    QMenu menu = QMenu.menu;

    @Override
    public List<PopularMenuDto> popularMenus() {
        LocalDate weekBefore = LocalDate.now().minusDays(7);
        LocalDate yesterday = LocalDate.now();

        return queryFactory.select(Projections.constructor(PopularMenuDto.class,
                        orderItem.menuId, menu.name, orderItem.number.sum()))
                .from(orderItem)
                .innerJoin(menu).on(orderItem.menuId.eq(menu.id))
                .where(orderItem.createdTime.between(weekBefore.atStartOfDay(), yesterday.atStartOfDay()))
                .groupBy(orderItem.menuId)
                .orderBy(orderItem.number.sum().desc())
                .limit(3)
                .fetch();
    }
}
