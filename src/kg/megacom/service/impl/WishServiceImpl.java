package kg.megacom.service.impl;

import kg.megacom.exceptions.BlockedException;
import kg.megacom.exceptions.MaxCountSubsWishes;
import kg.megacom.exceptions.WishNotFound;
import kg.megacom.models.Subscriber;
import kg.megacom.models.Wish;
import kg.megacom.service.SubscriberService;
import kg.megacom.service.WishService;

public class WishServiceImpl implements WishService {

    private Wish[] wishes = new Wish[10];
    //private SubscriberService service = new SubscriberServiceImpl();


    @Override
    public void createWish(String text, String phoneSender, String phoneReceipt) {

        Subscriber sender = SubscriberService.INSTANCE.findOrCreateSubscriber(phoneSender);
        Subscriber receipt = SubscriberService.INSTANCE.findOrCreateSubscriber(phoneReceipt);


        // Проверить, отправлял ли сообщения sender k receipt
        if (checkSendSms(sender, receipt)) {
            throw new MaxCountSubsWishes("Вы уже отправляли смс данному абоненту");
        }

        if (receipt.isActive()){
            throw new BlockedException("Получатель заблокиррован!");
        }

        sender.incrementSubsWish();
        // Создать Wish -> положить его в массив

        for (int i = 0; i < wishes.length; i++) {
            if (wishes[i] == null) {
                Wish wish = new Wish(text, sender, receipt);
                wishes[i] = wish;
                System.out.println("--------------------------------");
                System.out.println("Смс успешно отправлен!");
                System.out.println("--------------------------------");
                break;
            }
        }
        // Сообщение успешно отправлено
    }

    @Override
    public Wish[] receiptWishes(String phone) {

        Wish[] receiptWishes;
        Subscriber receiptFromDataBase = SubscriberService.INSTANCE.findOrCreateSubscriber(phone);
        System.out.println("ID получателя из базы " + receiptFromDataBase.getId());
        int countWish = 0;
        for (int i = 0; i < wishes.length; i++) {
            if (wishes[i] != null && receiptFromDataBase.getId() == wishes[i].getReceipt().getId()) {
                countWish++;
            }
        }

        // from db - 0.6395187209445142
        //           0.6395187209445142

        if (countWish == 0) {
            throw new WishNotFound("У вас пока нет смс");
        } else {
            receiptWishes = new Wish[countWish];
            int index = 0;
            for (Wish w : wishes) {
                if (w != null && w.getReceipt().getId() == receiptFromDataBase.getId()) {
                    receiptWishes[index] = w;
                    index++;
                }
            }
            return receiptWishes;
        }


        /*
               1. Subscriber = Находим абонента по номеру телефона
                   1.2 перебрать массив wishes ->
                        сравнить id абонентов
                            Положить в новый массив
                        Выкинуть ошибку, о том что у абонента не было смс
        * */

    }

    private boolean checkSendSms(Subscriber sender, Subscriber receipt) {
        for (int i = 0; i < wishes.length; i++) {
            if (wishes[i] == null) {
                continue;
            } else {
                if (wishes[i].getSender().getId() == sender.getId()
                        && wishes[i].getReceipt().getId() == receipt.getId()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
