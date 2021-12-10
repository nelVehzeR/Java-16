import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class World {
    private static BufferedReader cmd;
    private static Human.Player player = null;
    private static BattleGround battle = null;
    private static Human.Seller seller = null;

    public static void main(String[] args) {
        seller = new Human.Seller("Торговец", 10, 1, 1);
        cmd = new BufferedReader(new InputStreamReader(System.in));
        battle = new BattleGround();

        System.out.println("Приветствую, герой иного мира! Как Вас зовут?");
        try {
            action(cmd.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void action(String command) throws IOException {
        if (player == null || player.equals("")) {
            player = new Human.Player(command, 100, 15,12);

            System.out.println(String.format(
                    "Собрав и проверив своё снаряжение, Вы поняли, что готовы отправиться в путь. Куда направитесь, %s?",
                    player.getName()
            ));
        }

        switch (command) {
            case "1": {
                if ((int) (Math.random() * 100) + 1 % 2 == 0) {
                    System.out.println("Лавка закрыта, торговца на месте не оказалось.");
                } else {
                    System.out.println("Вы зашли в лавку. Всего один товар привлёк Ваше внимание");
                    if (player.getGold() >= 150) {
                        player.setGold(-150);
                        ++player.healthPotionInv;
                        System.out.println(String.format(
                                "Куплено %s. Отдано 150 золота персонажу %s.\nВ кошельке: %s.\nЗелий в кармане: %s",
                                seller.sell(Human.Seller.Store.HealthPotion),
                                seller.getName(),
                                player.getGold(),
                                player.healthPotionInv
                        ));
                    } else {
                        System.out.println("Однако, у Вас не хватает золота. Торговаться нет смысла.\nКуда отправитесь?");
                    }
                }
            }
            break;
            case "2": {
                System.out.println("В этих пещерах водятся гоблины.\n");
                battle.fight(player, new Monster.Goblin(100, 12, 7));
                break;
            }
            case "3": {
                System.out.println("Бродя по таинственному, заброшенному городу, Вам встретился оживший скелет. Заметив Вас. он моменталльно атаковал.\n");
                battle.fight(player, new Monster.Skeletor(100, 10, 15));
                break;
            }
            case "4": {
                System.out.println("В лесу тихо и спокойно. Изредка раздаётся пение птиц... Благодать!\n");
                break;
            }
            case "5":
                System.out.println(String.format(
                        "Прощайте, %s.\nИгра окончена.",
                        player.getName()
                ));
                System.exit(1);
                break;
            default:
                PointsOfInterest();
                break;
        }

        System.out.println("-----------------------------------------------------------------------------------------");
        action(cmd.readLine());
    }

    private static void PointsOfInterest() {
        System.out.println("1. Посетить торговца");
        System.out.println("2. Отправиться в пещеры");
        System.out.println("3. Исследовать заброшенный город");
        System.out.println("4. Прогуляться по лесу");
        System.out.println("5. К чёрту приключения!");
    }
}
