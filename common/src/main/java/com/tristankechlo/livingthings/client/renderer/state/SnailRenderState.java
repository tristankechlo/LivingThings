package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.SnailEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class SnailRenderState extends LivingEntityRenderState implements StateFromEntity<SnailEntity> {

    public int shellColorForeground;
    public int shellColorBackground;
    public ResourceLocation bodyTexture;
    public ResourceLocation shellPatternTextureForeground;
    public ResourceLocation shellPatternTextureBackground;

    public int getShellColor(SnailEntity.PatternType type) {
        return type == SnailEntity.PatternType.FOREGROUND ? shellColorForeground : shellColorBackground;
    }

    public ResourceLocation getShellPatternTexture(SnailEntity.PatternType type) {
        return type == SnailEntity.PatternType.FOREGROUND ? shellPatternTextureForeground : shellPatternTextureBackground;
    }

    @Override
    public void fromEntity(SnailEntity snail) {
        this.shellColorForeground = snail.getShellColor(SnailEntity.PatternType.FOREGROUND);
        this.shellColorBackground = snail.getShellColor(SnailEntity.PatternType.BACKGROUND);
        this.bodyTexture = snail.getBodyTexture();
        this.shellPatternTextureForeground = snail.getShellPatternTexture(SnailEntity.PatternType.FOREGROUND);
        this.shellPatternTextureBackground = snail.getShellPatternTexture(SnailEntity.PatternType.BACKGROUND);
    }
}
