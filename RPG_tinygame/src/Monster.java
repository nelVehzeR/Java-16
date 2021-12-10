public class Monster extends Creature {
    public Monster(String name, int hitpoints, int dexterity, int strength) {
        super(name, hitpoints, dexterity, strength);
    }

    /***    Скелет  ***********************************************************************************/
    static class Skeletor extends Monster {
        public Skeletor(int hitpoints, int dexterity, int strength) {
            super("скелет", hitpoints, dexterity, strength);
        }

        @Override
        public void hit(Creature creature, int dmgVal) {
            int rndmVal = (int) (Math.random() * 10);
            String sndMonster;

            switch (rndmVal) {  /**   Рандомные действия перед атакой.    */
                case 1: sndMonster = "Скелет замахивается, гремя костями.";
                break;
                case 2: sndMonster = "Из черепа скелета раздалось громкое шипение.";
                break;
                case 3: sndMonster = "Cкелет щёлкнул челюстью.";
                break;
                default: sndMonster = "";
                break;
            };

            if (!sndMonster.equals("")) {
                System.out.println(sndMonster);
            }

            damage(dmgVal, creature);
        }
    }

    /***    Гоблин  ***********************************************************************************/
    static class Goblin extends Monster {
        public Goblin(int hitpoints, int dexterity, int strength) {
            super("гоблин", hitpoints, dexterity, strength);
        }

        @Override
        public void hit(Creature creature, int dmgVal) {
            int rndmVal = (int) (Math.random() * 2) + 1;
            String trickMonster;

            damage(dmgVal, creature);

            if (getRndmVal() >= 87) {
                switch (rndmVal) {  /**   Уловка с дополнительной атакой.    */
                    case 1: trickMonster = "Гоблин оглушает истошным криком и, пользуясь моментом, бьёт снова.";
                        break;
                    case 2: trickMonster = "Гоблин ослепляет метким броском грязи и наносит новый удар.";
                        break;
                    default: trickMonster = "";
                        break;
                };

                System.out.println(trickMonster);
                damage(dmgVal, creature);
            }
        }
    }

    @Override
    public void hit(Creature creature, int dmgVal) {}

    @Override
    public int doubleDamage() {
        return super.doubleDamage();
    }
}
