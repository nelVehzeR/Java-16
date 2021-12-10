public class Human extends Creature {
    private int exp;    /** Очки опыта  */
    private int gold;   /** Золото      */

    public Human (String name, int hitpoints, int dexterity, int strength, int exp, int gold) {
        super(name, hitpoints, dexterity, strength);
        this.exp = exp;
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp += exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }

    /** Игрок   *******************************************************************************************************/
    static class Player extends Human {
        int healthPotionInv = 0;

        public Player (String name, int hitpoints, int dexterity, int strength) {
            super(name, hitpoints, dexterity, strength, 0, 200);
        }

        public void levelUP() { /** Прокачка персонажа  */
            switch (getExp()) {
                case 150: {
                    setHitpoints(100);
                    System.out.println("Путешествие закаляет, Вы стали сильнее!");
                    increaseStats(1);
                    break;
                }
                case 300: {
                    setHitpoints(110);
                    System.out.println("Вы становитесь более грозным воином!");
                    increaseStats(3);
                    break;
                }
                case 450: {
                    setHitpoints(140);
                    System.out.println("Вы стали ещё опытнее! Теперь мало кто решится перейти Вам дорогу.");
                    increaseStats(5);
                    break;
                }
            }
        }

        public void useHealthPotion() {
            --healthPotionInv;
            setHitpoints(getMaxHP());
            System.out.println("Зелье полностью излечило Вас. Осталось склянок: " + healthPotionInv);
        }
    }

    /** Торговец    ***************************************************************************************************/
    static class Seller extends Human implements Selling {
        public Seller (String name, int hitpoints, int dexterity, int strength) {
            super(name, hitpoints, dexterity, strength, 0, 0);
        }

        @Override
        public String sell(Store goods) {
            String result = "";
            if(goods == Store.HealthPotion) {
                result = "Зелье здоровья";
            }
            return result;
        }

        public enum Store {
            HealthPotion;
        }
    }

    @Override
    public void hit(Creature creature, int dmgVal) {
        damage(dmgVal, creature);
    }

    @Override
    public int doubleDamage() {
        return super.doubleDamage();
    }
}
