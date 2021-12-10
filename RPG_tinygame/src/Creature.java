abstract class Creature implements Fighting {
    /***    Основные параметры для существ. ***/
    private String name;            /** Имя                                 */
    private int hitpoints, maxHP;   /** Очки жизни, максимальный порог      */
    private int dexterity;          /** Очки ловкости                       */
    private int strength;           /** Очки силы                           */

    Creature(String name, int hitpoints, int dexterity, int strength) {
        this.name = name;
        this.maxHP = hitpoints;
        this.hitpoints = this.maxHP;
        this.dexterity = dexterity;
        this.strength = strength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public void increaseStats(int i) {
        this.maxHP += i * 10;
        this.hitpoints += getMaxHP();
        this.strength += i;
        this.dexterity += i;

        System.out.println(String.format(
                "Характеристики увеличены на %s.\nОчки жизни: %d\nОчки силы: %d\nОчки ловкости: %d",
                i,
                getMaxHP(),
                this.strength,
                this.dexterity
        ));
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return hitpoints;
    }
    public int getMaxHP() {
        return maxHP;
    }

    protected void damage (int amount, Creature creature) {
        amount = strengthModifier(amount, strength);    /** Модификатор силы */

        if (doubleDamage() >= 7) {
            amount *= 2; // 30%-й шанс на удвоение урона
            System.out.println(name + " готов нанести критический удар!");
        }

        if (hitChance(dexterity) >= getRndmVal()) {
            creature.hitpoints -= amount;
            System.out.println(name + " нанёс " + amount + " ед. урона. >>> " + creature.getName());
        } else {
            System.out.println(name + " промахнулся.");
        }

        if (creature.hitpoints <= 0) {
            return;
        }
    }
}
