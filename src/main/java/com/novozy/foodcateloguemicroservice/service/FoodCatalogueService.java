package com.novozy.foodcateloguemicroservice.service;

import com.novozy.foodcateloguemicroservice.dto.FoodCataloguePage;
import com.novozy.foodcateloguemicroservice.dto.FoodItemDTO;
import com.novozy.foodcateloguemicroservice.dto.Restaurant;
import com.novozy.foodcateloguemicroservice.entity.FoodItem;
import com.novozy.foodcateloguemicroservice.mapper.FoodItemMapper;
import com.novozy.foodcateloguemicroservice.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepository foodItemRepository;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO saveFoodItemDetails(FoodItemDTO foodItemDTO) {
        FoodItem save = foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(save);
    }

    public FoodCataloguePage fetchCataloguePageDetails(Integer restaurantId) {

        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        FoodCataloguePage foodCataloguePage = createFoodCataloguePage(foodItemList, restaurant);
        return foodCataloguePage;
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://restaurant-service/restaurant/fetchById/" + restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepository.findByRestaurantId(restaurantId);
    }
}
