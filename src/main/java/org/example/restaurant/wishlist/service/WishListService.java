package org.example.restaurant.wishlist.service;

import lombok.RequiredArgsConstructor;
import org.example.restaurant.naver.NaverClient;
import org.example.restaurant.naver.dto.SearchImageReq;
import org.example.restaurant.naver.dto.SearchLocalReq;
import org.example.restaurant.wishlist.dto.WishListDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final NaverClient naverClient;

    public WishListDto search(String query) {
        // 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.SearchLocal(searchLocalReq);

        if (searchLocalRes.getTotal() > 0) {
            var localItem = searchLocalRes.getItems().stream().findFirst().get();
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);
            // 이미지 검색

            var searchImageRes = naverClient.SearchImage(searchImageReq);

            if (searchImageRes.getTotal() > 0) {
                var imageItem = searchImageRes.getItems().stream().findFirst().get();
                // 결과물
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setReadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }
        return new WishListDto();

    }
}
