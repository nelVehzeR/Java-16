public class BattleGround implements Fighting {
    public void fight(Human.Player player, Monster monster) {
        Runnable r = () -> {
            int i = 1;
            boolean isDead = false;

            while (!isDead) {
                if (i++ % 2 != 0) {
                    if (player.getHP() < player.getMaxHP() / 100 * 25) {
                        System.out.println(player.getName() + " при смерти. Нужно выпить зелье.");

                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (player.healthPotionInv > 0) {
                            player.useHealthPotion();
                        } else {
                            System.out.println("Зелья не осталось... Это конец...");
                        }
                    }

                    player.hit(monster, getRndmVal());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (monster.getHP() <= 0) {
                        isDead = true;
                        System.out.println(monster.getName() + " повержен.");

                        player.setExp(150);
                        System.out.println("Получено 150 очков опыта. Текущий опыт: " + player.getExp());
                        player.setGold(100);
                        System.out.println("Получено 100 золотых. В кошельке: " + player.getGold());

                        player.levelUP();

                        switch ((int) (Math.random() * 2)) {
                            case 0:
                                System.out.println("\nКуда теперь?");
                                break;
                            case 1:
                                System.out.println("\nВ каком направлении на этот раз?");
                                break;
                        }
                    }
                } else {
                    monster.hit(player, getRndmVal());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (player.getHP() <= 0) {
                        isDead = true;
                        System.out.println(player.getName() + " убит.\n");
                        System.out.println("Игра окончена.");
                        System.exit(1);
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(r);
        thread.start();
    }

    @Override
    public void hit(Creature creature, int dmgVal) {}
}