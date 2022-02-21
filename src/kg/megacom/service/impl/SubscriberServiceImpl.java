package kg.megacom.service.impl;

import kg.megacom.exceptions.MaxSubsCount;
import kg.megacom.exceptions.SubNotFount;
import kg.megacom.models.Subscriber;
import kg.megacom.service.SubscriberService;

public class SubscriberServiceImpl implements SubscriberService {

    private Subscriber[] subscribers = new Subscriber[50];

    @Override
    public Subscriber findOrCreateSubscriber(String phone) {

        for (int i = 0; i < subscribers.length; i++){
            if (subscribers[i] == null){
                Subscriber subscriber = new Subscriber(phone);
                subscribers[i] = subscriber;
                return subscriber;
            }else {
                if (subscribers[i].getPhone().equals(phone)){
                    return subscribers[i];
                }else {
                    continue;
                }
            }
        }
        throw new MaxSubsCount("Массив переполнен");
    }

    @Override
    public void blockSubcriber(String phone){
        System.out.println(subscribers[0]);
        for (int i = 0; i < subscribers.length; i++){
            if (subscribers[i] != null && subscribers[i].getPhone().equals(phone)){
                System.out.println(subscribers[i].getPhone());
                Subscriber subscriber = subscribers[i];
                subscriber.setActive(true);
                subscribers[i] = subscriber;
                return;
            }
        }
        throw new SubNotFount("User не найден");
    }

}
