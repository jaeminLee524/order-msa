package dev.practice.order.application.item;

import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;
import dev.practice.order.domain.item.ItemService;
import dev.practice.order.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemFacade {
    private final ItemService itemService;
    private final NotificationService notificationService;

    public String registerItem(ItemCommand.RegisterItemRequest itemCommand, String partnerToken) {
        String itemToken = itemService.registerItem(itemCommand, partnerToken);
        notificationService.sendEmail(null, null, null);

        return itemToken;
    }
}
