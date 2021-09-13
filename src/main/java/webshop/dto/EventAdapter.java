package webshop.dto;

import webshop.domain.EventEntry;

public class EventAdapter {
    public static EventDTO toDTO(EventEntry eventEntry){
        ProductDTO productDTO = ProductAdapter.toDTO(eventEntry.getProduct());
        return new EventDTO(eventEntry.getCartId(), productDTO, eventEntry.getQuantity(), eventEntry.getDetail());
    }
}
