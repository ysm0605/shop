package kr.inhatc.shop.repository;

import kr.inhatc.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByItemNm(String itemNm);     // 해당 이름에 대한 상품 리스트 가져오기


    List<Item> findByStockNumberGreaterThanEqualAndItemNmLike(int stockNumber,String ItemNm);     // 해당 이름에 대한 상품 리스트 가져오기 (과제1번)


    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); // :itemDetail 변수로 전달

    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price asc", nativeQuery = true)
    List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);


    //과제2번(jpql)
    @Query("select i from Item i where i.stockNumber>=150 and i.itemNm like %:itemNm%")
    List<Item> findByTest2(@Param("itemNm")  String itemNm);

    //과제3번(Native)
    @Query(value = "select * from Item i where i.number>=150 and i.item_nm like %:itemNm%", nativeQuery = true)
    List<Item> findByTest3(@Param("itemNm")  String itemNm);


}
