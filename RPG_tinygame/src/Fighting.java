public interface Fighting {
    void hit (Creature creature, int dmgVal);

    default int strengthModifier (int damageValue, int strengthValue) { /** Модификатор силы для атаки  */
        return (int) ((Math.random() * damageValue) + ((damageValue / 100) * strengthValue)) + strengthValue;
    }

    default int doubleDamage () {   /** Просчёт шанса на критаческую атаку  */
        return (int) (Math.random() * 10);
    }

    default int hitChance (int dexValue) {  /** Просчёт шанса на удар   */
        return dexValue * 3;
    }

    default int getRndmVal () { /** Случайное значение  */
        return (int) (Math.random() * 100) + 1;
    }
}