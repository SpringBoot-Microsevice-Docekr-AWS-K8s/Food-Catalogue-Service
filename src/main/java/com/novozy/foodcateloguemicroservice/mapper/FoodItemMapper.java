package com.novozy.foodcateloguemicroservice.mapper;

import com.novozy.foodcateloguemicroservice.dto.FoodItemDTO;
import com.novozy.foodcateloguemicroservice.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {

    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItemDTO mapFoodItemToFoodItemDto(FoodItem foodItem);

    FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);
}
