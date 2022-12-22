package jp.neamod2.features;

import jp.neamod2.utils.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.util.ResourceLocation;

public class FishFeature {

    public static final FishFeature INSTANCE = new FishFeature();

    public static FishFeature getInstance() {
        return INSTANCE;
    }

    public void PlaySoundEvent() {
        ChatUtils.addChatMessage("釣り -> 食い込みを検知。");
        ISound sound = new PositionedSound(new ResourceLocation("random.successful_hit")) {{
            volume = 100;
            pitch = 2f;
            repeat = false;
            repeatDelay = 0;
            attenuationType = ISound.AttenuationType.NONE;
        }};

        float oldLevel = Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.RECORDS);
        Minecraft.getMinecraft().gameSettings.setSoundLevel(SoundCategory.RECORDS, 1);
        Minecraft.getMinecraft().getSoundHandler().playSound(sound);
        Minecraft.getMinecraft().gameSettings.setSoundLevel(SoundCategory.RECORDS, oldLevel);
    }
}
