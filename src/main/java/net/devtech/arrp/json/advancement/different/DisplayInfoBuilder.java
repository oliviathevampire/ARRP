package net.devtech.arrp.json.advancement.different;

import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.AssetInfo;

import java.util.Optional;

public class DisplayInfoBuilder {
    private Text title;
    private Text description;
    private ItemStack icon;
    private AssetInfo background;
    private AdvancementFrame type;
    private boolean showToast;
    private boolean announceChat;
    private boolean hidden;

    public DisplayInfoBuilder title(Text title) {
        this.title = title;
        return this;
    }

    public DisplayInfoBuilder description(Text description) {
        this.description = description;
        return this;
    }

    public DisplayInfoBuilder icon(ItemStack itemStack) {
        this.icon = itemStack;
        return this;
    }

    public DisplayInfoBuilder icon(Item item) {
        this.icon = new ItemStack(item);
        return this;
    }

    public DisplayInfoBuilder background(AssetInfo background) {
        this.background = background;
        return this;
    }

    public DisplayInfoBuilder type(AdvancementFrame type) {
        this.type = type;
        return this;
    }

    public DisplayInfoBuilder showToast(boolean showToast) {
        this.showToast = showToast;
        return this;
    }

    public DisplayInfoBuilder announceToChat(boolean announceToChat) {
        this.announceChat = announceToChat;
        return this;
    }

    public DisplayInfoBuilder hidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public AdvancementDisplay build() {
        return new AdvancementDisplay(this.icon, this.title, this.description, Optional.ofNullable(this.background), this.type,
                this.showToast, this.announceChat, this.hidden);
    }
}