package ru.mpei.PbIneiBot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.util.List;
import java.util.Random;

/**
 * @author Diagorn
 */
public class Main {

    //For getting random gifs and phrases
    private static final Random random = new Random();

    //List of cute gifs
    private static final List<String> gifsList = List.of(
            "https://i.pinimg.com/originals/b9/b2/32/b9b232952b22a6dfcee8148f2650129b.gif",
            "https://s.mediasole.ru/cache/content/data/images/1187/1187438/original.gif",
            "https://cdn.trinixy.ru/pics5/20180831/animals_cuddlers_04.gif",
            "https://i.gifer.com/origin/31/312c6b25c3f7c3e133cfc6b67a5ad745_w200.gif",
            "https://i.gifer.com/2xtI.gif",
            "https://i.gifer.com/origin/68/68162cae035d9b2ae99d215142190280_w200.gif",
            "https://s1.tchkcdn.com/i/9/1/131221_68f677e5_630306511.gif",
            "https://vgif.ru/gifs/162/vgif-ru-36057.gif",
            "https://static.life.ru/posts/2019/09/1241357/c53c332cefaf21fd14c0006430cf8f00.gif",
            "https://i.gifer.com/NzUm.gif",
            "https://99px.ru/sstorage/86/2016/04/image_860304161321279969381.gif"
    );

    //List of compliment phrases
    private static final List<String> complimentsList = List.of(
            "Воняешь активистом, так держать!",
            "Надеюсь, поработаем с тобой в одной команде!",
            "Тебе место на доске почёта, ты знал об этом?",
            "Алушта лечит, экзамены калечат. Ты справишься, просто потерпи",
            "Дедлайн ещё не скоро, ты справишься!",
            "Ты лучше, чем минералка после иллюма!",
            "Ты часть нашей истории!",
            "Миша бы никогда не съел такую умничку",
            "Когда-нибудь и твою фотку повесят на двери в ПБ. Всё придёт, не спеши, получай удовольствие",
            "Даже стипендию не так приятно получать, как сообщение от тебя"
    );

    public static void main(String[] args) {
        //Getting bot token from command line args
        final String BOT_TOKEN = args[0];

        //Initialising API
        DiscordApi api = new DiscordApiBuilder()
                .setToken(BOT_TOKEN)
                .addIntents(Intent.MESSAGE_CONTENT)
                .login().join();

        //Declaring slash command
        SlashCommand.with("привет", "Получи порцию милоты на сегодня!").createGlobal(api).join();

        //Implementing slash command
        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction interaction = event.getSlashCommandInteraction();
            String content = getResponseContent();
            if (interaction.getCommandName().equalsIgnoreCase("привет")) {
                interaction.createImmediateResponder()
                        .setContent(content)
                        .respond();
            }
        });
    }

    /**
     * Method randomly picks a random gif or a random compliment
     * @return cute gif if next random double > 0.5. Random compliment otherwise
     */
    private static String getResponseContent() {
        return random.nextDouble() > 0.5D ? getRandomListMember(gifsList) : getRandomListMember(complimentsList);
    }

    /**
     * Gets a random List member
     * @param list - initial list
     * @return random member
     */
    private static String getRandomListMember(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }
}
