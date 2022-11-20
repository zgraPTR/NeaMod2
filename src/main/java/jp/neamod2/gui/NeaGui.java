package jp.neamod2.gui;

import jp.neamod2.gui.button.FeatureButton;
import jp.neamod2.handlers.BrowserHandle;
import jp.neamod2.handlers.ConfigHandler;
import jp.neamod2.utils.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NeaGui extends GuiScreen {
    private final int page;
    private final List<GuiButton> allButtons = new ArrayList<>();
    private final List<GuiButton> foundButtons = new ArrayList<>();
    String initSearchText;

    private GuiButton backPage;
    private GuiButton closeGUI;
    private GuiButton githubLink;
    private GuiButton nextPage;
    private GuiButton pageLink;
    private GuiTextField search;
    private GuiButton twitterLink;
    // Toggles
    private GuiButton autoBoop;
    private GuiButton dungeonEsp;
    private GuiButton enderCrystalEsp;
    private GuiButton msgTimer;

    public NeaGui(int page, String searchText) {
        this.page = page;
        initSearchText = searchText;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();

        // Default button size is 200, 20
        closeGUI = new GuiButton(0, width / 2 - 100, (int) (height * 0.9), "閉じる");
        backPage = new GuiButton(0, width / 2 - 100, (int) (height * 0.8), 80, 20, "< 戻る");
        nextPage = new GuiButton(0, width / 2 + 20, (int) (height * 0.8), 80, 20, "次へ >");
        pageLink = new GuiButton(0, 2, height - 70, 80, 20, String.format("※ ページ : %d", page));
        githubLink = new GuiButton(0, 2, height - 50, 80, 20, "GitHub");
        twitterLink = new GuiButton(0, 2, height - 30, 80, 20, "Twitter");
        search = new GuiTextField(0, this.fontRendererObj, width - 102, 5, 100, 20);

        autoBoop = new GuiButton(0, 0, 0, "Boop! 自動返信 : " + Utils.getColouredBoolean(Utils.isAutoBoop));
        msgTimer = new FeatureButton("※ チャット時間表示 : " + Utils.getColouredBoolean(Utils.isMsgTimer), "メッセージの色がつかないバグがあります。\n解決策を探しています。");
        dungeonEsp = new FeatureButton("ダンジョンESP : " + Utils.getColouredBoolean(Utils.isDungeonEsp), "fel espが規約違反の為、この機能を制限しています。");
        enderCrystalEsp = new GuiButton(0, 0, 0, "エンダークリスタルESP : " + Utils.getColouredBoolean(Utils.isEnderCrystalEsp));

        allButtons.clear();
        allButtons.add(autoBoop);
        allButtons.add(msgTimer);
        allButtons.add(dungeonEsp);
        allButtons.add(enderCrystalEsp);

        search.setText(initSearchText);
        search.setVisible(true);
        search.setEnabled(true);

        reInit();
    }

    public void reInit() {
        this.buttonList.clear();
        foundButtons.clear();

        for (GuiButton button : allButtons) {
            if (search.getText().length() != 0) {
                String buttonName = StringUtils.stripControlCodes(button.displayString.toLowerCase());
                if (buttonName.contains(search.getText().toLowerCase())) {
                    foundButtons.add(button);
                }
            } else {
                foundButtons.add(button);
            }
        }

        for (int i = (page - 1) * 7, iteration = 0; iteration < 7 && i < foundButtons.size(); i++, iteration++) {
            GuiButton button = foundButtons.get(i);
            button.xPosition = width / 2 - 100;
            button.yPosition = (int) (height * (0.1 * (iteration + 1)));
            this.buttonList.add(button);
        }

        if (page > 1) this.buttonList.add(backPage);
        if (page < Math.ceil(foundButtons.size() / 7D)) this.buttonList.add(nextPage);

        this.buttonList.add(closeGUI);
        this.buttonList.add(githubLink);
        this.buttonList.add(twitterLink);
        this.buttonList.add(pageLink);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (GuiButton button : this.buttonList) {
            if (button instanceof FeatureButton && button.isMouseOver()) {
                FeatureButton feature = (FeatureButton) button;
                drawHoveringText(feature.hoverText, mouseX - 5, mouseY);
            }
        }

        search.drawTextBox();
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button == backPage) {
            mc.displayGuiScreen(new NeaGui(page - 1, search.getText()));
        } else if (button == closeGUI) {
            Minecraft.getMinecraft().thePlayer.closeScreen();
        } else if (button == twitterLink) {
            BrowserHandle.openUrl("https://twitter.com/Nea7s_");
        } else if (button == githubLink) {
            BrowserHandle.openUrl("https://github.com/zgraPTR/NeaMod2");
        } else if (button == nextPage) {
            mc.displayGuiScreen(new NeaGui(page + 1, search.getText()));
            // toggle
        } else if (button == autoBoop) {
            Utils.isAutoBoop = !Utils.isAutoBoop;
            autoBoop.displayString = "Boop! 自動返信 : " + Utils.getColouredBoolean(Utils.isAutoBoop);
            ConfigHandler.writeBool("message", "AutoBoop", Utils.isAutoBoop);
        } else if (button == dungeonEsp) {
            Utils.isDungeonEsp = !Utils.isDungeonEsp;
            dungeonEsp.displayString = "ダンジョンESP : " + Utils.getColouredBoolean(Utils.isDungeonEsp);
            ConfigHandler.writeBool("dungeon", "DnEsp", Utils.isDungeonEsp);
        } else if (button == enderCrystalEsp) {
            Utils.isEnderCrystalEsp = !Utils.isEnderCrystalEsp;
            enderCrystalEsp.displayString = "エンダークリスタルESP : " + Utils.getColouredBoolean(Utils.isEnderCrystalEsp);
            ConfigHandler.writeBool("block", "EnderCrystalEsp", Utils.isEnderCrystalEsp);
        } else if (button == msgTimer) {
            Utils.isMsgTimer = !Utils.isMsgTimer;
            msgTimer.displayString = "※ チャット時間表示 : " + Utils.getColouredBoolean(Utils.isMsgTimer);
            ConfigHandler.writeBool("message", "MsgTimer", Utils.isMsgTimer);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        search.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        search.textboxKeyTyped(typedChar, keyCode);
        initSearchText = search.getText();
        reInit();
    }
}
