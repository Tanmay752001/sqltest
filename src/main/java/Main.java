import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;
import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main extends ListenerAdapter{
    public static JDA jda;


    public static void main(String[] args) {
        LiteSQL.openConnection();
        SQLManager.createTables();
        SQLManager.insertValues();
        SQLManager.insertValues();
        JDABuilder jdaBuilder = JDABuilder.createDefault(System.getenv().get("TOKEN"));
        jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jdaBuilder.setActivity(Activity.playing("with 69 others"));
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);
        jdaBuilder.setMemberCachePolicy(MemberCachePolicy.ALL);
        jdaBuilder.addEventListeners(new Main());
        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(!Objects.requireNonNull(event.getMember()).getUser().isBot()) {
            String[] arguments = event.getMessage().getContentRaw().split("\\s+");
            if(arguments[0].equalsIgnoreCase("$$")){
                if(SQLManager.getValues()!=null){
                    event.getChannel().sendMessage(SQLManager.getValues()).queue();
                }
            }
        }
    }
}