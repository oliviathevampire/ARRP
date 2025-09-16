package net.devtech.arrp.json.advancement;// imports (Yarn names)

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.StringIdentifiable;

import java.util.Optional;

public final class JDisplay {
	public static final Codec<JDisplay> CODEC = RecordCodecBuilder.create(i -> i.group(
			JIcon.CODEC.fieldOf("icon").forGetter(d -> d.icon),
			TextCodecs.CODEC.fieldOf("title").forGetter(d -> d.title),
			TextCodecs.CODEC.fieldOf("description").forGetter(d -> d.description),
			Codec.STRING.optionalFieldOf("background").forGetter(d -> Optional.ofNullable(d.background)),
			Frame.CODEC.optionalFieldOf("frame", Frame.TASK).forGetter(d -> d.frame),
			Codec.BOOL.optionalFieldOf("show_toast").forGetter(d -> Optional.ofNullable(d.showToast)),
			Codec.BOOL.optionalFieldOf("announce_to_chat").forGetter(d -> Optional.ofNullable(d.announceChat)),
			Codec.BOOL.optionalFieldOf("hidden").forGetter(d -> Optional.ofNullable(d.hidden))
	).apply(i, (icon, title, desc, bg, frame, toast, announce, hidden) -> {
		JDisplay d = new JDisplay();
		d.icon = icon;
		d.title = title;
		d.description = desc;
		d.background = bg.orElse(null);
		d.frame = frame;
		d.showToast = toast.orElse(null);
		d.announceChat = announce.orElse(null);
		d.hidden = hidden.orElse(null);
		return d;
	}));
	public JIcon icon;
	public Text title;
	public Text description;
	public String background;
	public Frame frame = Frame.TASK;
	public Boolean showToast, announceChat, hidden;

	public JDisplay icon(JIcon icon) {
		this.icon = icon;
		return this;
	}

	public JDisplay title(Text title) {
		this.title = title;
		return this;
	}

	public JDisplay description(Text description) {
		this.description = description;
		return this;
	}

	public JDisplay background(String background) {
		this.background = background;
		return this;
	}

	public JDisplay frame(Frame frame) {
		this.frame = frame;
		return this;
	}

	public JDisplay showToast(Boolean showToast) {
		this.showToast = showToast;
		return this;
	}

	public JDisplay announceChat(Boolean announceChat) {
		this.announceChat = announceChat;
		return this;
	}

	public JDisplay hidden(Boolean hidden) {
		this.hidden = hidden;
		return this;
	}

	public enum Frame implements StringIdentifiable {
		TASK("task"),
		GOAL("goal"),
		CHALLENGE("challenge");

		public static final Codec<Frame> CODEC = StringIdentifiable.createCodec(Frame::values);

		private final String id;

		Frame(String id) {
			this.id = id;
		}

		@Override
		public String asString() {
			return id;
		}
	}
}
